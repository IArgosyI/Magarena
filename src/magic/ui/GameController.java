package magic.ui;

import magic.ai.MagicAI;
import magic.ai.RLAI;
import magic.ai.RLState;
import magic.data.GeneralConfig;
import magic.data.IconImages;
import magic.data.SoundEffects;
import magic.model.MagicCard;
import magic.model.MagicCardList;
import magic.model.MagicCardDefinition;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicSource;
import magic.model.choice.MagicChoice;
import magic.model.event.MagicEvent;
import magic.model.event.MagicPriorityEvent;
import magic.model.target.MagicTarget;
import magic.model.target.MagicTargetNone;
import magic.ui.viewer.CardViewer;
import magic.ui.viewer.ChoiceViewer;
import magic.ui.viewer.GameViewer;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GameController {

    private long MAX_TEST_MODE_DURATION=10000;

    private final GamePanel gamePanel;
    private final MagicGame game;
    private final boolean testMode;
    private final boolean selfMode = Boolean.getBoolean("selfMode");
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean gameConceded = new AtomicBoolean(false);
    private final Collection<ChoiceViewer> choiceViewers = new ArrayList<ChoiceViewer>();
    private Set<?> validChoices;
    private CardViewer cardViewer;
    private CardViewer imageCardViewer;
    private GameViewer gameViewer;
    private boolean actionClicked;
    private boolean combatChoice;
    private boolean resetGame;
    private MagicTarget choiceClicked = MagicTargetNone.getInstance();
    private MagicCardDefinition sourceCardDefinition = MagicCardDefinition.UNKNOWN;
    private BlockingQueue<Boolean> input = new SynchronousQueue<Boolean>();

    public GameController(final GamePanel aGamePanel,final MagicGame aGame) {
        gamePanel = aGamePanel;
        game = aGame;
        testMode = false;
        clearValidChoices();
    }

    /** Fully artificial test game. */
    public GameController(final MagicGame aGame) {
        gamePanel = null;
        game = aGame;
        testMode = true;
        clearValidChoices();
    }

    public MagicGame getGame() {
        return game;
    }

    public void enableForwardButton() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameViewer.enableButton(IconImages.FORWARD);
            }
        });
    }

    public void disableActionButton(final boolean thinking) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameViewer.disableButton(thinking);
            }
        });
    }

    private void disableActionUndoButtons() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameViewer.disableButton(false);
                gameViewer.enableUndoButton(true);
            }
        });
    }

    public void pause(final int t) {
        disableActionUndoButtons();
        try { //sleep
            Thread.sleep(t);
        } catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }


    private static void invokeAndWait(final Runnable task) {
        try { //invoke and wait
            SwingUtilities.invokeAndWait(task);
        } catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        } catch (final InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    /** Returns true when undo was clicked. */
    private boolean waitForInputOrUndo() {
        try {
            return input.take();
        } catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void waitForInput() throws UndoClickedException {
        try {
            final boolean undoClicked = input.take();
            if (undoClicked) {
                throw new UndoClickedException();
            }
        } catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void resume(final boolean undoClicked) {
        input.offer(undoClicked);
    }

    public void passKeyPressed() {
        if (gamePanel.canClickAction()) {
            actionClicked();
            game.setSkipTurn(true);
        }
    }

    public void actionKeyPressed() {
        if (gamePanel.canClickAction()) {
            actionClicked();
        }
    }

    public void actionClicked() {
        gameViewer.clearContentPanel();
        actionClicked = true;
        choiceClicked = MagicTargetNone.getInstance();
        resume(false);
    }

    public void undoKeyPressed() {
        if (gamePanel.canClickUndo()) {
            undoClicked();
        }
    }

    public void undoClicked() {
        if (game.hasUndoPoints()) {
            actionClicked = false;
            choiceClicked = MagicTargetNone.getInstance();
            setSourceCardDefinition(MagicEvent.NO_SOURCE);
            clearValidChoices();
            resume(true);
        }
    }

    public void processClick(final MagicTarget choice) {
        if (validChoices.contains(choice)) {
            actionClicked = false;
            choiceClicked = choice;
            hideInfo();
            resume(false);
        }
    }

    public boolean isActionClicked() {
        return actionClicked;
    }

    @SuppressWarnings("unchecked")
    public <T> T getChoiceClicked() {
        return (T)choiceClicked;
    }

    public void setMaxTestGameDuration(final long duration) {
        MAX_TEST_MODE_DURATION = duration;
    }

    public void setCardViewer(final CardViewer cardViewer) {
        this.cardViewer=cardViewer;
    }

    public void setImageCardViewer(final CardViewer cardViewer) {
        this.imageCardViewer=cardViewer;
    }

    public void setGameViewer(final GameViewer gameViewer) {
        this.gameViewer=gameViewer;
    }

    public void viewCard(final MagicCard card) {
        cardViewer.setCard(card.getCardDefinition(),card.getImageIndex());
    }

    public void viewCard(final MagicCardDefinition cardDefinition,final int index) {
        cardViewer.setCard(cardDefinition,index);
    }

    public void viewInfoAbove(final MagicCardDefinition cardDefinition,final int index,final Rectangle rect) {
        final Dimension size=gamePanel.getSize();
        final Point pointOnScreen=gamePanel.getLocationOnScreen();
        rect.x-=pointOnScreen.x;
        rect.y-=pointOnScreen.y;
        final int imageWidth=imageCardViewer.getWidth();
        final int imageHeight=imageCardViewer.getHeight();
        int x=rect.x+(rect.width-imageWidth)/2;
        final int y1=rect.y-imageHeight-6;
        final int y2=rect.y+rect.height+6;
        final int dy2=size.height-y2-imageHeight;
        if (x+imageWidth>=size.width) {
            x=rect.x+rect.width-imageWidth;
        }
        int y;
        // Position is next to card?
        if (y1<10&&dy2<10) {
            x=rect.x-6-imageWidth;
            if (y1>=dy2) {
                y=rect.y+rect.height-imageHeight;
                if (y<10) {
                    y=10;
                }
            } else {
                y=rect.y;
                if (y+imageHeight+10>size.height) {
                    y=size.height-10-imageHeight;
                }
            }
        // Position is above card?
        } else if (y1>=10) {
            y=y1;
        // Position if beneath card.
        } else {
            y=y2;
        }
        imageCardViewer.setCard(cardDefinition,index);
        imageCardViewer.setLocation(x,y);
        imageCardViewer.showDelayed(GeneralConfig.getInstance().getPopupDelay());
    }

    public void viewInfoRight(final MagicCardDefinition cardDefinition,final int index,final Rectangle rect) {
        final Dimension size=gamePanel.getSize();
        final Point pointOnScreen=gamePanel.getLocationOnScreen();
        rect.x-=pointOnScreen.x;
        rect.y-=pointOnScreen.y;
        final int x=rect.x+rect.width+10;
        final int maxY=size.height-8-imageCardViewer.getHeight();
        int y=rect.y+(rect.height-imageCardViewer.getHeight())/2;
        if (y<10) {
            y=10;
        } else if (y>maxY) {
            y=maxY;
        }
        imageCardViewer.setCard(cardDefinition,index);
        imageCardViewer.setLocation(x,y);
        imageCardViewer.showDelayed(GeneralConfig.getInstance().getPopupDelay());
    }

    public void hideInfo() {
        imageCardViewer.hideDelayed();
    }

    public void setSourceCardDefinition(final MagicSource source) {
        sourceCardDefinition=source.getCardDefinition();
    }

    public MagicCardDefinition getSourceCardDefinition() {
        return sourceCardDefinition;
    }

    public void focusViewers(final int handGraveyard,final int stackCombat) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gamePanel.focusViewers(handGraveyard,stackCombat);
            }
        });
    }

    public void showCards(final MagicCardList cards) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gamePanel.showCards(cards);
            }
        });
    }

    public void registerChoiceViewer(final ChoiceViewer choiceViewer) {
        choiceViewers.add(choiceViewer);
    }

    private void showValidChoices() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                for (final ChoiceViewer choiceViewer : choiceViewers) {
                    choiceViewer.showValidChoices(validChoices);
                }
            }
        });
    }

    public boolean isCombatChoice() {
        return combatChoice;
    }

    public void clearValidChoices() {
        validChoices=Collections.emptySet();
        combatChoice=false;
        showValidChoices();
    }

    public void setValidChoices(final Set<?> aValidChoices,final boolean aCombatChoice) {
        this.validChoices=aValidChoices;
        this.combatChoice=aCombatChoice;
        showValidChoices();
    }

    public Set<?> getValidChoices() {
        return validChoices;
    }

    public void update() {
        gamePanel.updateInfo();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gamePanel.update();
            }
        });
    }

    public static String getMessageWithSource(final MagicSource source,final String message) {
        if (source == null) {
            throw new RuntimeException("source is null");
        }
        if (source == MagicEvent.NO_SOURCE) {
            return message;
        } else {
            return "("+source+")|"+message;
        }
    }

    public void showMessage(final MagicSource source,final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameViewer.showMessage(getMessageWithSource(source,message));
            }
        });
    }

    public <E extends JComponent> E waitForInput(final Callable<E> func) throws UndoClickedException {
        final AtomicReference<E> ref = new AtomicReference<E>();
        final AtomicReference<Exception> except = new AtomicReference<Exception>();
        invokeAndWait(new Runnable() {
            public void run() {
                try {
                    final E content = func.call();
                    ref.set(content);
                    gameViewer.setContentPanel(content);
                } catch (Exception ex) {
                    except.set(ex);
                }
            }
        });
        waitForInput();
        if (except.get() != null) {
            throw new RuntimeException(except.get());
        } else {
            return ref.get();
        }
    }

    private Object[] getArtificialNextEventChoiceResults(final MagicEvent event) {
        if (!testMode) {
            disableActionButton(true);
            showMessage(event.getSource(),event.getChoiceDescription());
            GameController.invokeAndWait(new Runnable() {
                public void run() {
                    //do nothing, ensure that event dispatch queue is cleared
                }
            });
        }

        //dynamically get the AI based on the player's index
        final MagicPlayer player = event.getPlayer();
        final MagicAI ai = game.getDuel().getAIs()[player.getIndex()];
        return ai.findNextEventChoiceResults(game, player);
    }

    private Object[] getPlayerNextEventChoiceResults(final MagicEvent event) throws UndoClickedException {
        final MagicSource source=event.getSource();
        setSourceCardDefinition(source);
        final Object[] choiceResults;
        try {
            choiceResults = event.getChoice().getPlayerChoiceResults(this,game,event.getPlayer(),source);
        } finally {
            clearValidChoices();
            setSourceCardDefinition(MagicEvent.NO_SOURCE);
        }
        return choiceResults;
    }

    private void executeNextEventWithChoices(final MagicEvent event) {
        final Object[] choiceResults;
        if (selfMode || testMode || event.getPlayer().getPlayerDefinition().isArtificial()) {
            choiceResults = getArtificialNextEventChoiceResults(event);
            //RLAI ai= new RLAI();
            RLAI.RLAILearn(MagicGame.getInstance(),MagicGame.getInstance().getPlayer(0),choiceResults);
        } else {
            try {
                choiceResults = getPlayerNextEventChoiceResults(event);
                //System.out.println("**********"+key);
                //RLAI ai= new RLAI();
                //RLAI.RLAILearn(MagicGame.getInstance(),MagicGame.getInstance().getPlayer(0),choiceResults);
            } catch (UndoClickedException undo) {
                if (gameConceded.get()) {
                    return;
                } else {
                    performUndo();
                    return;
                }
            }
        }
        game.executeNextEvent(choiceResults);
    }

    public void resetGame() {
        if (game.hasUndoPoints()) {
            resetGame=true;
            undoClicked();
        }
    }

    public void concede() {
        if (!gameConceded.get() && !game.isFinished()) {
            game.setLosingPlayer(game.getPlayer(0));
            game.clearUndoPoints();
            gameConceded.set(true);
            resume(true);
        }
    }

    private void performUndo() {
        if (resetGame) {
            resetGame=false;
            while (game.hasUndoPoints()) {
                game.gotoLastUndoPoint();
            }
        } else {
            game.gotoLastUndoPoint();
        }
    }

    public void haltGame() {
        running.set(false);
    }

    public void runGame() {
        final long startTime=System.currentTimeMillis();

        running.set(true);
        while (running.get()) {
            if (game.isFinished()) {
                if (testMode) {
                    game.advanceDuel();
                    return;
                }

                game.logMessages();
                clearValidChoices();
                showMessage(MagicEvent.NO_SOURCE,
                    "{L} " +
                    game.getLosingPlayer() + " " +
                    (gameConceded.get() ? "conceded" : "lost" ) +
                    " the game.");

                if (game.getLosingPlayer().getIndex() == 0) {
                    SoundEffects.playClip(SoundEffects.LOSE_SOUND);
                } else {
                    SoundEffects.playClip(SoundEffects.WIN_SOUND);
                }

                enableForwardButton();

                if (!selfMode && waitForInputOrUndo()) {
                    performUndo();
                    update();
                    continue;
                } else {
                    game.advanceDuel();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            gamePanel.close();
                        }
                    });
                    return;
                }
            }

            if (game.hasNextEvent()) {
                final MagicEvent event=game.getNextEvent();
                if (event instanceof MagicPriorityEvent) {
                    game.logMessages();
                }
                if (event.hasChoice()) {
                    executeNextEventWithChoices(event);
                } else {
                    game.executeNextEvent();
                }
            } else {
                game.executePhase();
            }

            if (testMode) {
                if (false&&System.currentTimeMillis() - startTime > MAX_TEST_MODE_DURATION) {
                    System.err.println("WARNING. Max time for AI game exceeded");
                    break;
                }
            } else {
                update();
            }
        }
        running.set(false);
    }
}

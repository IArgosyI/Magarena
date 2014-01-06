package magic.model;

import magic.data.GeneralConfig;
import magic.model.action.MagicAction;
import magic.model.action.MagicActionList;
import magic.model.action.MagicAddEventAction;
import magic.model.action.MagicAddFirstEventAction;
import magic.model.action.MagicExecuteFirstEventAction;
import magic.model.action.MagicLogMarkerAction;
import magic.model.action.MagicMarkerAction;
import magic.model.action.MagicPutItemOnStackAction;
import magic.model.action.MagicRemoveFromPlayAction;
import magic.model.action.MagicMoveCardAction;
import magic.model.choice.MagicCombatCreature;
import magic.model.choice.MagicDeclareAttackersResult;
import magic.model.choice.MagicDeclareBlockersResult;
import magic.model.choice.MagicTargetChoice;
import magic.model.event.MagicEvent;
import magic.model.event.MagicSpellCardEvent;
import magic.model.event.MagicEventQueue;
import magic.model.event.MagicUniquenessEvent;
import magic.model.mstatic.MagicLayer;
import magic.model.mstatic.MagicPermanentStatic;
import magic.model.mstatic.MagicPermanentStaticMap;
import magic.model.mstatic.MagicStatic;
import magic.model.phase.MagicGameplay;
import magic.model.phase.MagicPhase;
import magic.model.phase.MagicPhaseType;
import magic.model.phase.MagicStep;
import magic.model.stack.MagicItemOnStack;
import magic.model.stack.MagicCardOnStack;
import magic.model.stack.MagicStack;
import magic.model.stack.MagicTriggerOnStack;
import magic.model.target.MagicTarget;
import magic.model.target.MagicTargetFilter;
import magic.model.target.MagicOtherPermanentTargetFilter;
import magic.model.target.MagicTargetHint;
import magic.model.target.MagicTargetNone;
import magic.model.target.MagicTargetType;
import magic.model.trigger.MagicPermanentTrigger;
import magic.model.trigger.MagicPermanentTriggerList;
import magic.model.trigger.MagicPermanentTriggerMap;
import magic.model.trigger.MagicTrigger;
import magic.model.trigger.MagicTriggerType;
import magic.model.trigger.MagicWhenOtherComesIntoPlayTrigger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class MagicGame {

    public static final boolean LOSE_DRAW_EMPTY_LIBRARY=true;
    private static final long ID_FACTOR=31;

    private static int COUNT;
    private static MagicGame INSTANCE;

    private final MagicDuel duel;
    private final MagicPlayer[] players;
    private MagicPermanentTriggerMap triggers;
    private final MagicPermanentTriggerMap additionalTriggers;
    private final MagicPermanentTriggerList turnTriggers;
    private final MagicPermanentStaticMap statics;
    private final MagicCardList exiledUntilEndOfTurn;
    private final MagicEventQueue events;
    private final MagicStack stack;
    private final MagicPlayer scorePlayer;
    private final MagicGameplay gameplay;
    private final MagicActionList actions;
    private final MagicActionList delayedActions;
    private final boolean sound;
    private int score;
    private int turn=1;
    private int startTurn;
    private int mainPhaseCount=100000000;
    private int landPlayed;
    private int maxLand;
    private int spellsPlayed;
    private int priorityPassedCount;
    private boolean creatureDiedThisTurn;
    private boolean priorityPassed;
    private boolean skipTurn;
    private boolean stateCheckRequired;
    private boolean artificial;
    private boolean fastChoices;
    private boolean immediate;
    private boolean disableLog;
    private MagicPlayer visiblePlayer;
    private MagicPlayer turnPlayer;
    private MagicPlayer losingPlayer = MagicPlayer.NONE;
    private MagicPhase phase;
    private MagicStep step;
    private MagicPayedCost payedCost;
    private MagicActionList undoPoints;
    private MagicLogBook logBook;
    private MagicLogMessageBuilder logMessageBuilder;
    private MagicSource activeSource = MagicEvent.NO_SOURCE;
    private long[] keys;
    private long stateId;
    private long time = 1000000;


    public static MagicGame getInstance() {
        return INSTANCE;
    }

    static int getCount() {
        return COUNT;
    }

    static MagicGame create(
            final MagicDuel duel,
            final MagicGameplay gameplay,
            final MagicPlayer[] players,
            final MagicPlayer startPlayer,
            final boolean sound) {
        COUNT++;
        INSTANCE = new MagicGame(duel, gameplay, players, startPlayer, sound);
        return INSTANCE;
    }

    private MagicGame(
            final MagicDuel aDuel,
            final MagicGameplay aGameplay,
            final MagicPlayer[] aPlayers,
            final MagicPlayer startPlayer,
            final boolean aSound) {

        artificial=false;
        duel = aDuel;
        gameplay = aGameplay;
        players = aPlayers;
        for (final MagicPlayer player : players) {
            player.setGame(this);
        }
        sound = aSound;

        triggers=new MagicPermanentTriggerMap();
        additionalTriggers=new MagicPermanentTriggerMap();
        turnTriggers=new MagicPermanentTriggerList();
        statics = new MagicPermanentStaticMap();
        exiledUntilEndOfTurn=new MagicCardList();
        events=new MagicEventQueue();
        stack=new MagicStack();
        visiblePlayer=players[0];
        scorePlayer=visiblePlayer;
        turnPlayer=startPlayer;
        actions=new MagicActionList();
        delayedActions=new MagicActionList();
        undoPoints=new MagicActionList();
        logBook=new MagicLogBook();
        logMessageBuilder=new MagicLogMessageBuilder(this);
        payedCost=new MagicPayedCost();
        changePhase(gameplay.getStartPhase(this));
    }

    public MagicGame(final MagicGame game,final MagicPlayer aScorePlayer) {

        artificial=true;
        sound=false;

        //copy the reference, these are singletons
        duel=game.duel;
        gameplay=game.gameplay;
        phase=game.phase;
        step=game.step;

        //copying primitives, array of primitive
        time = game.time;
        turn = game.turn;
        startTurn = game.startTurn;
        landPlayed = game.landPlayed;
        maxLand = game.maxLand;
        spellsPlayed = game.spellsPlayed;
        creatureDiedThisTurn = game.creatureDiedThisTurn;
        priorityPassed = game.priorityPassed;
        priorityPassedCount = game.priorityPassedCount;
        stateCheckRequired = game.stateCheckRequired;

        //copied and stored in copyMap
        final MagicCopyMap copyMap=new MagicCopyMap();
        players=copyMap.copyObjects(game.players,MagicPlayer.class);
        for (final MagicPlayer player : players) {
            player.setGame(this);
        }
        scorePlayer=copyMap.copy(aScorePlayer);
        visiblePlayer=copyMap.copy(game.visiblePlayer);
        turnPlayer=copyMap.copy(game.turnPlayer);
        losingPlayer=copyMap.copy(game.losingPlayer);
        payedCost=copyMap.copy(game.payedCost);
        activeSource=copyMap.copy(game.activeSource);

        //construct a new object using copyMap to copy internals
        events=new MagicEventQueue(copyMap, game.events);
        stack=new MagicStack(copyMap, game.stack);
        triggers=new MagicPermanentTriggerMap(copyMap, game.triggers);
        additionalTriggers=new MagicPermanentTriggerMap(copyMap, game.additionalTriggers);
        statics=new MagicPermanentStaticMap(copyMap, game.statics);
        exiledUntilEndOfTurn=new MagicCardList(copyMap, game.exiledUntilEndOfTurn);

        //construct a new object
        turnTriggers=new MagicPermanentTriggerList(triggers, game.turnTriggers);

        //the following are NOT copied when game state is cloned
        //fastChoices
        //immediate
        //skipTurn
        //mainPhaseCount

        //score is RESET to zero
        score=0;

        //historical actions are not carried over
        actions=new MagicActionList();

        //there should be no pending actions
        assert game.delayedActions.isEmpty() : "delayedActions: " + game.delayedActions;
        delayedActions=new MagicActionList();

        //no logging
        disableLog = true;
        undoPoints=null;
        logBook=null;
        logMessageBuilder=null;
    }

    public void setSkipTurn(final boolean skip) {
        skipTurn = skip;
    }

    private boolean getSkipTurn() {
        return skipTurn;
    }

    public void setScore(final int aScore) {
        score = aScore;
    }

    public void changeScore(final int amount) {
        score+=amount;
    }

    public int getScore() {
        return score;
    }

    public long getUniqueId() {
        time++;
        return time;
    }

    //follow factors in MagicMarkerAction
    public long getStateId() {
        keys = new long[] {
            turn,
            phase.hashCode(),
            step.hashCode(),
            turnPlayer.getIndex(),
            landPlayed,
            maxLand,
            spellsPlayed,
            priorityPassedCount,
            (creatureDiedThisTurn ? 1L : -1L),
            (priorityPassed ? 1L : -1L),
            (stateCheckRequired ? 1L : -1L),
            payedCost.getX(),
            payedCost.getKicker(),
            //payedCost.getTarget().getId(),
            stack.getStateId(),
            events.getStateId(),
            players[0].getStateId(),
            players[1].getStateId(),
            //triggers.getStateId(),
            //statics.getStateId(),
            exiledUntilEndOfTurn.getUnorderedStateId(),
        };
        stateId = magic.MurmurHash3.hash(keys);
        return stateId;
    }

    public String toString() {
        return "GAME: " +
               "id=" + stateId + " " +
               "t=" + turn + " " +
               "p=" + phase.getType() + " " +
               "s=" + step + " " +
               "tp=" + turnPlayer.getIndex() + " " +
               "lp=" + landPlayed + " " +
               "ppc=" + priorityPassedCount + " " +
               "pp=" + priorityPassed + " " +
               "sc=" + stateCheckRequired + " " +
               "x=" + getPayedCost().getX() + " " +
               "e=" + events.size() + " " +
               "s=" + stack.size();
    }

    public String getIdString() {
        final StringBuilder sb = new StringBuilder(toString());
        sb.append('\n');
        sb.append(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            sb.append(' ');
            sb.append(keys[i]);
        }
        sb.append('\n');
        sb.append(players[0].getIdString());
        sb.append('\n');
        sb.append(players[1].getIdString());
        return sb.toString();
    }

    public long getGameId(final int pruneScore) {
        long id=0;
        id = id*ID_FACTOR + turn;
        id = id*ID_FACTOR + phase.getType().ordinal();
        id = id*ID_FACTOR + score + pruneScore;
        id = players[0].getPlayerId(id);
        id = players[1].getPlayerId(id);
        return id;
    }

    public static boolean canSkipSingleChoice() {
        return GeneralConfig.getInstance().getSkipSingle();
    }

    public static boolean canSkipSingleManaChoice() {
        return GeneralConfig.getInstance().getSkipSingle();
    }

    //human is declaring blockers, skip if AI is not attacking
    public boolean canSkipDeclareBlockersSingleChoice() {
        return GeneralConfig.getInstance().getSkipSingle() && turnPlayer.getNrOfAttackers() == 0;
    }

    public boolean canAlwaysPass() {
        if (GeneralConfig.getInstance().getAlwaysPass()) {
            return phase.getType() == MagicPhaseType.Draw ||
                   phase.getType() == MagicPhaseType.BeginOfCombat;
        }
        return false;
    }

    private int getArtificialLevel() {
        return duel.getDifficulty();
    }

    public int getArtificialLevel(final int idx) {
        return duel.getDifficulty(idx);
    }

    public boolean isArtificial() {
        return artificial;
    }

    public boolean isReal() {
        return !artificial;
    }

    public void setArtificial(final boolean art) {
        artificial = art;
    }

    public boolean isSound() {
        return sound;
    }

    public void setFastChoices(final boolean aFastChoices) {
        fastChoices = aFastChoices;
    }

    public boolean getFastChoices() {
        return fastChoices;
    }

    public void setTurn(final int aTurn) {
        turn = aTurn;
    }

    public int getTurn() {
        return turn;
    }

    public void setMainPhases(final int count) {
        startTurn=turn;
        mainPhaseCount=count;
    }

    public int getRelativeTurn() {
        return startTurn>0?turn-startTurn:0;
    }

    public void decreaseMainPhaseCount() {
        mainPhaseCount--;
    }

    public void setMainPhaseCount(final int count) {
        mainPhaseCount=count;
    }

    public int getMainPhaseCount() {
        return mainPhaseCount;
    }

    public void setPhase(final MagicPhase aPhase) {
        phase = aPhase;
    }

    public void nextPhase() {
        changePhase(gameplay.getNextPhase(this));
    }

    private void changePhase(final MagicPhase aPhase) {
        phase = aPhase;
        step=MagicStep.Begin;
        priorityPassedCount=0;
        players[0].getActivationPriority().clear();
        players[1].getActivationPriority().clear();
    }

    public MagicPhase getPhase() {
        return phase;
    }

    public void executePhase() {
        phase.executePhase(this);
    }

    public boolean isPhase(final MagicPhaseType type) {
        return phase.getType()==type;
    }

    public boolean isMainPhase() {
        return phase.getType().isMain();
    }

    public boolean isCombatPhase() {
        return phase.getType().isCombat();
    }

    public void setStep(final MagicStep aStep) {
        step = aStep;
    }

    public MagicStep getStep() {
        return step;
    }

    public void resolve() {
        if (stack.isEmpty()) {
            step=MagicStep.NextPhase;
        } else {
            step=MagicStep.Resolve;
        }
    }

    public void resetPayedCost() {
        payedCost = new MagicPayedCost();
    }

    public void setPayedCost(final MagicPayedCost aPayedCost) {
        payedCost = aPayedCost;
    }

    public MagicPayedCost getPayedCost() {
        return new MagicPayedCost(payedCost);
    }

    /** Determines if game score should be cached for this game state. */
    public boolean cacheState() {
        switch (phase.getType()) {
            case FirstMain:
            case EndOfCombat:
            case Cleanup:
                return step==MagicStep.NextPhase;
            default:
                return false;
        }
    }

    /** Tells gameplay that it can skip certain parts during AI processing. */
    public boolean canSkip() {
        return stack.isEmpty() && artificial;
    }

    public boolean isFinished() {
        return losingPlayer.isValid() || mainPhaseCount <= 0;
    }

    public MagicLogBook getLogBook() {
        return logBook;
    }

    public void hideHiddenCards() {
        getOpponent(scorePlayer).setHandToUnknown();
        for (final MagicPlayer player : players) {
            player.getLibrary().setKnown(false);
        }
    }

    public void showRandomizedHiddenCards() {
        getOpponent(scorePlayer).showRandomizedHandAndLibrary();
        scorePlayer.getLibrary().shuffle(MagicRandom.nextRNGInt(999999));
        scorePlayer.getLibrary().setKnown(true);
    }

    Collection<MagicAction> getActions() {
        return actions;
    }

    public int getNumActions() {
        return actions.size();
    }

    public void startActions() {
        doAction(new MagicMarkerAction());
    }

    public void addDelayedAction(final MagicAction action) {
        delayedActions.add(action);
    }

    public void doAction(final MagicAction action) {
        actions.add(action);

        try {
            action.doAction(this);
        } catch (Throwable ex) {
            MagicGameReport.buildReport(this, Thread.currentThread(), ex);
            System.exit(1);
        }

        //performing actions update the score
        score += action.getScore(scorePlayer);
    }

    public void update() {
        doDelayedActions();
        MagicPermanent.update(this);
       
        // add Soulbond trigger here
        triggers = new MagicPermanentTriggerMap(additionalTriggers);
        triggers.add(new MagicPermanentTrigger(0,MagicPermanent.NONE,MagicWhenOtherComesIntoPlayTrigger.Soulbond));

        for (final MagicPlayer player : players) {
        for (final MagicPermanent perm : player.getPermanents()) {
        for (final MagicTrigger<?> trigger : perm.getTriggers()) {
            triggers.add(new MagicPermanentTrigger(perm.getId(), perm, trigger));
        }}}

        MagicPlayer.update(this);
        MagicGame.update(this);
        doDelayedActions();
    }

    public void apply(final MagicLayer layer) {
        switch (layer) {
            case Game:
                maxLand = 1;
                break;
            default:
                throw new RuntimeException("No case for " + layer + " in MagicGame.apply");
        }
    }

    private void apply(final MagicPermanent source, final MagicStatic mstatic) {
        final MagicLayer layer = mstatic.getLayer();
        switch (layer) {
            case Game:
                mstatic.modGame(source, this);
                break;
            default:
                throw new RuntimeException("No case for " + layer + " in MagicGame.apply");
        }
    }

    public static void update(final MagicGame game) {
        game.apply(MagicLayer.Game);
        for (final MagicPermanentStatic mpstatic : game.getStatics(MagicLayer.Game)) {
            final MagicStatic mstatic = mpstatic.getStatic();
            final MagicPermanent source = mpstatic.getPermanent();
            if (mstatic.accept(game, source, source)) {
               game.apply(source, mstatic);
            }
        }
    }

    private void doDelayedActions() {
        while (!delayedActions.isEmpty()) {
            final MagicAction action = delayedActions.removeFirst();
            doAction(action);
        }
    }

    public void undoActions() {
        //undo each action up to and including the first MagicMarkerAction
        MagicAction action;
        do {
            action = actions.removeLast();
            try {
                action.undoAction(this);
            } catch (Throwable ex) {
                MagicGameReport.buildReport(INSTANCE, Thread.currentThread(), ex);
                if (this != INSTANCE) {
                    MagicGameReport.buildReport(this, Thread.currentThread(), ex);
                }
                System.exit(1);
            }
        } while (!(action instanceof MagicMarkerAction));
    }

    public void undoAllActions() {
        assert actions.isEmpty() : "actions: " + actions;
    }

    public void createUndoPoint() {
        final MagicAction markerAction=new MagicMarkerAction();
        doAction(markerAction);
        doAction(new MagicLogMarkerAction());
        undoPoints.addLast(markerAction);
    }

    public void gotoLastUndoPoint() {
        final MagicAction markerAction = undoPoints.removeLast();
        MagicAction action;
        do {
            action = actions.removeLast();
            action.undoAction(this);
        } while (!(action == markerAction));
    }

    public int getNrOfUndoPoints() {
        return undoPoints.size();
    }

    public boolean hasUndoPoints() {
        return !undoPoints.isEmpty();
    }

    public void clearUndoPoints() {
        undoPoints.clear();
    }

    public void clearMessages() {
        logMessageBuilder.clearMessages();
    }

    public void logMessages() {
        if (disableLog) {
            return;
        }
        logMessageBuilder.logMessages();
    }

    private void logAppendEvent(final MagicEvent event,final Object[] choiceResults) {
        if (disableLog) {
            return;
        }
        final String message=event.getDescription(choiceResults);
        if (message.length() == 0) {
            return;
        }
        logMessageBuilder.appendMessage(event.getPlayer(),message);
    }

    public void logAppendMessage(final MagicPlayer player,final String message) {
        if (disableLog) {
            return;
        }
        logMessageBuilder.appendMessage(player,message);
    }

    public void logMessage(final MagicPlayer player,final String message) {
        if (disableLog) {
            return;
        }
        logBook.add(new MagicMessage(this,player,message));
    }

    public void logAttackers(final MagicPlayer player,final MagicDeclareAttackersResult result) {
        if (disableLog || result.isEmpty()) {
            return;
        }
        final SortedSet<String> names=new TreeSet<String>();
        for (final MagicPermanent attacker : result) {
            names.add(attacker.getName());
        }
        final StringBuilder builder = new StringBuilder(player + " attacks with ");
        MagicMessage.addNames(builder,names);
        builder.append('.');
        logBook.add(new MagicMessage(this,player,builder.toString()));
    }

    public void logBlockers(final MagicPlayer player,final MagicDeclareBlockersResult result) {
        if (disableLog) {
            return;
        }
        final SortedSet<String> names=new TreeSet<String>();
        for (final MagicCombatCreature[] creatures : result) {
            for (int index=1;index<creatures.length;index++) {
                names.add(creatures[index].getName());
            }
        }
        if (names.isEmpty()) {
            return;
        }
        final StringBuilder builder = new StringBuilder(player + " blocks with ");
        MagicMessage.addNames(builder,names);
        builder.append('.');
        logBook.add(new MagicMessage(this,player,builder.toString()));
    }

    public void executeEvent(final MagicEvent event,final Object[] choiceResults) {
        if (choiceResults == null) {
            throw new RuntimeException("choiceResults is null");
        }

        logAppendEvent(event,choiceResults);

        // Payed cost
        if (event.getManaChoiceResultIndex() >= 0) {
            payedCost.set(choiceResults[event.getManaChoiceResultIndex()]);
        }
        
        // Target in cost
        if (event.getTargetChoiceResultIndex() >= 0) {
            payedCost.set(choiceResults[event.getTargetChoiceResultIndex()]);
        }

        activeSource = event.getSource();
        event.executeEvent(this,choiceResults);
        update();

        // Move card to move location that is not play
        if (event.isSpellCardEvent()) {
            doAction(new MagicMoveCardAction(event.getCardOnStack()));
        }
    }

    public MagicEventQueue getEvents() {
        return events;
    }

    public boolean hasNextEvent() {
        return !events.isEmpty();
    }

    public MagicEvent getNextEvent() {
        return events.getFirst();
    }

    public void addEvent(final MagicEvent event) {
        doAction(new MagicAddEventAction(event));
    }
    
    public void addFirstEvent(final MagicEvent event) {
        doAction(new MagicAddFirstEventAction(event));
    }

    public void executeNextEvent(final Object[] choiceResults) {
        doAction(new MagicExecuteFirstEventAction(choiceResults));
    }

    public void executeNextEvent() {
        doAction(new MagicExecuteFirstEventAction(MagicEvent.NO_CHOICE_RESULTS));
    }

    public MagicDuel getDuel() {
        return duel;
    }

    public void advanceDuel() {
        duel.advance(losingPlayer!=players[0],this);
    }

    public MagicPlayer[] getPlayers() {
        return players;
    }

    public MagicPlayer getPlayer(final int index) {
        return players[index];
    }

    MagicPlayer getOpponent(final MagicPlayer player) {
        return players[1-player.getIndex()];
    }

    public void setVisiblePlayer(final MagicPlayer aVisiblePlayer) {
        visiblePlayer = aVisiblePlayer;
    }

    public MagicPlayer getVisiblePlayer() {
        return visiblePlayer;
    }

    public void setTurnPlayer(final MagicPlayer aTurnPlayer) {
        turnPlayer = aTurnPlayer;
    }

    public MagicPlayer getTurnPlayer() {
        return turnPlayer;
    }

    public MagicPlayer getAttackingPlayer() {
        return turnPlayer;
    }

    public MagicPlayer getDefendingPlayer() {
        return turnPlayer.getOpponent();
    }

    public MagicPlayer getPriorityPlayer() {
        return step == MagicStep.ActivePlayer ? turnPlayer : getOpponent(turnPlayer);
    }

    public MagicPlayer getScorePlayer() {
        return scorePlayer;
    }

    public void setLosingPlayer(final MagicPlayer player) {
        losingPlayer = player;
    }

    public MagicPlayer getLosingPlayer() {
        return losingPlayer;
    }
    
    public MagicSource getActiveSource() {
        return activeSource;
    }

    public boolean hasTurn(final MagicPlayer player) {
        return player == turnPlayer;
    }

    public int getNrOfPermanents(final MagicType type) {
        return players[0].getNrOfPermanents(type) +
               players[1].getNrOfPermanents(type);
    }

    public boolean canPlaySorcery(final MagicPlayer controller) {
        return phase.getType().isMain() &&
               stack.isEmpty() &&
               turnPlayer == controller;
    }

    public boolean canPlayLand(final MagicPlayer controller) {
        return landPlayed < maxLand && canPlaySorcery(controller);
    }

    public int getLandPlayed() {
        return landPlayed;
    }

    public void incLandPlayed() {
        landPlayed++;
    }

    public void resetLandPlayed() {
        landPlayed = 0;
    }

    public void setLandPlayed(final int lp) {
        landPlayed = lp;
    }

    public void incMaxLand() {
        maxLand++;
    }

    public void resetMaxLand() {
        maxLand = 1;
    }

    public int getSpellsPlayed() {
        return spellsPlayed;
    }

    public void setSpellsPlayed(final int spells) {
        spellsPlayed = spells;
    }

    public void incSpellsPlayed() {
        spellsPlayed++;
    }

    public boolean getCreatureDiedThisTurn() {
        return creatureDiedThisTurn;
    }

    public void setCreatureDiedThisTurn(final boolean died) {
        creatureDiedThisTurn = died;
    }

    public MagicStack getStack() {
        return stack;
    }

    public void setPriorityPassed(final boolean passed) {
        priorityPassed=passed;
    }

    public boolean getPriorityPassed() {
        return priorityPassed;
    }

    public void incrementPriorityPassedCount() {
        priorityPassedCount++;
    }

    public void setPriorityPassedCount(final int count) {
        priorityPassedCount=count;
    }

    public int getPriorityPassedCount() {
        return priorityPassedCount;
    }

    public MagicPermanent createPermanent(final MagicCard card,final MagicPlayer controller) {
        return new MagicPermanent(getUniqueId(),card,controller);
    }
    
    public MagicPermanent createPermanent(final MagicCard card, final MagicCardDefinition cardDef, final MagicPlayer controller) {
        return new MagicPermanent(getUniqueId(),card,cardDef,controller);
    }

    public MagicCardList getExiledUntilEndOfTurn() {
        return exiledUntilEndOfTurn;
    }

    public void setStateCheckRequired(final boolean required) {
        stateCheckRequired = required;
    }

    public void setStateCheckRequired() {
        stateCheckRequired = true;
    }

    public boolean getStateCheckRequired() {
        return stateCheckRequired;
    }

    public void checkState() {
        while (stateCheckRequired) {
            stateCheckRequired = false;

            // Check if a player has lost
            for (final MagicPlayer player : players) {
                player.generateStateBasedActions();
            }

            // Check permanents' state
            for (final MagicPlayer player : players) {
            for (final MagicPermanent permanent : player.getPermanents()) {
                permanent.generateStateBasedActions();
            }}

            update();
            // some action may set stateCheckRequired to true, if so loop again
        }
    }

    public void checkUniquenessRule(final MagicPermanent permanent) {
        // 704.5k "legend rule"
        if (permanent.hasType(MagicType.Legendary)) {
            final MagicTargetFilter<MagicPermanent> targetFilter=new MagicTargetFilter.LegendaryCopiesFilter(permanent.getName());
            final Collection<MagicPermanent> targets=filterPermanents(permanent.getController(),targetFilter);
            if (targets.size() > 1) {
                addEvent(new MagicUniquenessEvent(permanent, targetFilter));
            }
        }

        // 704.5j "planeswalker uniqueness rule."
        if (permanent.hasType(MagicType.Planeswalker)) {
            final MagicTargetFilter<MagicPermanent> targetFilter=new MagicTargetFilter.PlaneswalkerCopiesFilter(permanent);
            final Collection<MagicPermanent> targets=filterPermanents(permanent.getController(),targetFilter);
            if (targets.size() > 1) {
                addEvent(new MagicUniquenessEvent(permanent, targetFilter));
            }
        }
    
        // 704.5m "world rule"
        if (permanent.hasType(MagicType.World)) {
            final Collection<MagicPermanent> targets=filterPermanents(
                permanent.getController(),
                new MagicOtherPermanentTargetFilter(
                    MagicTargetFilter.WORLD,
                    permanent
                )
            );
            for (final MagicPermanent world : targets) {
                logAppendMessage(
                    world.getController(),
                    world.getName() + " is put into its owner's graveyard."
                );
                doAction(new MagicRemoveFromPlayAction(
                    world, 
                    MagicLocationType.Graveyard
                ));
            }
        }
    }

    public Object[] map(final Object[] data) {
        final int length=data.length;
        final Object[] mappedData=new Object[length];
        for (int index=0;index<length;index++) {
            final Object obj=data[index];
            if (obj != null && obj instanceof MagicMappable) {
                mappedData[index]=((MagicMappable)obj).map(this);
            } else {
                mappedData[index]=obj;
            }
        }
        return mappedData;
    }

    // ***** TARGETTING *****

    public List<MagicPermanent> filterPermanents(final MagicPlayer player,final MagicTargetFilter<MagicPermanent> targetFilter) {
        return targetFilter.filter(this, player, MagicTargetHint.None);
    }


    public List<MagicCard> filterCards(final MagicPlayer player,final MagicTargetFilter<MagicCard> targetFilter) {
        return targetFilter.filter(this, player, MagicTargetHint.None);
    }

    public List<MagicItemOnStack> filterItemOnStack(final MagicPlayer player,final MagicTargetFilter<MagicItemOnStack> targetFilter) {
        return targetFilter.filter(this, player, MagicTargetHint.None);
    }

    public boolean hasLegalTargets(
            final MagicPlayer player,
            final MagicSource source,
            final MagicTargetChoice targetChoice,
            final boolean hints) {

        if (targetChoice == MagicTargetChoice.NONE) {
            return true;
        }

        final Collection<? extends MagicTarget> targets = targetChoice.getTargetFilter().filter(
            this,
            player,
            targetChoice.getTargetHint(hints)
        );

        if (!targetChoice.isTargeted()) {
            return !targets.isEmpty();
        }

        for (final MagicTarget target : targets) {
            if (target.isValidTarget(source)) {
                return true;
            }
        }

        return false;
    }

    public List<MagicTarget> getLegalTargets(
            final MagicPlayer player,
            final MagicSource source,
            final MagicTargetChoice targetChoice,
            final MagicTargetHint targetHint) {

        final Collection<? extends MagicTarget> targets = targetChoice.getTargetFilter().filter(
            this,
            player,
            targetHint
        );

        final List<MagicTarget> options;
        if (targetChoice.isTargeted()) {
            options=new ArrayList<MagicTarget>();
            for (final MagicTarget target : targets) {
                if (target.isValidTarget(source)) {
                    options.add(target);
                }
            }
        } else {
            options=new ArrayList<MagicTarget>(targets);
        }

        if (options.isEmpty()) {
            // Try again without using hints
            if (targetHint != MagicTargetHint.None) {
                return getLegalTargets(player, source, targetChoice, MagicTargetHint.None);
            // Add none when there are no legal targets. Only for triggers.
            } else {
                options.add(MagicTargetNone.getInstance());
            }
        }
        return options;
    }

    public <T extends MagicTarget> boolean isLegalTarget(
            final MagicPlayer player,
            final MagicSource source,
            final MagicTargetChoice targetChoice,
            final T target) {

        MagicTargetFilter<T> targetFilter = (MagicTargetFilter<T>)targetChoice.getTargetFilter();

        if (target==null ||
            target==MagicTargetNone.getInstance() ||
            !targetFilter.accept(this,player,target)) {
            return false;
        }

        if (target.isLegalTarget(player, targetFilter)) {
            return !targetChoice.isTargeted() || target.isValidTarget(source);
        }

        return false;
    }

    // ***** STATICS *****

    public void addStatics(final MagicPermanent permanent) {
        for (final MagicStatic mstatic : permanent.getStatics()) {
            addStatic(permanent, mstatic);
        }
    }

    public Collection<MagicPermanentStatic> removeStatics(final MagicPermanent permanent) {
        return statics.remove(permanent);
    }

    public void addStatic(final MagicPermanent permanent, final MagicStatic mstatic) {
        addStatic(new MagicPermanentStatic(getUniqueId(),permanent,mstatic));
    }

    public void addStatic(final MagicPermanentStatic permanentStatic) {
        statics.add(permanentStatic);
    }

    public void addStatics(final Collection<MagicPermanentStatic> aStatics) {
        for (final MagicPermanentStatic mpstatic : aStatics) {
            addStatic(mpstatic);
        }
    }

    public Collection<MagicPermanentStatic> getStatics(final MagicLayer layer) {
        return statics.get(layer);
    }

    public Collection<MagicPermanentStatic> removeTurnStatics() {
        return statics.removeTurn();
    }

    public void removeStatic(final MagicPermanent permanent,final MagicStatic mstatic) {
        statics.remove(permanent, mstatic);
    }


    // ***** TRIGGERS *****

    /** Executes triggers immediately when they have no choices, otherwise ignore them. */
    public void setImmediate(final boolean aImmediate) {
        immediate = aImmediate;
    }
    
    public boolean isImmediate() {
        return immediate;
    }

    public MagicPermanentTrigger addTrigger(final MagicPermanent permanent, final MagicTrigger<?> trigger) {
        return addTrigger(new MagicPermanentTrigger(getUniqueId(),permanent,trigger));
    }

    public MagicPermanentTrigger addTrigger(final MagicPermanentTrigger permanentTrigger) {
        additionalTriggers.add(permanentTrigger);
        return permanentTrigger;
    }

    public MagicPermanentTrigger addTurnTrigger(final MagicPermanent permanent,final MagicTrigger<?> trigger) {
        final MagicPermanentTrigger permanentTrigger = addTrigger(permanent,trigger);
        turnTriggers.add(permanentTrigger);
        return permanentTrigger;
    }

    public void addTurnTriggers(final List<MagicPermanentTrigger> triggersList) {
        for (final MagicPermanentTrigger permanentTrigger : triggersList) {
            addTrigger(permanentTrigger);
        }
        turnTriggers.addAll(triggersList);
    }

    public void removeTurnTrigger(final MagicPermanentTrigger permanentTrigger) {
        additionalTriggers.remove(permanentTrigger);
        turnTriggers.remove(permanentTrigger);
    }

    public void removeTrigger(final MagicPermanentTrigger permanentTrigger) {
        additionalTriggers.remove(permanentTrigger);
    }

    public MagicPermanentTrigger removeTrigger(final MagicPermanent permanent, MagicTrigger<?> trigger) {
        return additionalTriggers.remove(permanent, trigger);
    }

    public List<MagicPermanentTrigger> removeTurnTriggers() {
        if (turnTriggers.isEmpty()) {
            return Collections.emptyList();
        }
        final MagicPermanentTriggerList removedTriggers = new MagicPermanentTriggerList(turnTriggers);
        for (final MagicPermanentTrigger permanentTrigger : removedTriggers) {
            removeTurnTrigger(permanentTrigger);
        }
        return removedTriggers;
    }

    public Collection<MagicPermanentTrigger> removeTriggers(final MagicPermanent permanent) {
        return additionalTriggers.remove(permanent);
    }

    public <T> void executeTrigger(
            final MagicTrigger<T> trigger,
            final MagicPermanent permanent,
            final MagicSource source,
            final T data) {

        if (trigger.accept(permanent, data) == false) {
            return;
        }

        final MagicEvent event=trigger.executeTrigger(this,permanent,data);

        if (event.isValid() == false) {
            return;
        }

        if (immediate) {
            if (event.hasChoice()) {
                // ignore
            } else if (trigger.usesStack()) {
                doAction(new MagicPutItemOnStackAction(new MagicTriggerOnStack(event)));
            } else {
                executeEvent(event, MagicEvent.NO_CHOICE_RESULTS);
            }
        } else if (trigger.usesStack()) {
            doAction(new MagicPutItemOnStackAction(new MagicTriggerOnStack(event)));
        } else {
            addEvent(event);
        }
    }

    public <T> void executeTrigger(final MagicTriggerType type,final T data) {
        final Collection<MagicPermanentTrigger> typeTriggers=triggers.get(type);
        if (typeTriggers.isEmpty()) {
            return;
        }

        final Collection<MagicPermanentTrigger> copiedTriggers=new ArrayList<MagicPermanentTrigger>(typeTriggers);
        for (final MagicPermanentTrigger permanentTrigger : copiedTriggers) {
            final MagicPermanent permanent = permanentTrigger.getPermanent();
            final MagicTrigger<T> trigger = (MagicTrigger<T>)permanentTrigger.getTrigger();
            executeTrigger(trigger,permanent,permanent,data);
        }
    }
}

package magic.model.choice;

import magic.data.GeneralConfig;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicSource;
import magic.model.event.MagicEvent;
import magic.ui.GameController;
import magic.ui.UndoClickedException;
import magic.ui.choice.MayChoicePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class MagicMayChoice extends MagicChoice {

    private static final List<Object[]> NO_OTHER_CHOICE_RESULTS=Arrays.asList(
            new Object[]{YES_CHOICE},
            new Object[]{NO_CHOICE});

    private final MagicChoice[] choices;
    private final MagicTargetChoice targetChoice;
    private final int manaChoiceResultIndex;
    private final int targetChoiceResultIndex;

    public MagicMayChoice(final String description,final MagicChoice... choices) {
        super(description);
        this.choices = (choices.length > 0 && choices[0] != MagicChoice.NONE) ?
            choices : new MagicChoice[0];

        MagicTargetChoice localTargetChoice = MagicTargetChoice.NONE;
        int localManaChoiceResultIndex = -1;
        int localTargetChoiceResultIndex = -1;
        for (int index=0;index<choices.length;index++) {
            final MagicChoice choice=choices[index];
            if (choice instanceof MagicTargetChoice) {
                localTargetChoice=(MagicTargetChoice)choice;
                localTargetChoiceResultIndex=index+1;
            } else if (choice instanceof MagicPayManaCostChoice) {
                localManaChoiceResultIndex=index+1;
            }
        }

        targetChoice = localTargetChoice;
        targetChoiceResultIndex = localTargetChoiceResultIndex;
        manaChoiceResultIndex = localManaChoiceResultIndex;
    }

    public MagicMayChoice(final MagicChoice... choices) {
        this("Proceed with \"may\" action?", choices);
    }

    private MagicChoice[] getChoices() {
        return choices;
    }

    @Override
    public MagicTargetChoice getTargetChoice() {
        return targetChoice;
    }

    @Override
    public int getTargetChoiceResultIndex() {
        return targetChoiceResultIndex;
    }

    @Override
    public int getManaChoiceResultIndex() {
        return manaChoiceResultIndex;
    }

    @Override
    Collection<Object> getArtificialOptions(
            final MagicGame game,
            final MagicEvent event,
            final MagicPlayer player,
            final MagicSource source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Object[]> getArtificialChoiceResults(
            final MagicGame game,
            final MagicEvent event,
            final MagicPlayer player,
            final MagicSource source) {

        final int nrOfChoices = choices.length;
        if (nrOfChoices == 0) {
            return NO_OTHER_CHOICE_RESULTS;
        }

        final int nrOfChoiceResults=nrOfChoices+1;
        final Object[] noChoiceResults=new Object[nrOfChoiceResults];
        noChoiceResults[0]=NO_CHOICE;

        final List<Collection<?>> optionsList=new ArrayList<Collection<?>>(nrOfChoices);
        for (int index=0;index<nrOfChoices;index++) {
            if (!choices[index].hasOptions(game,player,source,true)) {
                return Collections.singletonList(noChoiceResults);
            }
            optionsList.add(choices[index].getArtificialOptions(game,event,player,source));
        }

        final List<Object[]> choiceResultsList=new ArrayList<Object[]>();
        final Object[] yesChoiceResults=new Object[nrOfChoiceResults];
        yesChoiceResults[0]=YES_CHOICE;

        int index=0;
        final LinkedList<Iterator<?>> iterators=new LinkedList<Iterator<?>>();
        iterators.addLast(optionsList.get(0).iterator());
        while (index>=0) {
            final Iterator<?> iterator=iterators.getLast();
            if (iterator.hasNext()) {
                index++;
                yesChoiceResults[index]=iterator.next(); // Starts from index 1.
                if (index<nrOfChoices) {
                    iterators.addLast(optionsList.get(index).iterator());
                } else {
                    choiceResultsList.add(Arrays.copyOf(yesChoiceResults,nrOfChoiceResults));
                    index--;
                }
            } else {
                iterators.removeLast();
                index--;
            }
        }

        choiceResultsList.add(noChoiceResults);
        return choiceResultsList;
    }

    @Override
    public Object[] getPlayerChoiceResults(
            final GameController controller,
            final MagicGame game,
            final MagicPlayer player,
            final MagicSource source) throws UndoClickedException {

        final Object[] choiceResults=new Object[choices.length+1];
        choiceResults[0]=NO_CHOICE;

        final boolean hints = GeneralConfig.getInstance().getSmartTarget();
        for (final MagicChoice choice : choices) {
            if (!choice.hasOptions(game,player,source,hints)) {
                return choiceResults;
            }
        }

        controller.disableActionButton(false);
        final MayChoicePanel choicePanel = controller.waitForInput(new Callable<MayChoicePanel>() {
            public MayChoicePanel call() {
                return new MayChoicePanel(controller,source,getDescription());
            }
        });
        if (!choicePanel.isYesClicked()) {
            return choiceResults;
        }

        // Yes is chosen.
        choiceResults[0]=YES_CHOICE;
        for (int index=0;index<choices.length;index++) {
            final Object[] partialChoiceResults=choices[index].getPlayerChoiceResults(controller,game,player,source);
            choiceResults[index+1]=partialChoiceResults[0];
        }
        return choiceResults;
    }
}

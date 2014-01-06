
package magic.model.choice;

import magic.data.GeneralConfig;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicSource;
import magic.model.event.MagicEvent;
import magic.ui.GameController;
import magic.ui.UndoClickedException;
import magic.ui.choice.ModeChoicePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class MagicOrChoice extends MagicChoice {

    private final MagicChoice[] choices;

    public MagicOrChoice(final String description,final MagicChoice... aChoices) {
        super(description);
        choices = aChoices;
    }

    public MagicOrChoice(final MagicChoice... choices) {
        this("Choose the mode.", choices);
    }

    private MagicChoice[] getChoices() {
        return choices;
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

        final List<Object[]> choiceResultsList=new ArrayList<Object[]>();
        for (int index=0; index < choices.length; index++) {
            if (choices[index].hasOptions(game,player,source,true)) {
                choiceResultsList.add(new Object[] {
                    index
                });
            }
        }
       
        if (choiceResultsList.isEmpty()) {
            choiceResultsList.add(new Object[]{-1});
        }

        return choiceResultsList;
    }

    @Override
    public Object[] getPlayerChoiceResults(
            final GameController controller,
            final MagicGame game,
            final MagicPlayer player,
            final MagicSource source) throws UndoClickedException {

        
        final boolean hints = GeneralConfig.getInstance().getSmartTarget();
        final List<Integer> availableModes = new ArrayList<Integer>();
        for (int i = 0; i < choices.length; i++) {
            if (choices[i].hasOptions(game,player,source,hints)) {
                availableModes.add(i);
            }
        }

        if (availableModes.isEmpty()) {
            return new Object[]{-1};
        }

        controller.disableActionButton(false);
        final ModeChoicePanel choicePanel = controller.waitForInput(new Callable<ModeChoicePanel>() {
            public ModeChoicePanel call() {
                return new ModeChoicePanel(controller,source,availableModes);
            }
        });

        return new Object[] {
            choicePanel.getMode()
        };
    }
}

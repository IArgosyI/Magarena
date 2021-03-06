package magic.model.choice;

import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicRandom;
import magic.model.MagicSource;
import magic.model.event.MagicEvent;
import magic.ui.GameController;
import magic.ui.UndoClickedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class MagicChoice {

    static final String YES_CHOICE="yes";
    static final String NO_CHOICE="no";

    public static final MagicChoice NONE = new MagicChoice("none") {
        @Override
        public Collection<Object> getArtificialOptions(
                final MagicGame game,
                final MagicEvent event,
                final MagicPlayer player,
                final MagicSource source) {
            return Collections.emptyList();
        }
        @Override
        public Object[] getPlayerChoiceResults(
            final GameController controller,
            final MagicGame game,
            final MagicPlayer player,
            final MagicSource source) {
            return new Object[0];
        }
        @Override
        public boolean isValid() {
            return false;
        }
    };

    private final String description;

    MagicChoice(final String description) {
        this.description=description;
    }

    @Override
    public String toString() {
        return getClass().toString();
    }

    public final String getDescription() {
        return description;
    }

    public MagicTargetChoice getTargetChoice() {
        return MagicTargetChoice.NONE;
    }

    public int getTargetChoiceResultIndex() {
        return -1;
    }

    public int getManaChoiceResultIndex() {
        return -1;
    }

    public boolean isValid() {
        return true;
    }

    /** Checks if there are valid options for the choice. */
    public boolean hasOptions(final MagicGame game,final MagicPlayer player,final MagicSource source,final boolean hints) {
        return true;
    }

    /** Gets the available options for AI. */
    abstract Collection<?> getArtificialOptions(
            final MagicGame game,final MagicEvent event,final MagicPlayer player,final MagicSource source);

    /** Gets the choice results for AI. */
    public List<Object[]> getArtificialChoiceResults(
            final MagicGame game,
            final MagicEvent event,
            final MagicPlayer player,
            final MagicSource source) {
        final Collection<?> options=getArtificialOptions(game,event,player,source);
        final int size=options.size();
        if (size == 0) {
            throw new RuntimeException("no artificial choice result");
        } else if (size == 1) {
            return Collections.singletonList(new Object[]{options.iterator().next()});
        } else {
            final List<Object[]> choiceResultsList=new ArrayList<Object[]>(size);
            for (final Object option : options) {
                choiceResultsList.add(new Object[]{option});
            }
            return choiceResultsList;
        }
    }

    /** Gets one choice results for simulation. */
    public Object[] getSimulationChoiceResult(
            final MagicGame game,
            final MagicEvent event,
            final MagicPlayer player,
            final MagicSource source) {

        final List<Object[]> choices = getArtificialChoiceResults(game, event, player, source);
        final int size = choices.size();
        if (size == 0) {
            throw new RuntimeException("no simulation choice result");
        }
        return choices.get(MagicRandom.nextRNGInt(choices.size()));
    }

    /** Gets the choice results of the player. */
    public abstract Object[] getPlayerChoiceResults(
        final GameController controller,
        final MagicGame game,
        final MagicPlayer player,
        final MagicSource source
    ) throws UndoClickedException;

    public static boolean isYesChoice(final Object choiceResult) {
        return choiceResult == YES_CHOICE;
    }

    public static boolean isNoChoice(final Object choiceResult) {
        return choiceResult == NO_CHOICE;
    }
}

package magic.model.event;

import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicSource;
import magic.model.MagicLocationType;
import magic.model.MagicPermanent;
import magic.model.MagicCard;
import magic.model.choice.MagicChoice;
import magic.model.action.MagicRemoveCardAction;
import magic.model.action.MagicPlayMod;
import magic.model.action.MagicPlayCardAction;
import magic.model.action.MagicShuffleLibraryAction;
import magic.model.action.MagicCardAction;

public class MagicSearchOntoBattlefieldEvent extends MagicEvent {
    
    public MagicSearchOntoBattlefieldEvent(final MagicEvent event, final MagicChoice choice, final MagicPlayMod... mods) {
        this(event.getSource(), event.getPlayer(), choice, mods);
    }

    public MagicSearchOntoBattlefieldEvent(final MagicSource source, final MagicPlayer player, final MagicChoice choice, final MagicPlayMod... mods) {
        super(
            source,
            player,
            choice,
            EventAction(mods),
            ""
        );
    }

    private static final MagicEventAction EventAction(final MagicPlayMod... mods) {
        return new MagicEventAction() {
            @Override
            public void executeEvent(final MagicGame game, final MagicEvent event) {
                // choice could be MagicMayChoice or MagicTargetChoice, the condition below takes care of both cases
                if (event.isNo() == false) {
                    event.processTargetCard(game, new MagicCardAction() {
                        public void doAction(final MagicCard card) {
                            game.logAppendMessage(event.getPlayer(), "Found " + card + ".");
                            game.doAction(new MagicRemoveCardAction(card,MagicLocationType.OwnersLibrary));
                            game.doAction(new MagicPlayCardAction(card,event.getPlayer(),mods));
                            game.doAction(new MagicShuffleLibraryAction(event.getPlayer()));
                        }
                    });
                }
            }
        };
    }
}

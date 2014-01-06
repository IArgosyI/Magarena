package magic.model.trigger;

import magic.model.MagicCard;
import magic.model.MagicGame;
import magic.model.MagicLocationType;
import magic.model.MagicPermanent;
import magic.model.action.MagicCardAction;
import magic.model.action.MagicMoveCardAction;
import magic.model.action.MagicRemoveCardAction;
import magic.model.choice.MagicMayChoice;
import magic.model.choice.MagicTargetChoice;
import magic.model.event.MagicEvent;
import magic.model.target.MagicGraveyardTargetPicker;
import magic.model.target.MagicTargetFilter;
import magic.model.target.MagicTargetHint;

public class MagicSoulshiftTrigger extends MagicWhenDiesTrigger {

    private final int cmc;

    public MagicSoulshiftTrigger(final int cmc) {
        this.cmc = cmc;
    }

    @Override
    public MagicEvent getEvent(final MagicPermanent permanent) {
        final MagicTargetFilter<MagicCard> targetFilter =
            new MagicTargetFilter.MagicCMCCardFilter(
                MagicTargetFilter.TARGET_SPIRIT_CARD_FROM_GRAVEYARD,
                MagicTargetFilter.Operator.LESS_THAN_OR_EQUAL,
                cmc
            );
        final MagicTargetChoice targetChoice =
            new MagicTargetChoice(
                targetFilter,
                MagicTargetHint.None,
                "target Spirit card from your graveyard"
            );
        return new MagicEvent(
            permanent,
            new MagicMayChoice(targetChoice),
            MagicGraveyardTargetPicker.ReturnToHand,
            this,
            "PN may$ return target Spirit card$ with " +
            "converted mana cost " + cmc + " or less " +
            "from his or her graveyard to his or her hand."
        );
    }
    @Override
    public void executeEvent(final MagicGame game, final MagicEvent event) {
        if (event.isYes()) {
            event.processTargetCard(game,new MagicCardAction() {
                public void doAction(final MagicCard card) {
                    game.doAction(new MagicRemoveCardAction(
                        card,
                        MagicLocationType.Graveyard
                    ));
                    game.doAction(new MagicMoveCardAction(
                        card,
                        MagicLocationType.Graveyard,
                        MagicLocationType.OwnersHand
                    ));
                }
            });
        }
    }
}

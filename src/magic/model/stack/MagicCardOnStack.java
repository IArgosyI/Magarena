package magic.model.stack;

import magic.model.MagicAbility;
import magic.model.MagicColor;
import magic.model.MagicCard;
import magic.model.MagicCopyMap;
import magic.model.MagicGame;
import magic.model.MagicLocationType;
import magic.model.MagicPayedCost;
import magic.model.MagicPlayer;
import magic.model.MagicSource;
import magic.model.MagicType;
import magic.model.event.MagicActivation;
import magic.model.event.MagicSourceActivation;
import magic.model.event.MagicEvent;
import magic.model.event.MagicCardEvent;

import javax.swing.ImageIcon;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MagicCardOnStack extends MagicItemOnStack implements MagicSource {

    private MagicLocationType moveLocation=MagicLocationType.Graveyard;
    private final MagicPayedCost payedCost;
    private final MagicCardEvent cardEvent;

    public MagicCardOnStack(final MagicCard card,final MagicPlayer controller,final MagicCardEvent aCardEvent, final MagicPayedCost aPayedCost) {
        super(card, controller, aCardEvent, aPayedCost);
        payedCost = aPayedCost;
        cardEvent = aCardEvent;
    }

    public MagicCardOnStack(final MagicCard card,final MagicCardEvent aCardEvent, final MagicPayedCost aPayedCost) {
        this(card, card.getController(), aCardEvent, aPayedCost);
    }

    public MagicCardOnStack(final MagicCard card,final MagicPlayer controller,final MagicPayedCost aPayedCost) {
        this(card, controller, card.getCardDefinition().getCardEvent(), aPayedCost);
    }

    public MagicCardOnStack(final MagicCard card,final MagicPayedCost aPayedCost) {
        this(card, card.getController(), card.getCardDefinition().getCardEvent(), aPayedCost);
    }

    private MagicCardOnStack(final MagicCopyMap copyMap, final MagicCardOnStack cardOnStack) {
        super(copyMap, cardOnStack);
        payedCost = copyMap.copy(cardOnStack.payedCost);
        moveLocation = cardOnStack.moveLocation;
        cardEvent = cardOnStack.cardEvent;
    }

    public MagicCardOnStack copyCardOnStack(final MagicPlayer player) {
        final MagicCard card=MagicCard.createTokenCard(getCardDefinition(),player);
        final MagicCardOnStack copyCardOnStack=new MagicCardOnStack(card,cardEvent,payedCost);
        final Object[] choiceResults=getChoiceResults();
        if (choiceResults!=null) {
            copyCardOnStack.setChoiceResults(Arrays.copyOf(choiceResults,choiceResults.length));
        }
        return copyCardOnStack;
    }

    @Override
    public MagicCardOnStack copy(final MagicCopyMap copyMap) {
        return new MagicCardOnStack(copyMap, this);
    }

    @Override
    public MagicCardOnStack map(final MagicGame game) {
        return (MagicCardOnStack)super.map(game);
    }

    public MagicCard getCard() {
        return (MagicCard)getSource();
    }

    public void setMoveLocation(final MagicLocationType moveLocation) {
        this.moveLocation=moveLocation;
    }

    public MagicLocationType getMoveLocation() {
        return moveLocation;
    }

    @Override
    public int getConvertedCost() {
        return getCardDefinition().getConvertedCost() + payedCost.getX();
    }

    public int getX() {
        return payedCost.getX();
    }

    public MagicPayedCost getPayedCost() {
        return payedCost;
    }

    @Override
    public boolean isSpell() {
        return true;
    }

    @Override
    public boolean canBeCountered() {
        return !getCardDefinition().hasAbility(MagicAbility.CannotBeCountered);
    }

    @Override
    public ImageIcon getIcon() {
        return getCard().getCardDefinition().getIcon();
    }

    @Override
    public MagicGame getGame() {
        return getSource().getGame();
    }
    
    @Override
    public Collection<MagicSourceActivation<? extends MagicSource>> getSourceActivations() {
        return Collections.emptyList();
    }

    public int getKicker() {
        return payedCost.getKicker();
    }

    public boolean isKicked() {
        return payedCost.isKicked();
    }
}

package magic.model.action;

import magic.ai.ArtificialScoringSystem;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.trigger.MagicLifeChangeTriggerData;
import magic.model.trigger.MagicTriggerType;

/** Keeping the player life is done in the marker action. */
public class MagicChangeLifeAction extends MagicAction {

    private final MagicPlayer player;
    private int lifeChange;

    public MagicChangeLifeAction(final MagicPlayer aPlayer,final int aLifeChange) {
        player = aPlayer;
        lifeChange = aLifeChange;
    }

    public int getLifeChange() {
        return lifeChange;
    }

    public void setLifeChange(final int aLifeChange) {
        lifeChange = aLifeChange;
    }

    @Override
    public void doAction(final MagicGame game) {
        final int oldLife = player.getLife();

        game.executeTrigger(MagicTriggerType.IfLifeWouldChange, this);

        final int newLife = oldLife + lifeChange;
        player.setLife(newLife);

        setScore(player,ArtificialScoringSystem.getLifeScore(newLife)-ArtificialScoringSystem.getLifeScore(oldLife));
        if (newLife > oldLife) {
            game.executeTrigger(MagicTriggerType.WhenLifeIsGained,new MagicLifeChangeTriggerData(player,newLife-oldLife));
        } else if (newLife < oldLife) {
            game.executeTrigger(MagicTriggerType.WhenLifeIsLost,new MagicLifeChangeTriggerData(player,oldLife-newLife));
        }
        game.setStateCheckRequired();
    }

    @Override
    public void undoAction(final MagicGame game) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+" ("+player.getName()+','+lifeChange+')';
    }
}

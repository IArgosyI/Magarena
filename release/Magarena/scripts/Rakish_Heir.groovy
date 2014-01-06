[
    new MagicWhenDamageIsDealtTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            final MagicSource dmgSource = damage.getSource();
            return (damage.isCombat() &&
                    damage.getTarget().isPlayer() &&
                    permanent.isFriend(dmgSource) &&
                    dmgSource.isCreature() &&
                    dmgSource.hasSubType(MagicSubType.Vampire)) ?
                new MagicEvent(
                    permanent,
                    dmgSource,
                    this,
                    "Put a +1/+1 counter on RN."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicChangeCountersAction(event.getRefPermanent(),MagicCounterType.PlusOne,1,true));
        }
    }
]

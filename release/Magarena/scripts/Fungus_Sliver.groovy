
def FungusGrowth = new MagicWhenDamageIsDealtTrigger() {
    @Override
    public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicDamage damage) {
        return damage.getTarget() == permanent ?
            new MagicEvent(
                permanent,
                this,
                "Put a +1/+1 counter on SN."
            ) :
            MagicEvent.NONE;
    }
    @Override
    public void executeEvent(final MagicGame game, final MagicEvent event) {
        game.doAction(new MagicChangeCountersAction(
            event.getPermanent(),
            MagicCounterType.PlusOne,
            1,
            true
        ));
    }
};

[
    new MagicStatic(
        MagicLayer.Ability,
        MagicTargetFilter.TARGET_SLIVER
    ) {
        @Override
        public void modAbilityFlags(final MagicPermanent source,final MagicPermanent permanent,final Set<MagicAbility> flags) {    
            permanent.addAbility(FungusGrowth);
        }
    }
]

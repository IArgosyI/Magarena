
def FrenzyPump = new MagicWhenAttacksUnblockedTrigger() {
    @Override
    public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent creature) {
        return (creature == permanent) ?
            new MagicEvent(
                permanent,
                this,
                "SN gets +1/0 until end of turn."
            ):
            MagicEvent.NONE;
    }
    @Override
    public void executeEvent(final MagicGame game, final MagicEvent event) {
        game.doAction(new MagicChangeTurnPTAction(
            event.getPermanent(), 
            1, 0
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
            permanent.addAbility(FrenzyPump);
        }
    }
]

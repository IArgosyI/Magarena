def PT = new MagicStatic(MagicLayer.SetPT) {
    @Override
    public void modPowerToughness(
        final MagicPermanent source,
        final MagicPermanent permanent,
        final MagicPowerToughness pt) {
        pt.set(3,1);
    }
    @Override
    public boolean condition(
        final MagicGame game,
        final MagicPermanent source,
        final MagicPermanent target) {
        return target.getCounters(MagicCounterType.Feather) > 0;
    }
};

def AB = new MagicStatic(MagicLayer.Ability) {
    @Override
    public void modAbilityFlags(
        final MagicPermanent source,
        final MagicPermanent permanent,
        final Set<MagicAbility> flags) {
        flags.add(MagicAbility.Flying);
    }
    @Override
    public boolean condition(
        final MagicGame game,
        final MagicPermanent source,
        final MagicPermanent target) {
        return target.getCounters(MagicCounterType.Feather) > 0;
    }
};

[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            return permanent.isController(upkeepPlayer) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(
                        MagicTargetChoice.TARGET_CREATURE),
                    new MagicBecomeTargetPicker(3,1,true),
                    this,
                    "PN may\$ put a feather counter on target creature\$."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game, {
                    final MagicPermanent creature ->
                    game.doAction(new MagicChangeCountersAction(creature,MagicCounterType.Feather,1,true));
                    game.doAction(new MagicAddStaticAction(creature, PT));
                    game.doAction(new MagicAddStaticAction(creature, AB));
                } as MagicPermanentAction);
            }
        }
    }
]

[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            return permanent.isController(upkeepPlayer) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(MagicTargetChoice.TARGET_CREATURE_PLUSONE_COUNTER),
                    this,
                    "PN may\$ move a +1/+1 counter from " +
                    "target creature\$ onto SN."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game,new MagicPermanentAction() {
                    public void doAction(final MagicPermanent creature) {
                        if (creature.getCounters(MagicCounterType.PlusOne) > 0) {
                            game.doAction(new MagicChangeCountersAction(event.getPermanent(),MagicCounterType.PlusOne,1,true));
                            game.doAction(new MagicChangeCountersAction(creature,MagicCounterType.PlusOne,-1,true));
                        }
                    }
                });
            }
        }
    }
]

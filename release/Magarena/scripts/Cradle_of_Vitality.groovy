[
    new MagicWhenLifeIsGainedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicLifeChangeTriggerData lifeChange) {
            return permanent.isController(lifeChange.player) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(
                        new MagicPayManaCostChoice(MagicManaCost.create("{1}{W}")),
                        MagicTargetChoice.POS_TARGET_CREATURE
                    ),
                    MagicPumpTargetPicker.create(),
                    lifeChange.amount,
                    this,
                    "You may\$ pay {1}{W}\$. If you do, put a +1/+1 counter " +
                    "on target creature\$ for each 1 life you gained."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game,new MagicPermanentAction() {
                    public void doAction(final MagicPermanent creature) {
                        game.doAction(new MagicChangeCountersAction(
                            creature,
                            MagicCounterType.PlusOne,
                            event.getRefInt(),
                            true
                        ));
                    }
                });
            }
        }
    }
]

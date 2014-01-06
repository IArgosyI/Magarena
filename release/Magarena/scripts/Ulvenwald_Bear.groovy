[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return (game.getCreatureDiedThisTurn()) ?
                new MagicEvent(
                    permanent,
                    MagicTargetChoice.POS_TARGET_CREATURE,
                    MagicPumpTargetPicker.create(),
                    this,
                    "PN puts two +1/+1 counters on target creature\$."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicChangeCountersAction(
                        creature,
                        MagicCounterType.PlusOne,
                        2,
                        true
                    ));
                }
            });
        }
    }
]

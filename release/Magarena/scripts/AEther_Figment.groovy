[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            if (payedCost.isKicked()) {
                game.doAction(new MagicChangeCountersAction(
                    permanent,
                    MagicCounterType.PlusOne,
                    2,
                    true
                ));
            }
            return MagicEvent.NONE;
        }
    }
]

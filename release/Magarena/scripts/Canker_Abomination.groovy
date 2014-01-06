[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(
                final MagicGame game,
                final MagicPermanent permanent,
                final MagicPayedCost payedCost) {
            final int amount = permanent.getOpponent().getNrOfPermanents(MagicType.Creature);
            game.doAction(new MagicChangeCountersAction(
                permanent,
                MagicCounterType.MinusOne,
                amount,
                true
            ));
            return MagicEvent.NONE;
        }
    }
]

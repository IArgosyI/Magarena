[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPayedCost payedCost) {
            final MagicPermanent enchantedCreature = permanent.getEnchantedCreature();
            return enchantedCreature.isValid() ?
                new MagicEvent(
                    permanent,
                    this,
                    "Tap " + enchantedCreature + ". It doesn't " +
                    "untap during its controller's untap step"
                ) :
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent permanent= event.getPermanent();
            final MagicPermanent enchantedCreature = permanent.getEnchantedCreature();
            if (enchantedCreature.isValid()) {
                game.doAction(new MagicTapAction(enchantedCreature,true));
            }
        }
    }
]

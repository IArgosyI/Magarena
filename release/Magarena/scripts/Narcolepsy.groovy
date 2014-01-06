[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            final MagicPermanent enchantedCreature=permanent.getEnchantedCreature();
            return (enchantedCreature.isValid() && enchantedCreature.isUntapped()) ?
                new MagicEvent(
                    permanent,
                    this,
                    "If "+enchantedCreature+" is untapped, tap it."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent permanent=event.getPermanent();
            final MagicPermanent enchantedCreature=permanent.getEnchantedCreature();
            if (enchantedCreature.isValid()) {
                game.doAction(new MagicTapAction(enchantedCreature,true));
            }
        }
    }
]

[
    new MagicWhenBecomesTappedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent tapped) {
            final MagicPermanent enchantedPermanent = permanent.getEnchantedCreature();
            return (enchantedPermanent == tapped) ?
                new MagicEvent(
                    permanent,
                    enchantedPermanent,
                    this,
                    "Destroy RN."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicDestroyAction(event.getRefPermanent()));
        }
    }
]

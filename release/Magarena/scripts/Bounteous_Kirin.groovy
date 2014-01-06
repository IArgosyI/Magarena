[
    new MagicWhenYouCastSpiritOrArcaneTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicCardOnStack cardOnStack) {
            return new MagicEvent(
                permanent,
                new MagicSimpleMayChoice(
                    MagicSimpleMayChoice.GAIN_LIFE,
                    cardOnStack.getConvertedCost(),
                    MagicSimpleMayChoice.DEFAULT_YES
                ),
                cardOnStack.getConvertedCost(),
                this,
                "You may gain RN life."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                game.doAction(new MagicChangeLifeAction(event.getPlayer(), event.getRefInt()));
            }
        }
    }
]

[
    new MagicWhenDiesTrigger() {
        @Override
        public MagicEvent getEvent(final MagicPermanent permanent) {
            return new MagicEvent(
                permanent,
                this,
                "PN puts two 1/1 green Ooze creature tokens onto the battlefield."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicPlayTokensAction(event.getPlayer(), TokenCardDefinitions.get("Ooze1"), 2));
        }
    }
]

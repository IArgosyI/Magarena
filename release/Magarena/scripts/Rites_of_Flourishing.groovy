[
    MagicAtDrawTrigger.EachPlayerDraw,
    new MagicStatic(MagicLayer.Game) {
        @Override
        public void modGame(final MagicPermanent source, final MagicGame game) {
            game.incMaxLand();
        }
    }
]

[
    new MagicStatic(MagicLayer.Player) {
        @Override
        public void modPlayer(final MagicPermanent source, final MagicPlayer player) {
            source.getController().addAbility(MagicAbility.Shroud);
        }
    }
]

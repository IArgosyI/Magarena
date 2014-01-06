[
    new MagicStatic(MagicLayer.Player) {
        @Override
        public void modPlayer(final MagicPermanent source, final MagicPlayer player) {
            final MagicPlayer opponent = source.getController().getOpponent();
            opponent.setState(MagicPlayerState.CantCastSpells);
            opponent.setState(MagicPlayerState.CantActivateAbilities);
        }
        @Override
        public boolean condition(final MagicGame game,final MagicPermanent source,final MagicPermanent target) {
            return game.getTurnPlayer() == source.getController();
        }
    }
]

[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump),
        "Mill"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicSacrificePermanentEvent(
                    source,
                    MagicTargetChoice.SACRIFICE_CREATURE
                )
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.TARGET_PLAYER,
                payedCost.getTarget(),
                this,
                "Target player\$ puts a number of cards equal to the sacrificed creature's power " + 
                "from the top of his or her library into his or her graveyard."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game,new MagicPlayerAction() {
                public void doAction(final MagicPlayer player) {
                    final MagicPermanent sacrificed = event.getRefPermanent();
                    game.doAction(new MagicMillLibraryAction(player,sacrificed.getPower()));
                }
            });
        }
    }
]

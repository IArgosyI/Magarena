[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Draw),
        "Draw"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{1}"),
                new MagicSacrificeEvent(source)
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.TARGET_OPPONENT,
                this,
                "Target opponent\$ discards a card. If PN controls a Demon, that player loses 3 life."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game,new MagicPlayerAction() {
                public void doAction(final MagicPlayer player) {
                    game.addEvent(new MagicDiscardEvent(
                        event.getSource(),
                        player
                    ));
                    if (player.controlsPermanent(MagicSubType.Demon)) {
                        game.doAction(new MagicChangeLifeAction(player,-3));
                    }
                }
            });
        }
    }
]

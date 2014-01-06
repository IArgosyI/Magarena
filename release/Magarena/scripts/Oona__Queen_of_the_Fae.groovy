[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Token),
        "Token"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicPayManaCostEvent(source, "{X}{U/B}")];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.TARGET_OPPONENT,
                payedCost.getX(),
                this,
                "Choose a color. Target opponent\$ exiles the top RN cards of his or her library. " +
                "For each card of the chosen color exiled this way, put a 1/1 blue and black Faerie Rogue creature token with flying onto the battlefield."
            );
        }

        @Override
        public void executeEvent(final MagicGame outerGame, final MagicEvent outerEvent) {
            outerEvent.processTargetPlayer(outerGame,new MagicPlayerAction() {
                public void doAction(final MagicPlayer outerPlayer) {
                    outerGame.addEvent(new MagicEvent(
                        outerEvent.getSource(),
                        MagicColorChoice.ALL_INSTANCE,
                        outerPlayer,
                        {
                            final MagicGame game, final MagicEvent event ->
                            final MagicColor color = event.getChosenColor();
                            final MagicPlayer player = event.getRefPlayer();
                            final int x = outerEvent.getRefInt();
                            for (int i = 0; i < x && player.getLibrary().getCardAtTop() != MagicCard.NONE; i++) {
                                final MagicCard card = player.getLibrary().getCardAtTop();
                                game.doAction(new MagicRemoveCardAction(card,MagicLocationType.OwnersLibrary));
                                game.doAction(new MagicMoveCardAction(card,MagicLocationType.OwnersLibrary,MagicLocationType.Exile));
                                if (card.hasColor(color)) {
                                    game.doAction(new MagicPlayTokenAction(
                                        event.getPlayer(),
                                        TokenCardDefinitions.get("Faerie Rogue")
                                    ));
                                }
                            }
                        } as MagicEventAction,
                        "Chosen color\$."
                    ));
                }
            });
        }
    }
]

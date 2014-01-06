[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump),
        "Pump"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source, "{G}")
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.NEG_TARGET_CARD_FROM_ALL_GRAVEYARDS,
                MagicGraveyardTargetPicker.ExileOpp,
                this,
                "Exile target card\$ from a graveyard. " +
                "If it was a creature card, put a +1/+1 counter on Scavenging Ooze and you gain 1 life."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetCard(game,new MagicCardAction() {
                public void doAction(final MagicCard card) {
                    game.doAction(new MagicRemoveCardAction(
                        card,
                        MagicLocationType.Graveyard
                    ));
                    game.doAction(new MagicMoveCardAction(
                        card,
                        MagicLocationType.Graveyard,
                        MagicLocationType.Exile
                    ));
                    if (card.hasType(MagicType.Creature)) {
                        game.doAction(new MagicChangeCountersAction(
                            event.getPermanent(),
                            MagicCounterType.PlusOne,
                            1,
                            true
                        ));
                        game.doAction(new MagicChangeLifeAction(
                            event.getPlayer(),
                            1
                        ));
                    }
                }
            });
        }
    }
]

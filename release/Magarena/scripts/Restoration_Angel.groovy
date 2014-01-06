[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(
                final MagicGame game,
                final MagicPermanent permanent,
                final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                new MagicMayChoice(
                    MagicTargetChoice.TARGET_NON_ANGEL_CREATURE_YOU_CONTROL
                ),
                MagicBounceTargetPicker.create(),
                this,
                "You may\$ exile target non-Angel creature\$ you control, then return " +
                "that card to the battlefield under your control."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game,new MagicPermanentAction() {
                    public void doAction(final MagicPermanent creature) {
                        game.doAction(new MagicRemoveFromPlayAction(
                            creature,
                            MagicLocationType.Exile
                        ));
                        final MagicRemoveCardAction removeCard = new MagicRemoveCardAction(creature.getCard(), MagicLocationType.Exile);
                        game.doAction(removeCard);
                        if (removeCard.isValid()) {
                            game.doAction(new MagicPlayCardAction(
                                creature.getCard(),
                                event.getPlayer()
                            ));
                        }
                    }
                });
            }
        }
    }
]

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_NONLAND_PERMANENT,
                MagicBounceTargetPicker.create(),
                this,
                "Put target nonland permanent\$ on top of its owner's library."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicRemoveFromPlayAction(
                        creature,
                        MagicLocationType.TopOfOwnersLibrary
                    ));
                }
            });
        }
    }
]

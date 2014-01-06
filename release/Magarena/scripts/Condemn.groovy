[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_ATTACKING_CREATURE,
                MagicExileTargetPicker.create(), // could use a better TargetPicker
                this,
                "Put target creature\$ on the bottom of its owner's library. " +
                "Its controller gains life equal to its toughness."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicChangeLifeAction(creature.getController(), creature.getToughness()));
                    game.doAction(new MagicRemoveFromPlayAction(creature,MagicLocationType.BottomOfOwnersLibrary));
                }
            });
        }
    }
]

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.TARGET_CREATURE_YOU_DONT_CONTROL,
                new MagicWeakenTargetPicker(4, 0),
                this,
                "Target creature\$ you don't control gets -4/-0 until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicChangeTurnPTAction(creature, -4, 0));
                }
            });
        }
    },
    new MagicOverloadActivation(MagicTiming.Removal) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicCard source) {
            return [
                new MagicPayManaCostEvent(source,"{2}{U}")
            ];
        }
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Each creature you don't control gets -4/-0 until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final Collection<MagicPermanent> targets=
                game.filterPermanents(event.getPlayer(),MagicTargetFilter.TARGET_CREATURE_YOUR_OPPONENT_CONTROLS);
            for (final MagicPermanent creature : targets) {
                game.doAction(new MagicChangeTurnPTAction(creature, -4, 0));
            }
        }
    }
]

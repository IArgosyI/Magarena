[
    new MagicPermanentActivation(
        [
            MagicCondition.IS_ATTACKING_CONDITION,
        ],
        new MagicActivationHints(MagicTiming.Removal),
        "Damage"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicPayManaCostEvent(source,"{R}")];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.TARGET_CREATURE_YOUR_OPPONENT_CONTROLS,
                new MagicDamageTargetPicker(1),
                this,
                "SN deals 1 damage to target creature\$ your opponent controls."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTarget(game,new MagicTargetAction() {
                public void doAction(final MagicTarget target) {
                    final MagicDamage damage = new MagicDamage(event.getSource(),target,1);
                    game.doAction(new MagicDealDamageAction(damage));
                }
            });
        }
    }
]

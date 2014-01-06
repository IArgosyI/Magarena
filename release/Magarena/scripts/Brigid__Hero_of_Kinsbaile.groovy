[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Block),
        "Damage"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicTapEvent(source)];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.NEG_TARGET_PLAYER,
                this,
                "SN deals 2 damage to each attacking or blocking creature target player\$ controls."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game,new MagicPlayerAction() {
                public void doAction(final MagicPlayer player) {
                    final MagicSource source=event.getSource();
                    final Collection<MagicPermanent> targets=
                        game.filterPermanents(player,MagicTargetFilter.TARGET_ATTACKING_OR_BLOCKING_CREATURE_YOU_CONTROL);
                    for (final MagicPermanent target : targets) {
                        final MagicDamage damage=new MagicDamage(source,target,2);
                        game.doAction(new MagicDealDamageAction(damage));
                    }
                }
            });
        }
    }
]

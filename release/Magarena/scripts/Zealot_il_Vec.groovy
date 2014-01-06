[
    new MagicWhenAttacksUnblockedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent creature) {
            return (creature == permanent) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(MagicTargetChoice.NEG_TARGET_CREATURE),
                    new MagicDamageTargetPicker(1),
                    this,
                    "PN may\$ have SN deal 1 damage to target creature\$. " +
                    "If you do, prevent all combat damage SN would deal this turn."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game,new MagicPermanentAction() {
                    public void doAction(final MagicPermanent creature) {
                        final MagicPermanent permanent = event.getPermanent();
                        final MagicDamage damage = new MagicDamage(
                            permanent,
                            creature,
                            1
                        );
                        game.doAction(new MagicDealDamageAction(damage));
                        game.doAction(MagicChangeStateAction.Set(
                            permanent,
                            MagicPermanentState.NoCombatDamage
                        ));
                    }
                });
            }
        }
    }
]

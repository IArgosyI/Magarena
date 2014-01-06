[
    new MagicWhenAttacksUnblockedTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent creature) {
            return (creature == permanent) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(MagicTargetChoice.NEG_TARGET_CREATURE),
                    new MagicDamageTargetPicker(permanent.getPower()),
                    this,
                    "PN may\$ have SN deal damage equal to its power to target creature\$. " +
                    "If you do, SN assigns no combat damage this turn."
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
                            permanent.getPower()
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

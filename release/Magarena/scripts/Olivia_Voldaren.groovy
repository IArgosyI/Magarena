[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Damage"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source, "{1}{R}")
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            final MagicTargetChoice targetChoice = new MagicTargetChoice(
                new MagicOtherPermanentTargetFilter(
                    MagicTargetFilter.TARGET_CREATURE,
                    source
                ),
                MagicTargetHint.Negative,
                "another target creature"
            );
            return new MagicEvent(
                source,
                targetChoice,
                new MagicDamageTargetPicker(1),
                this,
                "SN deals 1 damage to another target creature\$. " +
                "That creature becomes a Vampire in addition to its other types. " + 
                "Put a +1/+1 counter on SN."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    final MagicDamage damage = new MagicDamage(
                        event.getSource(),
                        creature,
                        1
                    );
                    game.doAction(new MagicDealDamageAction(damage));
                    game.doAction(new MagicAddStaticAction(creature, MagicStatic.Vampire));
                    game.doAction(new MagicChangeCountersAction(
                        event.getPermanent(),
                        MagicCounterType.PlusOne,
                        1,
                        true
                    ));
                }
            });
        }
    },
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Control"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source, "{3}{B}{B}")
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.NEG_TARGET_VAMPIRE,
                MagicExileTargetPicker.create(),
                this,
                "Gain control of target Vampire\$ for as long as PN controls SN."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                final MagicPermanent perm ->
                game.doAction(new MagicAddStaticAction(
                    event.getPermanent(), 
                    MagicStatic.ControlAsLongAsYouControlSource(
                        event.getPlayer(),
                        perm
                    )
                ));
            } as MagicPermanentAction);
        }
    }
]

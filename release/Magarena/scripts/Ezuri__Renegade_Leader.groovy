[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump),
        "Regen"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicPayManaCostEvent(source,"{G}")];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            final MagicTargetChoice targetChoice = new MagicTargetChoice(
                new MagicOtherPermanentTargetFilter(
                    MagicTargetFilter.TARGET_ELF,
                    source
                ),
                MagicTargetHint.Positive,
                "another target Elf"
            );
            return new MagicEvent(
                source,
                targetChoice,
                MagicRegenerateTargetPicker.getInstance(),
                this,
                "Regenerate another target Elf\$."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicRegenerateAction(creature));
                }
            });
        }
    },
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump,true),
        "Pump"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicPayManaCostEvent(source,"{2}{G}{G}{G}")];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Elf creatures PN controls get +3/+3 and gain trample until end of turn."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final Collection<MagicPermanent> targets =
                    game.filterPermanents(event.getPlayer(),MagicTargetFilter.TARGET_ELF_YOU_CONTROL);
            for (final MagicPermanent creature : targets) {
                game.doAction(new MagicChangeTurnPTAction(creature,3,3));
                game.doAction(new MagicGainAbilityAction(creature,MagicAbility.Trample));
            }
        }
    }
]

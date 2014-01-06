[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Vampire creatures PN controls get +2/+0 " +
                "and gain first strike until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final Collection<MagicPermanent> targets = game.filterPermanents(
                    event.getPlayer(),
                    MagicTargetFilter.TARGET_VAMPIRE_YOU_CONTROL);
            for (final MagicPermanent creature : targets) {
                game.doAction(new MagicChangeTurnPTAction(creature,2,0));
                game.doAction(new MagicGainAbilityAction(creature,MagicAbility.FirstStrike));
            }
        }
    }
]

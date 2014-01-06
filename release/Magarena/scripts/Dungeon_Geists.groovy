def control = {
    final int you, final MagicTargetFilter<MagicPermanent> filter ->
    return new MagicStatic(MagicLayer.Ability,filter) {
        @Override
        public void modAbilityFlags(
            final MagicPermanent source,
            final MagicPermanent permanent,
            final Set<MagicAbility> flags) {
            flags.add(MagicAbility.DoesNotUntap);
        }
        @Override
        public boolean condition(
            final MagicGame game,
            final MagicPermanent source,
            final MagicPermanent target) {
            if (you != source.getController().getIndex()) {
                //remove this static after the update
                game.addDelayedAction(new MagicRemoveStaticAction(source, this));
                return false;
            } else {
                return true;
            }
        }
    };
}

[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                MagicTargetChoice.TARGET_CREATURE_YOUR_OPPONENT_CONTROLS,
                this,
                "Tap target creature opponent controls\$. " +
                "It doesn't untap during its controller's untap step as long as PN controls SN."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                final MagicPermanent perm ->
                final MagicPermanent source = event.getPermanent();
                game.doAction(new MagicTapAction(perm, true));
                final MagicTargetFilter<MagicPermanent> filter = new MagicTargetFilter.MagicPermanentTargetFilter(perm);
                game.doAction(new MagicAddStaticAction(source, control(source.getController().getIndex(), filter)));
            } as MagicPermanentAction);
        }
    }
]

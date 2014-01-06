[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Prevent all combat damage that would be dealt this turn. " +
                "If you have 5 or less life, tap all attacking creatures. Those creatures don't untap during their controller's next untap step."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            game.doAction(new MagicChangePlayerStateAction(
                player,
                MagicPlayerState.PreventAllCombatDamage
            ));
            game.doAction(new MagicChangePlayerStateAction(
                player.getOpponent(),
                MagicPlayerState.PreventAllCombatDamage
            ));
            if (MagicCondition.FATEFUL_HOUR.accept(event.getSource())) {
                final Collection<MagicPermanent> targets =
                    game.filterPermanents(player,MagicTargetFilter.TARGET_ATTACKING_CREATURE);
                for (final MagicPermanent perm : targets) {
                    game.doAction(new MagicTapAction(perm,true));
                    game.doAction(MagicChangeStateAction.Set(
                        perm,
                        MagicPermanentState.DoesNotUntapDuringNext
                    ));
                }
            }
        }
    }
]

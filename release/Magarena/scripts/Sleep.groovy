[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_PLAYER,
                this,
                "Tap all creatures target player\$ controls. Those creatures don't untap during their controller's next untap step."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game,new MagicPlayerAction() {
                public void doAction(final MagicPlayer player) {
                    final Collection<MagicPermanent> targets=
                        game.filterPermanents(player,MagicTargetFilter.TARGET_CREATURE_YOU_CONTROL);
                    for (final MagicPermanent creature : targets) {
                        game.doAction(new MagicTapAction(creature,true));
                        game.doAction(MagicChangeStateAction.Set(
                            creature,
                            MagicPermanentState.DoesNotUntapDuringNext
                        ));
                    }
                }
            });
        }
    }
]

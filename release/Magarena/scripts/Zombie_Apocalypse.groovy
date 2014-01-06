[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Return all Zombie creature cards from your graveyard " +
                "to the battlefield tapped, then destroy all Humans."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final List<MagicCard> zombies =
                    game.filterCards(player,MagicTargetFilter.TARGET_ZOMBIE_CARD_FROM_GRAVEYARD);
            for (final MagicCard target : zombies) {
                game.doAction(new MagicReanimateAction(
                    target,
                    player,
                    [MagicPlayMod.TAPPED]
                ));
            }
            final List<MagicPermanent> humans =
                    game.filterPermanents(player,MagicTargetFilter.TARGET_HUMAN);
            game.doAction(new MagicDestroyAction(humans));
        }
    }
]

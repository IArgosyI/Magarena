[
    new MagicStatic(
        MagicLayer.ModPT,
        MagicTargetFilter.TARGET_CREATURE) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            final MagicGame game = source.getGame();
            final int amount = game.filterCards(
                        source.getController(),
                        MagicTargetFilter.TARGET_CREATURE_CARD_FROM_ALL_GRAVEYARDS).size();
            pt.add(amount,amount);
        }
        @Override
        public boolean condition(final MagicGame game,final MagicPermanent source,final MagicPermanent target) {
            return (source.isEquipment()) ?
                source.getEquippedCreature() == target :
                source.getEnchantedCreature() == target;
        }
    }
]

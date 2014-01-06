[
    new MagicStatic(MagicLayer.ModPT) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            if (permanent.isEquipped()) {
                pt.add(2 * permanent.getEquipmentPermanents().size(),0);
            }
        }
    }
]

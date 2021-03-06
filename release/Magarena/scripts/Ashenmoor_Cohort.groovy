[
    new MagicStatic(MagicLayer.ModPT) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            final MagicPermanentFilterImpl filter = new MagicOtherPermanentTargetFilter(MagicTargetFilter.TARGET_BLACK_CREATURE, source);
            if (source.getController().controlsPermanent(filter)) {
                pt.add(1,1);
            }
        }
    }
]

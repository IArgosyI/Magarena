[
    new MagicStatic(MagicLayer.ModPT) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            if (permanent.getOpponent().getHand().isEmpty()) {
                pt.add(3,3);
            }
        }
    }
]

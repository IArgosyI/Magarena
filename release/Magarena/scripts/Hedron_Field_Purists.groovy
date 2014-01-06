[
    new MagicStatic(MagicLayer.SetPT) {
        @Override
        public void modPowerToughness(
                final MagicPermanent source,
                final MagicPermanent permanent,
                final MagicPowerToughness pt) {
            final int charges = permanent.getCounters(MagicCounterType.Charge);
            if (charges >= 5) {
                pt.set(2,5);
            } else if (charges >= 1) {
                pt.set(1,4);
            }
        }
    },
    new MagicIfDamageWouldBeDealtTrigger(MagicTrigger.PREVENT_DAMAGE) {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            if (permanent.isFriend(damage.getTarget())) {
                final int amountCounters = permanent.getCounters(MagicCounterType.Charge);
                final int amountPrevented =
                     amountCounters >= 5 ? 2 :
                     amountCounters >= 1 ? 1 : 0
                // Prevention effect.
                damage.prevent(amountPrevented);
            }
            return MagicEvent.NONE;
        }
    }
]

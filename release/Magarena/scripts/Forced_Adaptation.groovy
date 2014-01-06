[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            final MagicPermanent enchanted = permanent.getEnchantedCreature();
            return permanent.isController(upkeepPlayer) && enchanted.isValid() ?
                new MagicEvent(
                    permanent,
                    this,
                    "PN puts a +1/+1 counter on creature enchanted by SN."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent permanent=event.getPermanent();
            final MagicPermanent enchanted=permanent.getEnchantedCreature();
            if (enchanted.isValid()) {
                game.doAction(new MagicChangeCountersAction(enchanted,MagicCounterType.PlusOne,1,true));
            }
        }
    }
]

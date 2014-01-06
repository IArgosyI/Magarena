[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            return (permanent.isController(upkeepPlayer) &&
                    upkeepPlayer.getNrOfPermanents(MagicType.Land) >= 6) ?
                new MagicEvent(
                    permanent,
                    this,
                    "PN puts a 5/5 red Dragon creature token with flying onto the battlefield."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicPlayTokenAction(event.getPlayer(),TokenCardDefinitions.get("Dragon5")));
        }
    }
]

[
    new MagicWhenAttacksTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPermanent creature) {
            return (permanent==creature) ?
                new MagicEvent(
                    permanent,
                    this,
                    "PN puts two 1/1 white Soldier creature tokens " +
                    "onto the battlefield tapped and attacking."
                ) :
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player=event.getPlayer();
            final MagicCard card=MagicCard.createTokenCard(TokenCardDefinitions.get("Soldier"),player);
            for (int count=0; count < 2; count++) {
                game.doAction(new MagicPlayCardAction(
                    card,
                    player,
                    [MagicPlayMod.TAPPED, MagicPlayMod.ATTACKING]
                ));
            }
        }
    }
]

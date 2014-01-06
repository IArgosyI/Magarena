[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            return permanent.isOpponent(upkeepPlayer) ?
                new MagicEvent(
                    permanent,
                    upkeepPlayer,
                    this,
                    "SN deals 2 damage to PN unless PN has exactly three or exactly four cards in hand."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer opponent=event.getPlayer();
            final int amount=opponent.getHandSize();
            if (amount < 3 || amount > 4) {
                final MagicDamage damage=new MagicDamage(event.getSource(),opponent,2);
                game.doAction(new MagicDealDamageAction(damage));
            }
        }
    }
]

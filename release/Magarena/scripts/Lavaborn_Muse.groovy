[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer player) {
            return (permanent.isOpponent(player) &&
                    player.getHandSize() <= 2) ?
                new MagicEvent(
                    permanent,
                    player,
                    this,
                    "SN deals 3 damage to PN."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicDamage damage=new MagicDamage(event.getSource(),event.getPlayer(),3);
            game.doAction(new MagicDealDamageAction(damage));
        }
    }
]

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            final int amount=payedCost.getX();
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.TARGET_OPPONENT,
                this,
                "SN deals " + amount + " damage to target opponent\$. That player discards " + amount + " cards."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game,new MagicPlayerAction() {
                public void doAction(final MagicPlayer target) {
                    final int amount = event.getCardOnStack().getX();
                    final MagicDamage damage=new MagicDamage(event.getSource(),target,amount);
                    game.doAction(new MagicDealDamageAction(damage));
                    game.addEvent(new MagicDiscardEvent(event.getSource(), target, amount));
                }
            });
        }
    }
]

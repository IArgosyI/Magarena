[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "PN puts three 1/1 red Human creature " +
                "tokens with haste onto the battlefield. Sacrifice " +
                "those tokens at the beginning of the next end step."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final MagicCardDefinition token = TokenCardDefinitions.get("Human2");
            for (int x=3;x>0;x--) {
                final MagicPutIntoPlayAction action = new MagicPlayTokenAction(player, token);
                game.doAction(action);
                game.doAction(new MagicAddTriggerAction(
                    action.getPermanent(),
                    MagicAtEndOfTurnTrigger.Sacrifice
                ));
            }
        }
    }
]

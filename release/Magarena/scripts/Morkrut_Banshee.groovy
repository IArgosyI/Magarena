[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return game.getCreatureDiedThisTurn() ?
                new MagicEvent(
                    permanent,
                    MagicTargetChoice.TARGET_CREATURE,
                    new MagicWeakenTargetPicker(4,4),
                    this,
                    "Target creature\$ gets -4/-4 until end of turn."
                ) :
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicChangeTurnPTAction(creature,-4,-4));
                }
            });
        }
    }
]

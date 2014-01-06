def T = new MagicWhenDamageIsDealtTrigger() {
    @Override
    public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
        return (damage.getSource().isCreature() &&
                damage.isCombat() &&
                damage.getTarget() == permanent) ?
            new MagicEvent(
                permanent,
                damage.getSource(),
                this,
                "Destroy RN."
            ):
            MagicEvent.NONE;
    }

    @Override
    public void executeEvent(final MagicGame game, final MagicEvent event) {
        game.doAction(new MagicDestroyAction(event.getRefPermanent()));
    }
}

[
    new MagicPlaneswalkerActivation(1) {
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Until PN's next turn, whenever a creature deals combat damage to Vraska the Unseen, destroy that creature."
            );
        }
        @Override
        public void executeEvent(final MagicGame outerGame, final MagicEvent outerEvent) {
            final MagicWhenDamageIsDealtTrigger trigger = T;
            outerGame.doAction(new MagicAddTriggerAction(outerEvent.getPermanent(), trigger));
            // remove the trigger during player's next upkeep
            MagicAtUpkeepTrigger cleanup = new MagicAtUpkeepTrigger() {
                @Override
                public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
                    if (upkeepPlayer.getId() == outerEvent.getPlayer().getId()) {
                        game.addDelayedAction(new MagicRemoveTriggerAction(permanent, trigger));
                        game.addDelayedAction(new MagicRemoveTriggerAction(permanent, this));
                    }
                    return MagicEvent.NONE;
                }
            };
            outerGame.doAction(new MagicAddTriggerAction(outerEvent.getPermanent(), cleanup));

        }
    },
    new MagicPlaneswalkerActivation(-3) {
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.NEG_TARGET_NONLAND_PERMANENT,
                new MagicDestroyTargetPicker(false),
                this,
                "Destroy target nonland permanent."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,new MagicPermanentAction() {
                public void doAction(final MagicPermanent creature) {
                    game.doAction(new MagicDestroyAction(creature));
                }
            });
        }
    },
    new MagicPlaneswalkerActivation(-7) {
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Put three 1/1 black Assassin creature tokens onto the battlefield with " +
                "\"Whenever this creature deals combat damage to a player, that player loses the game.\""
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicPlayTokensAction(event.getPlayer(), TokenCardDefinitions.get("Assassin"), 3));
        }
    }
]

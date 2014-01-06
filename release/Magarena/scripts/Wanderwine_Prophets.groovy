[
    new MagicWhenDamageIsDealtTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            return (damage.getSource() == permanent &&
                    damage.getTarget().isPlayer() &&
                    damage.isCombat()) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(
                        MagicTargetChoice.SACRIFICE_MERFOLK
                    ),
                    MagicSacrificeTargetPicker.create(),
                    this,
                    "PN may\$ sacrifice a Merfolk\$. " +
                    "If you do, take an extra turn after this one"
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game,new MagicPermanentAction() {
                    public void doAction(final MagicPermanent creature) {
                        game.doAction(new MagicSacrificeAction(creature));
                        game.doAction(new MagicChangeExtraTurnsAction(event.getPlayer(),1));
                    }
                });
            }
        }
    }
]

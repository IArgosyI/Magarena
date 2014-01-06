[
    new MagicWhenDamageIsDealtTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            return (damage.getSource()==permanent &&
                    damage.getTarget().isPlayer() &&
                    damage.isCombat()) ?
                new MagicEvent(
                    permanent,
                    new MagicMayChoice(
                        new MagicPayManaCostChoice(MagicManaCost.create("{2}{G}")),
                        MagicColorChoice.MOST_INSTANCE
                    ),
                    this,
                    "You may\$ pay {2}{G}\$. If you do, choose a color\$. "+
                    "Put a 1/1 green Saproling creature token onto the battlefield for each permanent of that color."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                final MagicPlayer player=event.getPlayer();
                final MagicColor color=event.getChosenColor();
                final Collection<MagicPermanent> targets=
                    game.filterPermanents(player,MagicTargetFilter.TARGET_PERMANENT);
                for (final MagicPermanent permanent : targets) {
                    if (permanent.hasColor(color)) {
                        game.doAction(new MagicPlayTokenAction(player,TokenCardDefinitions.get("Saproling")));
                    }
                }
            }
        }
    }
]

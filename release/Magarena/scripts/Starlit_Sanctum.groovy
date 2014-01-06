[
   new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Gain Life"
   ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
         return [
                new MagicPayManaCostEvent(source,"{W}"),
                new MagicTapEvent(source),
                new MagicSacrificePermanentEvent(source,MagicTargetChoice.SACRIFICE_CLERIC)
            ];         
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                payedCost.getTarget(),
                this,
                "PN gains life equal to RN's toughness."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicChangeLifeAction(
                event.getPlayer(),
                event.getRefPermanent().getToughness()
            ));
        }
    },
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "Lose Life"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
         return [
                new MagicPayManaCostEvent(source,"{B}"),
                new MagicTapEvent(source),
                new MagicSacrificePermanentEvent(source,MagicTargetChoice.SACRIFICE_CLERIC)
            ];         
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.NEG_TARGET_PLAYER,
                payedCost.getTarget(),
                this,
                "Target player\$ loses life equal to RN's power."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game,new MagicPlayerAction() {
                public void doAction(final MagicPlayer player) {
                    game.doAction(new MagicChangeLifeAction(
                        player,
                        -event.getRefPermanent().getPower()
                    ));
                }
            });
        }
    }
]

[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump,1),
        "Color"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{G}"),
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicColorChoice.RED_WHITE_BLUE_INSTANCE,
                this,
                "SN becomes the color\$ of your choice until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent permanent=event.getPermanent();
            game.doAction(new MagicAddStaticAction(permanent,
                new MagicStatic(MagicLayer.SwitchPT,MagicStatic.UntilEOT) {
                @Override
                public int getColorFlags(final MagicPermanent perm, final int flags) {
                    return event.getChosenColor().getMask();
                }
            }));
        }
    }
]

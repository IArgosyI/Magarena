def Duplicate = new MagicAtUpkeepTrigger() {
    @Override
    public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
        return permanent.isController(upkeepPlayer) && permanent.isNonToken() ?
            new MagicEvent(
                permanent,
                this,
                "PN puts a token onto the battlefield that's a copy of SN."
            ):
            MagicEvent.NONE;
    }

    @Override
    public void executeEvent(final MagicGame game, final MagicEvent event) {
        game.doAction(new MagicPlayTokenAction(
            event.getPlayer(),
            event.getPermanent().getCardDefinition()
        ));
    }
};

def GainTrig = new MagicStatic(MagicLayer.Ability) {
    @Override
    public void modAbilityFlags(final MagicPermanent source, final MagicPermanent permanent, final Set<MagicAbility> flags) {
        permanent.addAbility(Duplicate);
    }
};

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicMayChoice(MagicTargetChoice.CREATURE),
                MagicCopyTargetPicker.create(),
                this,
                "Put SN onto the battlefield. You may\$ have SN enter the battlefield as a copy of any creature\$ on the battlefield, " + 
                "except it gains \"At the beginning of your upkeep, if this creature isn't a token, put a token onto the battlefield that's a copy of this creature.\""
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                event.processTargetPermanent(game, {
                    final MagicPermanent chosen ->
                    final MagicPlayCardFromStackAction action = new MagicPlayCardFromStackAction(
                        event.getCardOnStack(),
                        chosen.getCardDefinition()
                    );
                    game.doAction(action);
                    final MagicPermanent perm = action.getPermanent();
                    game.doAction(new MagicAddStaticAction(perm, GainTrig));
                } as MagicPermanentAction);
            } else {
                game.doAction(new MagicPlayCardFromStackAction(
                    event.getCardOnStack()
                ));
            }
        }
    }
]

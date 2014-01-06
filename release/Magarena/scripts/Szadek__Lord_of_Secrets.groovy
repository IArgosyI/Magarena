[
    new MagicIfDamageWouldBeDealtTrigger(MagicTrigger.REPLACE_DAMAGE) {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            int amount = 0;
            if (permanent == damage.getSource() &&
                damage.isCombat() &&
                damage.getTarget().isPlayer()) {
                // Replacement effect.
                amount = damage.replace();
            }

            return amount > 0 ?
                new MagicEvent(
                    permanent,
                    damage.getTarget(),
                    {
                        final MagicGame G, final MagicEvent E ->
                        G.doAction(new MagicChangeCountersAction(
                            E.getPermanent(),
                            MagicCounterType.PlusOne,
                            amount,
                            true
                        ));
                        G.doAction(new MagicMillLibraryAction(
                            E.getRefPlayer(),
                            amount
                        ));
                    } as MagicEventAction,
                    "Put ${amount} +1/+1 counters on SN and RN puts that many cards from the top of his or her library into his or her graveyard."
                ):
                MagicEvent.NONE;
        }
    }
]

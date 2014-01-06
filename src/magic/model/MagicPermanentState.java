package magic.model;

public enum MagicPermanentState {

    Tapped("tapped","{T}"),
    Summoned("summoned","{n}"),
    DoesNotUntapDuringNext("doesn't untap during its controller's next untap step","{s}"),
    Regenerated("regenerated","{r}"),
    CannotBeRegenerated("can't be regenerated","{~r}"),
    Attacking("attacking","{c}"),
    Blocking("blocking","{c}"),
    Blocked("blocked","{b}"),
    ExcludeManaSource("exclude as mana source",""),
    ExcludeFromCombat("exclude from combat",""),
    Destroyed("destroyed",""),
    CannotAttack("can't attack",""),
    NoCombatDamage("assigns no combat damage",""),
    MustPayEchoCost("",""),
    PreventAllDamage("prevent all damage that would be dealt this turn","")
    ;

    public static final int CLEANUP_MASK =
        Tapped.getMask()|
        Summoned.getMask()|
        DoesNotUntapDuringNext.getMask()|
        ExcludeManaSource.getMask()|
        ExcludeFromCombat.getMask()|
        MustPayEchoCost.getMask();

    private final String description;
    private final String text;
    private final int mask;

    private MagicPermanentState(final String description,final String text) {
        this.description=description;
        this.text=text;
        this.mask=1<<ordinal();
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public int getMask() {
        return mask;
    }

    public boolean hasState(final int flags) {
        return (flags&mask)!=0;
    }
}

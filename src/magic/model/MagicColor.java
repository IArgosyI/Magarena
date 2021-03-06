package magic.model;

import magic.data.IconImages;

import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MagicColor {

    White("white",'w'),
    Blue("blue",'u'),
    Black("black",'b'),
    Green("green",'g'),
    Red("red",'r')
    ;

    public static final int NR_COLORS=values().length;

    private final String name;
    private final char symbol;
    private final int mask;

    private MagicColor(final String name,final char symbol) {
        this.name=name;
        this.symbol=symbol;
        this.mask=1<<ordinal();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getMask() {
        return mask;
    }

    public boolean hasColor(final int flags) {
        return (flags&mask)!=0;
    }

    public MagicAbility getProtectionAbility() {
        switch (this) {
            case White: return MagicAbility.ProtectionFromWhite;
            case Blue: return MagicAbility.ProtectionFromBlue;
            case Black: return MagicAbility.ProtectionFromBlack;
            case Green: return MagicAbility.ProtectionFromGreen;
            case Red: return MagicAbility.ProtectionFromRed;
        }
        throw new RuntimeException("No protection ability for MagicColor " + this);
    }

    public MagicAbility getLandwalkAbility() {
        switch (this) {
            case White: return MagicAbility.Plainswalk;
            case Blue: return MagicAbility.Islandwalk;
            case Black: return MagicAbility.Swampwalk;
            case Red: return MagicAbility.Mountainwalk;
            case Green: return MagicAbility.Forestwalk;
        }
        throw new RuntimeException("No landwalk ability for MagicColor " + this);
    }

    public MagicAbility getCannotBeBlockedByAbility() {
        switch (this) {
            case White: return MagicAbility.CannotBeBlockedByWhite;
            case Blue: return MagicAbility.CannotBeBlockedByBlue;
            case Black: return MagicAbility.CannotBeBlockedByBlack;
            case Green: return MagicAbility.CannotBeBlockedByGreen;
            case Red: return MagicAbility.CannotBeBlockedByRed;
        }
        throw new RuntimeException("No CannotBeBlockedBy ability for MagicColor " + this);
    }

    public MagicSubType getLandSubType() {
        switch (this) {
            case White: return MagicSubType.Plains;
            case Blue: return MagicSubType.Island;
            case Black: return MagicSubType.Swamp;
            case Green: return MagicSubType.Forest;
            case Red: return MagicSubType.Mountain;
        }
        throw new RuntimeException("No land subtype for MagicColor " + this);
    }

    public MagicManaType getManaType() {
        switch (this) {
            case White: return MagicManaType.White;
            case Blue: return MagicManaType.Blue;
            case Black: return MagicManaType.Black;
            case Green: return MagicManaType.Green;
            case Red: return MagicManaType.Red;
        }
        return MagicManaType.Colorless;
    }

    public ImageIcon getIcon() {
        switch (this) {
            case White: return IconImages.WHITE;
            case Blue: return IconImages.BLUE;
            case Black: return IconImages.BLACK;
            case Green: return IconImages.GREEN;
            case Red: return IconImages.RED;
        }
        throw new RuntimeException("No icon for MagicColor " + this);
    }

    public static int getFlags(final String colors) {
        int flags=0;
        for (int index=0;index<colors.length();index++) {
            final char symbol=colors.charAt(index);
            final MagicColor color=getColor(symbol);
            flags|=color.getMask();
        }
        return flags;
    }

    public static MagicColor getColor(final char symbol) {
        final char usymbol=Character.toLowerCase(symbol);
        for (final MagicColor color : values()) {
            if (color.symbol==usymbol) {
                return color;
            }
        }
        throw new RuntimeException("No corresponding MagicColor for " + symbol);
    }

    public static String getRandomColors(final int count) {
        final List<MagicColor> colors = new ArrayList<MagicColor>(Arrays.asList(values()));
        final StringBuilder colorText=new StringBuilder();
        for (int c=count;c>0;c--) {
            final int index=MagicRandom.nextRNGInt(colors.size());
            colorText.append(colors.remove(index).getSymbol());
        }
        return colorText.toString();
    }

    private static int numColors(final MagicSource source) {
        int numColors = 0;
        for (final MagicColor color : values()) {
            if (source.hasColor(color)) {
                numColors++;
            }
        }
        return numColors;
    }

    private static boolean isColorless(final MagicSource source) {
        return numColors(source) == 0;
    }

    public static boolean isMono(final MagicSource source) {
        return numColors(source) == 1;
    }

    public static boolean isMulti(final MagicSource source) {
        return numColors(source) > 1;
    }
}

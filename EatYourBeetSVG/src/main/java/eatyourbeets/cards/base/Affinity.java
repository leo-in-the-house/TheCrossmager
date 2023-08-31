package eatyourbeets.cards.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.affinity.AbstractAffinityPower;
import eatyourbeets.resources.GR;
import eatyourbeets.ui.TextureCache;

public enum Affinity implements Comparable<Affinity>
{
    Red(0, "Red", GR.Common.Images.Affinities.Red),
    Green(1, "Green", GR.Common.Images.Affinities.Green),
    Blue(2, "Blue", GR.Common.Images.Affinities.Blue),
    White(3, "White", GR.Common.Images.Affinities.White),
    Black(4, "Black", GR.Common.Images.Affinities.Black),
    Yellow(5, "Yellow", GR.Common.Images.Affinities.Yellow),
    Brown(6, "Brown", GR.Common.Images.Affinities.Brown),
    Pink(7, "Pink", GR.Common.Images.Affinities.Pink),
    Violet(8, "Violet", GR.Common.Images.Affinities.Violet),
    Teal(9, "Teal", GR.Common.Images.Affinities.Teal),
    Star(-1, "Star", GR.Common.Images.Affinities.Star),
    General(-2, "General", GR.Common.Images.Affinities.General),
    Sealed(-3, "Sealed", GR.Common.Images.Affinities.Seal);

    public static final int MAX_ID = 4;

    protected static final TextureCache BorderBG = GR.Common.Images.Affinities.BorderBG;
    protected static final TextureCache BorderFG = GR.Common.Images.Affinities.BorderFG;
    protected static final TextureCache BorderLV2 = GR.Common.Images.Affinities.Border;
    protected static final TextureCache BorderLV1 = GR.Common.Images.Affinities.Border_Weak;
    protected static final TextureCache BorderSealed = GR.Common.Images.Affinities.Border_Seal;
    protected static final Affinity[] BASIC_TYPES = new Affinity[10];
    protected static final Affinity[] BASIC_TYPES_M = new Affinity[11];
    protected static final Affinity[] ALL_TYPES = new Affinity[12];

    static
    {
        ALL_TYPES[0] = BASIC_TYPES[0] = BASIC_TYPES_M[0] = Red;
        ALL_TYPES[1] = BASIC_TYPES[1] = BASIC_TYPES_M[1] = Green;
        ALL_TYPES[2] = BASIC_TYPES[2] = BASIC_TYPES_M[2] = Blue;
        ALL_TYPES[3] = BASIC_TYPES[3] = BASIC_TYPES_M[3] = White;
        ALL_TYPES[4] = BASIC_TYPES[4] = BASIC_TYPES_M[4] = Black;
        ALL_TYPES[5] = BASIC_TYPES[5] = BASIC_TYPES_M[5] = Yellow;
        ALL_TYPES[6] = BASIC_TYPES[6] = BASIC_TYPES_M[6] = Brown;
        ALL_TYPES[7] = BASIC_TYPES[7] = BASIC_TYPES_M[7] = Pink;
        ALL_TYPES[8] = BASIC_TYPES[8] = BASIC_TYPES_M[8] = Violet;
        ALL_TYPES[9] = BASIC_TYPES[9] = BASIC_TYPES_M[9] = Teal;
        ALL_TYPES[10] = BASIC_TYPES_M[10] = Star;
        ALL_TYPES[11] = Sealed;
    }

    public static Affinity[] Basic()
    {
        return BASIC_TYPES;
    }

    public static Affinity[] All(boolean includeSealed)
    {
        return includeSealed ? ALL_TYPES : BASIC_TYPES_M;
    }

    public final int ID;
    public final TextureCache Icon;
    public final String Name;

    Affinity(int id, String name, TextureCache icon)
    {
        this.ID = id;
        this.Icon = icon;
        this.Name = name;
    }

    public Texture GetIcon()
    {
        return Icon.Texture();
    }

    public Texture GetBorder(int level)
    {
        return (this == Sealed ? BorderSealed : level > 1 ? BorderLV2 : BorderLV1).Texture();
    }

    public Texture GetBackground(int level, int upgrade)
    {
        return (this != Sealed && ((level + upgrade) > 1)) ? BorderBG.Texture() : null;
    }

    public Texture GetForeground(int level)
    {
        return (this != Sealed && level > 1 ? BorderFG.Texture() : null);
    }

    public TextureRegion GetAffinityIcon()
    {
        switch (this)
        {
            case Red: return GR.Tooltips.RedThreshold.icon;

            case Green: return GR.Tooltips.GreenThreshold.icon;

            case Blue: return GR.Tooltips.BlueThreshold.icon;

            case White: return GR.Tooltips.WhiteThreshold.icon;

            case Black: return GR.Tooltips.BlackThreshold.icon;

            case Brown: return GR.Tooltips.BrownThreshold.icon;

            case Yellow: return GR.Tooltips.YellowThreshold.icon;

            case Teal: return GR.Tooltips.TealThreshold.icon;

            case Pink: return GR.Tooltips.PinkThreshold.icon;

            case Violet: return GR.Tooltips.VioletThreshold.icon;

            case Star: return GR.Tooltips.Affinity_Star.icon;

            case General: return GR.Tooltips.Affinity_General.icon;

            case Sealed: return GR.Tooltips.Affinity_Sealed.icon;

            default: return null;
        }
    }

    public Color GetAlternateColor(float lerp)
    {
        return Color.WHITE.cpy().lerp(GetAlternateColor(), lerp);
    }

    public Color GetAlternateColor()
    {
        switch (this)
        {
            case Red: return new Color(0.8f, 0.5f, 0.5f, 1f);

            case Green: return new Color(0.45f, 0.7f, 0.55f, 1f);

            case Blue: return new Color(0.45f, 0.55f, 0.7f, 1f);

            case White: return new Color(0.8f, 0.8f, 0.3f, 1f);

            case Black: return new Color(0.55f, 0.1f, 0.85f, 1);//0.7f, 0.55f, 0.7f, 1f);

            case Yellow: return new Color(0.8f, 0.8f, 0.5f, 1f);

            case Teal: return new Color(0.45f, 0.8f, 0.8f, 1f);

            case Pink: return new Color(0.8f, 0.45f, 0.7f, 1f);

            case Violet: return new Color(0.8f, 0.3f, 0.8f, 1f);

            case Brown: return new Color(0.85f, 0.1f, 0.2f, 1);//0.7f, 0.55f, 0.7f, 1f);

            case Star: default: return new Color(0.25f, 0.25f, 0.25f, 1f);
        }
    }

    public static Affinity FromTooltip(EYBCardTooltip tooltip)
    {   //@Formatter: Off
        if (tooltip.Is(GR.Tooltips.Affinity_Red)    ) { return Affinity.Red;     }
        if (tooltip.Is(GR.Tooltips.Affinity_Green)  ) { return Affinity.Green;   }
        if (tooltip.Is(GR.Tooltips.Affinity_Blue)   ) { return Affinity.Blue;    }
        if (tooltip.Is(GR.Tooltips.Affinity_White)  ) { return Affinity.White;   }
        if (tooltip.Is(GR.Tooltips.Affinity_Black)   ) { return Affinity.Black;    }
        if (tooltip.Is(GR.Tooltips.Affinity_Pink)    ) { return Affinity.Pink;     }
        if (tooltip.Is(GR.Tooltips.Affinity_Teal)  ) { return Affinity.Teal;   }
        if (tooltip.Is(GR.Tooltips.Affinity_Brown)   ) { return Affinity.Brown;    }
        if (tooltip.Is(GR.Tooltips.Affinity_Yellow)  ) { return Affinity.Yellow;   }
        if (tooltip.Is(GR.Tooltips.Affinity_Violet)   ) { return Affinity.Violet;    }
        if (tooltip.Is(GR.Tooltips.Affinity_Star)   ) { return Affinity.Star;    }
        if (tooltip.Is(GR.Tooltips.Affinity_General)) { return Affinity.General; }
        if (tooltip.Is(GR.Tooltips.Affinity_Sealed) ) { return Affinity.Sealed; }
        return null;
    }   //@Formatter: On

    public EYBCardTooltip GetTooltip()
    {
        switch (this)
        {
            case Red: return GR.Tooltips.Affinity_Red;
            case Green: return GR.Tooltips.Affinity_Green;
            case Blue: return GR.Tooltips.Affinity_Blue;
            case White: return GR.Tooltips.Affinity_White;
            case Black: return GR.Tooltips.Affinity_Black;
            case Pink: return GR.Tooltips.Affinity_Pink;
            case Teal: return GR.Tooltips.Affinity_Teal;
            case Brown: return GR.Tooltips.Affinity_Brown;
            case Yellow: return GR.Tooltips.Affinity_Yellow;
            case Violet: return GR.Tooltips.Affinity_Violet;
            case Star: return GR.Tooltips.Affinity_Star;
            case General: return GR.Tooltips.Affinity_General;
            case Sealed: return GR.Tooltips.Affinity_Sealed;

            default: throw new RuntimeException("Invalid enum value: " + this.name());
        }
    }

    public EYBCardTooltip GetScalingTooltip()
    {
        switch (this)
        {
            case Red: return GR.Tooltips.RedScaling;
            case Green: return GR.Tooltips.GreenScaling;
            case Blue: return GR.Tooltips.BlueScaling;
            case White: return GR.Tooltips.WhiteScaling;
            case Black: return GR.Tooltips.BlackScaling;
            case Brown: return GR.Tooltips.BrownScaling;
            case Pink: return GR.Tooltips.PinkScaling;
            case Teal: return GR.Tooltips.TealScaling;
            case Violet: return GR.Tooltips.VioletScaling;
            case Yellow: return GR.Tooltips.YellowScaling;
            case Star: return GR.Tooltips.MulticolorScaling;

            default: throw new RuntimeException("Invalid enum value: " + this.name());
        }
    }

    public TextureRegion GetPowerIcon(boolean thresholdPower)
    {
        final EYBCardTooltip tip = GetPowerTooltip(thresholdPower);
        return tip != null ? tip.icon : null;
    }

    public EYBCardTooltip GetPowerTooltip(boolean thresholdPowers)
    {
        if (thresholdPowers)
        {
            switch (this)
            {
                case Red: return GR.Tooltips.Strength;
                case Green: return GR.Tooltips.Dexterity;
                case Blue: return GR.Tooltips.Focus;
                case White: return GR.Tooltips.Enlightenment;
                case Black: return GR.Tooltips.Insanity;
                case Brown: return GR.Tooltips.Resistance;
                case Pink: return GR.Tooltips.Knowledge;
                case Teal: return GR.Tooltips.Innovation;
                case Violet: return GR.Tooltips.Pestilence;
                case Yellow: return GR.Tooltips.Supercharge;
                case Star: return GR.Tooltips.Affinity_Star;

                default: throw new RuntimeException("Invalid enum value: " + this.name());
            }
        }
        else
        {
            switch (this)
            {
                case Red: return GR.Tooltips.RedThreshold;
                case Green: return GR.Tooltips.GreenThreshold;
                case Blue: return GR.Tooltips.BlueThreshold;
                case White: return GR.Tooltips.WhiteThreshold;
                case Black: return GR.Tooltips.BlackThreshold;
                case Brown: return GR.Tooltips.BrownThreshold;
                case Pink: return GR.Tooltips.PinkThreshold;
                case Teal: return GR.Tooltips.TealThreshold;
                case Violet: return GR.Tooltips.VioletThreshold;
                case Yellow: return GR.Tooltips.YellowThreshold;
                case Star: return GR.Tooltips.Affinity_Star;

                default: throw new RuntimeException("Invalid enum value: " + this.name());
            }
        }
    }

    public AbstractAffinityPower GetPower()
    {
        return CombatStats.Affinities.GetPower(this);
    }
}
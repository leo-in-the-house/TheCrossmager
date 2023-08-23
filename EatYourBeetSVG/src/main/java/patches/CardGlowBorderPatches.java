package patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
import eatyourbeets.utilities.FieldInfo;
import eatyourbeets.utilities.JUtils;

public class CardGlowBorderPatches
{
    protected static final FieldInfo<AbstractCard> _card = JUtils.GetField("card", CardGlowBorder.class);
    protected static final FieldInfo<TextureAtlas.AtlasRegion> _img = JUtils.GetField("img", CardGlowBorder.class);
    protected static final FieldInfo<Float> _scale = JUtils.GetField("scale", CardGlowBorder.class);
    protected static final FieldInfo<Color> _color = JUtils.GetField("color", AbstractGameEffect.class);

    public static Color overrideColor;

    @SpirePatch(clz = CardGlowBorder.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, Color.class})
    public static class CardGlowBorderPatches_ctor
    {
        @SpirePostfixPatch
        public static void Method(CardGlowBorder __instance, AbstractCard card, Color letsHardcodeEverything)
        {
            if (overrideColor != null)
            {
                Color color = _color.Get(__instance);
                if (color != null)
                {
                    color.r = overrideColor.r;
                    color.g = overrideColor.g;
                    color.b = overrideColor.b;
                }
            }
        }
    }

    @SpirePatch(clz = CardGlowBorder.class, method = "update")
    public static class CardGlowBorderPatches_Update
    {
        @SpirePrefixPatch
        public static void Method(CardGlowBorder __instance)
        {
            AbstractCard card = _card.Get(__instance);
            if (card.transparency < 0.9f)
            {
                __instance.duration = 0f;
            }
        }
    }
}
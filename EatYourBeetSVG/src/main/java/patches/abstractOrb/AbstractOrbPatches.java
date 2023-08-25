package patches.abstractOrb;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.orbs.*;
import eatyourbeets.powers.CombatStats;

public class AbstractOrbPatches
{
    @SpirePatch(clz = Dark.class, method = "onEndOfTurn")
    @SpirePatch(clz = Frost.class, method = "onEndOfTurn")
    @SpirePatch(clz = Lightning.class, method = "onEndOfTurn")
    public static class AbstractOrb_onEndOfTurn
    {
        @SpirePostfixPatch
        public static void Postfix(AbstractOrb __instance)
        {
            if (!(__instance instanceof EmptyOrbSlot))
            {
                CombatStats.OnOrbPassiveEffect(__instance);
            }
        }
    }

    @SpirePatch(clz = Plasma.class, method = "onStartOfTurn")
    public static class AbstractOrb_onStartOfTurn
    {
        @SpirePostfixPatch
        public static void Postfix(AbstractOrb __instance)
        {
            if (!(__instance instanceof EmptyOrbSlot))
            {
                CombatStats.OnOrbPassiveEffect(__instance);
            }
        }
    }

    @SpirePatch(clz = AbstractOrb.class, method = "applyFocus")
    public static class AbstractOrbPatches_ApplyFocus
    {
        @SpirePostfixPatch
        public static void Postfix(AbstractOrb __instance)
        {
            AbstractOrbPatches.ApplyAmountChangeToOrb(__instance);
        }
    }

    public static void ApplyAmountChangeToOrb(AbstractOrb __instance)
    {
        String orbType = __instance.ID;

        __instance.passiveAmount += CombatStats.GetAmountIncreasedOnOrb(orbType);
    }
}
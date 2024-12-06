package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.relics.VioletLotus;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

@SpirePatch(clz = VioletLotus.class, method = "onChangeStance")
public class VioletLotusPatches
{
    @SpirePostfixPatch
    public static SpireReturn Postfix(VioletLotus __instance, AbstractStance prevStance, AbstractStance newStance)
    {
        if (GameUtilities.InStance(CalmStance.STANCE_ID)) {
            __instance.flash();
            GameActions.Bottom.GainEnergy(1);
        }

        return SpireReturn.Return(null);
    }
}
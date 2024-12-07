package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.relics.TeardropLocket;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;

@SpirePatch(clz = TeardropLocket.class, method = "atBattleStart")
public class TeardropLocketPatches
{
    public static SpireReturn Prefix(TeardropLocket __instance)
    {
        __instance.flash();
        GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID);

        return SpireReturn.Return(null);
    }
}
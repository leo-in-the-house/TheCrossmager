package patches.cInputHelper;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import eatyourbeets.utilities.InputManager;

@SpirePatch(clz= CInputHelper.class, method="listenerRelease")
public class CInputHelper_ListenerRelease
{
    @SpirePrefixPatch
    public static void Method(int keycode)
    {
        InputManager.OnControllerKeyRelease(keycode);
    }
}
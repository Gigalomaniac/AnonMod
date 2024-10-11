package patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.screens.DeathScreen;

@SpirePatch2(clz = DeathScreen.class, method = "updateAscensionProgress")
public class FixAscenscionUnlockOnGameoverWinPatch {
    public static boolean updateAscProgress;

    @SpirePostfixPatch
    public static void patch() {
        updateAscProgress = false;
    }
}

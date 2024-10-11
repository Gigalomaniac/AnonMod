package patch;

import Mod.AnonMod;
import characters.char_Anon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.scenes.TitleCloud;

import java.util.Collections;
import java.util.Random;

@SpirePatch(
        clz = TitleCloud.class,
        method = "render"
)
public class CloudPatch {
    public CloudPatch() {
    }

    @SpirePrefixPatch
    public static SpireReturn<Void> renderTitleCloud() {
        return SpireReturn.Return();
    }
}

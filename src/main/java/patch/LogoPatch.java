package patch;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import java.lang.reflect.Field;

@SpirePatch(
        clz = TitleBackground.class,
        method = "<ctor>"
)
public class LogoPatch {
    public LogoPatch() {
    }

    @SpireInsertPatch(
            rloc = 82
    )
    public static void Insert(TitleBackground __instance) {
        try {
            Field skyField = TitleBackground.class.getDeclaredField("titleLogoImg");

            skyField.setAccessible(true);
            Texture logo = ImageMaster.loadImage("img/UI/Title/logo.png");
            Field WField = TitleBackground.class.getDeclaredField("W");
            Field HField = TitleBackground.class.getDeclaredField("H");
            WField.setAccessible(true);
            HField.setAccessible(true);
            int w = logo.getWidth();
            int h = logo.getWidth();
            skyField.set(__instance, logo);
            WField.set(__instance, w);
            HField.set(__instance, h);
        } catch (IllegalAccessException | NoSuchFieldException var3) {
            var3.printStackTrace();
        }

    }
}

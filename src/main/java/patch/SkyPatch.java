package patch;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import java.lang.reflect.Field;
import java.util.Random;

@SpirePatch(
        clz = TitleBackground.class,
        method = "<ctor>"
)
public class SkyPatch {
    public SkyPatch() {
    }

    @SpireInsertPatch(
            rloc = 56
    )
    public static void Insert(TitleBackground __instance) {
        try {
            Field skyField = TitleBackground.class.getDeclaredField("sky");
            skyField.setAccessible(true);
            String title = "title1";


            TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img/UI/Title/title.atlas"));
            TextureAtlas.AtlasRegion newSky = atlas.findRegion(title);
            skyField.set(__instance, newSky);
        } catch (IllegalAccessException | NoSuchFieldException var7) {
            var7.printStackTrace();
        }

    }
}

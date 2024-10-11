package patch;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
@SpirePatch(clz = MainMusic.class, method = "getSong")
public class musicPatch
{
    @SpireInsertPatch(rloc = 0)
    public static SpireReturn<Music> Insert(MainMusic __instance, String key) { return SpireReturn.Return(getsong(key).get()); }

    public static SpireReturn<Music> getsong(String key) {
        switch (key) {
            case "MENU":
                return SpireReturn.Return(MainMusic.newMusic("audio/music/04. 壱雫空 -Instrumental-.mp3"));
        }
        return SpireReturn.Return(MainMusic.newMusic("audio/music/STS_Level1_NewMix_v1.ogg"));
    }
}
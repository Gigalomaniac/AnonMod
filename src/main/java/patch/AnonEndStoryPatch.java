package patch;
import actions.movie.CanStopMediaPlayerAction;
import actions.movie.AnonEndingSimplePlayVideoEffect;
import characters.char_Anon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static utils.AnonSpireKit.addToBot;

public class AnonEndStoryPatch
    {
        public static boolean notShowPlayerPowerTip = false;

        @SpirePatch2(clz = com.megacrit.cardcrawl.rooms.TrueVictoryRoom.class, method = "onPlayerEntry")
        public static class AbstractPlayerPowerTipPatch
        {
            public static SpireReturn<Void> Prefix() {
                if(AbstractDungeon.player instanceof char_Anon) {
                        AbstractDungeon.isScreenUp = true;
                        AbstractDungeon.overlayMenu.proceedButton.hide();
                        CardCrawlGame.fadeIn(1.0F);
                    AbstractDungeon.scene.fadeOutAmbiance();
                    CardCrawlGame.music.silenceBGM();
                    CardCrawlGame.music.silenceBGMInstantly();
                    CardCrawlGame.music.silenceTempBgmInstantly();
                        addToBot(new CanStopMediaPlayerAction(new AnonEndingSimplePlayVideoEffect("movie/mygo片尾曲.webm")));
                        return SpireReturn.Return();

                }
                return SpireReturn.Continue();
            } }

        @SpirePatch2(clz = com.megacrit.cardcrawl.rooms.TrueVictoryRoom.class, method = "update")
        public static class AbstractPlayerPowerTipPatch1
        {
            public static SpireReturn<Void> Prefix() {
                if(AbstractDungeon.player instanceof char_Anon){
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch2(clz = com.megacrit.cardcrawl.rooms.TrueVictoryRoom.class, method = "render")
        public static class AbstractPlayerPowerTipPatch2
        {
            public static SpireReturn<Void> Prefix() {
                if(AbstractDungeon.player instanceof char_Anon){
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch2(clz = com.megacrit.cardcrawl.rooms.TrueVictoryRoom.class, method = "renderAboveTopPanel")
        public static class AbstractPlayerPowerTipPatch3
        {
            public static SpireReturn<Void> Prefix() {
                if(AbstractDungeon.player instanceof char_Anon){
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
}

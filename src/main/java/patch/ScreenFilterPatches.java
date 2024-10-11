package patch;

import basemod.helpers.ScreenPostProcessorManager;
import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.List;

public class    ScreenFilterPatches
{
    public static final List<ScreenPostProcessor> postProcessors = new ArrayList();

    public static void addPostProcessor(ScreenPostProcessor processor) {
        postProcessors.add(processor);
        ScreenPostProcessorManager.addPostProcessor(processor);
    }

    public static void removePostProcessor(ScreenPostProcessor processor) {
        postProcessors.remove(processor);
        ScreenPostProcessorManager.removePostProcessor(processor);
    }

    @SpirePatch(clz = CardCrawlGame.class, method = "render")
    public static class GameRenderPatch
    {
        @SpireInsertPatch(locator = StartLocator.class)
        public static void BeforeSbStart(CardCrawlGame __instance, SpriteBatch ___sb) {
            if (!ScreenFilterPatches.postProcessors.isEmpty() &&
                    AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null &&
                    (AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT) {
                for (ScreenPostProcessor processor : ScreenFilterPatches.postProcessors) {
                    ScreenPostProcessorManager.removePostProcessor(processor);
                }
                ScreenFilterPatches.postProcessors.clear();
            }
        }

        public static class StartLocator
                extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(SpriteBatch.class, "begin");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }
}
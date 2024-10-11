package star;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.CardCrawlGame.GameMode;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.SeedHelper;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.shop.ShopScreen;
import patch.FixAscenscionUnlockOnGameoverWinPatch;

import java.lang.reflect.Field;

public class RestartRunHelper {
    public static boolean queuedRestart;
    public static boolean queuedScoreRestart;
    public static boolean queuedRoomRestart;
    public static boolean evilMode = false;
    private static Field evilField = null;

    public RestartRunHelper() {
    }

    public static void restartRun() {
        //Stop all lingering sounds from playing
        stopLingeringSounds();
        AbstractDungeon.getCurrRoom().clearEvent();

        //Fix Ascension unlock problem if beating third boss and not doing heart
        if(FixAscenscionUnlockOnGameoverWinPatch.updateAscProgress) {
            if(AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
                ReflectionHacks.privateMethod(DeathScreen.class, "updateAscensionProgress").invoke(AbstractDungeon.deathScreen);
            }
        }

        //Safety check to not call this method when the player restarts from the death/victory screen. This may cause crashes otherwise
        if(!queuedRestart) {
            AbstractDungeon.closeCurrentScreen();
        }
        //AbstractDungeon.dungeonMapScreen.closeInstantly();
        CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(Exordium.ID);
        //AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;

        AbstractDungeon.reset();
        Settings.hasEmeraldKey = false;
        Settings.hasRubyKey = false;
        Settings.hasSapphireKey = false;
        ShopScreen.resetPurgeCost();
        CardCrawlGame.tips.initialize();
        CardCrawlGame.metricData.clearData();
        CardHelper.clear();
        TipTracker.refresh();
        System.gc();
        System.out.println("Dungeon has been reset.");

        if(evilMode) {
            setDownfallMode();
        }

        if (CardCrawlGame.chosenCharacter == null) {
            CardCrawlGame.chosenCharacter = AbstractDungeon.player.chosenClass;
        }

        if (!Settings.seedSet) {
            System.out.println("Seed wasn't set, rerandomizing seed.");
            Long sTime = System.nanoTime();
            Random rng = new Random(sTime);
            Settings.seedSourceTimestamp = sTime;
            Settings.seed = SeedHelper.generateUnoffensiveSeed(rng);
            SeedHelper.cachedSeed = null;
        }
        AbstractDungeon.generateSeeds();
        System.out.println("Seeds have been reset.");

        CardCrawlGame.mode = CardCrawlGame.GameMode.CHAR_SELECT;
        System.out.println("Run has been reset.");
        queuedRestart = false;
        evilMode = false;
    }

    public static void scoreAndRestart() {
      AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());

        restartRun();
        queuedScoreRestart = false;
    }

    public static void restartRoom() {
        stopLingeringSounds();
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.dungeonMapScreen.closeInstantly();
        AbstractDungeon.reset();
        CardCrawlGame.loadingSave = true;
        CardCrawlGame.mode = GameMode.CHAR_SELECT;

        queuedRoomRestart = false;
    }

    public static void stopLingeringSounds() {
        CardCrawlGame.music.fadeAll();
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.stop("WIND");
        }

        if (AbstractDungeon.scene != null) {
            AbstractDungeon.scene.fadeOutAmbiance();
        }

    }

    public static boolean isDownfallMode() {
        if (evilField == null) {
            try {
                Class<?> clz = Class.forName("downfall.patches.EvilModeCharacterSelect");
                evilField = clz.getField("evilMode");
            } catch (NoSuchFieldException | ClassNotFoundException var2) {
                var2.printStackTrace();
            }
        }

        try {
            return evilField.getBoolean((Object)null);
        } catch (IllegalAccessException var1) {
            return false;
        }
    }

    public static void setDownfallMode() {
        if (evilField == null) {
            try {
                Class<?> clz = Class.forName("downfall.patches.EvilModeCharacterSelect");
                evilField = clz.getField("evilMode");
            } catch (NoSuchFieldException | ClassNotFoundException var2) {
                var2.printStackTrace();
            }
        }

        try {
            evilField.set((Object)null, true);
        } catch (IllegalAccessException var1) {
            throw new RuntimeException(var1);
        }
    }
}

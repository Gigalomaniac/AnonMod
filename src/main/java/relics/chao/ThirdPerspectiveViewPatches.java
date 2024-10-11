package relics.chao;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.stances.AbstractStance;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class ThirdPerspectiveViewPatches {
    private static boolean enable = false;
    private static float oldHbX = 0;
    private static float oldHbY = 0;
    private static float oldHbH = 0;
    private static float oldHbW = 0;

    public static void setEnable(boolean value) {
        if (value == enable) {
            return;
        }

        AbstractPlayer player = AbstractDungeon.player;
        if (player == null) {
            return;
        }

        enable = value;
        if (enable) {
            oldHbX = player.hb_x;
            oldHbY = player.hb_y;
            oldHbW = player.hb_w;
            oldHbH = player.hb_h;
            player.hb_x = 0;
            player.hb_y = 0;
            player.hb_w = 1920 / 3f;
            player.hb_h = 1080 / 9f * 4f;
        } else {
            player.hb_x = oldHbX;
            player.hb_y = oldHbY;
            player.hb_w = oldHbW;
            player.hb_h = oldHbH;
        }
        player.hb.width = player.hb_w;
        player.hb.height = player.hb_h;
        player.healthBarUpdatedEvent();
    }

    public static boolean getEnable() {
        return enable;
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "render")
    public static class AbstractDungeonRenderPatch {
        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    // Hide combat room foreground
                    if (m.getClassName().equals(AbstractScene.class.getName()) && m.getMethodName().equals("renderCombatRoomFg")) {
                        m.replace(String.format(" if (!%s.getEnable()) { $_ = $proceed($$); } ", ThirdPerspectiveViewPatches.class.getName()));
                    }
                }
            };
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "initializeClass")
    public static class AbstractPlayerInitializeClassPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance, String imgUrl, String shoulder2ImgUrl, String shouldImgUrl, String corpseImgUrl, CharSelectInfo info, @ByRef float[] hb_x, @ByRef float[] hb_y, @ByRef float[] hb_w, @ByRef float[] hb_h, EnergyManager energyManager) {
            if (!enable) {
                return;
            }

            oldHbX = hb_x[0];
            oldHbY = hb_y[0];
            oldHbW = hb_w[0];
            oldHbH = hb_h[0];
            hb_w[0] = 1920 / 3f;
            hb_h[0] = 1080 / 9f * 4f;
            hb_y[0] = 0;
            hb_x[0] = 0;
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "movePosition")
    public static class AbstractPlayerMovePositionPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance) {
            if (!enable) {
                return;
            }

            float drawX = Settings.WIDTH / 6f * (__instance.flipHorizontal ? 5 : 1);
            float drawY = Settings.HEIGHT / 9f * 2f;
            __instance.dialogX = drawX + 0.8f * __instance.hb_w / 2 * (__instance.flipHorizontal ? -1 : 1);
            __instance.dialogY = drawY + 170.0F * Settings.scale;
        }
    }

    @SpirePatch(clz = AbstractCreature.class, method = "renderHealth")
    public static class AbstractPlayerRenderHealthPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = { "x", "y" })
        public static void Insert(AbstractCreature __instance, SpriteBatch sb, @ByRef float[] x, @ByRef float[] y) {
            if (!enable) {
                return;
            }

            if (!(__instance instanceof AbstractPlayer)) {
                return;
            }

            x[0] = 40f * Settings.scale + (AbstractPlayerRenderPatch.playerDrawX - Settings.WIDTH / 6f) / (Settings.WIDTH * 2 / 3f) * (Settings.WIDTH - 80f * Settings.scale - __instance.healthHb.width);
            y[0] = Settings.HEIGHT - 250f * Settings.scale;
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "renderHealthBg");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = AbstractCreature.class, method = "refreshHitboxLocation")
    public static class AbstractPlayerRefreshHitboxLocationPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractCreature __instance) {
            if (!enable) {
                return;
            }

            if (!(__instance instanceof AbstractPlayer)) {
                return;
            }

            float drawX = Settings.WIDTH / 6f * (__instance.flipHorizontal ? 5 : 1);
            float drawY = Settings.HEIGHT / 9f * 2f;
            __instance.hb.move(drawX + __instance.hb_x, drawY + __instance.hb_y + __instance.hb_h / 2.0F);
            __instance.healthHb.move(20 * Settings.scale + __instance.hb_w / 2, Settings.HEIGHT - 250f * Settings.scale);
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "update")
    public static class AbstractPlayerUpdatePatch {
        private static final int[] HAND_HIDE_COUNT = { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4 };
        private static int floor = -1;
        private static float playerTargetDrawX;
        private static float oldPlayerTargetDrawX;

        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance) {
            if (!enable) {
                return;
            }

            int size = __instance.hand.group.size();
            int index = __instance.hand.group.indexOf(__instance.hoveredCard);
            float targetVisibility = 1;
            if (index != -1) {
                if (__instance.flipHorizontal) {
                    index = size - 1 - index;
                }
                if (size > 10) {
                    if (index * 10 / 4 <= size) {
                        targetVisibility = 0.5f;
                    }
                } else {
                    if (index < HAND_HIDE_COUNT[size]) {
                        targetVisibility = 0.5f;
                    }
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters != null) {
                AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.hoveredMonster;
                if (monster != null) {
                    if ((!__instance.flipHorizontal && monster.drawX < Settings.WIDTH / 3f) || (__instance.flipHorizontal && monster.drawX > 2 * Settings.WIDTH / 3f)) {
                        targetVisibility = 0.5f;
                    }
                }
            }

            AbstractPlayerRenderPatch.playerVisibility = MathHelper.fadeLerpSnap(AbstractPlayerRenderPatch.playerVisibility, targetVisibility);

            // next floor
            if (floor != AbstractDungeon.floorNum) {
                floor = AbstractDungeon.floorNum;
                AbstractPlayerRenderPatch.playerDrawX = -Settings.WIDTH / 6f;
            }

            if (__instance.isEscaping) {
                AbstractPlayerRenderPatch.playerDrawX += (Gdx.graphics.getDeltaTime() * 500.0F * Settings.scale) * (__instance.flipHorizontal ? -1 : 1);
            } else if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT || !AbstractDungeon.getCurrRoom().isBattleOver) {
                playerTargetDrawX = Settings.WIDTH / 6f * (__instance.flipHorizontal ? 5 : 1);
                AbstractPlayerRenderPatch.playerDrawX = MathHelper.cardLerpSnap(AbstractPlayerRenderPatch.playerDrawX, playerTargetDrawX);
                __instance.dialogX = playerTargetDrawX + 0.8f * __instance.hb_w / 2 * (__instance.flipHorizontal ? -1 : 1);
                if (oldPlayerTargetDrawX != playerTargetDrawX) {
                    for (int i = 0; i < __instance.orbs.size(); i++) {
                        __instance.orbs.get(i).setSlot(i, __instance.maxOrbs);
                    }
                    oldPlayerTargetDrawX = playerTargetDrawX;
                }
            }

            if (!__instance.isDead) {
                AbstractPlayerRenderPatch.playerDrawY = 0;
            }
        }
    }

    @SpirePatch(clz = DeathScreen.class, method = "update")
    public static class DeathScreenUpdatePatch {
        @SpirePostfixPatch
        public static void Postfix() {
            if (!enable) {
                return;
            }

            if (AbstractDungeon.player != null && AbstractDungeon.player.isDead) {
                AbstractPlayerRenderPatch.playerDrawY -= Gdx.graphics.getDeltaTime() * 800.0F * Settings.scale;
                AbstractDungeon.player.animX = MathUtils.sinDeg(AbstractPlayerRenderPatch.playerDrawY) * 80f * Settings.scale;
            }
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "render")
    public static class AbstractPlayerRenderPatch {
        public static float playerDrawX = Settings.WIDTH / 6f;
        public static float playerDrawY = 0;
        public static float playerVisibility = 1;

        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    // Hide combat room foreground
                    if (m.getClassName().equals(AbstractStance.class.getName()) && m.getMethodName().equals("render")) {
                        m.replace(String.format("$_ = $proceed($$); %s.renderPlayer(this, sb);", AbstractPlayerRenderPatch.class.getName()));
                    }
                }
            };
        }

        @SuppressWarnings("unused")
        public static void renderPlayer(AbstractPlayer __instance, SpriteBatch sb) {
            if (!enable) {
                return;
            }

            if (ReflectionHacks.getPrivate(__instance, AbstractPlayer.class, "renderCorpse")) {
                return;
            }

            sb.setColor(Color.WHITE);
            __instance.renderHand(sb);

            float distance = Settings.WIDTH - 1920 * Settings.scale;
            float percentage = (playerDrawX - Settings.WIDTH / 6f) / (Settings.WIDTH * 2 / 3f);

            float v1 = playerDrawX < Settings.WIDTH / 3f ? 1 : (playerDrawX > Settings.WIDTH * 2 / 3f ? 0 : (2 * Settings.WIDTH / 3f - playerDrawX) / (Settings.WIDTH / 3f));
            if (v1 > 0) {
                sb.setColor(1, 1, 1, playerVisibility * v1);
                sb.draw(__instance.shoulderImg, __instance.animX - Settings.WIDTH / 6f + playerDrawX + distance * percentage, playerDrawY, 1920.0F * Settings.scale, 1136.0F * Settings.scale, 0, 0, __instance.shoulderImg.getWidth(), __instance.shoulderImg.getHeight(), false, false);
            }

            float v2 = playerDrawX < Settings.WIDTH / 3f ? 0 : (playerDrawX > Settings.WIDTH * 2 / 3f ? 1 : (playerDrawX - Settings.WIDTH / 3f) / (Settings.WIDTH / 3f));
            if (v2 > 0) {
                sb.setColor(1, 1, 1, playerVisibility * v2);
                sb.draw(__instance.shoulderImg, -__instance.animX - Settings.WIDTH * 5 / 6f + playerDrawX + distance * percentage, playerDrawY, 1920.0F * Settings.scale, 1136.0F * Settings.scale, 0, 0, __instance.shoulderImg.getWidth(), __instance.shoulderImg.getHeight(), true, false);
            }
        }

        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<Void> Insert(AbstractPlayer __instance, SpriteBatch sb) {
            if (!enable) {
                return SpireReturn.Continue();
            }

            if (!ReflectionHacks.<Boolean>getPrivate(__instance, AbstractPlayer.class, "renderCorpse") &&
                    !(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
                __instance.hb.render(sb);
                __instance.healthHb.render(sb);
            }

            return SpireReturn.Return();
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.InstanceOfMatcher matcher = new Matcher.InstanceOfMatcher(RestRoom.class);
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = OverlayMenu.class, method = "render")
    public static class OverlayMenuRenderPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<Void> Insert(OverlayMenu __instance, SpriteBatch sb) {
            if (!enable) {
                return SpireReturn.Continue();
            }

            if (AbstractDungeon.player != null) {
                AbstractDungeon.player.hand.renderTip(sb);
            }
            return SpireReturn.Return();
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "renderHand");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = AbstractRoom.class, method = "render")
    public static class AbstractRoomRenderPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractRoom __instance, SpriteBatch sb) {
            if (!enable) {
                return;
            }

            if (!(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
                if (__instance.monsters != null && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.DEATH) {
                    __instance.monsters.render(sb);
                }
            }
        }

        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    // Hide original monster rendering
                    if (m.getClassName().equals(MonsterGroup.class.getName()) && m.getMethodName().equals("render")) {
                        m.replace(String.format(" if (!%s.getEnable()) { $_ = $proceed($$); } ", ThirdPerspectiveViewPatches.class.getName()));
                    }
                }
            };
        }
    }

    @SpirePatch(clz = AbstractOrb.class, method = "setSlot")
    public static class AbstractOrbSetSlot {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractOrb __instance, int slotNum, int maxOrbs) {
            if (!enable) {
                return;
            }

            float dist = 160.0F * Settings.scale + (float)maxOrbs * 15.0F * Settings.scale;
            float angle = 100.0F + (float)maxOrbs * 12.0F;
            float offsetAngle = angle / 2.0F;// 122
            angle *= (float)slotNum / ((float)maxOrbs - 1.0F);// 123
            angle += 90.0F - offsetAngle;// 124
            float drawX = AbstractPlayerUpdatePatch.playerTargetDrawX;
            float drawY = AbstractDungeon.player.hb_y;
            __instance.tX = dist * MathUtils.cosDeg(angle) + drawX;
            __instance.tY = 60.0F * Settings.scale + dist * MathUtils.sinDeg(angle) + drawY + AbstractDungeon.player.hb_h;
            if (maxOrbs == 1) {// 128
                __instance.tX = drawX;
                __instance.tY = 220.0F * Settings.scale + drawY + AbstractDungeon.player.hb_h;
            }
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(Hitbox.class, "move");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = AbstractOrb.class, method = "updateAnimation")
    public static class AbstractOrbUpdateAnimation {
        @SpirePostfixPatch
        public static void Postfix(AbstractOrb __instance) {
            if (!enable) {
                return;
            }

            ReflectionHacks.setPrivate(__instance, AbstractOrb.class, "scale",
                Interpolation.swingIn.apply(Settings.scale * 1.5f, 0.01F,
                        ReflectionHacks.<Float>getPrivate(__instance, AbstractOrb.class, "channelAnimTimer") / 0.5F));
        }
    }
}

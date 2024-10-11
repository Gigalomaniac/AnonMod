package patch.infoView;

import Mod.AnonMod;
import cards.AbstractLockAnonCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import relics.EliteAshAnonKey;
import relics.FirstHalfKey;
import relics.SecondHalfKey;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class infoViewPatch {

    public static void init(SingleCardViewPopup _inst, AbstractCard card, boolean unlock) {
        if (card instanceof AbstractLockAnonCard) {
            System.out.println("initinit" + ((AbstractLockAnonCard) card).locked);
            if (!((AbstractLockAnonCard) card).locked ) {
                AnonMod.saves.setBool("MachineHeartLocked", false);
                ((AbstractLockAnonCard) card).unlockInfo();
            }
            infoViewField.lock.set(_inst, null);
            if (((AbstractLockAnonCard) card).infoStage < ((AbstractLockAnonCard) card).maxInfo) {
                {

                    infoViewField.nextLock.set(_inst, null);
                    infoViewField.nextInfo.set(_inst, new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale));
                    ((Hitbox) infoViewField.nextInfo.get(_inst)).move(Settings.WIDTH / 2.0F + 250.0F * Settings.scale, 340.0F * Settings.scale);
                }
            } else {
                infoViewField.nextLock.set(_inst, null);
                infoViewField.nextInfo.set(_inst, null);
            }
                if (((AbstractLockAnonCard) card).infoStage > 1) {
                    infoViewField.lastInfo.set(_inst, new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale));
                    ((Hitbox) infoViewField.lastInfo.get(_inst)).move(Settings.WIDTH / 2.0F - 250.0F * Settings.scale, 340.0F * Settings.scale);
                } else {

                    infoViewField.lastInfo.set(_inst, null);
                }
//            }
        }else {

            infoViewField.lock.set(_inst, null);
            infoViewField.lastInfo.set(_inst, null);
            infoViewField.nextLock.set(_inst, null);
            infoViewField.nextInfo.set(_inst, null);
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "open", paramtypez = {AbstractCard.class, CardGroup.class})
    public static class openpatch {
        public static void Postfix(SingleCardViewPopup _inst, AbstractCard card, CardGroup group) {
            infoViewPatch.init(_inst, card, true);
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "open", paramtypez = {AbstractCard.class})
    public static class openpatch2 {
        public static void Postfix(SingleCardViewPopup _inst, AbstractCard card) {
            infoViewPatch.init(_inst, card, true);
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "updateArrows")
    public static class updateArrows {
        @SpirePrefixPatch
        public static void prefix(SingleCardViewPopup _inst) throws NoSuchFieldException, IllegalAccessException {
            Field card = _inst.getClass().getDeclaredField("card");
            card.setAccessible(true);

            if (card.get(_inst) instanceof AbstractLockAnonCard) {
            if (((AbstractLockAnonCard)card.get(_inst)).locked == true){
                ((Hitbox) infoViewField.nextInfo.get(_inst)).update();
            }else {
                if (infoViewField.nextInfo.get(_inst) != null) {
                    if (((AbstractLockAnonCard)card.get(_inst)).locked  == false) {
                        ((Hitbox) infoViewField.nextInfo.get(_inst)).update();
                        if (((Hitbox) infoViewField.nextInfo.get(_inst)).justHovered) {
                            CardCrawlGame.sound.play("UI_HOVER");
                        }
                        if (((Hitbox) infoViewField.nextInfo.get(_inst)).clicked) {
                            ((AbstractLockAnonCard)card.get(_inst)).infoStage++;
                            if(AbstractDungeon.player!=null)
                            if(AbstractDungeon.player.hasRelic(FirstHalfKey.ID) && AbstractDungeon.player.hasRelic(SecondHalfKey.ID) && !AbstractDungeon.player.hasRelic(EliteAshAnonKey.ID)){
                                EliteAshAnonKey r = new EliteAshAnonKey();
                                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), r);
                            }
                            ((AbstractLockAnonCard) card.get(_inst)).initInfo();

                            infoViewPatch.init(_inst, (AbstractCard) card.get(_inst), true);
                        }
                    }
                }
            }
            if (infoViewField.lastInfo.get(_inst) != null) {
                ((Hitbox) infoViewField.lastInfo.get(_inst)).update();
                if (((Hitbox) infoViewField.lastInfo.get(_inst)).justHovered) {
                    CardCrawlGame.sound.play("UI_HOVER");
                }
                if (((Hitbox) infoViewField.lastInfo.get(_inst)).clicked) {
                    ((AbstractLockAnonCard) card.get(_inst)).infoStage--;
                    ((AbstractLockAnonCard) card.get(_inst)).initInfo();

                    infoViewPatch.init(_inst, (AbstractCard) card.get(_inst), true);
                }
            }
        }
    }

    }
    @SpirePatch(clz = SingleCardViewPopup.class, method = "updateInput")
    public static class updateInput {
        @SpirePrefixPatch
        public static SpireReturn prefix(SingleCardViewPopup _inst) throws NoSuchFieldException, IllegalAccessException {
            Field card = _inst.getClass().getDeclaredField("card");
            card.setAccessible(true);

            if (card.get(_inst) instanceof AbstractLockAnonCard) {
//                if (CogitoBucket.level[((AbstractLockAnonCard)card.get(_inst)).AbnormalityID] == 0 && infoViewField.lock.get(_inst) != null &&
//                        InputHelper.justClickedLeft &&
//                        ((Hitbox)infoViewField.lock.get(_inst)).hovered) {
//                    ((Hitbox)infoViewField.lock.get(_inst)).clickStarted = true;
//                    CardCrawlGame.sound.play("UI_CLICK_1");
//                    return SpireReturn.Return(null);
//                }

                if (infoViewField.nextLock.get(_inst) != null &&
                        InputHelper.justClickedLeft &&
                        ((Hitbox) infoViewField.nextLock.get(_inst)).hovered) {
                    ((Hitbox) infoViewField.nextLock.get(_inst)).clickStarted = true;
                    CardCrawlGame.sound.play("UI_CLICK_1");
                    return SpireReturn.Return(null);
                }
                if(((AbstractLockAnonCard)card.get(_inst)).locked  == false) {
                    if (infoViewField.nextInfo.get(_inst) != null &&
                            InputHelper.justClickedLeft &&
                            ((Hitbox) infoViewField.nextInfo.get(_inst)).hovered) {
                        ((Hitbox) infoViewField.nextInfo.get(_inst)).clickStarted = true;
                        CardCrawlGame.sound.play("UI_CLICK_1");
                        return SpireReturn.Return(null);
                    }
                }else {
                    if(AbstractDungeon.player!=null)
                    if (infoViewField.nextInfo.get(_inst) != null &&
                            InputHelper.justClickedLeft &&
                            ((Hitbox) infoViewField.nextInfo.get(_inst)).hovered
                    && AbstractDungeon.player.hasRelic(FirstHalfKey.ID) && AbstractDungeon.player.hasRelic(SecondHalfKey.ID)) {
                        ((AbstractLockAnonCard)card.get(_inst)).locked = false;
                        ((AbstractLockAnonCard) card.get(_inst)).unlockInfo();
                        AnonMod.saves.setBool("MachineHeartLocked",false);
                        CardCrawlGame.sound.play("UI_CLICK_1");
                        return SpireReturn.Return(null);
                    }
                }
                if (infoViewField.lastInfo.get(_inst) != null &&
                        InputHelper.justClickedLeft &&
                        ((Hitbox) infoViewField.lastInfo.get(_inst)).hovered) {
                    ((Hitbox) infoViewField.lastInfo.get(_inst)).clickStarted = true;
                    CardCrawlGame.sound.play("UI_CLICK_1");
                    return SpireReturn.Return(null);
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "render")
    public static class render {
        @SpirePostfixPatch
        public static void postfix(SingleCardViewPopup _inst, SpriteBatch sb) {
            if (infoViewField.lock.get(_inst) != null) {
                ((Hitbox) infoViewField.lock.get(_inst)).render(sb);
            }

            if (infoViewField.nextLock.get(_inst) != null) {
                ((Hitbox) infoViewField.nextLock.get(_inst)).render(sb);
            }

            if (infoViewField.nextInfo.get(_inst) != null) {
                ((Hitbox) infoViewField.nextInfo.get(_inst)).render(sb);
            }

            if (infoViewField.lastInfo.get(_inst) != null) {
                ((Hitbox) infoViewField.lastInfo.get(_inst)).render(sb);
            }
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderArrows")
    public static class renderArrows {
        @SpirePostfixPatch
        public static void postfix(SingleCardViewPopup _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
//            if (infoViewField.lock.get(_inst) != null) {
//
//                sb.draw(ImageMaster.COLOR_TAB_LOCK, ((Hitbox)infoViewField.lock.get(_inst)).cX - 40.0F, ((Hitbox)infoViewField.lock.get(_inst)).cY - 40.0F, 20.0F, 20.0F, 80.0F, 80.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 40, 40, false, false);
//                if (((Hitbox)infoViewField.lock.get(_inst)).hovered) {
//
//                    sb.setBlendFunction(770, 1);
//                    sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
//                    sb.draw(ImageMaster.COLOR_TAB_LOCK, ((Hitbox)infoViewField.lock.get(_inst)).cX - 40.0F, ((Hitbox)infoViewField.lock.get(_inst)).cY - 40.0F, 20.0F, 20.0F, 80.0F, 80.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 40, 40, false, false);
//
//                    sb.setColor(Color.WHITE);
//                    sb.setBlendFunction(770, 771);
//                }
//            }
//
//            if (infoViewField.nextLock.get(_inst) != null) {
//
//                sb.draw(ImageMaster.COLOR_TAB_LOCK, ((Hitbox)infoViewField.nextLock.get(_inst)).cX - 40.0F, ((Hitbox)infoViewField.nextLock.get(_inst)).cY - 40.0F, 40.0F, 40.0F, 80.0F, 80.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 40, 40, false, false);
//                if (((Hitbox)infoViewField.nextLock.get(_inst)).hovered) {
//
//                    sb.setBlendFunction(770, 1);
//                    sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
//                    sb.draw(ImageMaster.COLOR_TAB_LOCK, ((Hitbox)infoViewField.nextLock.get(_inst)).cX - 40.0F, ((Hitbox)infoViewField.nextLock.get(_inst)).cY - 40.0F, 40.0F, 40.0F, 80.0F, 80.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 40, 40, false, false);
//
//                    sb.setColor(Color.WHITE);
//                    sb.setBlendFunction(770, 771);
//                }
//            }
            Field card = _inst.getClass().getDeclaredField("card");
            card.setAccessible(true);

            if (card.get(_inst) instanceof AbstractLockAnonCard)
            if (((AbstractLockAnonCard)card.get(_inst)).locked == true) {
                if (infoViewField.nextInfo.get(_inst) != null) {
                    sb.draw(ImageMaster.COLOR_TAB_LOCK, ((Hitbox) infoViewField.nextInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.nextInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 40, 40, true, false);
                    if (((Hitbox) infoViewField.nextInfo.get(_inst)).hovered) {
//                        System.out.println("hoveredhoveredhoveredhoveredhovered");
                        sb.setBlendFunction(770, 1);
                        sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
                        sb.draw(ImageMaster.COLOR_TAB_LOCK, ((Hitbox) infoViewField.nextInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.nextInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 40, 40, true, false);

                        sb.setColor(Color.WHITE);
                        sb.setBlendFunction(770, 771);
                    }
                } else {
                    if (infoViewField.nextInfo.get(_inst) != null) {

                        sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.nextInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.nextInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);
                        if (((Hitbox) infoViewField.nextInfo.get(_inst)).hovered) {

                            sb.setBlendFunction(770, 1);
                            sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
                            sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.nextInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.nextInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);

                            sb.setColor(Color.WHITE);
                            sb.setBlendFunction(770, 771);
                        }
                    }
                }
                if (infoViewField.lastInfo.get(_inst) != null) {

                    sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.lastInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.lastInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
                    if (((Hitbox) infoViewField.lastInfo.get(_inst)).hovered) {

                        sb.setBlendFunction(770, 1);
                        sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
                        sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.lastInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.lastInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);

                        sb.setColor(Color.WHITE);
                        sb.setBlendFunction(770, 771);
                    }
                }
            }else {
                if (infoViewField.nextInfo.get(_inst) != null) {

                    sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.nextInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.nextInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);
                    if (((Hitbox) infoViewField.nextInfo.get(_inst)).hovered) {

                        sb.setBlendFunction(770, 1);
                        sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
                        sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.nextInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.nextInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);

                        sb.setColor(Color.WHITE);
                        sb.setBlendFunction(770, 771);
                    }
                }
                if (infoViewField.lastInfo.get(_inst) != null) {

                    sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.lastInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.lastInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
                    if (((Hitbox) infoViewField.lastInfo.get(_inst)).hovered) {

                        sb.setBlendFunction(770, 1);
                        sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
                        sb.draw(ImageMaster.POPUP_ARROW, ((Hitbox) infoViewField.lastInfo.get(_inst)).cX - 50.0F, ((Hitbox) infoViewField.lastInfo.get(_inst)).cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);

                        sb.setColor(Color.WHITE);
                        sb.setBlendFunction(770, 771);
                    }
                }
            }
        }

        @SpirePatch(clz = SingleCardViewPopup.class, method = "loadPortraitImg")
        public static class loadPortraitImg {
            @SpirePrefixPatch
            public static void prefix(SingleCardViewPopup _inst) throws NoSuchFieldException, IllegalAccessException {
                Field card = _inst.getClass().getDeclaredField("card");
                card.setAccessible(true);

                if (card.get(_inst) instanceof AbstractLockAnonCard)
                    ((AbstractLockAnonCard) card.get(_inst)).loadImg();
            }
        }
    }
}
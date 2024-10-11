package patch;
import Mod.AnonMod;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StoreRelic;

import java.io.IOException;
import java.util.ArrayList;

public class ShopScreenPatch {
    public ShopScreenPatch() {
    }

    @SpirePatch(
            clz = ShopScreen.class,
            method = "initRelics"
    )
    public static class PatchInitRelics {
        public PatchInitRelics() {
        }

        public static void Postfix(final ShopScreen screen) {
            if(AbstractDungeon.actNum <=3){
                FileCorruptionPatches.failed = false;
            }
            if(!AbstractDungeon.player.hasRelic(PrismaticShard.ID))
            if((AbstractDungeon.actNum == 2 && AnonMod.saves.getInt("shopCount") == 1 ||  AnonMod.saves.getInt("shopCount") == 2 && AnonMod.saves.getInt("shopLastFloor") == AbstractDungeon.floorNum )
                    || (AbstractDungeon.actNum == 1 && AnonMod.saves.getInt("shopCount") == 0  ||  AnonMod.saves.getInt("shopCount") == 1 && AnonMod.saves.getInt("shopLastFloor") == AbstractDungeon.floorNum)){
//                if (AnonMod.saves.getInt("shopCount") == 0 || AnonMod.saves.getInt("shopCount") == 1 && AnonMod.saves.getInt("shopLastFloor") == AbstractDungeon.floorNum) {
                {
                    ArrayList<StoreRelic> relics = (ArrayList)ReflectionHacks.getPrivate(screen, ShopScreen.class, "relics");
                    StoreRelic key = new StoreRelic(new PrismaticShard(), 3, screen) {
                        public void update(float rugY) {
                            if (this.relic != null) {
                                if (!this.isPurchased) {
                                    this.relic.currentX = 1000.0F * Settings.scale + -105.0F * Settings.scale;
                                    this.relic.currentY = rugY + 295.0F * Settings.scale;
                                    this.relic.hb.move(this.relic.currentX, this.relic.currentY);
                                    this.relic.hb.update();
                                    if (this.relic.hb.hovered) {
                                        screen.moveHand(this.relic.currentX - 190.0F * Settings.scale, this.relic.currentY - 70.0F * Settings.scale);
                                        if (InputHelper.justClickedLeft) {
                                            this.relic.hb.clickStarted = true;
                                        }

                                        this.relic.scale = Settings.scale * 1.25F;
                                    } else {
                                        this.relic.scale = MathHelper.scaleLerpSnap(this.relic.scale, Settings.scale);
                                    }

                                    if (this.relic.hb.hovered && InputHelper.justClickedRight) {
                                        CardCrawlGame.relicPopup.open(this.relic);
                                    }
                                }

                                if (this.relic.hb.clicked || this.relic.hb.hovered && CInputActionSet.select.isJustPressed()) {
                                    this.relic.hb.clicked = false;
                                    if (!Settings.isTouchScreen) {
                                        CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1F);
                                        CardCrawlGame.metricData.addShopPurchaseData(this.relic.relicId);
                                        AbstractDungeon.getCurrRoom().relics.add(this.relic);
                                        this.relic.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.size(), true);
                                        this.relic.flash();
                                        AbstractDungeon.cardRng = new Random(Settings.seed, 2580);
//                                    CardRarityRngFix.cardRarityRng = new Random(Settings.seed, 10000);
                                        AnonMod.saves.setInt("cardRarityRngCounter", 10000);
                                        screen.playBuySfx();
                                        screen.createSpeech(ShopScreen.getBuyMsg());
                                        AbstractDungeon.getCurrRoom().rewards.clear();

                                        for(int i = 0; i < 2; ++i) {
                                            AbstractDungeon.getCurrRoom().addCardToRewards();
                                        }

//                                        AbstractDungeon.combatRewardScreen.open(CardCrawlGame.languagePack.getUIString("PrismaticShardBonus").TEXT[0]);
                                        this.isPurchased = true;
                                    } else if (AbstractDungeon.shopScreen.touchRelic == null) {
                                        if (AbstractDungeon.player.gold < this.price) {
                                            screen.playCantBuySfx();
                                            screen.createSpeech(ShopScreen.getCantBuyMsg());
                                        } else {
                                            AbstractDungeon.shopScreen.confirmButton.hideInstantly();
                                            AbstractDungeon.shopScreen.confirmButton.show();
                                            AbstractDungeon.shopScreen.confirmButton.isDisabled = false;
                                            AbstractDungeon.shopScreen.confirmButton.hb.clickStarted = false;
                                            AbstractDungeon.shopScreen.touchRelic = this;
                                        }
                                    }
                                }
                            }

                        }
                    };
                    key.price = 0;
                    relics.add(key);
                }

                AnonMod.saves.setInt("shopLastFloor", AbstractDungeon.floorNum);
            }


            try {
                AnonMod.saves.save();
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }
}

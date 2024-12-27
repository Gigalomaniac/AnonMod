package patch.boss;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcessiveFantasyCampfireSleepEffect extends AbstractGameEffect {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static final float HEAL_AMOUNT = 0.3F;
    private static final float DUR = 3.0F;
    private static final float FAST_MODE_DUR = 1.5F;
    private boolean hasHealed = false;
    private int healAmount;
    private Color screenColor;

    public ExcessiveFantasyCampfireSleepEffect() {
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        if (Settings.FAST_MODE) {
            this.startingDuration = 1.5F;
        } else {
            this.startingDuration = 3.0F;
        }
        System.out.println("?????????????????????");
        this.duration = this.startingDuration;
        this.screenColor.a = 0.0F;
        ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
        AbstractDungeon.overlayMenu.proceedButton.hide();
        if (ModHelper.isModEnabled("Night Terrors")) {
            this.healAmount = (int)((float)AbstractDungeon.player.maxHealth * 1.0F);
            AbstractDungeon.player.decreaseMaxHealth(5);
        } else {
            this.healAmount = (int)((float)AbstractDungeon.player.maxHealth * 0.3F);
        }

        if (AbstractDungeon.player.hasRelic("Regal Pillow")) {
            this.healAmount += 15;
        }

    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.updateBlackScreenColor();
        if (this.duration < this.startingDuration - 0.5F && !this.hasHealed) {
            this.playSleepJingle();
            this.hasHealed = true;
            if (AbstractDungeon.player.hasRelic("Regal Pillow")) {
                AbstractDungeon.player.getRelic("Regal Pillow").flash();
            }

            AbstractDungeon.player.heal(this.healAmount, false);
            Iterator var1 = AbstractDungeon.player.relics.iterator();

            while(var1.hasNext()) {
                AbstractRelic r = (AbstractRelic)var1.next();
                r.onRest();
            }
        }

        if (this.duration < this.startingDuration / 2.0F) {
            if (AbstractDungeon.player.hasRelic("Dream Catcher")) {
                AbstractDungeon.player.getRelic("Dream Catcher").flash();
                ArrayList<AbstractCard> rewardCards = AbstractDungeon.getRewardCards();
                if (rewardCards != null && !rewardCards.isEmpty()) {
                    AbstractDungeon.cardRewardScreen.open(rewardCards, (RewardItem)null, TEXT[0]);
                }
            }

            this.isDone = true;
            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
            AbstractRoom.waitTimer = 0.0F;
            AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
        }

    }

    private void playSleepJingle() {
        int roll = MathUtils.random(0, 2);
        switch (AbstractDungeon.id) {
            case "Exordium":
                if (roll == 0) {
                    CardCrawlGame.sound.play("SLEEP_1-1");
                } else if (roll == 1) {
                    CardCrawlGame.sound.play("SLEEP_1-2");
                } else {
                    CardCrawlGame.sound.play("SLEEP_1-3");
                }
                break;
            case "TheCity":
                if (roll == 0) {
                    CardCrawlGame.sound.play("SLEEP_2-1");
                } else if (roll == 1) {
                    CardCrawlGame.sound.play("SLEEP_2-2");
                } else {
                    CardCrawlGame.sound.play("SLEEP_2-3");
                }
                break;
            case "TheBeyond":
                if (roll == 0) {
                    CardCrawlGame.sound.play("SLEEP_3-1");
                } else if (roll == 1) {
                    CardCrawlGame.sound.play("SLEEP_3-2");
                } else {
                    CardCrawlGame.sound.play("SLEEP_3-3");
                }
        }

    }

    private void updateBlackScreenColor() {
        if (this.duration > this.startingDuration - 0.5F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - (this.startingDuration - 0.5F)) * 2.0F);
        } else if (this.duration < 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
        } else {
            this.screenColor.a = 1.0F;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }

    public void dispose() {
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("CampfireSleepEffect");
        TEXT = uiStrings.TEXT;
    }
}

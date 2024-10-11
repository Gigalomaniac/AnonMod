package potions;

import actions.liveboostAction;
import basemod.abstracts.CustomPotion;
import cards.token.Idea;
import cards.token.Minute;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class IdeaPotion extends CustomPotion {
    public static final String ID = "IdeaPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final PotionRarity rarity = PotionRarity.COMMON;
    private static final PotionSize size = PotionSize.M;
    private static final PotionColor color = PotionColor.BLUE;
    public static Color liquidColor = new Color(1.0F, 0.003921569F, 0.07450981F, 1.0F);
    public static Color hybridColor = new Color(0.99215686F, 0.007843138F, 0.08627451F, 1.0F);
    public static Color spotsColor = new Color(0.99607843F, 0.003921569F, 0.07450981F, 1.0F);
    private static final String imgUrl = "img/potions/IdeaPotion.png";
    private static final Texture img = ImageMaster.loadImage(imgUrl);
    private AbstractCard theCard = null;
    public IdeaPotion() {
        super(potionStrings.NAME, ID, rarity, size, color);
        this.isThrown = false;
        this.targetRequired = false;
        PowerTip tip = new PowerTip();
        tip.header="@STSLIB:FLAVOR@";
        tip.body="小小的爱音，大大的点子。";
        FlavorText.PowerTipFlavorFields.boxColor.set(tip, CardHelper.getColor(255, 136, 153));
        this.tips.add(tip);
    }


    public void use(AbstractCreature abstractCreature) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
                this.addToTop(new MakeTempCardInHandAction(new Idea(), 2));
            } else {
                this.addToTop(new MakeTempCardInHandAction(new Idea(), 1));
            }
        }
    }




    public int getPotency(int i) { return 2; }

    public void CardUpgrade(int i) {

    }


    public AbstractPotion makeCopy() { return new IdeaPotion(); }

    public void render(SpriteBatch sb) {
        sb.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        sb.draw(img, this.posX - 32.0F, this.posY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
    }

    public void initializeData() {
        this.potency = getPotency();
        this.tips.clear();

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[1];
            this.tips.add(new PowerTip(this.name, this.description));
        } else {

            this.description = potionStrings.DESCRIPTIONS[0];
            this.tips.add(new PowerTip(this.name, this.description));
        }

//        this.tips.add(new PowerTip(potionStrings.DESCRIPTIONS[2], (String)GameDictionary.keywords.get("batwinsmod:" + potionStrings.DESCRIPTIONS[2])));
//        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void shopRender(SpriteBatch sb) {
        generateSparkles(0.0F, 0.0F, false);
        if (this.hb.hovered) {
            TipHelper.queuePowerTips(InputHelper.mX + 50.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, this.tips);
            this.scale = 1.5F * Settings.scale;
        } else {
            this.scale = MathHelper.scaleLerpSnap(this.scale, 1.2F * Settings.scale);
        }
        render(sb);
        if (this.hb != null)
            this.hb.render(sb);
    }
    public void labRender(SpriteBatch sb) {
        render(sb);
        if (this.hb.hovered) {
            TipHelper.queuePowerTips(150.0F * Settings.scale, 800.0F * Settings.scale, this.tips);
            this.scale = 1.5F * Settings.scale;
        } else {
            this.scale = MathHelper.scaleLerpSnap(this.scale, 1.2F * Settings.scale);
        }
        if (this.hb != null) {
            this.hb.render(sb);
        }
    }


    public void renderOutline(SpriteBatch sb, Color c) {}




    public void renderOutline(SpriteBatch sb) {}




    public void renderLightOutline(SpriteBatch sb) {}




    public void renderShiny(SpriteBatch sb) {}
}

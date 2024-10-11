package potions;

import actions.liveboostAction;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class liveboost extends CustomPotion {
    public static final String ID = "liveboost";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final AbstractPotion.PotionRarity rarity = PotionRarity.UNCOMMON;
    private static final AbstractPotion.PotionSize size = AbstractPotion.PotionSize.M;
    private static final AbstractPotion.PotionColor color = AbstractPotion.PotionColor.BLUE;
    public static Color liquidColor = new Color(1.0F, 0.003921569F, 0.07450981F, 1.0F);
    public static Color hybridColor = new Color(0.99215686F, 0.007843138F, 0.08627451F, 1.0F);
    public static Color spotsColor = new Color(0.99607843F, 0.003921569F, 0.07450981F, 1.0F);
    private static final String imgUrl = "img/potions/liveboost.png";
    private static final Texture img = ImageMaster.loadImage(imgUrl);
    private AbstractCard theCard = null;
    public liveboost() {
        super(potionStrings.NAME, ID, rarity, size, color);
        this.isThrown = false;
        this.targetRequired = false;
        PowerTip tip = new PowerTip();
        tip.header="@STSLIB:FLAVOR@";
        tip.body="记得清火！！！";
        FlavorText.PowerTipFlavorFields.boxColor.set(tip, CardHelper.getColor(255, 136, 153));
        this.tips.add(tip);
    }


    public void use(AbstractCreature abstractCreature) {

            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
                if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                    this.addToBot(new liveboostAction());
                    this.addToBot(new liveboostAction());
                }else {
                    upgradeCards(2);
                }
            } else {
                if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                    this.addToBot(new liveboostAction());
                }else {
                    upgradeCards(1);
                }
            }

    }




    public int getPotency(int i) { return 5; }

    public void CardUpgrade(int i) {

    }
    public boolean canUse() {
        if (AbstractDungeon.actionManager.turnHasEnded && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            return false;
        } else {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
        }
    }

    public AbstractPotion makeCopy() { return new liveboost(); }

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

    private void upgradeCards(int num) {
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        ArrayList<AbstractCard> upgradableCards = new ArrayList();
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }

        List<String> cardMetrics = new ArrayList();
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            if (upgradableCards.size() == 1) {
                ((AbstractCard)upgradableCards.get(0)).upgrade();
                cardMetrics.add(((AbstractCard)upgradableCards.get(0)).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard)upgradableCards.get(0));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards.get(0)).makeStatEquivalentCopy()));
            } else {
                if (num == 1) {
                    ((AbstractCard) upgradableCards.get(0)).upgrade();
                    cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                    AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(0));
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                } else {
                    ((AbstractCard) upgradableCards.get(0)).upgrade();
                    ((AbstractCard) upgradableCards.get(1)).upgrade();
                    cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                    cardMetrics.add(((AbstractCard) upgradableCards.get(1)).cardID);
                    AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(0));
                    AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(1));
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(1)).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                }
            }
        }
    }
}

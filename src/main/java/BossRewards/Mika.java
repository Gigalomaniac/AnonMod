package BossRewards;

import actions.ThinkingAction;
import basemod.abstracts.CustomCard;
import cards.FullImgCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pathes.AbstractCardEnum;
import power.songs;

/**
 * 每一个瞬间的积累
 */
public class Mika extends FullImgCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Mika");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = -2;
    public static final String ID = "Mika";
    public static final String IMG_PATH = "img/transparent.png";

    public Mika() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        this.selfRetain = true;
        this.setBackgroundTexture("img/pink/1024/bg_skill_mika_512.png","img/pink/1024/bg_skill_mika_1024.png");
        this.setPortraitTextures("img/transparent.png","img/transparent.png");
        this.setBannerTexture("img/transparent.png","img/transparent.png");
        this.cardsToPreview = (AbstractCard)new MikaFake();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "熙熙攘攘、我们的城市"), this.magicNumber));

    }
//    public void triggerWhenDrawn() {
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -5), -5));
//    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if(c.type.equals(CardType.ATTACK) && !AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        }else
        if(c.type.equals(CardType.ATTACK) && AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) && AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount < 10)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Mika();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    public boolean canUpgrade() {
        return false;
    }
    @Override
    public float getTitleFontSize() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            return 16F;
        }
        return -1f;
    }
    private  float timer = 0f;
    private  int currentIndex = 0;
    private  float FrameDuring = 0.1f;

    @Override
    public void update() {
        super.update();
        timer += Gdx.graphics.getDeltaTime();
        if(timer >= FrameDuring){
            timer -= FrameDuring;
            currentIndex = (currentIndex+1)% StringIMG_PATHS.length;
            this.setBackgroundTexture(StringIMG_PATHS[currentIndex],"img/pink/1024/bg_skill_mika_1024.png");
//            this.loadCardImage(textureImg);
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.cantUseMessage = "我不能使用";
        }else {
            this.cantUseMessage = "I can't use";
        }
        return false;
    }
//    private Texture[] IMG_PATHS = {
//            new Texture("img/pink/1024/mika512/图层 1.png"),
//            new Texture("img/pink/1024/mika512/图层 2.png"),
//            new Texture("img/pink/1024/mika512/图层 3.png"),
//            new Texture("img/pink/1024/mika512/图层 4.png"),
//            new Texture("img/pink/1024/mika512/图层 5.png"),
//            new Texture("img/pink/1024/mika512/图层 6.png"),
//            new Texture("img/pink/1024/mika512/图层 7.png"),
//            new Texture("img/pink/1024/mika512/图层 8.png"),
//            new Texture("img/pink/1024/mika512/图层 9.png"),
//            new Texture("img/pink/1024/mika512/图层 10.png"),
//            new Texture("img/pink/1024/mika512/图层 11.png"),
//            new Texture("img/pink/1024/mika512/图层 12.png"),
//            new Texture("img/pink/1024/mika512/图层 13.png"),
//            new Texture("img/pink/1024/mika512/图层 14.png"),
//            new Texture("img/pink/1024/mika512/图层 15.png"),
//            new Texture("img/pink/1024/mika512/图层 16.png"),
//            new Texture("img/pink/1024/mika512/图层 17.png"),
//            new Texture("img/pink/1024/mika512/图层 18.png"),
//            new Texture("img/pink/1024/mika512/图层 19.png"),
//            new Texture("img/pink/1024/mika512/图层 20.png"),
//            new Texture("img/pink/1024/mika512/图层 21.png"),
//            new Texture("img/pink/1024/mika512/图层 22.png"),
//            new Texture("img/pink/1024/mika512/图层 23.png"),
//            new Texture("img/pink/1024/mika512/图层 24.png"),
//            new Texture("img/pink/1024/mika512/图层 25.png"),
//            new Texture("img/pink/1024/mika512/图层 26.png"),
//            new Texture("img/pink/1024/mika512/图层 27.png"),
//            new Texture("img/pink/1024/mika512/图层 28.png"),
//            new Texture("img/pink/1024/mika512/图层 29.png"),
//            new Texture("img/pink/1024/mika512/图层 30.png"),
//            new Texture("img/pink/1024/mika512/图层 31.png"),
//            new Texture("img/pink/1024/mika512/图层 32.png"),
//            new Texture("img/pink/1024/mika512/图层 33.png"),
//            new Texture("img/pink/1024/mika512/图层 34.png"),
//            new Texture("img/pink/1024/mika512/图层 35.png"),
//            new Texture("img/pink/1024/mika512/图层 36.png"),
//            new Texture("img/pink/1024/mika512/图层 37.png"),
//            new Texture("img/pink/1024/mika512/图层 38.png"),
//            new Texture("img/pink/1024/mika512/图层 39.png"),
//            new Texture("img/pink/1024/mika512/图层 40.png"),
//            new Texture("img/pink/1024/mika512/图层 41.png"),
//            new Texture("img/pink/1024/mika512/图层 42.png"),
//            new Texture("img/pink/1024/mika512/图层 43.png"),
//            new Texture("img/pink/1024/mika512/图层 44.png"),
//            new Texture("img/pink/1024/mika512/图层 45.png"),
//            new Texture("img/pink/1024/mika512/图层 46.png"),
//            new Texture("img/pink/1024/mika512/图层 47.png"),
//            new Texture("img/pink/1024/mika512/图层 48.png"),
//            new Texture("img/pink/1024/mika512/图层 49.png"),
//            new Texture("img/pink/1024/mika512/图层 50.png"),
//            new Texture("img/pink/1024/mika512/图层 51.png"),
//            new Texture("img/pink/1024/mika512/图层 52.png"),
//            new Texture("img/pink/1024/mika512/图层 53.png"),
//            new Texture("img/pink/1024/mika512/图层 54.png"),
//            new Texture("img/pink/1024/mika512/图层 55.png"),
//            new Texture("img/pink/1024/mika512/图层 56.png"),
//            new Texture("img/pink/1024/mika512/图层 57.png"),
//            new Texture("img/pink/1024/mika512/图层 58.png"),
//            new Texture("img/pink/1024/mika512/图层 59.png"),
//            new Texture("img/pink/1024/mika512/图层 60.png"),
//            new Texture("img/pink/1024/mika512/图层 61.png"),
//            new Texture("img/pink/1024/mika512/图层 62.png"),
//            new Texture("img/pink/1024/mika512/图层 63.png"),
//            new Texture("img/pink/1024/mika512/图层 64.png"),
//            new Texture("img/pink/1024/mika512/图层 65.png"),
//            new Texture("img/pink/1024/mika512/图层 66.png"),
//            new Texture("img/pink/1024/mika512/图层 67.png"),
//            new Texture("img/pink/1024/mika512/图层 68.png"),
//            new Texture("img/pink/1024/mika512/图层 69.png"),
//            new Texture("img/pink/1024/mika512/图层 70.png"),
//            new Texture("img/pink/1024/mika512/图层 71.png"),
//            new Texture("img/pink/1024/mika512/图层 72.png"),
//            new Texture("img/pink/1024/mika512/图层 73.png"),
//            new Texture("img/pink/1024/mika512/图层 74.png"),
//            new Texture("img/pink/1024/mika512/图层 75.png"),
//            new Texture("img/pink/1024/mika512/图层 76.png"),
//            new Texture("img/pink/1024/mika512/图层 77.png"),
//            new Texture("img/pink/1024/mika512/图层 78.png"),
//            new Texture("img/pink/1024/mika512/图层 79.png"),
//            new Texture("img/pink/1024/mika512/图层 80.png"),
//            new Texture("img/pink/1024/mika512/图层 81.png"),
//            new Texture("img/pink/1024/mika512/图层 82.png"),
//            new Texture("img/pink/1024/mika512/图层 83.png"),
//            new Texture("img/pink/1024/mika512/图层 84.png"),
//            new Texture("img/pink/1024/mika512/图层 85.png"),
//            new Texture("img/pink/1024/mika512/图层 86.png"),
//            new Texture("img/pink/1024/mika512/图层 87.png"),
//            new Texture("img/pink/1024/mika512/图层 88.png"),
//            new Texture("img/pink/1024/mika512/图层 89.png"),
//            new Texture("img/pink/1024/mika512/图层 90.png"),
//            new Texture("img/pink/1024/mika512/图层 91.png"),
//            new Texture("img/pink/1024/mika512/图层 92.png"),
//            new Texture("img/pink/1024/mika512/图层 93.png"),
//            new Texture("img/pink/1024/mika512/图层 94.png"),
//            new Texture("img/pink/1024/mika512/图层 95.png"),
//            new Texture("img/pink/1024/mika512/图层 96.png"),
//            new Texture("img/pink/1024/mika512/图层 97.png"),
//            new Texture("img/pink/1024/mika512/图层 98.png"),
//            new Texture("img/pink/1024/mika512/图层 99.png"),
//            new Texture("img/pink/1024/mika512/图层 100.png"),
//            new Texture("img/pink/1024/mika512/图层 101.png"),
//            new Texture("img/pink/1024/mika512/图层 102.png"),
//            new Texture("img/pink/1024/mika512/图层 103.png"),
//            new Texture("img/pink/1024/mika512/图层 104.png"),
//            new Texture("img/pink/1024/mika512/图层 105.png"),
//            new Texture("img/pink/1024/mika512/图层 106.png"),
//            new Texture("img/pink/1024/mika512/图层 107.png"),
//            new Texture("img/pink/1024/mika512/图层 108.png"),
//            new Texture("img/pink/1024/mika512/图层 109.png"),
//            new Texture("img/pink/1024/mika512/图层 110.png")
//    };
    private String[] StringIMG_PATHS = {
            "img/pink/1024/mika512/图层 1.png",
            "img/pink/1024/mika512/图层 2.png",
            "img/pink/1024/mika512/图层 3.png",
            "img/pink/1024/mika512/图层 4.png",
            "img/pink/1024/mika512/图层 5.png",
            "img/pink/1024/mika512/图层 6.png",
            "img/pink/1024/mika512/图层 7.png",
            "img/pink/1024/mika512/图层 8.png",
            "img/pink/1024/mika512/图层 9.png",
            "img/pink/1024/mika512/图层 10.png",
            "img/pink/1024/mika512/图层 11.png",
            "img/pink/1024/mika512/图层 12.png",
            "img/pink/1024/mika512/图层 13.png",
            "img/pink/1024/mika512/图层 14.png",
            "img/pink/1024/mika512/图层 15.png",
            "img/pink/1024/mika512/图层 16.png",
            "img/pink/1024/mika512/图层 17.png",
            "img/pink/1024/mika512/图层 18.png",
            "img/pink/1024/mika512/图层 19.png",
            "img/pink/1024/mika512/图层 20.png",
            "img/pink/1024/mika512/图层 21.png",
            "img/pink/1024/mika512/图层 22.png",
            "img/pink/1024/mika512/图层 23.png",
            "img/pink/1024/mika512/图层 24.png",
            "img/pink/1024/mika512/图层 25.png",
            "img/pink/1024/mika512/图层 26.png",
            "img/pink/1024/mika512/图层 27.png",
            "img/pink/1024/mika512/图层 28.png",
            "img/pink/1024/mika512/图层 29.png",
            "img/pink/1024/mika512/图层 30.png",
            "img/pink/1024/mika512/图层 31.png",
            "img/pink/1024/mika512/图层 32.png",
            "img/pink/1024/mika512/图层 33.png",
            "img/pink/1024/mika512/图层 34.png",
            "img/pink/1024/mika512/图层 35.png",
            "img/pink/1024/mika512/图层 36.png",
            "img/pink/1024/mika512/图层 37.png",
            "img/pink/1024/mika512/图层 38.png",
            "img/pink/1024/mika512/图层 39.png",
            "img/pink/1024/mika512/图层 40.png",
            "img/pink/1024/mika512/图层 41.png",
            "img/pink/1024/mika512/图层 42.png",
            "img/pink/1024/mika512/图层 43.png",
            "img/pink/1024/mika512/图层 44.png",
            "img/pink/1024/mika512/图层 45.png",
            "img/pink/1024/mika512/图层 46.png",
            "img/pink/1024/mika512/图层 47.png",
            "img/pink/1024/mika512/图层 48.png",
            "img/pink/1024/mika512/图层 49.png",
            "img/pink/1024/mika512/图层 50.png",
            "img/pink/1024/mika512/图层 51.png",
            "img/pink/1024/mika512/图层 52.png",
            "img/pink/1024/mika512/图层 53.png",
            "img/pink/1024/mika512/图层 54.png",
            "img/pink/1024/mika512/图层 55.png",
            "img/pink/1024/mika512/图层 56.png",
            "img/pink/1024/mika512/图层 57.png",
            "img/pink/1024/mika512/图层 58.png",
            "img/pink/1024/mika512/图层 59.png",
            "img/pink/1024/mika512/图层 60.png",
            "img/pink/1024/mika512/图层 61.png",
            "img/pink/1024/mika512/图层 62.png",
            "img/pink/1024/mika512/图层 63.png",
            "img/pink/1024/mika512/图层 64.png",
            "img/pink/1024/mika512/图层 65.png",
            "img/pink/1024/mika512/图层 66.png",
            "img/pink/1024/mika512/图层 67.png",
            "img/pink/1024/mika512/图层 68.png",
            "img/pink/1024/mika512/图层 69.png",
            "img/pink/1024/mika512/图层 70.png",
            "img/pink/1024/mika512/图层 71.png",
            "img/pink/1024/mika512/图层 72.png",
            "img/pink/1024/mika512/图层 73.png",
            "img/pink/1024/mika512/图层 74.png",
            "img/pink/1024/mika512/图层 75.png",
            "img/pink/1024/mika512/图层 76.png",
            "img/pink/1024/mika512/图层 77.png",
            "img/pink/1024/mika512/图层 78.png",
            "img/pink/1024/mika512/图层 79.png",
            "img/pink/1024/mika512/图层 80.png",
            "img/pink/1024/mika512/图层 81.png",
            "img/pink/1024/mika512/图层 82.png",
            "img/pink/1024/mika512/图层 83.png",
            "img/pink/1024/mika512/图层 84.png",
            "img/pink/1024/mika512/图层 85.png",
            "img/pink/1024/mika512/图层 86.png",
            "img/pink/1024/mika512/图层 87.png",
            "img/pink/1024/mika512/图层 88.png",
            "img/pink/1024/mika512/图层 89.png",
            "img/pink/1024/mika512/图层 90.png",
            "img/pink/1024/mika512/图层 91.png",
            "img/pink/1024/mika512/图层 92.png",
            "img/pink/1024/mika512/图层 93.png",
            "img/pink/1024/mika512/图层 94.png",
            "img/pink/1024/mika512/图层 95.png",
            "img/pink/1024/mika512/图层 96.png",
            "img/pink/1024/mika512/图层 97.png",
            "img/pink/1024/mika512/图层 98.png",
            "img/pink/1024/mika512/图层 99.png",
            "img/pink/1024/mika512/图层 100.png",
            "img/pink/1024/mika512/图层 101.png",
            "img/pink/1024/mika512/图层 102.png",
            "img/pink/1024/mika512/图层 103.png",
            "img/pink/1024/mika512/图层 104.png",
            "img/pink/1024/mika512/图层 105.png",
            "img/pink/1024/mika512/图层 106.png",
            "img/pink/1024/mika512/图层 107.png",
            "img/pink/1024/mika512/图层 108.png",
            "img/pink/1024/mika512/图层 109.png",
            "img/pink/1024/mika512/图层 110.png"
    };
}

package BossRewards;

import basemod.abstracts.CustomCard;
import cards.FullImgCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.songs;

/**
 * 每一个瞬间的积累
 */
public class KarenRewardCard extends FullImgCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KarenRewardCard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = -2;
    public static final String ID = "KarenRewardCard";
    public static final String IMG_PATH = "img/transparent.png";

    public KarenRewardCard() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        this.setBackgroundTexture("img/pink/1024/bg_skill_karen_512.png","img/pink/1024/bg_skill_Karen.png");
        this.setPortraitTextures("img/transparent.png","img/transparent.png");
        this.setBannerTexture("img/transparent.png","img/transparent.png");
        this.setOrbTexture("img/transparent.png","img/transparent.png");
        this.cardsToPreview = (AbstractCard)new KarenRewardCardFake();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.cantUseMessage = "我不能使用";
        }else {
            this.cantUseMessage = "I can't use";
        }
        return false;
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new KarenRewardCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
//            upgradeName();
//            this.upgradeBaseCost(0);
        }
    }
    public boolean canUpgrade() {
        return false;
    }

}

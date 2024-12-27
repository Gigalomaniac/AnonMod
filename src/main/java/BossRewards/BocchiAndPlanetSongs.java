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
public class BocchiAndPlanetSongs extends FullImgCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BocchiAndPlanetSongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 0;
    public static final String ID = "BocchiAndPlanetSongs";
    public static final String IMG_PATH = "img/transparent.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public BocchiAndPlanetSongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.setBackgroundTexture("img/pink/1024/bg_skill_sp_bocchi_512.png","img/pink/1024/bg_skill_sp_bocchi.png");
        this.setPortraitTextures("img/transparent.png","img/transparent.png");
        this.setBannerTexture("img/transparent.png","img/transparent.png");
        this.cardsToPreview = (AbstractCard)new BocchiAndPlanetSongsFake();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "吉他与孤独与蓝色星球"), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BocchiAndPlanetSongs();
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
    @Override
    public float getTitleFontSize() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            return 16F;
        }
        return -1f;
    }
}

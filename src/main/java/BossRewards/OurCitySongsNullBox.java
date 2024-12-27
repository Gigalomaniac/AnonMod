package BossRewards;

import basemod.abstracts.CustomCard;
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
public class OurCitySongsNullBox extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("OurCitySongsNullBox");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 0;
    public static final String ID = "OurCitySongsNullBox";
    public static final String IMG_PATH = "img/card_Anon/BossRewards/NullBox_skill.png";

    public OurCitySongsNullBox() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
//        this.setPortraitTextures("img/transparent.png","img/transparent.png");
//        this.setBannerTexture("img/transparent.png","img/transparent.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "熙熙攘攘、我们的城市"), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new OurCitySongsNullBox();
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
//        BitmapFont titleFont = this.getTitleFont();
//        if (titleFont == null) {
//            SpireSuper.call(new Object[]{sb});
//        } else {
//            BitmapFont savedFont = FontHelper.cardTitleFont;
//            FontHelper.cardTitleFont = titleFont;
//            Boolean useSmallTitleFont = (Boolean) ReflectionHacks.getPrivate(this, AbstractCard.class, "useSmallTitleFont");
//            ReflectionHacks.setPrivate(this, AbstractCard.class, "useSmallTitleFont", false);
//            SpireSuper.call(new Object[]{sb});
//            ReflectionHacks.setPrivate(this, AbstractCard.class, "useSmallTitleFont", useSmallTitleFont);
//            FontHelper.cardTitleFont = savedFont;
//        }
//    }

}

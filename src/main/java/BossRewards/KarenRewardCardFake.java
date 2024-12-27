package BossRewards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.songs;

/**
 * 每一个瞬间的积累
 */
public class KarenRewardCardFake extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KarenRewardCard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = -2;
    public static final String ID = "KarenRewardCard";
    public static final String IMG_PATH = "img/pink/1024/KarenRewardCard_skill.png";

    public KarenRewardCardFake() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
//        this.cardsToPreview = (AbstractCard)new OurCitySongsFake();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "熙熙攘攘、我们的城市"), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new KarenRewardCardFake();
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

}

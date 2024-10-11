package cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import power.Shining;
import power.heavy;
import pathes.AbstractCardEnum;

/**
 * 重力场
 */
public class Gravity extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Gravity");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "Gravity";
    public static final String IMG_PATH = "img/card_Anon/zhonglichang_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Gravity() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        //初始为3层活力
        this.baseDamage = 5 +heavy.count;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Gravity();
    }
    public void applyPowers() {
        if (this.upgraded) {
            this.baseDamage = 6 +heavy.count * 2;
        }else {
            this.baseDamage = 5 +heavy.count;
        }

    }
    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.baseDamage = 6 +heavy.count * 2;
            this.initializeDescription();
        }
    }


}

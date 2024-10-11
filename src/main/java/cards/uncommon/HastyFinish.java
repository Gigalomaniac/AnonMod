package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.beat;
import pathes.AbstractCardEnum;

import java.util.Objects;

import static power.beat.AllAmount;


/**
 * 鲁莽结束
 */
public class HastyFinish extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("HastyFinish");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "HastyFinish";
    public static final String IMG_PATH = "img/card_Anon/cangcushouwei_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public HastyFinish() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        //初始为3层活力
        this.baseDamage = 5;
        this.baseMagicNumber = 5;;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0 ; i < AllAmount ; i ++){
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new HastyFinish();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.baseDamage = 8;
            this.baseMagicNumber = 8;;
            this.magicNumber = this.baseMagicNumber;
        }
    }


}

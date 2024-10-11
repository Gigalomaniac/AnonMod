package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import pathes.AbstractCardEnum;
import power.EnemyHeavy;
import power.Shining;
import power.heavy;
import power.songs;

/**
 * 结束乐队
 */
public class Accomplice extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Accomplice");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "Accomplice";
    public static final String IMG_PATH = "img/card_Anon/gongfan_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Accomplice() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        //初始为3层活力
        this.baseMagicNumber = heavy.count + 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        if(upgraded){
//            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new heavy((AbstractCreature)p, 4), 4));
//            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new EnemyHeavy((AbstractCreature) m, 4), 4, true, AbstractGameAction.AttackEffect.NONE));
//        }else {
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new heavy((AbstractCreature)p, 3), 3));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new EnemyHeavy((AbstractCreature) m, 3), 3, true, AbstractGameAction.AttackEffect.NONE));
//        }
        this.addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Accomplice();
    }
    public void applyPowers() {
        this.baseMagicNumber = 0;
        if(AbstractDungeon.player.hasPower(heavy.POWER_ID)){
        this.baseMagicNumber = AbstractDungeon.player.getPower(heavy.POWER_ID).amount+3;
    }
        this.magicNumber = this.baseMagicNumber;

    }
    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}

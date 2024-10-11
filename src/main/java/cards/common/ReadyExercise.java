package cards.common;

import basemod.abstracts.CustomCard;
import cards.token.Minute;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import power.musicStart;

/**
 * 小练习
 */
public class ReadyExercise extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ReadyExercise");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "ReadyExercise";
    public static final String IMG_PATH = "img/card_Anon/ready_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public ReadyExercise() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        //初始为3层活力
        this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(musicStart.ifStart != 2){
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new ReadyExercise();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }


}

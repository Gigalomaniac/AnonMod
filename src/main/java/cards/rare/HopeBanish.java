package cards.rare;

import actions.HopeBanishAction;
import actions.RanaAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.Shining;
import power.variation;
import relics.AbstractDungeonBand;

/**
 *  释放
 */
public class HopeBanish extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("HopeBanish");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "HopeBanish";
    public static final String IMG_PATH = "img/card_Anon/Cover_02_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public HopeBanish() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        //初始为3层活力
        this.baseMagicNumber = Shining.allCount;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot((AbstractGameAction) new HopeBanishAction(this.upgraded, p, m, this.magicNumber * 2, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn)));
        }else {
            addToBot((AbstractGameAction) new HopeBanishAction(this.upgraded, p, m, this.magicNumber , new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn)));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new HopeBanish();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
//            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void applyPowers() {
        this.baseMagicNumber = 0;
        if (AbstractDungeon.player.hasPower(Shining.POWER_ID)){
            this.baseMagicNumber = AbstractDungeon.player.getPower(Shining.POWER_ID).amount;
        }
        this.magicNumber = this.baseMagicNumber;
        if (upgraded){
            this.baseDamage = this.magicNumber*3;
        }else {
            this.baseDamage = this.magicNumber*2;
        }

    }
}

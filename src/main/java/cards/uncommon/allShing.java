package cards.uncommon;

import Mod.AnonCardSignStrings;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import power.Shining;

/**
 * 练习或休息
 */
public class allShing extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("allShing");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "allShing";
    public static final String IMG_PATH = "img/card_Anon/guangmang_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public allShing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        //初始为3层活力

        this.baseDamage = 10;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(255, 136, 153));
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded) {
            this.addToBot(new DamageAllEnemiesAction(p, baseDamage + this.magicNumber * 8, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }else {
            this.addToBot(new DamageAllEnemiesAction(p, baseDamage + this.magicNumber * 5, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

    }
    public void applyPowers() {
        if(AbstractDungeon.player.hasPower(Shining.POWER_ID)){
            this.baseMagicNumber = AbstractDungeon.player.getPower(Shining.POWER_ID).amount;
        }else {
            this.baseMagicNumber=0;
        }
        this.magicNumber = this.baseMagicNumber;
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new allShing();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(4);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}

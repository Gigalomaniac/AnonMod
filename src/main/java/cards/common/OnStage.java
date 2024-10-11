package cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import power.musicStart;
import pathes.AbstractCardEnum;

/**
 * 小练习
 */
public class OnStage extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("OnStage");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "OnStage";
    public static final String IMG_PATH = "img/card_Anon/zhunbeishangtai_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public OnStage() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        //初始为3层活力
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new OnStage();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.cantUseMessage = "演出已经开始哦！";
        }else {
            this.cantUseMessage = "The performance has started yet.";
        }
        if(musicStart.ifStart != 2)
            return true;
        else
            return false;
    }
}

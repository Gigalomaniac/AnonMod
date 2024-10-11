package cards.common;

import actions.NeverSleepLateAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import pathes.AbstractCardEnum;
import power.heavy;

import java.util.Iterator;

/**
 * 小练习
 */
public class NeverSleepLate extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("NeverSleepLate");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "NeverSleepLate";
    public static final String IMG_PATH = "img/card_Anon/changqisushidaozhide_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public NeverSleepLate() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.COMMON, CardTarget.SELF);
        //初始为3层活力
//        this.baseBlock = 8;
//        this.baseMagicNumber = heavy.count;
//        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new NeverSleepLateAction(p), this.magicNumber);
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        AbstractMonster mo;
        var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while (var3.hasNext()) {
            mo = (AbstractMonster) var3.next();
            if(mo.hasPower("Constricted")){
                if(upgraded){
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, mo.getPower("Constricted").amount*2));
                }else {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, mo.getPower("Constricted").amount));
                }

            }

        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new NeverSleepLate();
    }
    public void applyPowers() {
        this.baseMagicNumber = heavy.count;
        this.magicNumber = this.baseMagicNumber;
    }
    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
//            upgradeBlock(4);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}

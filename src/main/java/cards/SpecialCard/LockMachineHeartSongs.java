package cards.SpecialCard;

import basemod.abstracts.CustomCard;
import cards.AbstractLockAnonCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
public class LockMachineHeartSongs extends AbstractLockAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("LockMachineHeartSongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    public static final String ID = "LockMachineHeartSongs";
    public static final String IMG_PATH = "img/card_Anon/machineheart_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public LockMachineHeartSongs() {
        super(ID, NAME,IMG_PATH, DESCRIPTION, CardRarity.BASIC,  CardTarget.SELF ,1,  1,1);
//        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "机械心"), this.magicNumber));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new LockMachineHeartSongs();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);
        }
    }

  public void unlockSuccess() {
     super.unlockSuccess();
     initInfo();
   }
}

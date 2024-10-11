package cards.uncommon;

import basemod.abstracts.CustomCard;
import cards.token.Minute;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import pathes.AbstractCardEnum;
import power.Shining;

/**
 * 每一个瞬间的积累
 */
public class KiraKiraDokiDoki extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KiraKiraDokiDoki");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    public static final String ID = "KiraKiraDokiDoki";
    public static final String IMG_PATH = "img/card_Anon/kirakira_power.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public KiraKiraDokiDoki() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new Shining(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new KiraKiraDokiDoki();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


}

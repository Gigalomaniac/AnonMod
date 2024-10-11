package bandFriendsCard;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import power.Shining;
import power.heavy;
import pathes.AbstractCardEnum;


public class Soyorin extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Soyorin");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "Soyorin";
    public static final String IMG_PATH = "img/bandFriends/SoyoCard_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Soyorin() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.ENEMY);
        //初始为3层活力
        this.baseMagicNumber = heavy.count * 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain =true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.magicNumber = heavy.count;
//        this.baseMagicNumber = this.magicNumber;
        this.addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Soyorin();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.baseMagicNumber = heavy.count * 4;;
            this.magicNumber = this.baseMagicNumber;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    public void applyPowers() {
            if(upgraded){
                this.baseMagicNumber = heavy.count * 4;;
                this.magicNumber = this.baseMagicNumber;
            }else {
                this.baseMagicNumber = heavy.count * 2;
                this.magicNumber = this.baseMagicNumber;
            }

    }

}

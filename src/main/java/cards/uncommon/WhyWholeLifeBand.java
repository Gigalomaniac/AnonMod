package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.WholeLifeBandPower;


/**
 * 鲁莽结束
 */
public class WhyWholeLifeBand extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WhyWholeLifeBand");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 3;
    public static final String ID = "WhyWholeLifeBand";
    public static final String IMG_PATH = "img/card_Anon/tomoriEMO/10_power.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WhyWholeLifeBand() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 1;;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new WholeLifeBandPower(p,this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new WhyWholeLifeBand();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.baseMagicNumber = 2;;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    private  float timer = 0f;
    private  int currentIndex = 0;
    private  float FrameDuring = 0.1f;

    @Override
    public void update() {
        super.update();
        timer += Gdx.graphics.getDeltaTime();
        if(timer >= FrameDuring){
            timer -= FrameDuring;
            currentIndex = (currentIndex+1)% IMG_PATHS.length;
            this.textureImg=IMG_PATHS[currentIndex];
            this.loadCardImage(textureImg);
        }
    }
    private  static  final  String[] IMG_PATHS = {
            "img/card_Anon/tomoriEMO/1_power.png",
            "img/card_Anon/tomoriEMO/2_power.png",
            "img/card_Anon/tomoriEMO/3_power.png",
            "img/card_Anon/tomoriEMO/4_power.png",
            "img/card_Anon/tomoriEMO/5_power.png",
            "img/card_Anon/tomoriEMO/6_power.png",
            "img/card_Anon/tomoriEMO/7_power.png",
            "img/card_Anon/tomoriEMO/8_power.png",
            "img/card_Anon/tomoriEMO/9_power.png",
            "img/card_Anon/tomoriEMO/10_power.png",
    };
}

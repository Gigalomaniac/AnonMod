package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pathes.AbstractCardEnum;
import power.EnemyHeavy;
import power.heavy;

/**
 * 结束乐队
 */
public class CatHero extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("CatHero");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "CatHero";
    public static final String IMG_PATH = "img/card_Anon/suyan/34_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public CatHero() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 2;;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ReducePowerAction(p, p, "heavy",1));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new CatHero();
    }

    public void applyPowers() {


    }
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
    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.baseMagicNumber += 1;
            this.magicNumber = this.baseMagicNumber;
//            this.upgradeBaseCost(0);
        }
    }
        private  float timer = 0f;
    private  int currentIndex = 0;
    private  float FrameDuring = 0.05f;

    private  static  final  String[] IMG_PATHS = {
            "img/card_Anon/suyan/1_skill.png",
            "img/card_Anon/suyan/2_skill.png",
            "img/card_Anon/suyan/3_skill.png",
            "img/card_Anon/suyan/4_skill.png",
            "img/card_Anon/suyan/5_skill.png",
            "img/card_Anon/suyan/6_skill.png",
            "img/card_Anon/suyan/7_skill.png",
            "img/card_Anon/suyan/8_skill.png",
            "img/card_Anon/suyan/9_skill.png",
            "img/card_Anon/suyan/10_skill.png",
            "img/card_Anon/suyan/11_skill.png",
            "img/card_Anon/suyan/12_skill.png",
            "img/card_Anon/suyan/13_skill.png",
            "img/card_Anon/suyan/14_skill.png",
            "img/card_Anon/suyan/15_skill.png",
            "img/card_Anon/suyan/16_skill.png",
            "img/card_Anon/suyan/17_skill.png",
            "img/card_Anon/suyan/18_skill.png",
            "img/card_Anon/suyan/19_skill.png",
            "img/card_Anon/suyan/20_skill.png",
            "img/card_Anon/suyan/21_skill.png",
            "img/card_Anon/suyan/22_skill.png",
            "img/card_Anon/suyan/23_skill.png",
            "img/card_Anon/suyan/24_skill.png",
            "img/card_Anon/suyan/25_skill.png",
            "img/card_Anon/suyan/26_skill.png",
            "img/card_Anon/suyan/27_skill.png",
            "img/card_Anon/suyan/28_skill.png",
            "img/card_Anon/suyan/29_skill.png",
            "img/card_Anon/suyan/30_skill.png",
            "img/card_Anon/suyan/31_skill.png",
            "img/card_Anon/suyan/32_skill.png",
            "img/card_Anon/suyan/33_skill.png",
            "img/card_Anon/suyan/34_skill.png"
    };
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "还不够……沉重";
        if(heavy.count >=1){
            return true;
        }else {
            return false;
        }
    }
}

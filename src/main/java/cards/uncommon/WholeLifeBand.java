package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import static power.heavy.count;


/**
 * 鲁莽结束
 */
public class WholeLifeBand extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WholeLifeBand");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "WholeLifeBand";
    public static final String IMG_PATH = "img/card_Anon/MutsumiBandHappy/21_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WholeLifeBand() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        //初始为3层活力
        this.baseDamage = 3;
        this.baseMagicNumber = 3;;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAllEnemiesAction(p,this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        for(int i = 0 ; i < count ; i ++){
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(p,this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
//        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(p, p, "heavy"));
    }

    private  float timer = 0f;
    private  int currentIndex = 0;
    private  float FrameDuring = 0.05f;

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
            "img/card_Anon/MutsumiBandHappy/1_attack.png",
            "img/card_Anon/MutsumiBandHappy/2_attack.png",
            "img/card_Anon/MutsumiBandHappy/3_attack.png",
            "img/card_Anon/MutsumiBandHappy/4_attack.png",
            "img/card_Anon/MutsumiBandHappy/5_attack.png",
            "img/card_Anon/MutsumiBandHappy/6_attack.png",
            "img/card_Anon/MutsumiBandHappy/7_attack.png",
            "img/card_Anon/MutsumiBandHappy/8_attack.png",
            "img/card_Anon/MutsumiBandHappy/9_attack.png",
            "img/card_Anon/MutsumiBandHappy/10_attack.png",
            "img/card_Anon/MutsumiBandHappy/11_attack.png",
            "img/card_Anon/MutsumiBandHappy/12_attack.png",
            "img/card_Anon/MutsumiBandHappy/13_attack.png",
            "img/card_Anon/MutsumiBandHappy/14_attack.png",
            "img/card_Anon/MutsumiBandHappy/15_attack.png",
            "img/card_Anon/MutsumiBandHappy/16_attack.png",
            "img/card_Anon/MutsumiBandHappy/17_attack.png",
            "img/card_Anon/MutsumiBandHappy/18_attack.png",
            "img/card_Anon/MutsumiBandHappy/19_attack.png",
            "img/card_Anon/MutsumiBandHappy/20_attack.png",
            "img/card_Anon/MutsumiBandHappy/21_attack.png",
    };
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new WholeLifeBand();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.baseDamage = 5;
            this.baseMagicNumber = 5;;
            this.magicNumber = this.baseMagicNumber;
        }
    }


}

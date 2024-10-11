package cards.token;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import power.heavy;
import power.songs;
import pathes.AbstractCardEnum;

/**
 * 音一会
 */
public class OtoichieSongs extends CustomCard {
//    private static final CardStrings cardStrings = "GiveNote";
//    public static final String NAME = "音一会";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("OtoichieSongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "OtoichieSongs";
    public static String IMG_PATH = "img/card_Anon/yihuiCover_01_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public OtoichieSongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.NONE);
        //初始为3层活力
//        if(upgraded){
//            IMG_PATH = "img/card_Anon/yihuiCover_01_skill.png";
//        }else {
//            IMG_PATH = "img/card_Anon/yihuiCover_02_skill.png";
//        }
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
        this.selfRetain = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "音一会"), this.magicNumber));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new OtoichieSongs();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
//            this.IMG_PATH = "img/card_Anon/yihuiCover_01_skill.png";
            this.upgradeBaseCost(0);
            upgradeName();
//            upgradeMagicNumber(2);
//        }else {
//            this.IMG_PATH = "img/card_Anon/yihuiCover_02_skill.png";
        }
    }
    public void applyPowers() {
//        if(upgraded){
//            IMG_PATH = "img/card_Anon/yihuiCover_01_skill.png";
//        }else {
//            IMG_PATH = "img/card_Anon/yihuiCover_02_skill.png";
//        }

    }
}

package cards.dust;
import actions.LooseGoldAction;
import actions.movie.LastWordVideoEffect;
import actions.movie.RunEffectAction;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import java.util.Iterator;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:46
 * 备注: 基础打击牌（初始牌）
 */
public class UnderMixer extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("UnderMixer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/dust/3_attack.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static boolean canEsp = true;
    public static final String ID = "UnderMixer";
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public UnderMixer() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.LockAnon_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster a) {
        if (!this.upgraded){
            this.baseDamage = (int) (p.currentBlock*1.5);
        }else {
            this.baseDamage =  p.currentBlock*2;
        }
        this.calculateCardDamage(a);
        this.addToBot(new DamageAction(a, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.rawDescription = this.rawDescription + cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
        this.addToBot(new LoseBlockAction(p,p,p.currentBlock));
    }
    public void applyPowers() {
        if (!this.upgraded){
            this.baseDamage = (int) (AbstractDungeon.player.currentBlock*1.5);
        }else {
            this.baseDamage =  AbstractDungeon.player.currentBlock*2;
        }
        super.applyPowers();


        if (!this.upgraded){
            this.rawDescription = cardStrings.DESCRIPTION+"(*1.5)";
        }else {
            this.rawDescription = cardStrings.DESCRIPTION+"(*2)";
        }
        this.rawDescription = this.rawDescription + cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }
    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new UnderMixer();
    }
    @Override

    public boolean isStrike() {
        //是否是最基础攻击牌，
        return true;
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
        }
    }

}

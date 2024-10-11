package cards.dust;
import actions.LooseGoldAction;
import actions.movie.LastWordVideoEffect;
import actions.movie.RunEffectAction;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
public class RelatedHouseholds extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RelatedHouseholds");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/dust/6_skill.png";
    private static final int COST = 3;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static boolean canEsp = true;
    public static final String ID = "RelatedHouseholds";
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public RelatedHouseholds() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.LockAnon_COLOR, CardRarity.BASIC, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock=5;
        this.block = this.baseBlock;
        this.exhaust = true;
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster a) {
        addToBot((AbstractGameAction)new DrawCardAction(p,this.magicNumber));
        this.addToBot(new GainBlockAction(p, p, this.baseBlock));
        this.addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
        this.addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
    }

    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new RelatedHouseholds();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            this.baseMagicNumber = 2;
            this.magicNumber = this.baseMagicNumber;
            this.baseBlock=7;
            this.block = this.baseBlock;
        }
    }
}

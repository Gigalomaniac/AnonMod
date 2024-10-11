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
import com.megacrit.cardcrawl.actions.common.HealAction;
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
public class RedRomantic extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RedRomantic");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/dust/7_skill.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static boolean canEsp = true;
    public static final String ID = "RedRomantic";
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public RedRomantic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.LockAnon_COLOR, CardRarity.BASIC, CardTarget.SELF);
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster a) {
        if (!this.upgraded){
            this.addToTop(new HealAction((AbstractCreature) p, (AbstractCreature) p, (int) (p.currentBlock*0.5)));
        }else {
            this.addToTop(new HealAction((AbstractCreature) p, (AbstractCreature) p,  (p.currentBlock)));
        }

        this.addToBot(new LoseBlockAction(p,p,p.currentBlock));
    }

    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new RedRomantic();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

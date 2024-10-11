package cards.dust;
import cards.SpecialAnonCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import power.DustAnonLeaderSign;
import power.DustAnonSign;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:46
 * 备注: 基础打击牌（初始牌）
 */
public class LeaderScapegoat extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("LeaderScapegoat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/dust/2_skill.jpg";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static boolean canEsp = true;
    public static final String ID = "LeaderScapegoat";
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public LeaderScapegoat() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.LockAnon_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster a) {
        addToBot((AbstractGameAction)new ApplyPowerAction(a, AbstractDungeon.player, new DustAnonLeaderSign(a), 1));
    }

    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new LeaderScapegoat();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            upgradeBaseCost(0);
        }
    }

}

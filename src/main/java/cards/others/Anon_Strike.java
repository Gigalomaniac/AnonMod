package cards.others;
import actions.movie.LastWordVideoEffect;
import actions.movie.RunEffectAction;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import vfx.ScaledHammerImprintEffect;
import vfx.moryyEffect;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:46
 * 备注: 基础打击牌（初始牌）
 */
public class Anon_Strike extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Strike_Anon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/Anondef_attack.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "Strike_Anon";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Anon_Strike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        //添加基础攻击标签和将伤害设为6
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseDamage = ATTACK_DMG;
       this.tags.add(AbstractCard.CardTags.STRIKE);
       this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        if (Settings.FAST_MODE) {
//        } else {
//            addToBot(new VFXAction(new moryyEffect(), 1.0F));
//        }
    }
    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Anon_Strike();
    }
    @Override

    public boolean isStrike() {
        //是否是最基础攻击牌，
        return true;
    }
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
//        AbstractDungeon.actionManager.addToTop(new SilentMovementAction(1));

    }
    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

}

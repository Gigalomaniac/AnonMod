package cards.others;
import actions.LooseGoldAction;
import actions.movie.LastWordVideoEffect;
import actions.movie.RunEffectAction;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import bossRoom.effect.LatterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import pathes.AbstractCardEnum;

import java.util.Iterator;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:46
 * 备注: 基础打击牌（初始牌）
 */
public class barrel extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("barrel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/barrel_skill.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static boolean canEsp = true;
    public static final String ID = "barrel";
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public barrel() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.ALL_ENEMY);
        //桶不吃盒子
//        this.tags.add(BaseModCardTags.BASIC_STRIKE);
//        this.baseDamage = ATTACK_DMG;
//       this.tags.add(CardTags.STRIKE);
//       this.tags.add(CardTags.STARTER_STRIKE);
        canEsp = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster a) {
        this.addToBot(new LooseGoldAction(AbstractDungeon.player.gold/2));
        if(AbstractDungeon.player.currentHealth != 1)
        AbstractDungeon.player.currentHealth = AbstractDungeon.player.currentHealth/2;
//if( canEsp = true){


        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(m));
        }
        AbstractDungeon.player.hideHealthBar();
        AbstractDungeon.player.isEscaping = true;
        AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
        AbstractDungeon.player.escapeTimer = 2.5F;
        p.animX = 0;

        ReflectionHacks.setPrivate(p, AbstractCreature.class, "animationTimer", 0f);

        addToBot(new RunEffectAction(new LastWordVideoEffect(), true));
//        addToTop(new LatterAction(null, 8F));
    }

    @Override

    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new barrel();
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
            upgradeBaseCost(0);
        }
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        {

            Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            do {
                if (!var1.hasNext()) {
                    return true;
                }

                m = (AbstractMonster) var1.next();
                if (m.hasPower("BackAttack")) {
                    return false;
                }
            } while (m.type != AbstractMonster.EnemyType.BOSS);

            return false;

        }
    }
}

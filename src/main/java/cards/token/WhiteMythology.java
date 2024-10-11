package cards.token;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
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
import pathes.AbstractCardEnum;
import power.songs;

import java.lang.reflect.Field;

/**
 * 每一个瞬间的积累
 */
public class WhiteMythology extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WhiteMythology");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 0;
    public static final String ID = "WhiteMythology";
    public static final String IMG_PATH = "img/card_Anon/White/Anon white_skill_p_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WhiteMythology() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.setBackgroundTexture("img/pink/test/bg_skill_white_512.png","img/pink/test/bg_skill_white.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "我爱你，与你何干？"), this.magicNumber));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new WhiteMythology();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Settings.FAST_MODE) {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, NAME), this.magicNumber));
    }

    public void initializeDescription() {
        super.initializeDescription();
        for (DescriptionLine tmp : this.description) {
            try {
                Field text = tmp.getClass().getDeclaredField("text");
                text.setAccessible(true);
                String str = (String)text.get(tmp);
                String updateTmp = "";
                updateTmp = updateTmp + "[#000000]";
                updateTmp = updateTmp + str;
//                    updateTmp = updateTmp + "[]";
                text.set(tmp, updateTmp);
            } catch (NoSuchFieldException|IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

package cards.token;

import Mod.AnonCardSignStrings;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import pathes.AbstractCardEnum;
import power.songs;

/**
 * 名無声
 */
public class YouthfulBeautifulSongs extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("YouthfulBeautifulSongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//    public static final String DESCRIPTION = "演奏：名無声 NL 演奏开始时清空负面效果获得20点 格挡";
    private static final int COST = 0;
    public static final String ID = "YouthfulBeautifulSongs";
    public static final String IMG_PATH = "img/card_Anon/star/youthful beautiful_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public YouthfulBeautifulSongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.NONE);
        //初始为3层活力
        this.baseMagicNumber =0;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
        this.setBackgroundTexture("img/1024/bg_skill_star_512.png","img/1024/bg_skill_star.png");
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        if (Settings.FAST_MODE) {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, NAME), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new YouthfulBeautifulSongs();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
//            upgradeMagicNumber(2);
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
}

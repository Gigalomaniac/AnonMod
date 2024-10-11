package cards.token;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import power.songs;
import pathes.AbstractCardEnum;

/**
 * 备注: 凝神，0费，技能牌，获得3层活力（升级后为5层）
 */
public class RefrainSongs extends CustomCard {
//    private static final CardStrings cardStrings = "GiveNote";
    public static final String NAME = "輪符雨";
    public static final String DESCRIPTION = "演奏：輪符雨";
    private static final int COST = 0;
    public static final String ID = "RefrainSongs";
    public static final String IMG_PATH = "img/cards_Seles/SavePower.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public RefrainSongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.NONE);
        //初始为3层活力
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
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
        return (AbstractCard)new RefrainSongs();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}

package cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import power.musicStart;


/**
 * 鲁莽结束
 */
public class RingInvite extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RiNGInvite");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "RiNGInvite";
    public static final String IMG_PATH = "img/card_Anon/RiNGdezhaodai_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public RingInvite() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //初始为3层活力
        this.baseDamage = 0;
        this.baseMagicNumber = 0;;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            addToBot((AbstractGameAction) new GainEnergyAction(2));
        }else {
            addToBot((AbstractGameAction) new GainEnergyAction(1));
        }
        if(musicStart.ifStart == 2){
            if(upgraded){
                addToBot((AbstractGameAction) new GainEnergyAction(2));
            }else {
                addToBot((AbstractGameAction) new GainEnergyAction(1));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new RingInvite();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        if (Settings.language == Settings.GameLanguage.ZHS) {
//            this.cantUseMessage = "演出还没开始哦！";
//        }else {
//            this.cantUseMessage = "The performance hasn't started yet.";
//        }
//
//        if(musicStart.ifStart == 2)
//        return true;
//        else
//            return false;
//    }
}

package cards.others;

import actions.movie.LastWordVideoEffect;
import actions.movie.RunEffectAction;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import pathes.AbstractCardEnum;

/**
 * 备注: 凝神，0费，技能牌，获得3层活力（升级后为5层）
 */
public class LoveMeIFYouCan extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("LoveMeIFYouCan");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "LoveMeIFYouCan";
    public static final String IMG_PATH = "img/boss/IfYouCan_attack.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public LoveMeIFYouCan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.ENEMY);
        //初始为3层活力
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
//        this.selfRetain = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        if(!m.hasPower(ArtifactPower.POWER_ID)){
//            // stop attack animation
//            p.animX = 0;
//            ReflectionHacks.setPrivate(p, AbstractCreature.class, "animationTimer", 0f);
//
////            CardCrawlGame.music.silenceBGMInstantly();
////            CardCrawlGame.music.silenceTempBgmInstantly();
//            addToBot(new WaitAction(0.2f));
//            addToBot(new RunEffectAction(new LastWordVideoEffect(), true));
//        }
        this.addToBot(new ApplyPowerAction(m, p, new power.LoveMeIFYouCan(p, 1, false), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new LoveMeIFYouCan();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
//            this.selfRetain = true;
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }
    }


}

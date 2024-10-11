package cards.rare;

import basemod.abstracts.CustomCard;
import cards.token.Idea;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.Inspiration;
import pathes.AbstractCardEnum;
import power.musicStart;
import power.notLive;

import static power.heavy.count;

/**
 * 爆了
 */
public class BrainStorming extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BrainStorming");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 4;
    public static final String ID = "BrainStorming";
    public static final String IMG_PATH = "img/card_Anon/huya_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public BrainStorming() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.SELF);
        //初始为3层活力
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard)new Idea();
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(musicStart.POWER_ID)){
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(p, p, musicStart.POWER_ID));
            musicStart.ifStart = 1;
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new notLive(p,1,false), 1));
        int Dcount = 0;
        if(upgraded){
            Dcount = AbstractDungeon.player.hand.size() ;
        }else {
            Dcount = AbstractDungeon.player.hand.size() -1;
        }
        for(int i = 0; i < Dcount; ++i) {
                this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            this.addToBot(new MakeTempCardInHandAction(new Idea(), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new BrainStorming();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(3);
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            upgradeDamage(5);
        }
    }


}

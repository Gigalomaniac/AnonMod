package cards.rare;

import basemod.abstracts.CustomCard;
import characters.char_Anon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.Shining;
import power.beat;
import power.musicStart;
import pathes.AbstractCardEnum;

import java.util.Objects;

import static power.beat.AllAmount;

/**
 * 赌上一切
 */
public class BetEverything extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BetEverything");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "BetEverything";
    public static final String IMG_PATH = "img/card_Anon/buy_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public BetEverything() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust=true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        char_Anon.beat = 0;

        if(Objects.equals(beat.nowBeats, "skill"))
        AbstractDungeon.player.getPower(beat.POWER_ID).amount=0;
//        addToBot((AbstractGameAction)new ReducePowerAction(p, p, beat.POWER_ID, AllAmount));
        AllAmount = 0;
        if(!p.hasPower("notLive")){
            addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new musicStart(p), 1));
            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
            this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 1), 1));
            musicStart.ifStart = 0;
        }
        addToBot((AbstractGameAction)new PressEndTurnButtonAction());
            }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new BetEverything();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
//            this.exhaust=false;
        }
    }


}

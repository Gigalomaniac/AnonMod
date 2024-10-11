package cards.others;

import Mod.AnonCardSignStrings;
import actions.SkipEnemiesTurnFalseAction;
import cards.SpecialAnonCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import monster.ShoujoKageki.Karen;
import pathes.AbstractCardEnum;
import power.AveMujica;
import power.Karen.KarenShining;
import power.musicStart;

import java.lang.reflect.Field;
import java.util.Iterator;


public class Roselia extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Roselia");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;
    public static final String ID = "Roselia";
    public static final String IMG_PATH = "img/card_Anon/roselia_bandlogo_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Roselia() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
//        this.isEthereal = true;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(51,68,170));
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
        this.setBackgroundTexture("img/1024/bg_skill_Roselia_512.png","img/1024/bg_skill_Roselia.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Roselia();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
        }
    }

    public void triggerWhenDrawn() {
        musicStart.ifStart =1;
        addToBot((AbstractGameAction)new SkipEnemiesTurnFalseAction());
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, musicStart.POWER_ID));
        this.addToBot(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player, StrengthPower.POWER_ID, 1));
        this.addToBot(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player, DexterityPower.POWER_ID, 1));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.cantUseMessage = "我不能使用";
        }else {
            this.cantUseMessage = "I can't use";
        }
        return false;
    }
    public boolean canUpgrade() {
        return false;
    }

    public void triggerOnExhaust() {
        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
    }
    public void triggerOnEndOfTurnForPlayingCard() {
        this.addToBot(new DiscardSpecificCardAction(this));
//        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }
    public void initializeDescription() {
        super.initializeDescription();
        for (DescriptionLine tmp : this.description) {
            try {
                Field text = tmp.getClass().getDeclaredField("text");
                text.setAccessible(true);
                String str = (String)text.get(tmp);
                String updateTmp = "";
                updateTmp = updateTmp + "[#ddaadd]";
                updateTmp = updateTmp + str;
//                    updateTmp = updateTmp + "[]";
                text.set(tmp, updateTmp);
            } catch (NoSuchFieldException|IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

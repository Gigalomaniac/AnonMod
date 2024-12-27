package cards.others;

import Mod.AnonCardSignStrings;
import actions.SkipEnemiesTurnFalseAction;
import cards.SpecialAnonCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;
import pathes.AbstractCardEnum;
import power.DustAnonLeaderSign;
import power.Shining;
import power.musicStart;

import java.lang.reflect.Field;


public class TheConsciousnessOfTheWorldToken extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("TheConsciousnessOfTheWorldToken");
    public static final String NAME = cardStrings.NAME;
    public static final String getDESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 0;
    public static final String ID = "TheConsciousnessOfTheWorldToken";
    public static final String IMG_PATH = "img/card_Anon/time_power.png";
    private  AbstractPower setPower ;
    private  int Powernum = -99;
    public static String DESCRIPTION = getDESCRIPTION;

    public TheConsciousnessOfTheWorldToken(AbstractPower avePower, int num) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(51,68,170));
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
        this.setBackgroundTexture("img/1024/bg_power_Time512.png","img/1024/bg_power_Time.png");
        setPower = avePower;
        Powernum = num;
    }
    public void applyPowers() {
      super.applyPowers();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        System.out.println(Powernum+setPower.ID);
        if(Powernum == -1){
            setPower.amount=1;
            addToBot(new ApplyPowerAction(p, p,  setPower, 1));
        }else {
            if(AbstractDungeon.player.hasPower(setPower.ID)){
                System.out.println(setPower.amount);
//                if(setPower.amount>10){
//                    addToBot(new ApplyPowerAction(p, p, setPower, 10));
//                }else {
//                    Powernum = AbstractDungeon.player.getPower(setPower.ID).amount + Powernum;
                    addToBot(new ApplyPowerAction(p, p, setPower, Powernum));
//                }
            }else {
                int a = Powernum;
                Powernum = 0;
                setPower.amount=0;
                System.out.println(setPower.amount+a);
                addToBot(new ApplyPowerAction(p, p,  setPower, 0));
                setPower.amount=a;
                addToBot(new ApplyPowerAction(p, p,  setPower, Powernum));
            }

        }

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new TheConsciousnessOfTheWorldToken(setPower,Powernum);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

    }

    public boolean canUpgrade() {
        return false;
    }

    public void initializeDescription() {
        if(setPower!=null){
            if(Powernum == -1){
                this.rawDescription = getDESCRIPTION +" NL "+ setPower.name+ "1"+"层";
            }else {
                this.rawDescription = getDESCRIPTION +" NL "+ setPower.name+ Powernum+"层";
            }
        }
        super.initializeDescription();

    }
    @Override
    public void update() {
        super.update();
//        initializeDescription();
//        if(setPower!=null){
//            if(Powernum == -1){
//                this.rawDescription = getDESCRIPTION +" NL "+ setPower.name+ "1"+"层";
//            }else {
//                this.rawDescription = getDESCRIPTION +" NL "+ setPower.name+ Powernum+"层";
//            }
//            System.out.println(setPower.name+Powernum);
//            System.out.println(DESCRIPTION);
//            super.initializeDescription();
//        }
    }
}

package relics;
import basemod.abstracts.CustomRelic;
import characters.char_Anon;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import power.*;

import java.util.Iterator;

public class GuitarWhiteAnon extends CustomRelic implements OnApplyPowerRelic {
    public boolean colosseum = false;

    public static final String ID = "GuitarWhiteAnon";
    private static final String IMG = "img/relics/Wgituar.png";
    private static final String IMG_OTL = "img/relics/Wgituar.png";
    public static boolean hasboss = false;
    public static boolean ifGirlReboot = false;
    public static boolean ifWorldLegacy = false;
    public static boolean ifWhiteMythology = false;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public GuitarWhiteAnon() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
    }


    @Override
    public void onVictory() {
        char_Anon k = (char_Anon)AbstractDungeon.player;
        k.WhiteAnonAwakeRefreshSkin(false);

        if(AbstractDungeon.getCurrRoom().eliteTrigger){
            this.counter = this.counter +2;
        }else {
            hasboss = false;
            Iterator var2 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while (var2.hasNext()) {
                AbstractMonster mo = (AbstractMonster) var2.next();
                if(mo.type.equals(AbstractMonster.EnemyType.BOSS)){
                    hasboss = true;
                    break;
                }
            }
            if(hasboss == true){
                this.counter = this.counter +4;
            }else {
                this.counter = this.counter +1;
            }
        }

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new GuitarWhiteAnon();
    }

    public void atBattleStart() {
        ifGirlReboot = false;
        ifWorldLegacy = false;
        ifWhiteMythology = false;
        super.atBattleStart();
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void atTurnStart() {
//        addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new heavy((AbstractCreature) AbstractDungeon.player, 0), 0));
//        addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new heavy((AbstractCreature) AbstractDungeon.player, 0), 0));
    }
    public  void  onPlayerEndTurn(){

    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature targrt, AbstractCreature source) {
        if(abstractPower.ID.equals(Shining.POWER_ID) && source == AbstractDungeon.player&& targrt == AbstractDungeon.player){
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, null, (AbstractPower) new heavy((AbstractCreature) AbstractDungeon.player, 1), 1));
        }
        if(abstractPower.ID.equals(heavy.POWER_ID)&& source == AbstractDungeon.player&& targrt == AbstractDungeon.player){
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, null, (AbstractPower) new Shining((AbstractCreature) AbstractDungeon.player, 1), 1));
        }
        return true;
    }

    @Override
    public int onApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if(power.ID.equals(Shining.POWER_ID)&& source == AbstractDungeon.player&& target == AbstractDungeon.player){
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, null, (AbstractPower) new heavy((AbstractCreature) AbstractDungeon.player, 1), 1));
        }
        if(power.ID.equals(heavy.POWER_ID)&& source == AbstractDungeon.player&& target == AbstractDungeon.player){
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, null, (AbstractPower) new Shining((AbstractCreature) AbstractDungeon.player, 1), 1));
        }
        return OnApplyPowerRelic.super.onApplyPowerStacks(power, target, source, stackAmount);
    }
}

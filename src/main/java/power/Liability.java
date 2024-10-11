package power;

import actions.LooseGoldAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import java.util.Iterator;

public class Liability extends AbstractPower implements OnReceivePowerPower, BetterOnApplyPowerPower {
    // 能力的ID
    public static final String POWER_ID = "Liability";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int count  = 0;

    public Liability(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/dustAnon/LiabilityPower84.png";
        String path48 = "img/newbuff/dustAnon/LiabilityPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {

        this.description = DESCRIPTIONS[0]+this.amount+ DESCRIPTIONS[1]+this.amount+ DESCRIPTIONS[2]+this.amount+ DESCRIPTIONS[3]+this.amount+ DESCRIPTIONS[4];

    }
    public void atStartOfTurn() {

            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player,this.amount), this.amount));
            this.addToBot(new HealAction(owner, owner, this.amount));
           this.addToBot(new GainEnergyAction(this.amount));
          addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new variation(AbstractDungeon.player,this.amount), this.amount));
    }
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player,this.amount,false), this.amount));
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player,this.amount,false), this.amount));
            AbstractDungeon.actionManager.addToBottom(new LooseGoldAction(this.amount));
        }
    }
    public void reducePower(int reduceAmount) {
        this.amount -= reduceAmount;
        if (this.amount + reduceAmount ==0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractCreature.equals(this.owner)){
            if(abstractPower.type.equals(PowerType.BUFF) && !abstractPower.ID.equals(WithoutLiability.POWER_ID) && !abstractPower.ID.equals(variation.POWER_ID)  && !abstractPower.ID.equals(StrengthPower.POWER_ID)){
                this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
                return false;
            }else{

                return true;
            }
        }
        return false;
    }
    public void onEnergyRecharge() {
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, BarricadePower.POWER_ID));
    }


    public void onVictory() {
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        return true;
    }
}


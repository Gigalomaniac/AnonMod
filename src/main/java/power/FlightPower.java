package power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FlightPower extends AbstractPower{
    public static final String POWER_ID = "AnonFlightPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int storedAmount;

    public FlightPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("flight");
        this.type = AbstractPower.PowerType.BUFF;

        this.storedAmount = amount;
    }


    public void playApplyPowerSfx() { CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F); }



    public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1]; }



    public void atStartOfTurn() { this.amount = this.storedAmount; }


    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.storedAmount += stackAmount;
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
            return damage / 2.0F;
        }
        return damage;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (this.amount > 0 && info.owner != AbstractDungeon.player && info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            flash();
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
        return damageAmount;
    }
}
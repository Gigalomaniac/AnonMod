package monster.act3;


import actions.LooseGoldAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

public class CarStstoryPower extends AbstractPower {
    public static final String POWER_ID = "Explosive";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final int DAMAGE_AMOUNT = 30;
    public static int money;
    public CarStstoryPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Explosive";
        this.owner = owner;
//        this.amount = damage;
        this.updateDescription();
        this.loadRegion("explosive");
        this.money = 0;
    }

    public void updateDescription() {
//        if (this.amount == 1) {
            this.description = DESCRIPTIONS[3] + money + DESCRIPTIONS[2];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + 30 + DESCRIPTIONS[2];
//        }

    }

    public void duringTurn() {
//        if (this.amount == 1 && !this.owner.isDying) {
//            this.addToBot(new VFXAction(new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
//            this.addToBot(new SuicideAction((AbstractMonster)this.owner));
//            AbstractDungeon.actionManager.addToBottom(new LooseGoldAction(money));
////            DamageInfo damageInfo = new DamageInfo(this.owner, 30, DamageType.THORNS);
////            this.addToBot(new DamageAction(AbstractDungeon.player, damageInfo, AttackEffect.FIRE, true));
//        } else {
            money += 30;
        this.description = DESCRIPTIONS[3] + money + DESCRIPTIONS[2];
//        }

    }
    public void onDeath() {
        this.addToBot(new VFXAction(new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
        this.addToBot(new SuicideAction((AbstractMonster)this.owner));
        AbstractDungeon.actionManager.addToBottom(new LooseGoldAction(money));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("CarStstoryPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

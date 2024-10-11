package power.StarAnon;

import actions.RemoveNumDebuffsAction;
import actions.RemoveNumbuffsAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import star.StarAnon.StarAnonPower.StarAnonShining;

public class BackTrack extends AbstractPower {
    public static final String POWER_ID = "BackTrack";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BackTrack(AbstractCreature owner, int thornsDamage) {
        this.name = NAME;
        this.ID = "BackTrack";
        this.owner = owner;
        this.amount = thornsDamage;
        this.updateDescription();
        this.loadRegion("time");
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] +  this.amount*2 + DESCRIPTIONS[1];
    }
    public void stackPower(int stackAmount) {
        if (this.amount == -1) {
        } else {
            this.fontScale = 8.0F;
            this.amount += stackAmount;
            this.updateDescription();
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageType.THORNS && info.type != DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            if(damageAmount >=200){
                this.addToTop(new RemoveNumDebuffsAction(this.owner));
                this.addToTop(new RemoveNumbuffsAction(AbstractDungeon.player));
            }

            if (damageAmount < this.owner.currentHealth){
                this.addToTop(new HealAction(this.owner, this.owner, this.amount*2));
            }
            this.amount ++;
        }

        return damageAmount;
    }
    public void atEndOfRound() {
        if(this.owner.hasPower(StarAnonShining.POWER_ID)){
            this.amount =this.owner.getPower(StarAnonShining.POWER_ID).amount;
        }else {
            this.amount =1;
        }

    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
            this.description = DESCRIPTIONS[0] +  this.amount*2 + DESCRIPTIONS[1];

    }
}

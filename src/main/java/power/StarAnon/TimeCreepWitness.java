package power.StarAnon;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TimeCreepWitness   extends AbstractPower {
    public static final String POWER_ID = "TimeCreepWitness";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("SoulShieldPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public TimeCreepWitness(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "TimeCreepWitness";
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage("lobotomyMod/images/powers/32/SoulShield.png");
        this.type = PowerType.BUFF;
        updateDescription();
    }


    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
    }


    public void atEndOfRound() {
        super.atEndOfRound();
        if (this.owner.currentBlock > 0) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this.owner, this.owner.currentBlock, DamageInfo.DamageType.THORNS)));
        }
    }


    public void updateDescription() { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
}

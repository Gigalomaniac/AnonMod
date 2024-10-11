package power;

import cards.others.Roselia;
import cards.others.untouchableFutureToken;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class untouchableFuturePower extends AbstractPower implements OnReceivePowerPower, BetterOnApplyPowerPower {
    // 这是高潮迭起
    public static final String POWER_ID ="untouchableFuturePower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    // 能力的描述
    private static int postfix = 0;
    public untouchableFuturePower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID + postfix++;;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;
        String path128 = "img/card_Anon/star/untouchableFuturePowerPower84.png";
        String path48 = "img/card_Anon/star/untouchableFuturePowerPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] ;
    }


    public void stackPower(int stackAmount) {
//        super.stackPower(stackAmount);
        this.amount += stackAmount;
        if (this.amount ==0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount >= 999)
            this.amount = 999;
    }
    public void atEndOfRound() {
//        if (this.justApplied) {
//            this.justApplied = false;
//        } else {
//            if (this.amount == 0) {
//                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//            } else {
//                this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
//            }
//
//        }
    }

    public void onAfterCardPlayed(AbstractCard usedCard) {


    }
    public void onEnergyRecharge() {
        flash();
//        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }


    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        return true;
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
//        if (abstractCreature.equals(this.owner)){
//            if(abstractPower.type.equals(PowerType.DEBUFF)){
//                return false;
//            }else{

                return true;
//            }
//        }
//        return false;
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        this.amount += damageAmount;
        return 0;
    }
    public void atStartOfTurn() {
        untouchableFutureToken untouchableFutureToken = new untouchableFutureToken();
        untouchableFutureToken.baseMagicNumber = this.amount;
        untouchableFutureToken.magicNumber = this.amount;
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(untouchableFutureToken, 1));
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.ID));
    }

}


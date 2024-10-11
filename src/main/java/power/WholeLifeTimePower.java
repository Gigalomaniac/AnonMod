package power;

import cards.token.Minute;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WholeLifeTimePower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "WholeLifeTimePower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int AllCount = 0;
    // 能力的描述

    public WholeLifeTimePower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/tomoriBuffPower84.png";
        String path48 = "img/newbuff/tomoriBuffPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
        AllCount = this.amount;
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
        this.description = DESCRIPTIONS[0] + this.amount * 5 + DESCRIPTIONS[1]+ this.amount + DESCRIPTIONS[2];
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        AllCount = this.amount;
        if (this.amount >= 999)
            this.amount = 999;
    }


    public void onEnergyRecharge() {
        flash();
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Draw"));
        }

    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type == AbstractCard.CardType.SKILL) {
//            flash();
//            action.exhaustCard = true;
//        }
        AllCount = this.amount;
    }

    public void onRemove() {

    }



    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {

           this.addToBot(new MakeTempCardInDrawPileAction(new Minute(), this.amount, true, true));
           this.flash();
           this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount * 5));
           this.addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, null, (AbstractPower) new heavy((AbstractCreature) AbstractDungeon.player, this.amount), this.amount));
        }
    }

    public void atEndOfRound() {
    }
}


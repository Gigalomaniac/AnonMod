package power;

import actions.exhaustDiscoveryAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PrePreparationPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "PrePreparation";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int AllCount = 0;
    // 能力的描述

    public PrePreparationPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/PrePreparationPower84.png";
        String path48 = "img/newbuff/PrePreparationPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        AbstractPlayer var10000 = AbstractDungeon.player;
        // 首次添加能力更新描述
        this.updateDescription();
        AllCount = this.amount;
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
        this.description = DESCRIPTIONS[0]+  this.amount +DESCRIPTIONS[1] +  this.amount +DESCRIPTIONS[2];
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (this.amount >= 999)
            this.amount = 999;
    }


    public void onEnergyRecharge() {
        flash();
    }

    public void reducePower(int reduceAmount) {
      super.reducePower(reduceAmount);
    }

    public void onRemove() {
      super.onRemove();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type == AbstractCard.CardType.SKILL) {
//            flash();
//            action.exhaustCard = true;
//        }
        AllCount = this.amount;
    }

    public void atStartOfTurn() {
        addToBot(new DrawCardAction(this.amount));
       if(musicStart.ifStart == 0){
            this.addToBot(new GainEnergyAction(this.amount));
        }
        this.flash();
    }
}


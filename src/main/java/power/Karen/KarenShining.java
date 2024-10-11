package power.Karen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KarenShining extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "KarenShining";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int allCount = 0;

    public KarenShining(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.canGoNegative = true;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        allCount = this.amount;
        // 添加一大一小两张能力图
        String path128 = "img/newbuff/shinePower84.png";
        String path48 = "img/newbuff/shinePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.canGoNegative = true;
        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        if (this.amount > 0) {
            this.type = PowerType.BUFF;
        } else {
            this.type = PowerType.DEBUFF;
        }
        this.description = DESCRIPTIONS[0]  +  this.amount + DESCRIPTIONS[1] ;
    }

    public void stackPower(int stackAmount) {
//        super.stackPower(stackAmount);
        this.amount += stackAmount;
        if (this.amount ==0) {
//            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount >= 999)
            this.amount = 999;
        allCount = this.amount;
    }

    public void reducePower(int reduceAmount) {
        this.amount -= reduceAmount;
        if (this.amount + reduceAmount ==0) {
//            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    public void onVictory() {
        this.amount = 0;
        allCount = 0;
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {

    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return  damage * (1+this.amount*0.05F);
        } else {
            return damage;
        }
    }
}


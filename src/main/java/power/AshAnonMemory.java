package power;

import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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

import java.util.Iterator;

public class AshAnonMemory extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "AshAnonMemory";
    // 能力的本地化字段
//    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
//    private static final String NAME = powerStrings.NAME;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int AllCount = 0;

    public static String power = "";

    // 能力的描述
//    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//
    public AshAnonMemory(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/IFPower84.png";
        String path48 = "img/newbuff/IFPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
//        this.loadRegion("artifact");
        AllCount = this.amount;
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
//        this.description = "不会收到伤害。 NL ";
//        this.description = "不会收到伤害。 NL ";
        this.description = DESCRIPTIONS[0] ;
        if(this.owner.id.equals("MachineHeart")){
            this.description =this.description +DESCRIPTIONS[1] ;
        }
        if(this.owner.id.equals("StarTrails")){
            this.description =this.description + DESCRIPTIONS[2] ;
        }
        if(this.owner.id.equals("ZhenHunJi")){
            this.description =this.description + DESCRIPTIONS[3] ;
        }
        if(this.owner.id.equals("IdealEnd")){
            this.description =this.description + DESCRIPTIONS[4] ;
        }
        if(this.owner.id.equals("SuicideFactor")){
            this.description =this.description + DESCRIPTIONS[5] ;
        }
        if(this.owner.id.equals("GirlRelive")){
            this.description =this.description + DESCRIPTIONS[6] ;
        }
        if(this.owner.id.equals("Falling") && InnerFavillaeSide.Stage != 3){
            this.description =this.description + DESCRIPTIONS[7];
        }
        if(this.owner.id.equals("Falling") && InnerFavillaeSide.Stage == 3){
            this.description =this.description + DESCRIPTIONS[8] ;
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

    }


    public void onEnergyRecharge() {
        flash();
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
    public void onUseCard(AbstractCard card, UseCardAction action) {

    }
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
//        if (damageAmount > this.amount) {
//            damageAmount = this.amount;
//        }
        return 0;
    }
}


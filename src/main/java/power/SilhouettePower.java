package power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SilhouettePower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID ="SilhouettePower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int count  = 0;
    public SilhouettePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/SilhouettePower84.jpg";
        String path48 = "img/newbuff/SilhouettePower32.jpg";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
        this.description = DESCRIPTIONS[0];
        count = this.amount;
    }


public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
    if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
        this.flash();
        if(damageAmount > target.currentHealth){
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, target.currentHealth));
        }else {
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, damageAmount));
        }

//        this.addToBot(new SilhouettePowerAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));

    }

}

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999)
            this.amount = 999;
    }


    public void onEnergyRecharge() {
        flash();
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
//    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
//        if (type == DamageInfo.DamageType.NORMAL) {
//            return damage * (1 + amount * 0.05F);
//        }
//        return damage;
//    }
}


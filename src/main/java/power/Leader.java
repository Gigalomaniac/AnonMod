package power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Leader extends AbstractPower implements OnReceivePowerPower, BetterOnApplyPowerPower {
    // 这是高潮迭起
    public static final String POWER_ID ="Leader";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    // 能力的描述

    public Leader(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;
        String path128 = "img/newbuff/WhiteAnon/memoryPower84.png";
        String path48 = "img/newbuff/WhiteAnon/memoryPower32.png";
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
        super.stackPower(stackAmount);

    }
    public void atEndOfTurn(boolean isPlayer) {

    }

    public void onAfterCardPlayed(AbstractCard usedCard) {


    }
    public void atEndOfRound() {

    }

    public void onEnergyRecharge() {
        flash();
//        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }


    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
//        System.out.println(abstractCreature.id);
//        System.out.println(this.owner.id);
//        if (!abstractCreature.equals(this.owner)){
//            if(abstractPower.type.equals(PowerType.DEBUFF)){
//                return false;
//            }else{
//
//                return true;
//            }
//        }
//        return false;
        return true;
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        System.out.println(abstractCreature.id);
        System.out.println(this.owner.id);
        if (abstractCreature.equals(this.owner)){
            if(abstractPower.type.equals(PowerType.DEBUFF)){
                return false;
            }else{

                return true;
            }
        }
        return false;
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return 0;
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return 0;
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        return 0;
    }
//    protected boolean canGoNegative() {
//        return true;
//    }

}


package power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WinterPower;

import java.util.Iterator;

public class DustAnonLeaderSign extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "DustAnonLeaderSign";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int count  = 0;

    public DustAnonLeaderSign(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/dustAnon/thPower84.png";
        String path48 = "img/newbuff/dustAnon/thPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {

        this.description = DESCRIPTIONS[0];

    }

    public void atStartOfTurn() {

        addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new WithoutLiability(AbstractDungeon.player,1), 1));
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
    public void onRemove() {
      if(AbstractDungeon.player.hasPower(WithoutLiability.POWER_ID)){
          addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Liability(AbstractDungeon.player, AbstractDungeon.player.getPower(WithoutLiability.POWER_ID).amount), AbstractDungeon.player.getPower(WithoutLiability.POWER_ID).amount));
          addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, WithoutLiability.POWER_ID));
      }
    }
    public void onDeath() {
        if(AbstractDungeon.player.hasPower(WithoutLiability.POWER_ID)){
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Liability(AbstractDungeon.player, AbstractDungeon.player.getPower(WithoutLiability.POWER_ID).amount), AbstractDungeon.player.getPower(WithoutLiability.POWER_ID).amount));
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, WithoutLiability.POWER_ID));
        }
    }
    public void onEnergyRecharge() {
//        flash();
    }


    public void onVictory() {
    }

}


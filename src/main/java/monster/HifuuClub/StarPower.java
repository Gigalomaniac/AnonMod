package monster.HifuuClub;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnDrawPileShufflePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class StarPower extends AbstractPower  {
    public static final String POWER_ID = "StarPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int storedAmount;

    public StarPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = -1;
        String path128 = "img/newbuff/starPower84.png";
        String path48 = "img/newbuff/starPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
        this.type = PowerType.BUFF;

        this.storedAmount = amount;
    }





    public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0]; }



    public void atStartOfTurn() { this.amount = this.storedAmount; }


    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 1.5F : damage;
    }
    public void atEndOfRound() {
        super.atEndOfRound();
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this.owner, (AbstractCreature) this.owner, (AbstractPower) new StrengthPower((AbstractCreature) this.owner, 1), 1));
    }

}
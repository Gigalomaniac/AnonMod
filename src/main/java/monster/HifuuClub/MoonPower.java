package monster.HifuuClub;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnDrawPileShufflePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class MoonPower extends AbstractPower {
    public static final String POWER_ID = "MoonPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int storedAmount;

    public MoonPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = -1;
        String path128 = "img/newbuff/moonPower84.png";
        String path48 = "img/newbuff/moonPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
        this.type = PowerType.BUFF;

        this.storedAmount = amount;
    }


//    public void playApplyPowerSfx() { CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F); }



    public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] ; }



    public void atStartOfTurn() { this.amount = this.storedAmount; }


    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
            return damage / 2.0F;
    }

    public void atEndOfRound() {
        super.atEndOfRound();
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) this.owner, (AbstractCreature) this.owner, 20));
        AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, 10));
    }



}
package ExcessiveFantasy.boss.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.AveMujicaShining;
import power.SakikoConsciousness;

public class MemorySakiko extends AbstractPower {
    // 这是高潮迭起
    public static final String POWER_ID ="MemorySakiko";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // 能力的描述

    public MemorySakiko(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        String path128 = "img/newbuff/World/omigaPower84.png";
        String path48 = "img/newbuff/World/omigaPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
    public void atEndOfRound() {
        super.atEndOfRound();
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AveMujicaShining(this.owner,1), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new SakikoConsciousness(this.owner,1), 1));
    }


}


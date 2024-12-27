package ExcessiveFantasy.boss.power;

import cards.uncommon.KiraKiraDokiDoki;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MemoryPP extends AbstractPower {
    // 这是高潮迭起
    public static final String POWER_ID ="MemoryPP";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // 能力的描述

    public MemoryPP(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        String path128 = "img/newbuff/World/bPower84.png";
        String path48 = "img/newbuff/World/bPower32.png";
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
        AbstractCard KiraKiraDokiDoki = new KiraKiraDokiDoki();
        KiraKiraDokiDoki.upgrade();
        KiraKiraDokiDoki.cost = 0;
        KiraKiraDokiDoki.magicNumber =1;
        KiraKiraDokiDoki.baseMagicNumber = 1;
        this.addToBot(new MakeTempCardInDiscardAction(KiraKiraDokiDoki, 2));
    }


}


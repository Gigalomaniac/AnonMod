package power.mika;

import cards.SpecialCard.MikaSwissRoll;
import cards.others.KarenShing;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


//音符buff
public class MikaSupport extends TwoAmountPower {

    // 能力的ID
    public static final String POWER_ID = "MikaSupport";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MikaSupport(AbstractCreature owner, int Amount2) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = 10;

        String path128 = "img/newbuff/mika/supportPower84.png";
        String path48 = "img/newbuff/mika/supportPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.amount2 = Amount2;

        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] +this.amount+ DESCRIPTIONS[1]+this.amount2+ DESCRIPTIONS[2];

    }
    public void stackPower(int stackAmount) {
        if (this.amount == -1) {

        } else {
            this.amount2 += stackAmount;
        }
    }
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power,target,source);
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if(this.amount > card.costForTurn){
                this.amount -=card.costForTurn;
            this.updateDescription();
        }else {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new MikaSwissRoll(), this.amount2));
            this.amount = 10;
            this.updateDescription();
        }
        super.onAfterUseCard(card,action);
    }
}


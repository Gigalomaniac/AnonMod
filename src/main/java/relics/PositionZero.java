package relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import power.Shining;
import utils.ClickableRelic;


public class PositionZero extends ClickableRelic{
    public static final String ID = "PositionZero";
    private static final String IMG = "img/relics/PositionZero.png";
    private static final String IMG_OTL = "img/relics/PositionZero.png";
//    public static final String DESCRIPTION = "乐奈塞给你的一把钥匙，看上去能和另一把凑成一对。";

    public static boolean isOn = false;

    public int event = 0;

    public AbstractEvent eventFinal = null;
    public PositionZero() {
        super("PositionZero", ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
//        setDescription();
    }
    public void playerdead() {
        this.counter++;
    }

    @Override
    public void onEquip() {
        super.onEquip();
        AbstractDungeon.player.energy.energyMaster++;
        this.counter = 0;
    }
    public void atBattleStart() {
        super.atBattleStart();
        addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, this.counter*3), this.counter*3));
        addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, this.counter*3), this.counter*3));
        addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new Shining((AbstractCreature) AbstractDungeon.player, this.counter*3), this.counter*3));
    }

    public AbstractRelic makeCopy() {
        return new PositionZero();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onRightClick() {

    }
}

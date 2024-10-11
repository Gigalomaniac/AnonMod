package relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import power.Shining;
import power.musicStart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class FrenchFries extends CustomRelic {
    public static final String ID = "FrenchFries";
    private static final String IMG = "img/relics/FrenchFries.png";
    private static final String IMG_OTL = "img/relics/FrenchFries.png";


    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public FrenchFries() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.CLINK);
        PowerTip tip = new PowerTip();
        tip.header="@STSLIB:FLAVOR@";
        tip.body="design by 爱音A杯亚军： NL Bye";
        FlavorText.PowerTipFlavorFields.boxColor.set(tip, CardHelper.getColor(255, 136, 153));
        this.tips.add(tip);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

    }
    @Override
    public void onVictory() {
    }
    public void onEquip() {

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new FrenchFries();
    }

    public void atBattleStart() {
    }
    public void atBattleStartPreDraw() {
    }

    public void atTurnStart() {
        System.out.println("atTurnStart"+musicStart.ifStart);
        if(musicStart.ifStart == 0){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Shining(AbstractDungeon.player, 1), 1));
        }

    }
}

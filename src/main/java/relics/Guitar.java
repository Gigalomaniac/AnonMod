package relics;
import basemod.abstracts.CustomRelic;
import characters.char_Anon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
import power.Shining;
import power.musicStart;
import power.songs;
import power.variation;

public class Guitar extends CustomRelic {
    public boolean colosseum = false;

    public static final String ID = "Guitar";
    private static final String IMG = "img/relics/gituar.png";
    private static final String IMG_OTL = "img/relics/gituar.png";

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public Guitar() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, AbstractRelic.LandingSound.CLINK);
    }



    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new Guitar();
    }



    public void onEquip() {}

    public int changeNumberOfCardsInReward(int numberOfCards) {
        if(AbstractDungeon.floorNum == 0){
            return numberOfCards - 3;
        }
        else {
            return numberOfCards;
        }
    }

    public void atTurnStart() {
        if (musicStart.ifStart == 0) {
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new variation((AbstractCreature)AbstractDungeon.player, 1), 1));
        }

    }
//    public boolean canUseCampfireOption(AbstractCampfireOption option) {
//        if (option instanceof RestOption && option.getClass().getName().equals(SmithOption.class.getName())) {
//            return false;
//        }
//        return true;
//    }
}

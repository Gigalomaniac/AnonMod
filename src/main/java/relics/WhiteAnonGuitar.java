package relics;
import basemod.abstracts.CustomRelic;
import characters.char_Anon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import power.Shining;
import power.musicStart;
import power.songs;
import power.variation;

public class WhiteAnonGuitar extends CustomRelic {
    public boolean colosseum = false;

    public static final String ID = "WhiteAnonGuitar";
    private static final String IMG = "img/relics/gituar.png";
    private static final String IMG_OTL = "img/relics/gituar.png";

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public WhiteAnonGuitar() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
    }


    @Override
    public void onVictory() {
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new WhiteAnonGuitar();
    }

    public void atBattleStart() {

        super.atBattleStart();
    }

    public void onEquip() {}

    public int changeNumberOfCardsInReward(int numberOfCards) {
        if(AbstractDungeon.floorNum == 0){
            return numberOfCards;
        }
        else {
            return numberOfCards;
        }
    }

    public void atTurnStart() {


    }
    public  void  onPlayerEndTurn(){

    }
}

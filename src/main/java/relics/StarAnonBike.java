package relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class StarAnonBike extends CustomRelic   implements CustomSavable<Integer> {

    public static final String ID = "StarAnonBike";
    private static final String IMG = "img/relics/StarAnonBike.png";
    private static final String IMG_OTL =  "img/relics/StarAnonBike.png";
    private Integer index;

    public StarAnonBike() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new StarAnonBike();
    }

    public void onRightClick() {

    }
    public void onEnterRoom(AbstractRoom room) {

    }
    public void usedUp() {
        super.usedUp();
        this.setCounter(-2);
    }
    @Override
    public void onEquip() {
        super.onEquip();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public Integer onSave() {
        if(this.usedUp){
            return 1;
        }
        return 0;
    }
    @Override
    public void onLoad(Integer integer) {
        if(integer ==  1){
            this.usedUp();
        }
    }

    public void useAgain() {
            this.usedUp = false;
            this.setCounter(-1);
            this.description =  this.DESCRIPTIONS[0];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));

    }
}

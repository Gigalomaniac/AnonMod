package relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
import star.BeyondTheStar;

public class StarAnonPocketWatch extends CustomRelic {

    public static final String ID = "StarAnonPocketWatch";
    private static final String IMG = "img/relics/PocketWatch.png";
    private static final String IMG_OTL =  "img/relics/PocketWatch.png";
    private Integer index;

    public StarAnonPocketWatch() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new StarAnonPocketWatch();
    }

    //右键使用
    public void onRightClick() {

    }

    @Override
    public void onEquip() {
        super.onEquip();
    }

    @Override
    public void update() {
        super.update();
    }

}

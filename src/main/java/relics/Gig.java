package relics;

import actions.movie.StarAnonGuitarAction;
import basemod.abstracts.CustomSavable;
import bossRoom.effect.LatterEffect;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
import star.BeyondTheStar;

public class Gig extends ClickableRelic {

    public static final String ID = "Gig";
    private static final String IMG = "img/relics/image.png";
    private static final String IMG_OTL =  "img/relics/image.png";
    private Integer index;

    public Gig() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Gig();
    }

    //右键使用
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                AbstractDungeon.actionManager.addToTop(new InstantKillAction(m));

            }
            this.counter++;
        }
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.counter = 0;
    }

    @Override
    public void update() {
        super.update();
    }

}

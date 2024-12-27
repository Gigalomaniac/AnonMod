package ExcessiveFantasy;

import ExcessiveFantasy.diag.ExcessiveFantasyOne;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ExcessiveFantasyFirstEventRoom extends AbstractRoom {

    public  boolean done = false;
    public ExcessiveFantasyFirstEventRoom() {
//        this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
        this.phase =AbstractRoom.RoomPhase.INCOMPLETE;
        this.mapSymbol = "?";
        this.mapImg = ImageMaster.MAP_NODE_EVENT;
        this.mapImgOutline = ImageMaster.MAP_NODE_EVENT_OUTLINE;
        done = false;
    }

    public void onPlayerEntry() {
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        CardCrawlGame.fadeIn(1.0F);
        CardCrawlGame.music.playTempBgmInstantly("03_Normal.mp3", true);
        AbstractDungeon.topLevelEffectsQueue.add(new ExcessiveFantasyOne(0, 15));
    }
    public void update(){
        if (done){
            AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
            AbstractDungeon.dungeonMapScreen.open(false);
            done = false;
        }
    }
    public void render(SpriteBatch sb){
//        super.render(sb);
    }
}
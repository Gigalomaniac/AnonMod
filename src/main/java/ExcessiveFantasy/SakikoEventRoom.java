package ExcessiveFantasy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

public class SakikoEventRoom extends AbstractRoom {

    MapRoomNode currNode;
    MapRoomNode node;
    public String setcase;
    public SakikoEventRoom(MapRoomNode enemyNode,String setcase) {
        this.phase = RoomPhase.EVENT;
        this.mapSymbol = "?";
        this.mapImg = ImageMaster.MAP_NODE_EVENT;
        this.mapImgOutline = ImageMaster.MAP_NODE_EVENT_OUTLINE;
        this.setcase=setcase;
        node = enemyNode;
    }

    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
        this.currNode = AbstractDungeon.getCurrMapNode();
        this.node.setRoom(new EventRoom() {
            public void onPlayerEntry() {
                AbstractDungeon.overlayMenu.proceedButton.hide();
                switch (setcase){
                    case "shop":
                        this.event = new SakikoShop();
                        break;
                    case "TheFinSakikoShop":
                        this.event = new TheFinSakikoShop();
                        break;
                    case "ExcessiveFantasyAwake":
                        this.event = new ExcessiveFantasyAwake();
                        break;
                    default:
                        this.event = new SakikoShop();
                        break;
                }
                this.event.onEnterRoom();

            }

        }
        );


        AbstractDungeon.dungeonMapScreen.dismissable = true;
        AbstractDungeon.nextRoom = this.node;
        AbstractDungeon.setCurrMapNode(this.node);
        AbstractDungeon.getCurrRoom().onPlayerEntry();
        AbstractDungeon.scene.nextRoom(this.node.room);
        switch (setcase) {
            case "TheFinSakikoShop":
                this.node.room.setMapImg(ImageMaster.loadImage("img/UI/FamilyMart.png"), ImageMaster.loadImage("img/UI/FamilyMart.png"));
                break;
        }
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp) {
            this.event.update();
        }

        if (this.event.waitTimer == 0.0F && !this.event.hasFocus && this.phase != RoomPhase.COMBAT) {
            this.phase = RoomPhase.COMPLETE;
            this.event.reopen();
        }

    }

    public void render(SpriteBatch sb) {
        if (this.event != null) {
            this.event.render(sb);
        }

        super.render(sb);
    }

    public void renderAboveTopPanel(SpriteBatch sb) {
        super.renderAboveTopPanel(sb);
        if (this.event != null) {
            this.event.renderAboveTopPanel(sb);
        }

    }
}

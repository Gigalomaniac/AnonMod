package relics;

import basemod.eventUtil.EventUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import events.AnondeterminationEvent;
import events.BandFriendsEvent;

public class AnonDeterminationCampfire extends AbstractCampfireOption {

    private static final UIStrings uiStrings = null;
    public static final String[] TEXT = new String[0];

    private int choice;

    public static String eventName = null;
    private smartPhone invitation;

    public AnonDeterminationCampfire(boolean active, smartPhone invitation) {
        this.label = "It's MyGO!";
        this.usable = active;
//        this.description = active ? TEXT[1] : TEXT[2];
        if (Settings.language == Settings.GameLanguage.ZHS){
            this.description = "会发生什么呢？";
        }else {
            this.description = "What will you find？";
        }

        this.img = ImageMaster.loadImage("img/event/mygo_115.png");
        this.invitation =invitation;
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new AbstractGameEffect(){
                @Override
                public void update() {
                    isDone = true;
                    invitation.colosseum = true;
                    invitation.usedUp();
                    if (eventName == null) {
                        String dungeonID = AbstractDungeon.id;
//                        eventName = ColosseumSE.GetID(dungeonID);
                    }
                    boolean keyNotExisted;
                    try {
                        AbstractEvent event = EventHelper.getEvent(eventName);
                        keyNotExisted = event == null;
                    } catch (Exception e) {
                        keyNotExisted = true;
                    }
                    if (keyNotExisted) {
//                        King.Log("No event associated with event key [" + eventName + "]");
                    }
                    RoomEventDialog.optionList.clear();
//                    AbstractDungeon.eventList.add(0, eventName);
//                    King.Log("Adding event key to event list: " + AbstractDungeon.eventList.get(0));
                    MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
                    MapRoomNode node = new MapRoomNode(currNode.x, currNode.y);
                    node.setRoom(!keyNotExisted ? new BandRoom() : new BandRoom(){
                        @Override
                        public void onPlayerEntry() {
                            AbstractDungeon.overlayMenu.proceedButton.hide();
                            event = EventUtils.getEvent(eventName);
                            if (event == null) event = new AnondeterminationEvent();
                            event.onEnterRoom();
                        }
                    });
                    for (MapEdge e : currNode.getEdges()) {
                        node.addEdge(e);
                    }
                    AbstractDungeon.player.getRelic("smartPhone").flash();
                    (AbstractDungeon.player.getRelic("smartPhone")).counter++;
                    AbstractDungeon.previousScreen = null;
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.dungeonMapScreen.closeInstantly();
                    AbstractDungeon.closeCurrentScreen();
                    AbstractDungeon.topPanel.unhoverHitboxes();
                    AbstractDungeon.fadeIn();
                    AbstractDungeon.topLevelEffects.clear();
                    AbstractDungeon.topLevelEffectsQueue.clear();
                    AbstractDungeon.effectsQueue.clear();
                    AbstractDungeon.dungeonMapScreen.dismissable = true;
                    AbstractDungeon.nextRoom = node;
                    AbstractDungeon.setCurrMapNode(node);
                    AbstractDungeon.getCurrRoom().onPlayerEntry();
                    AbstractDungeon.scene.nextRoom(node.room);
                    AbstractDungeon.rs = (node.room.event instanceof AbstractImageEvent) ? AbstractDungeon.RenderScene.EVENT : AbstractDungeon.RenderScene.NORMAL;
                }
                @Override
                public void render(SpriteBatch spriteBatch) {

                }

                @Override
                public void dispose() {

                }

//    private void reward(int num) {
//        (AbstractDungeon.getCurrRoom()).rewards.clear();
//        for (int i = 0; i < num; i++)
//            AbstractDungeon.getCurrRoom().addCardReward(new RewardItem(AbstractCard.CardColor.COLORLESS));
//        (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
//        AbstractDungeon.combatRewardScreen.open();
//        this.screen = CurScreen.LEAVE;
//    }

            });
        }
    }
}
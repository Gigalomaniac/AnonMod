package relics;

import Mod.AnonMod;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.core.Settings;
import basemod.eventUtil.EventUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.events.city.Beggar;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;
import events.AnonAwake;
import events.BandFriendsEvent;
import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;

import static basemod.BaseMod.logger;

public class smartPhoneSearchNumbers extends AbstractCampfireOption {

    public static String eventName = null;
    private static smartPhone invitation;

    public smartPhoneSearchNumbers(boolean active,smartPhone invitation) {
        if (Settings.language == Settings.GameLanguage.ZHS){
            this.label = "招募队员";
            this.description = "会遇到谁呢？";
        }else {
            this.label = "recruit band members";
            this.description = "What will you find？";
        }

        this.usable = active;
//        this.description = active ? TEXT[1] : TEXT[2];

        this.img = ImageMaster.loadImage("img/event/tex_tiket_star5_L_114.png");
        this.invitation =invitation;
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new AbstractGameEffect(){
                @Override
                public void update() {
                    ++AbstractDungeon.player.getRelic("smartPhone").counter;
                    isDone = true;
                    invitation.colosseum = true;
                    invitation.usedUp();
                    if (eventName == null) {
                        String dungeonID = AbstractDungeon.id;
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
                    MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
                    currNode.setRoom(!keyNotExisted ? new BandRoom() : new BandRoom(){
                        @Override
                        public void onPlayerEntry() {
                            AbstractDungeon.overlayMenu.proceedButton.hide();
                            event = EventUtils.getEvent(eventName);
                            if (event == null) event = new BandFriendsEvent();
                            event.onEnterRoom();
                        }
                    });
                    for (MapEdge e : currNode.getEdges()) {
                        currNode.addEdge(e);
                    }
                    AbstractDungeon.player.getRelic("smartPhone").flash();

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
                    AbstractDungeon.nextRoom = currNode;
                    AbstractDungeon.setCurrMapNode(currNode);
                    AbstractDungeon.getCurrRoom().onPlayerEntry();
                    AbstractDungeon.scene.nextRoom(currNode.room);
                    AbstractDungeon.rs = (currNode.room.event instanceof AbstractImageEvent) ? AbstractDungeon.RenderScene.EVENT : AbstractDungeon.RenderScene.NORMAL;
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


    public static void test() {
//        if (this.usable) {
            AbstractDungeon.effectList.add(new AbstractGameEffect(){
                @Override
                public void update() {
                    isDone = true;
                    if (eventName == null) {
                        String dungeonID = AbstractDungeon.id;
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
                            if (event == null) event = new AnonAwake();
                            event.onEnterRoom();
                        }
                    });
                    for (MapEdge e : currNode.getEdges()) {
                        node.addEdge(e);
                    }
                    AbstractDungeon.nextRoom = node;
                    AbstractDungeon.setCurrMapNode(node);
                    AbstractDungeon.getCurrRoom().onPlayerEntry();
                    AbstractDungeon.scene.nextRoom(node.room);
                    AbstractDungeon.rs =AbstractDungeon.RenderScene.NORMAL;
//                    (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                    smartPhone smartPhone = new smartPhone();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) smartPhone);
                    if(AnonMod.givekey){
                        AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.RED));
                        AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.BLUE));
                        AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.GREEN));
                    }
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

    public static void boss() {
//        if (this.usable) {
        AbstractDungeon.effectList.add(new AbstractGameEffect(){
            @Override
            public void update() {
                isDone = true;
                if (eventName == null) {
                    String dungeonID = AbstractDungeon.id;
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
                        if (event == null) event = new AnonAwake();
                        event.onEnterRoom();
                    }
                });
                for (MapEdge e : currNode.getEdges()) {
                    node.addEdge(e);
                }
//                    AbstractDungeon.previousScreen = null;
//                    AbstractDungeon.dynamicBanner.hide();
//                    AbstractDungeon.dungeonMapScreen.closeInstantly();
//                    AbstractDungeon.closeCurrentScreen();
//                    AbstractDungeon.topPanel.unhoverHitboxes();
//                    AbstractDungeon.fadeIn();
//                    AbstractDungeon.topLevelEffects.clear();
//                    AbstractDungeon.topLevelEffectsQueue.clear();
//                    AbstractDungeon.effectsQueue.clear();
//                    AbstractDungeon.dungeonMapScreen.dismissable = true;
                AbstractDungeon.nextRoom = node;
                AbstractDungeon.setCurrMapNode(node);
                AbstractDungeon.getCurrRoom().onPlayerEntry();
                AbstractDungeon.scene.nextRoom(node.room);
                AbstractDungeon.rs =AbstractDungeon.RenderScene.NORMAL;
                (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                smartPhone smartPhone = new smartPhone();
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) smartPhone);

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
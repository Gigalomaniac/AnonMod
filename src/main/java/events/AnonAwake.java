package events;

import basemod.eventUtil.EventUtils;
import cards.others.LoveMeIFYouCan;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.events.city.Colosseum;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;
import relics.BandRoom;
import relics.smartPhone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;

import static com.megacrit.cardcrawl.core.CardCrawlGame.saveFile;

public class AnonAwake extends AbstractImageEvent {
//    private static final String ID = King.MakeID(Colosseum.ID);
    public int state = 0;
    MapRoomNode currNode;
    MapRoomNode node;
//    public static String GetID(String dungeonID) {
//        if (RootDepths.ID.equals(dungeonID))
//            dungeonID = CityDepths.ID;
//        return ID.concat("." + dungeonID);
//    }

    public AnonAwake() {
        super("千早爱音的冒险", getBody(), "img/event/AnonThink.png");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.imageEventText.setDialogOption("……（我在哪里）");
        }else {
            this.imageEventText.setDialogOption("……（Where am i）");
        }
//        if (Settings.language == Settings.GameLanguage.ENG){
//            this.imageEventText.setDialogOption("……（Where am i）");
//        }
    }
 public void onEnterRoom() {
     RoomEventDialog.waitForInput = true;
 }
    private static String getBody() {
        String body  ="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
             body = "“爱音……！”耳边还时不时回响着呼唤声（呜……突然，头好痛） NL " +
                    "不知道过去了多久爱音慢慢睁开了眼 NL （糟了，选择几点了？现在几……） NL "+
                    "（这是什么地方……那边有一只……大~鲸~鱼~~？ NL "+
                    "我应该是昨天晚上练 吉他 太拼命了 #y（？） ，在上课途中睡着了……"+" NL "+
                    "晚上还有排练……虽然还只是个无名乐队，晚上还联系不上的话就麻烦了吧……(果然乐队名字还得是Anon Tokyo吧) NL "+
                    "没其他办法了，还是先找找回去的路吧，我总有种不好的预感）";
        }else {
//            if (Settings.language == Settings.GameLanguage.ENG){
                body = "“Anon……!” The echoing call still rings in my ears from time to time (Ugh... suddenly, my head hurts so much) NL " +
                        "I don't know how much time has passed, but Anon slowly opened her eyes NL " +
                        "(Oh no, what time is it now? What day is it...) NL " +
                        "(What is this place... There's a... big~ whale~~ over there? NL " +
                        "I must have practiced the guitar too hard last night #y(?), and fell asleep on my way to class...) NL " +
                        "I have a rehearsal tonight... Although it's still just a nameless band, it would be troublesome if we can't get in touch tonight... (The band name should definitely be Anon Tokyo) NL " +
                        "There's no other way, I'd better find my way back first, I always have a bad feeling)";
//            }
        }


        return body;
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        AbstractEvent event;
        switch (this.state) {
            case 0:
                switch (buttonPressed) {
                    case 0:
//                        openMap();
                        this.state = 1; //1为第二幕。0为第一幕
                        if (Settings.language == Settings.GameLanguage.ZHS) {
                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("“还好 #y吉他 也在身边， #y手机 还在口袋里” NL Anon心里这样想着，拿出手机（怎么没信号啊，聊天记录倒是都还在……），还有三首我们乐队的新歌  NL "
                                    + "“怎么会这样” 原地叹了口气后，背上吉他准备出发了 NL " +
                                    "“刚刚那边有个大鲸鱼好像想给我什么，先去看看吧”  NL 听说有人见过会说话的长颈鹿了，会搭讪的大鲸鱼也没什么奇怪的了……应该吧");
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("启程");
                        }else {
                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("“Thank goodness the #y guitar is still by my side, and the #y smartphone is in my pocket” NL Anon thought to herself, taking out her phone (No signal, but the chat logs are all there...), along with three new songs from our band NL "
                                    +"“Why is this happening?” After sighing in place, she slung the guitar onto her back and prepared to set off NL "
                                    +"“There was a big whale over there that seemed to want to give me something, I'll go check first” NL I've heard tales of giraffes that can talk, so a sociable whale probably isn't too strange either... right?");
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("embark");
                        }
                        return;
                }
                openMap();
                return;
            case 1:
                switch (buttonPressed) {
                    case 0:
                        for (com.megacrit.cardcrawl.vfx.AbstractGameEffect f : AbstractDungeon.effectList) {
                            if ((f instanceof InfiniteSpeechBubble)) {
                                ((InfiniteSpeechBubble) f).dismiss();
                            }
                        }
                        //回到捏奥
                        this.imageEventText.clearAllDialogs();
                        GenericEventDialog.hide();
                        this.currNode = AbstractDungeon.getCurrMapNode();
                        this.node = new MapRoomNode(this.currNode.x, this.currNode.y);
                        this.node.setRoom(new NeowRoom(false) {
                            public void onPlayerEntry() {
                                AbstractDungeon.overlayMenu.proceedButton.hide();
                                this.event = new NeowEvent();
                                Iterator var1 = AbstractDungeon.effectList.iterator();

                                while(var1.hasNext()) {
                                    AbstractGameEffect f = (AbstractGameEffect)var1.next();
                                    if (f instanceof InfiniteSpeechBubble) {
                                        ((InfiniteSpeechBubble)f).dismiss();
                                    }
                                }

                                this.event.onEnterRoom();
                            }
                        });


                        AbstractDungeon.dungeonMapScreen.dismissable = true;
                        AbstractDungeon.nextRoom = this.node;
                        AbstractDungeon.setCurrMapNode(this.node);
                        AbstractDungeon.getCurrRoom().onPlayerEntry();
                        AbstractDungeon.scene.nextRoom(this.node.room);
                        AbstractDungeon.currMapNode.setRoom(new NeowRoom(false));
                }
                return;
        }
        openMap();
    }

    private AbstractEvent getNeowOrHeartEvent() {
        return (AbstractEvent)new NeowEvent();
    }
}

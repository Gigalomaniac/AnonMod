package events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.RestRoom;
import relics.*;

public class ManaAshAnonEvent extends AbstractImageEvent {

    public static String StarAnonEventID = "ManaAshAnonEvent";
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static String title = "";
    public ManaAshAnonEvent() {
        super(title, getBody(), "");
        this.imageEventText.loadImage("img/event/ManaSad.png");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.imageEventText.setDialogOption("“……”");
        }else {
            this.imageEventText.setDialogOption("escape……?");
        }

//        this.imageEventText.loadImage("img/event/Anon3.png");
    }


    private static String getBody() {
        String body = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
             body = "“队长？还好吗？”虽然不知道你现在能不能听到我在说的话，" + " NL " +
                     "我也想就像这样呆在你的身边";
        }else {
             body = "(At the moment of reaching the deepest part, I recalled a question." + " NL " +
                    "Why do I feel like it's the first time I've been here, yet it's so familiar..." + " NL " +
                    "Why can I play the guitar so smoothly along the way, even though I haven't practiced much..." + " NL " +
                    "From the beginning, I didn't belong here, and when everyone came to find me... I also chose to escape";
        }

        return body;
    }

    private enum AllBandFriends {
       all,case1,case2,case3,case4,fin,crychic1,crychic2,crychic3,crychic4,Ash1,Ash2,Ash3,Ash4
    }
    @Override
    protected void buttonEffect(int buttonPressed) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            AbstractEvent event;
            switch (this.BandEvents) {
                case all:
                    switch (buttonPressed) {
                        case 0:
                            this.imageEventText.updateBodyText("“不过呢，队长……爱音。”"+ " NL " +
                                    "虽然不知道这次队长是带着什么目的，或者说为了谁深潜下去的，这次我也会一直陪在这里…………"+
                                    "就算这次是队长所擅长的任性的话，我也会允许的哦。"+ " NL " );
                            this.imageEventText.loadImage("img/event/ManaThink.png");

                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("“…………！！！”");
                            this.BandEvents = AllBandFriends.case1;
                            return;
                    }

                    openMap();
                    return;
                case case1:
                    Mana_relic Mana_relic = new Mana_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Mana_relic);
                    this.imageEventText.loadImage("img/event/ManaHappy.png");
                    this.imageEventText.updateBodyText("“所以，队长。按着你的目标继续努力下去！”"+ " NL " +
                            "无论这趟旅途的终点会是何处，我都会一直陪在队长身边的。" + " NL " +
                            "等你回来，“大家还在RiNG的舞台上等着你……去吧，队长。”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #b“……我出发了!”");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case case2:
                    switch (buttonPressed) {
                        case 0:
//                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("你见到少女的嘴角扬起一丝弧度。但她没有说话，只是将手中的吉他塞到了你的怀里，随后潇洒地离开了。这真奇怪……她是什么时候拿走的？（获得？爱音的吉他）");
                            if(AbstractDungeon.player.hasRelic(Guitar.ID)){
                                AbstractDungeon.player.loseRelic(Guitar.ID);
                            }
                            StarAnonGuitar StarAnonGuitar = new StarAnonGuitar();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) StarAnonGuitar);
                            this.BandEvents = AllBandFriends.fin;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("（……）");
                            return;
                        case 1:
//                        this.imageEventText.loadImage("img/event/AnonThink2.png");
                        this.imageEventText.updateBodyText("她没有多说什么，一言不发地离开了。");
                        this.BandEvents = AllBandFriends.fin;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption("（……）");
                        return;
                    }
                case fin:
//                this.imageEventText.clearAllDialogs();
                    openMap();

            }
        }else {
            AbstractEvent event;
            switch (this.BandEvents) {
                case all:
                    switch (buttonPressed) {
                        case 0:
                            this.imageEventText.updateBodyText(
                                    "The past... the present... the future... " + "NL "
                                            + "Here, it seems there is no concept of time; my consciousness has gone through countless cycles here." + "NL "
                                            + "This place is already the deepest part of the tower, terrifyingly quiet... Fireflies in the distance emit a faint light... Leading to the deepest end." + "NL "
                                            + "In the distance, there is only unfathomable darkness left, and it's time for me to make the #bfinal choice" + "NL "
                                            + " #rDecide your band's name");

                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("Mygo!!!!! #b(Enter the special ending)");
                            this.imageEventText.setDialogOption("Anon Tokyo #b(Continue to battle the heart)");
                            if (AbstractDungeon.player.hasRelic(Sakiko_relic.ID)) {
                                this.imageEventText.setDialogOption("Crychic #b(She once helped you)", false);
                            } else {
                                this.imageEventText.setDialogOption("Crychic #b(She once helped you)", true);
                            }
                            if (AbstractDungeon.player.hasRelic(Rana_relic.ID)) {
                                this.imageEventText.setDialogOption("#rInner #rFavillae (The price you once paid for this)", false);
                            } else {
                                this.imageEventText.setDialogOption("#rInner #rFavillae (The price you once paid for this)", true);
                            }

                            // Commented out line, presumably not needed in the current context
                            // this.imageEventText.setDialogOption("... Is there really any meaning in pondering all of this?");

                            this.BandEvents = AllBandFriends.case4;
                            return;
                    }
                    openMap();
                    return;
                case case1:
                    this.imageEventText.updateBodyText("(Really... Is there still a place that needs me?" + "NL "
                            + "Clearly, I was so helpless after that disbandment... Damn it, why can't I shed a tear even though I'm so sad?" + "NL "
                            + "That day on the bridge, I seemed to see... I wanted to reach out and touch, but I accidentally... #rfell down" + "NL "
                            + "During the fall, I seemed to see Shuangshi, Xiao Deng, Li Xi, and... Are they here to find me... I'm actually quite afraid of pain... ");

                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/hunmanBridge.png");
                    this.imageEventText.setDialogOption("(Does pondering all of this really have any meaning?)");
                    this.BandEvents = AllBandFriends.case2;
                    return;
                case case2:
                    this.imageEventText.updateBodyText("(Where am I now in reality..." + "NL "
                            + "Wait, this is...!" + "NL "
                            + "When did I have #ythis?)" + "NL "
                            + "I have never had it before, holding it feels as if they are by my side." + "NL "
                            + "‘Is this what you left for me, I understand...’");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("获得 #b“MyGO!!!!!的队徽” ");
                    this.imageEventText.loadImage("img/event/AnonThink2.png");

                    this.BandEvents = AllBandFriends.case3;
                    return;
                case case3:
                    this.imageEventText.updateBodyText("Thank you all..." + "NL "
                            + "Although I can't clearly remember every step you have accompanied me through... The songs that resonated in memory have given me strength, allowing me to reach the last step" + "NL "
                            + " #yTomorin... Soyorin... Rikki... Rana... Sakki... Mutsumin... " + "NL "
                            + "I set off, this time it's..." + "NL "
                            + " #bMyMygo!");
//                    Anon_determination Anon_determination = new Anon_determination();
//                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Anon_determination);
                    this.imageEventText.loadImage("img/bandFriends/Anon4.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #Let's Go！");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case case4:
                    switch (buttonPressed) {
                        case 0:
                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("Thank you all... I remembered..." + "NL "
                                    + "(How long have I been wandering here...)" + "NL "
                                    + "Just as the thought arose, Ai Yin shuddered, not daring to think further..." + "NL "
                                    + "(If I leave, what will happen to Crychic... Before leaving, they also said, #bMygo!!!!! will not disband because of this, but..." + "NL "
                                    + "If I return to Ring, will there still be someone waiting for me... I didn't do anything wrong, so why don't I want to go back..." + "NL "
                                    + "I really don't want to leave, if I'm unlucky and have become an old lady, they will tease me when we meet again.)");
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("（……）");
                            return;
                        case 1:
                            MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
                            MapRoomNode node = new MapRoomNode(currNode.x, currNode.y);
                            node.setRoom(new RestRoom());
                            for (MapEdge e : currNode.getEdges()) {
                                node.addEdge(e);
                            }
                            AbstractDungeon.nextRoom = node;
                            AbstractDungeon.setCurrMapNode(node);
                            AbstractDungeon.getCurrRoom().onPlayerEntry();
                            AbstractDungeon.scene.nextRoom(node.room);
//                        AbstractDungeon.rs =AbstractDungeon.RenderScene.valueOf("");
//                        (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
////                        CardCrawlGame.dungeon = new TheEnding(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                            CardCrawlGame.music.fadeOutBGM();
                            CardCrawlGame.music.fadeOutTempBGM();
                            AbstractDungeon.fadeOut();
                            AbstractDungeon.topLevelEffects.clear();
                            AbstractDungeon.actionManager.actions.clear();
                            AbstractDungeon.effectList.clear();
                            AbstractDungeon.effectsQueue.clear();
////                        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                            AbstractDungeon.dungeonMapScreen.open(true);
//                        AbstractDungeon.floorNum = AbstractDungeon.floorNum
//                        openMap();
                            return;
                        case 2:

                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("Why, why did I mention this name..." + "NL "
                                    + "(Right, I came to this tower to... Damn, my head hurts so much)" + "NL "
                                    + "This is already the deepest part of the tower, quiet as if every breath could be heard echoing... But somehow, everything around seems so familiar." + "NL "
                                    + "(I seem to have come here to find #bsomeone?)");
                            this.imageEventText.loadImage("img/event/AnonThink.png");
                            this.BandEvents = AllBandFriends.crychic1;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("“Kuang~……！”");
                            return;
                        case 3:
                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("Wait, why do I think of this name..." + "NL "
                                    + "(Damn, my head hurts so much, although I can't remember anything, there is a feeling of sadness... It seems like I have lost something before.)");
                            this.imageEventText.loadImage("img/event/AnonThink.png");
                            this.BandEvents = AllBandFriends.Ash1;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("“……！”");
                            return;
                    }
                case crychic1:
//                    Crychic crychic = new Crychic();
//                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) crychic);
                    this.imageEventText.updateBodyText(
                            "(Is this... a small badge with #bcrychic written on it?)" + " NL "
                                    + "Wait, I remember now... #yI came to the tower for this, not actually to find myself." + " NL "
                                    + "This was given to me by #ythem before I came." + " NL "
                                    + "I heard them say that this is a memory they can't forget or let go, although I don't quite understand...");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/AnonThink2.png");
                    this.imageEventText.setDialogOption("(Wait, come here?)");
                    this.BandEvents = AllBandFriends.crychic2;
                    return;
                case crychic2:
                    this.imageEventText.updateBodyText(
                            "(I remember now, this is the tower, a program built to recover #bsomeone's memory)" + " NL "
                                    + "The encounters and adventures with them before were all pre-set memories to guide me through the process." + " NL "
                                    + "“The lost child, from beginning to end, was not #yI, but #yYOU... right?”" + " NL "
                                    + "For this, we built this system, and I infiltrated to find the reason why you don't want to wake up.");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/AnonThink2.png");
                    this.imageEventText.setDialogOption("！");
                    this.BandEvents = AllBandFriends.crychic3;
                    return;
                case crychic3:
                    this.imageEventText.updateBodyText(
                            "You must be watching from outside now," + " NL "
                                    + "#yTomorin... Soyorin... Rikki... Rana... Mutsumin..." + " NL "
                                    + "Thank you all, I have come this far now."
                                    + "Now I am on " + AbstractDungeon.floorNum + " floor, about to reach the deepest part, the last room" + " NL "
                                    + "Although there is still some distance, I can feel it, #bYOU must be there." + " NL "
                                    + "I'm sorry I'm late, I'll take you home right away.");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/AnonThink2.png");
                    this.imageEventText.loadImage("img/bandFriends/Anon4.png");
                    this.imageEventText.setDialogOption("“Wait For Me”");
                    this.BandEvents = AllBandFriends.crychic4;
                    return;
                case crychic4:
                    this.imageEventText.updateBodyText(
                            "This is a story that we started together" + " NL "
                                    + "Now all the #ykeys have been collected. I'm coming to find you," + " NL "
                                    + " #bSakiko, wait for me ");
                    this.imageEventText.loadImage("img/bandFriends/Anon4.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #bLet's Go！");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case Ash1:
                    this.imageEventText.updateBodyText(
                            "Opened my eyes, the fire did not bring a trace of warmth, an unprecedented cold covered everything." + " NL "
                                    + "“My head is dizzy, wait, when did I wear this?");
//                    Inner Inner = new Inner();
//                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Inner);
                    this.imageEventText.loadImage("img/event/AnonThink.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" A badge worn on the chest ");
                    this.BandEvents = AllBandFriends.Ash2;
                    return;
                case Ash2:
                    this.imageEventText.updateBodyText(
                            "The moment I touched it, memories flooded in like a tide, there was never a warm band, only my own despair, and a fragile heart." + " NL "
                                    + "“Is this me?” This question, like a sharp blade, repeatedly slashed across the last of the heart, each stroke made me suffer unbearably." + " NL "
                                    + "Fear is like an invisible net, tightly wrapping me in the endless darkness. “Do I really want to go back?”"
                                    + "Once ignorant of the past, the subversion of self-awareness along the way makes me feel as if I am standing on the edge of a cliff, ready to fall into an abyss from which there is no return.” + "+ " NL " +
                        "Every flash of memory is like a torment of my soul, a wordless pain." + " NL "
                        + "Who is here now, is it #bME?");
                    this.imageEventText.loadImage("img/event/Anon3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #r……");
                    this.BandEvents = AllBandFriends.Ash3;
                    return;
                case Ash3:
                    this.imageEventText.updateBodyText("Or #rMe ? ");
                    this.imageEventText.loadImage("img/event/AshAnonThink.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" ?！");
                    this.BandEvents = AllBandFriends.Ash4;
                    return;
                case Ash4:
                    this.imageEventText.updateBodyText("From the moment of choice, fate was sealed. It's time to set off...");
                    this.imageEventText.loadImage("img/event/Anon3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" leave……");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case fin:
//                this.imageEventText.clearAllDialogs();
                    openMap();

            }
        }
    }

    private static String friendsName() {
        String name = "";
        if (AbstractDungeon.player.hasRelic("Tomori_relic")) {
            name = name + " #y小灯， ";
        }
        if (AbstractDungeon.player.hasRelic("Soyorin_relic")) {
            name = name + "  #y爽世， ";
        }
        if (AbstractDungeon.player.hasRelic("Rikki_relic")) {
            name = name + "  #y立希， ";
        }
        if (AbstractDungeon.player.hasRelic("Rana_relic")) {
            name = name + "  #y乐奈， ";
        }
        if (AbstractDungeon.player.hasRelic("Sakiko_relic")) {
            name = name + "  #y小祥， ";
        }
        if (AbstractDungeon.player.hasRelic("Mutsumi_relic")) {
            name = name + "  #y小睦， ";
        }
        return  name.substring(0,name.length());
    }

    static {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "少女们的逆行";
        }else {
            title = "All The End";
        }
    }
}

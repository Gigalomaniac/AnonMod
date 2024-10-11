package events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import relics.*;

import java.util.*;

public class AnondeterminationEvent extends AbstractImageEvent {
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static String title = "";
    public AnondeterminationEvent() {
        super(title, getBody(), "");

        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.imageEventText.setDialogOption("逃避……吗");
        }else {
            this.imageEventText.setDialogOption("escape……?");
        }

        this.imageEventText.loadImage("img/event/Anon3.png");
    }


    private static String getBody() {
        String body = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
             body = "（抵达最深处的那一刻，我回想起一个问题。" + " NL " +
                    "为什么我感觉是第一次来到这里，却又那么熟悉……" + " NL " +
                    "为什么明明没怎么练习过吉他，一路上能这么流畅的演奏出它们……" + " NL " +
                    "从一开始我就不属于这里，大家来找我的时候……我也选择了逃避";
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
                            this.imageEventText.updateBodyText("过去……现在……未来…… " + " NL "
                                    + "这里似乎没有时间这个概念，我的意识也已经在这里经历了多少个轮回。" + " NL "
                                    +
                                    "这里已经是塔的最深处了，安静的可怕……远处的萤火虫散发着微弱的光……通往终局的最深处" + " NL " +
                                    "远程只剩下深不见底的黑暗，我也该做出 #b最后的选择 了" + " NL " +
                                    " #r决定你的乐队名称");

                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("Mygo!!!!! #b（进入特殊结局） ");
                            this.imageEventText.setDialogOption("Anon Tokyo #b（继续对战心脏） ");
                            if (AbstractDungeon.player.hasRelic(Sakiko_relic.ID)) {
                                this.imageEventText.setDialogOption("Crychic #b（她曾经帮助了你） ", false);
                            } else {
                                this.imageEventText.setDialogOption("Crychic #b（她曾经帮助了你） ", true);
                            }
                            if (AbstractDungeon.player.hasRelic(Rana_relic.ID) || AbstractDungeon.player.hasRelic(Mana_relic.ID)) {
                                this.imageEventText.setDialogOption("#rInner #rFavillae（你曾为此付出的代价） ", false);
//                            this.imageEventText.setDialogOption("Crychic #b（她曾经帮助了你） ", false);
                            } else {
                                this.imageEventText.setDialogOption("#rInner #rFavillae（你曾为此付出的代价） ", true);
//                            this.imageEventText.setDialogOption("Crychic #b（她曾经帮助了你） ",true);
                            }

//                            this.imageEventText.setDialogOption("……思考这一切，真的还有意义吗？");
                            this.BandEvents = AllBandFriends.case4;
                            return;
                    }
//                "每个轮回中抵达这里时，我都会抱着吉他独自思考，看不清头顶是否存在的星空……"+" NL “如果他们在那个时候没有选择Crychic，而是选择了自己的话，我又是否会来到这里呢……”"+" NL "+
                    openMap();
                    return;
                case case1:
                    this.imageEventText.updateBodyText("（真的……还有哪里会需要我吗" + " NL "
                            + "明明那次解散后，我是这么的无助……可恶啊，为什么我明明那么难过，却流不出一滴眼泪" + " NL " +
                            "那天在桥上，我好像看到了……想要伸手去触碰，却不小心…… #r摔了下去 " + " NL " +
                            "坠落过程中，我好像看到了爽世，小灯，立希，还有……她们是来找我的吗……我其实还挺怕痛的……");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/hunmanBridge.png");
                    this.imageEventText.setDialogOption("（思考这一切，真的还有意义吗？）");
                    this.BandEvents = AllBandFriends.case2;
                    return;
                case case2:
                    this.imageEventText.updateBodyText("（现实中的我现在会在哪里呢……" + " NL " +
                            "等等，这是……！" + " NL " +
                            "什么时候，为什么我会有 #y这个 ？）" + " NL " +
                            "之前从来没有得到过，握着它的时候仿佛她们就在我的身边。" + " NL " +
                            "“这是你们留给我的吗，我懂了……”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("获得 #b“MyGO!!!!!的队徽” ");
                    this.imageEventText.loadImage("img/event/AnonThink2.png");

                    this.BandEvents = AllBandFriends.case3;
                    return;
                case case3:
                    this.imageEventText.updateBodyText(
                            "谢谢你们……" + " NL " +
                                    "虽然我不能清晰的记起一路上大家陪我走过的每一步……但是回忆中同声相应的歌曲给了我力量，让我走到了最后一步" + " NL "
                                    + " #yTomorin……Soyorin……Rikki……Rana……Sakki……Mutsumin…… " + " NL " +
                                    "我出发了，这次是……" + " NL " +
                                    " #b我的Mygo！ ");
                    Anon_determination Anon_determination = new Anon_determination();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Anon_determination);
                    this.imageEventText.loadImage("img/bandFriends/Anon4.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #b出发！");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case case4:
                    switch (buttonPressed) {
                        case 0:
                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("谢谢你们……我想起来了……" + " NL " +
                                    "（我在这……踯躅彷徨多久了呢……）" + " NL 一念刚起，爱音打了个寒颤，不敢再深思下去……" + " NL "
                                    + "(如果我出去的话，Crychic会变成什么样子呢……离开前她们也说过， #bMygo！！！！！不会因此解散 ，但是……" + " NL " +
                                    "如果我回到Ring，还会有人在等我吗……明明我什么都没有做错，为什么这么不想回去呢……" + " NL " +
                                    "真不想离开呀，要是运气不好，已经变成了老婆婆，再见面会被她们笑话吧）");
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("（……）");
                            return;
                        case 1:
                            openMap();
                            this.BandEvents = AllBandFriends.fin;
//                            MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
                            return;
                        case 2:

                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("为什么，为什么我会提到这个名字……" + " NL " +
                                    "（对了，我来到这个尖塔是为了……可恶头好痛）" + " NL 这里已经是塔的最深处了，安静的仿佛每一声呼吸都能听到回音……但是冥冥中，周遭的一切又似乎是那么的熟悉。" + " NL "
                                    + "(我好像，来到这里是为了找 #b一个人 ?）");
                            this.imageEventText.loadImage("img/event/AnonThink.png");
                            this.BandEvents = AllBandFriends.crychic1;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("“咣当……！”");
                            return;
                        case 3:
                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText("等等，为什么我会想到这个名字……" + " NL " +
                                    "（可恶头好痛，虽然还什么都想不起来，但是有一种悲伤的感觉…………好像，曾经失去过什么）");
                            this.imageEventText.loadImage("img/event/AnonThink.png");
                            this.BandEvents = AllBandFriends.Ash1;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption("“……！”");
                            return;
                    }
                case crychic1:
                    Crychic crychic = new Crychic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) crychic);
                    this.imageEventText.updateBodyText("（这是……上面写着 #bcrychic 的小徽章？" + " NL " +
                            "等等，我想起来了…… #y我为此来到尖塔 ，其实不是为了寻找我。" + " NL " +
                            "这是我来之前， #y她们 最后交给我的。" + " NL " +
                            "听她们说，这是她们无法忘却也无法放下的回忆，虽然我不是很懂啦……");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/AnonThink2.png");
                    this.imageEventText.setDialogOption("（等等，来这里？）");
                    this.BandEvents = AllBandFriends.crychic2;
                    return;
                case crychic2:
                    this.imageEventText.updateBodyText("（我想起来了，这里是尖塔，是为了找回 #b某个人的记忆 而搭建的程序" + " NL " +
                            "之前与她们的相遇与冒险，都是为了在过程中引导我，而预先设置好的记忆。" + " NL " +
                            "“那个迷路的孩子，由始至终都不是 #y我 ，而是 #y你 吗……”" + " NL " +
                            "为此，我们搭建了这套系统，并由我潜入来寻找你不愿醒来的原因。");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/AnonThink2.png");
                    this.imageEventText.setDialogOption("！");
                    this.BandEvents = AllBandFriends.crychic3;
                    return;
                case crychic3:
                    this.imageEventText.updateBodyText("" +
                            "你们现在一定在外面看着吧，" + " NL " +
                            " #yTomorin……Soyorin……Rikki……Rana……Mutsumin…… " + " NL " +
                            "谢谢你们，我现在已经走到这一步了。" +
                            "现在我处于" + AbstractDungeon.floorNum + "层，即将抵达最深处，最后的房间了" + " NL " +
                            "虽然还有点距离，但我能感觉的到， #b你 肯定就在那里。" + " NL " +
                            "对不起，我来晚了，马上就接你回家。");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/AnonThink2.png");
                    this.imageEventText.loadImage("img/bandFriends/Anon4.png");
                    this.imageEventText.setDialogOption("“等我”");
                    this.BandEvents = AllBandFriends.crychic4;
                    return;
                case crychic4:
                    this.imageEventText.updateBodyText("这是由你和我共同开启的故事" + " NL " +
                            "现在所有的 #y钥匙 都已经集齐了。我来找你了，" + " NL " +
                            " #b祥子同学,等我 ");
                    this.imageEventText.loadImage("img/bandFriends/Anon4.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #b出发！");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case Ash1:
                    this.imageEventText.updateBodyText("睁开了眼睛，火堆并未带来一丝温暖，前所未有的寒冷覆盖了全部。" + " NL " +
                            "“脑袋晕乎乎的，等下这个是什么时候佩戴在我身上的？”");
                    Inner Inner = new Inner();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Inner);
                    this.imageEventText.loadImage("img/event/AnonThink.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #r胸口佩戴的徽章 ");
                    this.BandEvents = AllBandFriends.Ash2;
                    return;
                case Ash2:
                    this.imageEventText.updateBodyText("触碰到的瞬间记忆如潮水般涌来，从来没有温馨的乐队，只剩下绝望我自己，和脆弱不堪的心脏。" + " NL " +
                            "“这就是我？”这个问题，如同一把锋利的利刃，在心脏最后的上反复划过，每一刀都让我痛不欲生。" + " NL " +
                            "恐惧像一张无形的网，在无尽的黑暗中将我紧紧包裹。“我真的想要回去吗？”" +
                            "曾经对过去的无知，对自我认知中一路上冒险的颠覆，让我感觉自己仿佛站在了悬崖边缘，随时可能坠入万劫不复的深渊。" + " NL " +
                            "每一次回忆的闪现，都像是在拷问着我的灵魂，无言之痛。" + " NL " +
                            "现在在这里的到底是谁,是 #b 我?");
                    this.imageEventText.loadImage("img/event/Anon3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" #r……");
                    this.BandEvents = AllBandFriends.Ash3;
                    return;
                case Ash3:
                    this.imageEventText.updateBodyText("还是 #r我 ? ");
                    this.imageEventText.loadImage("img/event/AshAnonThink.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" ?！");
                    this.BandEvents = AllBandFriends.Ash4;
                    return;
                case Ash4:
                    this.imageEventText.updateBodyText("从选择的那一刻起,命运已经注定.该出发了……");
                    this.imageEventText.loadImage("img/event/Anon3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(" 离开……");
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case fin:
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
                    Anon_determination Anon_determination = new Anon_determination();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Anon_determination);
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
                    Crychic crychic = new Crychic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) crychic);
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
                    Inner Inner = new Inner();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Inner);
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
            title = "招募乐队成员";
        }else {
            title = "All The End";
        }
    }
}

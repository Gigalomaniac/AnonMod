package events;

import bandFriendsCard.*;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.qingmu.sakiko.relics.menbers.Anon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;
import relics.*;
import sakikoMod.receiveRelic;
import ui.SkinSelectScreen;

import java.util.*;

public class BandFriendsEvent extends AbstractImageEvent {

    public int state = 0;
    public int BandNumbers = 0;

    // 用于记录所有曾经使用过的随机数，防止重复
    private static Set<Integer> allUsedNumbers = new HashSet<>(); //这是可用的数池子
    //     记录每组序列的第一个数字，用于后续序列生成时排除 1 Tomori  2 Soyorin  3 Rikki  4 Rana  5 Sakiko 6 Mutsumi
    private static List<Integer> prevFirstNumbers = new ArrayList<>();
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static String title = "";
    private String selectionBandNumber1 = "";
    private String selectionBandNumber2 = "";
    private String selectionBandNumber3 = "";
    public BandFriendsEvent() {
        super(title, getBody(), "img/event/AnonThink.png");
        allUsedNumbers = new HashSet<>();
        if (AbstractDungeon.player.hasRelic("Tomori_relic")) {
            allUsedNumbers.add(1);
            System.out.println("移除了allUsedNumbers.add(1);");
        }
        if (AbstractDungeon.player.hasRelic("Soyorin_relic")) {
            allUsedNumbers.add(2);
            System.out.println("移除了allUsedNumbers.add(2);");
        }
        if (AbstractDungeon.player.hasRelic("Rikki_relic")) {
            allUsedNumbers.add(3);
            System.out.println("移除了allUsedNumbers.add(3);");
        }
        if (AbstractDungeon.player.hasRelic("Rana_relic")) {
            allUsedNumbers.add(4);
            System.out.println("移除了allUsedNumbers.add(4);");
        }
        if (AbstractDungeon.player.hasRelic("Sakiko_relic")) {
            allUsedNumbers.add(5);
            System.out.println("移除了allUsedNumbers.add(5);");
        }
        if (AbstractDungeon.player.hasRelic("Mutsumi_relic")) {
            allUsedNumbers.add(6);
            System.out.println("移除了allUsedNumbers.add(6);");
        }
        List<Integer> currentSequence = generateUniqueSequence(BandNumbers + 1, 3, 1, 6);
        // 输出当前序列
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        //选项1
        if (Settings.language == Settings.GameLanguage.ZHS) {
            if (currentSequence.get(0) == 1) {
                this.imageEventText.setDialogOption("联系 高松灯",new Tomori(),new Tomori_relic());
                this.selectionBandNumber1 = "Tomori";
            }
            if (currentSequence.get(0) == 2) {
                this.imageEventText.setDialogOption("联系 长崎爽世",new Soyorin(),new Soyorin_relic());
                this.selectionBandNumber1 = "Soyorin";
            }
            if (currentSequence.get(0) == 3) {
                this.imageEventText.setDialogOption("联系 椎名立希",new Rikki(),new Rikki_relic());
                this.selectionBandNumber1 = "Rikki";
            }
            if (currentSequence.get(0) == 4) {
                this.imageEventText.setDialogOption("联系 要乐奈",new Rana(),new Rana_relic());
                this.selectionBandNumber1 = "Rana";
            }
            if (currentSequence.get(0) == 5) {
                this.imageEventText.setDialogOption("联系 丰川祥子",new Sakiko(),new Sakiko_relic());
                this.selectionBandNumber1 = "Sakiko";
            }
            if (currentSequence.get(0) == 6) {
                this.imageEventText.setDialogOption("联系 若叶睦",new Mutsumi(),new Mutsumi_relic());
                this.selectionBandNumber1 = "Mutsumi";
            }
            if (currentSequence.get(1) == 1) {
                this.imageEventText.setDialogOption("联系 高松灯",new Tomori(),new Tomori_relic());
                this.selectionBandNumber2 = "Tomori";
            }
            if (currentSequence.get(1) == 2) {
                this.imageEventText.setDialogOption("联系 长崎爽世",new Soyorin(),new Soyorin_relic());
                this.selectionBandNumber2 = "Soyorin";
            }
            if (currentSequence.get(1) == 3) {
                this.imageEventText.setDialogOption("联系 椎名立希",new Rikki(),new Rikki_relic());
                this.selectionBandNumber2 = "Rikki";
            }
            if (currentSequence.get(1) == 4) {
                this.imageEventText.setDialogOption("联系 要乐奈",new Rana(),new Rana_relic());
                this.selectionBandNumber2 = "Rana";
            }
            if (currentSequence.get(1) == 5) {
                this.imageEventText.setDialogOption("联系 丰川祥子",new Sakiko(),new Sakiko_relic());
                this.selectionBandNumber2 = "Sakiko";
            }
            if (currentSequence.get(1) == 6) {
                this.imageEventText.setDialogOption("联系 若叶睦",new Mutsumi(),new Mutsumi_relic());
                this.selectionBandNumber2 = "Mutsumi";
            }
            if (currentSequence.get(2) == 1) {
                this.imageEventText.setDialogOption("联系 高松灯",new Tomori(),new Tomori_relic());
                this.selectionBandNumber3 = "Tomori";
            }
            if (currentSequence.get(2) == 2) {
                this.imageEventText.setDialogOption("联系 长崎爽世",new Soyorin(),new Soyorin_relic());
                this.selectionBandNumber3 = "Soyorin";
            }
            if (currentSequence.get(2) == 3) {
                this.imageEventText.setDialogOption("联系 椎名立希",new Rikki(),new Rikki_relic());
                this.selectionBandNumber3 = "Rikki";
            }
            if (currentSequence.get(2) == 4) {
                this.imageEventText.setDialogOption("联系 要乐奈",new Rana(),new Rana_relic());
                this.selectionBandNumber3 = "Rana";
            }
            if (currentSequence.get(2) == 5) {
                this.imageEventText.setDialogOption("联系 丰川祥子",new Sakiko(),new Sakiko_relic());
                this.selectionBandNumber3 = "Sakiko";
            }
            if (currentSequence.get(2) == 6) {
                this.imageEventText.setDialogOption("联系 若叶睦",new Mutsumi(),new Mutsumi_relic());
                this.selectionBandNumber3 = "Mutsumi";
            }
        }else {
            if (currentSequence.get(0) == 1) {
                this.imageEventText.setDialogOption("contact Takamatsu Tomori");
                this.selectionBandNumber1 = "Tomori";
            }
            if (currentSequence.get(0) == 2) {
                this.imageEventText.setDialogOption("contact Nagasaki Soyo");
                this.selectionBandNumber1 = "Soyorin";
            }
            if (currentSequence.get(0) == 3) {
                this.imageEventText.setDialogOption("contact Shiina Taki");
                this.selectionBandNumber1 = "Rikki";
            }
            if (currentSequence.get(0) == 4) {
                this.imageEventText.setDialogOption("contact Kaname Rana");
                this.selectionBandNumber1 = "Rana";
            }
            if (currentSequence.get(0) == 5) {
                this.imageEventText.setDialogOption("contact Togawa Sakiko");
                this.selectionBandNumber1 = "Sakiko";
            }
            if (currentSequence.get(0) == 6) {
                this.imageEventText.setDialogOption("contact Wakaba Mutsumi");
                this.selectionBandNumber1 = "Mutsumi";
            }
            if (currentSequence.get(1) == 1) {
                this.imageEventText.setDialogOption("contact Takamatsu Tomori");
                this.selectionBandNumber2 = "Tomori";
            }
            if (currentSequence.get(1) == 2) {
                this.imageEventText.setDialogOption("contact Nagasaki Soyo");
                this.selectionBandNumber2 = "Soyorin";
            }
            if (currentSequence.get(1) == 3) {
                this.imageEventText.setDialogOption("contact Shiina Taki");
                this.selectionBandNumber2 = "Rikki";
            }
            if (currentSequence.get(1) == 4) {
                this.imageEventText.setDialogOption("contact Kaname Rana");
                this.selectionBandNumber2 = "Rana";
            }
            if (currentSequence.get(1) == 5) {
                this.imageEventText.setDialogOption("contact Togawa Sakiko");
                this.selectionBandNumber2 = "Sakiko";
            }
            if (currentSequence.get(1) == 6) {
                this.imageEventText.setDialogOption("contact Wakaba Mutsumi");
                this.selectionBandNumber2 = "Mutsumi";
            }
            if (currentSequence.get(2) == 1) {
                this.imageEventText.setDialogOption("contact Takamatsu Tomori");
                this.selectionBandNumber3 = "Tomori";
            }
            if (currentSequence.get(2) == 2) {
                this.imageEventText.setDialogOption("contact Nagasaki Soyo");
                this.selectionBandNumber3 = "Soyorin";
            }
            if (currentSequence.get(2) == 3) {
                this.imageEventText.setDialogOption("contact Shiina Taki");
                this.selectionBandNumber3 = "Rikki";
            }
            if (currentSequence.get(2) == 4) {
                this.imageEventText.setDialogOption("contact Kaname Rana");
                this.selectionBandNumber3 = "Rana";
            }
            if (currentSequence.get(2) == 5) {
                this.imageEventText.setDialogOption("contact Togawa Sakiko");
                this.selectionBandNumber3 = "Sakiko";
            }
            if (currentSequence.get(2) == 6) {
                this.imageEventText.setDialogOption("contact Wakaba Mutsumi");
                this.selectionBandNumber3 = "Mutsumi";
            }
        }
    }

    private enum AllBandFriends {
        all, Tomori1,Tomori2,Tomori3,Tomori4,Tomori5, Soyorin1,Soyorin2,Soyorin3,Soyorin4,
        Rikki1,Rikki2,Rikki3,Rikki4,Rana1,Rana2,Rana3,Rana4,
        Sakiko1,Sakiko2,Sakiko3,Sakiko4,Mutsumi1,Mutsumi2,Mutsumi3,Mutsumi4,Mutsumi5,finished,finished2;
    }
    private String BandFriendsEventsFirst(String name) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            if (name.equals("Tomori")) {
                String events = "“Anon酱……！" + " NL " +
                        "手机点开的一瞬间，不远处传来一声呼唤……”";
                return events;
            }
            if (name.equals("Soyorin")) {
                String events = "“Anon酱……！”" + " NL " +
                        "手机点开的一瞬间，不远处传来一声呼唤……" + " NL ”" +
                        "爱音……谢谢你今天愿意来见我……";
                return events;
            }
            if (name.equals("Rikki")) {
                String events = "“是你吗爱音！" + " NL " +
                        "手机点开的一瞬间，不远处传来一声呼唤……”";
                return events;
            }
            if (name.equals("Rana")) {
                String events = "“Ma cha non……！" + " NL " +
                        "手机点开的一瞬间，不远处传来一声呼唤……”";
                return events;
            }
            if (name.equals("Sakiko")) {
                String events = "“爱音同学……是你在那里吗”" + " NL " +
                        "（好熟悉的声音啊）";
                return events;
            }
            if (name.equals("Mutsumi")) {
                String events = "“爱音同学……”" + " NL "
                        + "（点开消息的时候，好像有人在呼唤我……诶？真的假的？她穿着月之森学院的校服啊" +
                        "那可是超级大小姐学校，难道她也是名人家的大小姐吗……？";
                return events;
            }
        }else {
            if(name.equals("Tomori")){
                String events = "“Anon! ..."+" NL " +
                        "The moment I opened my phone, a call came from not far away...";
                return  events;
            }
            if(name.equals("Soyorin")){
                String events = "“Anon! ..."+" NL " +
                        "The moment I opened my phone, a call came from not far away..."+" NL " +
                "Anon... Thank you for being willing to meet me today...";
                return  events;
            }
            if(name.equals("Rikki")){
                String events = "“Is that you, Anon!"+" NL " +
                        "The moment I opened my phone, a call came from not far away...";
                return  events;
            }
            if(name.equals("Rana")){
                String events = "“Ma cha non...!"+" NL " +
                        "The moment I opened my phone, a call came from not far away...";
                return  events;
            }
            if(name.equals("Sakiko")){
                String events = "“Anon, is that you over there?”"+" NL " +
                        "(What a familiar voice)";
                return  events;
            }
            if(name.equals("Mutsumi")){
                String events = "“Anon...”"+" NL " +
                        "(When I opened the message, it seemed like someone was calling out to me... Huh? Is this real? She's wearing the uniform of the Moon Forest Academy,"+
                "that's a super prestigious school, could she also be a young lady from a distinguished family...?)";
                return  events;
            }
        }
        return  null;
    }


    @Override
    protected void buttonEffect(int buttonPressed) {
        if (Settings.language == Settings.GameLanguage.ZHS){
            AbstractEvent event;
            switch (this.BandEvents) {
                case all:
                    switch (buttonPressed) {
                        case 0:
                            if(selectionBandNumber1 == "Tomori"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Tomori"));
                                this.imageEventText.loadImage("img/event/Tomori1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“小灯?!怎么你也在这里");
                                this.BandEvents= AllBandFriends.Tomori1;
                                return;
                            }
                            if(selectionBandNumber1 == "Soyorin"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Soyorin"));
                                this.imageEventText.loadImage("img/event/soyo1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("诶，爽世同学……");
                                this.BandEvents= AllBandFriends.Soyorin1;
                                return;
                            }
                            if(selectionBandNumber1 == "Rikki"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rikki"));
                                this.imageEventText.loadImage("img/event/Taki1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("是你吗，立希同学？");
                                this.BandEvents= AllBandFriends.Rikki1;
                                return;
                            }
                            if(selectionBandNumber1 == "Rana"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rana"));
                                this.imageEventText.loadImage("img/event/Rana1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“还真的是Rana同学欸，Rana同学真的跟猫一样呢……”");
                                this.BandEvents= AllBandFriends.Rana1;
                                return;
                            }
                            if(selectionBandNumber1 == "Sakiko"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Sakiko"));
                                this.imageEventText.loadImage("img/event/sakiko4.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“欸小祥同学？怎么你也在这里……”");
                                this.BandEvents= AllBandFriends.Sakiko1;
                                return;
                            }
                            if(selectionBandNumber1 == "Mutsumi"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Mutsumi"));
                                this.imageEventText.loadImage("img/event/mutsumi1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“内个……你好呀……诶？难道……真的是小睦？”");
                                this.BandEvents= AllBandFriends.Mutsumi1;
                                return;
                            }
                        case 1:
                            if(selectionBandNumber2 == "Tomori"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Tomori"));
                                this.imageEventText.loadImage("img/event/Tomori1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“小灯?!怎么你也在这里");
                                this.BandEvents= AllBandFriends.Tomori1;
                                return;
                            }
                            if(selectionBandNumber2 == "Soyorin"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Soyorin"));
                                this.imageEventText.loadImage("img/event/soyo1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("诶，爽世同学……");
                                this.BandEvents= AllBandFriends.Soyorin1;
                                return;
                            }
                            if(selectionBandNumber2 == "Rikki"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rikki"));
                                this.imageEventText.loadImage("img/event/Taki1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("是你吗，立希同学？");
                                this.BandEvents= AllBandFriends.Rikki1;
                                return;
                            }
                            if(selectionBandNumber2 == "Rana"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rana"));
                                this.imageEventText.loadImage("img/event/Rana1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“还真的是Rana同学欸，Rana同学真的跟猫一样呢……”");
                                this.BandEvents= AllBandFriends.Rana1;
                                return;
                            }
                            if(selectionBandNumber2 == "Sakiko"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Sakiko"));
                                this.imageEventText.loadImage("img/event/sakiko4.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“欸小祥同学？怎么你也在这里……”");
                                this.BandEvents= AllBandFriends.Sakiko1;
                                return;
                            }
                            if(selectionBandNumber2 == "Mutsumi"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Mutsumi"));
                                this.imageEventText.loadImage("img/event/mutsumi1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“内个……你好呀……诶？难道……真的是小睦？”");
                                this.BandEvents= AllBandFriends.Mutsumi1;
                                return;
                            }
                        case 2:
                            if(selectionBandNumber3 == "Tomori"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Tomori"));
                                this.imageEventText.loadImage("img/event/Tomori1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“小灯?!怎么你也在这里");
                                this.BandEvents= AllBandFriends.Tomori1;
                                return;
                            }
                            if(selectionBandNumber3 == "Soyorin"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Soyorin"));
                                this.imageEventText.loadImage("img/event/soyo1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("诶，爽世同学……");
                                this.BandEvents= AllBandFriends.Soyorin1;
                                return;
                            }
                            if(selectionBandNumber3 == "Rikki"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rikki"));
                                this.imageEventText.loadImage("img/event/Taki1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("是你吗，立希同学？");
                                this.BandEvents= AllBandFriends.Rikki1;
                                return;
                            }
                            if(selectionBandNumber3 == "Rana"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rana"));
                                this.imageEventText.loadImage("img/event/Rana1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“还真的是Rana同学欸，Rana同学真的跟猫一样呢……”");
                                this.BandEvents= AllBandFriends.Rana1;
                                return;
                            }
                            if(selectionBandNumber3 == "Sakiko"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Sakiko"));
                                this.imageEventText.loadImage("img/event/sakiko4.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“欸小祥同学？怎么你也在这里……”");
                                this.BandEvents= AllBandFriends.Sakiko1;
                                return;
                            }
                            if(selectionBandNumber3 == "Mutsumi"){
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Mutsumi"));
                                this.imageEventText.loadImage("img/event/mutsumi1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“内个……你好呀……诶？难道……真的是小睦？”");
                                this.BandEvents= AllBandFriends.Mutsumi1;
                                return;
                            }
                    }
                    openMap();
                    return;
                case Tomori1:
                    this.imageEventText.updateBodyText("“Anon酱……知道这里是哪里吗……这里让我感觉不太好”"+" NL "
                            +"Tomori双手紧握在胸口，好像在寻找着什么"+" NL " +
                            "Anon酱，还记得是怎么来到这里的吗……");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("哈哈……我也完全不记得怎么来的呢……小灯为什么会来这里呢？");
                    this.BandEvents= AllBandFriends.Tomori2;
                    return;
                case Tomori2:
                    this.imageEventText.updateBodyText("“这样啊……Anon酱不记得了啊……”"+" NL "
                            +"（Tomori脸上是没有掩饰的失落……我真的忘记了什么重要的事情吗……）"+" NL " +
                            "“但是没有关系的，我会一直等着Anon酱的……毕竟说好了……一辈子……”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/Tomori2.png");
                    this.imageEventText.setDialogOption("“一辈子什么的……对了，要不要加入……”");
                    this.BandEvents= AllBandFriends.Tomori3;
                    return;
                case Tomori3:
                    this.imageEventText.updateBodyText("“Anon Tokyo……？” "+" NL " +
                            "(Tomori好像在思考着什么……真是个可爱的小动物呢……虽然一直搞不明白她在想什么)"+" NL " +
                            "“如果这样能和Anon酱一辈子的话，我也可以加入的！”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“唔，突然……头好疼……水族馆……”");
                    this.BandEvents= AllBandFriends.Tomori4;
                    return;
                case Tomori4:
                    this.imageEventText.updateBodyText("（这是……水族馆……？"+" NL " +
                            "为什么我会在这里，和……和Tomori一起去水族馆？难道我真的忘了什么重要的事情……)"
                            +"“Anon酱难道想起来了什么！”"+" NL " +
                            "Tomori紧紧抓着我的手，好像再放开就会失去什么……"+" NL " +
                            "没事的，Anon酱……大家都在等你……这个给你……");
                    this.imageEventText.clearAllDialogs();

                    this.imageEventText.setDialogOption("“对不起呢……我好像什么都想不起来……诶，塞给我了什么”");
                    this.BandEvents= AllBandFriends.Tomori5;
                    this.imageEventText.loadImage("img/event/card_normal_Tomori.png");
                    return;
                case Tomori5:
                    this.imageEventText.updateBodyText("“我会一直陪着Anon酱走到终点的……”"+" NL "+
                            "这是……"
                            +"(诶诶诶，我接过 #y创口贴 刚想抬头寻找Tomori的身影，却发现火堆边又只剩我一个人了"+" NL " +
                            "如果不是创口贴还在手里的话，还以为是在休息时做了一场梦啊……)"+" NL " +
                            "该启程了……");
                    BandNumbers = BandNumbers + 1;
                    this.imageEventText.loadImage("img/event/Tomori3.png");
                    Tomori_relic Tomori_relic = new Tomori_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Tomori_relic);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“我就先收下吧，谢谢你”");
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case Soyorin1:
                    this.imageEventText.updateBodyText("说出口的一瞬间，爽世的眼神变了……"+" NL "
                            +"(完蛋了……我又说错什么话了吗……)"+" NL " +
                            "“嗯嗯……没事哦，抱歉突然来找你，没打扰到爱音同学吧？”"+" NL " +
                            "（这绝对不像没事的样子吧……）但爱音还是摇了摇头"+" NL " +
                            "“对不起，我无论如何都想当面向你道歉，那天是我们说了气话……”");
                    this.imageEventText.loadImage("img/event/soyo2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("没关系的哦……爽世同学知道这里是什么地方吗？");
                    this.BandEvents= AllBandFriends.Soyorin2;
                    return;
                case Soyorin2:
                    this.imageEventText.updateBodyText("“这里是……这样啊……爱音想不起来了吗……”"
                            +"爽世抬起右手，轻轻掐着指甲"+" NL " +
                            "“如果离开这里的话，爱音还愿意和我们一起……”"+" NL " +
                            "她的声音逐渐变小，渐渐……渐渐听不清后面的话"+" NL " +
                            "（是我忘记了什么吗？不过正好趁这个机会问问她吧，我记得爽世同学在学校里是吹奏部的吧，也会弹贝斯）");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("爽世同学，愿意加入Anon Tokyo吗？我是吉他主唱哦！");
                    this.BandEvents= AllBandFriends.Soyorin3;
                    return;
                case Soyorin3:
                    this.imageEventText.updateBodyText("“Anon……Tokyo……？”"+" NL "
                            +"（诶，这个名字很奇怪吗……我感觉还蛮好听的……）"+" NL " +
                            "爽世放下了掐指甲的手，笑了起来"+" NL " +
                            "“可以哦……”"+" NL " +
                            "(诶？)+"+" NL " +
                            "“原来是这么一回事啊……我完全明白了……爱音同学，这个给你”");
                    this.imageEventText.loadImage("img/event/soyo3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("继续");
                    Soyorin_relic Soyorin_relic = new Soyorin_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Soyorin_relic);
                    this.BandEvents= AllBandFriends.Soyorin4;
                    return;
                case Soyorin4:
                    this.imageEventText.updateBodyText("“Anon酱……你只是迷路了……”"
                            +"Crychic……Mygo的大家都在等你……这次一定不会……"+" NL " +
                            "（什么吗……Crychic还是Mygo什么的，我一点印象都没有……"+" NL " +
                            "虽然爽世同学还和以前一样温柔，但什么也没和我解释清楚呢，下次见面再好好问问吧……"+" NL " +
                            "已经找不到爽世同学的身影了，我也该出发了……）");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("启程");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case Rikki1:
                    this.imageEventText.updateBodyText("“真是的……你这次又逃到哪里去了啊”"+" NL " +
                            "立希突然出现，气氛紧张了起来"+" NL " +
                            "“喂，你知道你现在在什么地方吗？你不会还在为之前的事情生气吧……”"+" NL " +
                            "（什么啊，这个人……我们应该就几次合奏的交情吧，为什么她这么压力我？）");
                    this.imageEventText.loadImage("img/event/Taki2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“哈？我也很着急，正在找回去的路啊……之前的事？”");
                    this.BandEvents= AllBandFriends.Rikki2;
                    return;
                case Rikki2:
                    this.imageEventText.updateBodyText("“你说什么，之前明明……？”"+" NL " +
                            "立希放下手，微微低头开始思考"+" NL " +
                            "“难道你真的……？” "+" NL "+
                            "（这个人态度好差诶，和她一个乐队的话肯定很难相处，是吧）"+" NL " +
                            "立希倏然冲了过来，抓住爱音的肩膀"+" NL " +
                            "“Tomori……Mygo……你还记得吗？”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("诶……Tomori是我前桌那个主唱吧，Mygo又是什么，好奇怪的名字哦……对了，要加入Anon Tokyo吗？");
                    this.BandEvents= AllBandFriends.Rikki3;
                    return;
                case Rikki3:
                    this.imageEventText.updateBodyText("“这么重要的……我们的……Anon Tokyo又是什么？”"+" NL " +
                            "立希松开了爱音，背过身去，好像不想让爱音看到自己的表情……" +
                            "“拿好这个……如果碰到危险，我会来帮你的……”"+" NL " +
                            "（怎么大家都嫌弃Anon Tokyo这个名字诶…… #y这个东西 是？）"+" NL " +
                            "“Tomori还在等你回来……Crychic也在等你……”");
                    this.imageEventText.loadImage("img/event/Taki3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“虽然不是很明白你的意思，不过还是谢谢你啦，立希同学”");
                    Rikki_relic Rikki_relic = new Rikki_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Rikki_relic);
                    if(!AbstractDungeon.player.hasRelic(FirstHalfKey.ID) && SkinSelectScreen.Inst.index ==1){
                        FirstHalfKey First = new FirstHalfKey();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) First);
                    }
                    this.BandEvents= AllBandFriends.Rikki4;
                    return;
                case Rikki4:
                    this.imageEventText.updateBodyText("“下次再见面的时候，你应该就想起来了吧……"+" NL " +
                            "我们还会再见面的”"+" NL "+
                            "立希挥着手，身影消失在了篝火远处的黑暗中"+" NL "
                            +"(真是个特立独行的孩子呢，不过立希也加入的话，Anon Tokyo终于有鼓手了哦！"+" NL " +
                            "下次我会想起什么呢？该出发了……");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("启程");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case Rana1:
                    this.imageEventText.updateBodyText("“归宿，找到了吗……”"+" NL "
                            +"（欸……什么意思啊……）"+" NL " +
                            "“哼哼……”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/Rana2.png");
                    this.imageEventText.setDialogOption("“归宿是指，Ring吗？我们出去后要在那里练习吧……”");
                    this.BandEvents= AllBandFriends.Rana2;
                    return;
                case Rana2:
                    this.imageEventText.updateBodyText("“Live……?”"+" NL "
                            +"（乐奈同学的眼睛突然开始发光了……好神奇）"+" NL " +
                            "“Anon觉得……Ring就是归宿吗？”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“归宿什么的我不懂啦……不过Rana要加入Anon Tokyo吗？”");
                    this.BandEvents= AllBandFriends.Rana3;
                    return;
                case Rana3:
                    this.imageEventText.updateBodyText("“Anon Tokyo……”"+" NL "
                            +"乐奈闭上眼睛开始思考 然后突然塞过来一个东西"+" NL " +
                            "(这是…… #y一个杯子 ？好像是用来装乐奈酱平时点的芭菲……难道是她顺手带出来的？)"+" NL " +
                            "“Anon……不记得了……Mygo……但是……那里是我们两个人……最后的归宿”");
                    this.imageEventText.loadImage("img/event/Rana3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“Rana酱，出去后要把杯子还给店员哦……欸？”");
                    Rana_relic Rana_relic = new Rana_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Rana_relic);
                    if(!AbstractDungeon.player.hasRelic(FirstHalfKey.ID) && SkinSelectScreen.Inst.index ==1){
                        FirstHalfKey First = new FirstHalfKey();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) First);
                    }

                    this.BandEvents= AllBandFriends.Rana4;
                    return;
                case Rana4:
                    this.imageEventText.updateBodyText("“抹茶芭菲……回去后请我……”"+" NL " +
                            "（Ring……发生了什么吗……还有Mygo是什么？感觉没有Anon Tokyo好听呢……）"+" NL "+
                            "耳边还回响着话语，一个闭眼思考的时间已经找不到乐奈的身影了"+" NL "
                            +"(乐奈酱还是这么喜欢抹茶芭菲呀……！"+" NL " +
                            "哎，该启程了，看来回去后芭菲钱是没法省了……");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("启程");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case Sakiko1:
                    this.imageEventText.updateBodyText("“终于找到你了，爱音同学”"+" NL "
                            +"（欸，是隔壁班的祥子同学欸，我们应该只见过一面，而且那次……）"+" NL " +
                            "“爱音同学好像脸色不太好呢，是在烦恼什么事情吗？”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/sakiko3.png");
                    this.imageEventText.setDialogOption("“啊哈哈……确实……祥子同学知道这里是什么地方吗？”");
                    this.BandEvents= AllBandFriends.Sakiko2;
                    return;
                case Sakiko2:
                    this.imageEventText.updateBodyText("“这里……比起我，爱音同学应该更清楚吧！”"+" NL "
                            +"（……？可是我在这里迷路这么久，还没有找到出路啊……）"+" NL " +
                            "祥子看出了爱音的顾虑，半掩着嘴微微一笑"+" NL " +
                            "“爱音同学在烦恼什么呢，能不能说给我听听？”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/sakiko3.png");
                    this.imageEventText.setDialogOption("“烦恼……？我应该只是在这里迷路……”");
                    this.BandEvents= AllBandFriends.Sakiko3;
                    return;
                case Sakiko3:
                    this.imageEventText.updateBodyText("“嗯！你只是在这里迷路了……这个给你！”"+" NL "
                            +"（欸？这是 #y祥子同学的发带 ）"+" NL " +
                            "“不要怀疑，不要畏惧，去知晓，知晓一切才能得救……"+" NL "+
                            "“请尽快找到出去的路！Mygo……Crychic的大家都在等你回来！”");
                    this.imageEventText.loadImage("img/event/sakiko2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("等一下……？Mygo？Crychic？……");
                    this.BandEvents= AllBandFriends.Sakiko4;
                    return;
                case Sakiko4:
                    Sakiko_relic Sakiko_relic = new Sakiko_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Sakiko_relic);
                    this.imageEventText.updateBodyText("“这是曾经软弱的我留下的，现在交给你了……"+" NL " +
                            "碰到困难的时候……抓住它……我和大家都会来帮助爱音同学的……”"+" NL "+
                            "祥子的声音还在回荡，一眨眼却看不到她的身影了"+" NL "
                            +"(祥子同学你倒是把话说完啊……！"+" NL " +
                            "哎，该启程了，要是之后还能遇到祥子同学一定要问个清楚……)");
                        if (Loader.isModLoaded("sakikoMod")) {
                            receiveRelic.receiveRelicAddsakikoMod();
                        this.imageEventText.updateBodyText("“这是曾经软弱的我留下的，现在交给你了……"+" NL " +
                                "碰到困难的时候……抓住它……我和大家都会来帮助爱音同学的……”"+" NL "+
                                "祥子的声音还在回荡，一眨眼却看不到她的身影了"+" NL "
                                +"祥子同学你倒是把话说完啊……！"+" NL " +
                                "哎，等等，这是，我的玩偶？为什么祥子会有这个啊。");
                    }
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("继续");
                    BandNumbers = BandNumbers + 1;
//                openMap();
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case Mutsumi1:
                    this.imageEventText.updateBodyText("“爱音同学……怎么看待乐队成员的呢”"+" NL "
                            +"（难道小睦也想和我组乐队？没想到我也有今天呐~）");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("那个，乐队成员，肯定就是命运共同体了……小睦看过我们的演出吗？");
                    this.BandEvents= AllBandFriends.Mutsumi2;
                    return;
                case Mutsumi2:
                    this.imageEventText.updateBodyText("啊……那个新手节奏吉他…… "+" NL "
                            +"（诶，原来台下的观众是这种感受啊……感觉好受伤……）"+" NL " +
                            "小睦突然意识到自己又说了不该说的……低下头捏着衣角"+" NL ");
                    this.imageEventText.loadImage("img/event/mutsumi2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“没关系的啦，……那个，机会难得，小睦要加入Anon Tokyo吗？”");
                    this.BandEvents= AllBandFriends.Mutsumi3;
                    return;
                case Mutsumi3:
                    this.imageEventText.updateBodyText("“Anon……Tokyo……？”？"+" NL "
                            +"(诶，这个名字很奇怪吗……我感觉还蛮时尚的……)"+" NL " +
                            "“Mygo……还会回来吗");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("MyGo？那是什么……好像有点印象……");
                    this.BandEvents= AllBandFriends.Mutsumi4;
                    return;
                case Mutsumi4:
                    this.imageEventText.updateBodyText("（诶，小睦给了我什么……好像是， #y小黄瓜 ？）"+" NL "
                            +"“我……”"+" NL " +
                            "“我想陪Anon到最后……Crychic……Soyo……”"+" NL " +
                            "小睦突然顿住了，眼神一变");
                    this.imageEventText.loadImage("img/event/mutsumi3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“Crychic……还有爽世同学？”");
                    Mutsumi_relic Mutsumi_relic = new Mutsumi_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Mutsumi_relic);
                    this.BandEvents= AllBandFriends.Mutsumi5;
                    return;
                case Mutsumi5:
                    this.imageEventText.updateBodyText("“小黄瓜……我为数不多的……"+" NL " +
                            "需要的时候……我会来帮你的……”"+" NL "+
                            "尚在思索小睦的言语，回过神来仅余自己孤身一人"
                            +"(真是个特立独行的孩子呢，不过小睦也加入的话，Anon Tokyo以后肯定会出名吧！"+" NL " +
                            "该启程了……");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("继续");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case finished:
                    this.BandEvents= AllBandFriends.finished2;
                    openMap();
                case finished2:
                    openMap();
            }
            openMap();
        }else {
            AbstractEvent event;
            switch (this.BandEvents) {
                case all:
                    switch (buttonPressed) {
                        case 0:
                            if (selectionBandNumber1 == "Tomori") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Tomori"));
                                this.imageEventText.loadImage("img/event/Tomori1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Tomori?!How come you're here as well?");
                                this.BandEvents = AllBandFriends.Tomori1;
                                return;
                            }
                            if (selectionBandNumber1 == "Soyorin") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Soyorin"));
                                this.imageEventText.loadImage("img/event/soyo1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("hey，soyorin……");
                                this.BandEvents = AllBandFriends.Soyorin1;
                                return;
                            }
                            if (selectionBandNumber1 == "Rikki") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rikki"));
                                this.imageEventText.loadImage("img/event/Taki1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("Is that you，rikki classmate？");
                                this.BandEvents = AllBandFriends.Rikki1;
                                return;
                            }
                            if (selectionBandNumber1 == "Rana") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rana"));
                                this.imageEventText.loadImage("img/event/Rana1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“It's really you, Rana, isn't it? Rana, you're just like a cat……”");
                                this.BandEvents = AllBandFriends.Rana1;
                                return;
                            }
                            if (selectionBandNumber1 == "Sakiko") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Sakiko"));
                                this.imageEventText.loadImage("img/event/sakiko4.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Hey, Sakiko? Why are you here too……”");
                                this.BandEvents = AllBandFriends.Sakiko1;
                                return;
                            }
                            if (selectionBandNumber1 == "Mutsumi") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Mutsumi"));
                                this.imageEventText.loadImage("img/event/mutsumi1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Um... Hello there... Eh? Could it be... really little Mutsumi?”");
                                this.BandEvents = AllBandFriends.Mutsumi1;
                                return;
                            }
                        case 1:
                            if (selectionBandNumber2 == "Tomori") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Tomori"));
                                this.imageEventText.loadImage("img/event/Tomori1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Tomori?!How come you're here as well?");
                                this.BandEvents = AllBandFriends.Tomori1;
                                return;
                            }
                            if (selectionBandNumber2 == "Soyorin") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Soyorin"));
                                this.imageEventText.loadImage("img/event/soyo1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("hey，soyorin……");
                                this.BandEvents = AllBandFriends.Soyorin1;
                                return;
                            }
                            if (selectionBandNumber2 == "Rikki") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rikki"));
                                this.imageEventText.loadImage("img/event/Taki1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("Is that you，rikki classmate？");
                                this.BandEvents = AllBandFriends.Rikki1;
                                return;
                            }
                            if (selectionBandNumber2 == "Rana") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rana"));
                                this.imageEventText.loadImage("img/event/Rana1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“It's really you, Rana, isn't it? Rana, you're just like a cat……”");
                                this.BandEvents = AllBandFriends.Rana1;
                                return;
                            }
                            if (selectionBandNumber2 == "Sakiko") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Sakiko"));
                                this.imageEventText.loadImage("img/event/sakiko4.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Hey, Sakiko? Why are you here too……”");
                                this.BandEvents = AllBandFriends.Sakiko1;
                                return;
                            }
                            if (selectionBandNumber2 == "Mutsumi") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Mutsumi"));
                                this.imageEventText.loadImage("img/event/mutsumi1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Um... Hello there... Eh? Could it be... really little Mutsumi?”");
                                this.BandEvents = AllBandFriends.Mutsumi1;
                                return;
                            }
                        case 2:
                            if (selectionBandNumber3 == "Tomori") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Tomori"));
                                this.imageEventText.loadImage("img/event/Tomori1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Tomori?!How come you're here as well?");
                                this.BandEvents = AllBandFriends.Tomori1;
                                return;
                            }
                            if (selectionBandNumber3 == "Soyorin") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Soyorin"));
                                this.imageEventText.loadImage("img/event/soyo1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("hey，soyorin……");
                                this.BandEvents = AllBandFriends.Soyorin1;
                                return;
                            }
                            if (selectionBandNumber3 == "Rikki") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rikki"));
                                this.imageEventText.loadImage("img/event/Taki1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("Is that you，rikki classmate？");
                                this.BandEvents = AllBandFriends.Rikki1;
                                return;
                            }
                            if (selectionBandNumber3 == "Rana") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Rana"));
                                this.imageEventText.loadImage("img/event/Rana1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“It's really you, Rana, isn't it? Rana, you're just like a cat……”");
                                this.BandEvents = AllBandFriends.Rana1;
                                return;
                            }
                            if (selectionBandNumber3 == "Sakiko") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Sakiko"));
                                this.imageEventText.loadImage("img/event/sakiko4.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Hey, Sakiko? Why are you here too……”");
                                this.BandEvents = AllBandFriends.Sakiko1;
                                return;
                            }
                            if (selectionBandNumber3 == "Mutsumi") {
                                this.imageEventText.updateBodyText(BandFriendsEventsFirst("Mutsumi"));
                                this.imageEventText.loadImage("img/event/mutsumi1.png");
                                this.imageEventText.clearAllDialogs();
                                this.imageEventText.setDialogOption("“Um... Hello there... Eh? Could it be... really little Mutsumi?”");
                                this.BandEvents = AllBandFriends.Mutsumi1;
                                return;
                            }
                    }
                    openMap();
                    return;
                case Tomori1:
                    this.imageEventText.updateBodyText("“Anon... Do you know where we are... This place gives me an uneasy feeling.”" + " NL "
                            + "Tomori clutches her hands tightly at her chest, as if searching for something." + " NL "
                            + "Anon, do you remember how we got here?");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Haha... I also don't remember how I got here... Why are you here, Tomori?");
                    this.BandEvents = AllBandFriends.Tomori2;
                    return;
                case Tomori2:
                    this.imageEventText.updateBodyText("“So that's it... Anon doesn't remember...”" + " NL "
                            + "(Tomori's face shows undisguised disappointment... Did I really forget something important...)" + " NL "
                            + "But it doesn't matter, I will always wait for Anon... After all, we promised... For a lifetime...");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/Tomori2.png");
                    this.imageEventText.setDialogOption("“About a lifetime... Right, would you like to join...");
                    this.BandEvents = AllBandFriends.Tomori3;
                    return;
                case Tomori3:
                    this.imageEventText.updateBodyText("“Anon Tokyo...?” " + " NL "
                            + "(Tomori seems to be thinking about something... She's such a lovely creature... Although I've never understood what she's thinking)" + " NL "
                            + "If this allows me to be with Anon for a lifetime, then I can join too!");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“Ugh, suddenly... my head hurts... Aquarium...");
                    this.BandEvents = AllBandFriends.Tomori4;
                    return;
                case Tomori4:
                    this.imageEventText.updateBodyText("(Are we at... the aquarium...?" + " NL "
                            + "Why am I here, with... with Tomori at the aquarium? Did I really forget something important...)"
                            + "“Did Anon remember something!”" + " NL "
                            + "Tomori tightly holds my hand, as if letting go would lose something..." + " NL "
                            + "It's okay, Anon... Everyone is waiting for you... Here, take this...");
                    this.imageEventText.clearAllDialogs();

                    this.imageEventText.setDialogOption("“I'm sorry... I can't seem to remember anything... Hey, what did you give me?”");
                    this.BandEvents = AllBandFriends.Tomori5;
                    this.imageEventText.loadImage("img/event/card_normal_Tomori.png");
                    return;
                case Tomori5:
                    this.imageEventText.updateBodyText("“I will always accompany Anon to the end...”" + " NL "
                            + "This is..."
                            + "(Whoa, whoa, whoa, I just picked up the #y band-aid  and was about to look up for Tomori, only to find myself alone by the campfire again" + " NL "
                            + "If it weren't for the band-aid still in my hand, I would have thought it was just a dream during a break...)" + " NL "
                            + "It's time to set off...");
                    BandNumbers = BandNumbers + 1;
                    this.imageEventText.loadImage("img/event/Tomori3.png");
                    Tomori_relic Tomori_relic = new Tomori_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Tomori_relic);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“I'll take it for now, thank you.”");
                    this.BandEvents = AllBandFriends.finished;
                    return;
                case Soyorin1:
                    this.imageEventText.updateBodyText("The moment the words left my mouth, Soyorin's gaze changed..." + " NL "
                            + "(Oh no... Did I say something wrong again...?)" + " NL "
                            + "“Umm... It's okay, I'm sorry for coming to find you suddenly, didn't disturb Anon, did I?”" + " NL "
                            + "(She definitely doesn't seem fine...) But Anon still shook her head" + " NL "
                            + "“I'm sorry, no matter what, I wanted to apologize to you in person, that day we said angry things...”");
                    this.imageEventText.loadImage("img/event/soyo2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("It's okay... Does Soyorin know where we are?");
                    this.BandEvents = AllBandFriends.Soyorin2;
                    return;

                case Soyorin2:
                    this.imageEventText.updateBodyText("“This place... So it's like this... Anon can't remember, can she...”"
                            + "Soyorin raises her right hand, gently pinching her nails" + " NL "
                            + "If we leave here, would Anon still be willing to...”" + " NL "
                            + "Her voice gradually fades, slowly... slowly... I can't hear the rest" + " NL "
                            + "(Did I forget something? But it's a good opportunity to ask her, I remember Soyorin is in the band club at school, right? She can play the bass too)");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Would Soyorin like to join Anon Tokyo? I'm the guitar vocalist!");
                    this.BandEvents = AllBandFriends.Soyorin3;
                    return;
                case Soyorin3:
                    this.imageEventText.updateBodyText("“Anon... Tokyo...?”"+" NL "
                            +"(Huh, is this name strange? ...I think it sounds pretty good...)"+" NL "
                            +"Soyorin let go of her hand that was pinching her nails and smiled"+" NL "
                            +"“Sure...”"+" NL "
                            +"(Huh?)"+" NL "
                            +"“So it's like that... I completely understand now... Anon, here's this for you.”");
                    this.imageEventText.loadImage("img/event/soyo3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Continue");
                    Soyorin_relic Soyorin_relic = new Soyorin_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Soyorin_relic);
                    this.BandEvents = AllBandFriends.Soyorin4;
                    return;

                case Soyorin4:
                    this.imageEventText.updateBodyText("“Anon... you're just lost...”"
                            +"Crychic... Everyone in Mygo is waiting for you... This time, it definitely won't..."+" NL "
                            +"(What... Crychic or Mygo, I don't have any impression at all..."+" NL "
                            +"Although Soyorin is as gentle as before, she didn't explain anything clearly to me. I'll ask her next time I meet her..."+" NL "
                            +"Soyorin's figure is already gone, and I should set off too...) ");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Set off");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents = AllBandFriends.finished;
                    return;
                case Rikki1:
                    this.imageEventText.updateBodyText("Really... where have you run off to this time?"+" NL "
                            +"Rikki suddenly appears, the atmosphere becomes tense"+" NL "
                            +"Hey, do you know where you are now? You're not still angry about something from before, are you...?"+" NL "
                            +"(What's with this person... We've only played together a few times, right? Why is she pressuring me so much?)");
                    this.imageEventText.loadImage("img/event/Taki2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Huh? I'm also anxious, trying to find my way back... What previous matter?");
                    this.BandEvents = AllBandFriends.Rikki2;
                    return;

                case Rikki2:
                    this.imageEventText.updateBodyText("What are you saying, before it was clearly...?"+" NL "
                                    +"Rikki lets go of her hand, slightly lowers her head and starts thinking"+" NL "
                                    +"Could it be that you really...?"+" NL "
                                    +"(This person has such a bad attitude, being in a band with her would definitely be difficult to get along with, right?)"+" NL "
                                    +"Rikki suddenly rushes over, grabbing Anon's shoulders"+" NL "+
                            "Tomori... Mygo... Do you remember?");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Uh... Tomori is that lead singer in front of me, right? Mygo is what again, such a strange name... Oh, by the way, would you like to join Anon Tokyo?");
                    this.BandEvents = AllBandFriends.Rikki3;
                    return;

                case Rikki3:
                    this.imageEventText.updateBodyText("What is this very important... ours... Anon Tokyo?"+" NL "
                                    +"Rikki releases Anon, turns away, seemingly not wanting Anon to see her expression..."
                                    +"Take this... If you encounter danger, I will come to help you..."+" NL "
                                    +"(Why does everyone seem to dislike the name Anon Tokyo... What is this #y thing?)"+" NL "+
                            "Tomori is still waiting for you to come back... Crychic is waiting for you too...");
                    this.imageEventText.loadImage("img/event/Taki3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Although I don't quite understand what you mean, thank you anyway, Rikki.");
                    Rikki_relic Rikki_relic = new Rikki_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Rikki_relic);
                    this.BandEvents = AllBandFriends.Rikki4;
                    return;

                case Rikki4:
                    this.imageEventText.updateBodyText("Next time we meet, you should remember, right..."+" NL "
                            +"We will meet again"+" NL "
                            +"Rikki waves her hand, her figure disappearing into the darkness beyond the campfire"+" NL "
                            +"(She is such a unique child, but with Rikki joining, Anon Tokyo finally has a drummer!"
                            +"Next time, what will I remember? It's time to set off...)");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Set off");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents = AllBandFriends.finished;
                    return;
                case Rana1:
                    this.imageEventText.updateBodyText("“Home, have you found it...?”"+" NL "
                            +"(Uh... What does that mean...?)"+" NL "
                            +"“Hmm...”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/Rana2.png");
                    this.imageEventText.setDialogOption("“Home refers to, Ring, right? We will practice there after we get out...”");
                    this.BandEvents = AllBandFriends.Rana2;
                    return;

                case Rana2:
                    this.imageEventText.updateBodyText("“Live...?"+" NL "
                            +"(Rana's eyes suddenly started to shine... How magical)"+" NL "
                            +"“Does Anon think... Ring is home?”");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“I don't understand about home... But does Rana want to join Anon Tokyo?”");
                    this.BandEvents = AllBandFriends.Rana3;
                    return;

                case Rana3:
                    this.imageEventText.updateBodyText("“Anon Tokyo...?”"+" NL "
                            +"Rana closes her eyes and starts thinking, then suddenly hands something over"+" NL "
                            +"(This is... #y a cup? It seems to be used for the parfait that Rana usually orders... Could it be that she brought it out casually?)"+" NL "
                            +"Does Anon not remember... Mygo... But... That's our final home for the two of us.”");
                    this.imageEventText.loadImage("img/event/Rana3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("“Rana, after we get out, you have to return the cup to the shop assistant... Huh?”");
                    Rana_relic Rana_relic = new Rana_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Rana_relic);
                    this.BandEvents = AllBandFriends.Rana4;
                    return;

                case Rana4:
                    this.imageEventText.updateBodyText("“Matcha parfait... you owe me when we get back...”"+" NL "
                            +"(Ring... what happened... And what is Mygo? It doesn't sound as good as Anon Tokyo...)"+" NL "
                            +"Echoes of words still in my ears, in a blink of an eye, I can't find Rana's figure anymore"+" NL "
                            +"(Rana still loves matcha parfait so much...!"+" NL "
                            +"Ah, it's time to set off, it seems that the money for the parfait can't be saved when we get back...)");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Set off");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents = AllBandFriends.finished;
                    return;
                case Sakiko1:
                    this.imageEventText.updateBodyText("Finally found you, Anon"+" NL "
                            +"(Oh, it's the classmate from the next class, should we have only met once, and that time...)"+" NL "
                            +"Anon seems to be looking a bit unwell, is something troubling you?");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/sakiko3.png");
                    this.imageEventText.setDialogOption("Ahaha... indeed... does Sakiko know where this place is?");
                    this.BandEvents = AllBandFriends.Sakiko2;
                    return;

                case Sakiko2:
                    this.imageEventText.updateBodyText("This place... compared to me, Anon should be more clear, right?"+" NL "
                            +"(…? But I've been lost here for so long, and I haven't found the way out yet...)"+" NL "
                            +"Sakiko noticed Anon's concern and smiled with her hand half covering her mouth"+" NL "
                            +"Anon, what's troubling you, can you tell me?");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.loadImage("img/event/sakiko3.png");
                    this.imageEventText.setDialogOption("Trouble...? I should just be lost here...");
                    this.BandEvents = AllBandFriends.Sakiko3;
                    return;

                case Sakiko3:
                    this.imageEventText.updateBodyText("Ah! You are just lost here... Here, take this!"+" NL "
                            +"(Huh? This is #y Sakiko's ribbon)"+" NL "
                            +"Do not doubt, do not fear, go to understand, only by understanding everything can you be saved..."+" NL "
                            +"Please find a way out as soon as possible! Mygo... Everyone in Crychic is waiting for you to come back!");
                    this.imageEventText.loadImage("img/event/sakiko2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Wait a minute...? Mygo? Crychic?...");
                    this.BandEvents = AllBandFriends.Sakiko4;
                    return;

                case Sakiko4:
                    Sakiko_relic Sakiko_relic = new Sakiko_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Sakiko_relic);
                    this.imageEventText.updateBodyText("This is left by the once weak me, now it's handed over to you..."+" NL "
                            +"When you encounter difficulties... hold on to it... I and everyone will come to help Anon..."+" NL "
                            +"Sakiko's voice is still echoing, but in a blink of an eye, her figure is gone"+" NL "
                            +"(Sakiko, you didn't finish your words...!"+" NL "
                            +"Ah, it's time to set off, if I can meet Sakiko again later, I must ask clearly...)");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Continue");
                    BandNumbers = BandNumbers + 1;
                    // openMap(); // Assuming this is a method call to open the map in the game
                    this.BandEvents = AllBandFriends.finished;
                    return;
                case Mutsumi1:
                    this.imageEventText.updateBodyText("Anon... What do you think of the band members?"+" NL "
                            +"(Could it be that Mutsumi also wants to form a band with me? I didn't expect to have this kind of day~)");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Well, band members are definitely a community of fate... Has Mutsumi seen our performance?");
                    this.BandEvents = AllBandFriends.Mutsumi2;
                    return;

                case Mutsumi2:
                    this.imageEventText.updateBodyText("Ah... that novice rhythm guitarist... "+" NL "
                            +"(Eh, so this is how the audience feels... I feel so hurt...)"+" NL "
                            +"Mutsumi suddenly realized that she said something she shouldn't have... She lowered her head and pinched the corner of her clothes"+" NL ");
                    this.imageEventText.loadImage("img/event/mutsumi2.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("It's okay... Um, it's a rare opportunity, does Mutsumi want to join Anon Tokyo?");
                    this.BandEvents = AllBandFriends.Mutsumi3;
                    return;

                case Mutsumi3:
                    this.imageEventText.updateBodyText("Anon... Tokyo...?"+" NL "
                            +"(Eh, is this name strange? ...I think it's quite fashionable...)"+" NL "
                            +"Mygo... will it come back?");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("MyGo? What is that... It seems a bit familiar...");
                    this.BandEvents = AllBandFriends.Mutsumi4;
                    return;

                case Mutsumi4:
                    this.imageEventText.updateBodyText("(Eh, what did Mutsumi give me... It looks like a #y cucumber?)"+" NL "
                            +"I...");
                    this.imageEventText.loadImage("img/event/mutsumi3.png");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("Crychic... And what about Soyorin?");
                    Mutsumi_relic Mutsumi_relic = new Mutsumi_relic();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Mutsumi_relic);
                    this.BandEvents = AllBandFriends.Mutsumi5;
                    return;

                case Mutsumi5:
                    this.imageEventText.updateBodyText("The cucumber... one of the few I have..."+" NL "
                            +"When needed... I will come to help you..."+" NL "
                            +"Still pondering over Mutsumi's words, I come to my senses only to find myself alone"
                            +"(She is such a unique child, but if Mutsumi also joins, Anon Tokyo will definitely become famous in the future!"
                            +"Let's set off...)");
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption("继续");
                    BandNumbers = BandNumbers + 1;
                    this.BandEvents= AllBandFriends.finished;
                    return;
                case finished:
                    this.BandEvents= AllBandFriends.finished2;
                    openMap();
                case finished2:
                    openMap();
            }
            openMap();

        }
    }


    private static String getBody() {
        String body = "Finally, we've arrived at a place to rest,' Anon sighed, squatting down to sit" + " NL Suddenly, the phone rang, and three messages were received" + " NL (Who to reply to)?";
        if (Settings.language == Settings.GameLanguage.ZHS) {
             body = "“终于到休息的地方了”爱音长叹一声蹲坐下来" + " NL 突然手机响了起来，收到三条消息" + " NL （回复谁呢）？";
        }
        return body;
    }

    public static List<Integer> generateUniqueSequence(int sequenceIndex, int sequenceSize, int minNumber, int maxNumber) {
        sequenceSize= 3;
        minNumber = 1;
        maxNumber = 6;
        Set<Integer> availableNumbers = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            if (!allUsedNumbers.contains(i)) {
                availableNumbers.add(i);
            }
        }
        System.out.println("AbstractDungeon.miscRng.randomLong()"+AbstractDungeon.miscRng.randomLong());
        Random random = new Random(AbstractDungeon.miscRng.randomLong());
        ArrayList<Integer> numberList = new ArrayList<>(availableNumbers);
        Collections.shuffle(numberList, random);

        System.out.println("numberList"+numberList);
        if(numberList.size() >= 3){
            List<Integer> sequence = new ArrayList<>(numberList.subList(0, sequenceSize));
            return sequence;
        }

        List<Integer> sequence = new LinkedList<>();
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);
        return sequence;
    }
    static {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "招募乐队成员";
        }else {
            title = "recruit band members";
        }
    }
}

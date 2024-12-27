package events;

import basemod.eventUtil.EventUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.FastSmoke;
import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBlurEffect;
import relics.*;

import java.lang.reflect.Field;

public interface AnonAwakeWithNeo {
    public static class ForceBlessing {
        @SpireInsertPatch(rloc = 1)
        public static void Insert(Object o, boolean b) {
            Settings.isTestingNeow = true;
        }
    }

    @SpirePatch(clz = NeowRoom.class, method = "<ctor>", paramtypez = {boolean.class})
    public static class AddBetterRewardsButton {
        @SpirePostfixPatch
        public static void Postfix(NeowRoom room, boolean b) {
            int relicAtIndex = 0;
            if(AbstractDungeon.player.hasRelic(Guitar.ID)) {
                for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                    if (((AbstractRelic) AbstractDungeon.player.relics.get(i)).relicId.equals(StarAnonBike.ID)) {
                        relicAtIndex = i;
                        System.out.println(relicAtIndex);
                        AbstractDungeon.player.loseRelic(StarAnonBike.ID);
                        break;
                    }
                }
                for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                    if (((AbstractRelic) AbstractDungeon.player.relics.get(i)).relicId.equals(GuitarWhiteAnon.ID)) {
                        relicAtIndex = i;
                        System.out.println(relicAtIndex);
                        AbstractDungeon.player.loseRelic(GuitarWhiteAnon.ID);
                        break;
                    }
                }
            }
            boolean isNeowDone = b;
            if (!b) {
                if("Anon".equals(AbstractDungeon.player.name)){
                    boolean shouldShowButton = false;
                    int button = RoomEventDialog.optionList.size();
                    room.event.roomEventText.clear();
                    if (Settings.language == Settings.GameLanguage.ZHS)
                        room.event.roomEventText.addDialogOption(" #b“爱音……醒醒……” ");
                    else
                        room.event.roomEventText.addDialogOption(" “Anon……Wake up quickly……” ");

                }
            }
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "<ctor>", paramtypez = {boolean.class})
    public static class FixEventImage {
        @SpirePostfixPatch
        public static void Postfix(NeowEvent e, boolean b) {
            e.imageEventText.clear();
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "buttonEffect")
    public static class MaybeStartRewards {
        @SpirePrefixPatch
        public static void Prefix(AbstractEvent e, int buttonPressed) {
            if ("Anon".equals(AbstractDungeon.player.name)) {
                try {
                    for (com.megacrit.cardcrawl.vfx.AbstractGameEffect f : AbstractDungeon.effectList) {
                        if ((f instanceof InfiniteSpeechBubble)) {
                            ((InfiniteSpeechBubble) f).dismiss();
                        }
                    }

                    Field screenNumField = NeowEvent.class.getDeclaredField("screenNum");
                    //false有初始捏奥选项，true没有
                    screenNumField.setAccessible(true);
                    //添加遗物
                    if (!AbstractDungeon.player.hasRelic("smartPhone")) {
                        MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
                        System.out.println("MapRoomNode currNode+"+currNode.x+"         "+currNode.y);
//                    (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                        //关闭气泡
                        for (com.megacrit.cardcrawl.vfx.AbstractGameEffect f : AbstractDungeon.effectList) {
                            if ((f instanceof InfiniteSpeechBubble)) {
                                ((InfiniteSpeechBubble) f).dismiss();
                            }
                        }

//                        (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                        smartPhoneSearchNumbers.test();


//                    (AbstractDungeon.getCurrRoom()).rewards.clear();
//                    AbstractDungeon.getCurrRoom().addRelicToRewards(new smartPhone());
//                    AbstractDungeon.combatRewardScreen.open();


//                    smartPhone smartPhone = new smartPhone();
//                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) smartPhone);

                        //进入事件
//                e.imageEventText.clear();
//                AbstractEvent info = new AnonAwake();
//                AbstractDungeon.getCurrRoom().event = info;
//                AbstractDungeon.getCurrRoom().event.onEnterRoom();

//                    int sn = screenNumField.getInt(e);
//                    maybeStartRewards(e, buttonPressed, screenNumField, sn);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    static boolean acceptableScreenNum(int sn) {
        return (sn == 0 || sn == 1 || sn == 2 || (Settings.isTrial && sn == 10));
    }
    static void maybeStartRewards(AbstractEvent e, int buttonPressed, Field screenNumField, int sn) throws IllegalAccessException {

//            screenNumField.setInt(e, 99);

    }


}

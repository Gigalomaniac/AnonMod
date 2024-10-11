//package patch.smartphone;
//
//
//
//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.events.shrines.Nloth;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import relics.smartPhone;
//
//import java.lang.reflect.Field;
//
//@SpirePatch(
//        clz = Nloth.class,
//        method = "buttonEffect"
//)
//public class smartphonePatch2 {
//    public smartphonePatch2() {
//    }
//
//    @SpireInsertPatch(
//            loc = 73
//    )
//    public static void Insert1(Nloth _instance, int buttonPressed) {
//        try {
//            Field choice1Field = Nloth.class.getDeclaredField("choice1");
//            choice1Field.setAccessible(true);
//            AbstractRelic choice1 = (AbstractRelic) choice1Field.get(_instance);
//            if (choice1 instanceof smartPhone) {
//                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), new smartPhone());
//            }
//        } catch (IllegalAccessException | NoSuchFieldException var4) {
//            var4.printStackTrace();
//        }
//
//    }
//
//    @SpireInsertPatch(
//            loc = 95
//    )
//    public static void Insert2(Nloth _instance, int buttonPressed) {
//        try {
//            Field choice1Field = Nloth.class.getDeclaredField("choice1");
//            choice1Field.setAccessible(true);
//            AbstractRelic choice1 = (AbstractRelic) choice1Field.get(_instance);
//            if (choice1 instanceof smartPhone) {
//                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), new smartPhone());
//            }
//        } catch (IllegalAccessException | NoSuchFieldException var4) {
//            var4.printStackTrace();
//        }
//
//    }
//}
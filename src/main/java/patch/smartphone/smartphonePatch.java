//package patch.smartphone;
//
//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.neow.NeowReward;
//import relics.smartPhone;
//
//@SpirePatch(
//        clz = NeowReward.class,
//        method = "activate"
//)
//public class smartphonePatch {
//    public smartphonePatch() {
//    }
//
//    @SpireInsertPatch(
//            loc = 329
//    )
//    public static void Insert() {
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new smartPhone());
//    }
//}

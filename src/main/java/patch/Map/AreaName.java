//package patch.Map;
//
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
//import star.BeyondTheStar;
//
//@SpirePatch(
//        clz = DungeonTransitionScreen.class,
//        method = "setAreaName"
//)
//public class AreaName {
//    public AreaName() {
//    }
//
//    @SpirePrefixPatch
//    public static SpireReturn<Void> setAreaName(DungeonTransitionScreen _inst,String key) {
//        System.out.println("key"+key);
//     if (key.equals(BeyondTheStar.ID)){
//         AbstractDungeon.name = "AnotherStory";
//         AbstractDungeon.levelNum = "星遗物";
//         return SpireReturn.Return();
//     }
//
//        return SpireReturn.Continue();
//    }
//}

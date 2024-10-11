package pathes;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
public class LastWordPatch
    {
        public static boolean notShowPlayerPowerTip = false;

        @SpirePatch2(clz = com.megacrit.cardcrawl.characters.AbstractPlayer.class, method = "renderPowerTips")
        public static class AbstractPlayerPowerTipPatch
        {
            public static SpireReturn<Void> Prefix() {
                if (LastWordPatch.notShowPlayerPowerTip) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            } }

        @SpirePatch2(clz = com.megacrit.cardcrawl.monsters.AbstractMonster.class, method = "renderTip")
        public static class AbstractMonsterPowerTipPatch {
            public static SpireReturn<Void> Prefix() {
                if (LastWordPatch.notShowPlayerPowerTip) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

}

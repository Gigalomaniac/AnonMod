package patch.boss;


import Mod.AnonMod;
import characters.char_Anon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import java.util.Collections;
import java.util.Random;

@SpirePatch(
        clz = TheCity.class,
        method = "initializeBoss"
)
public class Act2BossPatch {
    public Act2BossPatch() {
    }

    @SpirePrefixPatch
    public static SpireReturn<Void> bosslist2() {
//        if (!AnonMod.forcecanclebosslogic) {
            TheCity.bossList.clear();
            if (Settings.isDailyRun) {
                TheCity.bossList.add("Automaton");
                TheCity.bossList.add("Collector");
                TheCity.bossList.add("Champ");
                Collections.shuffle(TheCity.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
                AbstractDungeon.bossList.remove(AbstractDungeon.bossList.size() - 1);
            } else if (AnonMod.onlymodboss  && AbstractDungeon.player instanceof char_Anon) {
                TheCity.bossList.add("Poppin'Party");
                TheCity.bossList.add("Roselia");
                TheCity.bossList.add("Poppin'Party");
                TheCity.bossList.add("Roselia");
                Collections.shuffle(TheCity.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
            } else {
                TheCity.bossList.add("Poppin'Party");
                TheCity.bossList.add("Roselia");
                TheCity.bossList.add("Automaton");
                TheCity.bossList.add("Collector");
                TheCity.bossList.add("Champ");
                Collections.shuffle(TheCity.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
                AbstractDungeon.bossList.remove(AbstractDungeon.bossList.size() - 1);
            }
//        }

        return SpireReturn.Return();
    }
}

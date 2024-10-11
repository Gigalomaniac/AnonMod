package patch.boss;

import Mod.AnonMod;
import characters.char_Anon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;

import java.util.Collections;
import java.util.Random;

@SpirePatch(
        clz = Exordium.class,
        method = "initializeBoss"
)
public class Act1BossPatch {
    public Act1BossPatch() {
    }

    @SpirePrefixPatch
    public static SpireReturn<Void> bosslist1() {
//        if (!AnonMod.forcecanclebosslogic) {
            Exordium.bossList.clear();
            if (Settings.isDailyRun) {
                Exordium.bossList.add("The Guardian");
                Exordium.bossList.add("Hexaghost");
                Exordium.bossList.add("Slime Boss");
                Collections.shuffle(Exordium.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
                AbstractDungeon.bossList.remove(AbstractDungeon.bossList.size() - 1);
            } else if (AnonMod.onlymodboss && AbstractDungeon.player instanceof char_Anon) {
                Exordium.bossList.add("Nina");
                Exordium.bossList.add("Bocchi");
                Exordium.bossList.add("Nina");
                Exordium.bossList.add("Bocchi");
            } else {
                Exordium.bossList.add("Nina");
                Exordium.bossList.add("Bocchi");
                Exordium.bossList.add("The Guardian");
                Exordium.bossList.add("Hexaghost");
                Exordium.bossList.add("Slime Boss");
                Collections.shuffle(Exordium.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
                AbstractDungeon.bossList.remove(AbstractDungeon.bossList.size() - 1);
            }
//        }
        System.out.println(Exordium.bossList);
        return SpireReturn.Return();
    }
}

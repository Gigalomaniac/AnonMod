package patch.boss;


import Mod.AnonMod;
import characters.char_Anon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
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
                TheCity.bossList.add("Pastel*Palettes");
                TheCity.bossList.add("Poppin'Party");
                TheCity.bossList.add("Roselia");
                TheCity.bossList.add("Pastel*Palettes");
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
        switch (TheCity.bossList.get(0)){
            case "Poppin'Party":
                AnonMod.saves.setString("Stage2","pp");
                break;
            case "Roselia":
                AnonMod.saves.setString("Stage2","ros");
                break;
            default:
                AnonMod.saves.setString("Stage2","pp");
                break;
        }
        return SpireReturn.Return();
    }
}

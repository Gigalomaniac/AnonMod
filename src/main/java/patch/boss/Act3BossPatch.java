package patch.boss;


import Mod.AnonMod;
import characters.char_Anon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@SpirePatch(
        clz = TheBeyond.class,
        method = "initializeBoss"
)
public class Act3BossPatch {
    public Act3BossPatch() {
    }

    @SpirePrefixPatch
    public static SpireReturn<Void> bosslist3() {
//        if (!AnonMod.forcecanclebosslogic) {
            TheBeyond.bossList.clear();
            if (Settings.isDailyRun) {
                TheBeyond.bossList.add("Awakened One");
                TheBeyond.bossList.add("Time Eater");
                TheBeyond.bossList.add("Donu and Deca");
                Collections.shuffle(TheBeyond.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
                TheBeyond.bossList.remove(AbstractDungeon.bossList.size() - 1);
            } else if (AnonMod.onlymodboss && AbstractDungeon.player instanceof char_Anon) {
                TheBeyond.bossList.add("ShoujoKageki");
                TheBeyond.bossList.add("DustAnon");
                TheBeyond.bossList.add("Mika");
                Collections.shuffle(TheBeyond.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));


                ArrayList<String> prelist = new ArrayList();
                prelist.add("ShoujoKageki");
                prelist.add("DustAnon");
                Collections.shuffle(prelist, new Random(AbstractDungeon.monsterRng.randomLong()));
                AbstractDungeon.bossList.add(prelist.get(0));
                AbstractDungeon.bossList.add(prelist.get(1));
                AbstractDungeon.bossList.add(prelist.get(0));
            } else {
                TheBeyond.bossList.add("ShoujoKageki");
                TheBeyond.bossList.add("DustAnon");
                TheBeyond.bossList.add("Awakened One");
                TheBeyond.bossList.add("Time Eater");
                TheBeyond.bossList.add("Donu and Deca");
                Collections.shuffle(TheBeyond.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
                TheBeyond.bossList.remove(AbstractDungeon.bossList.size() - 1);
            }
//        }
            System.out.println(TheBeyond.bossList);
        switch (TheBeyond.bossList.get(0)){
            case "ShoujoKageki":
                AnonMod.saves.setString("Stage3","Karen");
                break;
            case "DustAnon":
                AnonMod.saves.setString("Stage3","dust");
                break;
            default:
                AnonMod.saves.setString("Stage3","Karen");
                break;
        }
            return SpireReturn.Return();
        }
//    public static SpireReturn<Void> Prefix(TheBeyond ad) {
//        if (AnonMod.onlymodboss  && AbstractDungeon.player instanceof char_Anon) {
//            AbstractDungeon.bossList.clear();
//            AbstractDungeon.bossList.add("ShoujoKageki");
//            AbstractDungeon.bossList.add("DustAnon");
//            Collections.shuffle(AbstractDungeon.bossList, new Random(AbstractDungeon.monsterRng.randomLong()));
////            if (AbstractDungeon.bossList.size() < 3)
////                AbstractDungeon.bossList.add((String) AbstractDungeon.bossList.get(1));
//            System.out.println(TheBeyond.bossList);
//            return SpireReturn.Return(null);
//        }else {
//            return SpireReturn.Continue();
//        }

}

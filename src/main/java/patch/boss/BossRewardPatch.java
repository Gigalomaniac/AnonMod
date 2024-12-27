package patch.boss;

import ExcessiveFantasy.ExcessiveFantasyRestRoom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;

import java.lang.reflect.Field;
import java.util.ArrayList;

@SpirePatch(
        clz = CombatRewardScreen.class,
        method = "setupItemReward"
)
public class BossRewardPatch {
    public BossRewardPatch() {
    }
//    static CampfireUI campfireUI = new CampfireUI();
    @SpirePostfixPatch
    public static SpireReturn<Void> BossRewardPatch(CombatRewardScreen __instance) throws NoSuchFieldException, IllegalAccessException {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss &&AbstractDungeon.actNum==3) {
            __instance.rewards.removeIf(rewardItem -> rewardItem.cards.size()>0);
            return SpireReturn.Continue();
        }
        return SpireReturn.Continue();
    }



}

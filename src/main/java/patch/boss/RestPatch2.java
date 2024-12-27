package patch.boss;

import ExcessiveFantasy.ExcessiveFantasyRestRoom;
import bossRoom.effect.LatterEffect;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepScreenCoverEffect;


@SpirePatch(
        clz = RestOption.class,
        method = "useOption"
)
public class RestPatch2 {
    public RestPatch2() {
    }
    @SpirePrefixPatch
    public static SpireReturn<Void> RestExcessiveFantasyCampfireSleepEffect(RestOption __instance) throws NoSuchFieldException, IllegalAccessException {
        if (AbstractDungeon.getCurrRoom() instanceof ExcessiveFantasyRestRoom) {
            CardCrawlGame.sound.play("SLEEP_BLANKET");
            AbstractDungeon.effectList.add(new ExcessiveFantasyCampfireSleepEffect());
            for(int i = 0; i < 30; ++i) {
                AbstractDungeon.topLevelEffects.add(new CampfireSleepScreenCoverEffect());
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            MapRoomNode node = new MapRoomNode(-1, 15);
            node.room = new MonsterRoomBoss();
            node.room.rewardAllowed = false;
            AbstractDungeon.nextRoom = node;
            CardCrawlGame.music.fadeOutTempBGM();
            AbstractDungeon.pathX.add(1);
            AbstractDungeon.pathY.add(15);
            AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.nextRoomTransitionStart();
            },2f));
//            ++CardCrawlGame.metricData.campfire_rested;
//            CardCrawlGame.metricData.addCampfireChoiceData("REST");
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }



}

package patch;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.VictoryRoom;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import relics.StarAnonBike;

import java.util.ArrayList;

public class StarAnonBikePatches
{
    @SpirePatch(clz = MapRoomNode.class, method = "wingedIsConnectedTo")
    public static class WingedIsConnectedTo {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Boolean> Insert(MapRoomNode _instance, MapRoomNode node) {
            ArrayList<MapEdge> edges = _instance.getEdges();
            for (MapEdge edge : edges) {
                if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) && (!(AbstractDungeon.getCurrRoom() instanceof VictoryRoom))) {
                    if(AbstractDungeon.player.hasRelic(StarAnonBike.ID) && !AbstractDungeon.player.getRelic(StarAnonBike.ID).usedUp){
//                        if (node.y +2 <= edge.dstY && AbstractDungeon.player.hasRelic(StarAnonBike.ID) ) {
//                        if (node.y +2 <= edge.dstY ) {
                            if (node.y +2 <= edge.dstY && edge.dstY <= node.y + 6) {
                            return SpireReturn.Return(Boolean.valueOf(true));
                        }
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }




//    @SpirePatch(clz = MapRoomNode.class, method = "update")
//    public static class MapRoomNodeUpdate
//    {
//        @SpireInsertPatch(rloc = 74)
//        public static SpireReturn<Boolean> Insert(MapRoomNode _instance) {
//            if (AbstractDungeon.player.hasPotion(TransitionGenerator.POTION_ID) > 0) {
//                (AbstractDungeon.player.getRelic("WingedGreaves")).counter++;
//            }
//
//            return SpireReturn.Continue();
//        }
//    }




    @SpirePatch(clz = MapRoomNode.class, method = "update")
    public static class MapRoomNodeUpdate2
    {
        @SpireInsertPatch(rloc = 68, localvars = {"normalConnection", "wingedConnection"})
        public static SpireReturn<Boolean> Insert(MapRoomNode _instance,@ByRef boolean[] normalConnection,@ByRef boolean[] wingedConnection) {
            if (!normalConnection[0] && wingedConnection[0]) {
                    for (AbstractRelic naire : AbstractDungeon.player.relics){
                        if(naire.relicId.equals(StarAnonBike.ID)){
                            naire.flash();
                            naire.usedUp();
                            wingedConnection[0] = false;
                            normalConnection[0] = true;
                            break;
                        }
                    }
            }
            return SpireReturn.Continue();
        }
    }



//    @SpirePatch(clz = DungeonMapScreen.class, method = "updateControllerInput")
//    public static class MapUpdateControllerInput
//    {
//        @SpireInsertPatch(rloc = 31, localvars = {"flightMatters"})
//        public static SpireReturn<Boolean> Insert(DungeonMapScreen _instance, @ByRef boolean[] flightMatters) {
//            if (AbstractDungeon.player.hasRelic(StarAnonBike.ID))
//                flightMatters[0] = true;
//            return SpireReturn.Continue();
//        }
//    }


//    @SpirePatch(clz = AbstractDungeon.class, method = "getShrine")
//    public static class TheCityPatch {
//        @SpireInsertPatch(rloc = 35, localvars = {"e"})
//        public static void Insert(Random rng, String e) {}
//    }
}

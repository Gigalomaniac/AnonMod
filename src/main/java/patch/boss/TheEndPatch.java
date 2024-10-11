package patch.boss;


import bossRoom.AnonVictoryRoom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.*;
import rooms.Act4EventRoom;

import java.util.ArrayList;
public class TheEndPatch {
    public TheEndPatch() {
    }

    @SpirePatch(
            clz = TheEnding.class,
            method = "initializeBoss"
    )
    public static class InsertKMR {
        public InsertKMR() {
        }

        @SpirePrefixPatch
        public static SpireReturn DeleteHeart(TheEnding ending) {
            return SpireReturn.Return((Object)null);
        }
    }

//    @SpirePatch(
//            clz = Exordium.class,
//            method = "initializeBoss"
//    )
//    public static class InsertSV {
//        @SpirePatch(clz = Exordium.class, method = "initializeBoss")
//        public static class initializeBossPatch1
//        {
//            public static SpireReturn<Void> Prefix(Exordium Exordium) {
//                if (!AnonMod.onlymodboss){
//                    System.out.println("!AnonMod.onlymodboss");
//                    return SpireReturn.Continue();}
//                if (AnonMod.onlymodboss) {
//                    return SpireReturn.Continue();
//                }
//                System.out.println("AnonMod.onlymodboss");
//                AbstractDungeon.bossList.clear();
//                AbstractDungeon.bossList.add("Nina");
//                AbstractDungeon.bossList.add("Nina");
//                return SpireReturn.Return(null);
//
//            }
//        }
//    }

    @SpirePatch(
            clz = TheEnding.class,
            method = "generateSpecialMap"
    )
    public static class TheEnding_GenerateSpecialMap {
        public TheEnding_GenerateSpecialMap() {
        }

        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix (TheEnding __instance) {
            AbstractDungeon.map = new ArrayList();
            ArrayList<MapRoomNode> row1 = new ArrayList();
            MapRoomNode restNode = new MapRoomNode(3, 0);
            restNode.room = new RestRoom();
            MapRoomNode shopNode = new MapRoomNode(3, 1);
            shopNode.room = new ShopRoom();
            MapRoomNode enemyNode = new MapRoomNode(4, 2);
            enemyNode.room = new MonsterRoomElite();
            MapRoomNode Act4EventRoom = new MapRoomNode(3, 2);
            Act4EventRoom.room = new Act4EventRoom(Act4EventRoom);
            Act4EventRoom.room.setMapImg(ImageMaster.loadImage("img/UI/special.png"), ImageMaster.loadImage("img/UI/special.png"));
            MapRoomNode bossNode = new MapRoomNode(3, 3);
            bossNode.room = new MonsterRoomBoss();
            MapRoomNode victoryNode = new MapRoomNode(3, 4);
            victoryNode.room = new AnonVictoryRoom();
            connectNode(restNode, shopNode);
//            connectNode(shopNode, enemyNode);
            connectNode(Act4EventRoom, enemyNode);
            connectNode(shopNode, Act4EventRoom);
            enemyNode.addEdge(new MapEdge(enemyNode.x, enemyNode.y, enemyNode.offsetX, enemyNode.offsetY, bossNode.x, bossNode.y+1, bossNode.offsetX, bossNode.offsetY, false));
            Act4EventRoom.addEdge(new MapEdge(Act4EventRoom.x, Act4EventRoom.y, Act4EventRoom.offsetX, Act4EventRoom.offsetY, bossNode.x, bossNode.y+1, bossNode.offsetX, bossNode.offsetY, false));
            row1.add(new MapRoomNode(0, 0));
            row1.add(new MapRoomNode(1, 0));
            row1.add(new MapRoomNode(2, 0));
            row1.add(restNode);
            row1.add(new MapRoomNode(4, 0));
            row1.add(new MapRoomNode(5, 0));
            row1.add(new MapRoomNode(6, 0));
            ArrayList<MapRoomNode> row2 = new ArrayList();
            row2.add(new MapRoomNode(0, 1));
            row2.add(new MapRoomNode(1, 1));
            row2.add(new MapRoomNode(2, 1));
            row2.add(shopNode);
            row2.add(new MapRoomNode(4, 1));
            row2.add(new MapRoomNode(5, 1));
            row2.add(new MapRoomNode(6, 1));
            ArrayList<MapRoomNode> row3 = new ArrayList();
            row3.add(new MapRoomNode(0, 2));
            row3.add(new MapRoomNode(1, 2));
            row3.add(enemyNode);
            row3.add(new MapRoomNode(3, 2));
            row3.add(Act4EventRoom);
            row3.add(new MapRoomNode(5, 2));
            row3.add(new MapRoomNode(6, 2));
            ArrayList<MapRoomNode> row4 = new ArrayList();
            row4.add(new MapRoomNode(0, 3));
            row4.add(new MapRoomNode(1, 3));
            row4.add(new MapRoomNode(2, 3));
            row4.add(bossNode);
            row4.add(new MapRoomNode(4, 3));
            row4.add(new MapRoomNode(5, 3));
            row4.add(new MapRoomNode(6, 3));
            ArrayList<MapRoomNode> row5 = new ArrayList();
            row5.add(new MapRoomNode(0, 4));
            row5.add(new MapRoomNode(1, 4));
            row5.add(new MapRoomNode(2, 4));
            row5.add(victoryNode);
            row5.add(new MapRoomNode(4, 4));
            row5.add(new MapRoomNode(5, 4));
            row5.add(new MapRoomNode(6, 4));
            AbstractDungeon.map.add(row1);
            AbstractDungeon.map.add(row2);
            AbstractDungeon.map.add(row3);
            AbstractDungeon.map.add(row4);
            AbstractDungeon.map.add(row5);
            return SpireReturn.Return();
        }

        private static void connectNode(MapRoomNode src, MapRoomNode dst) {
            src.addEdge(new MapEdge(src.x, src.y, src.offsetX, src.offsetY, dst.x, dst.y, dst.offsetX, dst.offsetY, false));
        }
    }
}

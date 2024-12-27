/*    */ package ExcessiveFantasy;
/*    */
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.dungeons.TheEnding;
/*    */ import com.megacrit.cardcrawl.map.MapEdge;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.NotFoundException;
/*    */ import javassist.expr.ExprEditor;
/*    */ import javassist.expr.Instanceof;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SakikoShopTheEndingPatch
/*    */ {
/*    */   @SpirePatch(clz = TheEnding.class, method = "generateSpecialMap")
/*    */   public static class generateSpecialMapPatch
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void postfixPatch(TheEnding _instance, ArrayList<ArrayList<MapRoomNode>> ___map) {
///* 27 */       if (BATwinsMod.saveHelper.values.hasSoraPhone) {
///* 28 */         MapRoomNode node = new MapRoomNode(0, 1);
///*    */
///* 30 */         node.room = new BATwinsSoraShopRoom();
///* 31 */         MapRoomNode restNode = (MapRoomNode)((ArrayList)___map.get(0)).get(3);
///* 32 */         MapRoomNode enemyNode = (MapRoomNode)((ArrayList)___map.get(1)).get(3);
///* 33 */         BATwinsTheEndingPatch.connectNode(restNode, node);
///* 34 */         BATwinsTheEndingPatch.connectNode(node, enemyNode);
///* 35 */         for (int i = 0; i < ((ArrayList)___map.get(1)).size(); i++) {
///* 36 */           if (((MapRoomNode)((ArrayList)___map.get(true)).get(i)).room == null) {
///* 37 */             ((ArrayList)___map.get(1)).set(i, node);
///*    */             break;
///*    */           }
///*    */         }
///*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   private static void connectNode(MapRoomNode src, MapRoomNode dst) { src.addEdge(new MapEdge(src.x, src.y, src.offsetX, src.offsetY, dst.x, dst.y, dst.offsetX, dst.offsetY, false)); }
/*    */   
/*    */   @SpirePatch(clz = com.megacrit.cardcrawl.dungeons.AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {com.megacrit.cardcrawl.saveAndContinue.SaveFile.class})
/*    */   public static class AbstractDungeonNextRoomTransitionPatch
/*    */   {
/*    */     @SpireInstrumentPatch
/*    */     public static ExprEditor Instrument() {
/* 55 */       return new ExprEditor() {
/*    */           public void edit(Instanceof i) throws CannotCompileException {
/*    */             try {
/* 58 */               if (i.getType().getName().equals(com.megacrit.cardcrawl.rooms.EventRoom.class.getName()))
/* 59 */                 i.replace(String.format("{ $_ = $proceed($$) && !($1 instanceof %s); }", new Object[] { SakikoShopRoom.class.getName() }));
/* 60 */             } catch (NotFoundException notFoundException) {}
/*    */           }
/*    */         };
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\BlueArchive_SaibaSisters_Mod.jar!\baModDeveloper\patch\BATwinsTheEndingPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
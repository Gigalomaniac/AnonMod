/*    */ package patch;
/*    */ import Mod.AnonMod;
/*    */ import basemod.ReflectionHacks;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.PrismaticShard;
/*    */
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapRoomNodePatch
/*    */ {
/*    */   @SpirePatch(clz = MapRoomNode.class, method = "update")
/*    */   public static class PatchUpdate
/*    */   {
/*    */     public static void Prefix(MapRoomNode node) {
/* 22 */       float animWaitTimer = ((Float)ReflectionHacks.getPrivate(node, MapRoomNode.class, "animWaitTimer")).floatValue();
/* 23 */       if (animWaitTimer - Gdx.graphics.getDeltaTime() < 0.0F && animWaitTimer != 0.0F) {
/* 24 */         AbstractDungeon.shopRelicPool.remove("PrismaticShard");
/* 25 */         if (AbstractDungeon.firstRoomChosen)
/* 26 */           AnonMod.myActions.add(new AbstractGameAction() {
/* 27 */                 private float t = 0.3F;
/*    */ 
/*    */                 
/*    */                 public void update() {
/* 31 */                   this.t -= Gdx.graphics.getDeltaTime();
/* 32 */                   this.isDone = (this.t < 0.0F);
/* 33 */                   if ((AbstractDungeon.getCurrMapNode()).room instanceof com.megacrit.cardcrawl.rooms.ShopRoom && this.isDone) {
                            if((AbstractDungeon.actNum == 1 &&  AnonMod.saves.getInt("shopCount") == 0 )||(AbstractDungeon.actNum == 2 &&  AnonMod.saves.getInt("shopCount") == 1 ))
/* 34 */                     AnonMod.saves.setInt("shopCount", AnonMod.saves.getInt("shopCount") + 1);
/* 35 */                     System.out.println("increase shop count. current shop count: " + AnonMod.saves.getInt("shopCount"));
/*    */                     try {
/* 37 */                       AnonMod.saves.save();
/* 38 */                     } catch (IOException e) {
/* 39 */                       throw new RuntimeException(e);
/*    */                     }
/*    */                   } 
/*    */                 }
/*    */               }); 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }

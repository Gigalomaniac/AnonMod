/*    */ package storm;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TogglePlayerElecticShaderAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private static final float EFFECT_DUR = 0.5F;
/*    */   
/*    */   public void update() {
/* 16 */     if (this.duration == 0.5F) {
/* 17 */       StormUtil.activePlayerLightning = true;
/*    */     }
/* 19 */     StormUtil.brightTime = this.duration;
/* 20 */     if (this.duration < 0.0F) {
/* 21 */       StormUtil.activePlayerLightning = false;
/* 22 */       this.isDone = true;
/*    */     } 
/* 24 */     this.duration -= Gdx.graphics.getRawDeltaTime();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\素材\common\3142318909\SpireBiomes.jar!\spireMapOverhaul\zones\storm\actions\TogglePlayerElecticShaderAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
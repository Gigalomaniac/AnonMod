/*    */ package vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class guangEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 19 */   private static Texture GhostFireTexture = new Texture ("img/othervfx/guang_2.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == 3.0F)
/*    */     {
/* 31 */       CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */ 
/*    */     
/* 39 */     if (this.duration > 1.5F) {
/*    */       
/* 41 */       this.color.a = Interpolation.pow5In.apply(0.5F, 0.0F, (this.duration - 1.5F) / 1.5F);
/*    */     }
/*    */     else {
/*    */       
/* 45 */       this.color.a = Interpolation.exp10In.apply(0.0F, 0.5F, this.duration / 1.5F);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 50 */     if (this.duration < 0.0F) {
/*    */       
/* 52 */       this.color.a = 0.0F;
/*    */       
/* 54 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     sb.setColor(this.color);
/*    */     
/* 64 */     sb.setBlendFunction(770, 1);
/*    */     
/* 66 */     sb.draw(GhostFireTexture, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */     
/* 68 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\star\liuLZmod.jar!\liuLZmod\vfx\guangEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
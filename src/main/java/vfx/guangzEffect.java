/*    */ package vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class guangzEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 22 */   private static TextureRegion GhostFireTexture = new TextureRegion(new Texture(("img/othervfx/moryy_0.png")));
/*    */   
/*    */   private float startX;
/*    */   private float endX;
/*    */   private float x;
/*    */   
/*    */   public guangzEffect() {
/* 29 */     this.morTimer = 0.05F;
/*    */ 
/*    */     
/* 32 */     this.duration = 1.0F;
/* 33 */     this.startX = -GhostFireTexture.getRegionWidth();
/* 34 */     this.endX = Settings.WIDTH;
/* 35 */     this.x = this.startX;
/* 36 */     this.y = Settings.HEIGHT / 2.0F;
/* 37 */     this.color = Color.WHITE.cpy();
/* 38 */     this.color.a = 1.0F;
/* 39 */     this.timer = 0.0F;
/*    */   }
/*    */   private float y; private float timer; private float morTimer;
/*    */   
/*    */   public void update() {
/* 44 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 45 */     this.timer += Gdx.graphics.getDeltaTime();
/* 46 */     if (this.timer >= this.morTimer) {
/* 47 */       this.timer -= this.morTimer;
/* 48 */       AbstractDungeon.effectsQueue.add(new morEffect(this.x, this.y));
/*    */     } 
/*    */     
/* 51 */     this.x = Interpolation.linear.apply(this.startX, this.endX, 1.0F - this.duration);
/*    */     
/* 53 */     if (this.duration < 0.0F) {
/* 54 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 60 */     sb.setColor(this.color);
/*    */     
/* 62 */     sb.draw(GhostFireTexture, this.x, 0.0F);
/*    */     
/* 64 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\star\liuLZmod.jar!\liuLZmod\vfx\guangzEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
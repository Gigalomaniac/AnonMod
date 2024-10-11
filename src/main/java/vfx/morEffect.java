/*    */ package vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class morEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 17 */   private static TextureRegion[] MorTextures = { new TextureRegion(
/* 18 */        new Texture("img/othervfx/moryy_6.png")), new TextureRegion(
/* 19 */         new Texture ("img/othervfx/moryy_7.png")), new TextureRegion(
/* 20 */         new Texture ("img/othervfx/moryy_8.png")) };
/*    */   
/*    */   private final TextureRegion morTexture;
/*    */   
/*    */   private float x;
/*    */   private float y;
/*    */   private float timer;
/*    */   private float alpha;
/*    */   
/*    */   public morEffect(float x, float y) {
/* 30 */     this.x = x;
/* 31 */     this.y = y;
/* 32 */     this.timer = 0.0F;
/* 33 */     this.alpha = 1.0F;
/* 34 */     this.duration = 0.3F;
/* 35 */     int randomIndex = MathUtils.random(MorTextures.length - 1);
/* 36 */     this.morTexture = MorTextures[randomIndex];
/*    */   }
/*    */   
/*    */   public void update() {
/* 40 */     this.timer += Gdx.graphics.getDeltaTime();
/*    */     
/* 42 */     if (this.timer < 0.1F) {
/* 43 */       this.alpha = 1.0F;
/*    */     } else {
/* 45 */       this.alpha = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.1F) / 0.2F);
/*    */     } 
/*    */     
/* 48 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 50 */     if (this.duration < 0.0F) {
/* 51 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     Color color = Color.WHITE.cpy();
/* 57 */     color.a = this.alpha;
/* 58 */     sb.setColor(color);
/*    */     
/* 60 */     sb.draw(this.morTexture, this.x - (this.morTexture.getRegionWidth() / 2), this.y - (this.morTexture.getRegionHeight() / 2));
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\star\liuLZmod.jar!\liuLZmod\vfx\morEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
/*     */ package vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */
/*     */ 
/*     */ public class piaolwEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*     */   private int frame;
/*     */   private float vY;
/*     */   private float vX;
/*     */   private float scaleY;
/*     */   private float animTimer;
/*     */   private static final int W = 32;
/*  23 */   private static Texture piao = new Texture("img/othervfx/sy_5.png"); public piaolwEffect() {
/*     */     this.frame = 0;
/*     */     this.animTimer = 0.05F;
/*  26 */     this.x = MathUtils.random(100.0F * Settings.scale, 1820.0F * Settings.scale);
/*  27 */     this.y = Settings.HEIGHT + MathUtils.random(20.0F, 300.0F) * Settings.scale;
/*     */     
/*  29 */     this.frame = MathUtils.random(8);
/*  30 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/*  31 */     this.scale = MathUtils.random(1.0F, 2.5F);
/*  32 */     this.scaleY = MathUtils.random(1.0F, 1.2F);
/*  33 */     if (this.scale < 1.5F) {
/*  34 */       this.renderBehind = true;
/*     */     }
/*  36 */     this.vY = MathUtils.random(200.0F, 300.0F) * this.scale * Settings.scale;
/*  37 */     this.vX = MathUtils.random(-100.0F, 100.0F) * this.scale * Settings.scale;
/*  38 */     this.scale *= Settings.scale;
/*  39 */     if (MathUtils.randomBoolean()) {
/*  40 */       this.rotation += 180.0F;
/*     */     }
/*  42 */     this.color = new Color(1.0F, MathUtils.random(0.7F, 0.9F), MathUtils.random(0.7F, 0.9F), 1.0F);
/*  43 */     this.duration = 4.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  47 */     this.y -= this.vY * Gdx.graphics.getDeltaTime();
/*  48 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  49 */     this.animTimer -= Gdx.graphics.getDeltaTime() / this.scale;
/*  50 */     if (this.animTimer < 0.0F) {
/*  51 */       this.animTimer += 0.05F;
/*  52 */       this.frame++;
/*  53 */       if (this.frame > 11) {
/*  54 */         this.frame = 0;
/*     */       }
/*     */     } 
/*     */     
/*  58 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  59 */     if (this.duration < 0.0F) {
/*  60 */       this.isDone = true;
/*  61 */     } else if (this.duration < 1.0F) {
/*  62 */       this.color.a = this.duration;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  67 */     sb.setColor(this.color);
/*  68 */     switch (this.frame) {
/*     */       case 0:
/*  70 */         renderImg(sb, piao, false, false);
/*     */         break;
/*     */       case 1:
/*  73 */         renderImg(sb, piao, false, false);
/*     */         break;
/*     */       case 2:
/*  76 */         renderImg(sb, piao, false, false);
/*     */         break;
/*     */       case 3:
/*  79 */         renderImg(sb, piao, false, false);
/*     */         break;
/*     */       case 4:
/*  82 */         renderImg(sb, piao, true, true);
/*     */         break;
/*     */       case 5:
/*  85 */         renderImg(sb, piao, true, true);
/*     */         break;
/*     */       case 6:
/*  88 */         renderImg(sb, piao, true, true);
/*     */         break;
/*     */       case 7:
/*  91 */         renderImg(sb, piao, true, true);
/*     */         break;
/*     */       case 8:
/*  94 */         renderImg(sb, piao, true, true);
/*     */         break;
/*     */       case 9:
/*  97 */         renderImg(sb, piao, true, true);
/*     */         break;
/*     */       case 10:
/* 100 */         renderImg(sb, piao, false, false);
/*     */         break;
/*     */       case 11:
/* 103 */         renderImg(sb, piao, false, false);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderImg(SpriteBatch sb, Texture img, boolean flipH, boolean flipV) {
/* 115 */     sb.setBlendFunction(770, 1);
/* 116 */     sb.draw(img, this.x, this.y, 16.0F, 16.0F, 32.0F, 32.0F, this.scale, this.scale * this.scaleY, this.rotation, 0, 0, 32, 32, flipH, flipV);
/* 117 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\star\liuLZmod.jar!\liuLZmod\vfx\piaolwEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
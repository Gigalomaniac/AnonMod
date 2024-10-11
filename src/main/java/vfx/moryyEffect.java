/*    */ package vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class moryyEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 15 */   private float timer = 0.1F;

    public moryyEffect(float duration) {
        this.duration = duration;
    }

    /*    */
/*    */
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (this.duration == 2.0F) {
///* 23 */       AbstractDungeon.effectsQueue.add(new guangEffect());
/* 24 */       AbstractDungeon.effectsQueue.add(new guangzEffect());
/*    */     } 
/*    */     
/* 27 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 28 */     this.timer -= Gdx.graphics.getDeltaTime();
/*    */     
/* 30 */     if (this.timer < 0.0F) {
/* 31 */       this.timer += 0.1F;
/* 32 */       AbstractDungeon.effectsQueue.add(new piaolwEffect());
/* 33 */       AbstractDungeon.effectsQueue.add(new piaolwEffect());
/*    */     } 
/*    */     
/* 36 */     if (this.duration < 0.0F)
/* 37 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\star\liuLZmod.jar!\liuLZmod\vfx\moryyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
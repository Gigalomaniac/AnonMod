/*    */ package actions.movie;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class CanStopMediaPlayerAction
/*    */   extends AbstractGameAction {
/*    */   private final AbstractGameEffect effect;
/*    */   private Hitbox hb;
/*    */   public CanStopMediaPlayerAction(AbstractGameEffect effect) {
/* 13 */     this.effect = effect;
/* 14 */     AbstractDungeon.topLevelEffectsQueue.add(this.effect);
/*    */   }
/*    */
/*    */   public void update() {
/* 18 */     if (Gdx.input.isKeyPressed(61)) {
/* 19 */       this.effect.isDone = true;
/*    */     }
        if(this.effect.isDone){
                AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
                AbstractDungeon.victoryScreen = new VictoryScreen(null);
        }
/* 21 */     this.isDone = this.effect.isDone;
/*    */   }
/*    */ }


/* Location:              F:\madoka0.811.jar!\MadokaMod\action\CanStopMediaPlayerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
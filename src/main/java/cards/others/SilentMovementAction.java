package cards.others;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import vfx.animation.CurtainEffect;
import vfx.animation.FirstCurtainEffect;

public class SilentMovementAction
   extends AbstractGameAction
 {
  private int movement;
 private boolean did;

  public SilentMovementAction(int movement) {
//   CardCrawlGame.music.silenceTempBgmInstantly();
//     CardCrawlGame.music.silenceBGMInstantly();
    this.movement = movement;
    switch (this.movement) { case 1:
}

    this.startDuration = this.duration;
   this.did = false;
  }

   public void update() {
     switch (this.movement) {
       case 1:
         if (this.duration == this.startDuration) {
           AbstractDungeon.topLevelEffects.add(new CurtainEffect());
           CardCrawlGame.sound.play("movement_clap");
         }
         this.duration -= Gdx.graphics.getDeltaTime();
         if (this.duration <= this.startDuration - 6.0F &&
           !this.did) {
           CardCrawlGame.sound.play("movement_1");
           this.did = true;
         }

         if (this.duration < 0.0F){
             this.isDone = true;
            }
           break;
         case 0:
             if (this.duration == this.startDuration) {
                 AbstractDungeon.topLevelEffects.add(new FirstCurtainEffect());
                 CardCrawlGame.sound.play("movement_clap");
             }
             this.duration -= Gdx.graphics.getDeltaTime();
             if (this.duration <= this.startDuration - 6.0F &&
                     !this.did) {
                 CardCrawlGame.sound.play("movement_1");
                 this.did = true;
             }

             if (this.duration < 0.0F){
                 this.isDone = true;
             }
             break;
   }
 }}


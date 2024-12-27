 package actions.movie;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

 public class CanStopMediaPlayerAction
   extends AbstractGameAction {
   private final AbstractGameEffect effect;
   private Hitbox hb;
   public CanStopMediaPlayerAction(AbstractGameEffect effect) {
     this.effect = effect;
     AbstractDungeon.topLevelEffectsQueue.add(this.effect);
   }

   public void update() {
     if (Gdx.input.isKeyPressed(61)) {
       this.effect.isDone = true;
     }
        if(this.effect.isDone){
                AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
                AbstractDungeon.victoryScreen = new VictoryScreen(null);
        }
     this.isDone = this.effect.isDone;
   }
 }

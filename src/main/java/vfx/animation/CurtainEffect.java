 package vfx.animation;

 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.math.MathUtils;
 import com.megacrit.cardcrawl.actions.common.SuicideAction;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.helpers.ModHelper;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

 import java.util.Iterator;


 public class CurtainEffect
   extends AbstractGameEffect
 {
   private Texture ImgLeft;
   private Texture ImgRight;
   private Texture ImgMid;
   private float outCurtainX;
   private float curtainScaleX;
   private float timer;
   private boolean end;
     private boolean ifend;

   public CurtainEffect() {
     this.startingDuration = 7.0F;
     this.duration = this.startingDuration;

     this.ImgLeft = ImageMaster.loadImage("img/vfx/ros/CurtainUpperLeft.png");
     this.ImgRight = ImageMaster.loadImage("img/vfx/ros/CurtainUpperRight.png");
     this.ImgMid = ImageMaster.loadImage("img/vfx/ros/R-C.png");

     this.outCurtainX = -Settings.WIDTH / 2.0F;
     this.curtainScaleX = 0.0F;
     this.timer = 0.4F;
      this.ifend = false;

     this.end = false;
   }


   public void update() {
//       System.out.println(this.duration);
     this.duration -= Gdx.graphics.getDeltaTime();
      if(this.duration <= 2  && !ifend){
          this.ifend = true;
          Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
          while(var4.hasNext()) {
              AbstractMonster m2 = (AbstractMonster)var4.next();
                  m2.die();

          }
      }
       if(this.duration <= 0.5){
           this.isDone = true;
//           if (!CardCrawlGame.loadingSave) {
//               int card_seed_before_roll;
//               if (Settings.isDailyRun) {
//                   AbstractDungeon.getCurrRoom().addGoldToRewards(100);
//               } else {
//                   card_seed_before_roll = 100 + AbstractDungeon.miscRng.random(-5, 5);
//                   if (AbstractDungeon.ascensionLevel >= 13) {
//                       AbstractDungeon.getCurrRoom().addGoldToRewards(MathUtils.round((float)card_seed_before_roll * 0.75F));
//                   } else {
//                       AbstractDungeon.getCurrRoom().addGoldToRewards(card_seed_before_roll);
//                   }
//               }
//               AbstractDungeon.getCurrRoom().addPotionToRewards();
//           }
//           AbstractDungeon.combatRewardScreen.open();
       }
     if (!this.end) {
       if (this.duration > this.startingDuration - 2.0F) {
         this.outCurtainX = (2.0F + this.duration - this.startingDuration) * -Settings.WIDTH / 4.0F;
       }
       else if (this.duration > 3.0F) {
         this.timer -= Gdx.graphics.getDeltaTime();
//         if (this.timer <= 0.0F) {
//           this.timer += 0.4F;
           this.curtainScaleX = (5.0F - this.duration) / 2.0F;
//         }
       }
       else if (this.duration > 2.0F) {
         this.curtainScaleX = 1.0F;
         this.timer = 0.4F;
       }
//      else if (this.duration > 0.0F) {
//        this.timer -= Gdx.graphics.getDeltaTime();
//        if (this.timer <= 0.0F) {
//          this.timer += 0.4F;
//          this.curtainScaleX = this.duration / 2.0F;
//        }
//      } else {
//
//        this.curtainScaleX = 0.0F;
      }

  }
//    else if (this.duration > 5.0F) {
//      this.timer -= Gdx.graphics.getDeltaTime();
//      if (this.timer <= 0.0F) {
//        this.timer += 0.4F;
//        this.curtainScaleX = (7.0F - this.duration) / 2.0F;
//      }
//
//    } else if (this.duration > 4.0F) {
//      this.curtainScaleX = 1.0F;
//      this.timer = 0.4F;
//    }
//    else if (this.duration > 2.0F) {
//      this.timer -= Gdx.graphics.getDeltaTime();
//      if (this.timer <= 0.0F) {
//        this.timer += 0.4F;
//        this.curtainScaleX = (this.duration - 2.0F) / 2.0F;
//      }
//
//    } else if (this.duration > 0.0F) {
//      this.curtainScaleX = 0.0F;
//      this.outCurtainX = (2.0F - this.duration) * -Settings.WIDTH / 4.0F;
//    } else {
//
//      this.outCurtainX = -Settings.WIDTH / 2.0F;
//      this.isDone = true;
//    }
//   }



   public void render(SpriteBatch sb) {
     sb.setColor(Color.WHITE.cpy());
     sb.draw(this.ImgMid, 0.0F, 0.0F, Settings.WIDTH * this.curtainScaleX * 1.02F, Settings.HEIGHT);
     sb.draw(this.ImgMid, Settings.WIDTH * ((1.0F - this.curtainScaleX) * 1.02F - 0.02F), 0.0F, Settings.WIDTH * this.curtainScaleX * 1.02F, Settings.HEIGHT, 0, 0, 512, 288, true, false);


     sb.draw(this.ImgLeft, this.outCurtainX, 0.0F, Settings.WIDTH, Settings.HEIGHT);
     sb.draw(this.ImgRight, -this.outCurtainX, 0.0F, Settings.WIDTH, Settings.HEIGHT);
   }

   public void end() {
     this.end = true;
     this.duration = this.startingDuration;
   }

   public void dispose() {}
 }

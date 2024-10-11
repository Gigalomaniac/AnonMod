 package vfx.animation;

 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

 import java.util.Iterator;


 public class FirstCurtainEffect
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

   public FirstCurtainEffect() {
     this.startingDuration = 8.0F;
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
         this.duration -= Gdx.graphics.getDeltaTime();
         if (!this.end) {
             if (this.duration < this.startingDuration - 6.0F) {
                 this.outCurtainX = (6.0F + this.duration - this.startingDuration) * Settings.WIDTH / 4.0F;
             }
             if (this.duration > this.startingDuration - 2.0F) {
                 this.outCurtainX = (2.0F + this.duration - this.startingDuration) * -Settings.WIDTH / 4.0F;
             }
             else if (this.duration > 4.0F) {
                 this.timer -= Gdx.graphics.getDeltaTime();
                     this.curtainScaleX = (6.0F - this.duration) / 2.0F;
             } else if (this.duration > 2.0F) {
                 this.curtainScaleX = 1.0F;
                 this.timer = 0.4F;
             }
             else if (this.duration > 0.0F) {
                 this.timer -= Gdx.graphics.getDeltaTime();
//                 this.curtainScaleX = this.duration / 2.0F;
//                 this.curtainScaleX = (float) (Math.pow((2-this.duration), 2) / 2.0F);
                 this.curtainScaleX = ((-0.5f * this.duration * this.duration) + (2.0f * this.duration))/2;
             } else {
                 this.curtainScaleX = 0.0F;
             }

         }
     }


   public void render(SpriteBatch sb) {
     sb.setColor(Color.WHITE.cpy());
     sb.draw(this.ImgMid, 0.0F, 0.0F, Settings.WIDTH * this.curtainScaleX * 1.02F, Settings.HEIGHT);
     sb.draw(this.ImgMid, Settings.WIDTH * ((1.0F - this.curtainScaleX) * 1.02F - 0.02F), 0.0F, Settings.WIDTH * this.curtainScaleX * 1.02F, Settings.HEIGHT, 0, 0, 512, 288, true, false);

       if (this.duration > 0.0F) {
           sb.draw(this.ImgLeft, this.outCurtainX, 0.0F, Settings.WIDTH, Settings.HEIGHT);
           sb.draw(this.ImgRight, -this.outCurtainX, 0.0F, Settings.WIDTH, Settings.HEIGHT);
       }
   }

   public void end() {
     this.end = true;
     this.duration = this.startingDuration;
   }

   public void dispose() {}
 }

/*    */ package vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

 public class ApostleDieEffect
   extends AbstractGameEffect
 {
   private Texture img;
   private Texture img2;
   private int code;
   private boolean sound;
     public static final String ID = "TheConsciousnessOfTheWorld";
     private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
     private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
     private static  String detail ;
   public ApostleDieEffect(int code) {
     this.img = ImageMaster.loadImage("img/vfx/ClockShader.png");
     this.img2 = ImageMaster.loadImage("img/vfx/ClockArrow.png");
     this.color = Color.WHITE.cpy();
     this.color.a = 0.0F;
     this.duration = 3.0F;
     this.code = code;
     this.rotation = 90.0F * code - 90.0F;
     this.sound = true;
     detail = DESCRIPTIONS[code];
   }

     public void update() {
         if (this.duration == 3.0F) {
             // CardCrawlGame.sound.play("WhiteNight_Clock_Bell");
         }

         this.duration -= Gdx.graphics.getDeltaTime();

         if (this.duration > 2.5F) {
             this.color.a = (3.0F - this.duration) * 2.0F;
         } else {
             this.color.a = 1.0F;
         }

         if (this.duration > 1.5F && this.duration < 2.0F) {
             if (this.sound) {
                 this.sound = false;
                 // CardCrawlGame.sound.play("WhiteNight_Clock_Tick");
             }
             if (this.rotation < 90.0F * this.code) {
                 this.rotation += (2.0F - this.duration) * 60.0F;
             } else {
                 this.rotation = 90.0F * this.code;
             }
         } else if (this.duration < 1.5F) {
             this.rotation = 90.0F * this.code;
         }

         if (this.duration < 1.0F) {
             this.color.a = this.duration;
         }

         if (this.color.a < 0.0F) {
             this.color.a = 0.0F;
         }

         if (this.duration < 0.0F) {
             this.isDone = true;
         }
     }


   public void render(SpriteBatch sb) {
     sb.setColor(this.color);
     sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);


     sb.draw(this.img2, (960 * Settings.WIDTH) / 1920.0F - 960.0F, (540 * Settings.WIDTH) / 1920.0F - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.WIDTH / 1920.0F, Settings.HEIGHT / 1080.0F, -this.rotation, 0, 0, 1920, 1080, false, false);
       FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, detail,  Settings.WIDTH /2 -FontHelper.topPanelInfoFont.getSpaceWidth()*detail.length()*2 , Settings.HEIGHT/15, Color.WHITE.cpy());
   }

   public void dispose() {}
 }

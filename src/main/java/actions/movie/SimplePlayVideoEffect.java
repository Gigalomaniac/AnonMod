/*    */ package actions.movie;
/*    */ 
/*    */
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.files.FileHandle;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.video.VideoPlayer;
/*    */ import com.badlogic.gdx.video.VideoPlayerCreator;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimplePlayVideoEffect
/*    */   extends AbstractGameEffect
/*    */ {
    private Hitbox hb;
/* 26 */   private VideoPlayer videoPlayer = VideoPlayerCreator.createVideoPlayer(); public SimplePlayVideoEffect(String videoPath) {
        this.hb = new Hitbox(Settings.WIDTH, Settings.HEIGHT);
        this.hb.x = 0.0F;
        this.hb.y = 0.0F;
/* 27 */     if (this.videoPlayer == null) {
/* 28 */
/* 29 */       clearVideo();
/*    */       
/*    */       return;
/*    */     } 
/* 33 */     this.videoPlayer.setOnCompletionListener(e -> clearVideo());
/* 34 */     (new Thread(() -> {
/*    */           try {
/* 36 */             this.videoPlayer.play(Gdx.files.internal(videoPath));
/* 37 */           } catch (Exception e) {
/* 38 */
/* 39 */             e.printStackTrace();
/* 40 */             clearVideo();
/*    */           } 
/* 42 */         })).start();
/*    */   }
/*    */   
/*    */   private static final String COMMENT = "The video format needs to be webm !!!";
/*    */   
/* 47 */   public void update() {
    this.videoPlayer.update();
    this.hb.update();
    if (this.hb.hovered && InputHelper.justClickedLeft) {
        InputHelper.justClickedLeft = false;
        this.hb.clickStarted = true;
    }
    if (this.hb.clicked) {
        this.hb.clicked = false;
        this.isDone=true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
        AbstractDungeon.victoryScreen = new VictoryScreen(null);
        if (this.videoPlayer != null) {
            this.videoPlayer.dispose();
            this.videoPlayer = null;
        }
    }
    ; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 52 */     Texture texture = this.videoPlayer.getTexture();
/* 53 */     if (texture != null) {
/* 54 */       float width = texture.getWidth() * Settings.scale;
/* 55 */       float height = texture.getHeight() * Settings.scale;
/* 56 */       float x = (Settings.WIDTH - width) / 2.0F;
/* 57 */       float y = (Settings.HEIGHT - height) / 2.0F;
/* 58 */       sb.setColor(Color.WHITE);
/* 59 */       sb.draw(texture, x, y, width, height);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */   
/*    */   public void clearVideo() {
/* 69 */     this.isDone = true;
    AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
    AbstractDungeon.victoryScreen = new VictoryScreen(null);
/* 70 */     if (this.videoPlayer != null) {
/* 71 */       this.videoPlayer.dispose();
/* 72 */       this.videoPlayer = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\素材\common\VideoTheSpire.jar!\VideoTheSpire\effects\SimplePlayVideoEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
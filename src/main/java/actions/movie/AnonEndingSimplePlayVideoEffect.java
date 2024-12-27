/*    */ package actions.movie;
/*    */ 
/*    */
/*    */

import ExcessiveFantasy.diag.AnotherStoryEnding;
//import actions.ClearRelicAction;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
/*    */
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.video.VideoPlayer;
/*    */ import com.badlogic.gdx.video.VideoPlayerCreator;
/*    */
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import relics.Receipts;


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
/*    */ public class AnonEndingSimplePlayVideoEffect
/*    */   extends AbstractGameEffect
/*    */ {
    private Hitbox hb;
/* 26 */   private VideoPlayer videoPlayer = VideoPlayerCreator.createVideoPlayer(); public AnonEndingSimplePlayVideoEffect(String videoPath) {
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
        this.isDone = true;
        if (this.videoPlayer != null) {
            this.videoPlayer.dispose();
            this.videoPlayer = null;
        }
        if (AbstractDungeon.player.hasRelic(Receipts.ID) && AbstractDungeon.id.equals(TheEnding.ID)) {
            System.out.println("Special");
            AbstractDungeon.isScreenUp = true;
            AbstractDungeon.overlayMenu.proceedButton.hide();
            CardCrawlGame.fadeIn(1.0F);
            AbstractDungeon.topLevelEffectsQueue.add(new AnotherStoryEnding(0, 6));

            if(AbstractDungeon.player.hasRelic(Receipts.ID)) {
                for (AbstractRelic receipts : AbstractDungeon.player.relics) {
                    if (receipts.relicId.equals(Receipts.ID)) {
                        receipts.flash();
                        ((Receipts) receipts).memory();
                    }
                }
            }
            AbstractDungeon.player.masterDeck.clear();
            for (AbstractRelic Rec : AbstractDungeon.player.relics){
                if(Rec.relicId.equals(Receipts.ID)){
                    final float START_X = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_X");
                    final float START_Y = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_Y");
                    Rec.currentX =START_X;
                    Rec.currentY =START_Y;
                    Rec.hb.update(START_X,START_Y);
                    break;
                }
            }
            int index = 0;
            for (AbstractRelic naire : AbstractDungeon.player.relics){
                if(!naire.relicId.equals(Receipts.ID)){
                    naire.currentX=-naire.currentX;
                    naire.hb.update(naire.currentX,naire.currentY);
                }
                index ++;
            }
        } else {

            AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
            AbstractDungeon.victoryScreen = new VictoryScreen(null);

        }
        ;
    }
}


   public void render(SpriteBatch sb) {
     Texture texture = this.videoPlayer.getTexture();
     if (texture != null) {
       float width = texture.getWidth() * Settings.scale;
       float height = texture.getHeight() * Settings.scale;
       float x = (Settings.WIDTH - width) / 2.0F;
       float y = (Settings.HEIGHT - height) / 2.0F;
       sb.setColor(Color.WHITE);
       sb.draw(texture, x, y, width, height);
     }
   }



   public void dispose() {}


   public void clearVideo() {
     this.isDone = true;
    AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
    AbstractDungeon.victoryScreen = new VictoryScreen(null);
     if (this.videoPlayer != null) {
       this.videoPlayer.dispose();
       this.videoPlayer = null;
     }
   }
 }

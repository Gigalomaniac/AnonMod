/*    */ package ExcessiveFantasy;
/*    */ 
/*    */ import basemod.eventUtil.EventUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.EventRoom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SakikoShopRoom
/*    */   extends EventRoom
/*    */ {
/*    */   public void onPlayerEntry() {
    CardCrawlGame.music.silenceBGM();
    CardCrawlGame.music.silenceBGMInstantly();
    CardCrawlGame.music.silenceTempBgmInstantly();
    AbstractDungeon.scene.fadeOutAmbiance();
/* 14 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 15 */     this.event = EventUtils.getEvent(SakikoShop.ID);
/* 16 */     this.event.onEnterRoom();
/*    */   }
/*    */ }



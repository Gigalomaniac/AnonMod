/*    */ package patch;
/*    */ 
/*    */ import cards.SpecialAnonCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
/*    */
/*    */ 
/*    */ public class CardSignPatch
/*    */ {
/*    */   @SpirePatch(clz = SingleCardViewPopup.class, method = "renderTitle", paramtypez = {SpriteBatch.class})
/*    */   public static class SingleCardViewPatch {
/* 15 */     private static float drawScale = 2.0F;
/* 16 */     private static float yOffsetBase = 690.0F;
/*    */     
/*    */     @SpirePostfixPatch
/*    */     public static void Postfix(SingleCardViewPopup _inst, SpriteBatch sb, AbstractCard ___card, float ___current_y) {
/* 20 */       if (___card instanceof SpecialAnonCard) {
/* 21 */         SpecialAnonCard card = (SpecialAnonCard)___card;
/* 22 */         card.renderCardSign(sb, Settings.WIDTH / 2.0F, ___current_y, yOffsetBase, drawScale);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\素材\slaymod\AliceMargatroid (2).jar!\rs\antileaf\alice\patches\misc\CardSignPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
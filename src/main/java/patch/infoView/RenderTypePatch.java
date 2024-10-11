/*    */ package patch.infoView;
/*    */
/*    */ import Mod.AnonMod;
import cards.AbstractLockAnonCard;
import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
/*    */ import java.lang.reflect.Field;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class RenderTypePatch
/*    */ {
/* 24 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardType");
/* 25 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */
/*    */
/*    */
/*    */   @SpirePatch(clz = AbstractCard.class, method = "renderType")
/*    */   public static class renderType
/*    */   {
/*    */     @SpireInsertPatch(rloc = 0)
/*    */     public static SpireReturn Insert(AbstractCard _inst, SpriteBatch sb) {
/* 34 */       if (_inst instanceof AbstractLockAnonCard) {
/* 35 */         BitmapFont font = FontHelper.cardTypeFont;
/* 36 */         font.getData().setScale(_inst.drawScale);
/*    */
///* 38 */         FontHelper.renderRotatedText(sb, font, RenderTypePatch.TEXT[0], _inst.current_x, _inst.current_y - 22.0F * _inst.drawScale * Settings.scale, 0.0F, -1.0F * _inst.drawScale * Settings.scale, _inst.angle, false, new Color(0.35F, 0.35F, 0.35F,
///* 39 */               (Color.WHITE.cpy()).a));
               if(!AnonMod.saves.getBool("MachineHeartLocked")){
                   ((AbstractLockAnonCard) _inst).unlockInfo();
                       }
               //uuid对应
/* 40 */         return SpireReturn.Return(null);
/*    */       }
/* 42 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */
/*    */
/*    */
/*    */   @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
/*    */   public static class renderCardTypeText
/*    */   {
/*    */     @SpireInsertPatch(rloc = 0)
/*    */     public static SpireReturn Insert(SingleCardViewPopup _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
/* 53 */       Field card = _inst.getClass().getDeclaredField("card");
/* 54 */       card.setAccessible(true);
/* 55 */       if (card.get(_inst) instanceof AbstractLockAnonCard) {
///* 56 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, RenderTypePatch.TEXT[0], Settings.WIDTH / 2.0F + 3.0F * Settings.scale, Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, new Color(0.35F, 0.35F, 0.35F, 1.0F));
/*    */
//        if(!((AbstractLockAnonCard) card.get(_inst)).locked){
//            System.out.println("!((AbstractLockAnonCard) card).locked");
//            ((AbstractLockAnonCard) card.get(_inst)).unlockInfo();
//        }
        if(!AnonMod.saves.getBool("MachineHeartLocked")){
            ((AbstractLockAnonCard) card.get(_inst)).unlockInfo();
        }

/* 58 */         return SpireReturn.Return(null);
/*    */       }
/* 60 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Deskto\\uncommon\Lobotomy.jar!\lobotomyMod\patch\infoView\RenderTypePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
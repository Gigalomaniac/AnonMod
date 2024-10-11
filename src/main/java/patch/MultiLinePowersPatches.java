/*    */ package patch;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.Loader;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.CtBehavior;
/*    */ import javassist.expr.ExprEditor;
/*    */ import javassist.expr.MethodCall;
/*    */
/*    */ @SpirePatch(clz = AbstractCreature.class, method = "renderPowerIcons")
/*    */ public class MultiLinePowersPatches {
/* 22 */   private static float offsetY = 0.0F;
/*    */   private static final int INITIAL_LIMIT = 8;
/* 24 */   private static int count = 0;
/*    */
/*    */   private static boolean doingAmounts = false;
/*    */
/*    */
/* 29 */   public static boolean isNotInvis(AbstractPower p) { return !(p instanceof com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower); }
/*    */
/*    */
/*    */
/* 33 */   public static float getOffsetY() { return offsetY; }
/*    */
/*    */
/*    */   public static void incrementOffsetY(float[] offsetX, AbstractPower p) {
///* 37 */     if (isNotInvis(p)) {
///* 38 */       count++;
///* 39 */       if (count == 8) {
///* 40 */         count = 0;
///* 41 */         offsetY -= 38.0F * Settings.scale;
///* 42 */         offsetX[0] = ((doingAmounts ? 0 : 10) - 48) * Settings.scale;
///*    */       }
///*    */     }
/*    */   }
/*    */
/*    */   @SpirePrefixPatch
/*    */   public static void Prefix(AbstractCreature __instance, SpriteBatch sb, float x, float y) {
/* 49 */     offsetY = 0.0F;
/* 50 */     count = 0;
/* 51 */     doingAmounts = false;
/*    */   }
/*    */
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static void Insert(AbstractCreature __instance, SpriteBatch sb, float x, float y) {
/* 56 */     offsetY = 0.0F;
/* 57 */     count = 0;
/* 58 */     doingAmounts = true;
/*    */   }
/*    */
/*    */   private static class Locator extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
/* 63 */       Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractCreature.class, "powers");
/* 64 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), fieldAccessMatcher)[1] };
/*    */     }
/*    */   }
/*    */
/*    */   public static ExprEditor Instrument() {
/* 69 */     return new ExprEditor() {
/*    */         public void edit(MethodCall m) throws CannotCompileException {
/* 71 */           if (Loader.isModLoaded("MintySpire"))
/* 72 */             return;  if (m.getMethodName().equals("renderIcons")) {
/* 73 */             m.replace("{if(!" + MultiLinePowersPatches.class
/* 74 */                 .getName() + ".isExcludedClass($0.owner)){$3 += " + MultiLinePowersPatches.class
/*    */
/* 76 */                 .getName() + ".getOffsetY();$proceed($$);float[] offsetArr = new float[]{offset};" + MultiLinePowersPatches.class
/*    */
/* 78 */                 .getName() + ".incrementOffsetY(offsetArr, p);offset = offsetArr[0];}else{$proceed($$);}}");
/* 79 */           } else if (m.getMethodName().equals("renderAmount")) {
/* 80 */             m.replace("{if(!" + MultiLinePowersPatches.class
/* 81 */                 .getName() + ".isExcludedClass($0.owner)){$3 += " + MultiLinePowersPatches.class
/*    */
/* 83 */                 .getName() + ".getOffsetY();$proceed($$);float[] offsetArr = new float[]{offset};" + MultiLinePowersPatches.class
/*    */
/* 85 */                 .getName() + ".incrementOffsetY(offsetArr, p);offset = offsetArr[0];}else{$proceed($$);}}");
/*    */           }
/*    */         }
/*    */       };
/*    */   }
/*    */
/*    */   public static boolean isExcludedClass(AbstractCreature c) {
/* 92 */     if (c instanceof AbstractPlayer) {
/* 93 */       String tmp = ((AbstractPlayer)c).getTitle(((AbstractPlayer)c).chosenClass);
/* 94 */       return (tmp.equals("the Bladedancer") || tmp.equals("The Anomaly"));
/*    */     }
/* 96 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Deskto\\uncommon\Paranoia.jar!\TNN\Paranoia\patches\MultiLinePowersPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
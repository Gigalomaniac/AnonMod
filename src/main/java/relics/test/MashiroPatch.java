///*     */ package relics.test;
///*     */
//
//import Mod.AnonMod;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.glutils.ShaderProgram;
//import com.badlogic.gdx.utils.GdxRuntimeException;
//import com.evacipated.cardcrawl.modthespire.Loader;
//import com.evacipated.cardcrawl.modthespire.ModInfo;
//import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
//import com.evacipated.cardcrawl.modthespire.lib.SpireField;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.scenes.AbstractScene;
//import javassist.*;
//import org.clapper.util.classutil.*;
//
//import java.io.File;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//
///*     */
///*     */
///*     */
///*     */
///*     */ public class MashiroPatch {
///*     */   @SpirePatch(clz = AbstractScene.class, method = "<class>")
///*     */   public static class AbstractSceneFields {
///*  29 */     public static SpireField<ShaderProgram> shader = new SpireField(() -> null);
///*     */   }
///*     */
///*     */   @SpirePatch(clz = CardCrawlGame.class, method = "<ctor>")
///*     */   public static class ApplyRainShaderToAllCombatBGs {
///*     */     public static void Raw(CtBehavior ctBehavior) throws NotFoundException {
///*  35 */       ClassFinder finder = new ClassFinder();
///*     */
///*  37 */       finder.add(new File(Loader.STS_JAR));
///*     */
///*  39 */       for (ModInfo modInfo : Loader.MODINFOS) {
///*  40 */         if (modInfo.jarURL != null) {
///*     */           try {
///*  42 */             finder.add(new File(modInfo.jarURL.toURI()));
///*  43 */           } catch (URISyntaxException uRISyntaxException) {}
///*     */         }
///*     */       }
///*     */
///*     */
///*     */
///*     */
///*  50 */       AndClassFilter filter = new AndClassFilter(new ClassFilter[] { new NotClassFilter(new AbstractClassFilter()), new ClassModifiersClassFilter(1), new OrClassFilter(new ClassFilter[] { new SubclassClassFilter(AbstractScene.class), (classInfo, classFinder) ->
///*     */
///*     */
///*     */
///*     */
///*  55 */                 classInfo.getClassName().equals(AbstractScene.class.getName()) }) });
///*     */
///*     */
///*     */
///*  59 */       ArrayList<ClassInfo> foundClasses = new ArrayList<ClassInfo>();
///*  60 */       finder.findClasses(foundClasses, filter);
///*     */
///*  62 */       for (ClassInfo classInfo : foundClasses) {
///*  63 */         CtClass ctClass = ctBehavior.getDeclaringClass().getClassPool().get(classInfo.getClassName());
///*     */
///*     */         try {
///*  66 */           CtMethod[] methods = ctClass.getDeclaredMethods();
///*  67 */           for (CtMethod m : methods) {
///*  68 */             if (m.getName().equals("renderCombatRoomBg")) {
///*  69 */               m.insertBefore("{if(" + ApplyRainShaderToAllCombatBGs.class
///*  70 */                   .getName() + ".inStormZone()) {" + ApplyRainShaderToAllCombatBGs.class
///*  71 */                   .getName() + ".initShader($0);$1.setShader(" + ApplyRainShaderToAllCombatBGs.class
///*  72 */                   .getName() + ".getSceneShader($0));" + ApplyRainShaderToAllCombatBGs.class
///*  73 */                   .getName() + ".getSceneShader($0).setUniformf(\"u_time\", " + ApplyRainShaderToAllCombatBGs.class.getName() + ".getTime());}}");
///*     */
///*  75 */               m.insertAfter("{$1.setShader(null);}");
///*     */             }
///*     */
///*     */           }
///*     */
///*  80 */         } catch (CannotCompileException e) {
///*  81 */           e.printStackTrace();
///*     */         }
///*     */       }
///*     */     }
///*     */
///*  86 */     public static ShaderProgram getSceneShader(AbstractScene scene) { return (ShaderProgram) AbstractSceneFields.shader.get(scene); }
///*     */
///*     */
///*     */
///*  90 */     public static float getTime() { return AnonMod.time % 25.0F; }
///*     */        public static SpireConfig modConfig = null;
///*     */     public static boolean getShaderConfig() { return (modConfig != null && modConfig.getBool("enableShaders")); }
///*  93 */     public static boolean inStormZone() { return ( getShaderConfig()); }
///*     */
///*     */
///*     */     public static void initShader(AbstractScene scene) {
///*  97 */       if (getSceneShader(scene) == null) {
///*     */         try {
///*  99 */           AbstractSceneFields.shader.set(scene, new ShaderProgram(Gdx.files
///* 100 */                 .internal("shaders/storm/rain/vertex.vs"), Gdx.files
///* 101 */                 .internal("shaders/storm/rain/fragment.fs")));
///*     */
///* 103 */           if (!getSceneShader(scene).isCompiled()) {
///* 104 */             System.err.println(getSceneShader(scene).getLog());
///*     */           }
///* 106 */           if (!getSceneShader(scene).getLog().isEmpty()) {
///* 107 */             System.out.println(getSceneShader(scene).getLog());
///*     */           }
///* 109 */         } catch (GdxRuntimeException e) {
///* 110 */           System.out.println("ERROR: snow shader:");
///* 111 */           e.printStackTrace();
///*     */         }
///*     */       }
///*     */     }
//
//    /*     */   }
///*     */
/////*     */   @SpirePatch(clz = AbstractScene.class, method = "updateAmbienceVolume")
/////*     */   public static class SetRainAmbiance
/////*     */   {
/////*     */     @SpirePrefixPatch
/////*     */     public static void Prefix(AbstractScene __instance) {
/////* 122 */       if (StormUtil.isInStormZone()) {
/////* 123 */         if (Settings.AMBIANCE_ON) {
/////* 124 */           CardCrawlGame.sound.adjustVolume(StormZone.RAIN_KEY, StormUtil.rainSoundId);
/////*     */         } else {
/////* 126 */           CardCrawlGame.sound.adjustVolume(StormZone.RAIN_KEY, StormUtil.rainSoundId, 0.0F);
/////*     */         }
/////*     */       }
/////*     */     }
/////*     */   }
///*     */
/////*     */   @SpirePatch(clz = AbstractScene.class, method = "fadeOutAmbiance")
/////*     */   public static class FadeOutAmbiencePatch
/////*     */   {
/////*     */     @SpirePostfixPatch
/////*     */     public static void Postfix() {
/////* 137 */       if (StormUtil.isInStormZone()) {
/////* 138 */         CardCrawlGame.sound.adjustVolume(StormZone.RAIN_KEY, StormUtil.rainSoundId, 0.0F);
/////* 139 */         StormUtil.rainSoundId = 0L;
/////*     */       }
/////*     */     }
/////*     */   }
///*     */
/////*     */   @SpirePatch(clz = AbstractScene.class, method = "muteAmbienceVolume")
/////*     */   public static class MuteAmbiencePatch
/////*     */   {
/////*     */     @SpirePostfixPatch
/////*     */     public static void Postfix() {
/////* 149 */       if (StormUtil.isInStormZone())
/////* 150 */         CardCrawlGame.sound.adjustVolume(StormZone.RAIN_KEY, StormUtil.rainSoundId, 0.0F);
/////*     */     }
/////*     */   }
///*     */ }
//
//
///* Location:              C:\Users\Administrator\Desktop\素材\common\3142318909\SpireBiomes.jar!\spireMapOverhaul\zones\storm\patches\RainPatch.class
// * Java compiler version: 8 (52.0)
// * JD-Core Version:       1.0.7
// */
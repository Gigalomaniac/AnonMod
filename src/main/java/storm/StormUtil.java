/*     */ package storm;
/*     */ 
/*     */ import basemod.helpers.CardModifierManager;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Pixmap;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.graphics.glutils.FrameBuffer;
/*     */ import com.badlogic.gdx.graphics.glutils.ShaderProgram;
/*     */ import com.badlogic.gdx.utils.GdxRuntimeException;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */
/*     */
/*     */
/*     */ public class StormUtil
/*     */ {
/*     */   public static long rainSoundId;
/*     */   public static boolean activePlayerLightning;
/*     */   public static float brightTime;
/*     */   public static AbstractCreature conduitTarget;
/*     */   public static float timeToStrike;
/*     */   public static float timeSinceStrike;
/*     */
/*  30 */   public static FrameBuffer createBuffer() { return createBuffer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); }
/*     */
/*     */
/*     */
/*  34 */   public static FrameBuffer createBuffer(int sizeX, int sizeY) { return new FrameBuffer(Pixmap.Format.RGBA8888, sizeX, sizeY, false, false); }
/*     */
/*     */
/*     */   public static void beginBuffer(FrameBuffer fbo) {
/*  38 */     fbo.begin();
/*  39 */     Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/*  40 */     Gdx.gl.glClear(16640);
/*  41 */     Gdx.gl.glColorMask(true, true, true, true);
/*     */   }
/*     */
/*     */   public static TextureRegion getBufferTexture(FrameBuffer fbo) {
/*  45 */     TextureRegion texture = new TextureRegion((Texture)fbo.getColorBufferTexture());
/*  46 */     texture.flip(false, true);
/*  47 */     return texture;
/*     */   }
/*     */
/*     */
///*  51 */   public static boolean cardValidToMakeDamp(AbstractCard c) { return (!CardModifierManager.hasModifier(c, DampModifier.ID) && !CardModifierManager.hasModifier(c, ElectricModifier.ID)); }
/*     */
/*     */
/*     */
///*  55 */   public static int countValidCardsInHandToMakeDamp() { return (int)AbstractDungeon.player.hand.group.stream().filter(StormUtil::cardValidToMakeDamp).count(); }
/*     */
/*     */
/*     */
///*  59 */   public static boolean isInStormZone() { return Wiz.getCurZone() instanceof StormZone; }
/*     */
/*     */
/*     */   public static ShaderProgram initDripShader(ShaderProgram dripShader) {
/*  63 */     if (dripShader == null) {
/*     */
/*     */       try {
/*     */
/*  67 */         dripShader = new ShaderProgram(Gdx.files.internal("shaders/storm/drip/vertex.vs"), Gdx.files.internal("shaders/storm/drip/fragment.fs"));
/*     */
/*  69 */         if (!dripShader.isCompiled()) {
/*  70 */           System.err.println(dripShader.getLog());
/*     */         }
/*  72 */         if (!dripShader.getLog().isEmpty()) {
/*  73 */           System.out.println(dripShader.getLog());
/*     */         }
/*  75 */       } catch (GdxRuntimeException e) {
/*  76 */         System.out.println("ERROR: Failed to init electric shader:");
/*  77 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  80 */     return dripShader;
/*     */   }
/*     */
/*     */   public static ShaderProgram initDarkShader(ShaderProgram darkShader) {
/*  84 */     if (darkShader == null) {
/*     */
/*     */       try {
/*     */
/*  88 */         darkShader = new ShaderProgram(Gdx.files.internal("shaders/storm/dark/vertex.vs"), Gdx.files.internal("shaders/storm/dark/fragment.fs"));
/*     */
/*  90 */         if (!darkShader.isCompiled()) {
/*  91 */           System.err.println(darkShader.getLog());
/*     */         }
/*  93 */         if (!darkShader.getLog().isEmpty()) {
/*  94 */           System.out.println(darkShader.getLog());
/*     */         }
/*  96 */       } catch (GdxRuntimeException e) {
/*  97 */         System.out.println("ERROR: dark shader:");
/*  98 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 101 */     return darkShader;
/*     */   }
/*     */
/*     */   public static ShaderProgram initElectricShader(ShaderProgram electricShader) {
/* 105 */     if (electricShader == null) {
/*     */
///*     */       try {
///*     */
///* 109 */         electricShader = new ShaderProgram(Gdx.files.internal(SpireAnniversary6Mod.makeShaderPath("storm/electric/vertex.vs")), Gdx.files.internal(SpireAnniversary6Mod.makeShaderPath("storm/electric/fragment.fs")));
///*     */
///* 111 */         if (!electricShader.isCompiled()) {
///* 112 */           System.err.println(electricShader.getLog());
///*     */         }
///* 114 */         if (!electricShader.getLog().isEmpty()) {
///* 115 */           System.out.println(electricShader.getLog());
///*     */         }
///* 117 */       } catch (GdxRuntimeException e) {
///* 118 */         System.out.println("ERROR: electric shader:");
///* 119 */         e.printStackTrace();
///*     */       }
/*     */     }
/* 122 */     return electricShader;
/*     */   }

    /*     */ }


/* Location:              C:\Users\Administrator\Desktop\素材\common\3142318909\SpireBiomes.jar!\spireMapOverhaul\zones\storm\StormUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
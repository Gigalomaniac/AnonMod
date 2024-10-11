/*    */ package relics.test;
/*    */
/*    */ import Mod.AnonMod;
import basemod.abstracts.CustomRelic;
/*    */ import basemod.abstracts.CustomSavable;
import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import com.megacrit.cardcrawl.localization.RelicStrings;
/*    */

import java.util.Random;

/*    */
/*    */ 
/*    */ public class Mashiro
/*    */   extends CustomRelic
        /*    */ {
/* 21 */   public static final String ID = "Mashiro";
/* 22 */   private static final RelicStrings textStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final String IMG = "img/relics/MashiroMushroom.png";
    private static final String IMG_OTL = "img/relics/MashiroMushroom.png";
/*    */   private static final long invalidSfxID = -1L;
/* 26 */   private static long rainSfxID = -1L;
    private RainyShaderEffect effect;

    public boolean isBattle;
    private float timer = -10;

    /*    */
/*    */   
/* 29 */   public Mashiro() { super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
///*    */   public static void constructRainySoundEffect() {
///* 36 */     if (rainSfxID == -1L)
///* 37 */       rainSfxID = CardCrawlGame.sound.playAndLoop(Constant.CondensationNucleiRainSoundEffectKey);
///*    */   }
/*    */   
///*    */   public static void destructRainySoundEffect() {
///* 41 */     if (rainSfxID != -1L) {
///* 42 */       relicCount = 0;
///*    */
///* 44 */       if (AbstractDungeon.player != null)
///* 45 */         for (AbstractRelic relic : AbstractDungeon.player.relics) {
///* 46 */           if (relic instanceof CondensationNuclei)
///* 47 */             relicCount++;
///*    */         }
///* 49 */       if (relicCount == 0 || relicCount == 1) {
///* 50 */         CardCrawlGame.sound.stop(Constant.CondensationNucleiRainSoundEffectKey, rainSfxID);
///* 51 */         rainSfxID = -1L;
///*    */       }
///*    */     }
///*    */   }
/*    */   
///*    */   public static void updateRainySoundEffect() {
///* 57 */     if (rainSfxID != -1L) {
///* 58 */       if (Settings.AMBIANCE_ON) {
///* 59 */         CardCrawlGame.sound.adjustVolume(Constant.CondensationNucleiRainSoundEffectKey, rainSfxID);
///*    */       } else {
///* 61 */         CardCrawlGame.sound.adjustVolume(Constant.CondensationNucleiRainSoundEffectKey, rainSfxID, 0.0F);
///*    */       }
///*    */     }
///*    */   }
/*    */ 
/*    */   
/* 67 */   public String getUpdatedDescription() { return textStrings.DESCRIPTIONS[0]; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
///* 72 */   public boolean canSpawn() { return (super.canSpawn() && GlobalConfig.RequirePlayVisualEffectsByShaders && AetherShaderLibrary.isAetherShaderCompiled(ShaderEnums.Rainy)); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 77 */     AbstractDungeon.player.energy.energyMaster++;



/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 84 */     AbstractDungeon.player.energy.energyMaster--;
AnonMod.removePostProcessor(MashiroPostProcessor.class);
    isBattle =false;
}

    public void atBattleStart() {
        AnonMod.registerPostProcessor(new MashiroPostProcessor());
        isBattle =true;

    }


    @Override
    public void update() {
        super.update();
if(isBattle) {
    this.timer -= Gdx.graphics.getDeltaTime();
    if (this.timer <= -10F) {
        this.timer += 10F;

        AbstractDungeon.effectList.add(new BackgroundMonster(new Random().nextBoolean()));

    }
}
    }
    public void onVictory() {
        isBattle =false;
        AnonMod.removePostProcessor(MashiroPostProcessor.class);
        AbstractDungeon.effectList.remove(BackgroundMonster.class);
    }

    public static class MashiroPostProcessor
            implements ScreenPostProcessor
    {
        private static final ShaderProgram shader = new ShaderProgram(Gdx.files
                .internal("shaders/Mashiro/mashiro.vs").readString(), Gdx.files
                .internal("shaders/Mashiro/mashiro.fs").readString()); static  {
        if (!shader.isCompiled()) {
            throw new RuntimeException(shader.getLog());
        }
    }

        @Override

        public void postProcess(SpriteBatch sb, TextureRegion frameTexture, OrthographicCamera camera) {
            sb.end();

            sb.setShader(shader);
            sb.begin();
            sb.setColor(Color.WHITE);

            sb.draw(frameTexture, 0.0F, 0.0F);
            ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
        }
    }
/*    */ }


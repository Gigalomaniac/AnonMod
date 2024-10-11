/*    */ package patch;
/*    */ 
/*    */ import Mod.AnonMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.google.gson.Gson;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import patch.infoView.infoViewField;
import relics.test.Mashiro;
/*    */
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class SaveFilePatch
/*    */ {
/* 12 */   public static final Gson gson = new Gson();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = SaveFile.class, method = "<ctor>", paramtypez = {SaveFile.SaveType.class})
/*    */   public static class PatchConstructor
/*    */   {
/*    */     public static void Postfix(SaveFile saveFile, SaveFile.SaveType saveType) {
/* 23 */       saveFile.monster_hp_seed_count = AbstractDungeon.monsterHpRng.counter;
/* 24 */       saveFile.ai_seed_count = AbstractDungeon.aiRng.counter;
/* 25 */       saveFile.shuffle_seed_count = AbstractDungeon.shuffleRng.counter;
///* 26 */       AnonMod.saves.setInt("cardRarityRngCounter", AbstractDungeonPatch.CardRarityRngFix.cardRarityRng.counter);
/* 27 */       AnonMod.saves.setString("shrines", SaveFilePatch.gson.toJson(AbstractDungeon.shrineList));
//            AnonMod.saves.setBool("MachineHeartLocked", infoViewField.locked);

/*    */       try {
/* 29 */         AnonMod.saves.save();
/* 30 */       } catch (IOException e) {
/* 31 */         e.printStackTrace();
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\素材\common\sp-racing.jar!\demoMod\spracing\patches\rngfix\SaveFilePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
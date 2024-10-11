/*    */ package monster.act3;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FakeDungeon
/*    */   extends AbstractDungeon
/*    */ {
/* 10 */   public FakeDungeon(String name, String levelId, AbstractPlayer p, ArrayList<String> newSpecialOneTimeEventList) { super(name, levelId, p, newSpecialOneTimeEventList); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void initializeLevelSpecificChances() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   protected ArrayList<String> generateExclusions() { return null; }
/*    */   
/*    */   protected void generateMonsters() {}
/*    */   
/*    */   protected void generateWeakEnemies(int i) {}
/*    */   
/*    */   protected void generateStrongEnemies(int i) {}
/*    */   
/*    */   protected void generateElites(int i) {}
/*    */   
/*    */   protected void initializeBoss() {}
/*    */   
/*    */   protected void initializeEventList() {}
/*    */   
/*    */   protected void initializeEventImg() {}
/*    */   
/*    */   protected void initializeShrineList() {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\素材\common\madoka0.75.jar!\MadokaMod\modcore\FakeDungeon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
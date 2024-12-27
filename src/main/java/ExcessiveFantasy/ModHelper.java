/*     */ package ExcessiveFantasy;
/*     */ 

/*     */ import Mod.AnonMod;
import characters.char_Anon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class ModHelper
/*     */ {
/*  20 */   public static final Logger logger = LogManager.getLogger(AnonMod.class.getName());
/*  21 */   public static String MOMOI_FLODER = "momoi";
/*  22 */   public static String MIDORI_FLODER = "midori";
/*  23 */   public static Gson gson = new Gson();
/*     */   
/*  25 */   public static String makePath(String id) { return "BATwinsMod:" + id; }
/*     */ 
/*     */ 
/*     */   
/*  29 */   public static String makeImgPath(String floder, String imgName) { return "baModResources/img/" + floder + "/" + imgName + ".png"; }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static String makeGifPath(String floder, String character, String imgName) { return String.format("baModResources/img/%s/%s/%s.gif", new Object[] { floder, character, imgName }); }
/*     */ 
/*     */   
/*     */   public static <T> T checkBATwinPlayer(T player) {
/*  37 */     if (player instanceof char_Anon) {
/*  38 */       return player;
/*     */     }
/*  40 */     return player;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  45 */   public static String getModID() { return "BATwinsMod"; }
/*     */ 
/*     */   
/*     */   public static AbstractCard returnTrulyRandomCardInCombatByColor(AbstractCard.CardColor color) {
/*  49 */     ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
/*  50 */     Iterator var1 = AbstractDungeon.srcCommonCardPool.group.iterator();
/*     */ 
/*     */     
/*  53 */     while (var1.hasNext()) {
/*  54 */       AbstractCard c = (AbstractCard)var1.next();
/*  55 */       if (!c.hasTag(AbstractCard.CardTags.HEALING) && c.color == color) {
/*  56 */         list.add(c);
/*  57 */         UnlockTracker.markCardAsSeen(c.cardID);
/*     */       } 
/*     */     } 
/*  60 */     var1 = AbstractDungeon.srcUncommonCardPool.group.iterator();
/*  61 */     while (var1.hasNext()) {
/*  62 */       AbstractCard c = (AbstractCard)var1.next();
/*  63 */       if (!c.hasTag(AbstractCard.CardTags.HEALING) && c.color == color) {
/*  64 */         list.add(c);
/*  65 */         UnlockTracker.markCardAsSeen(c.cardID);
/*     */       } 
/*     */     } 
/*  68 */     var1 = AbstractDungeon.srcRareCardPool.group.iterator();
/*  69 */     while (var1.hasNext()) {
/*  70 */       AbstractCard c = (AbstractCard)var1.next();
/*  71 */       if (!c.hasTag(AbstractCard.CardTags.HEALING) && c.color == color) {
/*  72 */         list.add(c);
/*  73 */         UnlockTracker.markCardAsSeen(c.cardID);
/*     */       } 
/*     */     } 
/*  76 */     if (list.isEmpty()) {
/*  77 */       return null;
/*     */     }
/*  79 */     return (AbstractCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
/*     */   }
/*     */ 
/*     */   
/*  83 */   public static String makeAudioPath(String filename, String fileType) { return "baModResources/sound/" + filename + "." + fileType; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static String makeAudioPath(String filename) { return makeAudioPath(filename, "ogg"); }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static Logger getLogger() { return logger; }
/*     */ 
/*     */   
///*     */   public static Color getBATwinsOtherColor(Color color) {
///*  95 */     if (color.equals(BATwinsMod.MOMOIColor)) {
///*  96 */       return BATwinsMod.MIDORIColor;
///*     */     }
///*  98 */     return BATwinsMod.MOMOIColor;
///*     */   }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static String makeFilePath(String floder, String fileName, String fileType) { return "baModResources/img/" + floder + "/" + fileName + "." + fileType; }
/*     */ 
/*     */   
/* 106 */   public static Map<String, SoraItemStrings> soraItemStringsMap = new HashMap();


/*     */ }


/* Location:              F:\BlueArchive_SaibaSisters_Mod.jar!\baModDeveloper\helpers\ModHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
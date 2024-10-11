/*     */ package utils;
/*     */ 
/*     */ import Mod.AnonMod;
import basemod.BaseMod;
/*     */ import basemod.ReflectionHacks;
/*     */ import cards.SpecialAnonCard;
import com.badlogic.gdx.Gdx;
/*     */ import com.evacipated.cardcrawl.modthespire.Loader;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */
/*     */
/*     */
/*     */
/*     */ 
/*     */ 
/*     */ public abstract class AnonSpireKit
/*     */ {
/*  32 */   public static String getModID() { return AnonMod.modID; }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static String getModPrefix() { return getModID() + ":"; }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public static String makeID(String name) { return getModPrefix() + name; }
/*     */ 
/*     */ 
/*     */   
/*  44 */   public static String getLangShort() { return "zhs"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static String getImgFilePath(String type, String name) { return "AliceMargatroidMod/img/" + type + "/" + name + ".png"; }
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static String getCardImgFilePath(String name) { return getImgFilePath("cards", name); }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static String getRelicImgFilePath(String name) { return getImgFilePath("relics", name); }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static String getRelicOutlineImgFilePath(String name) { return getImgFilePath("relics/outline", name); }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static String getRelicLargeImgFilePath(String name) { return getImgFilePath("relics/large", name); }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static String getOrbImgFilePath(String name) { return getImgFilePath("orbs", name); }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static String getPowerImgFilePath(String name) { return getImgFilePath("powers", name); }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static String getEventImgFilePath(String name) { return getImgFilePath("events", name); }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static String getLocalizationFilePath(String name) { return "AliceMargatroidMod/localization/" + getLangShort() + "/" + name + ".json"; }
/*     */ 
/*     */   
/*     */   public static void loadCustomStrings(Class<?> clz, String name) {
/*  91 */     String lang = getLangShort();
/*     */     
/*  93 */     String path = getLocalizationFilePath(name);
/*  94 */     String buf = Gdx.files.internal(path).readString(String.valueOf(StandardCharsets.UTF_8));
/*  95 */     BaseMod.loadCustomStrings(clz, buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCardToHand(AbstractCard card) {
/* 103 */     if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
/* 104 */       addEffect(new ShowCardAndAddToHandEffect(card, Settings.WIDTH / 2.0F - 25.0F * Settings.scale + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 109 */       addEffect(new ShowCardAndAddToDiscardEffect(card, Settings.WIDTH / 2.0F + 25.0F * Settings.scale + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public static void addToBot(AbstractGameAction action) { AbstractDungeon.actionManager.addToBottom(action); }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public static void addToTop(AbstractGameAction action) { AbstractDungeon.actionManager.addToTop(action); }
/*     */ 
/*     */   
/*     */   public static void addActionsToTop(AbstractGameAction... actions) {
/* 124 */     for (AbstractGameAction action : (AbstractGameAction[])AnonMiscKit.reversedArray(actions))
/* 125 */       addToTop(action); 
/*     */   }
/*     */   
/* 128 */   static ArrayList<AbstractGameAction> buffer = new ArrayList();
/*     */ 
/*     */   
/* 131 */   public static void addActionToBuffer(AbstractGameAction action) { buffer.add(action); }
/*     */ 
/*     */   
/*     */   public static void commitBuffer() {
/* 135 */     addActionsToTop((AbstractGameAction[])buffer.toArray(new AbstractGameAction[0]));
/* 136 */     buffer.clear();
/*     */   }
/*     */ 
/*     */   
/* 140 */   public static void addEffect(AbstractGameEffect effect) { AbstractDungeon.effectList.add(effect); }
/*     */ 
/*     */   
/*     */   public static boolean isInBattle() {
/* 144 */     if (CardCrawlGame.dungeon != null && 
/* 145 */       AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.currMapNode != null)
/*     */     {
/* 147 */       if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT); } 
/*     */     return false;
/*     */   }
/*     */   public static int getMonsterIndex(AbstractMonster m) {
/* 151 */     int index = 0;
/* 152 */     for (AbstractMonster mon : (AbstractDungeon.getMonsters()).monsters) {
/* 153 */       if (mon == m) {
/*     */         break;
/*     */       }
/* 156 */       if (!mon.isDeadOrEscaped())
/* 157 */         index++; 
/*     */     } 
/* 159 */     return index;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static AbstractMonster getMonsterByIndex(int index) {
/* 164 */     int cnt = 0;
/*     */     
/* 166 */     if (!isInBattle()) {
/* 167 */       return null;
/*     */     }
/* 169 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 170 */       if (m.isDeadOrEscaped()) {
/*     */         continue;
/*     */       }
/* 173 */       if (cnt++ == index)
/* 174 */         return m; 
/*     */     } 
/* 176 */     return null;
/*     */   }
/*     */   
///*     */   public static AbstractMonster getMonsterWithLeastHP() {
///* 180 */     minHP = Integer.MAX_VALUE;
///* 181 */     AbstractMonster res = null;
///* 182 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
///* 183 */       if (m.isDeadOrEscaped()) {
///*     */         continue;
///*     */       }
///* 186 */       if (m.currentHealth < minHP) {
///* 187 */         minHP = m.currentHealth;
///* 188 */         res = m;
///*     */       }
///*     */     }
///* 191 */     return res;
///*     */   }
/*     */   
/*     */   public static ArrayList<AbstractCard> cardsPlayedThisTurnWithTag(AbstractCard.CardTags tag) {
/* 195 */     ArrayList<AbstractCard> res = new ArrayList<AbstractCard>();
/* 196 */     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
/* 197 */       if (c.hasTag(tag))
/* 198 */         res.add(c); 
/*     */     } 
/* 200 */     return res;
/*     */   }
/*     */   
/*     */   public static boolean hasPlayedCardThisTurnWithTag(AbstractCard.CardTags tag, AbstractCard except) {
/* 204 */     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
/* 205 */       if (c != except && c.hasTag(tag))
/* 206 */         return true; 
/*     */     } 
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<AbstractCard> duplicateCards(ArrayList<AbstractCard> cards, AbstractCard except) {
/* 219 */     HashSet<AbstractCard> res = new HashSet<AbstractCard>();
/* 220 */     HashSet<String> set = new HashSet<String>();
/* 221 */     for (AbstractCard c : cards) {
///* 222 */       if (c == except || PhantomCardModifier.check(c)) {
///*     */         continue;
///*     */       }
/* 225 */       if (!set.add(c.cardID))
/* 226 */         res.add(c); 
/*     */     } 
/* 228 */     return new ArrayList(res);
/*     */   }
/*     */   
///*     */   public static String getDuplicateCardsMessage(ArrayList<AbstractCard> group, AbstractCard except) {
///* 232 */     UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AliceDuplicateCards");
///*     */
///* 234 */     ArrayList<AbstractCard> duplicates = duplicateCards(group, except);
///* 235 */     StringBuilder sb = new StringBuilder(uiStrings.TEXT[0]);
///* 236 */     for (AbstractCard c : duplicates) {
///* 237 */       if (c != duplicates.get(false))
///* 238 */         sb.append(AliceLanguageStrings.CAESURA);
///* 239 */       sb.append(c.name);
///*     */     }
///* 241 */     sb.append(AliceLanguageStrings.PERIOD);
///* 242 */     return sb.toString();
///*     */   }
/*     */   
///*     */   public static boolean hasDuplicateCards(ArrayList<AbstractCard> cards, AbstractCard except) {
///* 246 */     HashSet<String> set = new HashSet<String>();
///* 247 */     for (AbstractCard c : cards) {
///* 248 */       if (c == except || PhantomCardModifier.check(c)) {
///*     */         continue;
///*     */       }
///* 251 */       if (!set.add(c.cardID))
///* 252 */         return true;
///*     */     }
///* 254 */     return false;
///*     */   }
/*     */   
/*     */   public static void upgradeCardDamage(AbstractCard card, int amount) {
/* 258 */     ReflectionHacks.privateMethod(AbstractCard.class, "upgradeDamage", new Class[] { int.class
/* 259 */         }).invoke(card, new Object[] { Integer.valueOf(amount) });
/*     */     
/* 261 */     if (card instanceof SpecialAnonCard) {
///* 262 */       ((SpecialAnonCard)card).upgradeSecondaryDamage(amount);
/*     */     }
/* 264 */     card.applyPowers();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String coloredNumber(int amount, int baseAmount) {
/* 269 */     if (amount != baseAmount) {
/* 270 */       return FontHelper.colorString("" + amount, 
/* 271 */           (amount > baseAmount) ? "g" : "r");
/*     */     }
/* 273 */     return FontHelper.colorString("" + amount, "b");
/*     */   }
/*     */   
/* 276 */   static String MARISA_MOD_ID = "TS05_Marisa";
/* 277 */   static String PATCHOULI_MOD_ID = "PatchouliMod";
/*     */   
/* 279 */   static HashMap<String, Boolean> modAvailable = new HashMap();
/*     */   
/*     */   public static boolean isModAvailable(String modID) {
/* 282 */     if (!modAvailable.containsKey(modID)) {
/* 283 */       modAvailable.put(modID, Boolean.valueOf(Loader.isModLoaded(modID)));
/*     */     }
/* 285 */     return ((Boolean)modAvailable.get(modID)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/* 289 */   public static boolean isMarisaModAvailable() { return isModAvailable(MARISA_MOD_ID); }
/*     */ 
/*     */ 
/*     */   
/* 293 */   public static boolean isPatchouliModAvailable() { return isModAvailable(PATCHOULI_MOD_ID); }
/*     */ 
/*     */ 
/*     */   
///* 297 */   public static void log(String what) { AliceMargatroidMod.logger.info(what); }
///*     */
///*     */
///*     */
///* 301 */   public static void log(Object who, Object what) { AliceMargatroidMod.logger.info("{} : {}", who.getClass().getSimpleName(), what); }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\素材\slaymod\AliceMargatroid (2).jar!\rs\antileaf\alic\\utils\AliceSpireKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
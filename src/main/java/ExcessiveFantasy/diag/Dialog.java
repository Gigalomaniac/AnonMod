/*     */ package ExcessiveFantasy.diag;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.ui.DialogWord;
/*     */ import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Dialog
/*     */ {
/*  22 */   private Color color = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  23 */   private Color targetColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  24 */   private static final Color PANEL_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*     */   private static final float COLOR_FADE_SPEED = 8.0F;
/*     */   private boolean isMoving = false;
/*  27 */   private float animateTimer = 0.0F;
/*     */   private static final float ANIM_SPEED = 0.5F;
/*     */   private boolean show = false;
/*  30 */   private float curLineWidth = 0.0F;
/*  31 */   private int curLine = 0;
/*     */   private DialogWord.AppearEffect a_effect;
/*     */   private Scanner s;
/*  34 */   private GlyphLayout gl = new GlyphLayout();
/*  35 */   private ArrayList<DialogWord> words = new ArrayList();
/*     */   private boolean textDone = true;
/*  37 */   private float wordTimer = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final float WORD_TIME = 0.02F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  52 */     if (this.isMoving) {
/*  53 */       this.animateTimer -= Gdx.graphics.getDeltaTime();
/*  54 */       if (this.animateTimer < 0.0F) {
/*  55 */         this.animateTimer = 0.0F;
/*  56 */         this.isMoving = false;
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     this.color = this.color.lerp(this.targetColor, Gdx.graphics.getDeltaTime() * 8.0F);
/*  61 */     if (this.show) {
/*  62 */       for (int i = 0; i < optionList.size(); i++) {
/*  63 */         ((LargeDialogOptionButton)optionList.get(i)).update(optionList.size());
/*  64 */         if (((LargeDialogOptionButton)optionList.get(i)).pressed && waitForInput) {
/*  65 */           selectedOption = i;
/*  66 */           ((LargeDialogOptionButton)optionList.get(i)).pressed = false;
/*  67 */           waitForInput = false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  72 */     if (Settings.lineBreakViaCharacter) {
/*  73 */       bodyTextEffectCN();
/*     */     } else {
/*  75 */       bodyTextEffect();
/*     */     } 
/*     */     
/*  78 */     Iterator var3 = this.words.iterator();
/*     */     
/*  80 */     while (var3.hasNext()) {
/*  81 */       DialogWord w = (DialogWord)var3.next();
/*  82 */       w.update();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedOption() {
/*  88 */     waitForInput = true;
/*  89 */     return selectedOption;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  93 */     optionList.clear();
/*  94 */     this.words.clear();
/*  95 */     waitForInput = true;
/*     */   }
/*     */   
/*     */   public void show() {
/*  99 */     this.targetColor = PANEL_COLOR;
/* 100 */     if (Settings.FAST_MODE) {
/* 101 */       this.animateTimer = 0.125F;
/*     */     } else {
/* 103 */       this.animateTimer = 0.5F;
/*     */     } 
/*     */     
/* 106 */     this.show = true;
/* 107 */     this.isMoving = true;
/*     */   }
/*     */   
/*     */   public void show(String text) {
/* 111 */     updateBodyText(text);
/* 112 */     show();
/*     */   }
/*     */   
/*     */   public void hide() {
/* 116 */     this.targetColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/* 117 */     if (Settings.FAST_MODE) {
/* 118 */       this.animateTimer = 0.125F;
/*     */     } else {
/* 120 */       this.animateTimer = 0.5F;
/*     */     } 
/*     */     
/* 123 */     this.show = false;
/* 124 */     this.isMoving = true;
/* 125 */     Iterator var1 = this.words.iterator();
/*     */     
/* 127 */     while (var1.hasNext()) {
/* 128 */       DialogWord w = (DialogWord)var1.next();
/* 129 */       w.dialogFadeOut();
/*     */     } 
/*     */     
/* 132 */     optionList.clear();
/*     */   }
/*     */ 
/*     */   
/* 136 */   public void removeDialogOption(int slot) { optionList.remove(slot); }
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void addDialogOption(String text) { optionList.add(new LargeDialogOptionButton(optionList.size(), text)); }
/*     */ 
/*     */ 
/*     */   
/* 144 */   public void addDialogOption(String text, AbstractCard previewCard) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, previewCard)); }
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void addDialogOption(String text, AbstractRelic previewRelic) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, previewRelic)); }
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void addDialogOption(String text, AbstractCard previewCard, AbstractRelic previewRelic) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, previewCard, previewRelic)); }
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void addDialogOption(String text, boolean isDisabled) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled)); }
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void addDialogOption(String text, boolean isDisabled, AbstractCard previewCard) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled, previewCard)); }
/*     */ 
/*     */ 
/*     */   
/* 164 */   public void addDialogOption(String text, boolean isDisabled, AbstractRelic previewRelic) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled, previewRelic)); }
/*     */ 
/*     */ 
/*     */   
/* 168 */   public void addDialogOption(String text, boolean isDisabled, AbstractCard previewCard, AbstractRelic previewRelic) { optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled, previewCard, previewRelic)); }
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void updateDialogOption(int slot, String text) { optionList.set(slot, new LargeDialogOptionButton(slot, text)); }
/*     */ 
/*     */ 
/*     */   
/* 176 */   public void updateBodyText(String text) { updateBodyText(text, DialogWord.AppearEffect.BUMP_IN); }
/*     */ 
/*     */   
/*     */   public void updateBodyText(String text, DialogWord.AppearEffect ae) {
/* 180 */     this.s = new Scanner(text);
/* 181 */     this.words.clear();
/* 182 */     this.textDone = false;
/* 183 */     this.a_effect = ae;
/* 184 */     this.curLineWidth = 0.0F;
/* 185 */     this.curLine = 0;
/*     */   }
/*     */   
/*     */   public void clearRemainingOptions() {
/* 189 */     for (int i = optionList.size() - 1; i > 0; i--) {
/* 190 */       optionList.remove(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void bodyTextEffectCN() {
/* 196 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/* 197 */     if (this.wordTimer < 0.0F && !this.textDone) {
/* 198 */       if (Settings.FAST_MODE) {
/* 199 */         this.wordTimer = 0.005F;
/*     */       } else {
/* 201 */         this.wordTimer = 0.02F;
/*     */       } 
/*     */       
/* 204 */       if (this.s.hasNext()) {
/* 205 */         String word = this.s.next();
/* 206 */         if (word.equals("NL")) {
/* 207 */           this.curLine++;
/* 208 */           this.curLineWidth = 0.0F;
/*     */           
/*     */           return;
/*     */         } 
/* 212 */         DialogWord.WordColor color = DialogWord.identifyWordColor(word);
/* 213 */         if (color != DialogWord.WordColor.DEFAULT) {
/* 214 */           word = word.substring(2, word.length());
/*     */         }
/*     */         
/* 217 */         DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
/* 218 */         if (effect != DialogWord.WordEffect.NONE) {
/* 219 */           word = word.substring(1, word.length() - 1);
/*     */         }
/*     */         
/* 222 */         for (int i = 0; i < word.length(); i++) {
/* 223 */           String tmp = Character.toString(word.charAt(i));
/* 224 */           this.gl.setText(FontHelper.topPanelInfoFont, tmp);
/* 225 */           if (this.curLineWidth + this.gl.width > DIALOG_MSG_W) {
/* 226 */             this.curLine++;
/* 227 */             this.curLineWidth = this.gl.width;
/*     */           } else {
/* 229 */             this.curLineWidth += this.gl.width;
/*     */           } 
/*     */
/* 232 */           this.words.add(new DialogWord(FontHelper.topPanelInfoFont, tmp, this.a_effect, effect, color, DIALOG_MSG_X + this.curLineWidth - this.gl.width, DIALOG_MSG_Y - LINE_SPACING * this.curLine, this.curLine));
/* 233 */           if (!this.show) {
/* 234 */             ((DialogWord)this.words.get(this.words.size() - 1)).dialogFadeOut();
/*     */           }
/*     */         } 
/*     */       } else {
/* 238 */         this.textDone = true;
/* 239 */         this.s.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void bodyTextEffect() {
/* 246 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/* 247 */     if (this.wordTimer < 0.0F && !this.textDone) {
/* 248 */       if (Settings.FAST_MODE) {
/* 249 */         this.wordTimer = 0.005F;
/*     */       } else {
/* 251 */         this.wordTimer = 0.02F;
/*     */       } 
/*     */       
/* 254 */       if (this.s.hasNext()) {
/* 255 */         String word = this.s.next();
/* 256 */         if (word.equals("NL")) {
/* 257 */           this.curLine++;
/* 258 */           this.curLineWidth = 0.0F;
/*     */           
/*     */           return;
/*     */         } 
/* 262 */         DialogWord.WordColor color = DialogWord.identifyWordColor(word);
/* 263 */         if (color != DialogWord.WordColor.DEFAULT) {
/* 264 */           word = word.substring(2, word.length());
/*     */         }
/*     */         
/* 267 */         DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
/* 268 */         if (effect != DialogWord.WordEffect.NONE) {
/* 269 */           word = word.substring(1, word.length() - 1);
/*     */         }
/*     */         
/* 272 */         this.gl.setText(FontHelper.topPanelInfoFont, word);
/* 273 */         if (this.curLineWidth + this.gl.width > DIALOG_MSG_W) {
/* 274 */           this.curLine++;
/* 275 */           this.curLineWidth = this.gl.width + CHAR_SPACING;
/*     */         } else {
/* 277 */           this.curLineWidth += this.gl.width + CHAR_SPACING;
/*     */         } 
/*     */         
/* 280 */         this.words.add(new DialogWord(FontHelper.topPanelInfoFont, word, this.a_effect, effect, color, DIALOG_MSG_X + this.curLineWidth - this.gl.width, DIALOG_MSG_Y - LINE_SPACING * this.curLine, this.curLine));
/* 281 */         if (!this.show) {
/* 282 */           ((DialogWord)this.words.get(this.words.size() - 1)).dialogFadeOut();
/*     */         }
/*     */       } else {
/* 285 */         this.textDone = true;
/* 286 */         this.s.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 293 */     if (!AbstractDungeon.player.isDead) {
/* 294 */       Iterator var2 = this.words.iterator();
/*     */       
/* 296 */       while (var2.hasNext()) {
/* 297 */         DialogWord w = (DialogWord)var2.next();
/* 298 */         w.render(sb, -50.0F * Settings.scale);
/*     */       } 
/*     */       
/* 301 */       var2 = optionList.iterator();
/*     */ 
/*     */       
/* 304 */       while (var2.hasNext()) {
/* 305 */         LargeDialogOptionButton b = (LargeDialogOptionButton)var2.next();
/* 306 */         b.render(sb);
/*     */       } 
/*     */       
/* 309 */       var2 = optionList.iterator();
/*     */       
/* 311 */       while (var2.hasNext()) {
/* 312 */         LargeDialogOptionButton b = (LargeDialogOptionButton)var2.next();
/* 313 */         b.renderCardPreview(sb);
/*     */       } 
/*     */       
/* 316 */       var2 = optionList.iterator();
/*     */       
/* 318 */       while (var2.hasNext()) {
/* 319 */         LargeDialogOptionButton b = (LargeDialogOptionButton)var2.next();
/* 320 */         b.renderRelicPreview(sb);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 327 */   private static final float CHAR_SPACING = 8.0F * Settings.scale;
/* 328 */   private static final float LINE_SPACING = 34.0F * Settings.scale;
/* 329 */   public static final float DIALOG_MSG_X = Settings.WIDTH * 0.1F;
/* 330 */   public static final float DIALOG_MSG_Y = 250.0F * Settings.scale;
/* 331 */   public static final float DIALOG_MSG_W = Settings.WIDTH * 0.8F;
/* 332 */   public static ArrayList<LargeDialogOptionButton> optionList = new ArrayList();
/* 333 */   public static int selectedOption = -1;
/*     */   public static boolean waitForInput = true;
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\素材\test\Ruina.jar!\ruina\monsters\theHead\dialogue\Dialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
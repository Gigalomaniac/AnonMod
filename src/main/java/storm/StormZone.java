///*     */ package storm;
///*     */
///*     */ import basemod.helpers.CardModifierManager;
///*     */ import com.badlogic.gdx.Gdx;
///*     */ import com.badlogic.gdx.graphics.Color;
///*     */ import com.badlogic.gdx.graphics.Pixmap;
///*     */ import com.badlogic.gdx.graphics.Texture;
///*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
///*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
///*     */ import com.badlogic.gdx.graphics.glutils.FrameBuffer;
///*     */ import com.badlogic.gdx.graphics.glutils.ShaderProgram;
///*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
///*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
///*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
///*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
///*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
///*     */ import java.util.ArrayList;
///*     */ import spireMapOverhaul.SpireAnniversary6Mod;
///*     */ import spireMapOverhaul.abstracts.AbstractZone;
///*     */ import spireMapOverhaul.util.Wiz;
///*     */ import spireMapOverhaul.zoneInterfaces.CombatModifyingZone;
///*     */ import spireMapOverhaul.zoneInterfaces.OnTravelZone;
///*     */ import spireMapOverhaul.zoneInterfaces.RewardModifyingZone;
///*     */ import spireMapOverhaul.zones.storm.cardmods.DampModifier;
///*     */ import spireMapOverhaul.zones.storm.cardmods.ElectricModifier;
///*     */ import spireMapOverhaul.zones.storm.powers.ConduitPower;
///*     */
///*     */
///*     */
///*     */ public class StormZone
///*     */   extends AbstractZone
///*     */   implements CombatModifyingZone, RewardModifyingZone, OnTravelZone
///*     */ {
///*     */   public static final String ID = "Storm";
///*  35 */   public static final String THUNDER_KEY = SpireAnniversary6Mod.makeID("Storm_Thunder");
///*  36 */   public static final String THUNDER_MP3 = SpireAnniversary6Mod.makePath("audio/storm/thunder.mp3");
///*  37 */   public static final String RAIN_KEY = SpireAnniversary6Mod.makeID("Storm_Rain");
///*  38 */   public static final String RAIN_MP3 = SpireAnniversary6Mod.makePath("audio/storm/rain.mp3");
///*     */
///*     */   public static ShaderProgram mapShader;
///*  41 */   static FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
///*     */
///*     */   public StormZone() {
///*  44 */     super("Storm", new AbstractZone.Icons[] { AbstractZone.Icons.MONSTER });
///*  45 */     this.width = 2;
///*  46 */     this.maxWidth = Integer.valueOf(4);
///*  47 */     this.height = 3;
///*  48 */     this.maxHeight = Integer.valueOf(4);
///*     */   }
///*     */
///*     */
///*     */
///*     */
///*  54 */   public AbstractZone copy() { return new StormZone(); }
///*     */
///*     */
///*     */
///*     */
///*  59 */   public Color getColor() { return Color.DARK_GRAY.cpy(); }
///*     */
///*     */
///*     */   public void onEnterRoom() {
///*  63 */     StormUtil.conduitTarget = null;
///*  64 */     if (StormUtil.rainSoundId == 0L)
///*  65 */       StormUtil.rainSoundId = CardCrawlGame.sound.playAndLoop(RAIN_KEY);
///*     */   }
///*     */
///*     */   public void onExit() {
///*  69 */     CardCrawlGame.sound.stop(RAIN_KEY, StormUtil.rainSoundId);
///*  70 */     StormUtil.rainSoundId = 0L;
///*  71 */     StormUtil.conduitTarget = null;
///*     */   }
///*     */
///*     */
///*     */   public void modifyRewardCards(ArrayList<AbstractCard> cards) {
///*  76 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite || AbstractDungeon.cardRng.randomBoolean()) {
///*  77 */       AbstractCard card = (AbstractCard)cards.get(AbstractDungeon.cardRng.random(cards.size() - 1));
///*  78 */       CardModifierManager.addModifier(card, new ElectricModifier());
///*     */     }
///*     */   }
///*     */
///*     */
///*     */   public void atTurnStartPostDraw() {
///*  84 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
///*  85 */       Wiz.atb(new AbstractGameAction()
///*     */           {
///*     */             public void update() {
///*  88 */               int validCards = StormUtil.countValidCardsInHandToMakeDamp();
///*  89 */               if (validCards > 0) {
///*  90 */                 AbstractCard card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
///*  91 */                 while (!StormUtil.cardValidToMakeDamp(card)) {
///*  92 */                   card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
///*     */                 }
///*  94 */                 CardModifierManager.addModifier(card, new DampModifier());
///*  95 */                 card.superFlash();
///*     */               }
///*  97 */               this.isDone = true;
///*     */             }
///*     */           });
///*     */     }
///*     */
///* 102 */     ArrayList<AbstractMonster> mons = Wiz.getEnemies();
///* 103 */     int totalActors = mons.size() + 1;
///*     */
///* 105 */     if (AbstractDungeon.cardRandomRng.random(1, totalActors) == 1) {
///* 106 */       StormUtil.conduitTarget = AbstractDungeon.player;
///* 107 */       Wiz.applyToSelf(new ConduitPower(AbstractDungeon.player));
///*     */     } else {
///* 109 */       AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
///* 110 */       StormUtil.conduitTarget = m;
///* 111 */       Wiz.applyToEnemy(m, new ConduitPower(m));
///*     */     }
///*     */   }
///*     */
///*     */
///*     */   public void renderOnMap(SpriteBatch sb, float alpha) {
///* 117 */     if (SpireAnniversary6Mod.getShaderConfig()) {
///* 118 */       sb.flush();
///* 119 */       fbo.begin();
///*     */
///* 121 */       Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
///* 122 */       Gdx.gl.glClear(16384);
///* 123 */       super.renderOnMap(sb, alpha);
///* 124 */       sb.flush();
///* 125 */       fbo.end();
///*     */
///*     */
///* 128 */       TextureRegion region = new TextureRegion((Texture)fbo.getColorBufferTexture());
///* 129 */       region.flip(false, true);
///*     */
///* 131 */       if (mapShader == null) {
///* 132 */         mapShader = StormUtil.initElectricShader(mapShader);
///*     */       }
///* 134 */       sb.setShader(mapShader);
///* 135 */       sb.setColor(Color.WHITE);
///* 136 */       mapShader.setUniformf("u_time", SpireAnniversary6Mod.time);
///* 137 */       mapShader.setUniformf("u_bright_time", 0.5F);
///*     */
///* 139 */       sb.draw(region, 0.0F, 0.0F);
///* 140 */       sb.setShader(null);
///* 141 */       sb.flush();
///*     */     } else {
///* 143 */       super.renderOnMap(sb, alpha);
///*     */     }
///*     */   }
///*     */ }
//
//
///* Location:              C:\Users\Administrator\Desktop\素材\common\3142318909\SpireBiomes.jar!\spireMapOverhaul\zones\storm\StormZone.class
// * Java compiler version: 8 (52.0)
// * JD-Core Version:       1.0.7
// */
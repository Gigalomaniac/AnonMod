/*     */ package cards.SpecialCard;
/*     */ 
/*     */ import Mod.AnonMod;
import basemod.abstracts.CustomSavable;
/*     */ import bossRoom.effect.AshAnonChangeSceneEffect;
import bossRoom.effect.ChangeSceneEffectLeft;
import bossRoom.effect.LatterEffect;
import cards.AbstractLockAnonCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
/*     */import cards.others.LoveMeIFYouCan;
import power.heavy;
import power.songs;
import relics.FirstHalfKey;
import relics.Mana_relic;
import relics.SecondHalfKey;
/*     */ import java.util.ArrayList;

import static com.megacrit.cardcrawl.helpers.input.InputActionSet.masterDeck;

 public class AIHeart
   extends AbstractLockAnonCard
   implements CustomSavable<Integer>
 {    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("AIHeartSongs");
    public static final String NAME = cardStrings.NAME;
    public static  String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 0;
    public static final String ID = "AIHeartSongs";
    public static  String IMG_PATH = "img/card_Anon/Ash/aiheart_skill.png";
    /*  37 */   public ArrayList<AbstractCard> focus1 = new ArrayList();
/*  38 */   public ArrayList<AbstractCard> focus2 = new ArrayList();
/*     */    private ChangeSceneEffectLeft effect;
/*     */   public AIHeart() {
/*  41 */     super("AIHeartSongs", NAME,IMG_PATH, DESCRIPTION, CardRarity.SPECIAL, CardTarget.SELF, 40, 2, 0);
            this.baseDamage = 0;
            this.baseMagicNumber = 0;
/*  42 */     this.baseMagicNumber = 2;
/*  43 */     this.magicNumber = this.baseMagicNumber;
/*  44 */     this.counter = 0;
/*  45 */     this.counter2 = 0;
/*  46 */     initInfo();
/*     */   }
/*     */
@Override
public void use(AbstractPlayer p, AbstractMonster m) {
    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "机械心"), 1));

    if(!locked && this.baseDamage == 0){
        this.effect = new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/bg00948_1920.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {

            AbstractDungeon.effectsQueue.add(this.effect);
            this.baseDamage +=1;
        }));

    }
}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsedCard(AbstractCard card, boolean hand, AbstractCreature target) {
/*  59 */

/*     */   }
/*     */ 
/*     */   
/*     */   public void endOfTurn(boolean hand) {
/* 109 */     super.endOfTurn(hand);
/* 110 */     for (AbstractCard card : this.focus2) {
/* 111 */       AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
/* 112 */       AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
/* 113 */       AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
/*     */     } 
/* 115 */     this.focus2.clear();
/*     */     
/* 117 */     this.focus2.addAll(this.focus1);
/* 118 */     this.focus1.clear();
/*     */   }
/*     */ 
/*     */
/*     */ 
/*     */
/*     */ 
/*     */   
/* 137 */   public AbstractCard makeCopy() { return new AIHeart(); }
     public void doughnut(){
         this.textureImg = unlock_cardIMG_PATH;
         this.loadCardImage(unlock_cardIMG_PATH);
     }

     public void initInfo() {
      if (this.locked != true) {
          this.setBackgroundTexture(unlock_IMG_PATH512, unlock_IMG_PATH);
      } else {
          this.setBackgroundTexture(lock_IMG_PATH512, lock_IMG_PATH);
          this.textureImg = lock_cardIMG_PATH;
          this.loadCardImage(lock_cardIMG_PATH);
      }
      loadImg();
      if(AnonMod.saves.getBool("MachineHeartLocked") != true){
          this.locked = false;
          unlockInfo();
          this.setBackgroundTexture(unlock_IMG_PATH512,unlock_IMG_PATH);

      }else {
          this.setBackgroundTexture(lock_IMG_PATH512,lock_IMG_PATH);
          this.textureImg = lock_cardIMG_PATH;
          this.loadCardImage(lock_cardIMG_PATH);
      }
      this.name = EXTENDED_DESCRIPTION[0];
      initializeTitle();
      this.rawDescription = EXTENDED_DESCRIPTION[this.infoStage];

        if(AbstractDungeon.player != null) {
            this.baseMagicNumber=this.magicNumber = (AbstractDungeon.player.hasRelic(FirstHalfKey.ID) ? 0 : 1) + (AbstractDungeon.player.hasRelic(SecondHalfKey.ID) ? 0 : 1);
        }
      if(this.infoStage == 1){
          this.rawDescription = EXTENDED_DESCRIPTION[3];;
      }
          initializeDescription();
          super.initInfo();

  }

    @Override

/*     */   public Integer onSave() {
            if(this.locked) {
                return 0;
            }else {
                return 1;

            }
/* 173 */
/*     */   }

    @Override
    public void onLoad(Integer integer) {
        if(integer.equals(0)){
            locked = true;
        }else {
            locked = false;
            this.setBackgroundTexture(unlock_IMG_PATH512,unlock_IMG_PATH);
//            this.textureImg = unlock_cardIMG_PATH;
//            this.loadCardImage(unlock_cardIMG_PATH);
            this.loadImg();
                AnonMod.saves.setBool("MachineHeartLocked", false);

        }
    }

    /*     */
/*     */
/*     */
  public void onLoad(int[] arg0) {
    initInfo();
  }
    public void unlockInfo() {
            super.unlockInfo();
        this.setBackgroundTexture(unlock_IMG_PATH512,unlock_IMG_PATH);
//        this.textureImg = unlock_cardIMG_PATH;
//        this.loadCardImage(unlock_cardIMG_PATH);
    }
   public void update() {
       super.update();
       if(AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(Mana_relic.ID)){
           this.textureImg = unlock_cardIMG_PATH;
           this.loadCardImage(unlock_cardIMG_PATH);
       }else {
           this.textureImg = lock_cardIMG_PATH;
           this.loadCardImage(lock_cardIMG_PATH);
       }
           if(AnonMod.saves.getBool("MachineHeartLocked") != true){
               this.setBackgroundTexture(unlock_IMG_PATH512,unlock_IMG_PATH);
           }else {
               this.setBackgroundTexture(lock_IMG_PATH512,lock_IMG_PATH);
           }
   }

     public void renderCardPreviewInSingleView(SpriteBatch sb) {
      super.renderCardPreviewInSingleView(sb);
         if(AnonMod.saves.getBool("MachineHeartLocked") == true){

         }
     }
    public void applyPowers() {
       super.applyPowers();
        this.baseMagicNumber = this.magicNumber = (AbstractDungeon.player.hasRelic(FirstHalfKey.ID) ? 0 : 1) + (AbstractDungeon.player.hasRelic(SecondHalfKey.ID) ? 0 : 1);
        if(this.magicNumber ==0 && this.infoStage == 1){
            this.rawDescription = EXTENDED_DESCRIPTION[3];
        }else {
            this.rawDescription = EXTENDED_DESCRIPTION[this.infoStage];
        }

    }
    public static final String lock_IMG_PATH512 = "img/1024/bg_skill_IF_512.png";
    public static final String lock_IMG_PATH = "img/1024/bg_skill_IF.png";
    public static final String unlock_IMG_PATH = "img/1024/bg_skill_IF_unlock.png";
    public static final String unlock_IMG_PATH512 = "img/1024/bg_skill_IF_unlock512.png";

    public static final String lock_cardIMG_PATH = "img/card_Anon/Ash/aiheart_skill.png";
    public static final String lock_cardIMG_PATH512 = "img/card_Anon/Ash/aiheart_skill_p.png";
    public static final String unlock_cardIMG_PATH = "img/card_Anon/Ash/aiheart_upgrade_skill.png";
    public static final String unlock_cardIMG_PATH512 = "img/card_Anon/Ash/aiheart_upgrade_skill_p.png";
   public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;



  private int counter;
  private int counter2;
}

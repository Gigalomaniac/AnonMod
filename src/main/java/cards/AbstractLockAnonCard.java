/*     */ package cards;
/*     */ 
/*     */ import Mod.AnonMod;
import basemod.abstracts.CustomCard;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pathes.AbstractCardEnum;

import java.util.UUID;

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
/*     */ 
/*     */ 
/*     */ public abstract class AbstractLockAnonCard extends CustomCard
/*     */ {
/*  32 */   protected int[] i = { 0 };
/*     */ 
/*     */   
/*  35 */   public int infoStage = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractLockAnonCard(String id, String name,String img, String description, CardRarity rarity, CardTarget target, int AbnormalityID, int maxInfo, int realCost) {
/*  42 */     super(id, name, img, 1, description, CardType.SKILL, AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, target);
/*  43 */     this.AbnormalityID = AbnormalityID;
/*  44 */     this.maxInfo = maxInfo;
/*  45 */     this.realCost = realCost;
/*  46 */     this.realRarity = rarity;
/*  47 */     this.canActivated = false;
/*  48 */     this.realTarget = target;

/*     */   }
/*     */
/*     */
/*     */   
/*  62 */   public void obtain() { CardCrawlGame.sound.play("DoorClick"); }
/*     */ 
/*     */   
/*     */   public void atPreBattle() {}
/*     */ 
/*     */   
/*     */   public void atBattleStart() {}
/*     */ 
/*     */   
/*     */   public void onUsedCard(AbstractCard card, boolean hand) {}
/*     */ 
/*     */   
/*     */   public void onUsedCard(AbstractCard card, boolean hand, AbstractCreature target) {}
/*     */ 
/*     */   
/*     */   public void endOfTurn(boolean hand) {}
/*     */   
/*     */   public void ExhaustCard(AbstractCard card, boolean hand) {}
/*     */   
/*     */   public void onVictory() {}
/*     */   
/*     */   public void onShuffle() {}
/*     */   
/*     */   public void onMonsterDeath(AbstractMonster monster) {}
/*     */   
/*     */   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target, boolean hand) {}
/*     */   
/*  89 */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount, boolean hand) { return damageAmount; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public float atDamageGive(float damage, DamageInfo.DamageType type) { return damage; }
/*     */ 
/*     */   
/*     */   public void onLoseHP(int damageAmount, boolean hand) {}
/*     */   
/*     */   public void onEnterRoom(AbstractRoom room) {}
/*     */   
/*     */   public void loadImg() {}
/*     */   
/*     */   public void initInfo() {
/* 103 */     this.cost = this.realCost;
/* 104 */     this.costForTurn = this.realCost;
/* 105 */     this.rarity = this.realRarity;
/* 106 */     this.target = this.realTarget;
/*     */   }
/*     */   
/*     */   public void changeCost(int cost) {
/* 110 */     this.realCost = cost;
/* 111 */     if (this.cost != -1) {
/* 112 */       this.cost = this.realCost;
/* 113 */       this.costForTurn = this.realCost;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeCostForTurn(int cost) {
/* 118 */     if (this.cost != -1) {
/* 119 */       this.costForTurn = cost;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canUnlockInfo() {
/* 124 */     if (AbstractDungeon.player == null) {
/* 125 */       return false;
/*     */     }
/* 127 */     if (!AbstractDungeon.player.hasRelic("CogitoBucket")) {
/* 128 */       return false;
/*     */     }
/* 130 */     int unlockCost = 0;
/* 131 */     switch (this.realRarity) {
/*     */       case COMMON:
/* 133 */         unlockCost = 30;
/*     */         break;
/*     */       case UNCOMMON:
/* 136 */         unlockCost = 60;
/*     */         break;
/*     */       case RARE:
/* 139 */         unlockCost = 90;
/*     */         break;
/*     */     } 
/* 142 */     return ((AbstractDungeon.player.getRelic("CogitoBucket")).counter >= unlockCost);
/*     */   }
/*     */
  public void unlockInfo() {
        this.locked = false;

  }
/*     */ 
/*     */   
/* 172 */   protected void unlockSuccess() {
//    LobotomyMod.logger.info("level: " + CogitoBucket.level[this.AbnormalityID]);

}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public boolean canUpgrade() { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade() {}
/*     */ 
/*     */   
/////* 184 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("AbstractLobotomyCard");
///* 185 */   public static final String NAME = cardStrings.NAME;
///* 186 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
///* 187 */   public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
/*     */   public int realCost;
/*     */   public int AbnormalityID;
/*     */   public int maxInfo;
/*     */   public CardRarity realRarity;
/*     */   public boolean canActivated;
/*     */   public CardTarget realTarget;

            public boolean locked = AnonMod.saves.getBool("MachineHeartLocked");;
/*     */ }


/* Location:              C:\Users\Administrator\Deskto\\uncommon\Lobotomy.jar!\lobotomyMod\card\AbstractLobotomyCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
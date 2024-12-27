/*    */ package patch;

 import BossRewards.KarenRewardCard;
 import OnStage.promiseOnStage;
 import TheTreeOfQliphoth.TheTreeOfQliphoth;
 import actions.ActionManagerClearAction;
 import actions.yakusokuNoBashoAction;
 import bossRoom.effect.LatterEffect;
 import cards.AbstractLockAnonCard;
 import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
 import com.evacipated.cardcrawl.modthespire.lib.Matcher;
 import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
 import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
 import javassist.CtBehavior;
 import power.Karen.RevueStarlight;
 import relics.PositionZero;
 import relics.StarAnonBike;

 import java.util.Iterator;

 import static utils.AnonSpireKit.addToBot;
 import static utils.AnonSpireKit.addToTop;


@SpirePatch(clz = AbstractPlayer.class, method = "damage", paramtypez = {DamageInfo.class})
 public class PositionZeroDeathPatch
 {
   @SpireInsertPatch(locator = Locator.class)
   public static SpireReturn Insert(AbstractPlayer p, DamageInfo info) {
       Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

       AbstractCard c;
       while(var1.hasNext()) {
           c = (AbstractCard)var1.next();
           if (c.cardID.equals(KarenRewardCard.ID)) {
               AbstractDungeon.player.masterDeck.removeCard(c);
               addToTop(new HealAction((AbstractCreature) p, (AbstractCreature) p,  p.maxHealth));
               if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                   if(!AbstractDungeon.player.hand.isEmpty()) {
                       Iterator var2 = AbstractDungeon.player.hand.group.iterator();
                       while(var2.hasNext()) {
                           AbstractCard d = (AbstractCard) var2.next();
                           if (d.cardID.equals(KarenRewardCard.ID)) {
                               addToTop(new ExhaustSpecificCardAction(d, AbstractDungeon.player.hand));
                               return SpireReturn.Return(null);
                           }
                       }
                   }
                   if(!AbstractDungeon.player.drawPile.isEmpty()) {
                       Iterator var3 = AbstractDungeon.player.drawPile.group.iterator();
                       while(var3.hasNext()) {
                       AbstractCard e = (AbstractCard) var3.next();
                       if (e.cardID.equals(KarenRewardCard.ID)) {
                           addToTop(new ExhaustSpecificCardAction(e, AbstractDungeon.player.drawPile));
                           return SpireReturn.Return(null);
                       }
                   }
                   }  if(!AbstractDungeon.player.discardPile.isEmpty()) {
                       Iterator var4 = AbstractDungeon.player.discardPile.group.iterator();
                       while(var4.hasNext()) {
                           AbstractCard f = (AbstractCard) var4.next();
                           if (f.cardID.equals(KarenRewardCard.ID)) {
                               addToTop(new ExhaustSpecificCardAction(f, AbstractDungeon.player.discardPile));
                               return SpireReturn.Return(null);
                           }
                       }
                   }
               }
               return SpireReturn.Return(null);
           }
       }
     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&  AbstractDungeon.id.equals(promiseOnStage.ID)) {
       if (p.hasRelic(PositionZero.ID) ) {
           addToTop(new HealAction((AbstractCreature) p, (AbstractCreature) p,  p.maxHealth));
           addToTop((AbstractGameAction) new ApplyPowerAction(p, p, (AbstractPower) new RevueStarlight(p,false), 1));
           AbstractDungeon.actionManager.clear();
           if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                       AbstractDungeon.actionManager.addToTop(new yakusokuNoBashoAction());
               for (AbstractRelic relic : AbstractDungeon.player.relics) {
                   if (relic.relicId.equals(PositionZero.ID)) {
                       relic.flash();
                       ((PositionZero)relic).playerdead();
                       break;
                   }
               }

           }else {

               CardCrawlGame.nextDungeon =  promiseOnStage.ID;
               CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(promiseOnStage.ID);
               AbstractDungeon.actNum = 4;
               AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
           }
//           this.usedUp();
//           this.setCounter(-2);
         return SpireReturn.Return(null);
       }
     }


     return SpireReturn.Continue();
   }

   private static class Locator
     extends SpireInsertLocator {
     public int[] Locate(CtBehavior ctBehavior) throws Exception {
       Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "isDead");
       return LineFinder.findInOrder(ctBehavior, fieldAccessMatcher);
     }
   }
 }

/*    */ package patch;
/*    */
/*    */ import cards.SpecialCard.AIHeart;
import cards.others.LoveMeIFYouCan;
import cards.uncommon.KiraKiraDokiDoki;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
import relics.EliteAshAnonKey;
import relics.FirstHalfKey;
import relics.SecondHalfKey;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @SpirePatch(clz = AbstractRoom.class, method = "addRelicToRewards", paramtypez = {AbstractRelic.RelicTier.class})
/*    */ public class EliteDropPatch
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn<Void> aaa(AbstractRoom _this, AbstractRelic.RelicTier tier) {
/* 25 */ System.out.println(AbstractDungeon.player.hasRelic(FirstHalfKey.ID)+""+AbstractDungeon.actNum);
    if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite  && AbstractDungeon.player.hasRelic(FirstHalfKey.ID) && !AbstractDungeon.player.hasRelic(SecondHalfKey.ID) && AbstractDungeon.actNum!=1) {
        AbstractDungeon.getCurrRoom().addRelicToRewards(new SecondHalfKey());
//        if (!AbstractDungeon.player.hasRelic(ManaCrystal.ID)) {
///* 32 */         _this.rewards.add(new RewardItem(new ManaCrystal()));
///* 33 */         return SpireReturn.Return();
///*    */       }
/*    */     }
/*    */
/* 37 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\素材\common\ZekkenNoMaou.jar!\mowang\Patches\relicPatch\EliteDropPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
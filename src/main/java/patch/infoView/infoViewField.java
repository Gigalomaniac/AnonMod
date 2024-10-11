/*    */ package patch.infoView;
/*    */ 
/*    */ import Mod.AnonMod;
import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = com.megacrit.cardcrawl.screens.SingleCardViewPopup.class, method = "<class>")
/*    */ public class infoViewField
/*    */ {
/* 17 */   public static SpireField<Hitbox> lastInfo = new SpireField(() -> null);
/* 18 */   public static SpireField<Hitbox> lock = new SpireField(() -> null);
/* 19 */   public static SpireField<Hitbox> nextLock = new SpireField(() -> null);
/* 20 */   public static SpireField<Hitbox> nextInfo = new SpireField(() -> null);

    public static boolean locked=   AnonMod.saves.getBool("MachineHeartLocked");




    /*    */ }


/* Location:              C:\Users\Administrator\Deskto\\uncommon\Lobotomy.jar!\lobotomyMod\patch\infoView\infoViewField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
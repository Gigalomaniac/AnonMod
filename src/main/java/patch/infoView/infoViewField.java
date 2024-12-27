 package patch.infoView;

 import Mod.AnonMod;
import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.megacrit.cardcrawl.helpers.Hitbox;








 @SpirePatch(clz = com.megacrit.cardcrawl.screens.SingleCardViewPopup.class, method = "<class>")
 public class infoViewField
 {
   public static SpireField<Hitbox> lastInfo = new SpireField(() -> null);
   public static SpireField<Hitbox> lock = new SpireField(() -> null);
   public static SpireField<Hitbox> nextLock = new SpireField(() -> null);
   public static SpireField<Hitbox> nextInfo = new SpireField(() -> null);

    public static boolean locked=   AnonMod.saves.getBool("MachineHeartLocked");




    /*    */ }

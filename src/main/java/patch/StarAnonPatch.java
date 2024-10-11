package patch;

 import power.StarAnon.DeterminationToProtectHer;
 import star.StarAnonSide;
 import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class StarAnonPatch
 {
   @SpirePatch(clz = TopPanel.class, method = "updateSettingsButtonLogic")
   public static class updateSettingsButtonLogic
   {
     @SpireInsertPatch(rloc = 4)
     public static SpireReturn Insert(TopPanel _inst) {
       if (((_inst.settingsHb.hovered && InputHelper.justClickedLeft) || InputHelper.pressedEscape || CInputActionSet.settings.isJustPressed()) &&
         (AbstractDungeon.getCurrRoom()).monsters != null && (AbstractDungeon.getCurrRoom()).monsters.monsters != null && !(AbstractDungeon.getCurrRoom()).monsters.monsters.isEmpty()) {
            for(int i =0 ; i < (AbstractDungeon.getCurrRoom()).monsters.monsters.size() ; i++){
                if ((AbstractDungeon.getCurrRoom()).monsters.monsters.get(i) instanceof StarAnonSide) {
                    if((AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hasPower(DeterminationToProtectHer.POWER_ID)){
                        ((StarAnonSide)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).priceForStop();
                        InputHelper.pressedEscape = false;
                        CInputActionSet.settings.unpress();
                        InputHelper.justClickedLeft = false;
                        return SpireReturn.Return(null);
                    }
                }
            }
         }

       return SpireReturn.Continue();
     }
   }
   }
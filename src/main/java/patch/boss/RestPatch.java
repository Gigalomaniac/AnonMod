package patch.boss;

import ExcessiveFantasy.ExcessiveFantasyRestRoom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;

import java.lang.reflect.Field;
import java.util.ArrayList;

@SpirePatch(
        clz = CampfireUI.class,
        method = "initializeButtons"
)
public class RestPatch {
    public RestPatch() {
    }
    static CampfireUI campfireUI = new CampfireUI();
    @SpirePrefixPatch
    public static SpireReturn<Void> RestCampfireUI(CampfireUI __instance) throws NoSuchFieldException, IllegalAccessException {
        if (AbstractDungeon.getCurrRoom() instanceof ExcessiveFantasyRestRoom) {


            // 获取 buttons 字段
            Field buttonsField = __instance.getClass().getDeclaredField("buttons");
            // 设置访问权限为 true，即使它是 private 的
            buttonsField.setAccessible(true);

            // 获取 buttons 字段的值
            @SuppressWarnings("unchecked")
            ArrayList<AbstractCampfireOption> buttons = new ArrayList<>();
            buttons.add(new RestOption(true));
            buttonsField.set(__instance,buttons);

            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }



}

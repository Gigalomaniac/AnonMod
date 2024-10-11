package sakikoMod;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.qingmu.sakiko.relics.menbers.Anon;

public class receiveRelic {
    public static void receiveRelicAddsakikoMod() {
        // 在这里注册
        Anon Anon = new Anon();
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) Anon);
    }
}

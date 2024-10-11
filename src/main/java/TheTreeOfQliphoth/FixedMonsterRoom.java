package TheTreeOfQliphoth;

import actlikeit.RazIntent.AssetLoader;
import actlikeit.patches.AbstractRoomUpdateIncrementElitesPatch;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import java.util.HashMap;
import java.util.Map;


public class FixedMonsterRoom extends MonsterRoom {
    private String encounterID;
    protected AbstractRelic relic;
    protected static Map<String, String> relics = new HashMap();

    public FixedMonsterRoom(String encounterID, String mapImg, String mapOutlineImg) {
        this.encounterID = encounterID;
        this.setMapImg(AssetLoader.loadImage(mapImg), AssetLoader.loadImage(mapOutlineImg));
        this.relic = RelicLibrary.getRelic((String)relics.get(encounterID));
    }

    public static void initialize() {
    }

    public void onPlayerEntry() {
        this.playBGM((String)null);
        this.monsters = BaseMod.getMonster(this.encounterID);
        this.monsters.init();
        waitTimer = 0.1F;
    }

    public void endBattle() {
        super.endBattle();
        AbstractRoomUpdateIncrementElitesPatch.Insert((AbstractRoom)null);
    }

    public void relicTips(SpriteBatch sb, float x, float y) {
        if (this.relic.isSeen) {
            TipHelper.queuePowerTips(x, y, this.relic.tips);
        }

    }
}

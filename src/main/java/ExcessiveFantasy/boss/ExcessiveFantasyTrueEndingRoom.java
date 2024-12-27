package ExcessiveFantasy.boss;

import Mod.AnonMod;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public class ExcessiveFantasyTrueEndingRoom
        extends AbstractRoom {
    public ExcessiveFantasyTrueEndingRoom() {
        this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
    }

    public void onPlayerEntry() {
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        CardCrawlGame.fadeIn(1.0F);
        CardCrawlGame.music.silenceBGM();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.silenceTempBgmInstantly();
            if(AnonMod.saves.getString("choice").equals("soyo"))
        AbstractDungeon.topLevelEffectsQueue.add(new AnotherStoryExcessiveFantasyEndingSoyo(0, 9));
        else
            AbstractDungeon.topLevelEffectsQueue.add(new AnotherStoryExcessiveFantasyEndingTomori(0, 9));
    }
}

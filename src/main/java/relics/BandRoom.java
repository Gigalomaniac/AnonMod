package relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.EventRoom;

public class BandRoom extends EventRoom {
    @Override
    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
//        String eventName = AbstractDungeon.eventList.remove(0);
//        this.event = EventHelper.getEvent(eventName);
        this.event.onEnterRoom();
    }
}

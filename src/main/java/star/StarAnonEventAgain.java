package star;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.RestRoom;
import relics.Rana_relic;
import relics.Sakiko_relic;
import relics.StarAnonGuitar;
import relics.StarAnonPocketWatch;

public class StarAnonEventAgain extends AbstractImageEvent {
    private static final EventStrings eventStrings;

    public static final String[] OPTIONS;

    public static final String[] DESCRIPTIONS;
    public static String StarAnonEventID = "StarAnonEventAgain";
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static String title = "";
    public StarAnonEventAgain() {
        super(title, getBody(), "");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
            this.imageEventText.setDialogOption(OPTIONS[0]);
    }


    private static String getBody() {
        return DESCRIPTIONS[0];
    }

    private enum AllBandFriends {
       all,case1,case2,case3,case4,fin,crychic1,crychic2,crychic3,crychic4,Ash1,Ash2,Ash3,Ash4
    }
    @Override
    protected void buttonEffect(int buttonPressed) {
            AbstractEvent event;
            switch (this.BandEvents) {
                case all:
                    switch (buttonPressed) {
                        case 0:
                            this.imageEventText.updateBodyText( DESCRIPTIONS[1]);
                            StarAnonPocketWatch StarAnonPocketWatch = new StarAnonPocketWatch();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) StarAnonPocketWatch);
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption(OPTIONS[1]);
                            this.BandEvents = AllBandFriends.case1;
                            return;
                    }
                    openMap();
                    return;
                case case1:
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[2]);
                    this.BandEvents = AllBandFriends.fin;
                    return;

                case fin:
//                this.imageEventText.clearAllDialogs();
                    openMap();


        }
    }




    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("StarAnonEventAgain");
        title = eventStrings.NAME;
        OPTIONS = eventStrings.OPTIONS;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}

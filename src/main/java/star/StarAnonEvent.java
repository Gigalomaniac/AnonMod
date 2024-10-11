package star;

import basemod.abstracts.events.PhasedEvent;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.RestRoom;
import relics.*;

public class StarAnonEvent extends AbstractImageEvent
{
    private static final EventStrings eventStrings;

    public static final String[] OPTIONS;

    public static final String[] DESCRIPTIONS;
    public static String StarAnonEventID = "StarAnonEvent";
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static final String title ;
    public StarAnonEvent() {
        super(title,  getBody(),"");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        this.imageEventText.setDialogOption(OPTIONS[0]);
    }


    private static String getBody() {
        String body = "";

             body = DESCRIPTIONS[0];


        return body;
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
                            this.imageEventText.updateBodyText(DESCRIPTIONS[1]);

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
                    if(Settings.hasEmeraldKey && Settings.hasRubyKey && Settings.hasSapphireKey && !AbstractDungeon.player.hasRelic(StarAnonGuitar.ID)){
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                    }else {
                        this.imageEventText.setDialogOption(OPTIONS[3],true);
                    }

                    this.imageEventText.setDialogOption(OPTIONS[4]);
                    this.BandEvents = AllBandFriends.case2;
                    return;
                case case2:
                    switch (buttonPressed) {
                        case 0:
//                            this.imageEventText.loadImage("img/event/AnonThink2.png");
                            this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                            if(AbstractDungeon.player.hasRelic(Guitar.ID)){
                                AbstractDungeon.player.loseRelic(Guitar.ID);
                            }
                            StarAnonGuitar StarAnonGuitar = new StarAnonGuitar();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) StarAnonGuitar);
                            this.BandEvents = AllBandFriends.fin;
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.setDialogOption(OPTIONS[4]);
                            return;
                        case 1:
//                        this.imageEventText.loadImage("img/event/AnonThink2.png");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.BandEvents = AllBandFriends.fin;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        return;
                    }
                case fin:
//                this.imageEventText.clearAllDialogs();
                    openMap();

        }
    }


    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("StarAnonEvent");
        title = eventStrings.NAME;
        OPTIONS = eventStrings.OPTIONS;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}

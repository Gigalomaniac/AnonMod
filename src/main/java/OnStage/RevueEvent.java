package OnStage;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
import potions.IdeaPotion;
import relics.*;

public class RevueEvent extends AbstractImageEvent {

    private static final EventStrings eventStrings;

    public static final String[] OPTIONS;

    public static final String[] DESCRIPTIONS;
    public static String id = "RevueEvent";
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static String title = "";
    public RevueEvent() {
        super(title, getBody(), "");
        this.imageEventText.loadImage("img/event/Wagarimasu.png");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
            this.imageEventText.setDialogOption(OPTIONS[0]);
            this.imageEventText.setDialogOption(OPTIONS[1]);
            this.imageEventText.setDialogOption(OPTIONS[2]);


//        this.imageEventText.loadImage("img/event/Anon3.png");
    }

    private static String getBody() {
        String body = "";
        body = DESCRIPTIONS[0];
        return body;
    }

    private enum AllBandFriends {
       all,case1,case2,case3,case4,fin,fin2,crychic1,crychic2,crychic3,crychic4,Ash1,Ash2,Ash3,Ash4
    }
    @Override
    protected void buttonEffect(int buttonPressed) {

            AbstractEvent event;
            switch (this.BandEvents) {
                case all:
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.BandEvents = AllBandFriends.fin;
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[3]);
                    switch (buttonPressed) {
                        case 0:
                            AbstractDungeon.player.gainGold(1000);
                            return;
                        case 1:
                            AbstractDungeon.player.maxHealth =  AbstractDungeon.player.maxHealth*2;
                            AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
                            return;
                        case 2:
                            AbstractPlayer var10000 = AbstractDungeon.player;
                            var10000.potionSlots += 2;
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 2));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
                            for(int i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
                                AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(new IdeaPotion()));
                            }
                            return;
                    }
                    openMap();
                    return;
                case fin:
                    this.BandEvents = AllBandFriends.fin2;
                    openMap();
                    return;
                case fin2:
                    openMap();
            }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(id);
        title = eventStrings.NAME;
        OPTIONS = eventStrings.OPTIONS;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}

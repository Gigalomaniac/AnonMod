package TheTreeOfQliphoth;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.EntropicBrew;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import potions.IdeaPotion;
import relics.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Present extends AbstractImageEvent
{
    private static final EventStrings eventStrings;

    public static final String[] OPTIONS;

    public static final String[] DESCRIPTIONS;
    public static String StarAnonEventID = "Present";
    private AllBandFriends BandEvents = AllBandFriends.all;
    private static final String title ;
    public Present() {
        super(title,  getBody(),"");
        this.imageEventText.clearAllDialogs();
        this.imageEventText.clearRemainingOptions();
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
        this.imageEventText.loadImage("img/event/Anon4.png");
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
                            AiHeartBlessing AiHeartBlessing = new AiHeartBlessing();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), AiHeartBlessing);
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.clearRemainingOptions();
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.setDialogOption(OPTIONS[2]);
                            this.imageEventText.setDialogOption(OPTIONS[3]);
                            return;
                        case 1:
                            AbstractPlayer var10000 = AbstractDungeon.player;
                            var10000.potionSlots += 8;
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 8));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 7));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 6));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 5));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 4));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 3));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 2));
                            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
                            for(int i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
                                AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(new IdeaPotion()));
                            }
                            IdeaBlessing idea = new IdeaBlessing();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), idea);
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.clearRemainingOptions();
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.setDialogOption(OPTIONS[2]);
                            this.imageEventText.setDialogOption(OPTIONS[3]);
                            return;
                    }

                    openMap();
                    return;
                case case1:
                    switch (buttonPressed) {
                        case 0:
                            Iterator var11 = AbstractDungeon.player.masterDeck.group.iterator();
                            int effectCount = 0;
                            List<String> upgradedCards = new ArrayList();
                            while(var11.hasNext()) {
                                AbstractCard c = (AbstractCard)var11.next();
                                if (c.canUpgrade()) {
                                    ++effectCount;
                                    if (effectCount <= 20) {
                                        float x = MathUtils.random(0.1F, 0.9F) * (float)Settings.WIDTH;
                                        float y = MathUtils.random(0.2F, 0.8F) * (float)Settings.HEIGHT;
                                        AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
                                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                                    }

                                    upgradedCards.add(c.cardID);
                                    c.upgrade();
                                    AbstractDungeon.player.bottledCardUpgradeCheck(c);
                                }
                            }
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.clearRemainingOptions();
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.setDialogOption(OPTIONS[4]);
                            this.imageEventText.setDialogOption(OPTIONS[5]);
                            this.BandEvents = AllBandFriends.case2;
                            return;
                        case 1:
                            AbstractRelic EnergyBlessing = new EnergyBlessing();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), EnergyBlessing);
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.clearRemainingOptions();
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.setDialogOption(OPTIONS[4]);
                            this.imageEventText.setDialogOption(OPTIONS[5]);
                            this.BandEvents = AllBandFriends.case2;
                            return;
                    }
                    this.BandEvents = AllBandFriends.case2;
                    return;
                case case2:
                    switch (buttonPressed) {
                        case 0:
                            AbstractDungeon.player.gainGold(2000);
                            AbstractRelic Courier = new Courier();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), Courier);
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.clearRemainingOptions();
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.setDialogOption(OPTIONS[6]);
                            this.BandEvents = AllBandFriends.fin;
                            return;
                        case 1:
                            AbstractRelic hpBlessing = new hpBlessing();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), hpBlessing);
                            this.imageEventText.clearAllDialogs();
                            this.imageEventText.clearRemainingOptions();
                            this.BandEvents = AllBandFriends.case1;
                            this.imageEventText.setDialogOption(OPTIONS[6]);
                            this.BandEvents = AllBandFriends.fin;
                        return;

                    }
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.clearRemainingOptions();
                    this.BandEvents = AllBandFriends.fin;
                    return;
                case fin:
                    AbstractRelic Gig = new Gig();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), Gig);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    openMap();

        }
    }


    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Present");
        title = eventStrings.NAME;
        OPTIONS = eventStrings.OPTIONS;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}

package ExcessiveFantasy;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.beyond.Deca;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import events.BandFriendsEvent;
import relics.Receipts;
import relics.StarAnonBike;

public class ExcessiveFantasyAwake extends AbstractEvent {
    public static final String ID = "ExcessiveFantasyAwake";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String INTRO_MSG;
    private screen screen = this.screen.INTRO;
    private enum screen{
        INTRO,PRE_COMBAT
    };


    public ExcessiveFantasyAwake() {
//        this.screen = MysteriousSphere.CurScreen.INTRO;
//        this.initializeImage("images/events/sphereClosed.png", 1120.0F * Settings.xScale, AbstractDungeon.floorY - 50.0F * Settings.scale);
        this.body = INTRO_MSG;
        this.roomEventText.addDialogOption(OPTIONS[0]);
//        this.roomEventText.addDialogOption(OPTIONS[1]);
        this.hasDialog = true;
        this.hasFocus = true;
        int a =AbstractDungeon.miscRng.random(0,3);
        switch (a){
            case 0:
                AbstractDungeon.getCurrRoom().monsters =new MonsterGroup(new AbstractMonster[] { new Cultist(-590.0F, 10.0F, false), new Cultist(-298.0F, -10.0F, false), new AwakenedOne(100.0F, 15.0F) });
                break;
            case 1:
                AbstractDungeon.getCurrRoom().monsters = new MonsterGroup(new TimeEater());
                break;
            case 2:
                AbstractDungeon.getCurrRoom().monsters = new MonsterGroup(new AbstractMonster[] { new Deca(), new Donu() });
                break;
            default:
                AbstractDungeon.getCurrRoom().monsters =new MonsterGroup(new AbstractMonster[] { new Cultist(-590.0F, 10.0F, false), new Cultist(-298.0F, -10.0F, false), new AwakenedOne(100.0F, 15.0F) });
                break;
        }
    }
    public void update() {
        super.update();
        if (!RoomEventDialog.waitForInput) {
            this.buttonEffect(this.roomEventText.getSelectedOption());
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch (screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
//                        AbstractDungeon.actionManager.addToTop();
                        AbstractDungeon.effectList.add(new TimepieceTrigger(AbstractDungeon.player, false));
                        for (AbstractRelic Rec : AbstractDungeon.player.relics){
                            if(Rec.relicId.equals(Receipts.ID)){
                                Rec.flash();
                                ((Receipts)Rec).AwakeUp();
                                break;
                            }
                        }
                        this.screen = screen.PRE_COMBAT;
                        this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[1]);
                        this.roomEventText.clearRemainingOptions();
                        logMetric("Mysterious Sphere", "Fight");
                        return;
                    default:
                        return;
                }
            case PRE_COMBAT:
//                if (Settings.isDailyRun) {
//                    AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(50));
//                } else {
//                    AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(45, 55));
//                }
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.RARE));
                if (this.img != null) {
                    this.img.dispose();
                    this.img = null;
                }

                this.img = ImageMaster.loadImage("images/events/sphereOpen.png");
                this.enterCombat();
                AbstractDungeon.lastCombatMetricKey = "2 Orb Walkers";
                break;
//            case END:
//                this.openMap();
        }

    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("ExcessiveFantasyAwake");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        INTRO_MSG = DESCRIPTIONS[0];
    }

    private static enum CurScreen {
        INTRO,
        PRE_COMBAT,
        END;

        private CurScreen() {
        }
    }
}

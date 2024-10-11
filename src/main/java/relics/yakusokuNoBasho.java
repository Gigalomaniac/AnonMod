package relics;

import OnStage.promiseOnStage;
import TheTreeOfQliphoth.TheTreeOfQliphoth;
import actions.movie.StarAnonGuitarAction;
import actions.yakusokuNoBashoAction;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.StartActSubscriber;
import bossRoom.effect.LatterEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import utils.ClickableRelic;


public class yakusokuNoBasho extends ClickableRelic   implements CustomSavable<Integer> {
    public static final String ID = "yakusokuNoBasho";
    private static final String IMG = "img/relics/yakusokuNoBasho.png";
    private static final String IMG_OTL = "img/relics/yakusokuNoBasho.png";

    public static boolean isOn = false;

    public int event = 0;

    public AbstractEvent eventFinal = null;
    public yakusokuNoBasho() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
//        setDescription();
    }



    public AbstractRelic makeCopy() {
        return new yakusokuNoBasho();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onRightClick() {
        if(!usedUp) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.id.equals(TheTreeOfQliphoth.ID)) {
                try {
                    AbstractDungeon.effectList.add(new LatterEffect(() -> {
                        AbstractDungeon.actionManager.addToBottom(new yakusokuNoBashoAction());
                        PositionZero PositionZero = new PositionZero();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) PositionZero);
                    }, 1f));
                } catch (Exception var4) {
                }
            } else {
                CardCrawlGame.nextDungeon = promiseOnStage.ID;
                CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(promiseOnStage.ID);
                AbstractDungeon.actNum = 4;
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    PositionZero PositionZero = new PositionZero();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) PositionZero);
                }, 1f));
            }


            this.usedUp();
            this.setCounter(-2);
        }
    }

    @Override
    public Integer onSave() {
        if(this.usedUp){
            return 1;
        }
        return 0;
    }
    @Override
    public void onLoad(Integer integer) {
        if(integer ==  1){
            this.usedUp();
        }
    }

}

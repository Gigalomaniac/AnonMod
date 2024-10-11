package actions;


import OnStage.promiseOnStage;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import star.BeyondTheStar;

public class yakusokuNoBashoAction extends AbstractGameAction {

    private AbstractCreature c;

    public yakusokuNoBashoAction() {
        this.duration = 1F;
    }


    public void update() {
        CardCrawlGame.nextDungeon =  promiseOnStage.ID;
        CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(promiseOnStage.ID);
        AbstractDungeon.actNum = 4;
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;

        this.isDone = true;
    }
}


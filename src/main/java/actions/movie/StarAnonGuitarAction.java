package actions.movie;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import power.beat;
import power.musicStart;
import power.songs;
import star.BeyondTheStar;
import star.StarAnon.StarAnonPower.StarAnonShining;

public class StarAnonGuitarAction extends AbstractGameAction {

    private AbstractCreature c;

    public StarAnonGuitarAction() {
        this.duration = 1F;
    }


    public void update() {
        CardCrawlGame.nextDungeon =  BeyondTheStar.ID;
        CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(BeyondTheStar.ID);
        AbstractDungeon.actNum = 4;
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;

        this.isDone = true;
    }
}


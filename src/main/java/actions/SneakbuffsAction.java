package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.Shining;
import power.beat;
import power.musicStart;
import power.songs;
import star.StarAnon.StarAnonPower.StarAnonShining;

public class SneakbuffsAction extends AbstractGameAction {

    private AbstractCreature c;

    public SneakbuffsAction(AbstractCreature c) {
        this.c = c;
        this.duration = 0.5F;
    }


    public void update() {
        for (AbstractPower p : AbstractDungeon.player.powers) {

            if (p.type == AbstractPower.PowerType.BUFF && !p.ID.equals(beat.POWER_ID)&& !p.ID.equals(musicStart.POWER_ID)&& !p.ID.equals(songs.POWER_ID)){
                if(p.amount >= 5){
                    addToBot((AbstractGameAction)new ApplyPowerAction(c, c, new StarAnonShining(c,5), 5));
                }else {
                    addToBot((AbstractGameAction)new ApplyPowerAction(c, c, new StarAnonShining(c,1), 1));
                    addToBot((AbstractGameAction)new ApplyPowerAction(c, c, new StarAnonShining(c,p.amount), p.amount));
                }
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player, p.ID));
                break;
            }
        }

        this.isDone = true;
    }
}


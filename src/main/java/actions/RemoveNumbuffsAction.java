package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.musicStart;
import power.songs;

public class RemoveNumbuffsAction extends AbstractGameAction {

    private AbstractCreature c;

    public RemoveNumbuffsAction(AbstractCreature c) {
        this.c = c;
        this.duration = 0.5F;
    }


    public void update() {
        for (AbstractPower p : this.c.powers) {
            if (p.type == AbstractPower.PowerType.BUFF&& !p.ID.equals("beat")&& !p.ID.equals(musicStart.POWER_ID)&& !p.ID.equals(songs.POWER_ID)) {
                    addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.c, this.c, p.ID));
                    break;
            }
        }

        this.isDone = true;
    }
}


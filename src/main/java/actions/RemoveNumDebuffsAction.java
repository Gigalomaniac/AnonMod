package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
public class RemoveNumDebuffsAction extends AbstractGameAction {

    private AbstractCreature c;

    public RemoveNumDebuffsAction(AbstractCreature c) {
        this.c = c;
        this.duration = 0.5F;
    }


    public void update() {
        for (AbstractPower p : this.c.powers) {
            if (p.type == AbstractPower.PowerType.DEBUFF) {
                addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.c, this.c, p.ID));
                break;
            }
        }

        this.isDone = true;
    }
}


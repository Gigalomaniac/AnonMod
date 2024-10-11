package actions.movie;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.StarAnon.Love;
import star.StarAnon.WorldCrown;
import star.StarAnonSide;

import java.util.Iterator;

public class PromiseAction extends AbstractGameAction {


    public PromiseAction() {
        this.duration = 0.5F;
    }


    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster m1 = (AbstractMonster)var3.next();
        while(var3.hasNext() && m1.id.equals(WorldCrown.ID)) {
            System.out.println(m1.id);
            if(m1.id.equals(StarAnonSide.id) ){
                addToBot((AbstractGameAction)new ApplyPowerAction(m1, m1, (AbstractPower)new Love(m1), 10));
            }
        }

        this.isDone = true;
    }
}


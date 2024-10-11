package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SkipEnemiesTurnFalseAction extends AbstractGameAction {
    public SkipEnemiesTurnFalseAction() {
    }

    public void update() {
        AbstractDungeon.getCurrRoom().skipMonsterTurn = false;
        this.isDone = true;
    }
}

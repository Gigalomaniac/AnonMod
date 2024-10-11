package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.DeathScreen;

import static star.RestartRunHelper.restartRun;

public class ActionManagerClearAction  extends AbstractGameAction{

    public ActionManagerClearAction() {

    }

    public void update() {
        AbstractDungeon.actionManager.clear();
        this.isDone = true;
    }
}

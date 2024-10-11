package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.DamageInfo;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.DeathScreen;

import static star.RestartRunHelper.restartRun;

public class RestartAction extends AbstractGameAction {


    public RestartAction() {

    }

    public void update() {
        AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
        restartRun();
        this.isDone = true;
    }
}

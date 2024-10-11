package actions;

import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import monster.act1.GBC486;


import java.util.Iterator;

public class SummonGBCFriends486Action extends AbstractGameAction {
    public SummonGBCFriends486Action() {
        this.s486 = new GBC486(250,-50);
        this.actionType = ActionType.SPECIAL;
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FAST;
        } else {
            this.startDuration = Settings.ACTION_DUR_LONG;
        }

        this.duration = this.startDuration;

        Iterator var3 = AbstractDungeon.player.relics.iterator();
//
        while(var3.hasNext()) {
            AbstractRelic r = (AbstractRelic)var3.next();
            r.onSpawnMonster(this.s486);
        }
    }




    private AbstractMonster s486;
    @Override
    public void update() {

        if (this.duration == this.startDuration) {
            this.s486.animX = 1200.0F * Settings.xScale;
            this.s486.init();
            this.s486.applyPowers();
            AbstractDungeon.getCurrRoom().monsters.addMonster(this.getSmartPosition(), this.s486);
//            this.addToBot(new ApplyPowerAction(this.m, this.m, new MinionPower(this.m)));
        }
        this.tickDuration();
        if (this.isDone) {
            this.s486.animX = 0.0F;
            this.s486.showHealthBar();
            this.s486.usePreBattleAction();
        } else {
            this.s486.animX = Interpolation.fade.apply(0.0F, 1200.0F * Settings.xScale, this.duration);
        }
    }

    private int getSmartPosition() {
        int position = 0;

        for(Iterator var2 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator(); var2.hasNext(); ++position) {
            AbstractMonster mo = (AbstractMonster)var2.next();

        }

        return position;
    }
}

package actions;

import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import monster.act1.GBCmmk;

import java.util.Iterator;

public class SummonGBCFriendsmmkAction extends AbstractGameAction {
    public SummonGBCFriendsmmkAction() {
        this.mmk = new GBCmmk(-250,-50);
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
            r.onSpawnMonster(this.mmk);
        }
    }


    private AbstractMonster mmk;


    @Override
    public void update() {

        if (this.duration == this.startDuration) {
            this.mmk.animX = 1200.0F * Settings.xScale;
            this.mmk.init();
            this.mmk.applyPowers();
            AbstractDungeon.getCurrRoom().monsters.addMonster(this.getSmartPosition(), this.mmk);

//            this.addToBot(new ApplyPowerAction(this.m, this.m, new MinionPower(this.m)));
        }
        this.tickDuration();
        if (this.isDone) {
            this.mmk.animX = 0.0F;
            this.mmk.showHealthBar();
            this.mmk.usePreBattleAction();
        } else {
            this.mmk.animX = Interpolation.fade.apply(0.0F, 1200.0F * Settings.xScale, this.duration);

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

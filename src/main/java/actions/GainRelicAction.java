package actions;


import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GainRelicAction extends AbstractGameAction {

    private AbstractRelic relic;

    private int count;

    public GainRelicAction(AbstractRelic relic, Integer integer) {
        this.relic = relic;
        this.count = integer;
    }


    public void update() {

            final float START_X = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_X");
            final float START_Y = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_Y");

            relic.isDone = true;
            relic.isObtained = true;
            relic.currentX = START_X + AbstractDungeon.player.relics.size() * AbstractRelic.PAD_X;
            relic.currentY = START_Y;
            relic.targetX = relic.currentX;
            relic.targetY = relic.currentY;
            relic.hb.move(relic.currentX, relic.currentY);
            relic.counter = this.count;
            AbstractDungeon.player.relics.add(relic);


        this.isDone = true;
    }
}


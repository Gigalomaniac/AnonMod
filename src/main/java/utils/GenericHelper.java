package utils;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class GenericHelper {

    public static void MoveMonster(AbstractMonster m, float x, float y) {
        m.drawX = x;
        m.drawY = y;
        m.animX = 0.0F;
        m.animY = 0.0F;
        m.hb.move(m.drawX + m.hb_x, m.drawY + m.hb_y + m.hb_h / 2.0F);
        m.healthHb.move(m.hb.cX, m.hb.cY - m.hb_h / 2.0F - m.healthHb.height / 2.0F);
        m.refreshIntentHbLocation();
    }

    public static void addEffect(AbstractGameEffect effect) {
        AbstractDungeon.effectList.add(effect);
    }


    public static void addToBotAbstract(final VoidSupplier func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                func.get();
                this.isDone = true;
            }
        });
    }

    public static interface VoidSupplier {
        void get();
    }
}

package bossRoom;

import basemod.abstracts.CustomMonster;

public abstract class AbstractSpriterMonster extends CustomMonster {
    public AbstractSpriterMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY);
    }

    public void die(boolean triggerRelics) {
        this.useShakeAnimation(5.0F);
//        ((BetterSpriterAnimation)this.animation).startDying();
        super.die(triggerRelics);
    }
}

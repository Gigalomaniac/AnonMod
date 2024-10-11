package bossRoom.huijinaiyin.effect;

import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class InnerFavillaeLightEffect extends AbstractGameEffect {
    private float timer = 0.1F;

    public InnerFavillaeLightEffect() {
        this.duration = 100000.0F;
    }

    public void update() {
        if ( this.duration == 100000.0F) {
            AbstractDungeon.effectsQueue.add(new huijinLightEffect());
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer -= Gdx.graphics.getDeltaTime();
//        if (this.timer < 0.0F) {
//            this.timer += 0.1F;
//            AbstractDungeon.effectsQueue.add(new PetalEffect());
//            AbstractDungeon.effectsQueue.add(new PetalEffect());
//        }
//
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }

    private static boolean isInteger(Float f) {
        String str = f.toString();


        //println("str.contains(\".\") ---> " + str.contains("."));
        String[] array = str.split("\\.");

        String decimalsStr = array[1];

        Integer i = Integer.parseInt(decimalsStr);
        boolean isInteger = (i == 0);

        return isInteger;
    }
}

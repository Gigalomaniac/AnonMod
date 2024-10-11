package vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TimeLateEffect extends AbstractGameEffect {
    private float timer;
    public TimeLateEffect(float time) {
        this.timer = time;
        this.duration = 0;
    }
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if(timer+this.duration>0){
            this.isDone = true;
        }
    }
    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}

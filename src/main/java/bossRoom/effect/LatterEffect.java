package bossRoom.effect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
public class LatterEffect
        extends AbstractGameEffect {
    private Runnable action;

    public LatterEffect(Runnable action) { this(action, 0.0F); }

    public LatterEffect(Runnable action, float lateTime) {
        this.action = action;
        this.duration = lateTime;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0.0F) {
            this.isDone = true;
            this.action.run();
        }
    }

    public void render(SpriteBatch sb) {}

    public void dispose() {}
}
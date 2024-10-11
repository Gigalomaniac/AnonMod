package bossRoom.effect;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class LatterAction
        extends AbstractGameAction
{
    private Runnable action;

    public LatterAction(Runnable action) { this(action, 0.0F); }

    public LatterAction(Runnable action, float lateTime) {
        this.action = action;
        this.duration = lateTime;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0.0F) {
            if (this.action != null) {
                this.action.run();
            }
            this.isDone = true;
        }
    }
}
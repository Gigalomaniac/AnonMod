package bossRoom.effect;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AbstractShaderEffect
        extends AbstractGameEffect
{
    public void end() { this.isDone = true; }

    public void render(SpriteBatch spriteBatch) {}

    public void dispose() {}
}
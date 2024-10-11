package relics.test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

class overlayScreen extends AbstractGameEffect {
//    public class FlameBarrierEffect extends AbstractGameEffect {
        private float x;
        private float y;

        private ShaderProgram shader;

    public overlayScreen(ShaderProgram shader) {
        this.shader = shader;
    }


    public void update() {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }


}


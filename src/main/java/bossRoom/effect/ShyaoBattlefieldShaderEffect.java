package bossRoom.effect;

import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import patch.ScreenFilterPatches;

public class ShyaoBattlefieldShaderEffect
        extends AbstractShaderEffect
{
    private ScreenPostProcessor processor;

    public void update() {
        if (this.duration == 2.57F) {
            this.processor = new BattlefieldPostProcessor();
            ScreenFilterPatches.addPostProcessor(this.processor);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
    }

    public void end() {
        ScreenFilterPatches.removePostProcessor(this.processor);
        this.isDone = true;
    }

    public void render(SpriteBatch spriteBatch) {}

    public void dispose() {}

    public static class BattlefieldPostProcessor
            implements ScreenPostProcessor
    {
        private static final ShaderProgram shader = new ShaderProgram(Gdx.files.internal("shaders/storm/rain/vertex.vs"), Gdx.files.internal("shaders/storm/rain/fragment.fs"));
        private float u_time = 0.0F;

        static  {
            if (!shader.isCompiled()) {
                throw new RuntimeException(shader.getLog());
            }
        }

        public void postProcess(SpriteBatch sb, TextureRegion frameTexture, OrthographicCamera camera) {
            sb.end();
            sb.setShader(shader);
            sb.begin();
            this.u_time += Gdx.graphics.getDeltaTime();
            shader.setUniform1fv("u_time", new float[] { this.u_time }, 0, 1);
            sb.setColor(Color.WHITE);
            sb.draw(frameTexture, 0.0F, 0.0F);
            ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
        }
    }
}
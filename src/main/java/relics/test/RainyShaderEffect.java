package relics.test;

import bossRoom.effect.AbstractChangeSceneEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class RainyShaderEffect extends AbstractChangeSceneEffect {

    private ShaderProgram rainyShader;

    public RainyShaderEffect() {
        this.color = Color.WHITE.cpy();
        this.renderBehind = true;
//        this.img = img;
//        this.img2 = ImageMaster.loadImage("img/vfx/Mask.png");
//        this.img3 = ImageMaster.loadImage("img/vfx/Mask2.png");
//        this.x = -Settings.WIDTH * 1.7F;
        this.timer = 0.0F;
        initializeRainyShader();
    }
    private float timer;
    private void initializeRainyShader() {
        // 加载着色器文件
        rainyShader = new ShaderProgram(
                Gdx.files.internal("shaders/rainy/vertexShader.vert").readString(),
                Gdx.files.internal("shaders/rainy/fragmentShader.frag").readString()
        );

        if (!rainyShader.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + rainyShader.getLog());
        }
    }

    @Override
    public void update(float deltaTime) {
        // 实现抽象方法 update
        // 这里可以添加效果的具体逻辑
        // 例如，更新效果的状态或生命周期
    }

    @Override
    public void render(SpriteBatch sb) {
        // 在渲染时应用着色器
        sb.setShader(rainyShader);

        // 这里可以添加渲染逻辑
        // 例如，渲染背景或特效

        // 渲染完成后恢复默认着色器
        sb.setShader(null);
    }


    @Override
    public void dispose() {
        // 清理着色器资源
        rainyShader.dispose();
    }
}
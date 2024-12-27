package vfx;

import bossRoom.effect.AbstractChangeSceneEffect;
import bossRoom.effect.AbstractShaderEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.lwjgl.Sys;

import static com.badlogic.gdx.math.MathUtils.random;

public class piwa extends AbstractGameEffect {

        private Texture img;

        public float x;
        private boolean right = true;
        public Color color;
        private AbstractShaderEffect effect;
        private boolean useShader;
    int num = 0;
    float time = 0;
    private String[] imgList = {"img/vfx/ros/piwa/frame0.png",
            "img/vfx/ros/piwa/frame1.png",
            "img/vfx/ros/piwa/frame2.png",
            "img/vfx/ros/piwa/frame3.png",
            "img/vfx/ros/piwa/frame4.png",
            "img/vfx/ros/piwa/frame5.png",
            "img/vfx/ros/piwa/frame6.png",
            "img/vfx/ros/piwa/frame7.png",
            "img/vfx/ros/piwa/frame8.png",
            "img/vfx/ros/piwa/frame9.png",
    };
    public piwa() {
        this.color = Color.WHITE.cpy();
        this.renderBehind = false;
        this.img = ImageMaster.loadImage(imgList[0]);
        this.x = -Settings.WIDTH * 1.7F;
        this.time = 0;
    }

        public void setShader(AbstractShaderEffect effect) {
        this.effect = effect;
        this.useShader = true;
    }

        public void update() {
            this.time += Gdx.graphics.getDeltaTime();
            if(this.time <=0.9){
                num = (int) (this.time * 10);
                this.img = ImageMaster.loadImage(imgList[num]);
            }else {
                this.isDone = true;
            }

    }

    public void render(SpriteBatch sb) {
        sb.flush();
        sb.setColor(Color.WHITE.cpy());
        sb.draw(this.img, 0, 0, Settings.WIDTH,Settings.HEIGHT);
    }

        public void dispose() {}
    }

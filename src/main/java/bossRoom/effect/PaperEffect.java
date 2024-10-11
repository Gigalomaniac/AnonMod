package bossRoom.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
//import utils.LOREXImageMaster;

public class PaperEffect
        extends AbstractGameEffect {
    private Texture img;
    private Texture img3;
    private float x;
    private float y;
    private float width;
    private float height;
    private float vY;
    private float vX;
    private float vR;
    private boolean flipX;
    private boolean flipY;
    private float maxScale;
    private Color color2;
    private Color color3;

    public PaperEffect(float x, float y) {
        this.x = x;
        this.y = y;
        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.maxScale = MathUtils.random(2.2F, 2.8F);
        this.width = MathUtils.random(0.0F, 128.0F) * Settings.scale;
        this.height = MathUtils.random(0.0F, 128.0F) * Settings.scale;
        this.vY = MathUtils.random(-128.0F, 128.0F) * Settings.scale;
        this.vX = MathUtils.random(-128.0F, 128.0F) * Settings.scale;
        this.vR = MathUtils.random(-30.0F, 30.0F);
        this.scale = 0.0F;
        this.maxScale *= Settings.scale;
        this.color = new Color(1.0F, 0.98039216F, 0.627451F, 1.0F);
        this.color2 = this.color.cpy();
        this.color2.a = 0.2F;
        this.color3 = this.color.cpy();
        this.color3.a = 0.0F;
        this.color.a = 0.6F;
        this.duration = MathUtils.random(1.8F, 2.4F);
        this.startingDuration = this.duration;
        this.duration = 0.0F;
        this.flipX = MathUtils.randomBoolean();
        this.flipY = MathUtils.randomBoolean();

//        LOREXImageMaster.PAPER[0] = ImageMaster.loadImage("img/vfx/light.png");

        this.img = ImageMaster.loadImage("img/vfx/light.png");

//            LOREXImageMaster.PAPER_GLOW[0] = ImageMaster.loadImage("img/vfx/smalllight.png");

        this.img3 = ImageMaster.loadImage("img/vfx/smalllight.png");
    }

    public void update() {
        if (this.width > 128.0F) {
            this.width = 256.0F - this.width;
            this.flipX = !this.flipX;
        }
        else if (this.width < 0.0F) {
            this.width = -this.width;
            this.flipX = !this.flipX;
        }
        if (this.height > 128.0F) {
            this.height = 256.0F - this.height;
            this.flipY = !this.flipY;
        }
        else if (this.height < 0.0F) {
            this.height = -this.height;
            this.flipY = !this.flipY;
        }

        if (this.flipX) {
            this.width += this.vX * Gdx.graphics.getDeltaTime();
        } else {

            this.width -= this.vX * Gdx.graphics.getDeltaTime();
        }
        if (this.flipY) {
            this.height += this.vY * Gdx.graphics.getDeltaTime();
        } else {

            this.height -= this.vY * Gdx.graphics.getDeltaTime();
        }
        this.rotation += this.vR * Gdx.graphics.getDeltaTime();

        this.duration += Gdx.graphics.getDeltaTime();
        if (this.duration < this.startingDuration / 3.0F) {
            this.scale = this.duration / this.startingDuration / 3.0F * this.maxScale;
            this.color2.a = this.duration / this.startingDuration / 3.0F * 0.4F + 0.2F;
            this.color3.a = this.duration / this.startingDuration / 3.0F * 0.4F;
        }
        else if (this.duration > this.startingDuration / 3.0F * 2.0F && this.duration < this.startingDuration) {
            this.scale = (this.startingDuration - this.duration) / this.startingDuration / 3.0F * this.maxScale;
            this.color2.a = (this.startingDuration - this.duration) / this.startingDuration / 3.0F * 0.4F + 0.2F;
            this.color3.a = (this.startingDuration - this.duration) / this.startingDuration / 3.0F * 0.4F;
        } else {

            this.scale = this.maxScale;
            this.color2.a = 0.6F;
            this.color3.a = 0.4F;
        }
        if (this.duration > this.startingDuration) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
//        sb.setColor(this.color);
        sb.draw(this.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, this.width, this.height, this.scale, this.scale, this.rotation, 0, 0, 128, 128, this.flipX, this.flipY);

        sb.setBlendFunction(770, 1);
        sb.setColor(this.color2);
        sb.draw(this.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, this.width, this.height, this.scale, this.scale, this.rotation, 0, 0, 128, 128, this.flipX, this.flipY);

        sb.setColor(this.color3);
        sb.draw(this.img3, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, this.width, this.height, this.scale, this.scale, this.rotation, 0, 0, 128, 128, this.flipX, this.flipY);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
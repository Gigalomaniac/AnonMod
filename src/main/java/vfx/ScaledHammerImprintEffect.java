package vfx;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
public class ScaledHammerImprintEffect
        extends AbstractGameEffect
{
    private TextureAtlas.AtlasRegion img;
    private TextureAtlas.AtlasRegion img2;
    private static final float DUR = 0.7F;
    private float x;
    private float y;
    private float hammerGlowScale;
    private Color shineColor;
    protected float rotation2;
    public ScaledHammerImprintEffect(float x, float y, float scale) {
        String imagePath = "img/othervfx/gaiz.png";
        String imagePath2 = "img/othervfx/gaizshort.png";
        Texture texture = ImageMaster.loadImage(imagePath);
        Texture texture2 = ImageMaster.loadImage(imagePath2);
        this.img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        this.img2 = new TextureAtlas.AtlasRegion(texture2, 0, 0, texture2.getWidth(), texture2.getHeight());
        this.shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        this.x = x - (this.img.packedWidth / 2);
        this.y = y - (this.img.packedHeight / 2);
        this.color = Color.WHITE.cpy();
        this.color.a = 1.0F;
        this.duration = 0.7F;
        this.scale = scale * Settings.scale / MathUtils.random(1.0F, 1.5F);
        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.rotation2 = MathUtils.random(0.0F, 360.0F);
        this.hammerGlowScale = 1.0F - this.duration;
        this.hammerGlowScale *= this.hammerGlowScale;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        this.color.a = this.duration+0.3f;
        this.hammerGlowScale = 1.7F - this.duration;
        this.hammerGlowScale *= this.hammerGlowScale * this.hammerGlowScale;
        this.scale += Gdx.graphics.getDeltaTime() / 20.0F * this.scale;
    }

//    public void render(SpriteBatch sb) {
//        sb.setBlendFunction(770, 1);
//        sb.setColor(this.color);
//        sb.draw(this.img, this.x- this.img.packedWidth / 15.0F + MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.y-this.img.packedHeight / 4.0F + MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
//        sb.draw(this.img2, this.x- this.img.packedWidth / 15.0F + MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.y-this.img.packedHeight / 4.0F + MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation2);
////        this.color.a /= 10.0F;
//        sb.setColor(this.shineColor);
////        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.hammerGlowScale, this.hammerGlowScale, this.rotation);
//        sb.setBlendFunction(770, 771);
//    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 771); // 使用标准透明混合模式
        sb.setColor(this.color);
        sb.draw(this.img, this.x - this.img.packedWidth / 15.0F, this.y - this.img.packedHeight / 4.0F, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.draw(this.img2, this.x - this.img.packedWidth / 15.0F, this.y - this.img.packedHeight / 4.0F, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation2);
        sb.setColor(this.shineColor);
        // sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.hammerGlowScale, this.hammerGlowScale, this.rotation);
    }

    public void dispose() {}
}
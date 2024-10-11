package monster.ShoujoKageki;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import utils.TextureLoader;

public class LastWordTEffect extends AbstractGameEffect {

    private static Texture[] IMGS = new Texture[]{
            TextureLoader.getTexture("img/char/karen/T01.png"),
            TextureLoader.getTexture("img/char/karen/T02.png"),
            TextureLoader.getTexture("img/char/karen/T03.png"),
            TextureLoader.getTexture("img/char/karen/T04.png"),
    };


    private float x;
    private float y;
    private float vY;
    private float vX;
    private float scaleY;
    private int frame = 0;
    private float animTimer = 0.05F;
    private static final int W = 32;
    private int selectImg;
    private float rotationSpeed;

    public LastWordTEffect() {
        this.x = MathUtils.random(100.0F * Settings.scale, 1820.0F * Settings.scale);
        this.y = (float) Settings.HEIGHT + MathUtils.random(20.0F, 300.0F) * Settings.scale;
        this.frame = MathUtils.random(8);
        this.rotation = MathUtils.random(-10.0F, 10.0F);
        this.scale = MathUtils.random(1.0F, 2.5F);
        this.scaleY = MathUtils.random(1.0F, 1.2F);
        if (this.scale < 1.5F) {
            this.renderBehind = true;
        }
        this.selectImg = MathUtils.random(0, IMGS.length - 1);
        this.rotationSpeed = MathUtils.random(10f, 50f);

        this.vY = MathUtils.random(200.0F, 300.0F) * this.scale * Settings.scale;
        this.vX = MathUtils.random(-100.0F, 100.0F) * this.scale * Settings.scale;
        this.scale *= Settings.scale;
        if (MathUtils.randomBoolean()) {
            this.rotation += 180.0F;
        }

//        this.color = new Color(1.0F, MathUtils.random(0.7F, 0.9F), MathUtils.random(0.7F, 0.9F), 1.0F);251f, 84f, 88.0f
        this.color = CardHelper.getColor(251, 84, 88);
        this.color.a = 1f;
        this.duration = 4.0F;
    }

    public void update() {
        this.y -= this.vY * Gdx.graphics.getDeltaTime();
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.animTimer -= Gdx.graphics.getDeltaTime() / this.scale;
        if (this.animTimer < 0.0F) {
            this.animTimer += 0.05F;
            ++this.frame;
            if (this.frame > 11) {
                this.frame = 0;
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else if (this.duration < 1.0F) {
            this.color.a = this.duration;
        }

        this.rotation += this.rotationSpeed * Gdx.graphics.getDeltaTime();

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        this.renderImg(sb, IMGS[selectImg], false, false);
    }

    public void dispose() {
    }

    private void renderImg(SpriteBatch sb, Texture img, boolean flipH, boolean flipV) {
        sb.setBlendFunction(770, 1);
        sb.draw(img, this.x, this.y, 16.0F, 16.0F, 32.0F, 32.0F, this.scale, this.scale * this.scaleY, this.rotation, 0, 0, 32, 32, flipH, flipV);
        sb.setBlendFunction(770, 771);
    }
}
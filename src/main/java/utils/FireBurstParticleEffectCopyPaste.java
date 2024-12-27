package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FireBurstParticleEffectCopyPaste extends AbstractGameEffect {
    private Texture img;
    private static final float DUR = 1.0F;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floor;
    private float GRAVITY;
    private static Texture GhostFireTexture = new Texture(("img/UI/reward/MusicEffect.png"));
    private static Texture GhostFireTexture2 = new Texture(("img/UI/reward/MusicEffect2.png"));
    private static Texture GhostFireTexture3 = new Texture(("img/UI/reward/MusicEffect3.png"));
    public FireBurstParticleEffectCopyPaste(Color color, float x, float y) {
        this(color, x, y, 180.0F * Settings.scale);
    }
    public FireBurstParticleEffectCopyPaste(Color color, float x, float y, float gravity) {
        int roll = MathUtils.random(0, 2);
        if (roll == 0) {
            this.img = GhostFireTexture;
        } else if (roll == 1) {
            this.img = GhostFireTexture2;
        } else {
            this.img = GhostFireTexture3;
        }

        this.GRAVITY = gravity;

        this.duration = MathUtils.random(0.5F, 1.0F);
        this.x = x - (float)(this.img.getWidth() / 2);
        this.y = y - (float)(this.img.getHeight() / 2);
        this.color = color;
        this.color.a = 0.0F;
        this.rotation = MathUtils.random(-10.0F, 10.0F);
        this.scale = MathUtils.random(2.0F, 4.0F) * Settings.scale;
        this.vX = MathUtils.random(-900.0F, 900.0F) * Settings.scale;
        this.vY = MathUtils.random(0.0F, 500.0F) * Settings.scale;
        this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
    }

    public void update() {
        this.vY += GRAVITY / this.scale * Gdx.graphics.getDeltaTime()/12;
        this.x += this.vX * Gdx.graphics.getDeltaTime()/12 * MathUtils.sinDeg(Gdx.graphics.getDeltaTime());
        this.y += this.vY * Gdx.graphics.getDeltaTime()/5;
        if (this.scale > 0.3F * Settings.scale) {
            this.scale -= Gdx.graphics.getDeltaTime() * 2.0F;
        }
        if (this.y < this.floor) {
            this.vY = -this.vY * 0.75F;
            this.y = this.floor + 0.1F;
            this.vX *= 1.1F;
        }

        if (1.0F - this.duration < 0.1F) {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
//        sb.draw(this.img, this.x, this.y, (float)this.img.getWidth() / 2.0F, (float)this.img.getHeight() / 2.0F, (float)this.img.getWidth(), (float)this.img.getHeight(), this.scale, this.scale, this.rotation);
        sb.draw(this.img, this.x, this.y, this.img.getWidth(), this.img.getHeight());
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}

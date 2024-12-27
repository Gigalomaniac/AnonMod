package monster.mika;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.AdditiveSlashImpactEffect;

public class MikaShotParticleEffect  extends AbstractGameEffect {
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float x;
    private float y;
    private float vY;
    private float vX;
    private TextureAtlas.AtlasRegion img;
    private boolean activated = false;

    public MikaShotParticleEffect(float sX, float sY, float tX, float tY) {
        this.img = ImageMaster.GLOW_SPARK_2;
        this.sX = sX + MathUtils.random(-90.0F, 90.0F) * Settings.scale;
        this.sY = sY + MathUtils.random(-90.0F, 90.0F) * Settings.scale;
        this.tX = tX + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
        this.tY = tY + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
        this.vX = this.sX + MathUtils.random(-200.0F, 200.0F) * Settings.scale;
        this.vY = this.sY + MathUtils.random(-200.0F, 200.0F) * Settings.scale;
        this.x = this.sX;
        this.y = this.sY;
        this.scale = 0.01F;
        this.startingDuration = 0.8F;
        this.duration = this.startingDuration;
        this.renderBehind = MathUtils.randomBoolean(0.2F);
        this.color = new Color(1.0F, 0.1F, MathUtils.random(0.2F, 0.5F), 1.0F);
    }

    public void update() {
        if (this.duration > this.startingDuration / 2.0F) {
            this.scale = Interpolation.pow3In.apply(2.5F, this.startingDuration / 2.0F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F)) * Settings.scale;
            this.x = Interpolation.swingIn.apply(this.sX, this.vX, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
            this.y = Interpolation.swingIn.apply(this.sY, this.vY, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
        } else {
            this.scale = Interpolation.pow3Out.apply(2.0F, 2.5F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale;
            this.x = Interpolation.swingOut.apply(this.tX, this.vX, this.duration / (this.startingDuration / 2.0F));
            this.y = Interpolation.swingOut.apply(this.tY, this.vY, this.duration / (this.startingDuration / 2.0F));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < this.startingDuration / 2.0F && !this.activated) {
            this.activated = true;
            this.sX = this.x;
            this.sY = this.y;
        }

        if (this.duration < 0.0F) {
            AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(this.tX, this.tY, this.color.cpy()));
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, MathUtils.randomBoolean());
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.argb8888(0.5f,255,192,203));
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 2.0F, this.scale * 2.0F, this.rotation);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
    }

    public void dispose() {
    }
}

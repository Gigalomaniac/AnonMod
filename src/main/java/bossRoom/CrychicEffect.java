package bossRoom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.SpotlightEffect;

public class CrychicEffect  extends AbstractGameEffect {
    public CrychicEffect() {
        this.startingDuration = 5.0F;
        this.duration = this.startingDuration;
        this.color = new Color(0.9F, 0.7F, 1.0F, 0.0F);
        this.renderBehind = true;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);
        }

        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.bounceIn.apply(1.0F, 0.0F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
        } else {
            this.color.a = Interpolation.bounceOut.apply(this.duration * (this.startingDuration / 2.0F));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a * 0.8F));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float)Settings.HEIGHT);
        sb.setColor(this.color);
//        sb.draw(ImageMaster.BORDER_GLOW_2, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }

    public void dispose() {
    }
}

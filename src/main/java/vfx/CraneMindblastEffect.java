//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class CraneMindblastEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final float DUR = 1.0F;
    private static TextureAtlas.AtlasRegion img;
    private boolean playedSfx = false;
    private boolean flipHorizontal = false;
    private double angle;
    private double a = AbstractDungeon.player.dialogX;
    private  double b= AbstractDungeon.player.dialogY;
    public CraneMindblastEffect(float x, float y, boolean flipHorizontal) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
        }

        this.flipHorizontal = flipHorizontal;
        this.x = x;
        this.y = y;
        this.color = Color.SKY.cpy();
        this.duration = 1.0F;
        this.startingDuration = 1.0F;
         angle = calculateAngle(a, b, x, y);
//         System.out.println(angle);
    }

    public void update() {
        if (!this.playedSfx) {
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
            this.playedSfx = true;
            CardCrawlGame.sound.play("ATTACK_MAGIC_BEAM_SHORT");
            CardCrawlGame.screenShake.rumble(2.0F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration - 0.5F);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(0.5F, 0.7F, 1.0F, this.color.a));

        sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + MathUtils.random(-0.1F, 0.1F), 180+MathUtils.random((float) (angle-5f), (float) (angle+5f)));
        sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + MathUtils.random(-0.1F, 0.1F),  180+MathUtils.random((float) (angle-5f), (float) (angle+5f)));
            sb.setColor(this.color);
            sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F, this.scale / 2.0F,  180+MathUtils.random((float) (angle-5f), (float) (angle+5f)));
            sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F, this.scale / 2.0F,  180+MathUtils.random((float) (angle-5f), (float) (angle+5f)));


        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }

    public static double calculateAngle(double a, double b, double x, double y) {
        // 计算方向向量
        double deltaX = x - a;
        double deltaY = y - b;

        // 计算角度
        double angleRadians = Math.atan2(deltaY, deltaX);
        double angleDegrees = Math.toDegrees(angleRadians);

        // 确保角度在 [0, 360) 的范围内
        if (angleDegrees < 0) {
            angleDegrees += 360;
        }

        return angleDegrees;
    }

}

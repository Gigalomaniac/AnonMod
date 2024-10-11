package monster.ShoujoKageki;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
public class ShiningRainEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floorY;
    private Texture img;
    private float randomXOffset;
    private float a = AbstractDungeon.player.dialogX;
    private float b =AbstractDungeon.player.dialogY-100;
    private Color color = new Color(0.9F, 0.9F, 1.0F, MathUtils.random(0.9F, 1.0F));

    public ShiningRainEffect(AbstractCreature target) {
        initializeEffect(target.hb_w / 2.0F, target.hb_w / 2.0F);
    }

    private void initializeEffect(float xleftoffset, float xrightoffset) {
        float xleftlimit = Settings.WIDTH - xrightoffset;
        float xrightlimit = Settings.WIDTH - xleftoffset;
        this.x = (float)MathUtils.random.nextInt((int)(xrightlimit - xleftlimit + 1.0F)) + xleftlimit;
        this.y = (float)Settings.HEIGHT;
        this.rotation = 45F;
        this.randomXOffset = MathUtils.random(-400.0F, 400.0F); // Add random horizontal offset
        this.x += this.randomXOffset; // Adjust target x position with the same offset
        this.a += this.randomXOffset;
        calculateVelocity();
    }

    private void calculateVelocity() {
        this.img = ImageMaster.loadImage("img/newbuff/shinePower32.png");
        final float TIME_TO_TARGET = 1.5f; // Time to reach the target point
        float dx = a - x ;
        float dy = b - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float speed = distance / TIME_TO_TARGET;
        float randomspeed = MathUtils.random(1.0F, 7.0F);
        this.vX = speed * (dx / distance) * Settings.scale *randomspeed;
        this.vY = speed * (dy / distance) * Settings.scale*randomspeed;
    }



    @Override
    public void update() {
//        super.update();
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();

        if (this.y < this.floorY) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.getWidth()/2F, this.img.getHeight()/2F, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        sb.setBlendFunction(770, 771);
    }

    public void setscale(float scale) {
        this.scale = scale;
    }

    public void dispose() {
        // Dispose of the texture
        this.img.dispose();
    }

}

package bossRoom.effect;

import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
public class CGEffect  extends AbstractGameEffect {
    private Texture img;
    private Texture img2;
//    private int code;
    private boolean sound;

    public CGEffect(String imgUrl) {
        this.img = ImageMaster.loadImage(imgUrl);
//        this.img2 = ImageMaster.loadImage("lorex/images/vfx/white_night/ClockArrow.png");
        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
        this.duration = 3.0F;
//        this.code = code;
//        this.rotation = 30.0F * code - 30.0F;
        this.sound = true;
    }

    public void update() {
//        if (this.duration == 3.0F) {
//            CardCrawlGame.sound.play("WhiteNight_Clock_Bell");
//        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 2.5F) {
            this.color.a = (3.0F - this.duration) * 2.0F;
        } else {

            this.color.a = 1.0F;
        }

//        if (this.duration > 1.5F && this.duration < 2.0F) {
//            if (this.sound) {
//                this.sound = false;
//                CardCrawlGame.sound.play("WhiteNight_Clock_Tick");
//            }
//            if (this.rotation < 30.0F * this.code) {
//                this.rotation += (2.0F - this.duration) * 60.0F;
//            } else {
//
//                this.rotation = 30.0F * this.code;
//            }

//        }
//        else if (this.duration < 1.5F) {
//            this.rotation = 30.0F * this.code;
//        }

        if (this.duration < 1.0F) {
            this.color.a = this.duration;
        }
        if (this.color.a < 0.0F) {
            this.color.a = 0.0F;
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);

//        sb.draw(this.img2, (960 * Settings.WIDTH) / 1920.0F - 960.0F, (540 * Settings.WIDTH) / 1920.0F - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.WIDTH / 1920.0F, Settings.HEIGHT / 1080.0F, -this.rotation, 0, 0, 1920, 1080, false, false);
    }

    public void dispose() {}
}


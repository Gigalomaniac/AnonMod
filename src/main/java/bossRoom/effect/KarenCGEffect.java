package bossRoom.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class KarenCGEffect extends AbstractGameEffect {
    private Texture img;
//    private int code;
    private boolean sound;

    private float offsetX;

    private float scaledWidth;
    private Texture img2 = ImageMaster.loadImage("img/char/karen/KarenCG2.png");
    public KarenCGEffect() {
        this.img = ImageMaster.loadImage("img/char/karen/KarenCG1.png");

        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
        this.duration = 3.0F;
        this.sound = true;
        float originalWidth = img.getWidth();
        float originalHeight = img.getHeight();
        float scaleY = Settings.HEIGHT / (originalHeight );

        // 使用 y 轴方向上的缩放比例计算 x 轴方向上的宽度
         scaledWidth = originalWidth * scaleY;
System.out.println("scaledWidth"+scaledWidth);
        // 居中绘制所需的 x 坐标偏移量
         offsetX = (Settings.WIDTH - scaledWidth) / 2f;

        // 居中绘制所需的 y 坐标偏移量
        float offsetY = (Settings.HEIGHT - (originalHeight * scaleY)) / 2f;
    }

    public void update() {
//        if (this.duration == 3.0F) {
//            CardCrawlGame.sound.play("WhiteNight_Clock_Bell");
//        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if(this.duration > 1.5F) {
            if (this.duration > 2.5F) {
                this.color.a = (3.0F - this.duration) * 2.0F;
            } else {
                this.color.a = 1.0F;
            }
        }else {
            this.img = img2;
            if (this.duration > 0.5F){
                this.color.a = 1.0F;
            }else {
                this.color.a = (this.duration) * 2.0F;
            }
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
        sb.draw(this.img, offsetX, 0.0F, scaledWidth, Settings.HEIGHT);

    }

    public void dispose() {}
}


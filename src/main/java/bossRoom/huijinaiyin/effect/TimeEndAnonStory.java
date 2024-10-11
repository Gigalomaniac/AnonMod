package bossRoom.huijinaiyin.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TimeEndAnonStory extends AbstractGameEffect {
    private GlyphLayout gl = new GlyphLayout();
//    private String word;
    private float x;
    private float y;
    private Texture img;
    private int code;
    protected Color color2;
    public TimeEndAnonStory() {
        AbstractDungeon.isScreenUp = true;

        this.duration = 12.0F;
        this.startingDuration = this.duration;
//        this.word = "启程吧，前往这个时空的尽头！";
        this.color = Color.WHITE.cpy();
        this.color2 =new Color(0.0F, 0.0F, 0.0F, 0.8F);
        this.color.a = 0.0F;
        this.code = 2;
        this.x = (float)Settings.WIDTH / 2.0F;
        switch (code) {
            case 2:
                this.img = new Texture(Gdx.files.internal("img/vfx/Black.png"));
                break;
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 14:
            default:
                this.img = null;
                break;
            case 4:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));
                break;
            case 6:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));
                break;
            case 7:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));
                break;
            case 9:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));
                break;
            case 11:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));
                break;
            case 13:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));;
                break;
            case 15:
                this.img = new Texture(Gdx.files.internal("img/vfx/IfYouCan1920.png"));
        }

    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0.0F) {
            this.isDone = true;
        }
        if(this.duration > 10.0F){
            this.color.a = 0.0F;
        }else
        if (this.duration > 7.0F) {
            this.color.a = (10.0F - this.duration)/3;
        }
        else {
            this.color.a = 1.0F;
            AbstractDungeon.isScreenUp = false;
        }

//        this.gl.setText(FontHelper.largeDialogOptionFont, this.word);
        this.x = (float)Settings.WIDTH / 2.0F - this.gl.width / 2.0F;
        switch (this.code) {
            case 2:
            case 4:
            case 7:
            case 9:
            case 11:
            case 13:
                this.y = 140.0F - this.gl.height / 2.0F;
                break;
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 14:
                this.y = 80.0F - this.gl.height / 2.0F;
                break;
            case 6:
                this.y = 100.0F - this.gl.height / 2.0F;
                break;
            case 15:
                this.y = 160.0F - this.gl.height / 2.0F;
                break;
            case 16:
                this.y = 110.0F - this.gl.height / 2.0F;
                break;
            case 17:
                this.y = 60.0F - this.gl.height / 2.0F;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }

    public void dispose() {
    }
}

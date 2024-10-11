package bossRoom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class KarenScene extends AbstractGameEffect {
    private GlyphLayout gl = new GlyphLayout();
    private String word;
    private float x;
    private float y;
    private Texture img;
    private int code;
    private float speed = 1500f;
    private float xword;

    private float newWIDTH;
    private float talkx;
    private float talky;
    public KarenScene() {
        AbstractDungeon.isScreenUp = true;
        this.duration = 999.0F;
        this.startingDuration = this.duration;
        this.word = "下一个舞台，会在哪里呢？";
        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;


        this.img = new Texture(Gdx.files.internal("img/vfx/blackMask.png"));
        float imgAspectRatio = (float) img.getWidth()/ (float) img.getHeight() ;
        newWIDTH = Settings.HEIGHT * imgAspectRatio;
        this.x = -newWIDTH; // 初始化 x 为屏幕左侧位置

    }

    public KarenScene(float talkX, float talkY) {
        AbstractDungeon.isScreenUp = true;

        this.duration = 999.0F;
        this.startingDuration = this.duration;
        this.word = "我懂的，这就是无法预测的命运舞台！";
        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
        this.talkx = talkX;
        this.talky = talkY;

        this.img = new Texture(Gdx.files.internal("img/vfx/blackMask.png"));
        float imgAspectRatio = (float) img.getWidth()/ (float) img.getHeight() ;
        newWIDTH = Settings.HEIGHT * imgAspectRatio;
        this.x = -newWIDTH; // 初始化 x 为屏幕左侧位置
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0.0F) {
            this.isDone = true;
        }


            this.color.a = 1.0F;
            AbstractDungeon.isScreenUp = false;


        // 更新图片位置
        this.x += speed * Gdx.graphics.getDeltaTime(); // 使用时间增量更新位置

        // 如果图片完全离开屏幕，则设置 isDone 为 true
        if (this.x > Settings.WIDTH) {
            AbstractDungeon.effectList.add(new SpeechBubble(talkx, talky, 10F, "纵使星屑飘落，繁花落尽。在这光芒璀璨的舞台之上，我获得重生。", false));

            this.isDone = true;
        }

        this.gl.setText(FontHelper.largeDialogOptionFont, this.word);
        this.xword = (float) Settings.WIDTH / 2.0F - this.gl.width / 2.0F; // 重新计算文字的位置

        this.y = 140.0F - this.gl.height / 2.0F;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
//        sb.draw(this.img, this.x, 0.0F, this.img.getWidth(), Settings.HEIGHT);
        sb.draw(this.img, this.x, 0.0F, newWIDTH, Settings.HEIGHT);

        FontHelper.renderSmartText(sb, FontHelper.largeDialogOptionFont, this.word, this.xword, this.y, (float) Settings.WIDTH, 0.0F, this.color);
    }

    public void dispose() {
        // 释放纹理资源
        this.img.dispose();
    }
}
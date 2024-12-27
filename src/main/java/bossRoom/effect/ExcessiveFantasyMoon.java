package bossRoom.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ExcessiveFantasyMoon extends AbstractChangeSceneEffect{

        private Texture img;
        private Texture img2;
        private Texture img3;
        public float newX;
    public float newY;
        private boolean right = true;
        private float timer;
        public Color color;
        private AbstractShaderEffect effect;
        private boolean useShader;
    private float centerX;
    private float centerY;
    private float startX, startY; // 起始点 (a, b)
    private float endX, endY;     // 终点 (c, d)
    private float radius;          // 半径 L
    private float startAngle;      // 起始角度
    private float endAngle;        // 结束角度
    private float currentAngle;    // 当前角度
    private float angleDelta;      // 每帧的角度变化量
    private boolean isMoving;      // 是否正在移动
    private float angleStart; // 起始角度
    private float angleEnd;   // 结束角度

    public ExcessiveFantasyMoon(Texture img) {
        this.color = Color.WHITE.cpy();
        this.renderBehind = true;
        this.img = img;
        this.img2 = ImageMaster.loadImage("img/vfx/Mask.png");
        this.img3 = ImageMaster.loadImage("img/vfx/Mask2.png");
//        this.x = -Settings.WIDTH * 1.7F;
        this.timer = 0.0F;

        this.startX = -1*this.img.getWidth();
        this.startY = -1*this.img.getHeight();
        this.endX = Settings.WIDTH*0.65f;
        this.endY = Settings.HEIGHT*0.5f;
        this.radius = 1000;
        this.duration = -1f;
        // 计算圆心位置
        centerX = (startX + endX) / 2;
        centerY = (startY + endY) / 2 - radius;

        // 计算起始角度和结束角度
        angleStart = (float) Math.toDegrees(Math.atan2(startY - centerY, startX - centerX));
        angleEnd = (float) Math.toDegrees(Math.atan2(endY - centerY, endX - centerX));

        if (angleStart > angleEnd) angleEnd += 360; // 确保角度递增

        currentAngle = angleStart;
//      start();
        this.isMoving = true;
    }
    public void start() {
        if (!isMoving) {
            this.isMoving = true;
            this.angleDelta = (endAngle - startAngle) / (15f / Gdx.graphics.getDeltaTime());
        }
    }
    public void update() {
        if(this.isMoving) {
            // 获取自上次调用以来的时间差
            float deltaTime = Gdx.graphics.getDeltaTime();
            // 增加已经经过的时间
            this.duration += deltaTime / 8;
            double transformedDuration = transformDuration(duration);
            if (transformedDuration <= 5.5f) {
                // 计算当前位置所占的比例
                float ratio = (float) (transformedDuration / 10f);
                // 计算当前角度，从起始点到终点是π弧度
                float angle = (float) (Math.PI * ratio);
                // 计算圆心的位置
                float centerX = (startX + endX) / 2;
                float centerY = endY - 1000; // 圆心在下方，所以是从起点y坐标向下L距离
                // 计算当前x和y坐标
                newX = centerX + 1000 * (float) Math.cos(angle);
                newY = centerY + 1000 * (float) Math.sin(angle);

            } else {
                this.isMoving = false;
            }
        }else {
            newX = -34f;
            newY = 527.8f;
        }
    }
    public static double transformDuration(double t) {
        double a = -1.0 / 7;
        double b = 6;
        // 应用变换函数
        double newDuration = a * Math.pow(t - 6, 2) + b;
        return newDuration;
    }

    //     this.img.getWidth(), this.img.getHeight()
//    Settings.WIDTH*0.65f, Settings.HEIGHT*0.5f
        public void end() {
        if (this.effect != null) {
            this.effect.end();
        }
        this.right = false;
//        this.x = Settings.WIDTH - Settings.WIDTH / 0.8F;
    }

    @Override
    public void update(float deltaTime) {

    }


    public void render(SpriteBatch sb) {
        sb.flush();
        sb.setColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, 0.4f);
//        sb.setColor(Color.WHITE.toFloatBits());
        sb.draw(this.img, -newX+ (float) Settings.WIDTH /2, newY- (float) Settings.HEIGHT /5, this.img.getWidth(), this.img.getHeight());




    }

        public void dispose() {}
    }

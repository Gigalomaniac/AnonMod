package bossRoom.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import static com.badlogic.gdx.math.MathUtils.random;

public class FallingStarTime extends AbstractChangeSceneEffect{

        private Texture img;

        public float x;
        private boolean right = true;
        private float timer;
        public Color color;
        private AbstractShaderEffect effect;
        private boolean useShader;
    float startX = Settings.WIDTH;
    float startY = Settings.HEIGHT;
    float targetX = 0;
    float targetY = 0;
    private int lastFallingNum = -1;
    float changedX;
    float changedY;
    float time;
    static float StarShining = 1f;
     Color StarShiningColor = new Color(1.0F, 1.0F, 1.0F, 0f);
    public FallingStarTime(float time) {
        this.color = Color.WHITE.cpy();
        this.renderBehind = true;
        this.img = ImageMaster.loadImage("img/vfx/FallingStar.png");
        this.x = -Settings.WIDTH * 1.7F;
        this.timer = random(-6, 0);
        this.time = time;
//        最大 Settings.WIDTH +this.img.getWidth()
//        Settings.HEIGHT + img.getHeight()
//        最小 -this.img.getWidth()*2
//        -this.img.getHeight()*2
    }

        public void setShader(AbstractShaderEffect effect) {
        this.effect = effect;
        this.useShader = true;
    }

        public void update() {
            this.timer += Gdx.graphics.getDeltaTime();
            float place = this.timer % time;
            int fallingNum = (int) (this.timer / time);
            if (fallingNum != lastFallingNum) {
                this.StarShining= (random(1, 7))/10f;
                this.StarShiningColor.a = StarShining;
//                System.out.println("StarShining"+StarShining);
                this.timer+= random(50, 200)/100;
                if(random(0, 1) == 0) {
                     changedX = random(-Settings.WIDTH/2, 0);
                     changedY = 0;
//                    System.out.println("xxxxxxxx"+changedX);
                }else {

                    changedX = 0 ;
                    changedY = random(-Settings.HEIGHT/2, 0);
//                    System.out.println("yyyyyyy"+changedY);
                }
                lastFallingNum = fallingNum;     // 更新lastFallingNum为新的值
            }

            targetX =changedX + Settings.WIDTH -(( Settings.WIDTH + this.img.getWidth())* place /time);
            targetY=changedY + Settings.HEIGHT - (( Settings.HEIGHT + this.img.getHeight())* place/time);

    }

        public void end() {
//        if (this.effect != null) {
//            this.effect.end();
//        }
//        this.right = false;
//        this.x = Settings.WIDTH - Settings.WIDTH / 0.8F;
    }

    @Override
    public void update(float deltaTime) {

    }

    public void render(SpriteBatch sb) {
        sb.flush();
        sb.setColor(StarShiningColor);
        sb.draw(this.img, targetX, targetY, this.img.getWidth(),this.img.getHeight());
    }

        public void dispose() {}
    }

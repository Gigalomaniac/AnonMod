package bossRoom.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AshAnonChangeSceneEffect extends AbstractGameEffect {
    private Texture img;
    private Texture img2;
    private Texture img3;
    private float x;

    public AshAnonChangeSceneEffect(Texture img) {
        this.right = true;

        this.color = Color.WHITE.cpy();
        this.renderBehind = true;
        this.img = img;
        this.img2 = ImageMaster.loadImage("img/vfx/Mask.png");
        this.img3 = ImageMaster.loadImage("img/vfx/Mask2.png");;
        this.x = Settings.WIDTH * 1.7F;
        this.timer =0.0F;
        this.settimer =0.0F;
    }
    private boolean right; private float timer;private float settimer; private AbstractShaderEffect effect; private boolean useShader; private boolean normalRender;
    public void setShader(AbstractShaderEffect effect) {
        this.effect = effect;
        this.useShader = true;
    }

    public void update() {
        this.settimer += Gdx.graphics.getDeltaTime();
        if (this.settimer >= 5) {
            if (this.right) {
                if (this.x > Settings.WIDTH - Settings.WIDTH / 0.8F) {
                    this.x -= 1000.0F * Gdx.graphics.getDeltaTime() * Settings.scale;
                    this.timer -= Gdx.graphics.getDeltaTime();
                    if (this.timer < 0.0F) {
                        this.timer += 0.02F;
                        for (int i = 0; i < 6; i++) {
                            AbstractDungeon.topLevelEffects.add(new PaperEffect(this.x - Settings.WIDTH * 0.2F - MathUtils.random(250.0F, 500.0F) * Settings.scale,
                                    MathUtils.random(Settings.HEIGHT)));
                        }
                    }
                } else {

                    this.x = Settings.WIDTH - Settings.WIDTH / 0.8F;
                    this.normalRender = true;
                    if (this.useShader) {
                        this.useShader = false;
                        AbstractDungeon.effectsQueue.add(this.effect);
                    }
                }
            } else {

                this.normalRender = false;
                if (this.x > -Settings.WIDTH * 1.7F) {
                    this.x -= 1000.0F * Gdx.graphics.getDeltaTime() * Settings.scale;
                    this.timer -= Gdx.graphics.getDeltaTime();
                    if (this.timer < 0.0F) {
                        this.timer += 0.02F;
                        for (int i = 0; i < 6; i++) {
                            AbstractDungeon.topLevelEffects.add(new PaperEffect(this.x + Settings.WIDTH * 1.3F - MathUtils.random(250.0F, 500.0F) * Settings.scale,
                                    MathUtils.random(Settings.HEIGHT)));
                        }
                    }
                } else {

                    this.isDone = true;
                }
            }
        }
    }
    public boolean isEnd() { return (this.x < 0.0F); }

    public void end() {
        if (this.effect != null) {
            this.effect.end();
        }
        this.right = false;
        this.x = Settings.WIDTH - Settings.WIDTH / 0.8F;
    }

    public void render(SpriteBatch sb) {
        sb.flush();
        sb.setColor(Color.WHITE.toFloatBits());
        if (this.normalRender) {
            sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.WIDTH / this.img.getWidth() * 1.0F * this.img.getHeight());
        } else {

            drawAlphaMask(sb);
            drawForeground(sb, (int)this.x, 0, (int)(Settings.WIDTH / 0.8F), Settings.HEIGHT);
        }
    }

    private void drawAlphaMask(SpriteBatch sb) {
        Gdx.gl.glColorMask(false, false, false, true);

        sb.setBlendFunction(771, 771);
        if (!this.right) {
            sb.draw(this.img2, this.x, 0.0F, Settings.WIDTH / 0.8F, Settings.HEIGHT);
        } else {

            sb.draw(this.img3, this.x, 0.0F, Settings.WIDTH / 0.8F, Settings.HEIGHT);
        }
    }

    private void drawForeground(SpriteBatch sb, int clipX, int clipY, int clipWidth, int clipHeight) {
        Gdx.gl.glColorMask(true, true, true, true);

        sb.setBlendFunction(773, 772);
        Gdx.gl.glEnable(3089);
        Gdx.gl.glScissor(clipX, clipY, clipWidth, clipHeight);
        sb.setColor(this.color);
        sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.WIDTH / this.img.getWidth() * 1.0F * this.img.getHeight());
        sb.flush();
        Gdx.gl.glDisable(3089);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
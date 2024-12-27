package ExcessiveFantasy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageNumberEffect;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;

public class TimepieceTrigger extends AbstractGameEffect {
    private static Texture clockface, clockhand;
    private AbstractCreature target;
    private int forward;

    private Color color;
    private float minutePhase, phaseVelocity = 0F;

    private boolean healvfx;

    public TimepieceTrigger(AbstractCreature target, boolean forward) {
        this(target, forward, true);
    }
    public TimepieceTrigger(AbstractCreature target, boolean forward, boolean healvfx) {
        this.renderBehind = false;
        this.duration = 5F;
        this.target = target;
        this.forward = (forward ? -1 : 1);
        this.healvfx = healvfx;
        this.color = Color.WHITE.cpy().sub(0 ,0, 0, 1F);

        if(clockface == null) {
            clockface = ImageMaster.loadImage("img/vfx/clockface.png");
            clockhand = ImageMaster.loadImage("img/vfx/clockhand.png");
        }

        this.target.hideHealthBar();
        this.target.healthBarUpdatedEvent();

        this.minutePhase = MathUtils.random(200F) * this.forward;

        this.scale = target.hb.width / clockface.getWidth() * 2;
    }

    @Override
    public void update() {
        phaseVelocity += 0.2F * this.forward;
        float phaseIncrease = Gdx.graphics.getDeltaTime() * phaseVelocity;
//        if(healvfx && Math.abs(minutePhase) % (Math.PI * 2) > Math.abs(minutePhase + phaseIncrease) % (Math.PI * 2)) {
//            if(forward < 0) {
//                AbstractDungeon.effectsQueue.add(new StrikeEffect(this.target, this.target.hb.cX, this.target.hb.cY, 0));
//                AbstractDungeon.effectsQueue.add(new DamageNumberEffect(this.target, this.target.hb.cX, this.target.hb.cY, MathUtils.random(20, 4000)));
//            } else {
//                AbstractDungeon.effectsQueue.add(new HealEffect(this.target.hb.cX, this.target.hb.cY, MathUtils.random(20, 4000)));
//            }
//        }
        minutePhase += phaseIncrease;

        if(this.duration > 0) {
            this.duration -= Gdx.graphics.getDeltaTime();
            if (color.a < 1F) {
                color.a += Gdx.graphics.getDeltaTime() * 2;
                if(color.a > 1F) {
                    color.a = 1F;
                }
            }
        } else {
            if (color.a > 0F) {
                color.a -= Gdx.graphics.getDeltaTime();
            } else {
                this.isDone = true;
                if(this.healvfx && !AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    this.target.showHealthBar();
                    this.target.healthBarUpdatedEvent();
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.setBlendFunction(770, 1);
        float width = clockface.getWidth();
        float height = clockface.getHeight();
        sb.draw(clockface, this.target.drawX - width / 2, this.target.hb.cY - height / 2, width / 2, height / 2, width, height, this.scale, this.scale, 0, 0, 0, clockface.getWidth(), clockface.getHeight(), false, false);

        width = clockhand.getWidth();
        height = clockhand.getHeight();
        sb.draw(clockhand, this.target.drawX - width / 2, this.target.hb.cY, width / 2, 0, width, height, this.scale, this.scale, (float)Math.toDegrees(minutePhase), 0, 0, clockhand.getWidth(), clockhand.getHeight(), false, false);

        final float hourhand = 0.9F;
        width *= hourhand;
        height *= hourhand;
        sb.setColor(color.cpy().sub(0.2F, 0.2F, 0.2F, 0F));
        sb.draw(clockhand, this.target.drawX - width / 2, this.target.hb.cY, width / 2, 0, width, height, this.scale * hourhand, this.scale * hourhand, (float)Math.toDegrees(minutePhase / 12), 0, 0, clockhand.getWidth(), clockhand.getHeight(), false, false);
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}

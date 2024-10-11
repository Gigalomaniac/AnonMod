package relics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import events.AnonAwake;
import events.BandFriendsEvent;

public class SearchNumbersEffect extends AbstractGameEffect {
    private static final float DUR = 2.0F;

    private boolean hasTrained = false;

    private Color screenColor = AbstractDungeon.fadeColor.cpy();


    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
//        updateBlackScreenColor();
        if (this.duration < 1.0F && !this.hasTrained) {
//            this.hasTrained = true;
//            if (AbstractDungeon.player.hasRelic("smartPhone")) {
//                AbstractDungeon.player.getRelic("smartPhone").flash();
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
//                CardCrawlGame.sound.play("ATTACK_HEAVY");
//                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
//                CardCrawlGame.metricData.addCampfireChoiceData("LIFT",
//
//                        Integer.toString((AbstractDungeon.player.getRelic("smartPhone")).counter));
            }
//            AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(new Color(0.8F, 0.6F, 0.1F, 0.0F)));




//        }
//        if (this.duration < 0.0F) {
//            this.isDone = true;
//            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
//        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }

    @Override
    public void dispose() {

    }


    private void updateBlackScreenColor() {
        if (this.duration > 1.5F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
        } else if (this.duration < 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
        } else {
            this.screenColor.a = 1.0F;
        }
    }
}

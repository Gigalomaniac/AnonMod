package bossRoom.effect;

import bossRoom.CrychicSide;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CrychicSceneEffect extends AbstractGameEffect{
    public CrychicSceneEffect(CrychicSide crychicSide) {

        this.hb = new Hitbox(Settings.WIDTH, Settings.HEIGHT);
        this.hb.x = 0.0F;
        this.hb.y = 0.0F;
        this.show = true;
        this.crychicSide = crychicSide;
        AbstractDungeon.isScreenUp = true;
    }
    private Hitbox hb; private RoomEventDialog roomEventText; private boolean show; private CrychicSide crychicSide;
    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.BLACK.cpy());
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
        sb.setColor(Color.WHITE.cpy());
        this.roomEventText.render(sb);
    }

    @Override
    public void dispose() {
        this.roomEventText.clear();
    }

    public void update() {
        this.crychicSide.startNext();
        this.roomEventText.update();
    }
}

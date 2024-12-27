package BossRewards;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FullImgCardFlashVfx extends AbstractGameEffect {
    private AbstractCard card;
    private TextureAtlas.AtlasRegion img;
    private float yScale;


    public FullImgCardFlashVfx(AbstractCard card) {
        this(card, new Color(1.0F, (float) 136 /255, (float) 153 /255, 0F));
    }


    public FullImgCardFlashVfx(AbstractCard card, Color c) {
        this.yScale = 0.0F;
        this.card = card;
        this.duration = 1.1F;
        this.img = ImageMaster.CARD_SUPER_SHADOW;

        this.color = c;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else {
            this.yScale = Interpolation.bounceIn.apply(1.2F, 1.1F, this.duration * 2.0F);
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        if(this.duration >=1f)
        this.color.a = 1f;
        else this.color.a = this.duration;
        sb.setColor(this.color);

            sb.draw(this.img, this.card.current_x + this.img.offsetX - (float)this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - (float)this.img.originalHeight / 2.0F, (float)this.img.originalWidth / 2.0F - this.img.offsetX, (float)this.img.originalHeight / 2.0F - this.img.offsetY, (float)this.img.packedWidth, (float)this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.52F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.53F * Settings.scale, this.card.angle);
            sb.draw(this.img, this.card.current_x + this.img.offsetX - (float)this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - (float)this.img.originalHeight / 2.0F, (float)this.img.originalWidth / 2.0F - this.img.offsetX, (float)this.img.originalHeight / 2.0F - this.img.offsetY, (float)this.img.packedWidth, (float)this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.55F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.57F * Settings.scale, this.card.angle);
            sb.draw(this.img, this.card.current_x + this.img.offsetX - (float)this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - (float)this.img.originalHeight / 2.0F, (float)this.img.originalWidth / 2.0F - this.img.offsetX, (float)this.img.originalHeight / 2.0F - this.img.offsetY, (float)this.img.packedWidth, (float)this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.58F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.6F * Settings.scale, this.card.angle);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}

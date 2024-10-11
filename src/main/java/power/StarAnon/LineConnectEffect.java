package power.StarAnon;

import com.badlogic.gdx.graphics.Color;
import power.StarAnon.WorldCrown;
import power.StarAnon.WorldChalice;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.brashmonkey.spriter.Calculator;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


public class LineConnectEffect
        extends AbstractGameEffect
{
    public static TextureAtlas.AtlasRegion simg = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/vfx/20240720194216.png"), 0, 0, 120, 8);
    private TextureAtlas.AtlasRegion img = simg;

    private float y1;

    private float x1;

    private float y2;

    private float x2;

    public LineConnectEffect() {
        this.color = Color.WHITE.cpy();
        if (AbstractDungeon.getCurrMapNode() == null)
            return;  if (AbstractDungeon.getCurrRoom() == null)
            return;  if (AbstractDungeon.getMonsters() == null)
            return;  if ((AbstractDungeon.getMonsters()).monsters == null)
            return;  boolean flag = true;
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m.hasPower(WorldCrown.POWER_ID)) {
                flag = false; continue;
            }  if (m.hasPower(WorldChalice.POWER_ID)) {
                this.isDone = false;
            }
        }
        this.isDone = (this.isDone || flag);
    }

    public void update() {
        if (this.isDone) {
            return;
        }

        this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (2.0F - this.duration) * (2.0F - this.duration) / 4.0F);

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F)
            this.isDone = true;
    }

    public void render(SpriteBatch sb) {
//        System.out.println("WorldCrown.WorldCrownX"+WorldCrown.WorldCrownX+"WorldCrown.WorldCrownY"+WorldCrown.WorldCrownY);
//        System.out.println("WorldChalice.WorldChaliceX"+WorldChalice.WorldChaliceX+"WorldChalice.WorldChaliceY"+WorldChalice.WorldChaliceY);
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, WorldCrown.WorldCrownX, WorldCrown.WorldCrownY - 4.0F, 0.0F, this.img.packedHeight / 2.0F, dist() / Settings.scale, this.img.packedHeight, this.scale, this.scale, zeta());
        sb.setBlendFunction(770, 771);
    }

    public static float dist() { return Calculator.sqrt((WorldCrown.WorldCrownX - WorldChalice.WorldChaliceX) * (WorldCrown.WorldCrownX - WorldChalice.WorldChaliceX) + (WorldCrown.WorldCrownY - WorldChalice.WorldChaliceY) * (WorldCrown.WorldCrownY - WorldChalice.WorldChaliceY)); }

    public static float zeta() { return (float)(Math.atan2((-WorldCrown.WorldCrownY + WorldChalice.WorldChaliceY), (-WorldCrown.WorldCrownX + WorldChalice.WorldChaliceX)) / Math.PI * 180.0D); }

    public void dispose() {}
}

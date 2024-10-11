package bossRoom.effect;

import bossRoom.CrychicSide;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;


public class ClickCGEffect
        extends AbstractGameEffect
{
    public static final String ID = "ClickCGEffect";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("ClickCGEffect");
    public static final String NAME = eventStrings.NAME;
    public static String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;

    private final float move = Settings.scale;
    private Texture img;
    private Texture img2;
    private int dialogue;

    public ClickCGEffect(CrychicSide crychicSide) {
        this.roomEventText = new RoomEventDialog();

        this.img = ImageMaster.loadImage("img/boss/end1.png");
        this.img2 = ImageMaster.loadImage("img/boss/end2.png");
        this.dialogue = 0;
        this.hb = new Hitbox(Settings.WIDTH, Settings.HEIGHT);
        this.hb.x = 0.0F;
        this.hb.y = 0.0F;
        this.show = true;
        this.crychicSide = crychicSide;
        this.DESCRIPTIONS = new String[]{"这里是文本1(按任意键继续)", "这里是文本2(按任意键继续)"};
        AbstractDungeon.isScreenUp = true;
        Settings.scale = (float) (Settings.scale *2);
    }
    private Hitbox hb; private RoomEventDialog roomEventText; private boolean show; private CrychicSide crychicSide;
    public void update() {
        if (this.show) {
            this.show = false;
            this.roomEventText.show(DESCRIPTIONS[this.dialogue]);
        }

        this.hb.update();
        if (this.hb.hovered && InputHelper.justClickedLeft) {
            InputHelper.justClickedLeft = false;
            this.hb.clickStarted = true;
        }
        if (this.hb.clicked) {
            this.hb.clicked = false;
            nextDialogue();
        }

        this.roomEventText.update();
    }

    private void nextDialogue() {
        if (this.dialogue == 0) {
//            CardCrawlGame.sound.play("ATTACK_FIRE");
            AbstractDungeon.topLevelEffectsQueue.add(new BorderFlashEffect(Color.RED));
        }
        if (this.dialogue < DESCRIPTIONS.length - 1) {
            this.dialogue++;
            this.roomEventText.updateBodyText(DESCRIPTIONS[this.dialogue]);
        } else {

//            this.crychicSide.startNext();
            Settings.scale = move;
            this.isDone = true;
            AbstractDungeon.isScreenUp = false;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.BLACK.cpy());
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);

        sb.setColor(Color.WHITE.cpy());
        if (this.dialogue > 0) {
            sb.draw(this.img2, 0 , 0, this.img2.getWidth(), this.img2.getHeight());
//            sb.draw(this.img2, Settings.WIDTH * 0.75F - (this.img2.getWidth() / 2), Settings.HEIGHT * 0.8F - this.img.getHeight(), this.img2.getWidth(), this.img2.getHeight());
        } else {
            sb.draw(this.img, 0, 0, this.img.getWidth(), this.img.getHeight());
//            sb.draw(this.img, Settings.WIDTH * 0.75F - (this.img.getWidth() / 2), Settings.HEIGHT * 0.8F - this.img.getHeight(), this.img.getWidth(), this.img.getHeight());
        }


        this.roomEventText.render(sb);
    }

    public void dispose() { this.roomEventText.clear(); }
}
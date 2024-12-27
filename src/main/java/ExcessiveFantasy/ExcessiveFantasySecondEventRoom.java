package ExcessiveFantasy;

import ExcessiveFantasy.diag.ExcessiveFantasySoyo;
import ExcessiveFantasy.diag.ExcessiveFantasyTomori;
import Mod.AnonMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ExcessiveFantasySecondEventRoom extends AbstractRoom {
    public static Texture SelectImg = new Texture("img/UI/WebGalHitbox.png");
    public  boolean done = false;
    public  boolean finished = false;
    private Hitbox hitbox;
    private Hitbox hitbox2;
    private final TextureRegion TEXTBOX;
    private String dialogMsg = "(自由行动时间)该找谁玩呢？";
    private String Select1 = "约Tomori去水族馆";
    private String Select2 = "去Soyorin家玩";
    public ExcessiveFantasySecondEventRoom() {
//        this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
        this.phase = RoomPhase.INCOMPLETE;
        this.mapSymbol = "?";
        this.mapImg = ImageMaster.MAP_NODE_EVENT;
        this.mapImgOutline = ImageMaster.MAP_NODE_EVENT_OUTLINE;
        AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
        done = false;
        Texture TEXTBOX_TEXTURE =  new Texture("img/boss/AnonTalk.png");
        this.TEXTBOX = new TextureRegion(TEXTBOX_TEXTURE);
        this.hitbox = new Hitbox(SelectImg.getWidth(), SelectImg.getHeight() );
        this.hitbox.move((float) Settings.WIDTH / 2 - (float) SelectImg.getWidth() / 2, (float) Settings.HEIGHT / 2 + (float) SelectImg.getHeight() );
        this.hitbox2 = new Hitbox(SelectImg.getWidth(), SelectImg.getHeight() );
        this.hitbox2.move((float) Settings.WIDTH / 2 - (float) SelectImg.getWidth() / 2, (float) Settings.HEIGHT / 2 );
    }

    public void onPlayerEntry() {
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        CardCrawlGame.fadeIn(1.0F);
        CardCrawlGame.music.playTempBgmInstantly("04_Nobiri.mp3", true);
    }
    public void update() {
        this.hitbox.update();
        this.hitbox2.update();
        if (this.hitbox.hovered && InputHelper.justClickedLeft){
            AnonMod.saves.setString("choice","tomori");
            AbstractDungeon.topLevelEffectsQueue.add(new ExcessiveFantasyTomori(0, 11));
            InputHelper.justClickedLeft = false;
            this.hitbox.translate(this.hitbox.x*-1,this.hitbox.y*-1);
            this.hitbox2.translate(this.hitbox2.x*-1,this.hitbox2.y*-1);
        }
        if (this.hitbox2.hovered && InputHelper.justClickedLeft){
            AnonMod.saves.setString("choice","soyo");
            AbstractDungeon.topLevelEffectsQueue.add(new ExcessiveFantasySoyo(0, 11));
            InputHelper.justClickedLeft = false;
            this.hitbox.translate(this.hitbox.x*-1,this.hitbox.y*-1);
            this.hitbox2.translate(this.hitbox2.x*-1,this.hitbox2.y*-1);
        }
//        if (this.waitTimer > 0.0F) {
//            this.waitTimer -= Gdx.graphics.getDeltaTime();
//            if (this.waitTimer < 0.0F && this.hasDialog) {
//                this.roomEventText.show(this.body);
//                this.waitTimer = 0.0F;
//            }
//        } else if (AbstractDungeon.getCurrRoom().phase != RoomPhase.COMBAT && !this.hideAlpha) {
//            this.panelAlpha = MathHelper.fadeLerpSnap(this.panelAlpha, 0.66F);
//        } else {
//            this.panelAlpha = MathHelper.fadeLerpSnap(this.panelAlpha, 0.0F);
//        }
//
//        if (!RoomEventDialog.waitForInput) {
//            this.buttonEffect(this.roomEventText.getSelectedOption());
//        }
//        System.out.println(done);
//        System.out.println(this.hitbox.hovered+"?"+this.hitbox2.hovered+"??"+InputHelper.justClickedLeft);
        if (done){
            AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
            AbstractDungeon.dungeonMapScreen.open(false);
            done = false;
            finished = true;
        }
    }

    public void render(SpriteBatch sb) {
//        super.render(sb);
        if(!this.finished) {
            sb.draw(SelectImg, (float) Settings.WIDTH / 2 - (float) SelectImg.getWidth() / 2, (float) Settings.HEIGHT / 2 + (float) SelectImg.getHeight() / 2, SelectImg.getWidth(), SelectImg.getHeight());
            sb.draw(SelectImg, (float) Settings.WIDTH / 2 - (float) SelectImg.getWidth() / 2, (float) Settings.HEIGHT / 2 - (float) SelectImg.getHeight() / 2, SelectImg.getWidth(), SelectImg.getHeight());
            sb.draw(this.TEXTBOX, 0, 0, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT / 3, Settings.scale, Settings.scale, 0.0F);
            renderSelection(sb);
            renderAnonDialog(sb);
        }
    }
    private void renderSelection(SpriteBatch sb) {
        sb.setColor(Color.BLACK);
            FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, Select1,
                    (float) Settings.WIDTH /2- (float) SelectImg.getWidth() /4, (float) Settings.HEIGHT /2 + (float) SelectImg.getHeight(), Color.WHITE.cpy());
        FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, Select2,
                (float) Settings.WIDTH /2- (float) SelectImg.getWidth() /4, (float) Settings.HEIGHT /2 , Color.WHITE.cpy());

    }
    private void renderAnonDialog(SpriteBatch sb) {
        sb.setColor(Color.BLACK);
        String[] splitStrings = splitStringIntoChunksOfTen();
        float y = Settings.HEIGHT * 0.2F;
        for (String s : splitStrings) {
            FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, s,  Settings.HEIGHT * 0.3F, y, Color.WHITE.cpy());
            y -= FontHelper.topPanelInfoFont.getLineHeight();
        }
    }
    public String[] splitStringIntoChunksOfTen() {
        int chunkSize = 50;
        int length = dialogMsg.length();
        int chunkCount = (int) Math.ceil((double) length / chunkSize);
        String[] chunks = new String[chunkCount];

        for (int i = 0; i < length; i += chunkSize) {
            chunks[i / chunkSize] = dialogMsg.substring(i, Math.min(length, i + chunkSize));
        }

        return chunks;
    }
    public void renderAboveTopPanel(SpriteBatch sb) {
//        super.renderAboveTopPanel(sb);
//        if (this.event != null) {
//            this.event.renderAboveTopPanel(sb);
//        }

    }
}
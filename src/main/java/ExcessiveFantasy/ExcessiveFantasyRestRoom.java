package ExcessiveFantasy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;

public class ExcessiveFantasyRestRoom extends RestRoom
{
    public long fireSoundId;
    public static long lastFireSoundId = 0L;
//    public CampfireUI campfireUI;

    public ExcessiveFantasyRestRoom() {
        this.phase = RoomPhase.INCOMPLETE;
        this.mapSymbol = "R";
        this.mapImg = ImageMaster.MAP_NODE_REST;
        this.mapImgOutline = ImageMaster.MAP_NODE_REST_OUTLINE;
    }

    public void onPlayerEntry() {
        if (!AbstractDungeon.id.equals("TheEnding")) {
            CardCrawlGame.music.silenceBGM();
        }

        this.fireSoundId = CardCrawlGame.sound.playAndLoop("REST_FIRE_WET");
        lastFireSoundId = this.fireSoundId;
        this.campfireUI = new CampfireUI();

//        Iterator var1 = AbstractDungeon.player.relics.iterator();
//
//        while(var1.hasNext()) {
//            AbstractRelic r = (AbstractRelic)var1.next();
//            r.onEnterRestRoom();
//        }

    }

    public AbstractCard.CardRarity getCardRarity(int roll) {
        return this.getCardRarity(roll, false);
    }

    public void update() {
//        super.update();
        if (this.campfireUI != null) {
            this.campfireUI.update();
        }

    }

    public void fadeIn() {
        if (!AbstractDungeon.id.equals("TheEnding")) {
            CardCrawlGame.music.unsilenceBGM();
        }

    }

    public void cutFireSound() {
        CardCrawlGame.sound.fadeOut("REST_FIRE_WET", ((ExcessiveFantasyRestRoom)AbstractDungeon.getCurrRoom()).fireSoundId);
    }

    public void updateAmbience() {
        CardCrawlGame.sound.adjustVolume("REST_FIRE_WET", this.fireSoundId);
    }

    public void render(SpriteBatch sb) {
        if (this.campfireUI != null) {
            this.campfireUI.render(sb);
        }

//        super.render(sb);
    }
}

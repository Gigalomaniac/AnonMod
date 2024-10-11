package bossRoom;

import actions.movie.AnonEndingEffect;
import actions.movie.CanStopMediaPlayerAction;
import actions.movie.SimplePlayVideoEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static utils.AnonSpireKit.addToBot;


public class AnonVictoryRoom
            extends AbstractRoom {
        public AnonVictoryRoom() {
            this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
            AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
        }


        public void onPlayerEntry() {
            AbstractDungeon.isScreenUp = true;
            AbstractDungeon.overlayMenu.proceedButton.hide();
            CardCrawlGame.fadeIn(1.0F);
            addToBot(new CanStopMediaPlayerAction(new SimplePlayVideoEffect("movie/mygo片尾曲.webm")));

    }
}

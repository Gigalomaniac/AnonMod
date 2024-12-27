package actions;

import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class TalkAction extends AbstractGameAction {
        private String word;
    private float settimer;
    private float x;
    private float y;
    private  float during;
    public TalkAction(float v, float v1, float v2, String s) {
        x=v;
        y=v1;
        word = s;
        during = v2;
    }

    public void update() {
            AbstractDungeon.effectList.add(new SpeechBubble(x,y,during, word, false));
            this.isDone = true;
        }
}



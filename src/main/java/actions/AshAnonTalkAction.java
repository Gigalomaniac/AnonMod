package actions;

import bossRoom.InnerFavillaeSide;
import cards.token.Minute;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class AshAnonTalkAction extends AbstractGameAction {
        private boolean upgrade;
    private float settimer;

    private InnerFavillaeSide ashAnon ;
        public AshAnonTalkAction(InnerFavillaeSide AshAnon) {
            this.settimer =0.0F;
            ashAnon = AshAnon;
        }

        public void update() {
            this.settimer += Gdx.graphics.getDeltaTime();
            if (this.settimer >= 0){
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(ashAnon.hb.cX + ashAnon.dialogX - 50, ashAnon.hb.cY + ashAnon.dialogY + 50, 10F, "谢谢你，最后再陪我任性一次吧 NL 这一次，竭尽全力吧！", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(ashAnon.hb.cX + ashAnon.dialogX - 50, ashAnon.hb.cY + ashAnon.dialogY + 50, 10F, "Thank you, accompany me one last time in my capriciousness, NL Let's turn to ashes together, my past self!", false));
                }
                this.isDone = true;
            }
        }
}



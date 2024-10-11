package actions;

import cards.token.Minute;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TomoriAction extends AbstractGameAction {
        private boolean upgrade;

        public TomoriAction(boolean upgraded) {
            this.upgrade = upgraded;
        }

        public void update() {
            int theSize = AbstractDungeon.player.hand.size();
            if (this.upgrade) {
                AbstractCard s = (new Minute()).makeCopy();
                s.upgrade();
                this.addToTop(new MakeTempCardInHandAction(s, theSize));
            } else {
                this.addToTop(new MakeTempCardInHandAction(new Minute(), theSize));
            }

            this.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, theSize, false));
            this.isDone = true;
        }
}



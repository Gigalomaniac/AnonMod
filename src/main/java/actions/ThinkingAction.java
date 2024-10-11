package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;

public class ThinkingAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList();
    private boolean upgraded = false;

    public ThinkingAction(boolean armamentsPlus) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = armamentsPlus;
    }

    public void update() {
        Iterator var1;
        AbstractCard c;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.upgraded) {
                var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (c.canUpgrade() && !c.cardID.equals("Thinking")) {
                        c.upgrade();
                        c.superFlash();
                        c.applyPowers();
                    }
                }

                this.isDone = true;
                return;
            }

            var1 = this.p.hand.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (!c.canUpgrade()) {
                    this.cannotUpgrade.add(c);
                }
            }

            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (c.canUpgrade() && !c.cardID.equals("Thinking")) {
                        c.upgrade();
                        c.superFlash();
                        c.applyPowers();
                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotUpgrade);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                this.p.hand.getTopCard().upgrade();
                this.p.hand.getTopCard().superFlash();
                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                c.upgrade();
                c.superFlash();
                c.applyPowers();
                this.p.hand.addToTop(c);
            }

            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        Iterator var1 = this.cannotUpgrade.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
        TEXT = uiStrings.TEXT;
    }
}

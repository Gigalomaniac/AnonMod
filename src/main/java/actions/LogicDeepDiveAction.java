package actions;

import bandFriendsCard.Mutsumi;
import bandFriendsCard.Sakiko;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

public class LogicDeepDiveAction extends AbstractGameAction {
    private AbstractPlayer p;
    private final boolean upgrade;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private ArrayList<AbstractCard> exhumes = new ArrayList();

    public LogicDeepDiveAction(boolean upgrade) {
        this.upgrade = upgrade;
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        Iterator c;
        AbstractCard derp;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
            } else if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
            } else if (this.p.exhaustPile.size() == 1) {
//                if (this.p.exhaustPile.group.get(0).cardID.equals("LogicDeepDive") || this.p.exhaustPile.group.get(0).cardID.equals(Sakiko.ID)||this.p.exhaustPile.group.get(0).cardID.equals(Mutsumi.ID)) {
                    if (this.p.exhaustPile.group.get(0).cardID.equals("LogicDeepDive") || this.p.exhaustPile.group.get(0).cardID.equals(Sakiko.ID)) {
                    this.isDone = true;
                } else {
                    AbstractCard a = this.p.exhaustPile.getTopCard();
                    a.unfadeOut();
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), a.costForTurn, true, true));
                    a.costForTurn = 0;
                    this.p.hand.addToHand(a);
                    if (AbstractDungeon.player.hasPower("Corruption") && a.type == AbstractCard.CardType.SKILL) {
                        a.setCostForTurn(-9);
                    }
                    a.setCostForTurn(-9);
                    this.p.exhaustPile.removeCard(a);
                    if (this.upgrade && a.canUpgrade()) {
                        a.upgrade();
                    }

                    a.unhover();
                    a.fadingOut = false;
                    this.isDone = true;
                }
            } else {
                c = this.p.exhaustPile.group.iterator();

                while(c.hasNext()) {
                    derp = (AbstractCard)c.next();
                    derp.stopGlowing();
                    derp.unhover();
                    derp.unfadeOut();
                }

                c = this.p.exhaustPile.group.iterator();

                while(c.hasNext()) {
                    derp = (AbstractCard)c.next();
                    if (derp.cardID.equals("LogicDeepDive") || derp.cardID.equals(Sakiko.ID) ) {
//                        if (derp.cardID.equals("LogicDeepDive") || derp.cardID.equals(Sakiko.ID) ||derp.cardID.equals(Mutsumi.ID)) {
                        c.remove();
                        this.exhumes.add(derp);
                    }
                }

                if (this.p.exhaustPile.isEmpty()) {
                    this.p.exhaustPile.group.addAll(this.exhumes);
                    this.exhumes.clear();
                    this.isDone = true;
                } else {
                    AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, TEXT[0], false);
                    this.tickDuration();
                }
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(c = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); c.hasNext(); derp.unhover()) {
                    derp = (AbstractCard)c.next();
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), derp.costForTurn, true, true));
                    derp.setCostForTurn(-9);
                    this.p.hand.addToHand(derp);
                    if (AbstractDungeon.player.hasPower("Corruption") && derp.type == AbstractCard.CardType.SKILL) {
                        derp.setCostForTurn(-9);
                    }
                    derp.setCostForTurn(-9);
                    this.p.exhaustPile.removeCard(derp);
                    if (this.upgrade && derp.canUpgrade()) {
                        derp.upgrade();
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
                this.p.exhaustPile.group.addAll(this.exhumes);
                this.exhumes.clear();

                for(c = this.p.exhaustPile.group.iterator(); c.hasNext(); derp.target_y = 0.0F) {
                    derp = (AbstractCard)c.next();
                    derp.unhover();
                    derp.target_x = (float)CardGroup.DISCARD_PILE_X;
                }
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");
        TEXT = uiStrings.TEXT;
    }
}

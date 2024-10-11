//package actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
// import com.megacrit.cardcrawl.cards.AbstractCard;
// import com.megacrit.cardcrawl.characters.AbstractPlayer;
// import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
// import java.util.ArrayList;
//
//public class StarAnonCardAction extends AbstractGameAction {
//    private AbstractPlayer p;
//    private int times;
//
//    public StarAnonCardAction(int times) {
//        this.p = AbstractDungeon.player;
//
//        this.times = times;
//    }
//
//    public void update() {
//        if (this.times == 0) {
//            ArrayList<AbstractCard> eligibleCards = new ArrayList<AbstractCard>();
//            for (AbstractCard c : this.p.hand.group) {
//                if (c != null && (c.baseDamage > 0 || c.baseBlock > 0 || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.ATTACK)) {
//                    eligibleCards.add(c);
//                }
//            }
//
//            if (!eligibleCards.isEmpty()) {
//                AbstractCard selectedCard = (AbstractCard)eligibleCards.get(AbstractDungeon.cardRandomRng.random(eligibleCards.size() - 1));
//                addToTop(new gaizAction(this.p, selectedCard, "hand", 1));
//                AbstractDungeon.effectList.add(new FineTuningEffect(selectedCard));
//            }
//        } else if (this.times > 0) {
//            for (AbstractCard c : this.p.hand.group) {
//                if (c != null && (c.baseDamage > 0 || c.baseBlock > 0 || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.ATTACK)) {
//                    addToTop(new gaizAction(this.p, c, "hand", 1));
//                    AbstractDungeon.effectList.add(new FineTuningEffect(c));
//                }
//            }
//        } else if (this.times < 0) {
//            for (AbstractCard c : this.p.hand.group) {
//                if (c.type == AbstractCard.CardType.STATUS) {
//                    addToTop(new gaizAction(this.p, c, "hand", 1));
//                    AbstractDungeon.effectList.add(new FineTuningEffect(c));
//                }
//            }
//        }
//
//        tickDuration();
//    }
//}

package cards.yuzu;

import cards.others.Anon_Strike;
import cards.others.KarenShing;
import cards.others.Roselia;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class YUZUSimplifiedComboAction extends AbstractGameAction {
    private ArrayList<AbstractCard> cardList;
    private int amount;
    private boolean retrieveCard=false;
    public YUZUSimplifiedComboAction(ArrayList<AbstractCard> cards, int amount){
        this.actionType= ActionType.CARD_MANIPULATION;
        this.duration= Settings.ACTION_DUR_FAST;
        this.cardList=cards;
        this.amount=amount;
    }
    @Override
    public void update() {
        if(this.duration==Settings.ACTION_DUR_FAST){
            AbstractDungeon.cardRewardScreen.customCombatOpen(cardList,"//",false);
            tickDuration();
            return;
        }
        if(!this.retrieveCard){
            if(AbstractDungeon.cardRewardScreen.discoveryCard!=null){
                AbstractCard card=AbstractDungeon.cardRewardScreen.discoveryCard;
                addToTop(new SelectCardsInHandAction(this.amount,"",false,false,card1 -> (((!card1.cardID.equals(Roselia.ID)) && (!card1.cardID.equals(KarenShing.ID)))),(cards -> {
                    for(int i=0;i<cards.size();i++){
                        AbstractCard originCard=cards.get(i);
                        AbstractCard newCard=card.makeStatEquivalentCopy();
                        newCard.current_x=originCard.current_x;
                        newCard.current_y=originCard.current_y;
                        newCard.cost=0;
                        newCard.costForTurn=0;
                        newCard.isCostModified=true;
                        cards.set(i,newCard);
                    }
                })));
            }
            this.retrieveCard=true;
        }
        tickDuration();
    }
}

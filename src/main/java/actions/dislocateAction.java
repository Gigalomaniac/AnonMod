package actions;

import cards.uncommon.DreamComeTrue;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import power.GirlsReboot;
import power.Shining;
import power.heavy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class dislocateAction  extends AbstractGameAction {

    private AbstractMonster m;

    private DreamComeTrue dreamComeTrue;

    AbstractCreature p;
    public dislocateAction(AbstractCreature p, DreamComeTrue dreamcometrue) {
        this.p = p;
        this.dreamComeTrue = dreamcometrue;
    }

    public void update() {
        int randomNum2 = 1;
        if(p.hasPower(GirlsReboot.POWER_ID) && Shining.allCount != heavy.count) {
            Random rand = new Random();
            int randomNum = rand.nextBoolean() ? 4 : 5;
            randomNum2 = rand.nextBoolean() ? 0 : 1;
        }
        ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
        ArrayList<Integer> CostList = new ArrayList<>();
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card != this.dreamComeTrue ){
                list.add(card);
                CostList.add(card.cost);
            }
        }
        for (int num = 0; num < Shining.allCount/2 + randomNum2; num++) {
            if (list.size() > 0) {
                Collections.shuffle(CostList);
                //梦想成真出牌记得-1
                int maxList;
                if(this.dreamComeTrue != null){
                    maxList = CostList.size() - 1;
                }else {
                    maxList = CostList.size() ;
                }
                for (int i = 0; i < maxList; i++) {
//                    list.get(i).cost = CostList.get(i);
                    list.get(i).costForTurn = CostList.get(i);

                }

                //以上是随机费用
                //以下是随机减费
                ArrayList<Integer> costTmp = new ArrayList<Integer>();
                ArrayList<AbstractCard> finalList = new ArrayList<AbstractCard>();
                for (AbstractCard c : list) {
                    costTmp.add(Integer.valueOf(c.cost));
                }
                int max = ((Integer) Collections.max(costTmp)).intValue();
                for (AbstractCard finalCard : list) {
                    if (finalCard.cost == max && finalCard != this.dreamComeTrue ) {
                        finalList.add(finalCard);
                    }
                }
                Collections.shuffle(finalList);
                AbstractCard tempCard = (AbstractCard) finalList.get(0);
                if(tempCard.costForTurn > 0)
                tempCard.setCostForTurn(tempCard.costForTurn - 1);
//                    addToBot(new BetterAutoPlayCardAction(tempCard, AbstractDungeon.player.drawPile));
//                }
            }
        }

        this.isDone = true;
    }
}

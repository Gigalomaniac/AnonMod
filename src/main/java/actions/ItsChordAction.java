package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.variation;

import java.util.Iterator;

public class ItsChordAction extends AbstractGameAction {
    private DamageInfo info;

    public ItsChordAction(AbstractCreature target, AbstractGameAction.AttackEffect effect) {
        this.info = info;
//        this.setValues(target, info);
//        this.actionType = ActionType.DAMAGE;
//        this.attackEffect = effect;
    }

//    public ItsChordAction(AbstractCreature target, DamageInfo info) {
//        this(target, info, AttackEffect.NONE);
//    }

    public void update() {
        this.isDone = true;
//        if (this.target != null && this.target.currentHealth > 0) {
            int count = 0;
            Iterator var2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();

            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
//                if (c.type == AbstractCard.CardType.ATTACK) {
                    ++count;
//                }
            }

            --count;

//            for(int i = 0; i < count; ++i) {
//                this.addToTop(new DamageAction(this.target, this.info, this.attackEffect));
                addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new variation(AbstractDungeon.player, count), count));
//            }
        }

//    }
}

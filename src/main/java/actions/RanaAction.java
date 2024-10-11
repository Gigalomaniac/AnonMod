package actions;

import cards.uncommon.Pafe;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RanaAction extends AbstractGameAction {
    private AbstractMonster m;

    private DamageInfo info;

    private boolean Upgraded;

    public RanaAction(boolean isUpgraded,AbstractMonster m, DamageInfo info) {
        this.m = m;
        this.info = info;

        this.Upgraded = isUpgraded;
    }

    public void update() {
        addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.m, this.info, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if(Upgraded){
            AbstractCard s = (new Pafe()).makeCopy();
            s.upgrade();
            this.addToBot(new MakeTempCardInDrawPileAction(s, 3, true, true));
        }else {
            this.addToBot(new MakeTempCardInDrawPileAction(new Pafe(), 2, true, true));
        }

        this.isDone = true;
    }
}

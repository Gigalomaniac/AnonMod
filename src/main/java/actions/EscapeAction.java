package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EscapeAction extends AbstractGameAction {
    private AbstractMonster m;

    private DamageInfo info;

    public EscapeAction(AbstractMonster m, DamageInfo info) {
        this.m = m;
        this.info = info;
    }

    public void update() {
        if (this.m != null && (this.m.intent != AbstractMonster.Intent.ATTACK && this.m.intent != AbstractMonster.Intent.ATTACK_BUFF && this.m.intent != AbstractMonster.Intent.ATTACK_DEBUFF && this.m.intent != AbstractMonster.Intent.ATTACK_DEFEND))
        addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.m, this.info, AttackEffect.SLASH_HEAVY));
        addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.m, this.info, AttackEffect.SLASH_HEAVY));
        this.isDone = true;
    }
}

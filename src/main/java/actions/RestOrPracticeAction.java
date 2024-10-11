package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
public class RestOrPracticeAction extends AbstractGameAction {
    private AbstractMonster m;

    private DamageInfo info;

    public RestOrPracticeAction(AbstractMonster m, DamageInfo info) {
        this.m = m;
        this.info = info;
    }

    public void update() {
        if (this.m != null && this.m.intent != Intent.ATTACK && this.m.intent != Intent.ATTACK_BUFF && this.m.intent != Intent.ATTACK_DEBUFF && this.m.intent != Intent.ATTACK_DEFEND){
                addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.m, this.info, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
        addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.m, this.info, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        this.isDone = true;
    }
}

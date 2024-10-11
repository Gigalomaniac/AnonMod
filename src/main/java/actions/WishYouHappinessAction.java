package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.Iterator;

public class WishYouHappinessAction extends AbstractGameAction {

    AbstractCard card;

    private AbstractMonster m;

    private DamageInfo info;

    public WishYouHappinessAction(AbstractMonster m,DamageInfo info) {
        this.m = m;
        this.target = m;
        this.info = info;
    }

    public void update() {
        if(this.target.hasPower("Constricted")){
            this.info.output = this.info.base =this.target.getPower("Constricted").amount;
        }else {
            this.info.output = this.info.base =0;
        }
        addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.m, this.info, AttackEffect.SLASH_HEAVY));
        this.isDone = true;
    }
}

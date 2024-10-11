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
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class AttackDamageRandomEnemyRikkiAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractGameAction.AttackEffect effect;

    public AttackDamageRandomEnemyRikkiAction(AbstractCard card, AbstractGameAction.AttackEffect effect) {
        this.card = card;
        this.effect = effect;
    }

    public AttackDamageRandomEnemyRikkiAction(AbstractCard card) {
        this(card, AttackEffect.NONE);
    }

    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.card.calculateCardDamage((AbstractMonster)this.target);
            if (AttackEffect.LIGHTNING == this.effect) {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage + 2, this.card.damageTypeForTurn), AttackEffect.NONE));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
            } else {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage + 2, this.card.damageTypeForTurn), this.effect));
            }
        }

        this.isDone = true;
    }
}

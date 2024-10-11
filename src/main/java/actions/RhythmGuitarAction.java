//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import java.util.Iterator;

public class RhythmGuitarAction extends AbstractGameAction {
    private DamageInfo info;

    public RhythmGuitarAction(AbstractCreature target, DamageInfo info) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.info = info;
        this.actionType = ActionType.BLOCK;
        this.target = target;
    }

    public void update() {
        this.addToTop(new DamageAction(this.target, this.info, true));
        if (this.target != null && this.target.hb != null) {
            this.addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
        }

        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == CardType.ATTACK) {
                this.addToTop(new DamageAction(this.target, this.info, true));
                if (this.target != null && this.target.hb != null) {
                    this.addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
                }
            }
        }

        this.isDone = true;
    }
}

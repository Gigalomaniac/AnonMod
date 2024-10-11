package actions;

import cards.token.Idea;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import power.Inspiration;

public class MindInflateAction extends AbstractGameAction {

    private boolean freeToPlayOnce;

    private boolean upgraded;

    private AbstractPlayer p;

    private int energyOnUse;

    public MindInflateAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }
    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (this.upgraded)
            effect++;
        if (effect > 0) {
//            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new Inspiration((AbstractCreature)this.p, effect), effect));
            this.addToBot(new MakeTempCardInDrawPileAction(new Idea(), effect, true,true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawCardNextTurnPower(AbstractDungeon.player, effect)));
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}

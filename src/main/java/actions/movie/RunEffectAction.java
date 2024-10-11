package actions.movie;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
public class RunEffectAction extends AbstractGameAction {
    private final AbstractGameEffect effect;
    private final boolean topLevel;

    public RunEffectAction(AbstractGameEffect effect, boolean topLevel) {
        this.effect = effect;
        this.topLevel = topLevel;

        if (topLevel) {
            AbstractDungeon.topLevelEffectsQueue.add(effect);
        } else {
            AbstractDungeon.effectsQueue.add(effect);
        }
    }

    @Override
    public void update() {
        this.isDone = this.effect.isDone;
    }
}

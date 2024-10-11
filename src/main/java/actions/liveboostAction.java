package actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import java.util.ArrayList;
import java.util.Iterator;

public class liveboostAction extends AbstractGameAction {
    private AbstractCard theCard = null;
    float x;
    float y;
    public liveboostAction() {
        x = MathUtils.random(0.1F, 0.9F) * (float)Settings.WIDTH;
        y = MathUtils.random(0.2F, 0.8F) * (float)Settings.HEIGHT;
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED ) {
                ArrayList<AbstractCard> possibleCards = new ArrayList();
                Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    if (c.canUpgrade()) {
                        possibleCards.add(c);
                    }
                }

                if (!possibleCards.isEmpty()) {
                    this.theCard = (AbstractCard)possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
                    this.theCard.upgrade();
                    AbstractDungeon.player.bottledCardUpgradeCheck(this.theCard);
                }
            }

//            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
//                AbstractDungeon.actionManager.clearPostCombatActions();
//            }


        this.tickDuration();
        if (this.isDone && this.theCard != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(x, y));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy(),x, y));
            this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }

    }
}


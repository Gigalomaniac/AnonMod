package actions;

import basemod.helpers.CardModifierManager;
import cardmodifier.AnonIdeaModifier;
import cards.SpecialCard.AIHeart;
import cards.others.BasicSongs;
import cards.others.UntouchableFuture;
import cards.others.WhiteAnonHaruhikageSongs;
import cards.yuzu.YUZUSimplifiedCombo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class exhaustHelpAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private boolean returnColorless = false;
    private AbstractCard.CardType cardType = null;
    private boolean updated = false;
    public exhaustHelpAction(boolean updated) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
        this.updated = updated;
    }


    public void update() {
        ArrayList generatedCards;
        generatedCards = this.generateCardChoices(this.cardType);
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        disCard.upgrade();
                        disCard2.upgrade();
                    }
                    if(this.updated){
                        disCard.upgrade();
                        disCard2.upgrade();
                    }

                    CardModifierManager.addModifier(disCard, new AnonIdeaModifier());

                    disCard.current_x = -1000.0F * Settings.xScale;
                    disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
                    if (this.amount == 1) {
                        if (AbstractDungeon.player.hand.size() < 10) {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        } else {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        }

                        disCard2 = null;
                    } else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard2, (float)Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    } else if (AbstractDungeon.player.hand.size() == 9) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, (float)Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, (float)Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }


    private ArrayList<AbstractCard> generateCardChoices(AbstractCard.CardType type) {
        ArrayList<AbstractCard> derp = new ArrayList();

//        while(derp.size() != 3) {
//            boolean dupe = false;
//            AbstractCard tmp = null;
//            if (type == null) {
//                tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
//            } else {
//                tmp = AbstractDungeon.returnTrulyRandomCardInCombat(type);
//            }
//
//            Iterator var5 = derp.iterator();
//
//            while(var5.hasNext()) {
//                AbstractCard c = (AbstractCard)var5.next();
//                if (c.cardID.equals(tmp.cardID)) {
//                    dupe = true;
//                    break;
//                }
//            }
//
//            if (!dupe) {
//                derp.add(tmp.makeCopy());
//            }
//        }
//        CardModifierManager.addModifier(new UntouchableFuture(), new PhantomCardModifier(new UntouchableFuture()).makeCopy());
        derp.add(new BasicSongs());
        derp.add(new AIHeart());
        derp.add(new WhiteAnonHaruhikageSongs(0));
        derp.add(new UntouchableFuture());
        derp.add(new YUZUSimplifiedCombo());
        return derp;
    }
}

package cardmodifier;


import basemod.abstracts.AbstractCardModifier;
/*    */ import basemod.helpers.CardModifierManager;
/*    */ import basemod.helpers.TooltipInfo;
/*    */
import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

import static utils.AnonSpireKit.addEffect;


 public class AnonIdeaModifier
   extends AbstractCardModifier {
   private static final String SIMPLE_NAME = "IdeaCardModifier";
   public static final String ID = SIMPLE_NAME;
    private static final UIStrings STRINGS = CardCrawlGame.languagePack.getUIString(ID);



   public static boolean check(AbstractCard card) { return CardModifierManager.hasModifier(card, ID); }








   public String modifyDescription(String rawDescription, AbstractCard card) {  return  rawDescription + String.format(STRINGS.TEXT[0]); }










   public void onInitialApplication(AbstractCard card) {
                card.exhaust = true;
                 card.setCostForTurn(0);
}



   public AbstractCardModifier makeCopy() { return new AnonIdeaModifier(); }








   public void atEndOfTurn(AbstractCard card, CardGroup group) {
//     assert check(card);
//     addToBot(new ExhaustSpecificCardAction(card, group));
//     addEffect(new ExhaustCardEffect(card));
   }



   public String identifier(AbstractCard card) { return ID; }




   public boolean shouldApply(AbstractCard card) { return !CardModifierManager.hasModifier(card, ID); }




   public Color getGlow(AbstractCard card) { return new Color(1.0f,221F / 255.0F,136F / 255.0F,1);}
 }


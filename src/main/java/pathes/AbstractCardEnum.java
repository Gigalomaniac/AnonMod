package pathes;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;


public class AbstractCardEnum {
    @SpireEnum
    public static AbstractCard.CardColor Anon_COLOR;
    @SpireEnum(name = "SpeicalAnonCards")
    public static AbstractCard.CardColor LockAnon_COLOR;
}

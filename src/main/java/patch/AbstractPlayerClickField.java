package patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz=AbstractPlayer.class,
        method=SpirePatch.CLASS
)
public class AbstractPlayerClickField {
    public AbstractPlayerClickField() {
    }

    public static SpireField<Boolean> RclickStart = new SpireField<>(() -> false);
    public static SpireField<Boolean> Rclick = new SpireField<>(() -> false);

}

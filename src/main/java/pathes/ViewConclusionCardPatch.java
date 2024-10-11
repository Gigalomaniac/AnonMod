package pathes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import java.util.Iterator;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "render"
)
public class ViewConclusionCardPatch {
    public ViewConclusionCardPatch() {
    }

    @SpirePostfixPatch
    public static void Postfix(AbstractPlayer p, SpriteBatch sb) {
        Iterator var2 = p.orbs.iterator();

        while(var2.hasNext()) {
            AbstractOrb o = (AbstractOrb)var2.next();
            o.render(sb);
        }

    }
}

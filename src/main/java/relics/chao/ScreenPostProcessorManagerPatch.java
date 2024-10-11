package relics.chao;

import basemod.patches.com.megacrit.cardcrawl.core.CardCrawlGame.ApplyScreenPostProcessor;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;

@SpirePatch(clz = ApplyScreenPostProcessor.class, method = "initFrameBuffer")
public class ScreenPostProcessorManagerPatch {
    @SpireInstrumentPatch
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(NewExpr e) throws CannotCompileException {
                if (e.getClassName().equals(FrameBuffer.class.getName())) {
                    e.replace("$_ = $proceed($1, $2, $3, true, $5);");
                }
            }
        };
    }
}

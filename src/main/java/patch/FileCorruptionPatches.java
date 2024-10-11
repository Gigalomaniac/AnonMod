package patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.helpers.AsyncSaver;
import com.megacrit.cardcrawl.helpers.File;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.Arrays;


import static star.RestartRunHelper.queuedRestart;
import static star.RestartRunHelper.queuedScoreRestart;

public class FileCorruptionPatches {
    public static boolean failed = false;
    @SpirePatch2(clz= File.class, method = "copyAndValidate")
    public static class CopyValidationErrorCapture {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch() {
            failed = true;
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Logger.class, "info");
                int[] allMatches = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
                //Copy first two logger infos, which are early returns when retries ran out
                return Arrays.copyOfRange(allMatches, 0, Math.min(2, allMatches.length));
            }
        }
    }

    @SpirePatch2(clz=File.class, method = "save")
    public static class AntiCorruptionRanger {
        @SpirePostfixPatch
        public static void resetFailstate() {
            failed = false;
        }

        @SpireInstrumentPatch
        public static ExprEditor preventBadDelete() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("deleteFile"))
                        m.replace("{ if(!"+FileCorruptionPatches.class.getName()+".failed) {" +
                                        "$proceed($$);" +
                                    "} else {" +
                                        AntiCorruptionRanger.class.getName()+".catcher();" +
                                        //"$proceed($$);" +
                                    "}" +
                                "}");
                }
            };
        }

        public static void catcher() throws InterruptedException {

        }
    }

    @SpirePatch2(clz = AsyncSaver.class, method = "save")
    public static class SaveSynchronuslyPls {
        @SpireInsertPatch(rloc = 1, localvars = {"enableAsyncSave"})
        public static void patch(@ByRef boolean[] enableAsyncSave) {
            //Save synchronously only when quick restarting
            if(queuedScoreRestart || queuedRestart) {
                enableAsyncSave[0] = false;
            }
        }
    }
}

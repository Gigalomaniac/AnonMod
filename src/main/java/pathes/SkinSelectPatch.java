package pathes;

import basemod.ReflectionHacks;
import characters.char_Anon.Enums;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import ui.SkinSelectScreen;
//import ui.SkinSelectScreen;

public class SkinSelectPatch {
    public static boolean isAnonSelected() {
        return (CardCrawlGame.chosenCharacter == ThmodClassEnum.Anon_COLOR && (
                (Boolean) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.charSelectScreen, CharacterSelectScreen.class, "anySelected")).booleanValue());
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class UpdateButtonPatch {
        public static void Prefix(CharacterSelectScreen _inst) {
            if (SkinSelectPatch.isAnonSelected()) {
                SkinSelectScreen.Inst.update();
                if (SkinSelectScreen.shouldUpdateBackground) {
                    _inst.bgCharImg = ImageMaster.loadImage(SkinSelectScreen.getSkinSelectScreenBG);
                    SkinSelectScreen.shouldUpdateBackground = false;
                }
            }
            if (SkinSelectPatch.isAnonSelected() && SkinSelectScreen.shouldUpdateBackground) {
                _inst.bgCharImg = ImageMaster.loadImage(SkinSelectScreen.getSkinSelectScreenBG);
                SkinSelectScreen.shouldUpdateBackground = false;
            }
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
    public static class RenderButtonPatch {
        public static void Postfix(CharacterSelectScreen _inst, SpriteBatch sb) {
            if (SkinSelectPatch.isAnonSelected())
                SkinSelectScreen.Inst.render(sb);
        }
    }

//    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
//    public static class ShouldUpdateBackgroundPatch {
//        private static class Locator
//                extends SpireInsertLocator {
//            public int[] Locate(CtBehavior ctBehavior) throws CannotCompileException, PatchingException {
//                int[] tmp = LineFinder.findInOrder(ctBehavior, new Matcher.MethodCallMatcher(com.megacrit.cardcrawl.characters.AbstractPlayer.class, "doCharSelectScreenSelectEffect"));
//
//                return new int[]{tmp[tmp.length - 1] - 2};
//            }
//        }

//    }
}


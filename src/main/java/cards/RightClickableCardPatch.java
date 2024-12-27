package cards;


import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.ExhaustPileViewScreen;
import java.util.ArrayList;
import java.util.Arrays;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class RightClickableCardPatch {
    public RightClickableCardPatch() {
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "update"
    )
    public static class UpdatePatch {
        public UpdatePatch() {
        }

        public static void Postfix(AbstractCard _inst) {
            if (AbstractDungeon.player != null && _inst instanceof RightClickableCard) {
                if (!AbstractDungeon.player.isDraggingCard) {
                    RightClickableCard clickable = (RightClickableCard)_inst;
                    State state = (State)RightClickableCardPatch.Fields.state.get(_inst);
                    if (state.rightClickStarted && InputHelper.justReleasedClickRight) {
                        ArrayList<RightClickableCard.Position> positions = new ArrayList(Arrays.asList(clickable.clickablePositions()));
                        if (positions.contains(RightClickableCard.Position.HAND) && _inst.isHoveredInHand(0.9F)) {
                            state.rightClick = true;
                        }

                        if (positions.contains(RightClickableCard.Position.DRAW_PILE) && AbstractDungeon.player.drawPile.group.contains(_inst) || positions.contains(RightClickableCard.Position.DISCARD_PILE) && AbstractDungeon.player.discardPile.group.contains(_inst) || positions.contains(RightClickableCard.Position.EXHAUST_PILE) && AbstractDungeon.player.exhaustPile.group.contains(RightClickableCardPatch.Fields.copiedFrom.get(_inst))) {
                            if (_inst.hb.hovered) {
                                state.rightClick = true;
                            }

                            if (state.rightClick) {
                            }
                        }

                        state.rightClickStarted = false;
                    }

                    if (_inst.hb != null && _inst.hb.hovered && InputHelper.justClickedRight) {
                        state.rightClickStarted = true;
                    }

                    if (RightClickableCardPatch.Fields.copiedFrom.get(clickable) != null) {
                        clickable = (RightClickableCard)RightClickableCardPatch.Fields.copiedFrom.get(clickable);
                    }

                    long now = System.currentTimeMillis();
                    if (now - state.lastClick >= 300L && state.doubleClick) {
                        state.doubleClick = false;
                        if (_inst instanceof RightClickableCard && _inst != clickable) {
//                            ((RightClickableCard)_inst).triggeredRightClick = true;
                            ((RightClickableCard)_inst).updateBgImg();
                        }

                        clickable.onRightClick();
                    }

                    if (state.rightClick) {
                        state.rightClick = false;
                        state.doubleClick = true;
                        if (now - state.lastClick < 300L) {
                            clickable.onDoubleRightClick();
                        }

                        state.lastClick = now;
                    }

                }
            }
        }
    }

    @SpirePatch(
            clz = ExhaustPileViewScreen.class,
            method = "open",
            paramtypez = {}
    )
    public static class ExhaustPileViewScreenPatch {
        public ExhaustPileViewScreenPatch() {
        }

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"c", "toAdd"}
        )
        public static void Insert(ExhaustPileViewScreen _inst, AbstractCard c, AbstractCard toAdd) {
            RightClickableCardPatch.Fields.copiedFrom.set(toAdd, c);
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctBehavior) throws CannotCompileException, PatchingException {
                return LineFinder.findInOrder(ctBehavior, new Matcher.MethodCallMatcher(CardGroup.class, "addToBottom"));
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "<class>"
    )
    public static class Fields {
        public static SpireField<State> state = new SpireField(State::new);
        public static SpireField<AbstractCard> copiedFrom = new SpireField(() -> {
            return null;
        });

        public Fields() {
        }
    }

    public static class State {
        public boolean rightClickStarted = false;
        public boolean rightClick = false;
        public boolean doubleClick = false;
        public long lastClick = 0L;

        public State() {
        }
    }
}

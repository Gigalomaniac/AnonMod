package patch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import pathes.PlayerAddFieldsPatch;
import utils.MinionHelper;

import java.lang.reflect.Field;

public class PlayerMethodPatches {
    public PlayerMethodPatches() {
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.core.AbstractCreature",
            method = "updatePowers"
    )
    public static class UpdatePowersPatch {
        public UpdatePowersPatch() {
        }

        public static void Postfix(AbstractCreature _instance) {
            if (_instance instanceof AbstractPlayer) {
                (PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player)).monsters.forEach((monster) -> {
                    monster.updatePowers();
                });
            }
        }
    }

    @SpirePatch(cls = "com.megacrit.cardcrawl.characters.AbstractPlayer", method = "updateSingleTargetInput")
    public static class UpdateSingleTargetInputPatch {
        @SpireInsertPatch(rloc = 14) //1039-1025
        public static void Insert(final AbstractPlayer player) {
            Field field;
            try {
                field = AbstractPlayer.class.getDeclaredField("hoveredMonster");
                field.setAccessible(true);
                for (final AbstractMonster m : MinionHelper.getMinionMonsters()) {
                    m.hb.update();
                    if (m.hb.hovered && !m.isDying && !m.isEscaping &&
                            m.currentHealth > 0) {
                        field.set(player, m);
                        break;
                    }
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.core.AbstractCreature",
            method = "applyStartOfTurnPowers"
    )
    public static class ApplyStartOfTurnPowersPatch {
        public ApplyStartOfTurnPowersPatch() {
        }

        public static void Postfix(AbstractCreature _instance) {
            if (_instance instanceof AbstractPlayer) {
                ((MonsterGroup) PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player)).monsters.forEach((monster) -> {
                    monster.applyStartOfTurnPowers();
                    monster.loseBlock();
                });
                PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player).showIntent();
            }
        }
    }

//    @SpirePatch(cls = "com.megacrit.cardcrawl.monsters.MonsterGroup", method = "showIntent")
//    public static class ShowIntentPatch {
//        @SpirePostfixPatch
//        public static void Postfix(final MonsterGroup group) {
//            if (group != PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player)) {
//                PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player).showIntent();
//
//            }
//        }
//    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.core.AbstractCreature",
            method = "applyStartOfTurnPostDrawPowers"
    )
    public static class ApplyStartOfTurnPostDrawPowersPatch {
        public ApplyStartOfTurnPostDrawPowersPatch() {
        }

        public static void Postfix(AbstractCreature _instance) {
            if (_instance instanceof AbstractPlayer) {
                ((MonsterGroup) PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player)).monsters.forEach((monster) -> {
                    monster.applyStartOfTurnPostDrawPowers();
                });
            }

        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "preBattlePrep"
    )
    public static class PreBattlePatch {
        public PreBattlePatch() {
        }

        public static void Postfix(AbstractPlayer _instance) {
//            BasePlayerMinionHelper.changeMaxMinionAmount(_instance, 100);//(Integer) PlayerAddFieldsPatch.f_baseMinions.get(_instance)
            MinionHelper.clearMinions(_instance);
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "update"
    )
    public static class UpdatePatch {
        public UpdatePatch() {
        }

        public static void Postfix(AbstractPlayer player) {
            if (AbstractPlayerClickField.RclickStart.get(player) && InputHelper.justReleasedClickRight) {
                if (player.hb.hovered) {
                    AbstractPlayerClickField.Rclick.set(player, true);
                }
                AbstractPlayerClickField.RclickStart.set(player, false);
            }
            if ((player.hb != null) && ((player.hb.hovered) && (InputHelper.justClickedRight))) {
                AbstractPlayerClickField.RclickStart.set(player, true);
            }
            if (AbstractPlayerClickField.Rclick.get(player)) {
                AbstractPlayerClickField.Rclick.set(player, false);
                //右击动作
                AbstractDungeon.actionManager.addToBottom(new AnimateJumpAction(player));
            }

            MonsterGroup minions = PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player);
            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (MinionHelper.hasMinions(AbstractDungeon.player)) {
                        minions.update();
                    }
            }

        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "render"
    )
    public static class RenderPatch {
        public RenderPatch() {
        }

        public static void Prefix(AbstractPlayer _instance, SpriteBatch sb) {
            MonsterGroup minions = PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player);
            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (MinionHelper.hasMinions(AbstractDungeon.player)) {
                        minions.render(sb);
                    }
            }
        }
    }

}

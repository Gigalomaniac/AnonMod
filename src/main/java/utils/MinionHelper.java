package utils;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import pathes.PlayerAddFieldsPatch;


import java.util.List;
import java.util.Objects;

public class MinionHelper {
    public MinionHelper() {
    }

    public static MonsterGroup getMinions(AbstractPlayer player) {
        return PlayerAddFieldsPatch.f_minions.get(player);
    }

    public static MonsterGroup getMinions() {
        return PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player);
    }

    public static List<AbstractMonster> getMinionMonsters() {
        return PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player).monsters;
    }

    public static void changeMaxMinionAmount(AbstractPlayer player, int newMax) {
        PlayerAddFieldsPatch.f_maxMinions.set(player, newMax);
    }

    public static boolean addMinion(AbstractPlayer player, AbstractMonster minionToAdd) {
        MonsterGroup minions = PlayerAddFieldsPatch.f_minions.get(player);
        int maxMinions = PlayerAddFieldsPatch.f_maxMinions.get(player);
        if (minions.monsters.size() == maxMinions) {
            return false;
        } else {
            minions.add(minionToAdd);
            minionToAdd.init();
            minionToAdd.usePreBattleAction();
            minionToAdd.showHealthBar();
            return true;
        }
    }

    public static boolean removeMinion(AbstractPlayer player, AbstractMonster minionToRemove) {
        return PlayerAddFieldsPatch.f_minions.get(player).monsters.remove(minionToRemove);
    }

    public static boolean hasMinions(AbstractPlayer player) {
        return PlayerAddFieldsPatch.f_minions.get(player).monsters.size() > 0;
    }

    public static boolean hasMinions() {
        return PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player).monsters.size() > 0;
    }

    public static boolean hasMinion(AbstractMonster m) {
        return PlayerAddFieldsPatch.f_minions.get(AbstractDungeon.player).monsters.contains(m);
    }

    public static int getMaxMinions(AbstractPlayer player) {
        return PlayerAddFieldsPatch.f_maxMinions.get(player);
    }

    public static void clearMinions(AbstractPlayer player) {
        MonsterGroup minions = new MonsterGroup(new AbstractMonster[]{});
        minions.monsters.removeIf(Objects::isNull);
        PlayerAddFieldsPatch.f_minions.set(player, minions);
    }
}
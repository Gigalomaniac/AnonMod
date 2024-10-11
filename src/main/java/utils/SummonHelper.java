package utils;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class SummonHelper {
    public SummonHelper() {
    }

    public static void summonMinion(AbstractMonster monster) {
        boolean canNotLose = AbstractDungeon.getCurrRoom().cannotLose;
        MinionHelper.addMinion(AbstractDungeon.player, monster);
        AbstractDungeon.getCurrRoom().cannotLose = canNotLose;
    }
}
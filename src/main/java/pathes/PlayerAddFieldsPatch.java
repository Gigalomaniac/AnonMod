package pathes;


import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
        method = "<class>"
)
public class PlayerAddFieldsPatch {
    private static Integer maxMinions = Integer.MAX_VALUE;
    private static MonsterGroup minions = new MonsterGroup(new AbstractMonster[]{});
    public static SpireField<Integer> f_maxMinions = new SpireField<>(() -> maxMinions);
    public static SpireField<MonsterGroup> f_minions =  new SpireField<>(() -> minions);

    public PlayerAddFieldsPatch() {
    }
}
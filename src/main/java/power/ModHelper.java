package power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ModHelper {
    public static void addToBotAbstract(Lambda func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    public static void addToTopAbstract(Lambda  func) {
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }
//    你发现卡牌ID每张都要写前缀太麻烦了。
//
//            > 02 - 添加新卡牌<br>为了和其他mod区分开来，你需要在ID之前加上你的modid前缀。
//
//    为了偷懒，你可以写一个帮手方法。首先创建一个帮手类。
//
//            * modcore
//* cards
//* helpers
//    * ModHelper.java <-这里
//
//    ModHelper.java:
//            ```java
        public static String makePath(String id) {
            return "ExampleMod:" + id;
        }
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        if (m != null) {
//            this.addToBot(new VFXAction(new PressurePointEffect(m.hb.cX, m.hb.cY)));
//        }
//        this.addToBot(new ApplyPowerAction(m, p, new MarkPower(m, this.magicNumber), this.magicNumber));
//        // 这里
//        ModHelper.addToBotAbstract(() -> {
//            if (m.hasPower(MarkPower.POWER_ID))
//                addToTop(new GainBlockAction(p, p, m.getPower(MarkPower.POWER_ID).amount));
//        });
//    }

    public interface Lambda extends Runnable {}
}
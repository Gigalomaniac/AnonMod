package utils;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AnonUtils {

    public static Point getCirclePoint(Point point, double angle, double r) {
        double x = point.x + r * Math.cos(angle);
        double y = point.y + r * Math.sin(angle);
        return new Point(x, y);
    }

    /**
     * 查询之前有几个伙伴遗物了
     *  int trueCount = countTrue(new boolean[]{a, b, c, d, e, f});
     *         System.out.println("True的数量: " + trueCount);
     * @param bools
     * @return
     */
    public static int countTrue(boolean... bools) {
        int count = 0;
        for (boolean value : bools) {
            if (value) {
                count++;
            }
        }
        return count;
    }
    public static double[] bandFriendsLocation(int i) {
        float a = Settings.scale;
        switch (i) {
            case 0:
                return new double[]{AbstractDungeon.player.hb.x - 960 - 150*a, AbstractDungeon.player.hb.y + 450 - 450 * a};

            case 1:
                return new double[]{AbstractDungeon.player.hb.x - 960 - 150*a, AbstractDungeon.player.hb.y -510*a +150};
            case 2:
                return new double[]{AbstractDungeon.player.hb.x - 1200-450*a, AbstractDungeon.player.hb.y + 450 - 450 * a};
            case 3:
                return new double[]{AbstractDungeon.player.hb.x - 1200-450*a, AbstractDungeon.player.hb.y  -510*a +150};
        }
        return new double[]{0.0, 0.0};
    }

}

package relics;
import BandFriends.Tomori;
import basemod.abstracts.CustomRelic;
import bossRoom.AnonSide;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import utils.Point;
import utils.SummonHelper;
import utils.AnonUtils;

import static utils.AnonUtils.countTrue;


public class Tomori_relic extends CustomRelic {
    public static final String ID = "Tomori_relic";
    private static final String IMG = "img/relics/TomoriRecli.png";
    private static final String IMG_OTL = "img/relics/TomoriRecli.png";

    public int getTurn ;

    public int summonTurn ;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public Tomori_relic() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在用户使用牌时触发
    }

    @Override
    public void onVictory() {
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new Tomori_relic();
    }

    public void atBattleStart() {
        summonTurn = getTurn = this.counter-1;
        flash();
    }
    public void atBattleStartPreDraw() {
        this.addToBot(new MakeTempCardInHandAction(new bandFriendsCard.Tomori(), 1, false));
    }
    public void onEquip() {
        summonTurn = getTurn = countTrue(new boolean[]{AbstractDungeon.player.hasRelic("Sakiko_relic")
                , AbstractDungeon.player.hasRelic("Soyorin_relic")
                , AbstractDungeon.player.hasRelic("Rikki_relic")
                , AbstractDungeon.player.hasRelic("Rana_relic")
                , AbstractDungeon.player.hasRelic("Mutsumi_relic")});
        this.counter=summonTurn +1;
    }

    public void atTurnStart() {
        if (AnonSide.finInfo == 0) {
            if (getTurn == 0) {
                AbstractCard s = (new bandFriendsCard.Tomori()).makeCopy();
                s.upgrade();
                s.costForTurn =0;
                this.addToBot(new MakeTempCardInHandAction(s, 1));
                flash();
                Point center = new Point(AnonUtils.bandFriendsLocation(summonTurn)[0], AnonUtils.bandFriendsLocation(summonTurn)[1]);
                double angle = 0;
                Point point = AnonUtils.getCirclePoint(center, angle, 100);
                Tomori Tomori = new Tomori((float) point.x, (float) point.y);
                SummonHelper.summonMinion(Tomori);
                getTurn = -1;
            } else {
                getTurn = getTurn - 1;
            }
        }
    }
}

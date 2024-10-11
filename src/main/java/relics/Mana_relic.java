package relics;

import BandFriends.Mutsumi;
import basemod.abstracts.CustomRelic;
import bossRoom.AnonSide;
import characters.char_Anon;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import utils.AnonUtils;
import utils.Point;
import utils.SummonHelper;

import static utils.AnonUtils.countTrue;


public class Mana_relic extends CustomRelic {
    public static final String ID = "Mana_relic";
    private static final String IMG = "img/relics/mana.png";
    private static final String IMG_OTL = "img/relics/mana.png";

    public int getTurn ;

    public int summonTurn ;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public Mana_relic() {
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
        return (AbstractRelic)new Mana_relic();
    }

    public void atBattleStart() {
        summonTurn = getTurn = this.counter-1;
        flash();
    }
    public void atBattleStartPreDraw() {
        this.addToBot(new MakeTempCardInHandAction(new bandFriendsCard.Mana(), 1, false));
    }
    public void onEquip() {
        summonTurn = getTurn = 3;
        this.counter=summonTurn +1;
        char_Anon k = (char_Anon)AbstractDungeon.player;
        k.AshAnonAwakeRefreshSkin();

    }

    public void atTurnStart() {
//        if(AnonSide.finInfo == 0){
//            if(getTurn == 0){
//                AbstractCard s = (new bandFriendsCard.Mutsumi()).makeCopy();
//                s.upgrade();
//                s.cost =0;
//                this.addToBot(new MakeTempCardInHandAction(s, 1));
//                flash();
//                Point center = new Point(AnonUtils.bandFriendsLocation(summonTurn)[0], AnonUtils.bandFriendsLocation(summonTurn)[1]);
//                double angle = 0;
//                Point point = AnonUtils.getCirclePoint(center, angle, 100);
//                Mutsumi Mutsumi = new Mutsumi((float) point.x, (float) point.y);
//                SummonHelper.summonMinion(Mutsumi);
//                getTurn = -1;
//            }else {
//                getTurn = getTurn -1;
//            }
//        }
    }
}

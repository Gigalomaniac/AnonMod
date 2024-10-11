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
import utils.AnonUtils;
import utils.Point;
import utils.SummonHelper;

import static utils.AnonUtils.countTrue;


public class EnergyBlessing extends CustomRelic {
    public static final String ID = "EnergyBlessing";
    private static final String IMG = "img/relics/star.png";
    private static final String IMG_OTL = "img/relics/star.png";

    public int getTurn ;

    public int summonTurn ;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public EnergyBlessing() {
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
        return (AbstractRelic)new EnergyBlessing();
    }

    public void atBattleStart() {

    }
    public void atBattleStartPreDraw() {

    }
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        ++AbstractDungeon.player.energy.energyMaster;
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
        --AbstractDungeon.player.energy.energyMaster;
        --AbstractDungeon.player.energy.energyMaster;
    }
}

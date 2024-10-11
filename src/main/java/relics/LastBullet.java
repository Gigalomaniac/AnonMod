package relics;

import BandFriends.Soyorin;
import basemod.abstracts.CustomRelic;
import bossRoom.AnonSide;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import utils.AnonUtils;
import utils.Point;
import utils.SummonHelper;

import static utils.AnonUtils.countTrue;


public class LastBullet extends CustomRelic {
    public static final String ID = "LastBullet";
    private static final String IMG = "img/relics/commiseration.png";
    private static final String IMG_OTL = "img/relics/commiseration.png";


    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public LastBullet() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.CLINK);
        PowerTip tip = new PowerTip();
        tip.header="@STSLIB:FLAVOR@";
        tip.body="design by 爱音A杯冠军： NL --冰水--";
        FlavorText.PowerTipFlavorFields.boxColor.set(tip, CardHelper.getColor(255, 136, 153));
        this.tips.add(tip);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if(this.counter < 600) {
            this.counter++;
        }else {
            AbstractDungeon.player.maxHealth = 1;
            if(AbstractDungeon.player.currentHealth>1 )
                AbstractDungeon.player.currentHealth = 1;
        }

    }
    @Override
    public void onVictory() {
    }
    public void onEquip() {
        this.counter = 0;
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new LastBullet();
    }

    public void atBattleStart() {
    }
    public void atBattleStartPreDraw() {
    }

    public void atTurnStart() {

    }
}

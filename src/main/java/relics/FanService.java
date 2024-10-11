package relics;

import actions.liveboostAction;
import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.*;


public class FanService extends CustomRelic {
    public static final String ID = "FanService";
    private static final String IMG = "img/relics/Sumimi.png";
    private static final String IMG_OTL = "img/relics/Sumimi.png";


    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public FanService() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.CLINK);
        PowerTip tip = new PowerTip();
        tip.header="@STSLIB:FLAVOR@";
        tip.body="Design by 爱音B杯冠军 NL  ---Asahi---";
        FlavorText.PowerTipFlavorFields.boxColor.set(tip, CardHelper.getColor(255, 136, 153));
        this.tips.add(tip);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

            this.counter++;
    }
    @Override
    public void onVictory() {
        if(this.counter<26){
            upgradeCards(1);
        }
    }

    private void upgradeCards(int num) {
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        ArrayList<AbstractCard> upgradableCards = new ArrayList();
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }

        List<String> cardMetrics = new ArrayList();
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            if (upgradableCards.size() == 1) {
                ((AbstractCard) upgradableCards.get(0)).upgrade();
                cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(0));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy()));
            } else {
                if (num == 1) {
                    ((AbstractCard) upgradableCards.get(0)).upgrade();
                    cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                    AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(0));
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                } else {
                    ((AbstractCard) upgradableCards.get(0)).upgrade();
                    ((AbstractCard) upgradableCards.get(1)).upgrade();
                    cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                    cardMetrics.add(((AbstractCard) upgradableCards.get(1)).cardID);
                    AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(0));
                    AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard) upgradableCards.get(1));
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(1)).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                }
            }
        }
    }

    public void onEquip() {
        this.counter = 0;

    }

    public void onUnequip() {
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new FanService();
    }

    public void atBattleStart() {
        this.counter = 0;
    }
    public void atBattleStartPreDraw() {
    }

    public void atTurnStart() {

    }
}

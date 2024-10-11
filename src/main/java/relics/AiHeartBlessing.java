package relics;

import actions.exhaustDiscoveryAction;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.Iterator;


public class AiHeartBlessing extends CustomRelic {
    public static final String ID = "AiHeartBlessing";
    private static final String IMG = "img/relics/machineheart.png";
    private static final String IMG_OTL = "img/relics/machineheart.png";

    public int getTurn ;

    public int summonTurn ;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public AiHeartBlessing() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {

    }
    public void atStartOfTurn() {

    }
    @Override
    public void onVictory() {

    }
    public void onEnergyRecharge() {
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new ArtifactPower(AbstractDungeon.player, 3), 3));
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new AiHeartBlessing();
    }

    public void atBattleStart() {

    }
    public void atBattleStartPreDraw() {

    }
    public void onEquip() {
        Iterator var1 = AbstractDungeon.combatRewardScreen.rewards.iterator();

        while(true) {
            RewardItem reward;
            do {
                if (!var1.hasNext()) {
                    return;
                }

                reward = (RewardItem)var1.next();
            } while(reward.cards == null);

            Iterator var3 = reward.cards.iterator();

            while(var3.hasNext()) {
                AbstractCard c = (AbstractCard)var3.next();
                this.onPreviewObtainCard(c);
            }
        }
    }
    public void onPreviewObtainCard(AbstractCard c) {
        this.onObtainCard(c);
    }
    public void onObtainCard(AbstractCard c) {
        if (c.canUpgrade() && !c.upgraded) {
            c.upgrade();
        }

    }
}

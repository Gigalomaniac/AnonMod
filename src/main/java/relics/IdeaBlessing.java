package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
import potions.IdeaPotion;


public class IdeaBlessing extends CustomRelic {
    public static final String ID = "IdeaBlessing";
    private static final String IMG = "img/relics/huya.png";
    private static final String IMG_OTL = "img/relics/huya.png";

    public int getTurn ;

    public int summonTurn ;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public IdeaBlessing() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在用户使用牌时触发
    }

    @Override
    public void onVictory() {
        for(int i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
            AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(new IdeaPotion()));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new IdeaBlessing();
    }

    public void atBattleStart() {

    }
    public void atBattleStartPreDraw() {

    }
    public void onEquip() {

    }


}

package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import pathes.AbstractCardEnum;
import power.Inspiration;
import power.Shining;

/**
 * 练习或休息
 */
public class LongTimeVagetarian extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("LongTimeVagetarian");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "LongTimeVagetarian";
    public static final String IMG_PATH = "img/card_Anon/amWe_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public LongTimeVagetarian() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseMagicNumber = Inspiration.AllCount;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < baseMagicNumber + 1 ; i ++){
            this.addToBot(new VFXAction(new BorderFlashEffect(Color.CHARTREUSE, true)));
            this.addToBot(new VFXAction(p, new MiracleEffect(Color.CHARTREUSE, Color.LIME, "BLOCK_GAIN_1"), 1.0F));
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.baseBlock), this.baseBlock));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new LongTimeVagetarian();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void applyPowers() {
        this.baseMagicNumber = Inspiration.AllCount;
        this.magicNumber = this.baseMagicNumber;
    }
}

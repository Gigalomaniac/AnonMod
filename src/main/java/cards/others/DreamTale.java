package cards.others;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import power.Inspiration;
import pathes.AbstractCardEnum;

/*
 * 能力卡模板
 * 功能为类似燃烧
   获得两点力量和敏捷 升级后(3,3)
 * */
public class DreamTale extends CustomCard {
    public static final String ID = "DreamTale";
    public static final String IMG_PATH = "img/cards_Seles/MyInflame.png";
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME="测试用dreamtale";
    public static final String DESCRIPTION="不受节奏的影响";
    //花费
    private static final int COST = 0;

    public DreamTale() {
        super(ID, cardStrings.NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        addToBot(new ApplyPowerAction(p, p, new Inspiration(p,1), this.magicNumber));
//        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new DreamTale();
    }
}

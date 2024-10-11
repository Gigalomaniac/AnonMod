package cards.yuzu;

import basemod.abstracts.CustomCard;
import cards.YUZUCustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.WholeLifeBandPower;
import power.yuzu.YUZUImagePower;


/**
 * 鲁莽结束
 */
public class image extends YUZUCustomCard {
    public static final String ID= "yuzu_Image";
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH="img/card_Anon/Image.png";
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= AbstractCardEnum.LockAnon_COLOR;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.SPECIAL;

    public image() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=22;
        this.selfRetain=true;
//        this.setBackgroundTexture("img/512/yuzu_bg_power_512.png","img/1024/yuzu_bg_power_1024.png");
    }

    @Override
    protected void upgradeMethod() {
        upgradeMagicNumber(8);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AddTemporaryHPAction(abstractPlayer,abstractMonster,this.magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUImagePower(abstractPlayer)));
    }

}

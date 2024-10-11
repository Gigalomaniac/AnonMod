package cards.yuzu;


import cards.YUZUCustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class YUZUClimaxOption extends YUZUCustomCard {
    public static final String ID= "yuzu_ClimaxOption";
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH="img/card_Anon/Climax.png";
    private static final int COST=-2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= AbstractCardEnum.LockAnon_COLOR;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.SPECIAL;
    private int position;

    public YUZUClimaxOption(int position, int magicNum) {
        super(ID, NAME, IMG_PATH, COST, CARD_STRINGS.EXTENDED_DESCRIPTION[position], TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=magicNum;
        this.position=position;
//        this.setBackgroundTexture("img/512/yuzu_bg_skill_512.png","img/1024/yuzu_bg_skill_1024.png");
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
    }

    @Override
    protected void upgradeMethod() {

    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void onChoseThisOption() {
        switch (this.position){
            case 0:
                addToBot(new DrawCardAction(this.magicNumber));
                break;
            case 1:
                addToBot(new GainEnergyAction(this.magicNumber));
                break;
            default:
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YUZUClimaxOption(this.position,this.baseMagicNumber);
    }
}

package cards.yuzu;

import cards.YUZUCustomCard;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import java.util.ArrayList;

public class YUZUClimax extends YUZUCustomCard {
    public static final String ID= "yuzu_Climax";
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= "img/card_Anon/Climax.png";
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= AbstractCardEnum.LockAnon_COLOR;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.SPECIAL;

    public YUZUClimax() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.setBackgroundTexture("img/512/yuzu_bg_skill_512.png","img/1024/yuzu_bg_skill_1024.png");
        this.baseMagicNumber=this.magicNumber=0;
        this.isEthereal=true;
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
    }

    @Override
    protected void upgradeMethod() {
//        this.upgradeBaseCost(0);
        this.isEthereal=false;
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> options=new ArrayList<>();
        for(int j=0;j<2;j++){
            options.add(new YUZUClimaxOption(j,this.magicNumber));
        }
        addToBot(new ChooseOneAction(options));
    }

    @Override
    public void triggerWhenDrawn() {
        this.baseMagicNumber++;
        this.magicNumber=this.baseMagicNumber;
    }
}

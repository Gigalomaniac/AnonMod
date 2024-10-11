package cards.yuzu;


import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import cards.YUZUCustomCard;
import cards.others.Anon_Def;
import cards.others.Anon_Strike;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

public class YUZUSimplifiedCombo extends YUZUCustomCard {
    public static final String ID= "yuzu_SimplifiedCombo";
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH="img/card_Anon/SimplifiedCombo_skill.png";
    private static final int COST=-1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= AbstractCardEnum.LockAnon_COLOR;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.SPECIAL;

    public YUZUSimplifiedCombo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=2;
        MultiCardPreview.add(this,new Anon_Strike());
        MultiCardPreview.add(this,new Anon_Def());
        this.exhaust=true;
//        this.setBackgroundTexture("img/512/yuzu_bg_skill_512.png","img/1024/yuzu_bg_skill_1024.png");
        this.setBackgroundTexture("img/pink/512/bg_skill.png","img/pink/1024/bg_skill.png");
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        BiFunction<Integer, int[], Boolean> function= (integer, ints) -> {
            AbstractCard strike=new Anon_Strike();
            AbstractCard defend=new Anon_Def();
            if(integer>0){
                strike.upgraded=true;
                defend.upgraded=true;
                strike.name=strike.name+"+"+integer;
                defend.name=defend.name+"+"+integer;
                for(int i=0;i<integer;i++){
                    strike.baseDamage += 3;
                    strike.upgradedDamage = true;
                    defend.baseBlock+=3;
                    defend.upgradedBlock=true;
                }
            }
            addToTop(new YUZUSimplifiedComboAction(new ArrayList<>(Arrays.asList(strike,defend)),ints[0]));
            return true;
        };
        addToBot(new EasyXCostAction(this,function,this.magicNumber));
    }
}

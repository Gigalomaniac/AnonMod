package cards.others;

import Mod.AnonCardSignStrings;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import cards.token.*;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import java.util.ArrayList;

/**
 * 备注: 凝神，0费，技能牌，获得3层活力（升级后为5层）
 */
public class SoloConcertSongs extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SoloConcertSongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "SoloConcertSongs";
    public static final String IMG_PATH = "img/card_Anon/star/SoloConcertSongs_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public SoloConcertSongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.BASIC, CardTarget.NONE);
        //初始为3层活力
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
        this.selfRetain = false;
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(135, 206, 235));
        this.setBackgroundTexture("img/1024/bg_skill_star_512.png","img/1024/bg_skill_star.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new YouthfulBeautifulSongs());
        stanceChoices.add(new LackOfVitalitySongs());
        stanceChoices.add(new HekitenbansouStarSongs());
        if (this.upgraded)
            for (AbstractCard c : stanceChoices)
                c.upgrade();
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new SoloConcertSongs();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}

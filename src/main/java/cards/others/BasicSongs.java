package cards.others;

import basemod.abstracts.CustomCard;
import cards.token.HekitenbansouSongs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import cards.token.NamonakiSongs;
import cards.token.SenzaihyoumeiSongs;

import java.util.ArrayList;

/**
 * 备注: 凝神，0费，技能牌，获得3层活力（升级后为5层）
 */
public class BasicSongs extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BasicSongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "BasicSongs";
    public static final String IMG_PATH = "img/card_Anon/jichuyinle_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public BasicSongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.NONE);
        //初始为3层活力
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = false;
        this.selfRetain = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new NamonakiSongs());
        stanceChoices.add(new SenzaihyoumeiSongs());
        stanceChoices.add(new HekitenbansouSongs());
        if (this.upgraded)
            for (AbstractCard c : stanceChoices)
                c.upgrade();
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new BasicSongs();
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

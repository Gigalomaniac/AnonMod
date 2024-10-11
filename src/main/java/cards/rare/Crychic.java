package cards.rare;

import actions.AttackDamageRandomEnemyRikkiAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import pathes.AbstractCardEnum;
import power.EnemyHeavy;
import power.heavy;
import power.songs;

/**
 * 每一个瞬间的积累
 */
public class Crychic extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Crychic");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    public static final String ID = "Crychic";
    public static final String IMG_PATH = "img/card_Anon/Crychicdeyinying_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Crychic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int entangle = 0;
        int  heavy = 0;
        if (p.hasRelic("Tomori_relic")) {
            entangle += 1;
            heavy += 1;
        }
        if (p.hasRelic("Soyorin_relic")) {
            entangle += 1;
            heavy += 1;
        }
        if (p.hasRelic("Rikki_relic")) {
            entangle += 1;
            heavy += 1;
        }
        if (p.hasRelic("Rana_relic")) {
            heavy += 1;
        }
        if (p.hasRelic("Sakiko_relic")) {
            entangle += 1;
        }
        if (p.hasRelic("Mutsumi_relic")) {
            entangle += 1;
        }
        this.addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, entangle * 3), entangle * 3, AbstractGameAction.AttackEffect.POISON));
        this.addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new EnemyHeavy((AbstractCreature) m, heavy*3), heavy*3, true, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Crychic();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}

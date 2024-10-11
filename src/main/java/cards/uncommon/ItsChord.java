package cards.uncommon;

import actions.ItsChordAction;
import basemod.abstracts.CustomCard;
import cards.token.Idea;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DamagePerAttackPlayedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.songs;

import java.util.Iterator;

/**
 * 每一个瞬间的积累
 */
public class ItsChord extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ItsChord");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "ItsChord";
    public static final String IMG_PATH = "img/card_Anon/hexian_attack.png";
    public static  int songsNum = 1;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public ItsChord() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        //初始为3层活力
        this.baseDamage = 3;
        this.isEthereal = true;
//        this.cardsToPreview = (AbstractCard)new Idea();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ItsChordAction(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }
    public void applyPowers() {
        int count = 0;
        Iterator var2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
//            if (c.type == CardType.ATTACK) {
            ++count;
//            }
        }
        if(upgraded){
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        }else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        this.initializeDescription();
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发

        return (AbstractCard)new ItsChord();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            this.upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    /**
     * 移除数组的第一个元素，并用空字符串填充，保持数组长度为10。
     *
     * @param array 原始的字符串数组
     * @return 修改后的字符串数组
     */
    public static String[] removeFirstAndPadWithEmpty(String[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        // 创建一个新的数组，长度不变，用于存放移除首个元素后的数组和补充的空字符串
        String[] newArray = new String[array.length];

        // 将原数组中除第一个元素外的所有元素复制到新数组中
        System.arraycopy(array, 1, newArray, 0, array.length - 1);

        // 末尾补上空字符串以保持数组长度为10
        newArray[array.length - 1] = "";

        return newArray;
    }
}

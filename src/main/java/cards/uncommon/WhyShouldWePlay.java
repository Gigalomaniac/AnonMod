package cards.uncommon;

import bandFriendsCard.Rikki;
import basemod.abstracts.CustomCard;
import cards.token.Idea;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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

/**
 * 每一个瞬间的积累
 */
public class WhyShouldWePlay extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WhyShouldWePlay");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "WhyShouldWePlay";
    public static final String IMG_PATH = "img/card_Anon/weishenmeyaoyanzouchunriying  (zenmenengbujiezhezhangne)_attack.png";
    public static  int songsNum = 1;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WhyShouldWePlay() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        //初始为3层活力
        this.baseDamage = 7;
        this.cardsToPreview = (AbstractCard)new Idea();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToTop(new MakeTempCardInHandAction(new Idea(), 1));
        if (!songs.SongsList[0].equals("")){
        if(songs.SongsList[0].equals("春日影")){
            songs.SongsList =  removeFirstAndPadWithEmpty(songs.SongsList);
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "春日影"), this.magicNumber));
        }else {
            songs.SongsList =  removeFirstAndPadWithEmpty(songs.SongsList);
        }
}

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发

        return (AbstractCard)new WhyShouldWePlay();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);

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

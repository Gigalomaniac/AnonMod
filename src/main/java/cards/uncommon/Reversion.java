package cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import pathes.AbstractCardEnum;
import power.Shining;
import power.musicStart;
import power.notLive;
import power.songs;

/**
 * 每一个瞬间的积累
 */
public class Reversion extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reversion");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "Reversion";
    public static final String IMG_PATH = "img/card_Anon/Gavinners_skill.png";
    public static  int songsNum = 0;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Reversion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //初始为3层活力
        this.baseBlock = 0;
//        this.magicNumber = this.baseMagicNumber;
//        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0  ; i < 10 ;i ++){
            if(songs.SongsList[i].equals("")){
                this.baseBlock = songsNum = i;
                break;
            }
        }

        reverseFirstNElements(songs.SongsList, songsNum);
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.baseBlock), this.baseBlock));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.baseBlock), this.baseBlock));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.baseBlock), this.baseBlock));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.baseBlock), this.baseBlock));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Reversion();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
    public static void reverseFirstNElements(String[] array, int n) {
        n = Math.min(n, array.length);
        for (int i = 0; i < n / 2; i++) {

            String temp = array[i];
            array[i] = array[n - 1 - i];
            array[n - 1 - i] = temp;
        }
    }
    public void applyPowers() {
        for(int i = 0  ; i < 10 ;i ++){
            if(songs.SongsList[i].equals("")){
                this.baseBlock = songsNum = i;
                break;
            }
        }

    }
}

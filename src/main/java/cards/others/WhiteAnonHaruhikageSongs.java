package cards.others;

import BandFriends.Soyorin;
import basemod.abstracts.CustomCard;
import bossRoom.AnonSide;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.Shining;
import power.songs;
import relics.Soyorin_relic;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * 每一个瞬间的积累
 */
public class WhiteAnonHaruhikageSongs extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WhiteAnonHaruhikageSongs");
    public static final String NAME = cardStrings.NAME;
    public static String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    public static final String ID = "WhiteAnonHaruhikageSongs";
    public static final String IMG_PATH = "img/card_Anon/White/haruhikage-Fiona_skill_skill.png";
    public int hasUpgrades;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WhiteAnonHaruhikageSongs(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isEthereal = true;
        this.timesUpgraded = upgrades;
        hasUpgrades = this.timesUpgraded;
        this.setBackgroundTexture("img/pink/test/bg_skill_white_512.png","img/pink/test/bg_skill_white.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 5));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, this.name), 1));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, 1));


    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new WhiteAnonHaruhikageSongs(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (this.timesUpgraded == 0) {
            this.name = "隔世遥望的春日影";
            this.timesUpgraded++;
            this.isEthereal = false;
            this.upgradeBaseCost(0);
            hasUpgrades = this.timesUpgraded;
            this.DESCRIPTION = "消耗5点生命值。抽一张卡。演奏：春日影（Fiona.ver）。演奏开始时，获得1层无实体，赋予敌我全场易伤2层，获得1点闪耀。这张卡可以无视节奏。 保留 ， 消耗 。";
//            this.upgraded = true;
        }else {
        if (this.timesUpgraded == 1) {
            this.name = "魂牵梦萦的春日影";
            this.timesUpgraded++;
            this.upgraded = true;
            this.upgradeBaseCost(0);
            this.isEthereal = false;
            this.selfRetain =true;
            this.DESCRIPTION = "消耗5点生命值。抽一张卡。演奏：春日影（Fiona.ver）。演奏开始时，获得1层无实体，赋予敌我全场易伤2层，再获得1点闪耀，战斗开始时获得1点重力（每场限1）。这张卡可以无视节奏。 保留 ， 消耗 。";
            hasUpgrades = this.timesUpgraded;
        }
        }
        initializeTitle();
    }
    public void initializeDescription() {
        super.initializeDescription();
        for (DescriptionLine tmp : this.description) {
            try {
                Field text = tmp.getClass().getDeclaredField("text");
                text.setAccessible(true);
                String str = (String)text.get(tmp);
                String updateTmp = "";
                updateTmp = updateTmp + "[#000000]";
                updateTmp = updateTmp + str;
//                    updateTmp = updateTmp + "[]";
                text.set(tmp, updateTmp);
            } catch (NoSuchFieldException|IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}

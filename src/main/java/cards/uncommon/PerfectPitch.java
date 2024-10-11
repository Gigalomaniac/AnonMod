package cards.uncommon;

import actions.LooseGoldAction;
import basemod.abstracts.CustomCard;
import cards.token.ChangeToAttackSongs;
import cards.token.ChangeToPowerSongs;
import cards.token.ChangeToSkillSongs;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

import java.util.ArrayList;

/**
 * 练习或休息
 */
public class PerfectPitch extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("PerfectPitch");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "PerfectPitch";
    public static final String IMG_PATH = "img/card_Anon/change_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public PerfectPitch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //初始为3层活力
        this.baseBlock = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new ChangeToAttackSongs());
        stanceChoices.add(new ChangeToSkillSongs());
        stanceChoices.add(new ChangeToPowerSongs());
////        if (this.upgraded)
////            for (AbstractCard c : stanceChoices)
////                c.upgrade();
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
        if(upgraded){
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new PerfectPitch();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(c.cardID.contains("Songs") && !c.cardID.equals("ChangeStateSongs") &&!c.cardID.equals("PerfectPitchSongs")){
            this.addToBot(new DiscardToHandAction(this));
        }
    }
}

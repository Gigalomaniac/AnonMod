package cards.rare;

import bandFriendsCard.*;
import basemod.abstracts.CustomCard;
import cards.token.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import relics.Mana_relic;

import java.util.ArrayList;

/**
 * 爆了
 */
public class AnonTokyo extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("AnonTokyo");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 4;
    public static final String ID = "AnonTokyo";
    public static final String IMG_PATH = "img/card_Anon/ANON TOKYO_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public AnonTokyo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.SELF);
        //初始为3层活力
        this.baseDamage = 5;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasRelic("Tomori_relic")){
            this.addToTop(new MakeTempCardInHandAction(new Tomori(), 1));
        }
        if(AbstractDungeon.player.hasRelic("Soyorin_relic")){
            this.addToTop(new MakeTempCardInHandAction(new Soyorin(), 1));
        }
        if(AbstractDungeon.player.hasRelic("Rikki_relic")){
            this.addToTop(new MakeTempCardInHandAction(new Rikki(), 1));
        }
        if(AbstractDungeon.player.hasRelic("Rana_relic")){
            this.addToTop(new MakeTempCardInHandAction(new Rana(), 1));
        }
        if(AbstractDungeon.player.hasRelic("Sakiko_relic")){
            this.addToTop(new MakeTempCardInHandAction(new Sakiko(), 1));
        }
        if(AbstractDungeon.player.hasRelic("Mutsumi_relic")){
            this.addToTop(new MakeTempCardInHandAction(new Mutsumi(), 1));
        }
        if(AbstractDungeon.player.hasRelic(Mana_relic.ID)){
            this.addToTop(new MakeTempCardInHandAction(new Mana(), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new AnonTokyo();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(3);
            upgradeName();
        }
    }


}

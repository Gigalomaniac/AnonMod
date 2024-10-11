package bandFriendsCard;

import actions.RanaAction;
import basemod.abstracts.CustomCard;
import cards.uncommon.Pafe;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.Shining;
import power.musicStart;
import power.notLive;


public class Mana extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Mana");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "Mana";
    public static final String IMG_PATH = "img/bandFriends/83c1fb1a8f22506277436bb58b9f601c867fb5d8.jpg_1256w_1024h__web-article-pic_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Mana() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        //初始为3层活力
        this.baseDamage = 10;
        this.magicNumber = 2;
        this.exhaust = true;
        this.selfRetain =true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(notLive.POWER_ID)){
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(p, p, notLive.POWER_ID));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new Shining(p,1), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new musicStart(p), 1));
        addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//        if (Settings.language == Settings.GameLanguage.ZHS)
//            addToBot((AbstractGameAction) new TalkAction(true, "Live要开始了哦！", 1.0F, 2.0F));
        musicStart.ifStart = 0;
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}

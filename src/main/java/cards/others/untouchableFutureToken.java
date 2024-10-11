package cards.others;

import Mod.AnonCardSignStrings;
import actions.SkipEnemiesTurnFalseAction;
import cards.SpecialAnonCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pathes.AbstractCardEnum;
import power.heavy;
import power.musicStart;

import java.lang.reflect.Field;


public class untouchableFutureToken extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("untouchableFutureToken");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;
    public static final String ID = "untouchableFutureToken";
    public static final String IMG_PATH = "img/card_Anon/star/记忆缺失_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public untouchableFutureToken() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.STATUS,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
//        this.isEthereal = true;
//        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(51,68,170));
//        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
//        if (sign != null)
//            this.cardSign = sign.SIGN;
        this.isEthereal = true;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.setBackgroundTexture("img/1024/bg_skill_star_512.png","img/1024/bg_skill_star.png");
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new untouchableFutureToken();
    }

    public void applyPowers() {
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
        }
    }

    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.cantUseMessage = "我不能使用";
        }else {
            this.cantUseMessage = "I can't use";
        }
        return false;
    }
    public boolean canUpgrade() {
        return false;
    }
}

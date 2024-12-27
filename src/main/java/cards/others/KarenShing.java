package cards.others;

import ExcessiveFantasy.boss.TheConsciousnessOfTheWorld;
import Mod.AnonCardSignStrings;
import OnStage.theEndKaren;
import actions.ThinkingAction;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import monster.ShoujoKageki.Karen;
import pathes.AbstractCardEnum;
import power.Karen.KarenShining;
import power.Karen.TheEndKarenShining;

import java.util.Iterator;

/**
 * 爆了
 */
public class KarenShing extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KarenShing");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;
    public static final String ID = "KarenShing";
    public static final String IMG_PATH = "img/char/karen/ki-ringtone _skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public KarenShing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
//        this.isEthereal = true;
        this.setBackgroundTexture("img/char/karen/bg_skill_default_gray_512.png", "img/char/karen/bg_skill_default_gray.png");
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(255, 84, 88));
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
        return (AbstractCard)new KarenShing();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
        }
    }

    public void triggerWhenDrawn() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && (mo.id.equals(Karen.ID) || mo.id.equals(theEndKaren.ID)))  {
                AbstractDungeon.actionManager.addToBottom(new RollMoveAction((AbstractMonster) mo));

                switch (mo.id) {
                    case Karen.ID:
                        addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new KarenShining((AbstractCreature) mo, 1), 1));
                        break;
                    case theEndKaren.ID:
                        addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new TheEndKarenShining((AbstractCreature) mo, 1), 1));
                        break;
                    case TheConsciousnessOfTheWorld.ID:
                        addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new TheEndKarenShining((AbstractCreature) mo, 1), 1));
                        break;
                }
            }
        }
        addToBot((AbstractGameAction)new DrawCardAction(AbstractDungeon.player, 1));
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
    public void triggerOnExhaust() {
        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
    }
    public void triggerOnEndOfTurnForPlayingCard() {
        this.addToBot(new DiscardSpecificCardAction(this));
//        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }
}

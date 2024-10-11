package cards.rare;

import actions.ThinkingAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

import java.util.Iterator;

/**
 * 爆了
 */
public class Thinking extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Thinking");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;
    public static final String ID = "Thinking";
    public static final String IMG_PATH = "img/cards_Seles/SavePower.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Thinking() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Thinking();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
//                if(AbstractDungeon.floorNum > 0){
//                    if (AbstractDungeon.getCurrRoom().phase != RoomPhase.COMBAT) {
//                        upgradeName();
//                        this.isEthereal = false;
//                        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//                        this.initializeDescription();
//                    }
//                }else {
                    upgradeName();
                    this.isEthereal = false;
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                    this.initializeDescription();
//                }

        }
    }

    public void triggerWhenDrawn() {
        this.addToBot(new ThinkingAction(true));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.cantUseMessage = "我不能使用";
        }else {
            this.cantUseMessage = "I can't use";
        }
        return false;
    }
}

package bandFriendsCard;

import actions.SkipEnemiesTurnFalseAction;
import actions.TomoriAction;
import basemod.abstracts.CustomCard;
import cards.token.Minute;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import power.Inspiration;
import power.musicStart;
import power.notLive;

/*
 * */
public class Tomori extends CustomCard {
    public static final String ID = "Tomori";
    
    public static final String IMG_PATH = "img/bandFriends/TomoriCard_skill.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Tomori");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    //花费
    private static final int COST = 1;

    public Tomori() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.selfRetain = true;
        this.cardsToPreview = new Minute();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new notLive((AbstractCreature)p,1,false), 1));
        this.addToBot(new TomoriAction(this.upgraded));
        if(AbstractDungeon.player.hasPower(musicStart.POWER_ID)){
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(p, p, musicStart.POWER_ID));
            addToBot((AbstractGameAction)new SkipEnemiesTurnFalseAction());
            musicStart.ifStart = 1;
        }
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Tomori();
    }
}

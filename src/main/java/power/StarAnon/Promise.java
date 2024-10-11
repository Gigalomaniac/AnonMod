package power.StarAnon;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import star.StarAnon.StarAnonPower.StarAnonShining;


public class Promise
  extends AbstractPower
{
  public static final String POWER_ID = "Promise";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Promise");
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public Promise(AbstractCreature owner) {
    this.name = NAME;
    this.ID = "Promise";
    this.owner = owner;
    this.amount = -1;
    String path128 = "img/newbuff/WhiteAnon/starBuffPower84Power84.png";
    String path48 = "img/newbuff/WhiteAnon/starBuffPower84Power32.png";
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    this.type = PowerType.BUFF;
    updateDescription();
  }
  public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {

  }

  public void onAfterUseCard(AbstractCard card, UseCardAction action) {
    if(card.type.equals(AbstractCard.CardType.SKILL)){
      addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BackTrack(this.owner,1), 1));
    }
  }

  public void atEndOfRound() {

  }


  public void updateDescription() { this.description = DESCRIPTIONS[0] ; }
}
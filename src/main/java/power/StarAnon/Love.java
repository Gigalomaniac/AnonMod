package power.StarAnon;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
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

import java.lang.reflect.Type;


public class Love
  extends AbstractPower
{
  public static final String POWER_ID = "Love";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Love");
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public Love(AbstractCreature owner) {
    this.name = NAME;
    this.ID = "Love";
    this.owner = owner;
    this.amount = -1;
    String path128 = "img/newbuff/jiahuPower84.png";
    String path48 = "img/newbuff/jiahuPower32.png";
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    this.type = PowerType.BUFF;
    updateDescription();
  }

  public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {

  }


  public void onAfterUseCard(AbstractCard card, UseCardAction action) {
  if(card.type.equals(AbstractCard.CardType.ATTACK)){
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
  }
  }


  public void atEndOfRound() {

  }


  public void updateDescription() { this.description = DESCRIPTIONS[0]; }
}
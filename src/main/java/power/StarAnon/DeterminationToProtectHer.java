package power.StarAnon;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;



public class DeterminationToProtectHer
  extends AbstractPower
{
  public static final String POWER_ID = "DeterminationToProtectHer";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DeterminationToProtectHer");
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public DeterminationToProtectHer(AbstractCreature owner) {
    this.name = NAME;
    this.ID = "DeterminationToProtectHer";
    this.owner = owner;
    this.amount = -1;
    String path128 = "img/newbuff/DeterminationBuffPower84.png";
    String path48 = "img/newbuff/DeterminationBuffPower32.png";
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    this.type = PowerType.BUFF;
    updateDescription();
  }


  public void onAfterUseCard(AbstractCard card, UseCardAction action) {
    super.onAfterUseCard(card, action);
    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 2));
  }


  public void atEndOfRound() {
    super.atEndOfRound();
//    if (this.owner.currentBlock > 0) {
//      AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this.owner, this.owner.currentBlock, DamageInfo.DamageType.THORNS)));
//    }
  }

  public void atStartOfTurn() {
    if (this.owner.currentBlock > 0) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this.owner, this.owner.currentBlock, DamageInfo.DamageType.THORNS)));
    }
  }
  public void updateDescription() { this.description = DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1]; }
}
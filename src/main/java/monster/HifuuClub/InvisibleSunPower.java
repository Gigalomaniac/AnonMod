package monster.HifuuClub;

import bandFriendsCard.Sakiko;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnDrawPileShufflePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class InvisibleSunPower extends AbstractPower implements OnDrawPileShufflePower {
    public static final String POWER_ID = "SunPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int storedAmount;

    public InvisibleSunPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = -1;
        String path128 = "img/newbuff/sunPower84.png";
        String path48 = "img/newbuff/sunPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
        this.type = PowerType.BUFF;

        this.storedAmount = amount;
    }


//    public void playApplyPowerSfx() { CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F); }



    public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] ; }



    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.addToTop(new MakeTempCardInHandAction(new CalculatedGamble(), 1));
    }



    public void atEndOfRound() {
        super.atEndOfRound();

    }


    @Override
    public void onShuffle() {

        AbstractMonster mo;
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (!mo.isDeadOrEscaped() ) {
                if( mo.hasPower(StarPower.POWER_ID)){
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction((AbstractCreature) mo,mo, StarPower.POWER_ID));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new MoonPower((AbstractCreature) mo), 1));
                }else if (mo.hasPower(MoonPower.POWER_ID)){
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction((AbstractCreature) mo,mo, MoonPower.POWER_ID));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new StarPower((AbstractCreature) mo), 1));
                }
            }
        }
    }
}
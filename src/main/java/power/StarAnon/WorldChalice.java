package power.StarAnon;

import actions.RemoveNumbuffsAction;
import actions.movie.LoveAction;
import actions.movie.PromiseAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import power.AveMujicaShining;
import star.StarAnon.WorldCrown;
import star.StarAnonSide;

import java.util.Iterator;

public class WorldChalice extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "WorldChalice";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int AllCount = 0;

    public static int MoveHp = 9999;
    public static float WorldChaliceX;
    public static float WorldChaliceY;

    // 能力的描述
//    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//
    public WorldChalice(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/jiahuPower84.png";
        String path48 = "img/newbuff/jiahuPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
        AllCount = this.amount;
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
        this.description = DESCRIPTIONS[0] ;
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

    }


    public void onEnergyRecharge() {
        flash();
    }

    public void reducePower(int reduceAmount) {
        this.amount -= reduceAmount;
        if (this.amount + reduceAmount ==0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }
    public void onUseCard(AbstractCard card, UseCardAction action) {

    }
    public void onDeath() {
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (m2.id.equals(StarAnonSide.id)) {
                addToBot((AbstractGameAction)new ApplyPowerAction(m2, m2, (AbstractPower)new Love(this.owner), 1));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m2,  50));
                if(m2.hasPower(Promise.POWER_ID))
                    this.addToBot(new RollMoveAction((AbstractMonster)m2));
            }
        }
    }


    public void flash() {
       if (WorldChaliceX > 0.1D && WorldChaliceY > 0.1D)
       AbstractDungeon.effectsQueue.add(new LineConnectEffect());
       super.flash();
     }

    public void flashWithoutSound() {
        if (WorldChaliceX > 0.1D && WorldChaliceY > 0.1D)
            AbstractDungeon.effectsQueue.add(new LineConnectEffect());
        super.flashWithoutSound();
    }

    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        WorldChaliceX = x;
        WorldChaliceY = y;
    }

    public void atEndOfRound() {
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if(m2.id.equals(WorldCrown.ID) && m2.currentHealth <=0){
                this.addToBot(new RemoveDebuffsAction(this.owner));
                this.addToBot(new RemoveNumbuffsAction(AbstractDungeon.player));
                this.addToBot(new RemoveNumbuffsAction(AbstractDungeon.player));
            }
        }
    }
}


package power.challenge;

import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.AshBlessing.BlessingFalling;
import power.AshBlessing.BlessingZhenHunJi;
import power.Shining;

import java.util.Iterator;

public class ChallengeFalling extends AbstractPower {
    // 这是高潮迭起
    public static final String POWER_ID ="ChallengeFalling";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    // 能力的描述

    private boolean hasAlive = false;
    public ChallengeFalling(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/huijinaiyin/falling150Power84.png";
        String path48 = "img/huijinaiyin/falling150Power32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        hasAlive = false;
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            System.out.println(m2.id+m2.currentHealth);
            if(!m2.isDeadOrEscaped() ){
                if (!m2.id.equals("AshAnon") && !m2.id.equals("Falling") && m2.currentHealth != 0) {
                    System.out.println(m2.id+m2.currentHealth);
                    hasAlive = true;
                }
            }
        }
        if(hasAlive == false){
            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
        }
//        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(var4.hasNext()) {
//            AbstractMonster m2 = (AbstractMonster)var4.next();
//            if(m2.isDead = false){
//                if (!m2.id.equals("AshAnon") && !m2.id.equals("Falling") && m2.currentHealth >0) {
//                    System.out.println(m2.id+m2.currentHealth);
//
//                }
//            }
//        }
//        if(hasAlive =  false){
//            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
//        }
        this.updateDescription();
    }
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(this.amount >= 100){
            if(AbstractDungeon.player.hasPower(Shining.POWER_ID))
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Shining.POWER_ID));
        }
    }

    public void stackPower(int stackAmount) {
//        super.stackPower(stackAmount);
        this.amount += stackAmount;
        if (this.amount ==0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount >= 999)
            this.amount = 999;
    }
    public void atEndOfRound() {
        hasAlive = false;
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
            }

        }
    }
    public void atStartOfTurn() {
        if(InnerFavillaeSide.Stage == 3){
            Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(var4.hasNext()) {
                AbstractMonster m2 = (AbstractMonster)var4.next();
                if (m2.id.equals("AshAnon") ) {
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new ChallengeFalling(m2,999), 999));
                    addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
                }

            }
        }

        hasAlive = false;
    }
    public void onEnergyRecharge() {
        flash();
//        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void onRemove() {
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (m2.id.equals("AshAnon") ) {
                addToBot((AbstractGameAction)new ApplyPowerAction(m2, m2, (AbstractPower)new BlessingFalling(m2), 1));
                addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
            }

        }
    }
}


package power.challenge;

import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.AshBlessing.BlessingFalling;
import power.AshBlessing.BlessingStarTrails;
import power.AshBlessing.BlessingZhenHunJi;
import power.beat;
import power.musicStart;

import java.util.Iterator;

public class ChallengeStarTrails extends AbstractPower {
    // 这是高潮迭起
    public static final String POWER_ID ="ChallengeStarTrails";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    // 能力的描述
    private int beatNum ;
    public ChallengeStarTrails(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;
        beatNum = 0;
        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/huijinaiyin/xzgjPower84.png";
        String path48 = "img/huijinaiyin/xzgjPower32.png";
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
        if(beatNum==6 && beat.nowBeats == "skill" && card.type.equals(AbstractCard.CardType.SKILL)){
            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
        }
        this.updateDescription();
        beatNum = beat.AllAmount;
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
    }
//    public int onAttacked(DamageInfo info, int damageAmount) {
//        // 非荆棘伤害，非生命流失伤害，伤害来源不为空，伤害来源不是能力持有者本身，伤害大于0
//        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
//            // 能力闪烁一下
//            this.flash();
//
//            // 添加回复action
//            this.addToTop(new HealAction(owner, owner, this.amount));
//        }
//
//        // 如果该能力不会修改受到伤害的数值，按原样返回即可
//        return damageAmount;
//    }
// 省略其他
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {

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
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new ChallengeStarTrails(m2,999), 999));
                    addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
                }

            }
        }
    }
    public void onEnergyRecharge() {

//        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void onRemove() {
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (m2.id.equals("AshAnon") ) {
                addToBot((AbstractGameAction)new ApplyPowerAction(m2, m2, (AbstractPower)new BlessingStarTrails(m2), 1));
                addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
            }

        }
    }
}


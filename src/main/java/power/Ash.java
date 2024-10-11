package power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import power.AshBlessing.*;


//音符buff
public class Ash extends AbstractPower {

    // 能力的ID
    public static final String POWER_ID = "Ash";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static String SongsName  = "";

    public static String[] SongsList = new String[]{"","","","","","","","","",""};

    /**
     * live演奏几首歌
     */
//    public static int LiveSongsNum = 2;

    public Ash(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/ashPower84.png";
        String path48 = "img/newbuff/ashPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] +this.amount+ DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2];
//        this.description = "当前有"+this.amount+"层，减少"+this.amount+"%收到伤害，当灰爱音的灰烬达到100时，获得所有祝福，该结束这场游戏了！";

    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(this.amount >= 100){
//            AbstractDungeon.effectsQueue.clear();
//            AbstractDungeon.effectList.clear();
            if (Settings.language == Settings.GameLanguage.ZHS) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 10F, "你究竟是带着何种觉悟前来挑战我的 NL 过去的那个“千早爱音”", false));
            }else {
                AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 10F, "You have come to challenge me with all sorts of resolve, NL The 'ChihayaAnon' from the past.", false));
            }
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingMachineHeart(this.owner)));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingStarTrails(this.owner)));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingZhenHunJi(this.owner)));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingIdealEnd(this.owner)));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingSuicideFactor(this.owner)));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingGirlRelive(this.owner)));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BlessingFalling(this.owner)));
            this.amount = 0;
            this.description = DESCRIPTIONS[0] +this.amount+ DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2];
        }
    }
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
//        if (damageAmount > this.amount) {
//            damageAmount = this.amount;
//        }
        return (int) (damageAmount*(1-(0.01*this.amount)));
    }
    public void stackPower(int stackAmount) {

        super.stackPower(stackAmount);
//        if (this.amount >= 3) {
//            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//            addToBot((AbstractGameAction)new PressEndTurnButtonAction());
//            this.amount -= 3;
//            if (this.amount <= 0)
//                addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "beat"));
//        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {

//
        flash();
//        this.description = "正在演奏"+ SongsName;
    }

    public void atStartOfTurnPostDraw() {
//
//        if(description.contains("春日影") && AbstractDungeon.player.hasPower("notLive")){
//            SongsName= "无";
//        }

        flash();
//        if (!AbstractDungeon.player.hasPower("beat") && this.amount >= 3) {
//            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//            addToBot((AbstractGameAction)new PressEndTurnButtonAction());
//        } else {
//            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new MantraPower(this.owner, this.amount), this.amount));
//        }
    }

    public void atEndOfTurn(boolean isPlayer) {
    }

//    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
////        if (damageAmount > this.amount) {
////            damageAmount = this.amount;
////        }
//        return (int) (damageAmount*Math.pow(0.99, this.amount));
//    }
}


package power;

import characters.char_Anon;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import relics.GuitarWhiteAnon;

import java.util.Objects;
import java.util.Random;


//音符buff
public class beat extends AbstractPower {

    // 能力的ID
    public static final String POWER_ID = "beat";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static String nowBeats  = "无";

    public static int energy = 0;
    public static int AllAmount  = 0;
    public beat(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;
        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/notePower84.png";
        String path48 = "img/newbuff/notePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + nowBeats + DESCRIPTIONS[1] + this.amount+ DESCRIPTIONS[2] ;
//        this.description = "Anon现在处于"+ nowBeats +"拥有" +  this.amount + " 层节奏。";
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


    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
//        flash();
        if(Objects.equals(nowBeats, "无")) {
            amount = 1;
            switch (card.type) {
                case ATTACK: {
                    nowBeats = "attack";
                    break;
                }
                case SKILL: {
                    nowBeats = "skill";
                    break;
                }
                case POWER: {
                    nowBeats = "power";
                    break;
                }
            }
        }else {
            if (card.cardID.equals("HastyFinish")) {
                AllAmount = amount = 0;
                nowBeats = "attack";
            }
            if (card.cardID.equals("Grin")) {
                AllAmount = amount = 0;
                nowBeats = "skill";
            }
            if (AbstractDungeon.player.hasPower("variation") || card.cardID.equals("DreamTale") || card.cardID.contains("Songs")|| card.cardID.contains("Rikki")|| card.cardID.contains("WhiteTrio")) {
                amount = amount + 1;
                musicFunction();
            } else {
                if (card.type != AbstractCard.CardType.ATTACK && card.type != AbstractCard.CardType.SKILL && card.type != AbstractCard.CardType.POWER) {
                    amount = 0;
                    nowBeats = "无";
                } else {
                    if (!AbstractDungeon.player.hasPower("variation")) {
                        if (card.type == AbstractCard.CardType.ATTACK) {
                            switch (nowBeats) {
                                case "attack": {
                                    amount = amount + 1;
                                    musicFunction();
                                    break;
                                }
                                case "skill":
                                    amount = 1;
                                    nowBeats = "attack";
                                    break;
                                case "power": {
                                    amount = 1;
                                    nowBeats = "attack";
                                    break;
                                }
                            }
                        }
                        if (card.type == AbstractCard.CardType.SKILL) {
                            switch (nowBeats) {
                                case "attack":
                                    amount = 1;
                                    nowBeats = "skill";
                                    break;
                                case "power": {
                                    amount = 1;
                                    nowBeats = "skill";
                                    break;
                                }
                                case "skill": {
                                    amount = amount + 1;
                                    musicFunction();
                                    break;
                                }
                            }
                        }
                        if (card.type == AbstractCard.CardType.POWER) {
                            switch (nowBeats) {
                                case "attack":
                                case "skill": {
                                    amount = 1;
                                    nowBeats = "power";
                                    break;
                                }
                                case "power": {
                                    amount = amount + 1;
                                    musicFunction();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        this.description = DESCRIPTIONS[0] + nowBeats + DESCRIPTIONS[1] + this.amount+ DESCRIPTIONS[2] ;
//        char_Anon.getBeats(this.amount,nowBeats);


    }

    public void atStartOfTurnPostDraw() {
        if(musicStart.ifStart == 2){
            EnergyPanel.totalCount = beat.energy + 1;
            //回费效果
        }
        char_Anon.getBeats(this.amount,nowBeats);
//        flash();
//        if (!AbstractDungeon.player.hasPower("beat") && this.amount >= 3) {
//            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//            addToBot((AbstractGameAction)new PressEndTurnButtonAction());
//        } else {
//            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new MantraPower(this.owner, this.amount), this.amount));
//        }
    }
//    addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//    addToBot((AbstractGameAction)new PressEndTurnButtonAction());
    public void onEnergyRecharge() {
        AllAmount = this.amount;
//        flash();
//        addToBot((AbstractGameAction)new DiscoveryAction());
//        if (this.amount <= 1) {
//            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        } else {
//            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
//        }

    }
    public void musicFunction(){
        if(amount >= 7){
            if(AbstractDungeon.player.hasPower(EncorePower.POWER_ID)){
                this.addToBot(new GainEnergyAction(1));
                if(AbstractDungeon.player.hasPower(notLive.POWER_ID)){
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
                }
            }
            if(AbstractDungeon.player.hasRelic(GuitarWhiteAnon.ID) &&AbstractDungeon.player.hasPower(notLive.POWER_ID)){
                Random random = new Random();
                int randomNumber = random.nextInt(100) + 1;
                if (randomNumber >= AbstractDungeon.player.getRelic(GuitarWhiteAnon.ID).counter) {
                    if(AbstractDungeon.player.hasPower(WorldLegacy.POWER_ID)){
                        addToBot((AbstractGameAction) new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, notLive.POWER_ID));
                    }else {
                        addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, -1, false), -1));
                    }

                }
            }
            amount = amount - 7;
            if(!AbstractDungeon.player.hasPower("notLive")){
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new musicStart(this.owner), 1));
                addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    addToBot((AbstractGameAction) new TalkAction(true, "Live要开始了哦！", 1.0F, 2.0F));
                }else {
                    addToBot((AbstractGameAction) new TalkAction(true, "Live is beginning!", 1.0F, 2.0F));
                }
                musicStart.ifStart = 0;
            }

            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new Shining(this.owner,1), 1));
        }
    }

    public void onAfterCardPlayed(AbstractCard usedCard){
//        System.out.println("onAfterCardPlayedenergy"+AbstractDungeon.player.energy.energyMaster);
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AllAmount = this.amount;
        char_Anon.getBeats(this.amount,nowBeats);
    }
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            energy = EnergyPanel.totalCount;
        }
    }
}


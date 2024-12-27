package monster.mika;//package monster.mika;

import actions.TalkAction;
import actions.movie.AnonEndingSimplePlayVideoEffect;
import actions.movie.CanStopMediaPlayerAction;
import actions.movie.MikaCanStopMediaPlayerAction;
import actions.movie.MikaSimplePlayVideoEffect;
import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.SpecialCard.MikaSwissRoll;
import characters.AnimaItem;
import characters.MikaAnimaItem;
import characters.character3DHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import monster.caicai.Car;
import monster.caicai.wa;
import power.Karen.KarenShining;
import power.mika.MikaCharge;
import power.mika.MikaStartCharge;
import power.mika.MikaSupport;
import power.waPower;
import utils.DreamCardRewards;
import vfx.animation.TimeLateEffect;
import vfx.piwa;

import java.util.ArrayList;
import java.util.Iterator;

import static utils.AnonSpireKit.addToBot;

public class mika extends AbstractSpriterMonster {
    public static final String ID = "mika";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP_MIN = 13;
    private static final int HP_MAX = 17;
    private static final int A_2_HP_MIN = 14;
    private static final int A_2_HP_MAX = 18;
    private static final int BLUNT_DAMAGE = 4;
    private static final int A_2_BLUNT_DAMAGE = 5;
    private static final int WEAK_AMT = 1;
    private static final byte BLUNT = 2;
    private int moveCount = 0;
    private ChangeScene effect;
    private boolean firstTurn = true;
    private boolean awake = false;
    public mika() {
        super(NAME, ID, 1000, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, 0, 0);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/test/mika.png");
        this.damage.add(new DamageInfo(this, 15));
        this.damage.add(new DamageInfo(this, 17));
        this.damage.add(new DamageInfo(this, 4));
        this.damage.add(new DamageInfo(this, 20));
        awake = false;
    }



    public void die() {
        super.die();
        ArrayList<AnimaItem> list = new ArrayList<AnimaItem>();
        AbstractDungeon.combatRewardScreen.rewards.clear();
        (AbstractDungeon.getCurrRoom()).rewards.add(new DreamCardRewards(32));

            list.add(MikaAnimaItem.Vital_Panic);
            this.char3D.queueAnimaItem(list, true);
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.combatRewardScreen.open();
        },1f));
    }


    @Override
    public void takeTurn() {
        ArrayList<AnimaItem> movelist = new ArrayList<AnimaItem>();
        switch (this.nextMove) {
            case 0:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.FIRE, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 20));
                movelist.add(MikaAnimaItem.Normal_Attack_Ing);
                movelist.add(MikaAnimaItem.Normal_Attack_End);
                CardCrawlGame.sound.play("murimuri");
                this.char3D.queueAnimaItem(movelist, true);
                if(this.hasPower(MikaCharge.POWER_ID) && this.getPower(MikaCharge.POWER_ID).amount <6){
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaCharge(this, 1), 1));
                }
                break;
            case 1:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.FIRE, true));
                }
                movelist.add(MikaAnimaItem.Normal_Attack_Ing);
                movelist.add(MikaAnimaItem.Normal_Attack_Ing);
                movelist.add(MikaAnimaItem.Normal_Attack_End);
                if(this.hasPower(MikaCharge.POWER_ID) && this.getPower(MikaCharge.POWER_ID).amount <6){
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaCharge(this, 1), 1));
                }
                break;
            case 2:
                for(int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                break;
            case 99:
                if(awake == false) {
                    awake = true;
                    this.getPower(MikaCharge.POWER_ID).amount = 0;
                    AbstractDungeon.effectsQueue.add(new ChangeScene(ImageMaster.loadImage("img/boss/177421.jpg")));
                    CardCrawlGame.music.silenceTempBgmInstantly();
                    CardCrawlGame.fadeIn(1.0F);
                    addToBot(new MikaCanStopMediaPlayerAction(new MikaSimplePlayVideoEffect("movie/mika.webm")));
                    ArrayList<AnimaItem> list = new ArrayList<AnimaItem>();
                    AbstractDungeon.effectList.add(new LatterEffect(() -> {
                        list.add(MikaAnimaItem.Normal_Reload_Random);
                        list.add(MikaAnimaItem.Normal_Attack_Start);
                        list.add(MikaAnimaItem.Normal_Attack_Ing);
                        list.add(MikaAnimaItem.Normal_Attack_Ing);
                        list.add(MikaAnimaItem.Normal_Attack_End);
                        list.add(MikaAnimaItem.Cafe_Reaction);
                        this.char3D.queueAnimaItem(list, true);
                    }, 6.5f));
                    this.addToBot(new VFXAction(this, new TimeLateEffect(3.6f), 3.6F));
                    if (Settings.FAST_MODE) {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MikaShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 10), 0.25F));
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MikaShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 10), 0.6F));
                    }
                    for (int i = 0; i < 10; ++i) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
                    }
                    this.addToBot(new VFXAction(this, new TimeLateEffect(2.4f), 2.4F));
                    AbstractDungeon.effectList.add(new LatterEffect(() -> {
                        CardCrawlGame.music.unsilenceBGM();
                        AbstractDungeon.scene.fadeOutAmbiance();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("Endless Carnival (All).ogg", true);
                        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaSupport(this, 1), 1));
                        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, new StrengthPower(this, 2), 2));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new GainBlockAction((AbstractCreature) this, (AbstractCreature) this, 30));
                    }, 12f));
                }else {
                    this.getPower(MikaCharge.POWER_ID).amount = 0;
                    CardCrawlGame.fadeIn(1.0F);
                    addToBot(new MikaCanStopMediaPlayerAction(new MikaSimplePlayVideoEffect("movie/mika.webm")));
                    ArrayList<AnimaItem> list = new ArrayList<AnimaItem>();
                    AbstractDungeon.effectList.add(new LatterEffect(() -> {
                        list.add(MikaAnimaItem.Normal_Reload_Random);
                        list.add(MikaAnimaItem.Normal_Attack_Start);
                        list.add(MikaAnimaItem.Normal_Attack_Ing);
                        list.add(MikaAnimaItem.Normal_Attack_Ing);
                        list.add(MikaAnimaItem.Normal_Attack_End);
                        list.add(MikaAnimaItem.Cafe_Reaction);
                        this.char3D.queueAnimaItem(list, true);
                    }, 6.5f));
                    this.addToBot(new VFXAction(this, new TimeLateEffect(3.6f), 3.6F));
                    if (Settings.FAST_MODE) {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MikaShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 10), 0.25F));
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MikaShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 10), 0.6F));
                    }
                    for (int i = 0; i < 7; ++i) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
                    }
                    this.addToBot(new VFXAction(this, new TimeLateEffect(2.4f), 2.4F));
                    AbstractDungeon.effectList.add(new LatterEffect(() -> {
//                        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaSupport(this, 1), 1));
                        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, new StrengthPower(this, 2), 2));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new GainBlockAction((AbstractCreature) this, (AbstractCreature) this, 30));
                    }, 12f));
                }
                break;
            case 100:
                this.halfDead = false;
                this.awake = true;
                wa wa = new wa(-300.0F, -20.0F);
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(wa, false));
                addToTop((AbstractGameAction)new ApplyPowerAction(wa, wa, (AbstractPower)new waPower(wa)));
                maxHealth = 300;
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this,maxHealth));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(new ChangeScene(ImageMaster.loadImage("img/boss/mikaBG1.jpg")));
                },1.5f));
                AbstractDungeon.effectsQueue.add(this.effect);
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                AbstractDungeon.topLevelEffects.add(new piwa());
                },1f));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, DIALOG[0]));
                },3f));

                this.addToBot(new VFXAction(this, new TimeLateEffect(2), 2F));

                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(new Cleave(),2,true,true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AttackEffect.SLASH_HORIZONTAL));
                break;
        }
        ++this.moveCount;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if(this.hasPower(MikaCharge.POWER_ID) && this.getPower(MikaCharge.POWER_ID).amount >5){
            this.setMove(MOVES[1], (byte) 99, Intent.ATTACK_DEFEND, 4, 7, true);
        }else {

                int act = this.moveCount % 2;
                switch (act) {
                    case 0: {
                        this.setMove( (byte) 0, Intent.ATTACK_DEFEND, 15, 0, false);
                        break;
                    }
                    case 1: {
                        this.setMove(MOVES[0],(byte) 1, Intent.ATTACK, 17, 3, true);
                        break;
                    }
                }
        }

        this.createIntent();
    }

    public void usePreBattleAction() {
        AbstractDungeon.scene= new TheEndingScene();
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/595395.jpg"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.playTempBgmInstantly("Do you wanna kiss angel (伴奏).ogg", true);
        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaCharge(this, 3), 3));
        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaSupport(this, 1), 1));
        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new MikaStartCharge(this), 1));
        this.halfDead = false;
    }
    private int MikaBossPower = 0;
    public void damage(DamageInfo info) {
        super.damage(info);
        MikaBossPower+=info.base;
        if (MikaBossPower >=300) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new MikaSwissRoll(), 1,true,false));
            MikaBossPower = 0;
        }
    }
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("mika");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
    private mikacharacter3DHelper char3D;
    public void update() {
        super.update();
//        updateSkinType();
//        if (this.skinType != -1) {
//            updateReaction();
        if (this.char3D == null)
            try {
                this.char3D = new mikacharacter3DHelper(this.hb.cX + this.dialogX - 50);
            } catch (Exception e) {
//                    this.skinType = -1;
            }
        this.char3D.update(0, 0);
//        }
    }
    public Texture cost = new Texture("img/boss/mika/cost.png");
    public Texture CostBlue = new Texture("img/boss/mika/blue.png");
    public Texture CostWhite = new Texture("img/boss/mika/white.png");
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
//        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX , this.hb.cY + this.dialogY, 2.5F, "小睦，谢谢你 NL 再陪我任性最后一次吧", false));
        spriteBatch.setColor(Color.WHITE);

        spriteBatch.draw(cost,this.hb.cX + this.dialogX -this.hb.width*2f, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,cost.getWidth()*Settings.scale,cost.getHeight()*Settings.scale);
        if(this.hasPower(MikaCharge.POWER_ID))
        FontHelper.renderFontLeft(spriteBatch, FontHelper.topPanelInfoFont, this.getPower(MikaCharge.POWER_ID).amount+"",
                this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()/2*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale +cost.getHeight()/2*Settings.scale, Color.WHITE.cpy());
        else
            FontHelper.renderFontLeft(spriteBatch, FontHelper.topPanelInfoFont, "0",
                    this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()/2*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale +cost.getHeight()/2*Settings.scale, Color.WHITE.cpy());
        if(this.hasPower(MikaCharge.POWER_ID)) {
        switch (this.getPower(MikaCharge.POWER_ID).amount){
            case 0:
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
            case 1:
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
            case 2:
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
            case 3:
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
            case 4:
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
            case 5:
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostWhite,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
            case 6:
            default:
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*2)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*3)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*4)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                spriteBatch.draw(CostBlue,this.hb.cX + this.dialogX -this.hb.width*2f+cost.getWidth()*Settings.scale +(CostBlue.getWidth()*0.9f*5)*Settings.scale, this.hb.cY + this.dialogY +this.hb.height/3*2-50*Settings.scale,CostBlue.getWidth()*Settings.scale,CostBlue.getHeight()*Settings.scale);
                break;
        }

        }

        if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.RestRoom)) {
            if (this.isDead)
                this.char3D.update(this.drawX, this.drawY);
            spriteBatch.setColor(Color.WHITE);

//        spriteBatch.draw(MyImageMaster.charShadow, this.drawX - MyImageMaster.charShadow
//                .getWidth() * Settings.scale / 2.0F, this.drawY - MyImageMaster.charShadow
//                .getHeight() * Settings.scale / 2.0F, MyImageMaster.charShadow
//                .getWidth() * Settings.scale, MyImageMaster.charShadow.getHeight() * Settings.scale, 0, 0, MyImageMaster.charShadow
//
//                .getWidth(), MyImageMaster.charShadow.getHeight(), this.flipHorizontal, this.flipVertical);
            if (char3D != null) {
                this.char3D.render(spriteBatch, this.flipHorizontal);
            } else
                System.out.println("char3D = null");
        }
    }
}

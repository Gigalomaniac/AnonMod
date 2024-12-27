package bossRoom;

import Mod.AnonMod;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomMonster;
import bossRoom.effect.ChangeSceneEffect;
import bossRoom.effect.ChangeSceneEffectLeft;
import bossRoom.effect.LatterEffect;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.*;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import org.lwjgl.Sys;
import power.CarryOffShining;
import power.ImAnon;
import power.Shining;
import power.notLive;
import relics.Crychic;
import utils.Invoker;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AnonSide extends AbstractSpriterMonster  {
    public static final String ID = "InnerAnon";
    private boolean form1 = true;
    private boolean isAwake = false;

    public static final String NAME;
    private boolean saidPower = false;
    private static boolean ifAwake;

    public static int event = 0;
    private boolean isWeaken = false;
    boolean ifMusic = false;
    public boolean isWeaken() {
        return isWeaken;
    }

    public void setWeaken(boolean weaken) {
        isWeaken = weaken;
    }

    private boolean firstTurn = true;

    public GenericEventDialog imageEventText = new GenericEventDialog();
    public static String[] DIALOG = {"果然，我是不被需要的吧", "啊"};

    private ArrayList<AwakenedWingParticle> wParticles = new ArrayList<>();
    private int moveCount = 0;

//    private int Shining = 0;
    private Bone back;

    private int[] powerAf = {
            0,2,5,10,50
    };
    private int powerAfNum = 0;

    /**
     *复活后队友
     */
    public static int finInfo = -1;

    boolean isFirstMove = true;

    private int AnonBossPower = 1;
//    Map<String, Texture> AnonImg = new HashMap<>();
    private final Texture img1 = new Texture("img/boss/AnonBossAwake/anon1.png");
    private final Texture img2 = new Texture("img/boss/AnonBossAwake/anon2.png");
    private final Texture img3 = new Texture("img/boss/AnonBossAwake/anon3.png");
    private final Texture img4 = new Texture("img/boss/AnonBossAwake/anon4.png");
    private final Texture img5 = new Texture("img/boss/AnonBossAwake/anon5.png");
    private final Texture img6 = new Texture("img/boss/AnonBossAwake/anon6.png");
    private final Texture img7 = new Texture("img/boss/AnonBossAwake/anon7.png");
    private final Texture img8 = new Texture("img/boss/AnonBossAwake/anon8.png");
    private final Texture img9 = new Texture("img/boss/AnonBossAwake/anon9.png");



    public AnonSide(float x, float y) {
        super(NAME, "Anon", 1000, -8.0F, 0.0F, 360.0F, 240.0F, null, x, y);
        this.img = new Texture("img/boss/anon_boss.png");
        this.type = EnemyType.BOSS;
        this.setHp(1000);
        finInfo = -1;
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 2));
        this.setMove((byte) Move.UNKNOWN.id, Intent.UNKNOWN);
    }

    private boolean addedPower = false;

    private void addBuff() {
//        if (!hasPower(DeliriousPower.POWER_ID)) {
//            addedPower = true;
//            this.addPower(new DeliriousPower(this, 0));
//            this.addPower(new ImpatiencePower(this));
//        }
    }

    public void takeTurn() {
        animeStart();
        switch (this.nextMove) {
            case 1:
                for (int i = 0; i < 10; i++)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case 3:
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartMegaDebuffEffect()));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                if(powerAf[powerAfNum] != 0){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, powerAf[powerAfNum]), powerAf[powerAfNum]));
                }
                else {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "没有人在乎我……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Nobody cares about me……", false));
                    }
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new notLive(AbstractDungeon.player,1,true), 1));
                powerAfNum ++;
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_1"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REBIRTH"));
                break;
            case 11:
                for (int i = 0; i < 15; i++){
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
                addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new notLive(AbstractDungeon.player,1,true), 1));
                break;
            case 12:

//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                //抽闪耀
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CarryOffShining(this,2), 2));

                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }
    /**
     * 1多段 2重击 3强化
     * @param
     */
    @Override
    protected void getMove(int i) {
        int act = this.moveCount % 3 ;
        if(!this.isAwake){
            switch (this.moveCount % 3) {
                case 0:{
                    this.setMove((byte)3  , Intent.BUFF, 0, 0, true);
                    break;
                }
                case 2: {
                    this.setMove((byte)2, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(0)).base);
                    break;

                }
                case 1: {
                    this.setMove((byte)1, Intent.ATTACK_DEFEND, 2, 10, true);
                    break;
                }
            }
        }else {
            act = this.moveCount % 2 ;
            switch (act) {
                case 0:{
                    this.setMove((byte)11, Intent.ATTACK_DEBUFF, 2, 15, true);
                    break;

                }
                case 1: {
                    this.setMove((byte)12  , Intent.ATTACK_BUFF, 50, 1, false);
                    break;
                }
            }
        }
        ++this.moveCount;
    }
    public void usePreBattleAction() {
        AnonMod.saves.setString("Stage4","InnerAnon");
        ifAwake = false;
        this.imageEventText.setDialogOption(options[0]);
        if (Settings.language == Settings.GameLanguage.ZHS) {
            ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " 千早爱音的觉悟 ");
        }else {
            ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " Anon's determination");
        }
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        this.imageEventText.loadImage("img/event/hospital_600.png");
        if (Settings.language == Settings.GameLanguage.ZHS){
            this.imageEventText.updateBodyText(bodyText[0]);
        }else {
            this.imageEventText.updateBodyText(bodyTextEng[0]);
        }



        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.imageEventText.setDialogOption("进入");
        }else {
            this.imageEventText.setDialogOption("come in");
        }
        this.imageEventText.clearAllDialogs();
        AbstractDungeon.actionManager.addToBottom(new AnonBossEventAction(this));
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ImAnon(this,1), 1));
    }






    private static String bodyText[] = {
            "医生：“98号病床的病人已经稳定了，可以进入探望了 NL "+
                    "病人的状况良好，断层扫描结果也正常，只是不知道为什么还没有苏醒迹象 NL "+
                    "另外你们知道她变成这样的原因的话，多去陪陪他” NL " +
                    "为什么呢，为什么会变成这样……" + " NL " +
                    "“好的太感谢你们了”"
    };
    private static String bodyTextEng[] = {
            "Doctor: 'The patient in bed number 98 has been stabilized and is ready for visiting. NL "
                    + "The patient's condition is good, and the tomography scan results are normal, but we don't know why there's no sign of waking up yet. NL "
                    + "Also, if you know the reason why she became like this, spend more time with her.' NL "
                    + "Why, why did it come to this... " + " NL "
                    + "‘Okay, thank you so much.’"
    };
    private static String titleText[] = {
            " #b千早爱音的觉悟 ",
    };
    private static String options[] = {
            "……",
    };
    @Override
    public void update() {
        super.update();

//        if (!addedPower && currentHealth <= maxHealth * 0.7 && !hasPower(DeliriousPower.POWER_ID)) {
//            addBuff();
//        }
    }

    @Override
    public void die() {
        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
            super.die();
            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            if (this.saidPower) {
                CardCrawlGame.sound.play("VO_AWAKENEDONE_2");
                this.saidPower = true;
            }
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!m.isDying && m instanceof com.megacrit.cardcrawl.monsters.exordium.Cultist)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(m));
            }

            finInfo =-1;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        imageEventText.render(sb);
        animeStart();
    }


//    @Override
//    public void createIntent() {
//        super.createIntent();
////        AbstractDungeon.actionManager.addToTop(new TalkAction(this, "Pure Damage!", 1.0f, 2.0f));
//    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        EnemyMoveInfo moveInfo = Invoker.getField(this, "move");
        if (moveInfo != null && moveInfo.intent == Intent.ATTACK) {
            if ((int) Invoker.getField(this, "intentDmg") != -1) {
                Invoker.setField(this, "intentDmg", 1);
            }
            Invoker.invoke(this, "updateIntentTip");
        }
    }
    private ChangeSceneEffectLeft effect2;
    public void changeState(String key) {
        switch (key) {
            case "REBIRTH":
                this.maxHealth = 1000;
                AnonBossPower = 1;
                this.halfDead = false;
                this.isAwake = true;
                this.effect2 = new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/AnonSidebg00948.png"));
//                this.effect.setShader(new ShyaoBattlefieldShaderEffect());
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(this.effect2);
//            AbstractDungeon.effectsQueue.add(new ShyaoAroundEffect(this));
                }));
                if (Settings.language == Settings.GameLanguage.ZHS) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX -50, this.hb.cY + this.dialogY + 50, 2.5F, "夺走……闪耀", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX -50, this.hb.cY + this.dialogY + 50, 2.5F, "Take……shining", false));
                }

                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ImAnon(this,1), 1));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                //抽闪耀
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, StrengthPower.POWER_ID));

                if(Shining.allCount >=2){
                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CarryOffShining(this,Shining.allCount), Shining.allCount));
                    addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Shining.POWER_ID));
                }else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CarryOffShining(this,2), 2));
                }


//                Shining = Shining +2;
//                damage.get(2).base =(int) (50*(1+ 0.1*Shining));
//                damage.get(3).base = (int) (2*(1+ 0.1*Shining));
                ifAwake = true;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 10, true), 10));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 10, true), 10));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 10, true), 10));
                this.moveCount = 0;
                this.nextMove = 0;
                break;
//            case "ATTACK_1":
//                this.state.setAnimation(0, "Attack_1", false);
//                this.state.addAnimation(0, "Idle_1", true, 0.0F);
//                break;
//            case "ATTACK_2":
//                this.state.setAnimation(0, "Attack_2", false);
//                this.state.addAnimation(0, "Idle_2", true, 0.0F);
//                break;
        }
    }

    static {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            NAME = "里爱音";
        }
        else {
            NAME = "InnerAnon";
        }
    }


    public void damage(DamageInfo info) {
        super.damage(info);

        if(this.currentHealth <= (1000 - AnonBossPower * 250) && this.currentHealth > 0){
            this.addToBot(new PressEndTurnButtonAction());
            this.addToBot(new PressEndTurnButtonAction());
            this.addToBot(new PressEndTurnButtonAction());
            AnonBossPower +=1;
        }
        if (this.currentHealth <= 0 && !this.halfDead) {
            if ((AbstractDungeon.getCurrRoom()).cannotLose == true)
                this.halfDead = true;
            for (AbstractPower p : this.powers)
                p.onDeath();
            for (AbstractRelic r : AbstractDungeon.player.relics)
                r.onMonsterDeath(this);
            addToTop((AbstractGameAction)new ClearCardQueueAction());
            for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                AbstractPower p = s.next();
                if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
                        .equals("Shackled"))
                    s.remove();
            }
            setMove((byte)4, AbstractMonster.Intent.UNKNOWN);
            createIntent();
//            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0]));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)4, AbstractMonster.Intent.UNKNOWN));
//            nextMove =4;
            applyPowers();
            AnonSide.finInfo = 0;
            this.firstTurn = true;
            this.form1 = false;
        }
        if(!isAwake){
            if(this.currentHealth <= 0) {
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "果然，我是不被需要的吧", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Sure enough, I wasn't needed......", false));
                }
            }
        } else {
            if(this.currentHealth <= 0){
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "回去吧…… NL 她们在等着你……", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Let's go back...... NL They're waiting for you......", false));
                }
                finInfo =-1;
            }
        }
    }

    public void animeStart(){
        if(ifAwake){
            String t1= String.valueOf((System.currentTimeMillis()));
            String AnimeCode = String.valueOf(t1.charAt(t1.length() - 2));
            if(AnimeCode.equals("1")){
                try{
                    this.img = img1;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("2")){
                try{
                    this.img = img2;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("3")){
                try{
                    this.img = img3;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("4")){
                try{
                    this.img = img4;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("5")){
                try{
                    this.img = img5;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("6")){
                try{
                    this.img = img6;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("7")){
                try{
                    this.img = img7;
                }catch (Exception e){}
            }
            if(AnimeCode.equals("8")){
                try{
                    this.img = img8;
            }catch (Exception e){}
            }
            if(AnimeCode.equals("9")){
                try{
                    this.img = img9;
            }catch (Exception e){}
            }
        }
    }

//    public void animeStart() {
//        if (ifAwake) {
//            String t1 = String.valueOf(System.currentTimeMillis());
//            String AnimeCode = String.valueOf(t1.charAt(t1.length() - 2));
//            try {
//                this.img = imageMap.get(AnimeCode);
//            }
//        } catch (Exception e) {
//            //do nothing.
//        }
//    }

}

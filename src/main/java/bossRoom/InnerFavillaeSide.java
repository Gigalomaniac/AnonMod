package bossRoom;


import Mod.AnonMod;
import actions.AshAnonTalkAction;
import basemod.ReflectionHacks;
import bossRoom.crychic.Amoris;
import bossRoom.crychic.Doloris;
import bossRoom.crychic.Mortis;
import bossRoom.crychic.Timoris;
import bossRoom.effect.*;
import bossRoom.huijinaiyin.*;
import bossRoom.huijinaiyin.effect.AshAnonStory;
import bossRoom.huijinaiyin.effect.InnerFavillaeEffect;
import bossRoom.huijinaiyin.effect.InnerFavillaeLightEffect;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import org.lwjgl.Sys;
import power.*;
import power.AshBlessing.*;
import power.challenge.*;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class InnerFavillaeSide extends AbstractSpriterMonster  {
    private boolean form1 = true;
    private boolean isAwake = false;

    public static String NAME = "";
    private boolean saidPower = false;
    private static boolean ifAwake;

    public static int event = 0;
    private boolean isWeaken = false;
    boolean ifMusic = false;
    private AshAnonChangeSceneEffect effect2;

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
    public static int moveCount = 0;

//    private int Shining = 0;
    private Bone back;

    public static boolean light = true;
    private int[] powerAf = {
            1,2,5,10,20
    };
    private int powerAfNum = 0;

    public static boolean Doloris = true;

    public static boolean Timoris = true;

    public static boolean Mortis = true;

    public static boolean Amoris = true;

    public static boolean ifTheRightProcess = false;

    /**
     *复活后队友
     */
    public static int CrychicfinInfo = -1;

    public static int Stage = 1;

    public static int StageSongsNum = 1;

    boolean isFirstMove = true;

    private int SakikoBossStage = 1;

    public static float Corrode_BAR_HEIGHT =  20.0F * Settings.scale;

    private Hitbox Corrode;

    public Color CorrodeShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);

    public Color CorrodeBgColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color CorrodetextColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);

    public Color CorrodeBarColorBlue = new Color(120.0F, 153.0F, 204.0F, 1.0F);
    public Color CorrodeBarColoBlue = new Color(0.0F, 204.0F, 255.0F, 1.0F);
    public Color CorrodeBarColoRed = new Color(1.0F, 0.165F, 0.36F, 1.0F);
    public static boolean Haruhikage = false;

//    public static String music = "music/machineheart.mp3";
//    public static String music2 = "music/suiside(In).mp3";


//    public static Music musicz1 = MainMusic.newMusic(music);
//    public static Music musicz2 = MainMusic.newMusic(music2);

    private final Texture img0 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_300.png");
    private final Texture img1 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_301.png");
    private final Texture img2 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_302.png");
    private final Texture img3 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_303.png");
    private final Texture img4 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_304.png");
    private final Texture img5 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_305.png");
    private final Texture img6 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_306.png");
    private final Texture img7 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_307.png");
    private final Texture img8 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_308.png");
    private final Texture img9 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_309.png");
    private final Texture img10 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_310.png");
    private final Texture img11 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_311.png");
    private final Texture img12 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_312.png");
    private final Texture img13 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_313.png");
    private final Texture img14 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_314.png");
    private final Texture img15 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_315.png");
    private final Texture img16 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_316.png");
    private final Texture img17 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_317.png");
    private final Texture img18 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_318.png");
    private final Texture img19 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_319.png");
    private final Texture img20 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_320.png");
    private final Texture img21 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_321.png");
    private final Texture img22 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_322.png");
    private final Texture img23 = new Texture("img/huijinaiyin/huijinaiyin260_2/huijinaiyin260_2_323.png");

    private Texture[] imgAll = {
            img0,img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20,img21,img22,img23,img23
    };


    public InnerFavillaeSide(float x, float y) {
        super(NAME, "AshAnon", 1000, -8.0F, 0.0F, 360.0F, 240.0F, null, x, y);
        this.img = new Texture("img/boss/huijinaiyinBossOne260.png");
        this.type = EnemyType.BOSS;
        this.setHp(1000);
        CrychicfinInfo = -1;
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 5));
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 5));
        this.damage.add(new DamageInfo(this, 11));
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
//        animeStart();
        switch (this.nextMove) {
            case 1:
                for (int i = 0; i < 10; i++)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, 100));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartMegaDebuffEffect()));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new VulnerablePower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));

                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SharpHidePower(this, powerAf[powerAfNum]), powerAf[powerAfNum]));
                if (powerAfNum == 0) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "小灯，我真的很想和你一直在一起……", false));
                }
                if (powerAfNum == 1) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "立希，你已经能代替我创作乐曲了吗……", false));
                }
                if (powerAfNum == 2) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "爽世，你也一直无法放下过去吗……", false));
                }
                if (powerAfNum == 3) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "小睦，谢谢你 NL 再陪我任性最后一次吧", false));
                }
//                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
                powerAfNum++;
                break;
            case 4:
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_1"));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStateAction(this, "GodHand"));
                break;
            case 11:
                for (int i = 0; i < 0+this.moveCount; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(4), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new VulnerablePower((AbstractCreature) AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 1, true), 1));
//                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
                addToBot((AbstractGameAction)new ApplyPowerAction(this,this, (AbstractPower)new Ash(this,2), 2));
                break;
            case 12:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CrychicEffect()));
                for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                    AbstractPower p = s.next();
                    if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
                            .equals("Shackled"))
                        s.remove();
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ArtifactPower(this,3), 3));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AveMujicaShining(this,3), 3));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new SakikoConsciousness(this,3), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
                break;
            case 25:
                for (int i = 0; i < 5; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new Ash(this,1), 1));
//                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new AveMujicaShining(this, 2), 2));
                break;
            case 44:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_1"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REBIRTH"));
                break;
            case 45:
                for (int i = 0; i < 1+this.moveCount; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
//                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new AveMujicaShining(this, 2), 2));

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
        if(Stage == 2){
            if(moveCount == 0){
                setMove("你能回忆起其中的故事吗",(byte)4, AbstractMonster.Intent.UNKNOWN);
                createIntent();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)4, AbstractMonster.Intent.UNKNOWN));

            }else {
                this.setMove("事到如今你来做什么",(byte)45  , Intent.ATTACK_BUFF, 5, 1+this.moveCount, true);
            }

        }else {


        if(!this.isAwake){
            if(CrychicfinInfo == 0){
                this.setMove("我是，oblivionis",(byte)25  , Intent.ATTACK_BUFF, 5, 5+this.moveCount, true);
            }else {
                this.setMove("不要靠近我……",(byte)25  , Intent.ATTACK_BUFF, 5, 5, true);
            }
        }else {
            act = this.moveCount % 2 ;
            switch (act) {
                case 0:
                case 1: {
                    this.setMove("过去的那个爱音啊，请化为灰烬吧",(byte)11, Intent.ATTACK_BUFF, 11, 1+this.moveCount, true);
                    break;
                }
            }
        }
        }
        ++this.moveCount;
    }

    private ChangeScene effect;
    public void usePreBattleAction() {
        AnonMod.saves.setString("Stage4","Ash");
        ifTheRightProcess = false;
        light = true;
        InnerFavillaeSide.Stage = 1;
        CrychicDream.MoveHp=this.currentHealth;
        ifAwake = false;
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new InnerFavillaeEffect()));
//        imageEventText = new GenericEventDialog();
        this.imageEventText.setDialogOption(options[0]);
        ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " Crychic的阴影 ");
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        this.effect = new ChangeScene(ImageMaster.loadImage("img/vfx/bg00026_1920.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        this.imageEventText.loadImage("img/event/hospital_600.png");
        this.imageEventText.updateBodyText(bodyText[0]);
        this.imageEventText.setDialogOption("……");
        this.imageEventText.clearAllDialogs();
//        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new MachineHeart(300.0F, 400.0F, 0), false));
        InnerFavillaeSide.Stage = 1 ;
        InnerFavillaeSide.StageSongsNum = 1;
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new MachineHeart(300.0F, 400.0F, 0), false));
//        AbstractDungeon.actionManager.addToBottom(new CrychicBossEventAction(this));
        AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
        if (Settings.language == Settings.GameLanguage.ZHS) {
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "欢迎来到我们的领域 NL “千早爱音”", false));
        }else {
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Welcome to our field NL “ChihayaAnon”", false));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"机械心"), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonBeat(this), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new Ash(this,0), 0));
        CardCrawlGame.music.playTempBgmInstantly("machineheart.mp3", true);
    }






    private static String bodyText[] = {
            "“目标状态良好，已经到达最深处，当前处于第"+AbstractDungeon.floorNum+"层，即将产生接触！”"+" NL "+
            "“终于走到这最后一步了吗…… NL "+
            "当年你提出通过潜入寻找 #b她 沉睡的原因” NL " +
            "现在经过分析，我们终于发现了，那个执着于 #bCrychic 不愿醒来的她还在那里……" + " NL " +
            "“多亏了你的冒险，我们得以重现这段故事。”"
    };

    private static String titleText[] = {
            " #bCrychic的阴影 ",
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
            if (Settings.language == Settings.GameLanguage.ZHS) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "回到你的故事吧 NL 这些曲子真有她的风格呢……", false));
            }else {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Return to your story." + " NL " + "These tunes are truly characteristic of her style..." , false));
            }
            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            if (this.saidPower) {
                CardCrawlGame.sound.play("VO_AWAKENEDONE_2");
                this.saidPower = true;
            }
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!m.isDying )
                    m.die();
            }
            onBossVictoryLogic();
//            UnlockTracker.hardUnlockOverride("CROW");
//            UnlockTracker.unlockAchievement("CROW");
            onFinalBossVictoryLogic();
            CrychicfinInfo =-1;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        imageEventText.render(sb);
        animeStart();
    }

    private void TruthValueBgRender(SpriteBatch sb, float x, float y) {
        sb.setColor(this.CorrodeShadowColor);
        //左底框
        sb.draw(ImageMaster.HB_SHADOW_L, x - this.hb.width/ 18F, this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, 20.0F * Settings.scale, 25);
        //中间
        sb.draw(ImageMaster.HB_SHADOW_B, x, this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, this.hb.width, 25);
        //右底框
        sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, 20.0F * Settings.scale, 25);

        if(Stage ==3) {
            sb.setColor(this.CorrodeShadowColor);
            //左底框
            sb.draw(ImageMaster.HB_SHADOW_L, x - this.hb.width / 18F, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F + 50, 20.0F * Settings.scale, 25);
            //中间
            sb.draw(ImageMaster.HB_SHADOW_B, x, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F + 50, this.hb.width, 25);
            //右底框
            sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F + 50, 20.0F * Settings.scale, 25);
        }
    }



    private void TruthValueText(SpriteBatch sb, float y) {
        if(Stage ==3) {
        float tmp = this.CorrodetextColor.a;
        if(this.hasPower(Ash.POWER_ID))
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, this.getPower(Ash.POWER_ID).amount + "/100", this.hb.cX, this.hb.cY + this.dialogY + 50+ this.hb.width * 0.53F+50, this.CorrodetextColor);
            this.CorrodetextColor.a = tmp;
        }
            float tmp2 = this.CorrodetextColor.a;
            if (this.hasPower(AshAnonBeat.POWER_ID))
                FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, this.getPower(AshAnonBeat.POWER_ID).amount + "/7", this.hb.cX, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.55F, this.CorrodetextColor);
            this.CorrodetextColor.a = tmp2;


    }
    private void renderTruthValueBar(SpriteBatch sb1,SpriteBatch sb, float x, float y) {
        try {
            Corrode_BAR_HEIGHT = this.hb.cX/ 25.0F;
            sb1.setColor(this.CorrodeBarColoBlue);
            //实际左框
            sb1.draw(ImageMaster.HEALTH_BAR_L, x - this.hb.width/ 18F, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F, 20.0F * Settings.scale, 25);
            //实际中间width表示长度
            sb1.draw(ImageMaster.HEALTH_BAR_B, x  , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, this.getPower(AshAnonBeat.POWER_ID).amount * (this.hb.width/7), 25);
            //实际右边的框
            sb1.draw(ImageMaster.HEALTH_BAR_R, x  + this.getPower(AshAnonBeat.POWER_ID).amount * (this.hb.width/7) , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, 20.0F * Settings.scale, 25);

            if(Stage ==3){
                sb.setColor(this.CorrodeBarColoRed);
                sb.draw(ImageMaster.HEALTH_BAR_L, x - this.hb.width/ 18F, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F+50, 20.0F * Settings.scale, 25);
                //实际中间width表示长度
                sb.draw(ImageMaster.HEALTH_BAR_B, x  , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F+50, this.getPower(Ash.POWER_ID).amount * (this.hb.width/100), 25);

                //实际右边的框
                sb.draw(ImageMaster.HEALTH_BAR_R, x  + this.getPower(Ash.POWER_ID).amount * (this.hb.width/100) , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F+50, 20.0F * Settings.scale, 25);

                //实际左框
//                sb.draw(ImageMaster.HEALTH_BAR_L, x - this.hb.width/ 18F, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F+50, 20.0F * Settings.scale, 25);
//                //实际中间width表示长度
//                sb.draw(ImageMaster.HEALTH_BAR_B, x  , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F+50, this.getPower(Ash.POWER_ID).amount * (this.hb.width/10), 25);
//                //实际右边的框
//                sb.draw(ImageMaster.HEALTH_BAR_R, x  + this.getPower(SakikoBeat.POWER_ID).amount * (this.hb.width/7) , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F+50, 20.0F * Settings.scale, 25);
            }
            Corrode_BAR_HEIGHT = this.hb.cX/ 25.0F;

        }catch (Exception e){}
    }
    public void renderHealth(SpriteBatch sb) {
//        if(this.ifAwake){
            float x = this.hb.cX - this.hb.width / 2.0F;
            float y = this.hb.cY - this.hb.height / 2.0F;
//        float x = 1000;
//        float y = 1000;
            this.Corrode = new Hitbox(x, y, this.hb_w, Corrode_BAR_HEIGHT);
            this.Corrode.render(sb);
            this.TruthValueBgRender(sb, x, y);//底框
            this.renderTruthValueBar(sb,sb, x, y);//实际
            this.TruthValueText(sb, y);//数字文本
//        }
        super.renderHealth(sb);

    }

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
    public void changeState(String key) {
        switch (key) {
            case "GodHand":
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "一无所知的你 NL 为什么舞台上的你还是如此闪耀……", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Oblivious as you are, NL why do you still shine so brightly on stage...", false));
                }
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new MachineHeart(-500.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new StarTrails(-350.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new ZhenHunJi(-200F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new IdealEnd(-50.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SuicideFactor(100.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GirlRelive(250, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Falling(400F, 500.0F, 0), false));
//                this.addToBot(new VFXAction(new GrandFinalEffect()));
//                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CrychicEffect()));
                Texture SakikoAvemujica = new Texture("img/huijinaiyin/huijinaiyin260_2.png");
//                this.maxHealth = 300;
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                if(this.hasPower(SharpHidePower.POWER_ID)){
                    addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, SharpHidePower.POWER_ID));
                }
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AveMujica(this), 1));
                this.img = SakikoAvemujica;
                SakikoBossStage = 2;
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                this.halfDead = false;
//                this.moveCount = 0;
                ifTheRightProcess = true;
                this.nextMove = 0;
                break;
            case "REBIRTH":
//                if(ifTheRightProcess == false){
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingFalling(this), 1));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingGirlRelive(this), 1));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingIdealEnd(this), 1));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingMachineHeart(this), 1));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingStarTrails(this), 1));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingSuicideFactor(this), 1));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingZhenHunJi(this), 1));
//                }
                Stage = 3;

                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if (m2.id.equals(Falling.ID) && m2.hasPower(ChallengeFalling.POWER_ID)) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingFalling(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                    if (m2.id.equals(GirlRelive.ID) && m2.hasPower(ChallengeGirlRelive.POWER_ID)) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingGirlRelive(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                    if (m2.id.equals(IdealEnd.ID) && m2.hasPower(ChallengeIdealEnd.POWER_ID)) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingIdealEnd(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                    if (m2.id.equals(MachineHeart.ID) && m2.hasPower(ChallengeMachineHeart.POWER_ID)) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingMachineHeart(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                    if (m2.id.equals(StarTrails.ID)&& m2.hasPower(ChallengeStarTrails.POWER_ID) ) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingStarTrails(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                    if (m2.id.equals(SuicideFactor.ID)&& m2.hasPower(ChallengeSuicideFactor.POWER_ID) ) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingSuicideFactor(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                    if (m2.id.equals(ZhenHunJi.ID)&& m2.hasPower(ChallengeZhenHunJi.POWER_ID) ) {
                        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BlessingZhenHunJi(this), 1));
                        addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                    }
                }
                this.maxHealth = 1000;
                SakikoBossStage = 3;
                this.halfDead = false;
                this.isAwake = true;
//                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, Ash.POWER_ID));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new Ash(this,2), 2));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new InnerFavillaeEffect()));
                this.addToBot(new VFXAction(new InnerFavillaeLightEffect()));
                light  = true;


                CardCrawlGame.music.playTempBgmInstantly("suiside(In).mp3", true);
                if(AbstractDungeon.player.hasPower(Shining.POWER_ID))
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new InnerFavillaeShining(this,Shining.allCount), Shining.allCount));
                AbstractDungeon.topLevelEffectsQueue.add(new AshAnonStory(2));
                addToBot(new LatterAction(null, 5F));
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, AveMujica.POWER_ID));
                AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"机械心"), 1));
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"星之轨迹"), 1));
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"镇魂祭"), 1));
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"理想的尽头"), 1));
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"自灭因子"), 1));
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"少女再生"), 1));
//                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonSongs(this,"坠落少女"), 1));
                AshAnonSongs.SongsList = new String[]{"机械心","星之轨迹","镇魂祭","理想的尽头","自灭因子","少女再生","坠落少女","","",""};
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new MachineHeart(-500.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new StarTrails(-350.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new ZhenHunJi(-200F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new IdealEnd(-50.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SuicideFactor(100.0F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GirlRelive(250, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Falling(400F, 500.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                AbstractDungeon.actionManager.addToBottom(new AshAnonTalkAction(this));
//                if (Settings.language == Settings.GameLanguage.ZHS) {
//                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 10F, "谢谢你，最后再陪我任性一次吧 NL 这一次，竭尽全力吧！", false));
//                }else {
//                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 10F, "Thank you, accompany me one last time in my capriciousness, NL Let's turn to ashes together, my past self!", false));
//                }
                ifAwake = true;
                addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new cards.others.LoveMeIFYouCan(), 1));
                this.moveCount = 0;
                this.nextMove = 0;
                this.effect2 = new AshAnonChangeSceneEffect(ImageMaster.loadImage("img/boss/bg/bg00948_1920.png"));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {

                    AbstractDungeon.effectsQueue.add(this.effect2);
                }));
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
        if(Settings.language == Settings.GameLanguage.ZHS){
            NAME = "灰爱音";
        }else {
            NAME = "AshAnon";
        }

    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if(this.currentHealth <= 0) {
            if(ifTheRightProcess == true){
                if(!isAwake){
                    if ((AbstractDungeon.getCurrRoom()).cannotLose == true)
                        this.halfDead = true;
                    for (AbstractPower p : this.powers)
                        p.onDeath();
                    for (AbstractRelic r : AbstractDungeon.player.relics)
                        r.onMonsterDeath(this);
                    addToTop((AbstractGameAction)new ClearCardQueueAction());
                    for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                        AbstractPower p = s.next();
                        if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals(InnerFavillaeShining.POWER_ID) || p.ID.equals("Unawakened") || p.ID
                                .equals("Shackled"))
                            s.remove();
                    }
                    setMove("这是起始，也是终焉",(byte)44, AbstractMonster.Intent.UNKNOWN);
                    createIntent();
//            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0]));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)44, AbstractMonster.Intent.UNKNOWN));
//            nextMove =4;
                    applyPowers();
//            AnonSide.finInfo = 0;
                    this.firstTurn = true;
                    this.form1 = false;
                    light = false;
                    CardCrawlGame.music.silenceBGM();
                    CardCrawlGame.music.silenceBGMInstantly();
                    CardCrawlGame.music.silenceTempBgmInstantly();
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "事到如今你又能做什么 NL 我已经只剩下吉他了……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "At this point, what can you do? NL I only have my guitar left...", false));
                    }
                    isAwake = true;
                }
            }else {
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, 1));
            }

        } else {
            if(this.currentHealth <= 0){

//                finInfo =-1;
            }
        }
    }

    public void animeStart(){
        if(ifAwake){
            long timestamp = System.currentTimeMillis();
            float fourthLastDigit = (int) ((timestamp / 100) % 10); // 获取倒数第四位
            float thirdLastDigit = (int) ((timestamp / 10) % 10); // 获取倒数第三位
            float combinedDigits = fourthLastDigit * 10 + thirdLastDigit;
            int baseIndex = (int) (combinedDigits/4);
            int extraIndex = 0;
            if (combinedDigits >= 28 || combinedDigits >= 53 || combinedDigits >= 78 || combinedDigits == 99) {
                extraIndex = 1;
            }
            if(baseIndex +extraIndex<= 23){
                this.img = imgAll[baseIndex +extraIndex];
            }else {
                if (combinedDigits == 98|| combinedDigits == 99){
                    this.img = imgAll[0];
                }else {
                    this.img = imgAll[23];
                }
            }
        }
    }
}

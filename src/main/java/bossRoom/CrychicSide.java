package bossRoom;


import BandFriends.crychic.MutsumiCrychic;
import BandFriends.crychic.SoyoCrychic;
import BandFriends.crychic.TomoriCrychic;
import basemod.ReflectionHacks;
import bossRoom.crychic.*;
import bossRoom.effect.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.VictoryRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import javassist.*;
import org.clapper.util.classutil.ClassInfo;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import patch.RainPatch;
import power.*;
import storm.TogglePlayerElecticShaderAction;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;
import com.megacrit.cardcrawl.map.MapRoomNode;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.scene;

public class CrychicSide extends AbstractSpriterMonster  {
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
            1,2,5,10,20
    };
    private int powerAfNum = 0;

    public static boolean Doloris = true;

    public static boolean Timoris = true;

    public static boolean Mortis = true;

    public static boolean Amoris = true;

    /**
     *复活后队友
     */
    public static int CrychicfinInfo = -1;

    boolean isFirstMove = true;

    private int SakikoBossStage = 1;

    public static float Corrode_BAR_HEIGHT =  20.0F * Settings.scale;

    private Hitbox Corrode;

    public Color CorrodeShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);

    public Color CorrodeBgColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color CorrodetextColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);

    public Color CorrodeBarColorBlue = new Color(120.0F, 153.0F, 204.0F, 1.0F);
    public Color CorrodeBarColoBlue = new Color(0.0F, 204.0F, 255.0F, 1.0F);

    public static boolean Haruhikage = false;


    private final Texture img1 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss1.png");
    private final Texture img2 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss2.png");
    private final Texture img3 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss3.png");
    private final Texture img4 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss4.png");
    private final Texture img5 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss5.png");
    private final Texture img6 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss6.png");
    private final Texture img7 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss7.png");
    private final Texture img8 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss8.png");
    private final Texture img9 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss9.png");
    private final Texture img0 = new Texture("img/boss/AvemujicaAwake/sakikoAvemujicaBoss10.png");



    public CrychicSide(float x, float y) {
        super(NAME, "sakiko", 1000, -8.0F, 0.0F, 360.0F, 240.0F, null, x, y);
        this.img = new Texture("img/boss/sakikoCrychic.png");
        this.type = EnemyType.BOSS;
        this.setHp(600);
        CrychicfinInfo = -1;
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 3));
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
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "小灯，我真的很想和你一直在一起……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Tomori，I really want to be with you all the time...", false));
                    }
                }
                if (powerAfNum == 1) {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "立希，你已经能代替我创作乐曲了吗……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Rikki,can you write music for me already...", false));
                    }
                }
                if (powerAfNum == 2) {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "爽世，你也一直无法放下过去吗……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Soyo,you have been unable to let go of the past...", false));
                    }
                }
                if (powerAfNum == 3) {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "小睦，谢谢你 NL 再陪我任性最后一次吧", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "mutsumi, thank you for being with me one last time", false));
                    }
                }
//                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
                powerAfNum++;
                break;
            case 4:
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_1"));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStateAction(this, "Avemujica"));
                break;
            case 11:
                for (int i = 0; i < 20; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new VulnerablePower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
//                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
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
                for (int i = 0; i < 5+this.moveCount; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new AveMujicaShining(this, 2), 2));
                break;
            case 44:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_1"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REBIRTH"));
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
            if(CrychicfinInfo == 0){
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    this.setMove("我是，oblivionis", (byte) 25, Intent.ATTACK_BUFF, 5, 5 + this.moveCount, true);
                }else {
                    this.setMove("I'm oblivionis", (byte) 25, Intent.ATTACK_BUFF, 5, 5 + this.moveCount, true);
                }
            }else {
                        this.setMove((byte)3  , Intent.BUFF, 0, 0, true);

            }
        }else {
            act = this.moveCount % 2 ;
            switch (act) {
                case 0:{
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        this.setMove("你还在哭泣吗，千早爱音", (byte) 11, Intent.ATTACK_DEBUFF, 3, 20, true);
                    }else {
                        this.setMove("Are you still crying? Chihaya Anon", (byte) 11, Intent.ATTACK_DEBUFF, 3, 20, true);
                    }
                    break;
                }
                case 1: {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                    this.setMove("我们都是不被需要的孩子",(byte)12  , Intent.BUFF, 0, 0, false);
                    }else {
                        this.setMove("We're all unwanted children",(byte)12  , Intent.BUFF, 0, 0, false);
                    }
                    break;
                }
            }
        }
        ++this.moveCount;
    }


    private ChangeRainScene raineffect;
    public void usePreBattleAction() {
        this.raineffect = new ChangeRainScene(ImageMaster.loadImage("img/boss/bg00918 (1).png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.raineffect);
        }));


        CrychicDream.MoveHp=this.currentHealth;
        ifAwake = false;
        this.imageEventText.setDialogOption(options[0]);
        if (Settings.language == Settings.GameLanguage.ZHS) {
            ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " Crychic的阴影 ");
        }else {
            ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " Crychic's shadow ");
        }
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        CardCrawlGame.music.silenceBGM();
        scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        this.imageEventText.loadImage("img/event/hospital_600.png");
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.imageEventText.updateBodyText(bodyText[0]);
        }else {
            this.imageEventText.updateBodyText(bodyText[1]);
        }
        this.imageEventText.setDialogOption("……");
        this.imageEventText.clearAllDialogs();
        AbstractDungeon.actionManager.addToBottom(new CrychicBossEventAction(this));
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CrychicDream(this,4), 4));

        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new TomoriCrychic(200.0F, 380.0F), false));
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new MutsumiCrychic(200.0F, -10.0F), false));
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SoyoCrychic(-350.0F, 380.0F), false));
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BandFriends.crychic.RikkiCrychic(-350.0F, -10.0F), false));
    }






    private static String bodyText[] = {
            "“目标状态良好，已经到达最深处，当前处于第"+AbstractDungeon.floorNum+"层，即将产生接触！”"+" NL "+
            "“终于走到这最后一步了吗…… NL "+
            "当年你提出通过潜入寻找 #b她 沉睡的原因” NL " +
            "现在经过分析，我们终于发现了，那个执着于 #bCrychic 不愿醒来的她还在那里……" + " NL " +
            "“多亏了你的冒险，我们得以重现这段故事。”",
            "“Objective status is good, has reached the deepest part, currently on the " + AbstractDungeon.floorNum + "th floor, about to make contact!”" + " NL "
                    + "“Finally, we've come to this last step... " + " NL "
                    + "When you proposed to infiltrate and find out the reason for #bher slumber,”" + " NL "
                    + "Now after analysis, we have finally discovered that she, who was obsessed with #bCrychic and unwilling to wake up, is still there..." + " NL "
                    + "“Thanks to your adventure, we have been able to recreate this story.”"
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
//        if ((AbstractDungeon.getCurrRoom()).cannotLose) {
//            AbstractDungeon.topLevelEffectsQueue.add(new ShyaoMonologueEffect(this));
//            return;
//        }
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
                if(Haruhikage){
                    if(SakikoBossStage == 1){
//                        AbstractDungeon.effectsQueue.clear();
//                        AbstractDungeon.effectList.clear();
                        if (Settings.language == Settings.GameLanguage.ZHS) {
                            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "真是虚伪呢，想演奏是你们的自由", false));
                        }
                            else{
                            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "How hypocritical. You're free to play if you want", false));
                        }
                    }
                    if(SakikoBossStage == 2){
//                        AbstractDungeon.effectsQueue.clear();
//                        AbstractDungeon.effectList.clear();
                        if (Settings.language == Settings.GameLanguage.ZHS) {
                            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "我现在 NL 无畏忘却", false));
                        }else {
                            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "I am now NL now fearless forget", false));
                        }
                    }
                    if(SakikoBossStage == 3){
//                        AbstractDungeon.effectsQueue.clear();
//                        AbstractDungeon.effectList.clear();
                        if (Settings.language == Settings.GameLanguage.ZHS) {
                            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "这就是你的选择吗 NL 千早爱音", false));
                        }else {
                            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "Is that what you chose NL ChihayaAnon", false));
                        }
                    }
                    Haruhikage = false;
                }
            if(!Doloris){

//                AbstractDungeon.effectsQueue.clear();
//                AbstractDungeon.effectList.clear();
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "辛苦了Doloris，接下来就交给我吧", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "Thanks, Doloris. I'll take it from here", false));
                }
                }
                Doloris= true;
            if(!Amoris){

//                AbstractDungeon.effectsQueue.clear();
//                    AbstractDungeon.effectList.clear();
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "稍微休息一下吧 NL Amoris", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "Take a little break, NL Amoris", false));
                }
                Amoris= true;
                }
        if(!Mortis){

//            AbstractDungeon.effectsQueue.clear();
//            AbstractDungeon.effectList.clear();
            if (Settings.language == Settings.GameLanguage.ZHS) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "对不起……Mortis，谢谢你一直陪着我", false));
            }else {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "I'm sorry... Mortis, thank you for being there for me", false));
            }
            Mortis= true;
        }
        if(!Timoris){

//            AbstractDungeon.effectsQueue.clear();
//            AbstractDungeon.effectList.clear();
            if (Settings.language == Settings.GameLanguage.ZHS) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "感谢你Timoris，见证最后最后的演出吧", false));
            }else {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "Thank you, Timoris, for the final performance", false));
            }
            Timoris= true;
        }
    }
    private void renderTruthValueBar(SpriteBatch sb, float x, float y) {
        try {

            Corrode_BAR_HEIGHT = this.hb.cX/ 25.0F;
            sb.setColor(this.CorrodeBarColoBlue);

//            System.out.println("float a = Settings.scale;"+Settings.scale);
            //实际左框
            sb.draw(ImageMaster.HEALTH_BAR_L, x - this.hb.width/ 18F, this.hb.cY + this.dialogY + 50 + this.hb.width * 0.5F, 20.0F * Settings.scale, 25);
            //实际中间width表示长度
            sb.draw(ImageMaster.HEALTH_BAR_B, x  , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, this.getPower(SakikoBeat.POWER_ID).amount * (this.hb.width/7), 25);
            //实际右边的框
            sb.draw(ImageMaster.HEALTH_BAR_R, x  + this.getPower(SakikoBeat.POWER_ID).amount * (this.hb.width/7) , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, 20.0F * Settings.scale, 25);

//            sb.draw(ImageMaster.HEALTH_BAR_L, x - Corrode_BAR_HEIGHT  / 2.0F, this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
//            sb.draw(ImageMaster.HEALTH_BAR_B, x, this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F,  beat * (this.hb.width/7), Corrode_BAR_HEIGHT);
//            sb.draw(ImageMaster.HEALTH_BAR_R, x + beat * (this.hb.width/7), this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
        }catch (Exception e){

        }
    }
//    this.hb.cX + this.dialogX -50,
    private void TruthValueBgRender(SpriteBatch sb, float x, float y) {
        sb.setColor(this.CorrodeShadowColor);
        //左底框
        sb.draw(ImageMaster.HB_SHADOW_L, x - this.hb.width/ 18F, this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, 20.0F * Settings.scale, 25);
        //中间
        sb.draw(ImageMaster.HB_SHADOW_B, x, this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, this.hb.width, 25);
        //右底框
        sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width , this.hb.cY + this.dialogY + 50+ this.hb.width * 0.5F, 20.0F * Settings.scale, 25);



//        sb.draw(ImageMaster.HEALTH_BAR_L, x - Corrode_BAR_HEIGHT, y, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
//        sb.draw(ImageMaster.HEALTH_BAR_B, x, y, this.hb.width, Corrode_BAR_HEIGHT);
//        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.hb.width, y, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
    }

    private void TruthValueText(SpriteBatch sb, float y) {
        float tmp = this.CorrodetextColor.a;
//        if(musicStart.ifStart == 2){
//            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "LIVE进行中！", this.hb.cX, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.7F, this.CorrodetextColor);
//        }
//        if(musicStart.ifStart == 0){
//            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "准备演奏！", this.hb.cX, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.55F , this.CorrodetextColor);
//        }
//        else {
            if(this.hasPower(SakikoBeat.POWER_ID))
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, this.getPower(SakikoBeat.POWER_ID).amount + "/7", this.hb.cX, this.hb.cY + this.dialogY + 50+ this.hb.width * 0.55F, this.CorrodetextColor);
//        }
        this.CorrodetextColor.a = tmp;
    }
    public void renderHealth(SpriteBatch sb) {
        if(this.ifAwake){
            float x = this.hb.cX - this.hb.width / 2.0F;
            float y = this.hb.cY - this.hb.height / 2.0F;
//        float x = 1000;
//        float y = 1000;
            this.Corrode = new Hitbox(x, y, this.hb_w, Corrode_BAR_HEIGHT);
            this.Corrode.render(sb);
            this.TruthValueBgRender(sb, x, y);//底框
            this.renderTruthValueBar(sb, x, y);//实际
            this.TruthValueText(sb, y);//数字文本
        }
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
            case "Avemujica":
//                startNext();
                this.effect = new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/bgLight1920.png"));
//                this.effect.setShader(new ShyaoBattlefieldShaderEffect());
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(this.effect);
//            AbstractDungeon.effectsQueue.add(new ShyaoAroundEffect(this));
                }));

                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "欢迎来到AveMujica的世界", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Welcome to AveMujica", false));
                }
                CardCrawlGame.music.playTempBgmInstantly("黑色生日.mp3", true);

                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Mortis(200.0F, 380.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Amoris(200.0F, -10.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Doloris(-250.0F, 380.0F, 0), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Timoris(-250.0F, -10.0F, 0), false));
                Texture SakikoAvemujica = new Texture("img/boss/sakikoAvemujica.png");
                this.maxHealth = 300;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                if(this.hasPower(SharpHidePower.POWER_ID)){
                    addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, SharpHidePower.POWER_ID));
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AveMujica(this), 1));
                this.img = SakikoAvemujica;
                SakikoBossStage = 2;
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                this.halfDead = false;
                this.moveCount = 0;
                this.nextMove = 0;
                break;
            case "REBIRTH":
//                AbstractDungeon.topLevelEffectsQueue.add(new CrychicSceneEffect(this));
//                AbstractDungeon.effectsQueue.add(new LatterEffect(() -> {
//                    MapRoomNode node = new MapRoomNode(-1, 15);
//                    node.room = new VictoryRoom(VictoryRoom.EventType.HEART);
//                    AbstractDungeon.nextRoom = node;
//                    AbstractDungeon.closeCurrentScreen();
//                    AbstractDungeon.nextRoomTransitionStart();
//                }));

//                AbstractDungeon.isScreenUp = false;
                this.maxHealth = 1000;
                SakikoBossStage = 3;
                this.halfDead = false;
                this.isAwake = true;
//                AbstractDungeon.effectsQueue.clear();
//                AbstractDungeon.effectList.clear();
                this.effect2 = new ChangeSceneEffect(ImageMaster.loadImage("img/boss/bg/musicroom1920.png"));
//                this.effect.setShader(new ShyaoBattlefieldShaderEffect());
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(this.effect2);
//            AbstractDungeon.effectsQueue.add(new ShyaoAroundEffect(this));
                }));
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "这是由我们开启的故事 NL 现在该由我们画上句号了！", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "This is the story we started and now it 's up to us to finish it!", false));
                }
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, AveMujica.POWER_ID));
                if(this.hasPower(AveMujicaShining.POWER_ID))
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new SakikoConsciousness(this,this.getPower(AveMujicaShining.POWER_ID).amount), this.getPower(AveMujicaShining.POWER_ID).amount));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SakikoBeat(this)));
                //抽闪耀
//                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this, this, StrengthPower.POWER_ID));

//                if(Shining.allCount >=2){
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CarryOffShining(this,Shining.allCount), Shining.allCount));
//                    addToBot((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Shining.POWER_ID));
//                }else {
//                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
//                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CarryOffShining(this,2), 2));
//                }


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
        NAME = "丰川祥子";
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if(!isAwake){
            if(this.currentHealth <= 0 && CrychicSide.CrychicfinInfo == -1) {
//                AbstractDungeon.effectsQueue.clear();
//                AbstractDungeon.effectList.clear();
                raineffect.rain=false;
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "过去软弱的我 NL 已经死了", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "My old weak NL is dead", false));
                }

                CardCrawlGame.music.silenceBGM();
                CardCrawlGame.music.silenceBGMInstantly();
                CardCrawlGame.music.silenceTempBgmInstantly();

                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if (m2.id.equals("TomoriCrychic") || m2.id.equals("MutsumiCrychic") || m2.id.equals("SoyoCrychic") || m2.id.equals("RikkiCrychic")) {
                        m2.die();
                    }
                }
            }
        } else {
            if(this.currentHealth <= 0){
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "谢谢你，爱音 NL 我们回去吧……", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Thank you Anon, let's go back...", false));
                }
                CrychicfinInfo =-1;
            }
        }
        CrychicDream.MoveHp=this.currentHealth;
        if (this.currentHealth <= 0 && !this.halfDead && CrychicfinInfo ==-1) {
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
            setMove((byte)4, Intent.UNKNOWN);
            createIntent();
//            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0]));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)4, Intent.UNKNOWN));
//            nextMove =4;
            applyPowers();
            CrychicSide.CrychicfinInfo = 0;
            this.firstTurn = true;
            this.form1 = false;
        }else {
            if (this.currentHealth <= 0 && !this.halfDead && CrychicfinInfo ==0) {
                for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                    AbstractPower p = s.next();
                    if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
                            .equals("Shackled"))
                        s.remove();
                }
//                AbstractDungeon.effectsQueue.clear();
//                AbstractDungeon.effectList.clear();

                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 8F, "现在这里只剩下你和我了 NL 千早同学。", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 8F, "Now it's just you and me here, NL Chihaya.", false));
                }
                CardCrawlGame.music.silenceBGM();
                scene.fadeOutAmbiance();
                CardCrawlGame.music.silenceBGMInstantly();
//                musicz2.stop();
                if ((AbstractDungeon.getCurrRoom()).cannotLose == true)
                    this.halfDead = true;
                for (AbstractPower p : this.powers)
                    p.onDeath();
                for (AbstractRelic r : AbstractDungeon.player.relics)
                    r.onMonsterDeath(this);
                addToTop((AbstractGameAction)new ClearCardQueueAction());
                setMove((byte)44, Intent.UNKNOWN);
                createIntent();
//            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0]));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)44, Intent.UNKNOWN));
                applyPowers();
                CrychicSide.CrychicfinInfo = 1;
            }
        }

    }
    private ChangeSceneEffectLeft effect;
    private ChangeSceneEffect effect2;
    public void startNext() {
        healthBarUpdatedEvent();
        this.halfDead = false;
        (AbstractDungeon.getCurrRoom()).cannotLose = false;
//        this.effect = new ChangeSceneEffectLeft(0);
        this.effect = new ChangeSceneEffectLeft(ImageMaster.loadImage("img/vfx/bg009152.png"));
        this.effect.setShader(new ShyaoBattlefieldShaderEffect());
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
//            AbstractDungeon.effectsQueue.add(new ShyaoAroundEffect(this));
        }));
        CardCrawlGame.music.silenceTempBgmInstantly();

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
            if(AnimeCode.equals("0")){
                try{
                    this.img = img0;
                }catch (Exception e){}
            }
        }
    }
}

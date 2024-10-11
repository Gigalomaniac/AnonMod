package star;


import TheTreeOfQliphoth.TheTreeOfQliphoth;
import actions.RemoveNumbuffsAction;
import actions.SneakbuffsAction;
import bandFriendsCard.Mutsumi;
import basemod.ReflectionHacks;
import bossRoom.*;
import bossRoom.effect.*;
import bossRoom.huijinaiyin.effect.WhiteAnonStory;
import cards.others.WorldLegacyAnon;
import cards.token.WorldLegacy;
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
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Barricade;
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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import power.*;
import power.StarAnon.*;
import relics.StarAnonGuitar;
import relics.StarAnonPocketWatch;
import star.StarAnon.StarAnonPower.StarAnonShining;
import star.StarAnon.WorldChalice;
import star.StarAnon.WorldCrown;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class StarAnonSide extends AbstractSpriterMonster {

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

    public static String id = "StarAnon";
    public StarAnonSide() {
        super(NAME, id, 500, -8.0F, 0.0F, 360.0F, 340.0F, null, -50, 0);
        this.img = new Texture("img/map/StarAnonWithout.png");
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
            case 3:
                for (int i = 0; i < 10; i++)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case 2:
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, new StarAnonShining(this,5), 5));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, new BackTrack(this,5), 5));
                addToBot((AbstractGameAction)new RemoveDebuffsAction(this));
//                addToBot((AbstractGameAction)new SneakbuffsAction(this));
                break;
            case 4:
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
//                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CrychicEffect()));
                for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                    AbstractPower p = s.next();
                    if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
                            .equals("Shackled"))
                        s.remove();
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ArtifactPower(this,3), 3));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new StarAnonShining(this,3), 3));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BackTrack(this,3), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
                break;
            case 25:
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if(!m2.isDeadOrEscaped()){
                        if(m2 != this)
                        addToBot((AbstractGameAction)new ApplyPowerAction(m2, m2, (AbstractPower)new ArtifactPower(m2,3), 3));
                        addToBot((AbstractGameAction)new GainBlockAction(m2, 100));
                    }
                }
                break;
            case 45:
                maxHealth +=this.currentBlock*2;
                SakikoBossStage = 3;
                this.halfDead = false;
                this.isAwake = true;
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 7F, "在这时空的尽头，所有的碎片已经集齐了 NL 你是追随着哪颗星星走到了这里呢", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "This is the story we started and now it 's up to us to finish it!", false));
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ArtifactPower(this,this.currentBlock/20), this.currentBlock/20));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new StarAnonShining(this,this.currentBlock/20), this.currentBlock/20));
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BackTrack(this,this.currentBlock/20), this.currentBlock/20));
                addToBot((new LoseBlockAction(this,this,this.currentBlock)));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction(this,this,Recollect.POWER_ID));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                ifAwake = true;
                FallingStarTime  FallingStarTime2 = new FallingStarTime(8);
                FallingStarTime  FallingStarTime3 = new FallingStarTime(8);
                FallingStarTime  FallingStarTime4 = new FallingStarTime(8);
                FallingStarTime  FallingStarTime12 = new FallingStarTime(6);
                FallingStarTime  FallingStarTime13 = new FallingStarTime(6);
                FallingStarTime  FallingStarTime14 = new FallingStarTime(6);
                AbstractDungeon.effectsQueue.add(FallingStarTime2);
                AbstractDungeon.effectsQueue.add(FallingStarTime3);
                AbstractDungeon.effectsQueue.add(FallingStarTime4);
                AbstractDungeon.effectsQueue.add(FallingStarTime12);
                AbstractDungeon.effectsQueue.add(FallingStarTime13);
                AbstractDungeon.effectsQueue.add(FallingStarTime14);
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(FallingStarTime2);
                    AbstractDungeon.effectsQueue.add(FallingStarTime3);
                    AbstractDungeon.effectsQueue.add(FallingStarTime4);
                    AbstractDungeon.effectsQueue.add(FallingStarTime12);
                    AbstractDungeon.effectsQueue.add(FallingStarTime13);
                    AbstractDungeon.effectsQueue.add(FallingStarTime14);

                }));
                if(AbstractDungeon.player.hasRelic(StarAnonPocketWatch.ID))
                    this.addToTop(new MakeTempCardInHandAction(new WorldLegacyAnon(), 1));
                this.moveCount = 0;
                this.nextMove = 0;
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
        if( SakikoBossStage == 2){
            if(this.hasPower(Love.POWER_ID) && this.hasPower(Promise.POWER_ID)){
                this.setMove("星遗物", (byte) 45, Intent.DEFEND, 5, 5 + this.moveCount, false);
                this.createIntent();
            }else {
                this.setMove("你也在回忆吗", (byte) 25, Intent.BUFF, 5, 5 + this.moveCount, true);
            }
        }else {
        int act = this.moveCount % 3 ;
        if(!this.isAwake){
            if(CrychicfinInfo == 0){
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    this.setMove("我是，oblivionis", (byte) 25, Intent.BUFF, 5, 5 + this.moveCount, true);
                }else {
                    this.setMove("I'm oblivionis", (byte) 25, Intent.ATTACK_BUFF, 5, 5 + this.moveCount, true);
                }
            }else {
                System.out.println("moveCount"+moveCount);
                switch (this.moveCount % 3) {
                    case 2:{
                        this.setMove((byte)2  , Intent.BUFF, 0, 0, true);
                        break;
                    }
                    case 1: {
                        this.setMove((byte)1, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(0)).base);
                        break;

                    }
                    case 0: {
                        this.setMove((byte)3, Intent.ATTACK_DEFEND, 2, 10, true);
                        break;
                    }
                }

            }
        }else {
            act = this.moveCount % 2 ;
            switch (act) {
                case 0:{
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        this.setMove("你最深层的愿望是什么呢", (byte) 11, Intent.ATTACK_DEBUFF, 3, 20, true);
                    }else {
                        this.setMove("Are you still crying? Chihaya Anon", (byte) 11, Intent.ATTACK_DEBUFF, 3, 20, true);
                    }
                    break;
                }
                case 1: {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                    this.setMove("你能看到天上坠落的流星吗",(byte)12  , Intent.BUFF, 0, 0, false);
                    }else {
                        this.setMove("We're all unwanted children",(byte)12  , Intent.BUFF, 0, 0, false);
                    }
                    break;
                }
            }
        }
        }
        ++this.moveCount;
    }

    private FallingStarTime FallingStarTime;
    public void usePreBattleAction() {
        ifAwake = false;
        if (AbstractDungeon.id.equals(TheTreeOfQliphoth.ID)) {
            StarAnonPocketWatch StarAnonPocketWatch = new StarAnonPocketWatch();
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic) StarAnonPocketWatch);
        }
        this.imageEventText.setDialogOption(options[0]);
        if (Settings.language == Settings.GameLanguage.ZHS) {
            ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " Crychic的阴影 ");
        }else {
            ReflectionHacks.setPrivate(this.imageEventText, GenericEventDialog.class, "title", " Crychic's shadow ");
        }
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        this.imageEventText.loadImage("img/event/hospital_600.png");
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.imageEventText.updateBodyText(bodyText[0]);
        }else {
            this.imageEventText.updateBodyText(bodyText[1]);
        }
        this.imageEventText.setDialogOption("……");
        this.imageEventText.clearAllDialogs();
//        AbstractDungeon.actionManager.addToBottom(new CrychicBossEventAction(this));
//        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new CrychicDream(this,4), 4));
        this.effect2 = new ABChangeScene(ImageMaster.loadImage("img/map/bg00830-150.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect2);
        }));
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new Witness(this), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new BackTrack(this,1), 1));
        CardCrawlGame.music.playTempBgmInstantly("逆转.mp3", true);
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
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        imageEventText.render(sb);

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
                AbstractDungeon.topLevelEffectsQueue.add(new WhiteAnonStory(2));
                this.moveChangeScene = new MoveChangeScene(new Texture("img/map/bg00830-150.png"));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(this.moveChangeScene);
                },1));
                Texture SakikoAvemujica = new Texture("img/map/StarAnonWithBike.png");
                this.effect = new WhiteAnonChangeScene(0);
//
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(this.effect);
                }));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    this.img=SakikoAvemujica;
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new WorldCrown(-400.0F, -10.0F, 0), false));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new WorldChalice(400.0F, -10.0F, 0), false));
                },7F));
                addToBot(new LatterAction(null, 10F));
                this.FallingStarTime = new FallingStarTime(8);
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    CardCrawlGame.music.playTempBgmInstantly("碧天伴奏.mp3", true);
                },15F));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.effectsQueue.add(FallingStarTime);

                }));
                CardCrawlGame.music.silenceBGM();
                CardCrawlGame.music.silenceBGMInstantly();
                CardCrawlGame.music.silenceTempBgmInstantly();
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 5F, "接下来，来到“我们”最后的舞台吧", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Welcome to AveMujica", false));
                }



                this.maxHealth = 1000;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new Recollect(this)));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new BarricadePower(this)));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DeterminationToProtectHer(this)));
                SakikoBossStage = 2;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                this.halfDead = false;
                this.moveCount = 0;
                this.nextMove = 0;
                break;
            case "REBIRTH":
                this.maxHealth = 1000;
                SakikoBossStage = 3;
                this.halfDead = false;
                this.isAwake = true;
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "这是由我们开启的故事 NL 现在该由我们画上句号了！", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "This is the story we started and now it 's up to us to finish it!", false));
                }
                if(this.hasPower(AveMujicaShining.POWER_ID))
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new SakikoConsciousness(this,this.getPower(AveMujicaShining.POWER_ID).amount), this.getPower(AveMujicaShining.POWER_ID).amount));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SakikoBeat(this)));
                ifAwake = true;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 10, true), 10));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 10, true), 10));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 10, true), 10));
                this.moveCount = 0;
                this.nextMove = 0;
                break;
        }
    }

    static {
        NAME = "星爱音";
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if(!isAwake){
            if(this.currentHealth <= 0 && StarAnonSide.CrychicfinInfo == -1) {
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 7F, "你已经摧毁了塔内最后的设备 NL 所有的权限转移到了我身上", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "My old weak NL is dead", false));
                }

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
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "你已经抓到了那颗星星 NL 她在等你，回去吧……", false));
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
            StarAnonSide.CrychicfinInfo = 0;
            this.firstTurn = true;
            this.form1 = false;
        }

    }
    private WhiteAnonChangeScene effect;
    private ABChangeScene effect2;
    private MoveChangeScene moveChangeScene;


    public void priceForStop() {
    }
}

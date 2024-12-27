package ExcessiveFantasy.boss;

import ExcessiveFantasy.boss.power.*;
import Mod.AnonMod;
import actions.RemoveNumDebuffsAction;
import actions.TalkAction;
import bossRoom.AbstractSpriterMonster;
import bossRoom.KarenScene;
import bossRoom.Move;
import bossRoom.effect.*;
import cards.others.KarenShing;
import cards.others.Roselia;
import cards.others.TheConsciousnessOfTheWorldToken;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheBottomScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import monster.ShoujoKageki.ShiningRainEffect;
import power.Karen.IllationPower;
import power.Karen.KarenShining;
import power.Karen.RevueStarlight;
import power.Shining;
import power.StarAnon.Witness;
import power.beat;
import power.notLive;
import power.songs;
import ui.SkinSelectScreen;
import utils.Invoker;
import vfx.ApostleDieEffect;
import vfx.animation.TimeLateEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class TheConsciousnessOfTheWorld extends AbstractSpriterMonster {

    public static final String ID = "TheConsciousnessOfTheWorld";
    private boolean form1 = true;
    private boolean isAwake = false;
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);

    private static final String NAME = monsterStrings.NAME;
    private static final String[] MOVES = monsterStrings.MOVES;
    private static final String[] DIALOG = monsterStrings.DIALOG;

    private boolean saidPower = false;
    private static boolean ifAwake;

    public static int event = 0;
    private boolean isWeaken = false;
    boolean ifMusic = false;
    private ChangeSceneEffectLeft effect;
    private AijoKarenSceneEffect effect3;
    private boolean canHurt;
    public boolean isWeaken() {
        return isWeaken;
    }

    public void setWeaken(boolean weaken) {
        isWeaken = weaken;
    }

    private boolean firstTurn = true;
    private  String[] FinImg= {
            "img/char/anon.png",
            "img/test/newAshAnon.png",
            "img/test/Anon white.png",
            "img/test/AnonStar.png",
            "img/test/caicai.png",
            "img/test/AnonFes.png",
            "img/test/AnonGive.png",
            "img/test/AnonSix.png",
            "img/test/shiro.png",
            "img/test/feimali.png",
            "img/test/PAREO.png",
            "img/test/yukina.png",
            "img/test/soyo.png",
            "img/test/smallworker.png",
            "img/test/tech.png",
            "img/test/leader.png",
            "img/test/KSM.png",
            "img/test/yuzu.png"
    };
    public GenericEventDialog imageEventText = new GenericEventDialog();
//    public static String[] DIALOG = {"果然，我是不被需要的吧", "啊"};

    private ArrayList<AwakenedWingParticle> wParticles = new ArrayList<>();
    private int moveCount = 0;

    public static int stage = 1;

    boolean isFirstMove = true;
    /**
     * 0gbc 1Bocchi 2pp 3ros 4dust 5Karen 6InnerAnon 7Sakiko 8Ash
     */
    public String[] Skin= {"gbc","Bocchi","pp","ros","dust","Karen","InnerAnon","Sakiko","Ash"};
    public String[] Img= {"gbc","Bocchi","pp","ros","dust","Karen","InnerAnon","Sakiko","Ash"};
    public String SkinDetail = "gbc";
    public String nextStage;
    public TheConsciousnessOfTheWorld() {
        super(NAME, ID, 2147483647, 0.0F, 20.0F, 300.0F, 350.0F, null, 0,  - 50);
        this.img = new Texture("img/char/karen/skin_fan/Karen2.png");
        this.type = EnemyType.BOSS;
        this.setHp(300);
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 7));
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 5));
        this.setMove((byte) Move.UNKNOWN.id, Intent.UNKNOWN);
        canHurt = true;
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
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new notLive((AbstractCreature) AbstractDungeon.player, 1, true), 1));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new VulnerablePower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                break;
            case 0:
                for (int i = 0; i < 1 + stage; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 10:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new notLive((AbstractCreature) AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new VulnerablePower((AbstractCreature) AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 3, true), 3));
                addToBot((AbstractGameAction) new RemoveNumDebuffsAction((AbstractCreature) this));
                break;
            case 11:
                for (int i = 0; i < 7; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 99:
                switch (nextStage){
                    case "pp":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(1));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("2. ティアドロップス.mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            this.img = new Texture("img/boss/KSMBoss.png");
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第二观测开始。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/ACT2BossBG.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "ros":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(1));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        AbstractDungeon.getCurrRoom().playBgmInstantly("Music_LOUDER.mp3");
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            this.img = new Texture( "img/boss/Ros/Yukina.png");
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第二观测开始。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/RosBG.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "dust":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(2));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("towards the light.mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            this.img = new Texture("img/boss/dust/leader.png");
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第三计算加载。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/dust/DustRoom.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "Karen":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(2));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("aijoFallingStar.mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第三计算加载。"));
                            this.img = new Texture("img/char/karen/skin_fan/newKaren.png");
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/shamo.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "InnerAnon":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(3));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("03. 迷星叫 -Instrumental-.mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            this.img = new Texture("img/boss/anon_boss.png");
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第四应对方案重启。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/AnonSidebg00948.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "Sakiko":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(3));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("黑色生日.mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            this.img = new Texture("img/boss/sakikoAvemujica.png");
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第四应对方案重启。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/bg00948_1920.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "Ash":
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(3));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("suiside(In).mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            this.img = new Texture("img/boss/sakikoAvemujica.png");
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第四应对方案重启。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/bg00948_1920.png")));
                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        break;
                    case "World":
                        (AbstractDungeon.getCurrRoom()).cannotLose = false;
                        AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(4));
                        CardCrawlGame.music.silenceBGM();
                        CardCrawlGame.music.silenceBGMInstantly();
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBgmInstantly("Ether(Instrumental).mp3", true);
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 5F, "这里是就是，这个世界的原点，也是你旅途的终点。"));
                            AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/NoMoon.png")));
                            this.maxHealth = 1000;
                            this.img = new Texture(FinImg[SkinSelectScreen.index]);

                            AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                        },2.5f));
                        AbstractDungeon.effectList.add(new LatterEffect(() -> {
                            AbstractDungeon.effectsQueue.add(new ExcessiveFantasyMoon(ImageMaster.loadImage("img/boss/bg/onlymoon.png")));
                        },4f));
                        break;
                }
                this.addToBot(new VFXAction(this, new TimeLateEffect(3), 3F));
                LetPower();
                canHurt =true;
                this.halfDead = false;
                this.moveCount = 0;
                this.nextMove = 0;
                break;
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    /**
     * 1多段 2重击 3强化
     *
     * @param
     */
    @Override
    protected void getMove(int i) {
        if(stage != 5) {
            switch (this.moveCount % 3) {
                case 0: {
                    this.setMove((byte) 0, Intent.ATTACK, 7, 1 + stage, true);
                    break;
                }
                case 1: {
                    this.setMove((byte) 1, Intent.ATTACK_DEBUFF, 50, 1, false);
                    break;
                }
                case 2: {
                    this.setMove((byte) 2, Intent.DEFEND_BUFF, 0, 0, false);
                    break;
                }
            }
        }else {
            switch (this.moveCount % 2) {
                case 0: {
                    this.setMove((byte) 10, Intent.STRONG_DEBUFF, 0, 0, false);
                    break;
                }
                case 1: {
                    this.setMove((byte) 11, Intent.ATTACK_DEBUFF, 5, 7, true);
                    break;
                }
            }
        }
        ++this.moveCount;
        this.createIntent();
    }

    public void usePreBattleAction() {
        stage = 1;
        AbstractDungeon.scene= new TheEndingScene();
        AbstractDungeon.effectsQueue.add(new ChangeScene(ImageMaster.loadImage("img/boss/bg/bg01012.png")));
        ifAwake = false;
        switch ( AnonMod.saves.getString("Stage1")){
            case "Bocchi":
                nextStage = Skin[1];
                this.img = new Texture("img/char/bocchi/bocchi.png");
                CardCrawlGame.music.unsilenceBGM();
                AbstractDungeon.scene.fadeOutAmbiance();
                CardCrawlGame.music.silenceBGMInstantly();
                CardCrawlGame.music.playTempBgmInstantly("btfjg-7obvg.ogg", true);
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第一测试准备。"));
                    AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/bg/starry.png")));
                },2.5f));
                break;
            case "gbc":
            default:
//                AbstractDungeon.topLevelEffectsQueue.add(new ApostleDieEffect(4));
                nextStage = Skin[0];
                this.img = new Texture("img/boss/Nina470.png");
                CardCrawlGame.music.unsilenceBGM();
                AbstractDungeon.scene.fadeOutAmbiance();
                CardCrawlGame.music.silenceBGMInstantly();
                CardCrawlGame.music.playTempBgmInstantly("02. 空の箱.mp3", true);
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "第一测试准备。"));
                    AbstractDungeon.effectsQueue.add(new ChangeSceneEffectLeft(ImageMaster.loadImage("img/boss/nina1920.png")));;
                },2.5f));
                break;
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new MemoryBanishment(this), 1));
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
//        AbstractDungeon.scene.fadeOutAmbiance();
//        CardCrawlGame.music.silenceBGMInstantly();
//        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 1), 1));
//        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 2, false, true));
//        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new TheConsciousnessOfTheWorldToken(), 1));
    }

    public void LetPower(){
        ArrayList<AbstractPower> PowerList = AbstractDungeon.player.powers;
        for (AbstractPower avePower : PowerList) {
            if (avePower.ID.equals(beat.POWER_ID) || avePower.ID.equals(songs.POWER_ID)) {
                continue;
            }
            AbstractPower getPower = avePower;
            int num = getPower.amount;
            for (int i = 0; i < num / 10; i++) {
//                getPower.amount = 10;
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDiscardAction(new TheConsciousnessOfTheWorldToken(getPower, 10), 1));
                AbstractDungeon.onModifyPower();
            }
            if (num % 10 != 0) {
                getPower.amount = num % 10;
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDiscardAction(new TheConsciousnessOfTheWorldToken(getPower, num % 10), 1));
                AbstractDungeon.onModifyPower();
          }
            this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, getPower.ID));
        }
    }


    @Override
    public void update() {
        super.update();
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
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new EscapeAction(m));
            }
            onBossVictoryLogic();

            onFinalBossVictoryLogic();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    private ChangeScene effect2;

    public void changeState(String key) {
        switch (key) {
            case "REBIRTH":
                AbstractDungeon.topLevelEffectsQueue.add(new KarenScene(hb.cX + dialogX - 50, hb.cY + dialogY + 50));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    this.halfDead = false;
                    this.isAwake = true;
                    this.effect2 = new ChangeScene(ImageMaster.loadImage("img/boss/bg/shamo.png"));
                    CardCrawlGame.music.playTempBgmInstantly("aijoFallingStar.mp3", true);
                    AbstractDungeon.effectsQueue.add(this.effect2);
                    this.img = new Texture("img/char/karen/skin_fan/newKaren.png");
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this, this, RevueStarlight.POWER_ID));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new IllationPower(this, 1), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 3), 3));
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
                },3F));
                this.addToBot(new VFXAction(this, new TimeLateEffect(8), 8F));
                ifAwake = true;
                this.moveCount = 0;
                this.nextMove = 0;
                break;

        }
    }


    public void damage(DamageInfo info) {
        if(canHurt) {
            super.damage(info);
            if (this.currentHealth <= 0 && !this.halfDead) {
                canHurt =false;
                this.addToTop(new ClearCardQueueAction());
                Iterator s = this.powers.iterator();
                AbstractPower p;
                stage++;
                switch (nextStage) {
                    case "gbc":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryToTo(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL TOGENASHITOGEARI再现完成。", false));
                        break;
                    case "Bocchi":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryBocchi(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL 結束バンド再现完成。", false));
                        break;
                    case "pp":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryPP(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL Poppin'Party!再现完成。", false));
                        break;
                    case "ros":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryRos(this), 1));
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Roselia(), 2));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL Roselia再现完成。", false));
                        break;
                    case "dust":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryDust(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL 打灰爱音再现完成。", false));
                        break;
                    case "Karen":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryKaren(this), 1));
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new KarenShing(), 2));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL 少女歌剧再现完成。", false));
                        break;
                    case "InnerAnon":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryInnerAnon(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL MyGO!!!!!再现完成。", false));
                        break;
                    case "Sakiko":
                        this.addToBot(new ApplyPowerAction(this, this, new MemorySakiko(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL AveMujica再现完成。", false));
                        break;
                    case "Ash":
                        this.addToBot(new ApplyPowerAction(this, this, new MemoryAsh(this), 1));
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "构想记忆： NL InnerFavillae再现完成。", false));
                        break;
                }
                switch (stage) {
                    case 2:
                        switch (AnonMod.saves.getString("Stage2")) {
                            case "pp":
                                nextStage = Skin[2];
                                break;
                            case "ros":
                            default:
                                nextStage = Skin[3];
                                break;
                        }
                        break;
                    case 3:
                        switch (AnonMod.saves.getString("Stage3")) {
                            case "dust":
                                nextStage = Skin[4];
                                break;
                            case "Karen":
                            default:
                                nextStage = Skin[5];
                                break;
                        }
                        break;
                    case 4:
                        switch (AnonMod.saves.getString("Stage4")) {
                            case "Sakiko":
                                nextStage = Skin[7];
                                break;
                            case "Ash":
                                nextStage = Skin[8];
                                break;
                            case "InnerAnon":
                            default:
                                nextStage = Skin[6];
                                break;
                        }
                        break;
                    case 5:
                        nextStage = "World";
                        System.out.println("???");
                        break;
                    case 6:
                        AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "这就是，你的愿望吗……"));
                        super.die();
                        break;
                }
//                this.halfDead = true;
                while (true) {
                    do {
                        if (!s.hasNext()) {
                            this.setMove((byte) 99, Intent.UNKNOWN);
                            this.createIntent();
                            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this, (byte) 99, Intent.UNKNOWN));
                            this.applyPowers();
                            this.firstTurn = true;
                            this.form1 = false;
                            return;
                        }
                        p = (AbstractPower) s.next();
                    }
                    while (p.type != AbstractPower.PowerType.DEBUFF && !p.ID.equals("Curiosity") && !p.ID.equals("Unawakened") && !p.ID.equals("Shackled"));
                    s.remove();
                }
            }

            System.out.println(this.canHurt);
        }else {
            info.base =0;
        }

    }
}

package monster.BocchiTheRock;

import actions.RemoveNumDebuffsAction;
import bossRoom.AbstractSpriterMonster;
import bossRoom.InnerFavillaeSide;
import bossRoom.KarenScene;
import bossRoom.Move;
import bossRoom.effect.*;
import cards.others.KarenShing;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import monster.ShoujoKageki.ShiningRainEffect;
import power.Karen.IllationPower;
import power.Karen.KarenShining;
import power.Karen.RevueStarlight;
import power.Shining;
import power.notLive;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class Bocchi extends AbstractSpriterMonster {

    public static final String ID = "Bocchi";
    private boolean form1 = true;
    private boolean isAwake = false;

    public static final String NAME;
    private boolean saidPower = false;
    private static boolean ifAwake;

    public static int event = 0;
    private boolean isWeaken = false;
    boolean ifMusic = false;
    private ChangeScene effect;
    private AijoKarenSceneEffect effect3;

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
    private boolean hastalked = false;

    private int[] powerAf = {
            0, 2, 5, 10, 50
    };
    private int powerAfNum = 0;

    /**
     * 复活后队友
     */
    public static int finInfo = -1;

    boolean isFirstMove = true;
//    public static String music = "music/孤独と蒼い惑星.mp3";
//    public static Music musicz = MainMusic.newMusic(music);

    private Texture bocchi = new Texture("img/char/bocchi/bocchi.png");
    private Texture box = new Texture("img/char/bocchi/mango.png");

    private Texture Snack = new Texture("img/char/bocchi/BocchiSnack.png");
    private int AnonBossPower = 1;

    public Bocchi(float x, float y) {
//        super(NAME, "Anon", 1000, -8.0F, 0.0F, 360.0F, 240.0F, null, x, y);
        super(NAME, ID, 2147483647, 0.0F, 00.0F, 300.0F, 300.0F, null, x, y - 50);
        this.img = box;
        this.type = EnemyType.BOSS;
        this.setHp(80);
        finInfo = -1;
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 10));
        this.damage.add(new DamageInfo(this, 5));
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 50));
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
        hastalked = false;
        switch (this.nextMove) {
            case 1:
                for (int i = 0; i < 3; i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case 0:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                firstTurn = false;

                break;
            case 4:
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("VO_AWAKENEDONE_1"));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractCreature) this, (AbstractGameEffect) new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStateAction(this, "REBIRTH"));
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "呜呜呜,牡蛎牡蛎……", false));
                hastalked = true;
                break;
            case 11:
                for (int i = 0; i < 1 + this.getPower(KarenShining.POWER_ID).amount; i++) {
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    this.addToBot(new VFXAction(new ShiningRainEffect(AbstractDungeon.player)));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 12:
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 1, false, true));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 3,true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -3), -3));
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 3), 3));
//                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                break;
            case 100:
                this.maxHealth += 100;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "这就是 NL 舞台赋予了我们生存的意义。", false));
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 5), 5));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 1, false, true));
//                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,true), 1));
                this.moveCount = 1;
                this.nextMove = 1;
                break;
            case 101:
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "因为约定 NL 我才站在这里。", false));
                AbstractDungeon.topLevelEffectsQueue.add(new KarenCGEffect());
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                this.maxHealth += 100;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 5), 5));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 2, false, true));
//                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,true), 1));
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                },1F));
                addToBot(new LatterAction(null, 3F));
                this.moveCount = 0;
                this.nextMove= 0;
                break;

            case 120:
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "熟悉的感觉~", false));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 20));
//                AbstractMonster mo;
//                Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//                while(var3.hasNext()) {
//                    mo = (AbstractMonster)var3.next();
//                    if (!mo.isDeadOrEscaped()) {
//                        mo.maxHealth += 14;
                        this.addToBot(new HealAction(this, this, 14));
//                    }
//                }
                this.img = box;
                this.moveCount =1;
                break;
        }
        ++this.moveCount;
        System.out.println(moveCount);
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    /**
     * 1多段 2重击 3强化
     *
     * @param
     */
    @Override
    protected void getMove(int i) {
        if(firstTurn){
            this.setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
            firstTurn=false;
        }else {
            if (this.currentBlock > 0) {
                switch (this.moveCount % 2) {
                    case 1: {
                        this.setMove((byte) 1, Intent.ATTACK_DEBUFF, 5, 3, true);
                        break;
                    }
                    case 0: {
                        this.setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                        break;
                    }
                }
            } else {
                this.setMove("完熟芒果",(byte) 120, Intent.DEFEND_BUFF, 0, 0, true);
            }
        }
        this.createIntent();
    }

    public void usePreBattleAction() {
        AbstractScene TheEndingScene = new TheEndingScene();
        AbstractDungeon.scene= TheEndingScene;
        CardCrawlGame.music.silenceTempBgmInstantly();
//        CardCrawlGame.music.playTempBgmInstantly("btfjg-7obvg.ogg", true);
        AbstractDungeon.getCurrRoom().playBgmInstantly("btfjg-7obvg.ogg");
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/bg/starry.png"));
            AbstractDungeon.effectsQueue.add(this.effect);
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 40));
        this.addToBot(new ApplyPowerAction(this, this, new BarricadePower(this)));
//        CardCrawlGame.music.silenceBGMInstantly();
    }


//    @Override
//    public void update() {
//        super.update();
//
//    }

    @Override
    public void die() {
//        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {

        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "我……我就是下北泽的槌子蛇", false));
            super.die();
//            useFastShakeAnimation(5.0F);
//            CardCrawlGame.screenShake.rumble(4.0F);
//            if (this.saidPower) {
//                CardCrawlGame.sound.play("VO_AWAKENEDONE_2");
//                this.saidPower = true;
//            }
//            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
//                if (!m.isDying && m instanceof com.megacrit.cardcrawl.monsters.exordium.Cultist)
//                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new EscapeAction(m));
//            }
//            onBossVictoryLogic();
////            UnlockTracker.hardUnlockOverride("CROW");
////            UnlockTracker.unlockAchievement("CROW");
//            onFinalBossVictoryLogic();
//            finInfo = -1;
//        }
    }

//    @Override
//    public void render(SpriteBatch sb) {
//        super.render(sb);
//    }


//    @Override
//    public void applyPowers() {
//        super.applyPowers();
//        EnemyMoveInfo moveInfo = Invoker.getField(this, "move");
//        if (moveInfo != null && moveInfo.intent == Intent.ATTACK) {
//            if ((int) Invoker.getField(this, "intentDmg") != -1) {
//                Invoker.setField(this, "intentDmg", 1);
//            }
//            Invoker.invoke(this, "updateIntentTip");
//        }
//    }

    private ChangeScene effect2;

    public void changeState(String key) {
        switch (key) {
            case "REBIRTH":
                AbstractDungeon.topLevelEffectsQueue.add(new KarenScene(hb.cX + dialogX - 50, hb.cY + dialogY + 50));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    maxHealth += 100;
                    AnonBossPower = 1;
                    this.halfDead = false;
                    this.isAwake = true;
                    this.effect2 = new ChangeScene(ImageMaster.loadImage("img/boss/bg/shamo.png"));
                    AbstractDungeon.effectsQueue.add(this.effect2);
                    this.img = new Texture("img/char/karen/skin_fan/newKaren.png");
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this, this, RevueStarlight.POWER_ID));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new IllationPower(this, 1), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 3), 3));
                },3F));

                ifAwake = true;
//
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
            NAME = "后藤一里";
        } else {
            NAME = "InnerAnon";
        }
    }


    public void damage(DamageInfo info) {
        super.damage(info);


            if (this.currentBlock <= 0) {
                if(!this.intent.equals(Intent.DEFEND_BUFF)){
                    AbstractDungeon.actionManager.addToBottom(new AnimateHopAction(this));
                    AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
                    AbstractDungeon.actionManager.addToBottom(new AnimateHopAction(this));
                    this.setMove((byte)4, Intent.STUN);
                    this.createIntent();
                }
//                this.setMove((byte) 120, Intent.DEFEND_BUFF, 0, 0, true);
//                this.setMove((byte)120, Intent.DEFEND_BUFF);
//                this.createIntent();
//                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this, (byte)120, Intent.DEFEND_BUFF));
//                this.applyPowers();
                if (this.currentHealth <= this.maxHealth/2){
                    this.img = Snack;
                }else {
                    this.img = bocchi;
                }
//               if(!hastalked && this.currentHealth >0) {
//
//               }

            }



    }
}

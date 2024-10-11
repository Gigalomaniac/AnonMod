package OnStage;

import actions.RemoveNumDebuffsAction;
import bossRoom.AbstractSpriterMonster;
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
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import monster.ShoujoKageki.ShiningRainEffect;
import power.Karen.TrueIllationPower;
import power.Karen.TheEndKarenShining;
import power.Karen.RevueStarlight;
import power.Shining;
import power.notLive;
import utils.Invoker;
import vfx.animation.TimeLateEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class theEndKaren  extends AbstractSpriterMonster{

    public static final String ID = "theEndKaren";
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
    private Bone back;

    private int[] powerAf = {
            0, 2, 5, 10, 50
    };
    private int powerAfNum = 0;

    /**
     * 复活后队友
     */
    public static int finInfo = -1;

    boolean isFirstMove = true;
    private int AnonBossPower = 1;




    public theEndKaren() {
        super(NAME, ID, 2147483647, 0.0F, 20.0F, 300.0F, 350.0F, null, 0,  - 50);
        this.img = new Texture("img/char/karen/skin_fan/Karen2.png");
        this.type = EnemyType.BOSS;
        this.setHp(100);
        finInfo = -1;
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 3));
        this.damage.add(new DamageInfo(this, 70));
        this.damage.add(new DamageInfo(this, 70));
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
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new VulnerablePower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case 0:
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TheEndKarenShining(this, 3), 3));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 1, false, true));
                break;
            case 4:
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("VO_AWAKENEDONE_1"));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractCreature) this, (AbstractGameEffect) new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStateAction(this, "REBIRTH"));
//                this.addToBot(new WaitAction(10f));
                break;
            case 11:
                for (int i = 0; i < 1 + this.getPower(TheEndKarenShining.POWER_ID).amount; i++) {
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
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TheEndKarenShining(this, 3), 3));
//                this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                break;
            case 100:
                this.maxHealth += 100;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "这就是 NL 舞台赋予了我们生存的意义。", false));
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TheEndKarenShining(this, 5), 5));
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
                addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TheEndKarenShining(this, 5), 5));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 2, false, true));
//                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,true), 1));
                    this.addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                },1F));
                addToBot(new LatterAction(null, 3F));
                this.moveCount = 0;
                this.nextMove= 0;
                break;
        }
        ++this.moveCount;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }


    @Override
    protected void getMove(int i) {
        if(this.hasPower(RevueStarlight.POWER_ID) && this.currentHealth >0 && this.hasPower(TrueIllationPower.POWER_ID)){
            if (this.getPower(TheEndKarenShining.POWER_ID).amount >= 30 ){
                this.setMove((byte) 101, Intent.ATTACK_BUFF, 70, 0, false);
            } else {
                this.setMove((byte) 100, Intent.DEFEND_DEBUFF, 0, 0, true);
            }
        }else {
        if (this.currentHealth <= 0 && !this.halfDead){
            this.setMove((byte) 4, Intent.UNKNOWN, 0, 0, true);
        }else {
          {
            if (!this.isAwake) {
                switch (this.moveCount % 2) {
                    case 0: {
                        this.setMove((byte) 0, Intent.DEFEND_DEBUFF, 0, 0, true);
                        break;
                    }
                    case 1: {
                        this.setMove((byte) 1, Intent.ATTACK_DEBUFF, 50, 1, false);
                        break;
                    }
                }
            } else {
                int act = this.moveCount % 2;
                switch (act) {
                    case 0: {
                        this.setMove((byte) 11, Intent.ATTACK_DEBUFF, 3, 1 + this.getPower(TheEndKarenShining.POWER_ID).amount, true);
                        break;
                    }
                    case 1: {
                        this.setMove((byte) 12, Intent.ATTACK_BUFF, 70, 1, false);
                        break;
                    }
                }
        }}}}
        this.createIntent();
    }

    public void usePreBattleAction() {
        AbstractScene TheEndingScene = new TheEndingScene();
        AbstractDungeon.scene= TheEndingScene;
        ifAwake = false;
        this.effect = new ChangeScene(ImageMaster.loadImage("img/char/karen/transport.png"));
            AbstractDungeon.effectsQueue.add(this.effect);
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TheEndKarenShining(this, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 3, false, true));
        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.playTempBgmInstantly("lunhui.mp3", true);
    }



    @Override
    public void update() {
        super.update();

    }

    @Override
    public void die() {
        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
            super.die();
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
        EnemyMoveInfo moveInfo = Invoker.getField(this, "move");
        if (moveInfo != null && moveInfo.intent == Intent.ATTACK) {
            if ((int) Invoker.getField(this, "intentDmg") != -1) {
                Invoker.setField(this, "intentDmg", 1);
            }
            Invoker.invoke(this, "updateIntentTip");
        }
    }

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
                    CardCrawlGame.music.playTempBgmInstantly("aijoFallingStar.mp3", true);
                    AbstractDungeon.effectsQueue.add(this.effect2);
                    this.img = new Texture("img/char/karen/skin_fan/newKaren.png");
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this, this, RevueStarlight.POWER_ID));
//                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TrueIllationPower(this, 1), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TheEndKarenShining(this, 3), 3));
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new KarenShing(), 2, false, true));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,true), 1));
                },3F));
                this.addToBot(new VFXAction(this, new TimeLateEffect(8), 8F));
                ifAwake = true;
//
                this.moveCount = 0;
                this.nextMove = 0;
                break;
        }
    }

    static {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            NAME = "“约定之王”爱城华恋";
        } else {
            NAME = "InnerAnon";
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.currentHealth <= 0 && !this.halfDead && isAwake == false) {
            addToTop((AbstractGameAction) new ClearCardQueueAction());
            for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                AbstractPower p = s.next();
                if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
                        .equals("Shackled"))
                    s.remove();
            }
            setMove((byte) 4, Intent.UNKNOWN);
            createIntent();
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SetMoveAction(this, (byte) 4, Intent.UNKNOWN));
            applyPowers();
            this.firstTurn = true;
            this.form1 = false;
        }
        if (!isAwake) {
            if (this.currentHealth <= 0) {
                CardCrawlGame.music.silenceTempBgmInstantly();
                this.img = new Texture("img/char/karen/skin_fan/corpse.png");
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "你也还记得吗……", false));
                } else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Sure enough, I wasn't needed......", false));
                }
            }
        } else {
            if (this.currentHealth <= 0) {
                    this.halfDead = false;
                    AbstractDungeon.actionManager.addToTop(new HealAction(this, this, maxHealth));

                    addToBot((AbstractGameAction) new RemoveDebuffsAction((AbstractCreature) this));
            addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new TrueIllationPower(this, 1), 1));
                addToTop((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,false), 1));

                if((this.hasPower(TheEndKarenShining.POWER_ID) && this.getPower(TheEndKarenShining.POWER_ID).amount >= 30 )){
//                    awakeDamage = this.getPower(TheEndKarenShining.POWER_ID).amount*5;
                    this.setMove((byte) 101, Intent.ATTACK_BUFF, 70, 0, false);
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,true), 1));
                }else {
                    this.setMove((byte) 100, Intent.DEFEND_DEBUFF, 0, 0, true);
                    addToTop((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,false), 1));
                }
                createIntent();

//                addToTop((AbstractGameAction) new ApplyPowerAction(this, this,  new TheEndKarenShining(this,3), 3));

        }
        }

    }


    private MoveChangeScene moveChangeScene;


    public void priceForStop() {
    }
}

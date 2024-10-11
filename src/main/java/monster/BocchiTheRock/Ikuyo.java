package monster.BocchiTheRock;

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
import com.megacrit.cardcrawl.audio.MainMusic;
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
import monster.Roselia.Yukina;
import monster.ShoujoKageki.ShiningRainEffect;
import power.Karen.IllationPower;
import power.Karen.KarenShining;
import power.Karen.RevueStarlight;
import power.RoseliaShining;
import power.Shining;
import power.notLive;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class Ikuyo extends AbstractSpriterMonster {

    public static final String ID = "Ikuyo";
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



    public Ikuyo(float x, float y) {
//        super(NAME, "Anon", 1000, -8.0F, 0.0F, 360.0F, 240.0F, null, x, y);
        super(NAME, ID, 2147483647, 0.0F, 00.0F, 300.0F, 300.0F, null, x, y - 50);
        this.img = new Texture("img/char/bocchi/ikuyo.png");
        this.type = EnemyType.BOSS;
        this.setHp(100);
        finInfo = -1;
        /**
         * 两次伤害基数
         */
        this.damage.add(new DamageInfo(this, 15));
        this.damage.add(new DamageInfo(this, 7));
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
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster) var4.next();
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m2,  15));
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case 0:
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "小孤独，再加把劲啦", false));
                addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new notLive(AbstractDungeon.player,1,true), 1));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new WeakPower((AbstractCreature) AbstractDungeon.player, 3, true), 3));
                firstTurn = false;
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) this, (AbstractPower) new FrailPower((AbstractCreature) AbstractDungeon.player, 2, true), 2));

                break;
            case 4:
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("VO_AWAKENEDONE_1"));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractCreature) this, (AbstractGameEffect) new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStateAction(this, "REBIRTH"));
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
        }
        ++this.moveCount;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    /**
     * 1多段 2重击 3强化
     *
     * @param
     */
    @Override
    protected void getMove(int num) {
        if(firstTurn){
            this.setMove((byte) 0, Intent.DEBUFF, 50, 0, false);
        }else {
            if (num < 20) {
            this.setMove((byte) 1, Intent.DEFEND_DEBUFF, 0, 0, true);
        }else {
                if (num < 60) {
                    this.setMove((byte) 2, Intent.ATTACK, 15, 0, false);
            } else {
                    this.setMove((byte) 3, Intent.ATTACK_BUFF, 7, 0, false);
        }}}
        this.createIntent();
    }

    public void usePreBattleAction() {
    }



//    @Override
//    public void update() {
//        super.update();
//
////        if (!addedPower && currentHealth <= maxHealth * 0.7 && !hasPower(DeliriousPower.POWER_ID)) {
////            addBuff();
////        }
//    }

    @Override
    public void die() {
//        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "郁代郁代~", false));
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
//
//
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
            NAME = "喜多郁代";
        } else {
            NAME = "InnerAnon";
        }
    }


    public void damage(DamageInfo info) {
        super.damage(info);
//        if (this.currentHealth <= 0 && !this.halfDead && isAwake == false) {
////            if ((AbstractDungeon.getCurrRoom()).cannotLose == true)
////                this.halfDead = true;
//            for (AbstractPower p : this.powers)
//                p.onDeath();
//            for (AbstractRelic r : AbstractDungeon.player.relics)
//                r.onMonsterDeath(this);
//            addToTop((AbstractGameAction) new ClearCardQueueAction());
//            for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
//                AbstractPower p = s.next();
//                if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
//                        .equals("Shackled"))
//                    s.remove();
//            }
//            setMove((byte) 4, Intent.UNKNOWN);
//            createIntent();
////            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0]));
//            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SetMoveAction(this, (byte) 4, Intent.UNKNOWN));
////            nextMove =4;
//            applyPowers();
//            Ikuyo.finInfo = 0;
//            this.firstTurn = true;
//            this.form1 = false;
//        }
//        if (!isAwake) {
//            if (this.currentHealth <= 0) {
//
//                this.img = new Texture("img/char/karen/skin_fan/corpse.png");
//                if (Settings.language == Settings.GameLanguage.ZHS) {
//                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "你也还记得吗……", false));
//                } else {
//                    AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "Sure enough, I wasn't needed......", false));
//                }
//            }
//        } else {
//            if (this.currentHealth <= 0) {
//                    this.halfDead = false;
//                    AbstractDungeon.actionManager.addToTop(new HealAction(this, this, maxHealth));
//
//                    addToBot((AbstractGameAction) new RemoveNumDebuffsAction((AbstractCreature) this));
//            addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new IllationPower(this, 1), 1));
//                addToTop((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,false), 1));
//                this.addToBot(new ReducePowerAction(this, this, KarenShining.POWER_ID, this.getPower(KarenShining.POWER_ID).amount/2));
//
//                if((this.hasPower(KarenShining.POWER_ID) && this.getPower(KarenShining.POWER_ID).amount >= 15 )|| (this.hasPower(IllationPower.POWER_ID) && this.getPower(IllationPower.POWER_ID).amount >= 3)){
////                    awakeDamage = this.getPower(KarenShining.POWER_ID).amount*5;
//                    this.setMove((byte) 101, Intent.ATTACK_BUFF, 50, 0, false);
//                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,true), 1));
//                }else {
//                    this.setMove((byte) 100, Intent.DEFEND_DEBUFF, 0, 0, true);
//                    addToTop((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new RevueStarlight(this,false), 1));
//                }
//                createIntent();
//
////                addToTop((AbstractGameAction) new ApplyPowerAction(this, this,  new KarenShining(this,3), 3));
//
//        }
//        }

    }
}

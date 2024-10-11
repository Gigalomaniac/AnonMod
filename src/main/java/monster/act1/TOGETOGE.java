package monster.act1;

import BandFriends.crychic.TomoriCrychic;
import actions.SummonGBCFriends486Action;
import actions.SummonGBCFriendsmmkAction;
import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import power.GIRLSBANDCRY;
import utils.Invoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public  class TOGETOGE extends AbstractSpriterMonster {

    private static final Logger logger = LogManager.getLogger(TheCollector.class.getName());
    public static final String ID = "Nina";
    private static MonsterStrings monsterStrings = null;
    public static String NAME;
    public static String[] MOVES;
    public static String[] DIALOG;
    public static boolean s486heatlth = false;
    public static boolean mmkHealth = false;
    public float timer = 0.0F;;
    public static boolean victory = false;
    private HashMap<Integer, AbstractMonster> enemySlots = new HashMap();
    private int whirlwindCount = 4;
    private int moveCount = 0;
    private boolean firstTurn = true;
    public Color color;
    static {
        MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Nina");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
    public TOGETOGE() {
        super(NAME, "Nina", 100, 0.0F, -40.0F, 300.0F, 520.0F, (String)null, 00.0F, -50.0F);
        this.img = new Texture("img/boss/Nina470.png");
        this.monsterStrings = monsterStrings;
        this.dialogX = -90.0F * Settings.scale;
        this.dialogY = 10.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(250);
        } else {
            this.setHp(200);
        }
        this.damage.add(new DamageInfo(this, 2));
        s486heatlth = false;
        mmkHealth = false;
        victory = false;
    }

    public void takeTurn() {
        int key;
//        TorchHead newMonster;
        label52:
        switch (this.nextMove) {
            case 1:
//                for(key = 1; key < 3; ++key) {
                    AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "CALL"));
                    AbstractDungeon.actionManager.addToBottom(new SummonGBCFriendsmmkAction());
                    AbstractDungeon.actionManager.addToBottom(new SummonGBCFriends486Action());
//                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new GIRLSBANDCRY(this), 1));
//                this.initialSpawn = false;
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                break;
            case 3:
                if (AbstractDungeon.ascensionLevel >= 9) {
                    this.whirlwindCount = 3;
                } else {
                    this.whirlwindCount = 2;
                }

                for(int i = 0; i < this.whirlwindCount; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.NONE, true));
                }
            case 5:
                Iterator var3 = this.enemySlots.entrySet().iterator();

                while(true) {
                    if (!var3.hasNext()) {
                        break label52;
                    }

//                    Map.Entry<Integer, AbstractMonster> m = (Map.Entry)var3.next();
//                    if (((AbstractMonster)m.getValue()).isDying) {
//                        newMonster = new TorchHead(this.spawnX + -185.0F * (float)(Integer)m.getKey(), MathUtils.random(-5.0F, 25.0F));
//                        key = (Integer)m.getKey();
//                        this.enemySlots.put(key, newMonster);
//                        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(newMonster, true));
//                    }
                }
            default:
                logger.info("ERROR: Default Take Turn was called on " + this.name);
        }

//        ++this.turnsTaken;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if (this.firstTurn) {
            this.setMove((byte)1, Intent.UNKNOWN);
            this.firstTurn = false;
            return;
        }
        this.setMove((byte)2, Intent.BUFF);
       if(s486heatlth && mmkHealth){
           this.setMove(MOVES[0],(byte)3, Intent.ATTACK, 2, 3, true);
       }
        ++this.moveCount;
    }
    private boolean isMinionDead() {
        Iterator var1 = this.enemySlots.entrySet().iterator();

//        Map.Entry m;
//        do {
//            if (!var1.hasNext()) {
//                return false;
//            }
//
//            m = (Map.Entry)var1.next();
//        } while(!((AbstractMonster)m.getValue()).isDying);

        return true;
    }
    public void die() {
        if(this.timer <=3){
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        }else {
            super.die();
        }

    }

    private ChangeScene effect;
    public void usePreBattleAction() {
        victory = false;
        s486heatlth = false;
        mmkHealth = false;
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/nina1920.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.playTempBgmInstantly("02. 空の箱.mp3", true);
        }


    public void render(SpriteBatch sb) {
        super.render(sb);
//        drawForeground(sb, Settings.WIDTH, 0, (int)(Settings.WIDTH / 0.8F), Settings.HEIGHT);
    }

    private void drawForeground(SpriteBatch sb, int clipX, int clipY, int clipWidth, int clipHeight) {
        Texture imgBack = new Texture(("img/vfx/bg009152.png"));
        Gdx.gl.glColorMask(true, true, true, true);
        this.color = Color.WHITE.cpy();
        sb.setBlendFunction(773, 772);
        Gdx.gl.glEnable(3089);
        Gdx.gl.glScissor(clipX, clipY, clipWidth, clipHeight);
        sb.setColor(this.color);
        sb.draw(imgBack, 0.0F, 0.0F, Settings.WIDTH, Settings.WIDTH / imgBack.getWidth() * imgBack.getHeight());
        sb.flush();
        Gdx.gl.glDisable(3089);
        sb.setBlendFunction(770, 771);
    }
    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.currentHealth <= this.maxHealth/2) {
            this.halfDead = true;
            Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
            (AbstractDungeon.getCurrRoom()).cannotLose = false;
            this.onBossVictoryLogic();
            while(var1.hasNext()) {
                AbstractMonster m = (AbstractMonster)var1.next();
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(m));
            }
            this.timer = 0.0F;
            victory = true;
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX , this.hb.cY + this.dialogY + 50, 7F, "呜哇哇哇 NL 欺负人！", false));
            if(moveCount >=  1){
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX -400, this.hb.cY + this.dialogY + 50, 7F, "等一等 NL 跑慢点，仁菜！", false));
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX +400, this.hb.cY + this.dialogY + 50, 7F, "嗯？ NL 小仁菜真麻烦啊", false));
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if(victory == true){
            this.timer += Gdx.graphics.getDeltaTime();
            if(this.timer >= 3){
                this.die();
            }
        }



    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Nina");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}


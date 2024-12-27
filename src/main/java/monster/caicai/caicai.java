package monster.caicai;

import actions.TalkAction;
import bossRoom.AbstractSpriterMonster;
import bossRoom.crychic.Amoris;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.others.TheConsciousnessOfTheWorldToken;
import cards.uncommon.KiraKiraDokiDoki;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import power.AveMujicaShining;
import power.KiraKiraShining;
import power.Shining;
import power.waPower;
import utils.DreamCardRewards;
import vfx.animation.TimeLateEffect;
import vfx.piwa;

import java.util.Iterator;

public class caicai extends AbstractSpriterMonster {
    public static final String ID = "caicai";
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
    public caicai(float x, float y) {
        super(NAME, ID, 200, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/CaicaiBoss.png");
        this.damage.add(new DamageInfo(this, 30));
        this.damage.add(new DamageInfo(this, 8));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 15));
    }



    public void die() {

    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDiscardAction(new SelfRepair(), 2));
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if(!m2.isDeadOrEscaped() ){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new StrengthPower(m2, 1), 1));
                    }
                }
                break;
            case 1:
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
                    AbstractMonster m = (AbstractMonster)var1.next();
                    if (!m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(m, this,10));
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m, this, 20));
                    }

                break;
            case 4:
                for(int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
                break;
            case 99:
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Car(-100.0F, -20.0F), false));
                this.firstTurn = false;
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
                    AbstractDungeon.effectsQueue.add(new ChangeScene(ImageMaster.loadImage("img/boss/bg00425.png")));
                },1.5f));
                AbstractDungeon.effectsQueue.add(this.effect);
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                AbstractDungeon.topLevelEffects.add(new piwa());
                },1f));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, DIALOG[0]));
                },3f));

                this.addToBot(new VFXAction(this, new TimeLateEffect(2), 2F));
//                AbstractDungeon.effectList.add(new LatterEffect(() -> {
//                this.setMove( (byte) 4, Intent.ATTACK_BUFF, 20, 2, true);
//                this.createIntent();
//                },4f));

                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(new Cleave(),2,true,true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if(this.firstTurn) {
            this.setMove(MOVES[0], (byte)99, Intent.BUFF );
        }else {
            if(!this.awake){
            int act = this.moveCount % 2;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[0], (byte) 0, Intent.STRONG_DEBUFF, 0, 0, false);
                    break;
                }
                case 1: {
                    this.setMove((byte) 1, Intent.DEFEND_DEBUFF, 0, 0, false);
                    break;
                }
            }
            }else {
                this.setMove(MOVES[2], (byte) 4, Intent.ATTACK_BUFF, 15, 2, true);
            }
        }
        ++this.moveCount;
    }

    public void usePreBattleAction() {

        AbstractDungeon.scene= new TheEndingScene();
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/bg00496.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.playTempBgmInstantly("しゅわりん☆どり～みん.mp3", true);
        this.halfDead = false;
        (AbstractDungeon.getCurrRoom()).rewards.add(new DreamCardRewards(23));
    }
    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.currentHealth <= 0 && !this.halfDead) {
            if(awake)
                super.die();
            this.halfDead = true;
            this.awake = true;
            firstTurn = false;
            Iterator s = this.powers.iterator();
            AbstractPower p;
            while (true) {
                do {
                    if (!s.hasNext()) {
                        this.setMove(MOVES[1], (byte) 100, Intent.ATTACK, 30, 1, false);
                        this.createIntent();
                        AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this, (byte) 100, Intent.ATTACK));
                        this.firstTurn = false;
                        this.applyPowers();
                        return;
                    }
                    p = (AbstractPower) s.next();
                }
                while (p.type != AbstractPower.PowerType.DEBUFF && !p.ID.equals("Curiosity") && !p.ID.equals("Unawakened") && !p.ID.equals("Shackled"));
                s.remove();
            }
        }
    }
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("caicai");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

package monster.act2;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.uncommon.KiraKiraDokiDoki;
import cards.uncommon.Pafe;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import monster.act1.TOGETOGE;
import power.KiraKiraShining;
import power.Shining;
import power.notLive;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class KSMBoss extends AbstractSpriterMonster {
    public static final String ID = "KSMNoss";
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

    public KSMBoss(float x, float y) {
        super(NAME, ID, 200, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/KSMBoss.png");
        this.damage.add(new DamageInfo(this, 12));
        this.damage.add(new DamageInfo(this, 8));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 20));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }



    public void die() {

            super.die();

    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.NONE, true));
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if(!m2.isDeadOrEscaped() ){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new KiraKiraShining(AbstractDungeon.player, 2), 2));
                    }
                }
                break;
            case 2:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 0:
                AbstractCard KiraKiraDokiDoki = new KiraKiraDokiDoki();
                KiraKiraDokiDoki.upgrade();
                KiraKiraDokiDoki.cost = 0;
                KiraKiraDokiDoki.magicNumber =1;
                KiraKiraDokiDoki.baseMagicNumber = 1;
                this.addToBot(new MakeTempCardInDrawPileAction(KiraKiraDokiDoki, 5, true, true));
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "大家一起变得KiraKira吧！", false));
                break;
            case 5:
                for(int i = 0; i < 7; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
//                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                Iterator var5 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var5.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var5.next();
                    if(!m2.isDeadOrEscaped() ){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new KiraKiraShining(AbstractDungeon.player, 2), 2));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if(this.currentHealth >= maxHealth/2) {
            int act = this.moveCount % 3;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[0], (byte) 0, Intent.DEBUFF, 4, 1, false);
                    break;
                }
                case 1: {
                    this.setMove( (byte) 1, Intent.ATTACK_BUFF, 12, 1, false);
                    break;
                }
                case 2: {
                    this.setMove((byte) 2, Intent.ATTACK, 8, 3, true);
                    break;
                }
            }
        }else {
            int act = this.moveCount % 2;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[3], (byte)5, Intent.ATTACK_DEBUFF, 2, 7, true);
                    break;
                }
                case 1: {
                    this.setMove(MOVES[3], (byte)6, Intent.ATTACK_BUFF, 20, 1, false);
                    break;
                }
            }
        }
        ++this.moveCount;
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.clear();
        AbstractDungeon.effectsQueue.clear();
        AbstractDungeon.effectList.clear();
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/ACT2BossBG.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.playTempBgmInstantly("2. ティアドロップス.mp3", true);
        this.halfDead = false;
    }
    public void damage(DamageInfo info) {
        super.damage(info);
//        if (this.currentHealth <= 0) {
//            this.halfDead = true;
//            TOGETOGE.s486heatlth = true;
//            for (AbstractPower p : this.powers)
//                p.onDeath();
//            for (AbstractRelic r : AbstractDungeon.player.relics)
//                r.onMonsterDeath(this);
//            addToTop((AbstractGameAction)new ClearCardQueueAction());
//            for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
//                AbstractPower p = s.next();
//                if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
//                        .equals("Shackled"))
//                    s.remove();
//            }
//        }
    }
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("KSMNoss");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

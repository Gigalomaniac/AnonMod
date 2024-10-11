package monster.act2;

import bossRoom.AbstractSpriterMonster;
import bossRoom.CrychicSide;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.uncommon.ReLive;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Healer;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import monster.act1.TOGETOGE;
import power.AveMujicaShining;
import power.Shining;
import power.notLive;

import java.util.Iterator;

public class ARSBoss extends AbstractSpriterMonster {
    public static final String ID = "ARSBoss";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;

    public static boolean  hidehealth = false;
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

    public ARSBoss(float x, float y) {
        super(NAME, ID, 200, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.img = new Texture("img/boss/ARSBoss.png");
        this.type = EnemyType.BOSS;
        this.damage.add(new DamageInfo(this, 12));
        this.damage.add(new DamageInfo(this, 10));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 20));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 2:
                Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var3.hasNext()) {
                    AbstractMonster mo = (AbstractMonster)var3.next();
                    if (!mo.isDead) {
                        this.addToBot(new GainBlockAction(mo, this, 10));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this, new PlatedArmorPower(mo, 5), 5));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this, new RegenPower(mo, 5), 5));
                    }
                }
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "真是的，不要再玩啦！", false));
                break;
            case 0:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 3, true), 3));

                break;
            case 5:
                for(int i = 0; i < 7; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void die() {

            super.die();

    }


    @Override
    protected void getMove(int num) {
        if(this.currentHealth >= maxHealth/2){
            int act = this.moveCount % 3;
        switch (act) {
            case 0: {
                this.setMove(MOVES[0], (byte)0, Intent.DEBUFF, 5, 3, true);
                break;
            }
            case 1: {
                this.setMove( (byte)1, Intent.ATTACK_DEBUFF, 12, 1, false);
                break;
            }
            case 2: {
                this.setMove((byte)2, Intent.BUFF, 4, 1, false);
                break;
            }
        }
        }else {
            int act2 = this.moveCount % 2;
            switch (act2) {
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

        this.halfDead = false;
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.currentHealth <= 0) {
            TOGETOGE.mmkHealth = true;
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
        }
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("ARSBoss");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

package monster.caicai;

import basemod.BaseMod;
import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import monster.act3.CarStstoryPower;
import vfx.CraneMindblastEffect;

import java.util.Iterator;

public class Car extends AbstractSpriterMonster {
    public static final String ID = "Car";
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

    public Car(float x, float y) {
        super(NAME, ID, 200, 0.0F, 0.0F, 300.0F, 250.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.loadAnimation("images/characters/defect/idle/skeleton.atlas", "images/characters/defect/idle/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(0.9F);
        this.flipHorizontal = true;
        this.damage.add(new DamageInfo(this, 20));
        this.damage.add(new DamageInfo(this, 6));
        this.damage.add(new DamageInfo(this, 999999999));
        this.damage.add(new DamageInfo(this, 30));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }



    public void die() {

        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
        while (var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            if (m.id.equals(caicai.ID)) {

                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(m,999999999), AttackEffect.SLASH_HORIZONTAL, true));
            }
        }

        super.die();
//        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//
//        while(var4.hasNext()) {
//            AbstractMonster m2 = (AbstractMonster)var4.next();
//            if (!m2.isDeadOrEscaped() && m2.id.equals(Crane.ID)) {
//                ((Crane)m2).status = "destory";
//                this.addToBot(new RollMoveAction((AbstractMonster)m2));
//
//            }
//        }

    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                this.addToBot(new VFXAction(this, new CraneMindblastEffect(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, true), 0.5F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.NONE, true));
                break;
            case 1:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, true));
                }

                break;
            case 2:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));

                }
                break;

            case 5:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new ConstrictedPower(AbstractDungeon.player, this, 5), 5, AttackEffect.POISON));
                break;
            case 99:
                this.firstTurn = false;
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
            int act = this.moveCount % 2;
            switch (act) {
                case 0: {
                    this.setMove( (byte)0, Intent.ATTACK, 20, 1, false);
                    break;
                }
                case 1: {
                    this.setMove( (byte)1, Intent.ATTACK, 6, 3, true);
                    break;
                }
            }
//        }
        ++this.moveCount;
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new CarStstoryPower(this), 1));
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Car");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

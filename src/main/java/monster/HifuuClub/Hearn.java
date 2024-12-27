package monster.HifuuClub;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import monster.act1.TOGETOGE;
import power.notLive;

import java.util.Iterator;

public class Hearn extends AbstractSpriterMonster {
    public static final String ID = "Hearn";
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

    public Hearn(float x, float y) {
        super(NAME, ID, 120, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.img = new Texture("img/boss/Hearn.png");
        this.type = EnemyType.BOSS;
        this.damage.add(new DamageInfo(this, 10));
        this.damage.add(new DamageInfo(this, 10));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 20));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 0:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 2, true), 2));
                AbstractMonster mo;
                Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var3.hasNext()) {
                    mo = (AbstractMonster)var3.next();
                    if (!mo.isDeadOrEscaped()) {
                        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new PlatedArmorPower((AbstractCreature) mo,2), 2));
                    }
                }
                break;
            case 5:
                for(int i = 0; i < 7; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void die() {

            super.die();

    }


    protected void getMove(int num) {
        if (num < 50) {
            this.setMove(MOVES[0],(byte) 0, Intent.STRONG_DEBUFF, 0, 0, false);
        }else {
            this.setMove((byte) 1, Intent.ATTACK_DEFEND, 10, 1, false);
        }
        this.createIntent();
    }

    public void usePreBattleAction() {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this, (AbstractCreature) this, (AbstractPower) new MoonPower((AbstractCreature) this), 1));
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Hearn");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

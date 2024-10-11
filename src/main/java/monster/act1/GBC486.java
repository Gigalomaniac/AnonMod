package monster.act1;

import bossRoom.AbstractSpriterMonster;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

public class GBC486 extends AbstractSpriterMonster {
    public static final String ID = "s486";
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
    public GBC486(float x, float y) {
        super(NAME, "s486", 100, 0.0F, 0.0F, 300.0F, 520.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.img = new Texture("img/boss/gbc/486_470.png");
        this.damage.add(new DamageInfo(this, 5));
        this.damage.add(new DamageInfo(this, 4));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }



    public void die() {
        if (!AbstractDungeon.getCurrRoom().cannotLose) {
            super.die();
        }
    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
//                if( this.halfDead = true){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player,this.damage.get(0), AttackEffect.SLASH_HORIZONTAL));
//                }else {
//                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT));
//                }
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
//                if( this.halfDead = true){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this, 4, DamageInfo.DamageType.THORNS), AttackEffect.SLASH_HORIZONTAL));
//                }else {
//                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.BLUNT_LIGHT));
//                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        int act = this.moveCount % 2;
        switch (act) {
            case 0: {
                this.setMove(MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, 4, 1, false);
                break;
            }
            case 1: {
                this.setMove(MOVES[1], (byte)1, Intent.ATTACK, 5, 1, false);
                break;
            }
        }
        ++this.moveCount;
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.currentHealth <= 0) {
//            if ((AbstractDungeon.getCurrRoom()).cannotLose == true)
            this.halfDead = true;
            TOGETOGE.s486heatlth = true;
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("s486");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

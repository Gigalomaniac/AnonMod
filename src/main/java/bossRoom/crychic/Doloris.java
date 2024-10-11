package bossRoom.crychic;

import bossRoom.CrychicSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.AveMujicaBelief;
import power.AveMujicaShining;

import java.util.Iterator;

public class Doloris  extends AbstractMonster {
    public static final String ID = "Doloris";
//    private static final MonsterStrings monsterStrings;
    public static final String NAME;
//    public static final String[] MOVES;
//    public static final String[] DIALOG;
    public static final int HP_MIN = 38;
    public static final int HP_MAX = 40;
    public static final int A_2_HP_MIN = 40;
    public static final int A_2_HP_MAX = 45;
    public static final int ATTACK_DMG = 7;
    private static final byte TACKLE = 1;
    private float fireTimer = 0.0F;

    private boolean isdone = false;
    private static final float FIRE_TIME = 0.04F;
    private int moveCount = 0;
    public Doloris(float x, float y, int i) {
        super(NAME, "Doloris", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 145.0F, 240.0F, (String)null, x, y);
        this.setMove((byte)1, Intent.ATTACK, 30);
        this.damage.add(new DamageInfo(this, 30));
//        this.loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
        this.img = new Texture(Gdx.files.internal("img/boss/mujica_Doloris.png"));
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
            this.setHp(300, 300);
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
//                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this, (byte)1, Intent.ATTACK, 30));
            default:
        }
    }

    public void update() {
        super.update();
        if(isdone == false){
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AveMujicaBelief(this), 1));
            isdone= true;
        }

//        if (!this.isDying) {
//            this.fireTimer -= Gdx.graphics.getDeltaTime();
//            if (this.fireTimer < 0.0F) {
//                this.fireTimer = 0.04F;
//                AbstractDungeon.effectList.add(new TorchHeadFireEffect(this.skeleton.getX() + this.skeleton.findBone("fireslot").getX() + 10.0F * Settings.scale, this.skeleton.getY() + this.skeleton.findBone("fireslot").getY() + 110.0F * Settings.scale));
//            }
//        }

    }
    public void usePreBattleAction() {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new AveMujicaBelief(this)));
    }
    protected void getMove(int num) {
        this.setMove("无惧悲伤",(byte)1, Intent.ATTACK, 30);
        ++this.moveCount;
    }

    static {
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead");
        NAME = "Doloris";
//        MOVES = monsterStrings.MOVES;
//        DIALOG = monsterStrings.DIALOG;
    }

    public void die() {
        super.die();
        CrychicSide.Doloris=false;

    }
}

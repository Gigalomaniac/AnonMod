package bossRoom.crychic;

import bossRoom.CrychicSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.AveMujicaBelief;
import power.AveMujicaShining;


import java.util.Iterator;

public class Mortis extends AbstractMonster {
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
    private static final float FIRE_TIME = 0.04F;

    private boolean isdone = false;
    private int moveCount = 0;

    public Mortis(float x, float y, int i) {
        super(NAME, "Mortis", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 145.0F, 240.0F, (String)null, x, y);
        this.setMove((byte)1, Intent.ATTACK, 7);
        this.damage.add(new DamageInfo(this, 1));
//        this.loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
        this.img = new Texture(Gdx.files.internal("img/boss/MortisAvemujica.png"));
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
            this.setHp(300, 300);
    }

    public void takeTurn() {
        label23:
        switch (this.nextMove) {
            case 1:
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
                while(true) {
                    if (!var1.hasNext()) {
                        break label23;
                    }
                    AbstractMonster m = (AbstractMonster)var1.next();
                  if (!m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new AveMujicaShining(m, 2), 2));
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m, this, 20));
                    }
                }
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

    protected void getMove(int num) {
        this.setMove("无惧死亡",(byte)1, Intent.BUFF, 50);
        ++this.moveCount;
    }

    static {
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead");
        NAME = "Mortis";
//        MOVES = monsterStrings.MOVES;
//        DIALOG = monsterStrings.DIALOG;
    }


    public void die() {
        super.die();
        CrychicSide.Mortis=false;

    }
}

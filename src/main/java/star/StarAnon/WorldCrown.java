package star.StarAnon;

import bossRoom.CrychicSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.StarAnon.Love;
import power.StarAnon.Promise;
import star.StarAnonSide;

import java.util.Iterator;

public class WorldCrown  extends AbstractMonster {
    public static final String ID = "WorldCrown";
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

    public AbstractMonster another = null;
    public WorldCrown(float x, float y, int i) {
        super(NAME, "WorldCrown", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 150.0F, 300.0F, (String)null, x, y);
        this.setMove((byte)1, Intent.ATTACK, 30);
//        this.loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
        this.img = new Texture(Gdx.files.internal("img/boss/starAnon/WorldWand250.png"));
            this.setHp(750);
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 10));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                for (int i = 0; i < 10; i++)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;
            default:
        }
    }

    public void update() {
        super.update();
        if( this.another == null){
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                if (m instanceof WorldChalice) {
                    this.another = m;
                    addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new power.StarAnon.WorldCrown(this), 1));
                    break;
                }
            }
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
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new power.StarAnon.WorldCrown(this)));

    }
    protected void getMove(int num) {
        this.setMove((byte)1, Intent.ATTACK, 10, 10, true);
        ++this.moveCount;
    }

    static {
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead");
        NAME = "星遗物－『星杖』";
//        MOVES = monsterStrings.MOVES;
//        DIALOG = monsterStrings.DIALOG;
    }

    public void die() {
        super.die();
    }
}

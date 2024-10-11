package star.StarAnon;

import bossRoom.CrychicSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import power.Shining;
import power.StarAnon.BackTrack;
import power.StarAnon.Love;
import power.musicStart;
import power.songs;
import star.StarAnon.StarAnonPower.StarAnonShining;
import star.StarAnonSide;

import java.util.Iterator;

public class WorldChalice extends AbstractMonster {
    public static final String ID = "WorldChalice";
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
    private int moveCount = 0;
    private boolean isdone = false;
    public AbstractMonster another = null;

    public WorldChalice(float x, float y, int i) {
        super(NAME, "WorldChalice", 100, -5.0F, -20.0F, 150.0F, 300.0F, (String)null, x, y);
        this.setMove((byte)1, Intent.ATTACK, 7);
        this.damage.add(new DamageInfo(this, 1));
//        this.loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
        this.img = new Texture(Gdx.files.internal("img/boss/starAnon/StarChalice250.png"));
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
            this.setHp(750);
    }

    public void takeTurn() {
        label23:
        switch (this.nextMove) {
            case 1:
                for (Iterator<AbstractPower> s = AbstractDungeon.player.powers.iterator(); s.hasNext(); ) {
                    AbstractPower c = s.next();
                    if (c.type == AbstractPower.PowerType.BUFF && !c.ID.equals("beat")&& !c.ID.equals(musicStart.POWER_ID)&& !c.ID.equals(songs.POWER_ID)){
                        s.remove();
                        break;
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), 2, true, true));

        }
    }

    public void update() {
        super.update();
//        if(isdone == false){
//
//
//
//        }
        if(this.another == null){
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new power.StarAnon.WorldChalice(this), 1));
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                if (m instanceof WorldCrown) {
                    this.another = m;
                    break;
                }
            }
            System.out.println("222"+this.another);
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
    public void die() {
        super.die();
    }
    protected void getMove(int num) {
        this.setMove("爱",(byte)1, Intent.DEBUFF, 50);
        ++this.moveCount;
    }

    static {
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead");
        NAME = "星遗物 -『星杯』";
//        MOVES = monsterStrings.MOVES;
//        DIALOG = monsterStrings.DIALOG;
    }
    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new power.StarAnon.WorldChalice(this)));

    }
}

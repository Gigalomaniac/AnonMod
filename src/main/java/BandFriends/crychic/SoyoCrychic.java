package BandFriends.crychic;

import BandFriends.AbstractPet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import pathes.Move;

public class SoyoCrychic extends AbstractPet {

    public static final String NAME;
    public static final String MOVE_NAME;

    private int attackDmg = 6;

    public SoyoCrychic(float x, float y) {
        super(NAME, "SoyoCrychic", 0, -8.0F, 10.0F, 70, 70.0F, (String) null, x, y);
        this.setHp(0);
        this.halfDead = true;
        this.img = new Texture(Gdx.files.internal("img/boss/soyo_340.png"));
        this.damage.add(new DamageInfo(this, attackDmg));
//        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX , this.hb.cY + this.dialogY + 50, 5F, "回来弹吉他…… NL 一起办演唱会……", false));
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
//        this.hideHealthBar();
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new FadingPower(this, 6)));
    }

    @Override
    public void takeTurn() {
//        this.hideHealthBar();
//        AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
//        AbstractMonster monster = getTarget();
//        if (monster != null) {
//            this.damage.get(0).applyPowers(this, monster);
//            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//            AbstractDungeon.player.heal(1, true);
//        } else {
//            AbstractDungeon.actionManager.addToBottom(new SuicideAction(this, false));
//        }
//        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    public void update() {
        super.update();
    }


    /**
     * 隐藏意图
     */
    @Override
    public void createIntent() {

    }
    @Override
    protected void getMove(int i) {
        this.setMove((byte) Move.UNKNOWN.id, Intent.UNKNOWN);
    }

    static {

            NAME = "Tomori";
            MOVE_NAME = "演奏！！！";

    }

    /**
     * 隐藏生命
     */
    public void showHealthBar() {
//        this.hideHealthBar();
    }



    @Override
    protected Texture getAttackIntent() {
        return super.getAttackIntent();
    }
}
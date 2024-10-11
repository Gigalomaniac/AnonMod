package BandFriends;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import pathes.Move;

public class Sakiko extends AbstractPet {

    public static final String NAME;
    public static final String MOVE_NAME;

    private int attackDmg = 6;

    public Sakiko(float x, float y) {
        super(NAME, "Sakiko", 10, -8.0F, 10.0F, 70, 70.0F, (String) null, x, y);
        this.setHp(10000000);
        this.halfDead = false;
        this.img = new Texture(Gdx.files.internal("img/boss/sakiko_320.png"));
        this.damage.add(new DamageInfo(this, attackDmg));
        if (Settings.language == Settings.GameLanguage.ZHS) {
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "Anon酱，这次轮到 NL 我们来拯救你了……", false));
        }else {
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "Anon, it's NL our turn to save you...", false));
        }
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
        if (Settings.language == Settings.GameLanguage.ZHS) {
            NAME = "水蛭";
            MOVE_NAME = "吸！";
        } else {
            NAME = "Leech";
            MOVE_NAME = "Blood Suck!";
        }
    }

    /**
     * 隐藏生命
     */
    public void showHealthBar() {
        this.hideHealthBar();
    }



    @Override
    protected Texture getAttackIntent() {
        return super.getAttackIntent();
    }
}
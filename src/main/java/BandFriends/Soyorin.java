package BandFriends;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import pathes.Move;

public class Soyorin extends AbstractPet {

    public static final String NAME;
    public static final String MOVE_NAME;
    public static boolean Haruhikage = false;
    private int attackDmg = 6;

    public Soyorin(float x, float y) {
        super(NAME, "Soyorin", 10, -8.0F, 10.0F, 70, 70.0F, (String) null, x, y);
        this.setHp(10000000);
        this.halfDead = false;
        this.img = new Texture(Gdx.files.internal("img/boss/soyo_340.png"));
        this.damage.add(new DamageInfo(this, attackDmg));
        if (Settings.language == Settings.GameLanguage.ZHS) {
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "要是没有Anon酱的话，我就…… NL 只要是我能做的，我什么都愿意做", false));
        }else {
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "If I didn't have Anon sauce, I'd just... NL As long as I can do, I will do anything", false));
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
    }

    static {

            NAME = "Tomori";
            MOVE_NAME = "演奏！！！";

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

    public void render(SpriteBatch sb) {
        super.render(sb);
        if(Haruhikage){
            if (Settings.language == Settings.GameLanguage.ZHS) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "为什么…… NL 为什么要演奏春日影啊！", false));
            }else {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY + 50, 5F, "why...  NL  Why is theplaying Haruhikage?!", false));
            }
            this.setMove((byte) Move.UNKNOWN.id, Intent.UNKNOWN);
            Haruhikage= false;
        }

    }
}
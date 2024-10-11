package monster.Roselia;

import bossRoom.AbstractSpriterMonster;
import bossRoom.KarenScene;
import bossRoom.Move;
import bossRoom.effect.*;
import cards.others.KarenShing;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import monster.ShoujoKageki.ShiningRainEffect;
import power.Karen.IllationPower;
import power.Karen.KarenShining;
import power.Karen.RevueStarlight;
import power.RoseliaShining;
import power.Shining;
import power.notLive;

import java.util.ArrayList;
import java.util.Iterator;

public class Rinko extends AbstractSpriterMonster {

    public static final String ID = "Rinko";
    private boolean form1 = true;
    private boolean isAwake = false;

    public static final String NAME;
    private boolean saidPower = false;
    private static boolean ifAwake;

    public static int event = 0;
    private boolean isWeaken = false;
    boolean ifMusic = false;
    private ChangeScene effect;
    private AijoKarenSceneEffect effect3;

    public boolean isWeaken() {
        return isWeaken;
    }

    public void setWeaken(boolean weaken) {
        isWeaken = weaken;
    }

    private boolean firstTurn = true;

    public GenericEventDialog imageEventText = new GenericEventDialog();
    public static String[] DIALOG = {"果然，我是不被需要的吧", "啊"};

    private ArrayList<AwakenedWingParticle> wParticles = new ArrayList<>();
    private int moveCount = 0;

    //    private int Shining = 0;
    private Bone back;

    private int[] powerAf = {
            0, 2, 5, 10, 50
    };
    private int powerAfNum = 0;

    /**
     * 复活后队友
     */
    public static int finInfo = -1;

    boolean isFirstMove = true;
    private int AnonBossPower = 1;



    public Rinko(float x, float y) {
        super(NAME, ID, 0, 0.0F, 00.0F, 300.0F, 300.0F, null, x, y - 50);
        this.img = new Texture("img/boss/Ros/Rinko.png");
        finInfo = -1;
        this.halfDead = true;
        this.damage.add(new DamageInfo(this, 15));
        this.damage.add(new DamageInfo(this, 7));
        this.setMove((byte) Move.UNKNOWN.id, Intent.UNKNOWN);
    }

    private boolean addedPower = false;

    private void addBuff() {
//        if (!hasPower(DeliriousPower.POWER_ID)) {
//            addedPower = true;
//            this.addPower(new DeliriousPower(this, 0));
//            this.addPower(new ImpatiencePower(this));
//        }
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
                break;
            case 1:
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster) var4.next();
                    if (m2.id.equals(Yukina.ID))
                        addToBot((AbstractGameAction) new ApplyPowerAction(m2, m2, (AbstractPower) new RoseliaShining(m2, 1), 1));
                }
                break;

        }
        ++this.moveCount;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }
    @Override
    protected void getMove(int num) {
        if (num < 50) {
            this.setMove((byte) 0, Intent.DEBUFF, 0, 0, true);
        }else {
            this.setMove((byte) 1, Intent.BUFF, 0, 0, true);
        }
        this.createIntent();
    }

    public void usePreBattleAction() {
    }


    @Override
    public void die() {

            super.die();

    }


    private ChangeScene effect2;

    public void changeState(String key) {
        switch (key) {
            case "REBIRTH":
                AbstractDungeon.topLevelEffectsQueue.add(new KarenScene(hb.cX + dialogX - 50, hb.cY + dialogY + 50));
                AbstractDungeon.effectList.add(new LatterEffect(() -> {
                    maxHealth += 100;
                    AnonBossPower = 1;
                    this.halfDead = false;
                    this.isAwake = true;
                    this.effect2 = new ChangeScene(ImageMaster.loadImage("img/boss/bg/shamo.png"));

                    AbstractDungeon.effectsQueue.add(this.effect2);
                    this.img = new Texture("img/char/karen/skin_fan/newKaren.png");
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this, this, RevueStarlight.POWER_ID));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new IllationPower(this, 1), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new KarenShining(this, 3), 3));
                },3F));

                ifAwake = true;
//
                this.moveCount = 0;
                this.nextMove = 0;
                break;

        }
    }

    static {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            NAME = "白金燐子";
        } else {
            NAME = "InnerAnon";
        }
    }


    public void damage(DamageInfo info) {
        super.damage(info);
    }

    public void showHealthBar() {

    }
}

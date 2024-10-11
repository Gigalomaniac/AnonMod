package monster.act3;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.uncommon.KiraKiraDokiDoki;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.InvinciblePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import power.Shining;
import power.notLive;
import utils.Invoker;
import vfx.CraneMindblastEffect;
import vfx.moryyEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Crane extends AbstractSpriterMonster {
    public static final String ID = "Crane";
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

    public String status = "normal";
    private ChangeScene effect;

    public Crane(float x, float y) {
        super(NAME, ID, 800, 100F, 0.0F, 700.0F, 500.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/RC.png");
        this.damage.add(new DamageInfo(this, 7));
        this.damage.add(new DamageInfo(this, 30));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 30));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }



    public void die() {

            super.die();
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            if (!m.isDying && m instanceof LeaderAnon) {
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(m));
                AbstractDungeon.effectList.add(new SpeechBubble(m.hb.cX + m.dialogX, m.hb.cY + m.dialogY + 50, 5F, "“这工地谁爱干谁干！”", false));
            }
        }
    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.NONE, true));
                break;
            case 2:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, true));
                }

                break;
            case 0:
                this.addToBot(new GainBlockAction(this, this, 200));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(AbstractDungeon.player, 2), 2));
                break;
            case 5:

                addToBot(new SFXAction("ATTACK_MAGIC_BEAM_SHORT"));
                addToBot(new VFXAction(new moryyEffect(2.0F), 2F));
                CardCrawlGame.screenShake.rumble(3.0F);
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.NONE, true));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Injury(), 1, true, true));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 3, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                break;
            case 6:
                this.addToBot(new VFXAction(this, new CraneMindblastEffect(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY+this.img.getHeight()/2, true), 0.5F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PlatedArmorPower(this, 20), 20));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        System.out.println(status);
        if(Objects.equals(status, "normal")) {
            int act = this.moveCount % 2;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[0], (byte) 0, Intent.DEBUFF, 4, 1, false);
                    break;
                }
            }
        }else {
            int act = this.moveCount % 2;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[1], (byte)5, Intent.ATTACK_DEBUFF, 7, 3, true);
                    break;
                }
                case 1: {
                    this.setMove(MOVES[1], (byte)6, Intent.ATTACK_BUFF, 30, 1, false);
                    break;
                }
            }
        }
        this.createIntent();
        ++this.moveCount;
    }

    public void usePreBattleAction() {
        AbstractScene TheEndingScene = new TheEndingScene();
        AbstractDungeon.scene= TheEndingScene;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new InvinciblePower(this, 300), 300));
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/dust/DustRoom.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.playTempBgmInstantly("towards the light.mp3", true);
        this.halfDead = false;
    }
    public void damage(DamageInfo info) {
        super.damage(info);
//        if (this.currentHealth <= 0) {
//            this.halfDead = true;
//            TOGETOGE.s486heatlth = true;
//            for (AbstractPower p : this.powers)
//                p.onDeath();
//            for (AbstractRelic r : AbstractDungeon.player.relics)
//                r.onMonsterDeath(this);
//            addToTop((AbstractGameAction)new ClearCardQueueAction());
//            for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
//                AbstractPower p = s.next();
//                if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
//                        .equals("Shackled"))
//                    s.remove();
//            }
//        }
    }
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Crane");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

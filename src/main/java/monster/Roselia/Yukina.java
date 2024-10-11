package monster.Roselia;

import actions.RemoveNumDebuffsAction;
import bossRoom.AbstractSpriterMonster;
import bossRoom.crychic.Amoris;
import bossRoom.crychic.Doloris;
import bossRoom.crychic.Mortis;
import bossRoom.crychic.Timoris;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.others.KarenShing;
import cards.others.Roselia;
import cards.others.SilentMovementAction;
import cards.uncommon.KiraKiraDokiDoki;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import power.KiraKiraShining;
import power.PrepareForLive;
import power.Shining;
import power.notLive;
import utils.Invoker;
import vfx.CraneMindblastEffect;
import vfx.animation.TimeLateEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class Yukina extends AbstractSpriterMonster {
    public static final String ID = "Yukina";
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
    private ChangeScene effect;
    private boolean firstmove = true;
    public Yukina(float x, float y) {
        super(NAME, ID, 400, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/Ros/Yukina.png");
        this.damage.add(new DamageInfo(this, 7));
        this.damage.add(new DamageInfo(this, 10));
        this.damage.add(new DamageInfo(this, 4));
        this.damage.add(new DamageInfo(this, 20));
        this.damage.add(new DamageInfo(this, 50));
    }



    public void die() {
//        super.die();
    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 2:
                for(int i = 0; i < 6; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 3:
                addToBot((AbstractGameAction) new RemoveNumDebuffsAction((AbstractCreature) this));
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 4:
                addToBot((AbstractGameAction) new RemoveDebuffsAction((AbstractCreature) this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(4), AttackEffect.SLASH_HORIZONTAL, true));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                Iterator var5 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var5.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var5.next();
                    if(!m2.isDeadOrEscaped() ){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new KiraKiraShining(AbstractDungeon.player, 2), 2));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                break;
            case 100:
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 4F, DIALOG[0], false));
                AbstractDungeon.actionManager.addToTop(new SilentMovementAction(0));
                this.addToBot(new VFXAction(this, new TimeLateEffect(4), 4F));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Ako(250.0F, 380.0F), false));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Rinko(250.0F, -10.0F), false));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Risa(-250.0F, 380.0F), false));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Sayo(-250.0F, -10.0F), false));
                this.addToBot(new VFXAction(this, new TimeLateEffect(4), 4F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Roselia(), 2));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

        if (firstmove) {
            this.setMove(MOVES[0],(byte) 100, Intent.UNKNOWN, 0, 0, false);
            firstmove = false;
        } else {
            if (this.currentHealth >= maxHealth * 2  / 3) {
                int act = this.moveCount % 2;
                switch (act) {
                    case 0: {
                        this.setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
                        break;
                    }
                    case 1: {
                        this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
                        break;
                    }
                }
            } else {
                if (this.currentHealth >= maxHealth/ 3) {
                    int act = this.moveCount % 2;
                    switch (act) {
                        case 0: {
                            this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(2)).base, 6, true);
                            break;
                        }
                        case 1: {
                            this.setMove( (byte) 3, Intent.ATTACK, this.damage.get(3).base, 1, false);
                            break;
                        }
                    }
                } else {
                    this.setMove(MOVES[1], (byte)4, Intent.ATTACK, ((DamageInfo)this.damage.get(4)).base);
                }
            }
        }
            ++this.moveCount;
        }

    public void usePreBattleAction() {
        AbstractScene TheEndingScene = new TheEndingScene();
        AbstractDungeon.scene= TheEndingScene;
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/RosBG.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        AbstractDungeon.getCurrRoom().playBgmInstantly("Music_LOUDER.mp3");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PrepareForLive(this, 1), 1));

    }
    public void damage(DamageInfo info) {
        super.damage(info);
        if(this.currentHealth <= 0) {
            this.addToBot(new VFXAction(this, new TimeLateEffect(5), 5F));
            AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 4F, DIALOG[1], false));
            AbstractDungeon.actionManager.addToTop(new SilentMovementAction(1));
            AbstractDungeon.effectList.add(new LatterEffect(() -> {
                super.die();
            },5l));
        }
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Yukina");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

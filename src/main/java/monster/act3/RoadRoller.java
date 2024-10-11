package monster.act3;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.uncommon.KiraKiraDokiDoki;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import power.Shining;
import power.notLive;
import star.StarAnonSide;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class RoadRoller extends AbstractSpriterMonster {
    public static final String ID = "RoadRoller";
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

    public RoadRoller(float x, float y) {
        super(NAME, ID, 200, 0.0F, 0.0F, 300.0F, 500.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/dust/RoadRoller.png");
        this.damage.add(new DamageInfo(this, 20));
        this.damage.add(new DamageInfo(this, 8));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 30));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }



    public void die() {

            super.die();
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (!m2.isDeadOrEscaped() && m2.id.equals(Crane.ID)) {
                ((Crane)m2).status = "destory";
                this.addToBot(new RollMoveAction((AbstractMonster)m2));

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
                AbstractCard KiraKiraDokiDoki = new KiraKiraDokiDoki();
                KiraKiraDokiDoki.upgrade();
                KiraKiraDokiDoki.magicNumber =1;
//                this.addToBot(new MakeTempCardInDrawPileAction(KiraKiraDokiDoki, 5, true, true));
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new ConstrictedPower(AbstractDungeon.player, this, 5), 5, AbstractGameAction.AttackEffect.POISON));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
//        if(this.currentHealth >= maxHealth/2) {
//            int act = this.moveCount % 3;
//            switch (act) {
//                case 0: {
//                    this.setMove(MOVES[0], (byte) 0, Intent.DEBUFF, 4, 1, false);
//                    break;
//                }
//                case 1: {
//                    this.setMove(MOVES[0], (byte) 1, Intent.ATTACK_DEBUFF, 20, 1, false);
//                    break;
//                }
//                case 2: {
//                    this.setMove(MOVES[0], (byte) 2, Intent.ATTACK, 8, 3, true);
//                    break;
//                }
//            }
//        }else {
//            int act = this.moveCount % 2;
//            switch (act) {
//                case 0: {
                    this.setMove(MOVES[0], (byte)5, Intent.DEBUFF, 2, 10, true);
//                    break;
//                }
//                case 1: {
//                    this.setMove(MOVES[0], (byte)6, Intent.ATTACK_BUFF, 30, 1, false);
//                    break;
//                }
//            }
//        }
        ++this.moveCount;
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new CarStstoryPower(this), 1));
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("RoadRoller");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

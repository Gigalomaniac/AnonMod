package monster.ShoujoKageki;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import power.Leader;
import power.Shining;
import power.notLive;

import java.util.Iterator;

public class AijoKaren extends AbstractSpriterMonster {
    public static final String ID = "AijoKaren";
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

    public AijoKaren(float x, float y) {
//        super(NAME, ID, 2147483647, 0.0F, 0.0F, 300.0F, 500.0F, (String)null, x, y);
        super(NAME, ID, 2147483647, 0.0F, 20.0F, 300.0F, 350.0F, null, x, y-50);
//        this.loadAnimation("img/char/karen/skin_fan/Karen.atlas", "img/char/karen/skin_fan/Karen.json", 0.55F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTimeScale(1.5F);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/char/karen/skin_fan/newKaren.png");
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

    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var1.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var1.next();
                    if(!m2.isDeadOrEscaped() ){
                        m2.maxHealth +=100;
                        this.addToTop(new HealAction(m2, this, 100));
                    }
                }
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY -50, 5F, "项目工期还很长，没干完不许回家！", false));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                Iterator var2 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//                while(var2.hasNext()) {
//                    AbstractMonster m2 = (AbstractMonster)var2.next();
//                    if(!m2.isDeadOrEscaped() ){
//                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new StrengthPower(AbstractDungeon.player, 3), 3));
//                    }
//                }
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY -50, 5F, "生产队的驴都不敢这样歇着！", false));
                break;
            case 0:
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if(!m2.isDeadOrEscaped() ){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new StrengthPower(AbstractDungeon.player, 3), 3));
                    }
                }
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY -50, 5F, "快回去干活！！！！！", false));
                break;
            case 5:
                for(int i = 0; i < 10; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AttackEffect.SLASH_HORIZONTAL, true));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 3, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new notLive(AbstractDungeon.player, 1, true), 1));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction( AbstractDungeon.player, this.damage.get(3), AttackEffect.SLASH_HORIZONTAL, true));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -2), -2));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {

            int act = this.moveCount % 3;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[0], (byte) 0, Intent.BUFF, 4, 1, false);
                    break;
                }
                case 1: {
                    this.setMove(MOVES[0], (byte) 1, Intent.BUFF, 20, 1, false);
                    break;
                }
                case 2: {
                    this.setMove(MOVES[0], (byte) 2, Intent.BUFF, 8, 3, true);
                    break;
                }
            }

        ++this.moveCount;
    }

    public void usePreBattleAction() {
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new Leader(this), 1));
        AbstractDungeon.actionManager.addToBottom(new HealAction(this, this,2147483647));
        this.effect = new ChangeScene(ImageMaster.loadImage("img/char/karen/skin_fan/preview.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("AijoKaren");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

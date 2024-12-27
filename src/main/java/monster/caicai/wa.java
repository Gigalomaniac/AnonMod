package monster.caicai;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import monster.act3.CarStstoryPower;

public class wa extends AbstractSpriterMonster {
    public static final String ID = "wa";
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
    private boolean firstTurn = true;

    public wa(float x, float y) {
        super(NAME, ID, 100, 0.0F, 0.0F, 300.0F, 300.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/wa.png");
        this.damage.add(new DamageInfo(this, 20));
        this.damage.add(new DamageInfo(this, 6));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 30));
//        this.loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
    }



    public void die() {

            super.die();
//        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//
//        while(var4.hasNext()) {
//            AbstractMonster m2 = (AbstractMonster)var4.next();
//            if (!m2.isDeadOrEscaped() && m2.id.equals(Crane.ID)) {
//                ((Crane)m2).status = "destory";
//                this.addToBot(new RollMoveAction((AbstractMonster)m2));
//
//            }
//        }

    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(new Cleave(),1,true,true));
                break;
            case 1:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, true));
                }

                break;
            case 2:
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));

                }
                break;

            case 5:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new ConstrictedPower(AbstractDungeon.player, this, 5), 5, AttackEffect.POISON));
                break;
            case 99:
                this.firstTurn = false;
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        this.setMove((byte)0, Intent.DEBUFF, 0, 0, false);
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("wa");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

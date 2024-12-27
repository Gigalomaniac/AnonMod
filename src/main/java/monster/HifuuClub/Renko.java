package monster.HifuuClub;

import bossRoom.AbstractSpriterMonster;
import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import cards.uncommon.KiraKiraDokiDoki;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import power.KiraKiraShining;
import power.Shining;
import utils.DreamCardRewards;

import java.util.Iterator;

public class Renko extends AbstractSpriterMonster {
    public static final String ID = "Renko";
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

    public Renko(float x, float y) {
        super(NAME, ID, 120, 0.0F, 0.0F, 180.0F, 360.0F, (String)null, x, y);
        this.dialogY = 30.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        this.img = new Texture("img/boss/Renko.png");
        this.damage.add(new DamageInfo(this, 3));
        this.damage.add(new DamageInfo(this, 5));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 20));
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
            case 2:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.NONE, true));
                Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while(var4.hasNext()) {
                    AbstractMonster m2 = (AbstractMonster)var4.next();
                    if(!m2.isDeadOrEscaped() ){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, this, new KiraKiraShining(AbstractDungeon.player, 2), 2));
                    }
                }
                break;
            case 1:
                for(int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 0:
                for(int i = 0; i < 3; ++i) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.SLASH_HORIZONTAL, true));
                }
                break;
            case 5:
                for(int i = 0; i < 7; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new CleaveEffect(true), 0.15F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(2), AttackEffect.SLASH_HORIZONTAL, true));
                }
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
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
            int act = this.moveCount % 2;
            switch (act) {
                case 0: {
                    this.setMove(MOVES[0], (byte) 0, Intent.ATTACK, 3, 3, true);
                    break;
                }
                case 1: {
                    this.setMove( (byte) 1, Intent.ATTACK, 4, 2, true);
                    break;
                }
//                case 2: {
//                    this.setMove((byte) 2, Intent.ATTACK, 8, 3, true);
//                    break;
//                }
            }

        ++this.moveCount;
    }

    public void usePreBattleAction() {
        AbstractDungeon.scene= new TheEndingScene();
        this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/1600px-剧情背景图10010102.png"));
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.effectsQueue.add(this.effect);
        }));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.playTempBgmInstantly("1. 童祭 ～ Innocent Treasures.mp3", true);
        this.halfDead = false;
        (AbstractDungeon.getCurrRoom()).rewards.add(new DreamCardRewards(3));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this, (AbstractCreature) this, (AbstractPower) new StarPower((AbstractCreature) this), 1));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new InvisibleSunPower((AbstractCreature) AbstractDungeon.player), 1));
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Renko");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}

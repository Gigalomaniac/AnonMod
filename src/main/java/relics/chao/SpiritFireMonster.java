package relics.chao;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireBurningEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;


public class SpiritFireMonster extends AbstractMonster {
    public static final String ID = "monster.SpiritFire";
    private final int strengthAmt;
    private final int burnDmg;
    private final int flameDmg;
    private float fireTimer;

    private static final byte INTENT_DEBUFF = 0;
    private static final byte INTENT_BURN = 1;
    private static final byte INTENT_FLAME = 2;
    private static final byte INTENT_BUFF = 3;

    public SpiritFireMonster() {
        super(CardCrawlGame.languagePack.getMonsterStrings(ID).NAME, ID, 120, 0, 0, 600, 400, "img/relics/fire/blank.png", 0, 0);
        if (AbstractDungeon.ascensionLevel >= 18) {
            strengthAmt = 4;
        } else {
            strengthAmt = 3;
        }

        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(135);
        }

        if (AbstractDungeon.ascensionLevel >= 3) {
            this.burnDmg = 6;
            this.flameDmg = 12;
        } else {
            this.burnDmg = 5;
            this.flameDmg = 10;
        }

        this.damage.add(new DamageInfo(this, this.burnDmg));
        this.damage.add(new DamageInfo(this, this.flameDmg));

        this.type = EnemyType.ELITE;
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.playTempBgmInstantly("ELITE");
    }

    @Override
    public void takeTurn() {
        switch (nextMove) {
            case INTENT_DEBUFF:
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1,true), 1));
                setMove(INTENT_BURN, Intent.ATTACK_DEBUFF, burnDmg);
                break;
            case INTENT_BURN:
                addToBot(new VFXAction(new FireballEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.5F));
                addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
                Burn c = new Burn();
                addToBot(new MakeTempCardInDiscardAction(c, 3));
                setMove(INTENT_FLAME, Intent.ATTACK, flameDmg);
                break;
            case INTENT_FLAME:
                addToBot(new VFXAction(new BorderFlashEffect(Color.CHARTREUSE)));
                addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.FIRE));
                setMove(INTENT_BUFF, Intent.BUFF);
                break;
            case INTENT_BUFF:
                addToBot(new VFXAction(this, new InflameEffect(this), 0.5F));
                addToBot(new ApplyPowerAction(this, this, new StrengthPower(this, this.strengthAmt), this.strengthAmt));
                setMove(INTENT_BURN, Intent.ATTACK_DEBUFF, burnDmg);
                break;
        }
    }

    @Override
    public void update() {
        super.update();

        this.fireTimer -= Gdx.graphics.getDeltaTime();
        if (this.fireTimer < 0.0F) {
            this.fireTimer = 0.05F;
            CampfireBurningEffect e = new CampfireBurningEffect();
            e.renderBehind = true;
            AbstractDungeon.effectList.add(e);
            e = new CampfireBurningEffect();
            e.renderBehind = true;
            AbstractDungeon.effectList.add(e);
        }
    }

    @Override
    protected void getMove(int i) {
        setMove(INTENT_DEBUFF, Intent.STRONG_DEBUFF);
    }

    @Override
    public void die() {
        super.die();
        AbstractDungeon.scene.fadeInAmbiance();
        CardCrawlGame.music.fadeOutTempBGM();
    }
}

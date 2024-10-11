package cards.others;

import Mod.AnonMod;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.UnawakenedPower;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import pathes.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import power.AveMujicaBelief;
import power.untouchableFuturePower;
import vfx.FineTuningEffect;
import vfx.ScaledHammerImprintEffect;


public class UntouchableFuture extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("UntouchableFuture");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "UntouchableFuture";
    public static final String IMG_PATH = "img/card_Anon/star/untouchableFuture_skill.png";
    Color StarShiningColor = new Color(1.0F, 1.0F, 1.0F, 0.7f);
    static float duration = 0f;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public UntouchableFuture() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
//        PurgeField.purge.set(this,true);
//        this.img = ImageMaster.loadImage("img/vfx/bg009152.png");
            this.exhaust = true;
        this.selfRetain = true;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(135, 206, 235));
        this.duration = 0.0F;
        this.setBackgroundTexture("img/1024/bg_skill_star_512.png","img/1024/bg_skill_star.png");
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new untouchableFuturePower(AbstractDungeon.player,0)));
    }
    public void triggerWhenDrawn() {

//        AbstractDungeon.topLevelEffectsQueue.add(new ScaledHammerImprintEffect(this.current_x + 30.0F * this.targetDrawScale * Settings.scale, this.target_y + 120.0F * this.targetDrawScale * Settings.scale, this.targetDrawScale));
//        for (int i = 0; i < 10; i++) {
//            AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(this.current_x + 30.0F * this.targetDrawScale * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale * this.angle,
//                    this.targetDrawScale * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale * this.angle));
//        }
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new UntouchableFuture();
    }

    public void render(SpriteBatch sb) {
        if (!Settings.hideCards) {
        this.render(sb, false);
        }

        this.duration += Gdx.graphics.getDeltaTime();
        if (duration >= 1f) {
            AbstractDungeon.topLevelEffectsQueue.add(new ScaledHammerImprintEffect(this.current_x + 30.0F * this.targetDrawScale * Settings.scale, this.target_y + 120.0F * this.targetDrawScale * Settings.scale, this.targetDrawScale));
            duration = 0f;
        }



    }

}

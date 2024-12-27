package BossRewards;

import BandFriends.Soyorin;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import bossRoom.AnonSide;
import bossRoom.huijinaiyin.effect.AshAnonStory;
import cards.FullImgCard;
import cards.token.Minute;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import com.megacrit.cardcrawl.vfx.cardManip.CardFlashVfx;
import pathes.AbstractCardEnum;
import power.songs;
import relics.Soyorin_relic;

import java.util.Iterator;

/**
 * 每一个瞬间的积累
 */
public class OurCitySongs extends FullImgCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("OurCitySongs");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 0;
    public static final String ID = "OurCitySongs";
    public static final String IMG_PATH = "img/transparent.png";
    public boolean Side = true;
    private  float ChangeTime= 0f;
    private Color ChangeColor= Color.WHITE;
    public OurCitySongs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.LockAnon_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.setBackgroundTexture("img/pink/1024/bg_skill_sp_512.png","img/pink/1024/bg_skill_sp.png");
        this.setPortraitTextures("img/transparent.png","img/transparent.png");
        this.setBannerTexture("img/transparent.png","img/transparent.png");
        this.cardsToPreview = (AbstractCard)new OurCitySongsFake();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(Side)
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "熙熙攘攘、我们的城市"), this.magicNumber));
        else
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new songs((AbstractCreature)p, "空之箱"), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new OurCitySongs();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    public boolean canUpgrade() {
        return false;
    }
    @Override
    public float getTitleFontSize() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            return 16F;
        }
        return -1f;
    }

    public void onRightClick() {
        if(Side){
            Side=false;
            this.setBackgroundTexture("img/pink/1024/NullBox_512.png","img/pink/1024/NullBox.png");
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.name = cardStrings.EXTENDED_DESCRIPTION[1];
            this.cardsToPreview = (AbstractCard)new OurCitySongsNullBox();
            ChangeTime=0;
            AbstractDungeon.topLevelEffectsQueue.add(new FullImgCardFlashVfx(this));
        }
        else{
            this.setBackgroundTexture("img/pink/1024/bg_skill_sp_512.png","img/pink/1024/bg_skill_sp.png");
            Side=true;
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
            this.name = cardStrings.EXTENDED_DESCRIPTION[0];
            this.cardsToPreview = (AbstractCard)new OurCitySongsFake();
            ChangeTime=0;
            AbstractDungeon.topLevelEffectsQueue.add(new FullImgCardFlashVfx(this));
        }

    }
}

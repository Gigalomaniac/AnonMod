package power;

import actions.SkipEnemiesTurnFalseAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;

import java.util.Iterator;

public class SakikoBeat  extends AbstractPower {

        public static final String POWER_ID = "SakikoBeat";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//        public static final String[] DESC;
        private static final int STR_AMT = 2;
        private static final int COUNTDOWN_AMT = 12;

        public SakikoBeat(AbstractCreature owner) {
            this.name = "丰川祥子的节奏";
            this.ID = "SakikoBeat";
            this.owner = owner;
            this.amount = 0;
            this.updateDescription();
//            this.loadRegion("time");
            this.type = PowerType.BUFF;
            String path128 = "img/newbuff/notePower84.png";
            String path48 = "img/newbuff/notePower32.png";
            this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        }

//        public void playApplyPowerSfx() {
//            CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
//        }

        public void updateDescription() {
            this.description =DESCRIPTIONS[0]+(7-this.amount) +DESCRIPTIONS[1];
        }

        public void onAfterUseCard(AbstractCard card, UseCardAction action) {
            this.flashWithoutSound();
            ++this.amount;
            if (this.amount == 7) {
                this.amount = 0;
                this.playApplyPowerSfx();
                musicStart.ifStart =1;
                addToBot((AbstractGameAction)new SkipEnemiesTurnFalseAction());
                AbstractDungeon.actionManager.callEndTurnEarlySequence();
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
//                AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());
//                Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

//                while(var3.hasNext()) {
//                    AbstractMonster m = (AbstractMonster)var3.next();
//                    this.addToBot(new ApplyPowerAction(m, m, new StrengthPower(m, 2), 2));
//                }
            }

            this.updateDescription();
        }

        static {

        }
    }



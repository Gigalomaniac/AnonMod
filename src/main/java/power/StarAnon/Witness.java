package power.StarAnon;

import bossRoom.effect.AshAnonChangeSceneEffect;
import bossRoom.effect.LatterEffect;
import bossRoom.huijinaiyin.effect.TimeEndAnonStory;
import bossRoom.huijinaiyin.effect.WhiteAnonStory;
import cards.others.WorldLegacyAnon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.PostRenderSubscriber;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import patch.FixAscenscionUnlockOnGameoverWinPatch;
import star.RestartRunHelper;

public class Witness extends AbstractPower {

    public static final String POWER_ID ="Witness";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    // 能力的描述
    private float timer;
    int minutes ;
    int remainingSeconds;

    boolean ifEnd = false;
    TimeEndAnonStory  effect ;
    public Witness(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;
        String path128 = "img/newbuff/TimeEndPower84.png";
        String path48 = "img/newbuff/TimeEndPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.timer = 600.0F;
        ifEnd = false;
        // 首次添加能力更新描述
        this.updateDescription();
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {

        this.description = DESCRIPTIONS[0] +minutes+DESCRIPTIONS[1]+remainingSeconds+DESCRIPTIONS[2];
    }


    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

    }
    public void atEndOfRound() {

    }

    public void onEnergyRecharge() {
        flash();
//        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.cardID.equals(WorldLegacyAnon.ID)){
            this.timer += 600f;
            AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX, this.owner.hb.cY + this.owner.dialogY + 50, 5F, "看来你也回想起那个约定了 NL 她还在外面等着你", false));
        }
    }
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        this.timer -= Gdx.graphics.getDeltaTime();
        minutes = (int) ( this.timer / 60);
        remainingSeconds = (int)  this.timer % 60;
        if(this.timer>=0)
        this.description = DESCRIPTIONS[0] +minutes+DESCRIPTIONS[1]+remainingSeconds+DESCRIPTIONS[2];
        if(this.timer <= 0F && !ifEnd){
            addToTop((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this.owner));
            this.effect = new TimeEndAnonStory();
            AbstractDungeon.effectList.add(new LatterEffect(() -> {
                AbstractDungeon.player.hand.group.clear();
                AbstractDungeon.topLevelEffectsQueue.add(this.effect);
            }));
            AbstractDungeon.effectList.add(new LatterEffect(() -> {
                RestartRunHelper.queuedRoomRestart = true;
            },7f));
            ifEnd = true;
        }
    }

}


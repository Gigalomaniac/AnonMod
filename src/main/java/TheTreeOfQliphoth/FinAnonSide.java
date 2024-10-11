package TheTreeOfQliphoth;


import bossRoom.AbstractSpriterMonster;
import bossRoom.AnonSide;
import bossRoom.InnerFavillaeSide;
import bossRoom.Move;
import bossRoom.effect.*;
import bossRoom.huijinaiyin.effect.WhiteAnonStory;
import cards.others.Anon_Def;
import cards.others.Anon_Strike;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import power.*;
import power.StarAnon.*;
import relics.chao.ThirdPerspectiveViewPatches;
import star.StarAnon.WorldChalice;
import star.StarAnon.WorldCrown;
import utils.Invoker;

import java.util.ArrayList;
import java.util.Iterator;

public class FinAnonSide extends AbstractSpriterMonster {

    public static final String NAME;

    public static int event = 0;
    private boolean isWeaken = false;
    public boolean isWeaken() {
        return isWeaken;
    }

    public void setWeaken(boolean weaken) {
        isWeaken = weaken;
    }


    public GenericEventDialog imageEventText = new GenericEventDialog();
    public static String[] DIALOG = {"果然，我是不被需要的吧", "啊"};

    private ArrayList<AwakenedWingParticle> wParticles = new ArrayList<>();


    public static boolean Doloris = true;

    public static boolean Amoris = true;



    public static boolean Haruhikage = false;


    public static String id = "StarAnon";
    public FinAnonSide() {
        super(NAME, id, 30, -8.0F, 0.0F, 300.0F, 300.0F, null, -300, 0);
        this.img = new Texture("img/boss/TogawaSakiko.png");
        this.type = EnemyType.BOSS;
        this.setHp(30);
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 50));
        this.damage.add(new DamageInfo(this, 3));
        this.setMove((byte) Move.UNKNOWN.id, Intent.MAGIC);
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "下次再见咯！", false));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }
    @Override
    protected void getMove(int i) {
       this.setMove("感谢游玩哦", (byte) 0, Intent.MAGIC, 0, 0, false);
       this.createIntent();
    }

    public void usePreBattleAction() {
        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "终于，你来到了这里……", false));
            ThirdPerspectiveViewPatches.setEnable(true);
        AbstractDungeon.effectsQueue.add(new ChangeScene(ImageMaster.loadImage("img/boss/bg00098.png")));
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.silenceBGMInstantly();
        CardCrawlGame.music.playTempBgmInstantly("03. 迷星叫 -Instrumental-.mp3", true);
        cursepre();
    }
    private void cursepre() {
        Iterator<AbstractCard> var1 = AbstractDungeon.player.drawPile.group.iterator();
        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            addToBot(new VFXAction(new ExhaustEmberEffect(c.current_x, c.current_y)));
            var1.remove();
        }

        Iterator<AbstractCard> var2 = AbstractDungeon.player.discardPile.group.iterator();
        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            addToBot(new VFXAction(new ExhaustEmberEffect(c.current_x, c.current_y)));
            var2.remove();
        }

        Iterator<AbstractCard> var3 = AbstractDungeon.player.hand.group.iterator();
        while (var3.hasNext()) {
            AbstractCard c = (AbstractCard)var3.next();
            addToBot(new VFXAction(new ExhaustEmberEffect(c.current_x, c.current_y)));
            var3.remove();
        }

        AbstractDungeon.effectList.add(new ExhaustEmberEffect(AbstractDungeon.topPanel.deckHb.cX, AbstractDungeon.topPanel.deckHb.cY));
        AbstractDungeon.player.masterDeck.clear();

        ArrayList<AbstractRelic> relicsToRemove = new ArrayList<AbstractRelic>();
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            relicsToRemove.add(relic);
        }
        for (AbstractRelic relic : relicsToRemove) {
            AbstractDungeon.effectList.add(new ExhaustEmberEffect(relic.currentX, relic.currentY));
            AbstractDungeon.player.loseRelic(relic.relicId);
        }
        AbstractDungeon.player.relics.removeAll(relicsToRemove);

        ArrayList<AbstractPotion> potionToRemove = new ArrayList<AbstractPotion>(AbstractDungeon.player.potions);
        if (potionToRemove.isEmpty()) {
            AbstractDungeon.player.potions.removeAll(potionToRemove);
        }

        AbstractCard s = (new Anon_Strike()).makeCopy();
        s.upgrade();
        this.addToBot(new MakeTempCardInHandAction(s, 4));
        AbstractCard a = (new Anon_Def()).makeCopy();
        a.upgrade();
        this.addToBot(new MakeTempCardInHandAction(a, 1));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void die() {
            super.die();
        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX - 50, this.hb.cY + this.dialogY + 50, 2.5F, "ByeBye！下一个轮回再见！", false));
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        imageEventText.render(sb);

    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        EnemyMoveInfo moveInfo = Invoker.getField(this, "move");
        if (moveInfo != null && moveInfo.intent == Intent.ATTACK) {
            if ((int) Invoker.getField(this, "intentDmg") != -1) {
                Invoker.setField(this, "intentDmg", 1);
            }
            Invoker.invoke(this, "updateIntentTip");
        }
    }
    public void changeState(String key) {
        switch (key) {

        }
    }

    static {
        NAME = "丰川祥子";
    }

    public void damage(DamageInfo info) {
        super.damage(info);
    }
    private WhiteAnonChangeScene effect;
    private ABChangeScene effect2;
    private MoveChangeScene moveChangeScene;


    public void priceForStop() {
    }
}

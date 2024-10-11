package relics;
import actions.movie.StarAnonGuitarAction;
import basemod.abstracts.CustomSavable;
import bossRoom.effect.LatterEffect;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
import star.BeyondTheStar;

public class StarAnonGuitar extends ClickableRelic  implements  CustomSavable<Integer> {

    public static final String ID = "StarAnonGuitar";
    private static final String IMG = "img/relics/gituar.png";
    private static final String IMG_OTL =  "img/relics/gituar.png";
    private Integer index;

    public StarAnonGuitar() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new StarAnonGuitar();
    }

    //右键使用
    public void onRightClick() {

        if(this.counter !=  -2){

            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                try {
                    AbstractDungeon.effectList.add(new LatterEffect(() -> {
                        AbstractDungeon.actionManager.addToBottom(new StarAnonGuitarAction());
                    }, 1f));


                } catch (Exception var4) {
                }

            }else {

                    CardCrawlGame.nextDungeon =  BeyondTheStar.ID;
                    CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(BeyondTheStar.ID);
                    AbstractDungeon.actNum = 4;
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            }
        this.usedUp();
        this.setCounter(-2);
}

    }

    @Override
    public void onEquip() {
        super.onEquip();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public Integer onSave() {
        if(this.usedUp){
            return 1;
        }
        return 0;
    }
    @Override
    public void onLoad(Integer integer) {
        if(integer ==  1){
            this.usedUp();
        }
    }

    private void prepareTransition() {
        AbstractDungeon.player.hand.group.clear();
        AbstractDungeon.actionManager.clear();
        AbstractDungeon.effectsQueue.clear();
        AbstractDungeon.effectList.clear();

        for(int i = AbstractDungeon.topLevelEffects.size() - 1; i > 0; --i) {
            if (AbstractDungeon.topLevelEffects.get(i) instanceof BattleStartEffect) {
                AbstractDungeon.topLevelEffects.remove(i);
            }
        }

    }
}

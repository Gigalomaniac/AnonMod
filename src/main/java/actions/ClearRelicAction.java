//package actions;
//
//
//import basemod.ReflectionHacks;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
//
//public class ClearRelicAction extends AbstractGameEffect {
//
//    private AbstractRelic relic;
//    private String id;
//
//    public ClearRelicAction(String id) {
//  this.id = id;
//    }
//
//
//    public void update() {
//
////        AbstractDungeon.player.relics.clear();
//        AbstractDungeon.player.loseRelic(id);
//
//
//        this.isDone = true;
//    }
//
//    @Override
//    public void render(SpriteBatch spriteBatch) {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
//

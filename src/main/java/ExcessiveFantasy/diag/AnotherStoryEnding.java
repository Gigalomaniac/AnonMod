 package ExcessiveFantasy.diag;

 import ExcessiveFantasy.ExcessiveFantasyWorld;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.graphics.g2d.TextureRegion;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.dungeons.Exordium;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.localization.EventStrings;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


 public class AnotherStoryEnding extends AbstractGameEffect {
   public static final String ID = "AnotherStoryEnding";
   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
   public static final String NAME = eventStrings.NAME;
   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
   public static final String[] OPTIONS = eventStrings.OPTIONS;
   private final TextureRegion SCENE1;
   private final TextureRegion SCENE2;
   private final TextureRegion TEXTBOX;
   private final Dialog roomEventText = new Dialog();
   private int dialogue;
   private Hitbox hb;
   private boolean show;
   private int end;

   public AnotherStoryEnding(int start, int end) {

     Texture SCENE1_TEXTURE =  new Texture("img/boss/fullblack.png");
     this.SCENE1 = new TextureRegion(SCENE1_TEXTURE);
     Texture SCENE2_TEXTURE =  new Texture("img/boss/AnonHouse.png");
     this.SCENE2 = new TextureRegion(SCENE2_TEXTURE);
     Texture TEXTBOX_TEXTURE =  new Texture("img/boss/AnonTalk.png");
     this.TEXTBOX = new TextureRegion(TEXTBOX_TEXTURE);
     this.dialogue = start;
     this.end = end;
     this.hb = new Hitbox(Settings.WIDTH, Settings.HEIGHT);
     this.hb.x = 0.0F;
     this.hb.y = 0.0F;
     this.show = true;
   }

   public void update() {
     if (this.show) {
       this.show = false;
       AbstractDungeon.isScreenUp = true;
       this.roomEventText.show(DESCRIPTIONS[this.dialogue]);
     }
     this.hb.update();
     if (this.hb.hovered && InputHelper.justClickedLeft) {
       InputHelper.justClickedLeft = false;
       this.hb.clickStarted = true;
     }
     if (this.hb.clicked) {
       this.hb.clicked = false;
       nextDialogue();
     }
     this.roomEventText.update();
   }

   private void nextDialogue() {
     if (this.dialogue < this.end) {
       this.dialogue++;
       if (this.dialogue == 4) CardCrawlGame.fadeIn(1.0F);
       this.roomEventText.updateBodyText(DESCRIPTIONS[this.dialogue]);
     } else {
       AbstractDungeon.isScreenUp = false;
       this.isDone = true;
       exit();
     }
   }

   private void exit() {
       AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            CardCrawlGame.nextDungeon = ExcessiveFantasyWorld.ID;
            CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen(Exordium.ID);
            AbstractDungeon.actNum = 5;
   }

   public void render(SpriteBatch sb) {
     sb.setColor(Color.WHITE.cpy());
     if (this.dialogue >= 4) {
       sb.draw(this.SCENE2, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
     } else {
       sb.draw(this.SCENE1, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
     }
     sb.draw(this.TEXTBOX, 0, 0, Settings.WIDTH, Settings.HEIGHT/3F);
     this.roomEventText.render(sb);
   }


   public void dispose() { this.roomEventText.clear(); }
 }

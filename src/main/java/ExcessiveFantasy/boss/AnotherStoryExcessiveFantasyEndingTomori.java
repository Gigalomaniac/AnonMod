 package ExcessiveFantasy.boss;

 import ExcessiveFantasy.diag.Dialog;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.graphics.g2d.TextureRegion;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.FontHelper;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.localization.EventStrings;
 import com.megacrit.cardcrawl.screens.VictoryScreen;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


 public class AnotherStoryExcessiveFantasyEndingTomori extends AbstractGameEffect {
   public static final String ID = "AnotherStoryExcessiveFantasyEndingTomori";
     private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
     public static final String NAME = eventStrings.NAME;
     public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
     public static final String[] OPTIONS = eventStrings.OPTIONS;
     private final TextureRegion SCENE1;
     private final TextureRegion SCENE2;
     private final TextureRegion SCENE3;
     private final TextureRegion SCENE4;
     private final TextureRegion TEXTBOX;
     private final Dialog roomEventText = new Dialog();
     private int dialogue;
     private Hitbox hb;
     private boolean show;
     private int end;
     private final TextureRegion AnonHello  = new TextureRegion(new Texture("img/webgal/AnonHello.png"));
     private final TextureRegion AnonThink  = new TextureRegion(new Texture("img/webgal/AnonThink.png"));
     private final TextureRegion AnonShame  = new TextureRegion(new Texture("img/webgal/AnonShame.png"));
     private final TextureRegion AnonProud  = new TextureRegion(new Texture("img/webgal/AnonProud.png"));
     private final TextureRegion AnonStrange1  = new TextureRegion(new Texture("img/webgal/Anon-_-.png"));
     private final TextureRegion AnonStrange2  = new TextureRegion(new Texture("img/webgal/AnonO_o.png"));
     private final TextureRegion AnonIdea  = new TextureRegion(new Texture("img/webgal/AnonIdea.png"));
     private final TextureRegion SoyoKime  = new TextureRegion(new Texture("img/webgal/SoyoKime.png"));
     private final TextureRegion SoyoSerious  = new TextureRegion(new Texture("img/webgal/SoyoSerious.png"));
     private final TextureRegion SoyoThinking  = new TextureRegion(new Texture("img/webgal/SoyoThinking.png"));
     private final TextureRegion SoyoSmile  = new TextureRegion(new Texture("img/webgal/SoyoSmile.png"));
     private final TextureRegion SoyoRight  = new TextureRegion(new Texture("img/webgal/SoyoRight.png"));
     private final TextureRegion[] Anon = {AnonHello,AnonThink,AnonShame,AnonProud,AnonStrange1,AnonStrange2,AnonIdea};
     private final TextureRegion[] Soyo = {SoyoKime,SoyoSerious,SoyoThinking,SoyoSmile,SoyoRight};
     private int AnonIndex = 0;
     private int SoyoIndex = 0;
     private final String[] DiagName = {"千早爱音","高松灯","椎名立希","长崎爽世","要乐奈"," "};
     private int DiagNameIndex = 0;
     public AnotherStoryExcessiveFantasyEndingTomori(int start, int end) {

         Texture SCENE1_TEXTURE =  new Texture("img/boss/AnonHouse.png");
         this.SCENE1 = new TextureRegion(SCENE1_TEXTURE);
         Texture SCENE2_TEXTURE =  new Texture("img/boss/bg00911.png");
         this.SCENE2 = new TextureRegion(SCENE2_TEXTURE);
         Texture SCENE3_TEXTURE =  new Texture("img/boss/fullblack.png");
         this.SCENE3 = new TextureRegion(SCENE3_TEXTURE);
         Texture SCENE4_TEXTURE =  new Texture("img/boss/ExcessiveFantasyTheEnding.png");
         this.SCENE4 = new TextureRegion(SCENE4_TEXTURE);
         Texture TEXTBOX_TEXTURE =  new Texture("img/webgal/TalkWithputName.png");
         this.TEXTBOX = new TextureRegion(TEXTBOX_TEXTURE);
         this.dialogue = start;
         this.end = end;
         this.hb = new Hitbox(Settings.WIDTH, Settings.HEIGHT);
         this.hb.x = 0.0F;
         this.hb.y = 0.0F;
         this.show = true;
     }

     public void update() {
         switch (this.dialogue){
             case 0:
             case 1:
             case 2:
             case 4:
             case 6:
             case 7:
                 DiagNameIndex = 0;
                 break;
             case 3:
             case 5:
                 DiagNameIndex = 1;
                 break;
             default:
                 DiagNameIndex = 5;
                 break;
         }
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
             if (this.dialogue == 7) CardCrawlGame.fadeIn(1.0F);
//      switch (this.dialogue){
//          case 1:
////              AnonIndex = 0;
//              SoyoIndex = 0;
//              break;
//          case 2:
//              AnonIndex = 6;
////              SoyoIndex = 0;
//              break;
//          case 3:
////              AnonIndex = 0;
//              SoyoIndex = 1;
//              break;
//          case 4:
//              AnonIndex = 2;
////              SoyoIndex = 0;
//              break;
//          case 5:
////              AnonIndex = 0;
//              SoyoIndex = 2;
//              break;
//          case 6:
//              AnonIndex = 1;
////              SoyoIndex = 0;
//              break;
//          case 7:
//              AnonIndex = 4;
//              SoyoIndex = 1;
//              break;
//          case 8:
//              AnonIndex = 5;
////              SoyoIndex = 0;
//              break;
//          case 9:
////              AnonIndex = 0;
//              SoyoIndex = 4;
//              break;
//          case 10:
//              AnonIndex = 3;
////              SoyoIndex = 0;
//              break;
//          case 11:
////              AnonIndex = 0;
//              SoyoIndex = 3;
//              break;
//
//          default:
//              AnonIndex = 3;
//              SoyoIndex = 0;
//              break;
//      }
             this.roomEventText.updateBodyText(DESCRIPTIONS[this.dialogue]);
         } else {
             AbstractDungeon.isScreenUp = false;
             this.isDone = true;
             exit();
         }
     }

     private void exit() {
         AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
   AbstractDungeon.victoryScreen = new VictoryScreen(null);
     }

     public void render(SpriteBatch sb) {
         sb.setColor(Color.WHITE.cpy());
         switch (this.dialogue){
             case 0:
             case 6:
                 sb.draw(this.SCENE3, 0.0F, 0.0F,  Settings.WIDTH, Settings.HEIGHT);
                 break;
             case 1:
             case 2:
                 sb.draw(this.SCENE1, 0.0F, 0.0F,  Settings.WIDTH, Settings.HEIGHT);
                 break;
             case 3:
             case 4:
             case 5:
                 sb.draw(this.SCENE2, 0.0F, 0.0F,  Settings.WIDTH, Settings.HEIGHT);
                 break;
             case 7:
             case 8:
             case 9:
             default:
                 sb.draw(this.SCENE4,  (Settings.WIDTH-this.SCENE4.getRegionWidth())/2, (Settings.HEIGHT-this.SCENE4.getRegionHeight())/2,  this.SCENE4.getRegionWidth(), this.SCENE4.getRegionHeight());
                 break;
         }

//         sb.draw(this.SCENE1, 0.0F, 0.0F,  Settings.WIDTH, Settings.HEIGHT);
//     }
//       switch (this.dialogue){
//           case 0:
//               sb.draw(this.AnonHello, 0.0F, AnonHello.getRegionHeight()*-0.15f,  AnonHello.getRegionWidth(), AnonHello.getRegionHeight());
//               break;
//           default:
//               sb.draw(this.Anon[AnonIndex], 0.0F, this.Anon[AnonIndex].getRegionHeight()*-0.15f,  this.Anon[AnonIndex].getRegionWidth(), this.Anon[AnonIndex].getRegionHeight());
//               sb.draw(this.Soyo[SoyoIndex], Settings.WIDTH/2, this.Soyo[SoyoIndex].getRegionHeight()*-0.15f,  this.Soyo[SoyoIndex].getRegionWidth(), this.Soyo[SoyoIndex].getRegionHeight());
//               break;
//       }
         sb.draw(this.TEXTBOX, 0, 0,  Settings.WIDTH, Settings.HEIGHT/3);
         FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, DiagName[DiagNameIndex],  Settings.HEIGHT * 0.2F, Settings.HEIGHT/3-FontHelper.topPanelInfoFont.getLineHeight()*0.7f, Color.WHITE.cpy());
         this.roomEventText.render(sb);
     }

     public void dispose() { this.roomEventText.clear(); }
 }

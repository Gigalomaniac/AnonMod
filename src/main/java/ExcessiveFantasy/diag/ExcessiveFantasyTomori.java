 package ExcessiveFantasy.diag;

 import ExcessiveFantasy.ExcessiveFantasySecondEventRoom;
 import bandFriendsCard.Soyorin;
 import bandFriendsCard.Tomori;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.graphics.g2d.TextureRegion;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.FontHelper;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.localization.EventStrings;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;


 public class ExcessiveFantasyTomori extends AbstractGameEffect {
   public static final String ID = "ExcessiveFantasyTomori";
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
     private final TextureRegion AnonHello  = new TextureRegion(new Texture("img/webgal/AnonHello.png"));
     private final TextureRegion AnonThink  = new TextureRegion(new Texture("img/webgal/AnonThink.png"));
     private final TextureRegion AnonShame  = new TextureRegion(new Texture("img/webgal/AnonShame.png"));
     private final TextureRegion AnonProud  = new TextureRegion(new Texture("img/webgal/AnonProud.png"));
     private final TextureRegion AnonStrange1  = new TextureRegion(new Texture("img/webgal/Anon-_-.png"));
     private final TextureRegion AnonStrange2  = new TextureRegion(new Texture("img/webgal/AnonO_o.png"));
     private final TextureRegion AnonIdea  = new TextureRegion(new Texture("img/webgal/AnonIdea.png"));
     private final TextureRegion TomoriSerious  = new TextureRegion(new Texture("img/webgal/TomoriSerious.png"));
     private final TextureRegion Tomorikime  = new TextureRegion(new Texture("img/webgal/Tomorikime.png"));
     private final TextureRegion TomoriCry  = new TextureRegion(new Texture("img/webgal/TomoriCry.png"));
     private final TextureRegion TomoriHappy  = new TextureRegion(new Texture("img/webgal/TomoriHappy.png"));
     private final TextureRegion TomoriShy  = new TextureRegion(new Texture("img/webgal/TomoriShy.png"));
     private final TextureRegion TomoriThink  = new TextureRegion(new Texture("img/webgal/TomoriThink.png"));
     private final TextureRegion[] Anon = {AnonHello,AnonThink,AnonShame,AnonProud,AnonStrange1,AnonStrange2,AnonIdea};
     private final TextureRegion[] Tomori = {TomoriSerious,Tomorikime,TomoriCry,TomoriHappy,TomoriShy,TomoriThink};
     private int AnonIndex = 0;
     private int TomoriIndex = 0;
     private final String[] DiagName = {"千早爱音","高松灯","椎名立希","长崎爽世","要乐奈"," "};
     private int DiagNameIndex = 0;
   public ExcessiveFantasyTomori(int start, int end) {

     Texture SCENE1_TEXTURE =  new Texture("img/boss/bg00170.png");
     this.SCENE1 = new TextureRegion(SCENE1_TEXTURE);
     Texture SCENE2_TEXTURE =  new Texture("img/boss/bg00918 (1).png");
     this.SCENE2 = new TextureRegion(SCENE2_TEXTURE);
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
           case 2:
           case 4:
           case 6:
           case 8:
           case 10:
               DiagNameIndex = 0;
               break;
           case 1:
           case 5:
           case 7:
           case 9:
           case 11:
               DiagNameIndex = 1;
               break;
           case 3 :
               DiagNameIndex = 5;

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
      switch (this.dialogue){
          case 0:
              AnonIndex = 0;
              TomoriIndex = 4;
              break;
          case 1:
              AnonIndex = 1;
//              TomoriIndex = 0;
              break;
          case 2:
              AnonIndex = 4;
              TomoriIndex = 5;
              break;
          case 3:
              AnonIndex = 5;
              TomoriIndex = 0;
              break;
          case 4:
              AnonIndex = 2;
//              TomoriIndex = 0;
              break;
          case 5:
//              AnonIndex = 0;
              TomoriIndex = 2;
              break;
          case 6:
              AnonIndex = 1;
//              TomoriIndex = 0;
              break;
          case 7:
//              AnonIndex = 4;
              TomoriIndex = 1;
              break;
          case 8:
              AnonIndex = 5;
//              TomoriIndex = 0;
              break;
          case 9:
//              AnonIndex = 0;
              TomoriIndex = 4;
              break;
          case 10:
              AnonIndex = 3;
//              TomoriIndex = 0;
              break;
          case 11:
              AnonIndex = 2;
              TomoriIndex = 4;
              break;
          default:
              AnonIndex = 3;
              TomoriIndex = 5;
              break;
      }
       this.roomEventText.updateBodyText(DESCRIPTIONS[this.dialogue]);
     } else {
       AbstractDungeon.isScreenUp = false;
       this.isDone = true;
       exit();
     }
   }

   private void exit() {
       AbstractCard Tomori = new Tomori();
       Tomori.upgrade();
           AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(Tomori, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
       AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            if(AbstractDungeon.getCurrRoom() instanceof ExcessiveFantasySecondEventRoom){
                ((ExcessiveFantasySecondEventRoom) AbstractDungeon.getCurrRoom()).done=true;
            }
   }

   public void render(SpriteBatch sb) {
     sb.setColor(Color.WHITE.cpy());
       sb.draw(this.SCENE1,   Settings.WIDTH, Settings.HEIGHT);
//     if (this.dialogue >= 4) {
//       sb.draw(this.SCENE2,   Settings.WIDTH, Settings.HEIGHT);
//     } else {
       sb.draw(this.SCENE1, 0.0F, 0.0F,   Settings.WIDTH, Settings.HEIGHT);
//     }
       switch (this.dialogue){
           default:
               sb.draw(this.Anon[AnonIndex], 0.0F, this.Anon[AnonIndex].getRegionHeight()*-0.15f,  this.Anon[AnonIndex].getRegionWidth(), this.Anon[AnonIndex].getRegionHeight());
               sb.draw(this.Tomori[TomoriIndex], Settings.WIDTH/2, this.Tomori[TomoriIndex].getRegionHeight()*-0.15f,  this.Tomori[TomoriIndex].getRegionWidth(), this.Tomori[TomoriIndex].getRegionHeight());
               break;
       }
     sb.draw(this.TEXTBOX, 0, 0,  Settings.WIDTH, Settings.HEIGHT/3);
     FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, DiagName[DiagNameIndex],  Settings.HEIGHT * 0.2F, Settings.HEIGHT/3-FontHelper.topPanelInfoFont.getLineHeight()*0.7f, Color.WHITE.cpy());
     this.roomEventText.render(sb);
   }

   public void dispose() { this.roomEventText.clear(); }
 }

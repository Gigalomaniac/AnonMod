 package ExcessiveFantasy.diag;

 import ExcessiveFantasy.ExcessiveFantasyFirstEventRoom;
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
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


 public class ExcessiveFantasyOne extends AbstractGameEffect {
   public static final String ID = "ExcessiveFantasyOne";
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
     private final TextureRegion AnonThink  = new TextureRegion(new Texture("img/webgal/AnonThink.png"));
     private final TextureRegion AnonShame  = new TextureRegion(new Texture("img/webgal/AnonShame.png"));
     private final TextureRegion AnonProud  = new TextureRegion(new Texture("img/webgal/AnonProud.png"));
     private final TextureRegion TomoriSerious  = new TextureRegion(new Texture("img/webgal/TomoriSerious.png"));
     private final TextureRegion Tomorikime  = new TextureRegion(new Texture("img/webgal/Tomorikime.png"));
     private final TextureRegion RikkiSigh  = new TextureRegion(new Texture("img/webgal/RikkiSigh.png"));
     private final TextureRegion SoyoKime  = new TextureRegion(new Texture("img/webgal/SoyoKime.png"));
     private final TextureRegion RanaHappy  = new TextureRegion(new Texture("img/webgal/RanaHappy.png"));

     private final String[] DiagName = {"千早爱音","高松灯","椎名立希","长崎爽世","要乐奈"};
     private int DiagNameIndex = 0;
   public ExcessiveFantasyOne(int start, int end) {

     Texture SCENE1_TEXTURE =  new Texture("img/boss/bg00911.png");
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
           case 5:
           case 8:
           case 15:
               DiagNameIndex = 0;
               break;
           case 1:
           case 3:
           case 6:
               DiagNameIndex = 1;
               break;
           case 10:
               DiagNameIndex = 3;
               break;
           case 7:
           case 12:
           case 14:
               DiagNameIndex = 2;
               break;
           case 11:
           case 13:
               DiagNameIndex = 4;
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
            if(AbstractDungeon.getCurrRoom() instanceof ExcessiveFantasyFirstEventRoom){
                ((ExcessiveFantasyFirstEventRoom) AbstractDungeon.getCurrRoom()).done=true;
            }
   }

   public void render(SpriteBatch sb) {
     sb.setColor(Color.WHITE.cpy());
       sb.draw(this.SCENE1, 0.0F, 0.0F,   Settings.WIDTH, Settings.HEIGHT);
     if (this.dialogue >= 4) {
       sb.draw(this.SCENE2, 0.0F, 0.0F,   Settings.WIDTH, Settings.HEIGHT);
     } else {
       sb.draw(this.SCENE1, 0.0F, 0.0F,   Settings.WIDTH, Settings.HEIGHT);
     }
     switch (this.dialogue){
         case 0:
             sb.draw(this.AnonThink, 0.0F, AnonThink.getRegionHeight()*-0.15f,  AnonThink.getRegionWidth(), AnonThink.getRegionHeight());
             break;
         case 1:
         case 2:
             sb.draw(this.AnonThink, 0.0F, AnonThink.getRegionHeight()*-0.15f,  AnonThink.getRegionWidth(), AnonThink.getRegionHeight());
             sb.draw(this.TomoriSerious, Settings.WIDTH/2, TomoriSerious.getRegionHeight()*-0.15f,  TomoriSerious.getRegionWidth(), TomoriSerious.getRegionHeight());
             break;
         case 3:
             sb.draw(this.AnonThink, 0.0F, AnonThink.getRegionHeight()*-0.15f,  AnonThink.getRegionWidth(), AnonThink.getRegionHeight());
             sb.draw(this.Tomorikime, Settings.WIDTH/2, Tomorikime.getRegionHeight()*-0.15f,  Tomorikime.getRegionWidth(), Tomorikime.getRegionHeight());
             break;
         case 4:
         case 5:
             sb.draw(this.AnonProud, -50.0F, AnonProud.getRegionHeight()*-0.15f,  AnonProud.getRegionWidth(), AnonProud.getRegionHeight());
             break;
         case 6:
             sb.draw(this.AnonProud, -50.0F, AnonProud.getRegionHeight()*-0.15f,  AnonProud.getRegionWidth(), AnonProud.getRegionHeight());
             sb.draw(this.Tomorikime, Settings.WIDTH/2+50, Tomorikime.getRegionHeight()*-0.15f,  Tomorikime.getRegionWidth(), Tomorikime.getRegionHeight());
             break;
         case 7:
         case 8:
             sb.draw(this.AnonProud, -50.0F, AnonShame.getRegionHeight()*-0.15f,  AnonProud.getRegionWidth(), AnonProud.getRegionHeight());
             sb.draw(this.Tomorikime, Settings.WIDTH/2+50, Tomorikime.getRegionHeight()*-0.15f,  Tomorikime.getRegionWidth(), Tomorikime.getRegionHeight());
             sb.draw(this.RikkiSigh, Settings.WIDTH/3, RikkiSigh.getRegionHeight()*-0.15f,  RikkiSigh.getRegionWidth(), RikkiSigh.getRegionHeight());
             break;
         case 9:
             sb.draw(this.AnonShame, -50.0F, AnonShame.getRegionHeight()*-0.15f,  AnonShame.getRegionWidth(), AnonShame.getRegionHeight());
             sb.draw(this.Tomorikime, Settings.WIDTH/2+50, Tomorikime.getRegionHeight()*-0.15f,  Tomorikime.getRegionWidth(), Tomorikime.getRegionHeight());
             sb.draw(this.RikkiSigh, Settings.WIDTH/3, RikkiSigh.getRegionHeight()*-0.15f,  RikkiSigh.getRegionWidth(), RikkiSigh.getRegionHeight());
             break;
         case 10:
             sb.draw(this.AnonShame, -50.0F, AnonShame.getRegionHeight()*-0.15f,  AnonShame.getRegionWidth(), AnonShame.getRegionHeight());
             sb.draw(this.Tomorikime, Settings.WIDTH/2+50, Tomorikime.getRegionHeight()*-0.15f,  Tomorikime.getRegionWidth(), Tomorikime.getRegionHeight());
             sb.draw(this.RikkiSigh, Settings.WIDTH/3, RikkiSigh.getRegionHeight()*-0.15f,  RikkiSigh.getRegionWidth(), RikkiSigh.getRegionHeight());
             sb.draw(this.SoyoKime, Settings.WIDTH/6, SoyoKime.getRegionHeight()*-0.15f,  SoyoKime.getRegionWidth(), SoyoKime.getRegionHeight());
             break;
         case 11:
             sb.draw(this.RanaHappy, 0, RanaHappy.getRegionHeight()*-0.15f,  RanaHappy.getRegionWidth(), RanaHappy.getRegionHeight());
             break;
         case 12:
         case 13:
         case 14:
             sb.draw(this.RanaHappy, 0, RanaHappy.getRegionHeight()*-0.15f,  RanaHappy.getRegionWidth(), RanaHappy.getRegionHeight());
             sb.draw(this.RikkiSigh, Settings.WIDTH/2, RikkiSigh.getRegionHeight()*-0.15f,  RikkiSigh.getRegionWidth(), RikkiSigh.getRegionHeight());
             break;
     }
     sb.draw(this.TEXTBOX, 0, 0,  Settings.WIDTH, Settings.HEIGHT/3);
     FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, DiagName[DiagNameIndex],  Settings.HEIGHT * 0.2F, Settings.HEIGHT/3-FontHelper.topPanelInfoFont.getLineHeight()*0.7f, Color.WHITE.cpy());
     this.roomEventText.render(sb);
   }

   public void dispose() { this.roomEventText.clear(); }
 }

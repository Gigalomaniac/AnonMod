/*     */ package ExcessiveFantasy;

/*     */

import bossRoom.effect.ChangeScene;
import bossRoom.effect.LatterEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.commons.lang3.StringUtils;
import relics.smartPhone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TheFinSakikoShop extends AbstractImageEvent {
   public static final String ID = "TheFinSakikoShop";
   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
   private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
   private static final String[] OPTIONS = {"进入超商","离开"};
   private static final String[] SpecialOPTIONS = {"进入超商","离开"};
   private static final String title = eventStrings.NAME;
   private static final String imgUrl = ModHelper.makeImgPath("event", "SoraShop");
   private static Texture DIALOG = new Texture("frame/frame.png");

   private static Texture shopBackground = TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "shopBackGround"));

   private static UIStrings soraMessage = CardCrawlGame.languagePack.getUIString("TheFinShopMessage");
   private ChangeScene effect;
   private SakikoShopBG effect2;

   private enum CurrentScreen { START, SHOPPING, SHOPEND, END; }

   private static int lostHp = 7;

   private CurrentScreen currentScreen = CurrentScreen.START;
    private HashMap<String, Texture> SakikoImg = new HashMap();
   private SkeletonJson json;
   private SkeletonData data;
   private AnimationStateData stateData;
   private Skeleton skeleton;
   private AnimationState state;
   private ArrayList<SakikoShopItem> items;
   private Vector2 eyePosition;
   private float dialogDuration = 0.0F;
   private float dialogFadeDuration = 0.0F;
   private boolean showDialog = false;
   private String dialogMsg = "";
   private Color dialogColor = Color.WHITE.cpy();
    private String SakikoState;
   private Random random = new Random();
   private Hitbox soraHb;
   private Hitbox leaveButtonHb;

   public TheFinSakikoShop() {
     super(title, DESCRIPTIONS[0], imgUrl);
       SakikoImg.put("Pre",new Texture("img/live2d/SakikoShopWelcome.png"));
       SakikoImg.put("Arrogant",new Texture("img/live2d/SakikoShopArrogant.png"));
       SakikoImg.put("happy",new Texture("img/live2d/SakikoShopHappy.png"));
       SakikoImg.put("Thinking",new Texture("img/live2d/SakikoShopThinking.png"));
     this.imageEventText.setDialogOption(OPTIONS[0]);
       SakikoState = "Pre";
     this.items = new ArrayList();
     int index = 0;
     addShopItem(index, SakikoShopItem.ShopItem.LIFE);
     index++;
     addShopItem(index, SakikoShopItem.ShopItem.POTIONSLOT);
     index++;
     addShopItem(index, SakikoShopItem.ShopItem.Sexy);
     index++;
     addShopItem(index, SakikoShopItem.ShopItem.WorkWork);
     index++;
       addShopItem(index, SakikoShopItem.ShopItem.Purge);
       index++;
       addShopItem(index, SakikoShopItem.ShopItem.IDEAPOTIONS);
       index++;
      addShopItem(index, SakikoShopItem.ShopItem.LiveBoost);
       index++;
//       addShopItem(index, BATwinsSoraShopItem.ShopItem.ExcessiveFantasy);
//       index++;
//     if (!Settings.hasRubyKey || !Settings.hasEmeraldKey || !Settings.hasSapphireKey) {
//       addShopItem(index, BATwinsSoraShopItem.ShopItem.KEY);
//       index++;
//     }
//     addShopItem(index, BATwinsSoraShopItem.ShopItem.CHALLENGE);
//     index++;
//     addShopItem(index, BATwinsSoraShopItem.ShopItem.INITIALRELIC);
//     index++;
//     addShopItem(index, BATwinsSoraShopItem.ShopItem.POTIONSLOT);
//     index++;
//     addShopItem(index, BATwinsSoraShopItem.ShopItem.TELEPHONE);
//     index++;
//     while (index < 6) {
//       addShopItem(index, BATwinsSoraShopItem.ShopItem.LiveBoost);
//       index++;
//     }

     this.eyePosition = new Vector2(622.0F * Settings.scale, 700.0F * Settings.yScale);

     this.soraHb = new Hitbox(Settings.WIDTH * 0.2F, Settings.HEIGHT * 0.5F);
     this.soraHb.move(this.eyePosition.x, Settings.HEIGHT * 0.5F);

     this.leaveButtonHb = new Hitbox((SakikoShopItem.SakikoITEMBUTTON.getWidth() * 2) * SakikoShopItem.BGSCALE, (SakikoShopItem.SakikoITEMBUTTON.getHeight() * 2) * SakikoShopItem.BGSCALE);
     this.leaveButtonHb.move(Settings.WIDTH / 2.0F + 800.0F * Settings.xScale, Settings.HEIGHT / 2.0F - 0.0F * Settings.scale);
   }

    private void addShopItem(int index, SakikoShopItem.ShopItem item) {
     float x = Settings.WIDTH / 2.0F - 200.0F * Settings.xScale;
     float y = Settings.HEIGHT / 2.0F + 80.0F * Settings.yScale;
     x += (index % 4) * 200.0F * Settings.xScale;
     y -= (index / 4) * 300.0F * Settings.yScale;
     this.items.add(new SakikoShopItem(false, new Vector2(x, y), item, this));
   }



   protected void buttonEffect(int i) {
     switch (this.currentScreen) {
       case START:
         AbstractDungeon.effectList.clear();
         AbstractDungeon.effectsQueue.clear();
           AbstractDungeon.scene.fadeOutAmbiance();
           CardCrawlGame.music.silenceBGM();
           CardCrawlGame.music.silenceBGMInstantly();
           CardCrawlGame.music.silenceTempBgmInstantly();
           CardCrawlGame.music.playTempBgmInstantly("62_sense.mp3", true);
         this.currentScreen = CurrentScreen.SHOPPING;
         this.effect = new ChangeScene(ImageMaster.loadImage("img/boss/bg/bg01071.png"));
         this.effect2 = new SakikoShopBG(ImageMaster.loadImage("img/live2d/SakikoShopWelcome.png"));
         AbstractDungeon.effectList.add(new LatterEffect(() -> {
           AbstractDungeon.effectsQueue.add(this.effect);
             AbstractDungeon.effectsQueue.add(this.effect2);
         }));
         GenericEventDialog.hide();
//         this.state.addAnimation(0, "Start01_Idle_01", false, 0.0F);
//         this.state.addAnimation(0, "Idle_01", true, 0.0F);
        showDialog(soraMessage.TEXT[0], 5.0F, ModHelper.makePath(soraMessage.EXTRA_TEXT[0]));
//         CardCrawlGame.music.unsilenceBGM();
//         CardCrawlGame.music.playTempBgmInstantly(ModHelper.makePath("soraShop"));

         break;
       case SHOPEND:
         if (AbstractDungeon.id.equals("TheEnding")) {
           openMap();
           break;
         }
         switch (i) {
           case 0:
             this.imageEventText.clearAllDialogs();
             this.imageEventText.updateBodyText(eventStrings.DESCRIPTIONS[2]);
             this.imageEventText.setDialogOption(eventStrings.OPTIONS[1]);
             this.currentScreen = CurrentScreen.END;
             AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new smartPhone());
             AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, 7, DamageInfo.DamageType.HP_LOSS));
             break;
           case 1:
             try {
//               ((BATwinsSoraShopItem)this.items.get(0)).activeEffect();
//               ((BATwinsSoraShopItem)this.items.get(0)).activeEffect();
//               ((BATwinsSoraShopItem)this.items.get(1)).activeEffect();
             } catch (Exception e) {
               ModHelper.logger.warn("What happened?");
             }
             this.imageEventText.clearAllDialogs();
             this.imageEventText.updateBodyText(eventStrings.DESCRIPTIONS[3]);
             this.imageEventText.setDialogOption(eventStrings.OPTIONS[1]);
             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Shame(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
             this.currentScreen = CurrentScreen.END;
             break;
           case 2:
             openMap();
             break;
         }
         openMap();
         break;

       case END:
         openMap();
         break;
     }
   }


   public void update() {
     if (!this.combatTime) {
       this.hasFocus = true;

       if (this.waitTimer > 0.0F) {
         this.waitTimer -= Gdx.graphics.getDeltaTime();
         if (this.waitTimer < 0.0F) {
           this.imageEventText.show(this.title, this.body);
           this.waitTimer = 0.0F;
         }
       }

       if (!GenericEventDialog.waitForInput) {
         this.buttonEffect(GenericEventDialog.getSelectedOption());
       }
     }
     if (this.currentScreen == CurrentScreen.SHOPPING) {
//       updateAnimation();
       updateShopItems();
       updateSoraDialog();

       this.soraHb.update();
       if (this.soraHb.hovered && InputHelper.justClickedLeft) {
         InputHelper.justClickedLeft = false;
         if (!this.showDialog) {
           randomSoraAudio();
         }
       }

       this.leaveButtonHb.update();
       if (this.leaveButtonHb.hovered && InputHelper.justClickedLeft) {
         InputHelper.justClickedLeft = false;
           openMap();
//         this.currentScreen = CurrentScreen.SHOPEND;
//         this.imageEventText.clearAllDialogs();
//         this.imageEventText.updateBodyText(eventStrings.DESCRIPTIONS[1].substring(0, eventStrings.DESCRIPTIONS[1].indexOf("NL")));
//         if (!AbstractDungeon.id.equals("TheEnding")) {
//           this.imageEventText.updateBodyText(eventStrings.DESCRIPTIONS[1]);
//           this.imageEventText.setDialogOption(String.format(eventStrings.OPTIONS[2], new Object[] { Integer.valueOf(lostHp) }), new smartPhone());
//           this.imageEventText.setDialogOption(eventStrings.OPTIONS[3], new Shame());
//         }
//         this.imageEventText.setDialogOption(eventStrings.OPTIONS[1]);
//         GenericEventDialog.show();
         CardCrawlGame.music.silenceTempBgmInstantly();
       }
     }
   }

   private void randomSoraAudio() {
     int r = this.random.nextInt(soraMessage.TEXT.length - 2) + 1;
     showDialog(soraMessage.TEXT[r], 2.0F, ModHelper.makePath(soraMessage.EXTRA_TEXT[r]));
       ArrayList<String> keys = new ArrayList<>(SakikoImg.keySet());
//       int randomIndex = random.nextInt(keys.size());
//       String randomKey = keys.get(randomIndex);
//       ChangeSakikoImg(randomKey);
     switch (r) {
       case 1:
           String randomKey ="Thinking";
           ChangeSakikoImg(randomKey);
         break;
       case 2:
           String randomKey2 ="Arrogant";
           ChangeSakikoImg(randomKey2);
         break;
       case 3:
           String randomKey3 ="Happy";
           ChangeSakikoImg(randomKey3);
         break;
     }
   }

    public void ChangeSakikoImg(String state) {
//        AbstractDungeon.effectList.remove(SakikoShop.class);
        AbstractDungeon.effectList.removeIf(effect -> effect instanceof SakikoShopBG);
        AbstractDungeon.effectList.add(new SakikoShopBG(SakikoImg.get(state)));
//        sb.draw(SakikoImg.get(SakikoState), 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }


   private void updateSoraDialog() {
     if (this.showDialog) {
       this.dialogDuration -= Gdx.graphics.getDeltaTime();
       this.dialogFadeDuration = Math.min(this.dialogFadeDuration + Gdx.graphics.getDeltaTime(), 1.0F);
       this.dialogColor.a = this.dialogFadeDuration;
       if (this.dialogDuration < 0.0F) {
         this.showDialog = false;
       }
     } else if (this.dialogFadeDuration > 0.0F) {
       this.dialogFadeDuration = Math.max(this.dialogFadeDuration - Gdx.graphics.getDeltaTime(), 0.0F);
       this.dialogColor.a = this.dialogFadeDuration;
     }
   }


   private void updateShopItems() { this.items.forEach(SakikoShopItem::update); }


//   private void updateAnimation() {
//     this.state.update(Gdx.graphics.getDeltaTime());
//     this.state.apply(this.skeleton);
//     if (this.state.getCurrent(0).getAnimation().getName().equals("Idle_01")) {
//       MoveEyes();
//     }
//     this.skeleton.updateWorldTransform();
//     this.skeleton.setPosition(Settings.WIDTH / 2.0F, 0.0F);
//   }


//   private void MoveEyes() {
//     Bone bone = this.skeleton.findBone("Touch_Eye_Key");
//     Bone bone1 = this.skeleton.findBone("Touch_Point_Key");
//     Vector2 target = new Vector2(InputHelper.mX, InputHelper.mY);
//     Vector2 temp = target.sub(this.eyePosition);
//     temp.scl(Settings.scale * 0.05F);
//     bone.setPosition(temp.y, -temp.x);
//     bone1.setPosition(temp.y, -temp.x);
//   }


   public void render(SpriteBatch sb) {
     super.render(sb);
     if (this.currentScreen == CurrentScreen.SHOPPING) {
       sb.end();
       CardCrawlGame.psb.begin();
//       AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
       CardCrawlGame.psb.end();
       sb.begin();
       sb.setBlendFunction(770, 771);

//       renderShopBackGround(sb);
       renderShopItems(sb);

       renderLeaveButton(sb);

       renderDialogFramework(sb);
       renderSoraDialog(sb);

       this.soraHb.render(sb);

       this.leaveButtonHb.render(sb);
     }
   }
   public static Texture leaveButton = new Texture("img/soraShop/SakikoShopleaveButton.png");
   private void renderLeaveButton(SpriteBatch sb) {
     Color color = Color.WHITE.cpy();
     if (this.leaveButtonHb.hovered) {
       color.g = 0.3F;
     }
     sb.setColor(color);
     sb.draw(leaveButton, this.leaveButtonHb.x, this.leaveButtonHb.y, this.leaveButtonHb.width, this.leaveButtonHb.height);

     FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, SpecialOPTIONS[1], this.leaveButtonHb.cX, this.leaveButtonHb.cY + Settings.yScale*100, Color.WHITE);
   }

   private void renderDialogFramework(SpriteBatch sb) {
     sb.setColor(Color.WHITE.cpy());
     sb.draw(DIALOG, Settings.WIDTH * 0F, Settings.HEIGHT * 0F, Settings.WIDTH, Settings.HEIGHT/3f);
   }
   private void renderSoraDialog(SpriteBatch sb) {
     sb.setColor(Color.BLACK);
     String[] splitStrings = splitStringIntoChunksOfTen();
     float y = Settings.HEIGHT * 0.2F;
     for (String s : splitStrings) {
       FontHelper.renderFontLeft(sb, FontHelper.topPanelInfoFont, s,  Settings.HEIGHT * 0.1F, y, this.dialogColor);
       y -= FontHelper.topPanelInfoFont.getLineHeight();
     }
   }
  public String[] splitStringIntoChunksOfTen() {
    int chunkSize = 50;
    int length = dialogMsg.length();
    int chunkCount = (int) Math.ceil((double) length / chunkSize);
    String[] chunks = new String[chunkCount];

    for (int i = 0; i < length; i += chunkSize) {
      chunks[i / chunkSize] = dialogMsg.substring(i, Math.min(length, i + chunkSize));
    }

    return chunks;
  }
   public static String[] splitStringIntoGroups(String input, int groupSize) {
     int inputLength = input.length();
     int numGroups = (int)Math.ceil(inputLength / groupSize);
     String[] result = new String[numGroups];

     for (int i = 0; i < numGroups; i++) {
       int start = i * groupSize;
       int end = Math.min(start + groupSize, inputLength);
       result[i] = input.substring(start, end);
     }

     return result;
   }

   private void renderShopBackGround(SpriteBatch sb) {
     sb.setColor(Color.WHITE);
     sb.draw(shopBackground, Settings.WIDTH / 2.0F, 0.0F, Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.8F);
   }



   private void renderShopItems(SpriteBatch sb) {
     this.items.forEach(i -> i.render(sb));
   }


   public void showDialog(String msg, float duration, String audioKey) {
     this.dialogDuration = duration;
     this.dialogFadeDuration = 0.0F;
     this.showDialog = true;
     this.dialogMsg = msg;
     if (!StringUtils.isEmpty(audioKey)) {
       CardCrawlGame.sound.play(audioKey);
     }
   }


   public void dispose() {
     super.dispose();
   }
 }

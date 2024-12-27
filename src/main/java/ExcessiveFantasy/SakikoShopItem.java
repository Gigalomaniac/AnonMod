/*     */ package ExcessiveFantasy;

/*     */ import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*     */ import com.megacrit.cardcrawl.helpers.RelicLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
/*     */
/*     */
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import potions.IdeaPotion;
import potions.liveboost;
import relics.Guitar;
import relics.Receipts;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.megacrit.cardcrawl.events.AbstractEvent.logMetricCardRemoval;





 public class SakikoShopItem {
   public static Texture ITEMBG = new Texture("img/soraShop/SakikoShopItemBg.png");
//   public static Texture ITEMBUTTON = TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "ShopItemButton"));
public static Texture SakikoITEMBUTTON = new Texture("img/soraShop/SakikoShopItemButton.png");
   private boolean isRare;

   private Vector2 position;
   private ShopItem item;
   private Hitbox hitbox;
   private Hitbox buttonHb;
   public static float BGSCALE = 0.6F * Settings.scale;
   private static Vector2 HITBOXOFFSET = new Vector2(ITEMBG.getWidth() * BGSCALE / 2.0F, ITEMBG.getHeight() * BGSCALE / 2.0F);

   private SoraItemStrings soraItemStrings;

   private PowerTip tip;

   private float buttonScale;
   private int count;
   private boolean enable = true;
   private Texture img;
   SakikoShop shop;
   Random random;
   private int price;

   public SakikoShopItem(boolean isRare, Vector2 position, ShopItem item, SakikoShop shop) {
     this.isRare = isRare;
     this.position = position;
     this.item = item;
     this.hitbox = new Hitbox(ITEMBG.getWidth() * BGSCALE, ITEMBG.getHeight() * BGSCALE);
     this.hitbox.move(position.x + HITBOXOFFSET.x, position.y + HITBOXOFFSET.y);
       initSoraItemString("ZHS");
     this.soraItemStrings = (SoraItemStrings)ModHelper.soraItemStringsMap.get(item.getId());

     this.buttonHb = new Hitbox(SakikoITEMBUTTON.getWidth() * BGSCALE, SakikoITEMBUTTON.getHeight() * BGSCALE);
     this.buttonHb.move(position.x + HITBOXOFFSET.x, position.y + SakikoITEMBUTTON.getHeight() * BGSCALE / 2.0F);

     this.tip = new PowerTip();
     this.tip.header = this.soraItemStrings.name;
     this.tip.body = this.soraItemStrings.description;
     this.buttonScale = BGSCALE;
     this.count = this.soraItemStrings.count;
     this.enable = true;
     this.random = new Random(AbstractDungeon.miscRng.randomLong());

     this.img = getItemImg(this.item);

     this.price = this.soraItemStrings.price;
  }

   public SakikoShopItem(boolean isRare, Vector2 position, ShopItem item, TheFinSakikoShop theFinSakikoShop) {
     this.isRare = isRare;
     this.position = position;
     this.item = item;
     this.hitbox = new Hitbox(ITEMBG.getWidth() * BGSCALE, ITEMBG.getHeight() * BGSCALE);
     this.hitbox.move(position.x + HITBOXOFFSET.x, position.y + HITBOXOFFSET.y);
     initSoraItemString("ZHS");
     this.soraItemStrings = (SoraItemStrings)ModHelper.soraItemStringsMap.get(item.getId());

     this.buttonHb = new Hitbox(SakikoITEMBUTTON.getWidth() * BGSCALE, SakikoITEMBUTTON.getHeight() * BGSCALE);
     this.buttonHb.move(position.x + HITBOXOFFSET.x, position.y + SakikoITEMBUTTON.getHeight() * BGSCALE / 2.0F);

     this.tip = new PowerTip();
     this.tip.header = this.soraItemStrings.name;
     this.tip.body = this.soraItemStrings.description;
     this.buttonScale = BGSCALE;
     this.count = this.soraItemStrings.count;
     this.enable = true;
//     this.shop = theFinSakikoShop;
     this.random = new Random(AbstractDungeon.miscRng.randomLong());

     this.img = getItemImg(this.item);

     this.price = this.soraItemStrings.price;
   }

   private void initSoraItemString(String lang) {
    String json = Gdx.files.internal("localization/SakikoItems.json").readString(String.valueOf(StandardCharsets.UTF_8));
    Gson gson = new Gson();

    Type soraItemMapType = (new TypeToken<Map<String, SoraItemStrings>>() {  }).getType();
    ModHelper.soraItemStringsMap = (Map)gson.fromJson(json, soraItemMapType);

    ModHelper.getLogger().info(ModHelper.soraItemStringsMap);
  }
   public void update() {
     this.hitbox.update();
     this.buttonHb.update();
     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
       CardCrawlGame.sound.play("CARD_EXHAUST");
       logMetricCardRemoval("Purifier", "Purged", (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0), (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
       AbstractDungeon.player.masterDeck.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
       AbstractDungeon.gridSelectScreen.selectedCards.clear();
     }
     if (this.buttonHb.hovered && InputHelper.justClickedLeft && this.enable && AbstractDungeon.player.gold >= this.price) {
       InputHelper.justClickedLeft = false;

       if(item.id.equals("liveboost") || item.id.equals("IDEAPOTIONS")){
         for (AbstractPotion Potion : AbstractDungeon.player.potions) {
           if (Potion.ID.equals(PotionSlot.POTION_ID)){
             this.count--;
             break;
           }
         }

       }else
       this.count--;
       if (this.count <= 0)
         this.enable = false;
       activeEffect();
     }
   }
   public void activeEffect() {
     String relicId;
     ArrayList<String> relics, canUpgraded;
     boolean isActivated = true;
     switch (this.item) {
       case IDEAPOTIONS:
         break;
       case LiveBoost:
         break;
       case Purge:
         this.hitbox.hovered = false;
         AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, "???", false, false, false, true);
         break;
       case Sexy:
         AbstractDungeon.player.maxHealth +=AbstractDungeon.player.maxHealth*9;
         AbstractDungeon.player.heal((int)(AbstractDungeon.player.maxHealth * 1F));
         break;
       case LIFE:
         AbstractDungeon.player.heal((int)(AbstractDungeon.player.maxHealth * 0.2F));
         break;
       case SMITH:
//        canUpgraded = new ArrayList<AbstractCard>();
//        AbstractDungeon.player.masterDeck.group.stream().filter(AbstractCard::canUpgrade).forEach(canUpgraded::add);
//        if (!canUpgraded.isEmpty()) {
//          Collections.shuffle(canUpgraded, this.random);
//          AbstractCard card = (AbstractCard)canUpgraded.get(0);
//          card.upgrade();
//          AbstractDungeon.player.bottledCardUpgradeCheck((AbstractCard)canUpgraded.get(0));
//          AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(((AbstractCard)canUpgraded
//                .get(0)).makeStatEquivalentCopy()));
//          AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F)); break;
//        }
//        this.shop.showDialog(this.soraItemStrings.message, 2.0F, null);
//        isActivated = false;
         break;

       case CRYSTALHANIWA:
         AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new Guitar());
         break;
       case KEY:
         if (this.img == ImageMaster.RUBY_KEY)
           AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.RED));
         if (this.img == ImageMaster.EMERALD_KEY)
           AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.GREEN));
         if (this.img == ImageMaster.SAPPHIRE_KEY)
           AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.BLUE));
         break;
       case CHALLENGE:
//        BATwinsMod.saveHelper.values.challengeCoupons = true;
         break;
       case INITIALRELIC:
         relics = AbstractDungeon.player.getStartingRelics();
         relicId = (String)relics.get(relics.size() - 1);
         AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, RelicLibrary.getRelic(relicId));
         break;
       case POTIONSLOT:
         AbstractDungeon.player.potionSlots++;
         AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
         AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
         break;
       case TELEPHONE:
//         BATwinsMod.saveHelper.values.hasSoraPhone = true;
         break;
       case ExcessiveFantasy:
         AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new Receipts());
         break;
     }


     if (isActivated){
       if(!item.id.equals("WorkWork")) {
         if(item.id.equals("liveboost") || item.id.equals("IDEAPOTIONS")){
         switch (item.id){
           case "liveboost":
             if (AbstractDungeon.player.obtainPotion(new liveboost())) {
               AbstractDungeon.player.loseGold(this.price);
             }
             break;
           case "IDEAPOTIONS":
             if (AbstractDungeon.player.obtainPotion(new IdeaPotion())) {
               AbstractDungeon.player.loseGold(this.price);
             }
             break;
         }

         }else {
           AbstractDungeon.player.loseGold(this.price);
         }
       }
       else {
         if (AbstractDungeon.player.currentHealth > 10)
           AbstractDungeon.player.damage(new DamageInfo((AbstractCreature) null, 10));
         else
           AbstractDungeon.player.damage(new DamageInfo((AbstractCreature) null, AbstractDungeon.player.currentHealth - 1));
         AbstractDungeon.player.gainGold(this.price);
       }
     }

   }

   public void render(SpriteBatch sb) {
     Color color;
     sb.setColor(Color.WHITE);
     sb.draw(ITEMBG, this.position.x, this.position.y, 0.0F, 0.0F, ITEMBG.getWidth(), ITEMBG.getHeight(), BGSCALE, BGSCALE, 0.0F, 0, 0, ITEMBG
         .getWidth(), ITEMBG.getHeight(), false, false);
     if (this.buttonHb.hovered) {
       sb.setColor(1.0F, 1.0F, 1.0F, 0.25F);
     } else {
       sb.setColor(Color.WHITE);
     }
     if (!this.enable) {
       sb.setColor(Color.GRAY);
     }
     sb.draw(SakikoITEMBUTTON, this.position.x, this.position.y, 0.0F, 0.0F, SakikoITEMBUTTON.getWidth(), SakikoITEMBUTTON.getHeight(), this.buttonScale, this.buttonScale, 0.0F, 0, 0, SakikoITEMBUTTON
.getWidth(), SakikoITEMBUTTON.getHeight(), false, false);

     sb.setColor(Color.WHITE);
     sb.draw(ImageMaster.UI_GOLD, this.position.x + SakikoITEMBUTTON.getWidth() * BGSCALE * 0.1F, this.position.y + SakikoITEMBUTTON.getHeight() * BGSCALE * 0.2F, 0.0F, 0.0F, ImageMaster.UI_GOLD
         .getWidth(), ImageMaster.UI_GOLD.getHeight(), 0.8F * Settings.scale, 0.8F * Settings.scale, 0.0F, 0, 0, ImageMaster.UI_GOLD

         .getWidth(), ImageMaster.UI_GOLD.getHeight(), false, false);

     if (AbstractDungeon.player.gold >= this.price) {
       color = Color.WHITE.cpy();
     } else {
       color = Color.RED.cpy();
     }
     if(!this.item.id.equals("WorkWork"))
     FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont,
         Integer.toString(this.price), this.position.x + SakikoITEMBUTTON.getWidth() * BGSCALE * 0.5F, this.position.y + SakikoITEMBUTTON.getHeight() * BGSCALE * 0.2F + FontHelper.tipHeaderFont.getCapHeight() * 2.0F, color);
else
       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont,
               "+"+Integer.toString(this.price), this.position.x + SakikoITEMBUTTON.getWidth() * BGSCALE * 0.5F, this.position.y + SakikoITEMBUTTON.getHeight() * BGSCALE * 0.2F + FontHelper.tipHeaderFont.getCapHeight() * 2.0F, color);

     renderTitle(sb);
     renderItemImage(sb);
     if (this.hitbox.hovered) {
       renderTips(sb);
     }

     this.hitbox.render(sb);
     this.buttonHb.render(sb);
   }


   private void renderTips(SpriteBatch sb) {
  TipHelper.renderGenericTip(InputHelper.mX, InputHelper.mY - 60.0F * Settings.yScale, this.soraItemStrings.name, this.soraItemStrings.description);
}


   private void renderItemImage(SpriteBatch sb) {
     sb.setColor(Color.WHITE);
     if (this.item == ShopItem.INITIALRELIC) {
       sb.draw(this.img, this.position.x + HITBOXOFFSET.x - 32.0F, this.position.y + HITBOXOFFSET.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
     } else {

       sb.draw(this.img, this.position.x + HITBOXOFFSET.x - 32.0F, this.position.y + HITBOXOFFSET.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 64, 64, false, false);
     }
   }



   private void renderTitle(SpriteBatch sb) {
     float x = this.position.x + HITBOXOFFSET.x;
     float y = this.position.y + ITEMBG.getHeight() * BGSCALE * 0.9F;
     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, this.soraItemStrings.name, x, y, Color.BLACK, 0.7F);
   }

   public enum ShopItem
   {
     Sexy("Sexy"),
     LIFE("Life"),
     SMITH("Smith"),
     MEDICINE("Medicine"),
     CRYSTALHANIWA("CrystalHaniwa"),
     KEY("Key"),
     CHALLENGE("Challenge"),
     INITIALRELIC("InitialRelics"),
     POTIONSLOT("PotionSlot"),
     TELEPHONE("Telephone"),
     LiveBoost("liveboost"),
     WorkWork("WorkWork"),
     IDEAPOTIONS("IDEAPOTIONS"),
     Purge("Purge"),
     ExcessiveFantasy("ExcessiveFantasy");

     private String id;


     ShopItem(String id) { this.id = id; }



     public String getId() { return this.id; } }
    private static final Texture sakikoLove = new Texture("img/UI/SakikoLove.png");
  private static final Texture liveboost = new Texture("img/potions/liveboost.png");
  private static final Texture java = new Texture("img/UI/java.png");
  private static final Texture IdeaPotions = new Texture("img/potions/IdeaPotion.png");
  private static final Texture Purge = new Texture("img/UI/removecard.png");
  private static final Texture ExcessiveFantasy = new Texture("img/UI/Receipts.png");

   public static Texture getItemImg(ShopItem item) {
     String relicId;
     ArrayList<String> relics;
     List<Texture> keyList;
     switch (item) {
       case ExcessiveFantasy:
         return ExcessiveFantasy;
       case Purge:
         return Purge;
       case WorkWork:
         return java;
       case IDEAPOTIONS:
         return IdeaPotions;
       case LiveBoost:
         return liveboost;
       case Sexy:
         return sakikoLove;
       case LIFE:
         return ImageMaster.TP_HP;
       case SMITH:
         return TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "smithStone"));
       case MEDICINE:
         return TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "medicine"));
       case CRYSTALHANIWA:
         return TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "CrystalHaniwa"));
       case KEY:
//         keyList = new ArrayList<Texture>();
//         if (!Settings.hasRubyKey) {
//           keyList.add(ImageMaster.RUBY_KEY);
//         }
//         if (!Settings.hasEmeraldKey) {
//           keyList.add(ImageMaster.EMERALD_KEY);
//         }
//         if (!Settings.hasSapphireKey) {
//           keyList.add(ImageMaster.SAPPHIRE_KEY);
//         }
//         if (!keyList.isEmpty()) {
//           Collections.shuffle(keyList, new Random(AbstractDungeon.miscRng.randomLong()));
//           return (Texture)keyList.get(0);
//         }
         return ImageMaster.KEY_SLOTS_ICON;
       case CHALLENGE:
         return TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "challengeCoupons"));
       case INITIALRELIC:
         relics = AbstractDungeon.player.getStartingRelics();
         relicId = (String)relics.get(relics.size() - 1);
         return (RelicLibrary.getRelic(relicId)).img;
       case POTIONSLOT:
         return ImageMaster.POTION_T_CONTAINER;
       case TELEPHONE:
         return TextureLoader.getTexture(ModHelper.makeImgPath("UI/soraShop", "Telephone"));
     }
     return ImageMaster.TP_HP;
   }
 }

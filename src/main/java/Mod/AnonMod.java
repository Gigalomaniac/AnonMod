 package Mod;

 import BossRewards.*;
 import ExcessiveFantasy.SakikoShop;
 import ExcessiveFantasy.ExcessiveFantasyWorld;
 import actlikeit.ActLikeIt;
 import bandFriendsCard.*;
 import basemod.*;
 import basemod.helpers.RelicType;
 import basemod.helpers.ScreenPostProcessorManager;
 import basemod.interfaces.*;
 import bossRoom.AnonSide;
 import bossRoom.CrychicSide;
 import bossRoom.InnerFavillaeSide;
 import cards.SpecialCard.AIHeart;
 import cards.SpecialCard.MikaSwissRoll;
 import cards.SpecialCard.haventFiguredItOut;
 import cards.common.*;
 import cards.dust.*;
 import cards.others.*;
 import cards.rare.*;
 import cards.rare.Crychic;
 import cards.token.*;
 import cards.uncommon.*;
 import cards.yuzu.*;
 import characters.char_Anon;
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.evacipated.cardcrawl.modthespire.Loader;
 import com.evacipated.cardcrawl.modthespire.ModInfo;
 import com.evacipated.cardcrawl.modthespire.Patcher;
 import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
 import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.dungeons.Exordium;
 import com.megacrit.cardcrawl.dungeons.TheCity;
 import com.megacrit.cardcrawl.helpers.CardHelper;
 import com.megacrit.cardcrawl.helpers.FontHelper;
 import com.megacrit.cardcrawl.localization.*;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.monsters.MonsterGroup;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.rewards.RewardSave;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
 import monster.BocchiTheRock.Bocchi;
 import monster.BocchiTheRock.Ikuyo;
 import monster.HifuuClub.Hearn;
 import monster.HifuuClub.Renko;
 import monster.Roselia.Yukina;
 import monster.ShoujoKageki.Karen;
 import monster.act1.soyorin;
 import monster.act2.ARSBoss;
 import monster.act2.KSMBoss;
 import monster.act3.Crane;
 import monster.act3.FakeDungeon;
 import monster.act3.LeaderAnon;
 import monster.act3.RoadRoller;
 import monster.caicai.caicai;
 import patch.FixAscenscionUnlockOnGameoverWinPatch;
 import pathes.SkinSelectPatch;
 import potions.IdeaPotion;
 import potions.liveboost;
 import OnStage.promiseOnStage;
 import relics.chao.SpiritFireMonster;
 import relics.chao.ThirdPerspectiveViewPatches;
 import relics.test.Mashiro;
 import star.RestartRunHelper;
 import star.StarAnonEvent;
 import monster.act1.TOGETOGE;
 import monster.mika.mika;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import org.scannotation.AnnotationDB;
 import pathes.AbstractCardEnum;
 import pathes.ThmodClassEnum;
 import relics.*;
 import star.BeyondTheStar;
 import TheTreeOfQliphoth.TheTreeOfQliphoth;
 import star.StarAnonSide;
 import ui.SkinSelectScreen;
 import utils.DreamCardRewards;
 import utils.RewardTypeEnum;

 import java.io.IOException;
 import java.nio.charset.StandardCharsets;
 import java.util.*;

 @SpireInitializer
 public class AnonMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber,
         PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber,
         OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber,PostDeathSubscriber
         ,PostRenderSubscriber,
         StartGameSubscriber , PostCreateStartingDeckSubscriber,StartActSubscriber, PostUpdateSubscriber,PostCreateStartingRelicsSubscriber,AddAudioSubscriber{
     public static SpireConfig saves;
     public static final Color Anon_PUPPETEER_FLAVOR = CardHelper.getColor(250, 250, 210);
     private static final String MOD_BADGE = "img/UI_Seles/badge.png";
   //攻击、技能、能力牌的背景图片(512)
   private static final String ATTACK_CC = "img/512/bg_attack_SELES_s.png";
   private static final String SKILL_CC = "img/512/bg_skill_SELES_s.png";
   private static final String POWER_CC = "img/512/bg_power_SELES_s.png";
   private static final String ENERGY_ORB_CC = "img/UI/star.png";
   //攻击、技能、能力牌的背景图片(1024)
   private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_SELES.png";
   private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_SELES.png";
   private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_SELES.png";
   private static final String ENERGY_ORB_CC_PORTRAIT = "img/UI/star_22.png";
   public static final String CARD_ENERGY_ORB = "img/UI_Seles/energyOrb.png";
   //选英雄界面的角色图标、选英雄时的背景图片
   private static final String MY_CHARACTER_BUTTON = "img/charSelect/SelesButtongita.png";
   private static final String MARISA_PORTRAIT = "img/charSelect/animeanon.png";
   public static final Color SILVER = CardHelper.getColor(200, 200, 200);
     public static boolean limitedSLOption;
     public static float time;
     public static boolean forcecanclebosslogic;
     /**
      * 3d
      */
     public static boolean done = false;
     public static boolean onlymodboss;
     private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
   public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();
     private static SpireConfig config;
     public static final String MOD_NAME = "AnonMod";

     public static final String AUTHOR = "Gig";
     public static final Logger logger = LogManager.getLogger(AnonMod.class.getName());
     public static int corrode;
   public AnonMod() {
     //构造方法，初始化各种参数
     BaseMod.subscribe((ISubscriber)this);
       BaseMod.addColor(AbstractCardEnum.Anon_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, "img/pink/512/bg_attack.png", "img/pink/512/bg_skill.png", "img/pink/512/bg_power.png", "img/UI/star.png", "img/pink/1024/bg_attack.png", "img/pink/1024/bg_skill.png", "img/pink/1024/bg_power.png", "img/UI/star_164.png", "img/UI/star_22.png");
       BaseMod.addColor(AbstractCardEnum.LockAnon_COLOR, Anon_PUPPETEER_FLAVOR, Anon_PUPPETEER_FLAVOR, Anon_PUPPETEER_FLAVOR, Anon_PUPPETEER_FLAVOR, Anon_PUPPETEER_FLAVOR, Anon_PUPPETEER_FLAVOR, Anon_PUPPETEER_FLAVOR,
               "img/pink/512/bg_attack.png", "img/1024/bg_skill_IF_512.png", "img/pink/512/bg_power.png", "img/UI/star.png", "img/pink/1024/bg_attack.png", "img/1024/bg_skill_IF.png", "img/pink/1024/bg_power.png", "img/UI/star_164.png", "img/UI/star_22.png");

   }
     public static String MakePath(String id) {
         return "AnonMod:" + id;
     }
     public static String MOD_ID = "paleoftheancients";
     public static String makeID(String id) {
         return MOD_ID + ":" + id;
     }
     public static String assetPath(String path) {
         return MOD_ID + "/" + path;
     }

     public static ArrayList<AbstractGameAction> myActions = new ArrayList();

     public static void registerPostProcessor(Mashiro.MashiroPostProcessor MashiroPostProcessor) {
         postProcessors.add(MashiroPostProcessor);
         ScreenPostProcessorManager.addPostProcessor(MashiroPostProcessor);
     }
     private static final List<ScreenPostProcessor> postProcessors = new ArrayList();

     public static void removePostProcessor(Class<Mashiro.MashiroPostProcessor> processorClass) {
         for (Iterator<ScreenPostProcessor> iterator = postProcessors.iterator(); iterator.hasNext(); ) {
             ScreenPostProcessor postProcessor = (ScreenPostProcessor)iterator.next();
             if (processorClass.isAssignableFrom(postProcessor.getClass())) {
                 ScreenPostProcessorManager.removePostProcessor(postProcessor);
                 iterator.remove();
                 break;
             }
         }
     }

     @Override
   public void receiveEditCharacters() {
     //添加角色到MOD中
     BaseMod.addCharacter((AbstractPlayer)new char_Anon("Anon"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, ThmodClassEnum.Anon_COLOR);
   }
   //初始化整个MOD,一定不能删
     public static void initialize() {
         new AnonMod();

         try {
             Properties defaults = new Properties();
             defaults.setProperty("onlymodboss", Boolean.toString(true));
             defaults.setProperty("forcecanclebosslogic", Boolean.toString(false));
             config = new SpireConfig("madokamod", "Config", defaults);
             config.load();
             onlymodboss = config.getBool("onlymodboss");
             forcecanclebosslogic = config.getBool("forcecanclebosslogic");
         } catch (Exception var1) {
             var1.printStackTrace();
         }

     }
   @Override
   public void receiveEditCards() {
     //将卡牌批量添加
     loadCardsToAdd();
     Iterator<AbstractCard> var1 = this.cardsToAdd.iterator();
     while (var1.hasNext()) {
       AbstractCard card = var1.next();
       BaseMod.addCard(card);
     }

   }

     @Override
     public void receiveStartGame() {
         SkinSelectScreen.shouldUpdateBackground = true;
         FixAscenscionUnlockOnGameoverWinPatch.updateAscProgress = true;
         if (AbstractDungeon.floorNum == 0) {
             AnonSide.finInfo = -1;
             saves.setBool("MachineHeartLocked",true);
             saves.setInt("shopCount", 0);
             saves.setInt("shopLastFloor", 0);
             saves.setString("Stage1","gbc");
             saves.setString("Stage2","pp");
             saves.setString("Stage3","Karen");
             saves.setString("Stage4","InnerAnon");
             saves.setString("choice","soyo");
             try {
                 saves.save();
             } catch (IOException var4) {
                 throw new RuntimeException(var4);
             }
         }
         clearPostProcessors();
         AbstractPlayer player = AbstractDungeon.player;

         ThirdPerspectiveViewPatches.setEnable(false);

     }

     private void clearPostProcessors() {
         for (ScreenPostProcessor postProcessor : postProcessors) {
             ScreenPostProcessorManager.removePostProcessor(postProcessor);
         }
         postProcessors.clear();
     }

     @Override
   public void receivePostExhaust(AbstractCard c) {}

   @Override
   public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

   }

   @Override
   public void receivePowersModified() {}


   @Override
   public void receivePostDungeonInitialize() {

//       BaseMod.addEvent(StarEvent.ID, StarEvent.class, Narration.ID);
//       BaseMod.addEvent((new AddEventParams.Builder("BandFriendsEvent", BandFriendsEvent.class)).dungeonID("test").playerClass(CustomCharacter.Anon).create());
   }


   @Override
   public void receivePostDraw(AbstractCard arg0) {}

   private static String loadJson(String jsonPath) {
     return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
   }


   @Override
   public void receiveEditKeywords() {
       Gson gson = new Gson();
//        String lang = "eng";
//        if (language == Settings.GameLanguage.ZHS) {
//        lang = "zh";
//        }
       String json = null;
       if (Settings.language == Settings.GameLanguage.ZHS) {
            json = Gdx.files.internal("localization/Anon_keywords_zhs.json")
                   .readString(String.valueOf(StandardCharsets.UTF_8));
       }
       else {
            json = Gdx.files.internal("localization/Anon_keywords_en.json")
                   .readString(String.valueOf(StandardCharsets.UTF_8));
       }
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword(keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
   }

   @Override
   public void receiveEditStrings() {
     //读取遗物，卡牌，能力，药水，事件的JSON文本

     String relic="", card="", power="", potion="", event="",monster="",sign="",ui="";

     if (Settings.language == Settings.GameLanguage.ZHS) {
         card = "localization/Anon_cards-zh.json";
         relic = "localization/Anon_relics-zh.json";
         power = "localization/Anon_powers-zh.json";
         monster = "localization/Anon_monsters-zh.json";
         sign = "localization/cardsign.json";
         ui = "localization/UI.json";
         potion = "localization/Anon_potion-zh.json";
         event = "localization/Anon_events-zh.json";
     } else {
         card = "localization/Anon_cards-en.json";
         relic = "localization/Anon_relics-en.json";
         power = "localization/Anon_powers-en.json";
         monster = "localization/Anon_monsters-eng.json";
         sign = "localization/cardsign.json";
         ui = "localization/UI.json";
         event = "localization/Anon_events-en.json";
         potion = "localization/Anon_potion-en.json";
         //其他语言配置的JSON
     }

     String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
     String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
     String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
       String monsterStrings = Gdx.files.internal(monster).readString(String.valueOf(StandardCharsets.UTF_8));
       BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
       String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
       BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
       String signStrings = Gdx.files.internal(sign).readString(String.valueOf(StandardCharsets.UTF_8));
       BaseMod.loadCustomStrings(PowerStrings.class, signStrings);
       AnonCardSignStrings.init((Map)(new Gson()).fromJson(Gdx.files.internal("localization/cardsign.json").readString(String.valueOf(StandardCharsets.UTF_8)), (new TypeToken<Map<String, AnonCardSignStrings>>() {
       }).getType()));
       String uiStrings = Gdx.files.internal(ui).readString(String.valueOf(StandardCharsets.UTF_8));
       BaseMod.loadCustomStrings(com.megacrit.cardcrawl.localization.UIStrings.class, uiStrings);
     String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
//     String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
   }

   private void loadCardsToAdd() {

     //将自定义的卡牌添加到这里

     this.cardsToAdd.clear();
     this.cardsToAdd.add(new Anon_Strike());
     this.cardsToAdd.add(new Anon_Def());
       this.cardsToAdd.add(new dust());
       this.cardsToAdd.add(new CatchCivilEngineering());
       this.cardsToAdd.add(new LeadershipInspection());
       this.cardsToAdd.add(new LeaderScapegoat());
       this.cardsToAdd.add(new Mixer());
       this.cardsToAdd.add(new ProjectTwo());
       this.cardsToAdd.add(new RedRomantic());
       this.cardsToAdd.add(new RelatedHouseholds());
       this.cardsToAdd.add(new Scapegoat());
       this.cardsToAdd.add(new UnderMixer());
      this.cardsToAdd.add(new LoveMeIFYouCan());
       this.cardsToAdd.add(new Roselia());
       this.cardsToAdd.add(new KarenShing());
       this.cardsToAdd.add(new UntouchableFuture());
       this.cardsToAdd.add(new WorldLegacyAnon());
       this.cardsToAdd.add(new haventFiguredItOut());
       this.cardsToAdd.add(new AIHeart());
//     this.cardsToAdd.add(new SavePower());
//     this.cardsToAdd.add(new Printf());
//     this.cardsToAdd.add(new MyInflame());
//     this.cardsToAdd.add(new GiveNote());
//     this.cardsToAdd.add(new GiveVariation());
//     this.cardsToAdd.add(new DreamTale());
//     this.cardsToAdd.add(new MayoiutaSongs());
//     this.cardsToAdd.add(new RefrainSongs());
     this.cardsToAdd.add(new BasicSongs());
       this.cardsToAdd.add(new SoloConcertSongs());
     this.cardsToAdd.add(new RestOrPractice());
     this.cardsToAdd.add(new ChangeToPowerSongs());
     this.cardsToAdd.add(new ChangeToAttackSongs());
     this.cardsToAdd.add(new ChangeToSkillSongs());
     this.cardsToAdd.add(new ChangeStateSongs());
     this.cardsToAdd.add(new Minute());
     this.cardsToAdd.add(new LittleExercise());
     this.cardsToAdd.add(new Pafe());
     this.cardsToAdd.add(new HastyFinish());
     this.cardsToAdd.add(new MindInflate());
     this.cardsToAdd.add(new VocalMe());
     this.cardsToAdd.add(new UrgentPractice());
     this.cardsToAdd.add(new EveryMinutes());
       this.cardsToAdd.add(new Encore());
     this.cardsToAdd.add(new Release());
       this.cardsToAdd.add(new Absolute());
       this.cardsToAdd.add(new IdeaKing());
       this.cardsToAdd.add(new Tomori());
       this.cardsToAdd.add(new Rikki());
       this.cardsToAdd.add(new Mutsumi());
       this.cardsToAdd.add(new Rana());
       this.cardsToAdd.add(new Mana());
       this.cardsToAdd.add(new Sakiko());
       this.cardsToAdd.add(new Soyorin());
        this.cardsToAdd.add(new Idea());
       this.cardsToAdd.add(new OtherHelp());
       this.cardsToAdd.add(new NamonakiSongs());
       this.cardsToAdd.add(new OtoichieSongs());
       this.cardsToAdd.add(new HaruhikageSongs());
       this.cardsToAdd.add(new SilhouetteSongs());
       this.cardsToAdd.add(new KiritorisenSongs());
       this.cardsToAdd.add(new HitoshizukuSongs());
       this.cardsToAdd.add(new HekitenbansouSongs());
       this.cardsToAdd.add(new SenzaihyoumeiSongs());
       this.cardsToAdd.add(new UtakotobaSongs());
       this.cardsToAdd.add(new PassionPlay());
       this.cardsToAdd.add(new Compressive());
       this.cardsToAdd.add(new CurtainCall());
       this.cardsToAdd.add(new Gravity());
       this.cardsToAdd.add(new OnStage());
       this.cardsToAdd.add(new BetEverything());
       this.cardsToAdd.add(new BrainStorming());
       this.cardsToAdd.add(new Thinking());
       this.cardsToAdd.add(new PrePreparation());
       this.cardsToAdd.add(new ReadyExercise());
       this.cardsToAdd.add(new ReLive());
       this.cardsToAdd.add(new allShing());
       this.cardsToAdd.add(new PerfectPitch());
       this.cardsToAdd.add(new IBegYou());
       this.cardsToAdd.add(new Logic());
       this.cardsToAdd.add(new LittleStar());
       this.cardsToAdd.add(new RhythmGuitar());
       this.cardsToAdd.add(new KiraKiraDokiDoki());
       this.cardsToAdd.add(new StarLight());
       this.cardsToAdd.add(new Ha());
       this.cardsToAdd.add(new LongTimeVagetarian());
       this.cardsToAdd.add(new GravitationalCollapse());
       this.cardsToAdd.add(new Traction());
       this.cardsToAdd.add(new LogicDeepDive());
       this.cardsToAdd.add(new DreamComeTrue());
       this.cardsToAdd.add(new FutureVision());
       this.cardsToAdd.add(new Simper());
       this.cardsToAdd.add(new FinishBand());
       this.cardsToAdd.add(new Unite());
       this.cardsToAdd.add(new AreYourequired());
       this.cardsToAdd.add(new FirstCosmicVelocity());
       this.cardsToAdd.add(new Stars());
       this.cardsToAdd.add(new WholeLifeTime());
       this.cardsToAdd.add(new Accomplice());
       this.cardsToAdd.add(new FaceDear());
       this.cardsToAdd.add(new AnonTokyo());
       this.cardsToAdd.add(new WishYouHappiness());
       this.cardsToAdd.add(new Crychic());
       this.cardsToAdd.add(new AmIRequired());
       this.cardsToAdd.add(new RingInvite());
       this.cardsToAdd.add(new Determination());
       this.cardsToAdd.add(new Grin());
       this.cardsToAdd.add(new NeverSleepLate());
       this.cardsToAdd.add(new DespairBreak());
       this.cardsToAdd.add(new HopeBanish());
       this.cardsToAdd.add(new AstoundingWisdom());
       this.cardsToAdd.add(new Reversion());
       this.cardsToAdd.add(new WhyShouldWePlay());
       this.cardsToAdd.add(new ItsChord());
       this.cardsToAdd.add(new Sad());
       this.cardsToAdd.add(new MachineHeartSongs());
       this.cardsToAdd.add(new WholeLifeBand());
       this.cardsToAdd.add(new WhyWholeLifeBand());
       this.cardsToAdd.add(new barrel());
//       this.cardsToAdd.add(new image());
//       this.cardsToAdd.add(new YUZUClimax());
//       this.cardsToAdd.add(new YUZUSimplifiedCombo());
//       this.cardsToAdd.add(new KoiKouEnishiSongs());
       this.cardsToAdd.add(new MikaSwissRoll());
       this.cardsToAdd.add(new BocchiAndPlanetSongs());
       this.cardsToAdd.add(new OurCitySongs());
       this.cardsToAdd.add(new Mika());
       this.cardsToAdd.add(new KarenRewardCard());
       this.cardsToAdd.add(new HifuuClubSongs());
       this.cardsToAdd.add(new DustAnonRewards());
       this.cardsToAdd.add(new PoppinPartyRewardSongs());
       this.cardsToAdd.add(new xiuwaxiuwaSongs());
       this.cardsToAdd.add(new FIREBIRDSongs());
//       this.cardsToAdd.add(new OurCitySongsFake());
//       this.cardsToAdd.add(new LockMachineHeartSongs());
       
       this.cardsToAdd.add(new CatHero());
       try{
           this.cardsToAdd.add(new WhiteTrio());
       } catch (NoSuchFieldException e) {
           throw new RuntimeException(e);
       } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
       }
       this.cardsToAdd.add(new GirlsReboot());
       this.cardsToAdd.add(new WorldLegacy());
       this.cardsToAdd.add(new WhiteMythology());
       this.cardsToAdd.add(new WhiteAnonHaruhikageSongs(0));
   }

   //添加事件
     public void receiveEditEvents() {
     }
   //添加遗物
   @Override
   public void receiveEditRelics() {
       //将自定义的遗物添加到这里
       BaseMod.addRelicToCustomPool((AbstractRelic)new Inner(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Guitar(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new GuitarWhiteAnon(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new smartPhone(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Tomori_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Sakiko_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Soyorin_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Rana_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Mutsumi_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Mana_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new Rikki_relic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.Crychic(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.StarAnonGuitar(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.StarAnonPocketWatch(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.EliteAshAnonKey(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.FirstHalfKey(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.SecondHalfKey(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.FrenchFries(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelicToCustomPool((AbstractRelic)new relics.StarAnonBike(),AbstractCardEnum.Anon_COLOR);
       BaseMod.addRelic(new PositionZero(), RelicType.SHARED);
       BaseMod.addRelic(new Receipts(), RelicType.SHARED);
       BaseMod.addRelic(new yakusokuNoBasho(), RelicType.SHARED);
       BaseMod.addRelic(new Anon_determination(), RelicType.SHARED);
       BaseMod.addRelic(new AiHeartBlessing(), RelicType.SHARED);
       BaseMod.addRelic(new Mashiro(), RelicType.SHARED);
       BaseMod.addRelic(new hpBlessing(), RelicType.SHARED);
       BaseMod.addRelic(new IdeaBlessing(), RelicType.SHARED);
       BaseMod.addRelic(new EnergyBlessing(), RelicType.SHARED);
       BaseMod.addRelic(new Gig(), RelicType.SHARED);
       BaseMod.addRelic(new LastBullet(), RelicType.SHARED);
       BaseMod.addRelic(new FanService(), RelicType.SHARED);
   }

   @Override
   public void receiveRelicGet(AbstractRelic relic) {
     //移除遗物,这里移除了小屋子，太垃圾了。

     if (AbstractDungeon.player.name == "Anon") {
//       AbstractDungeon.shopRelicPool.remove("TinyHouse");
//         AbstractDungeon.shopRelicPool.remove("Velvet Choker");
//         AbstractDungeon.shopRelicPool.remove("Ectoplasm");
         AbstractDungeon.bossRelicPool.remove("Velvet Choker");
         AbstractDungeon.bossRelicPool.remove("Ectoplasm");
         AbstractDungeon.bossRelicPool.remove("Snecko Eye");
     }
   }

   @Override
   public void receiveCardUsed(AbstractCard abstractCard) {

   }

   @Override
   public void receivePostBattle(AbstractRoom r) {

   }
     public static final float X = 400;
     public static final float Y = 700;

     public static boolean stopRevolve = true;
     public static boolean givekey = false;

     public static boolean bangdreameffect = true;
     public static final String DESCRIPTION = MOD_NAME;
   @Override
   public void receivePostInitialize() {
       loadSettings();
       ModPanel panel = new ModPanel();
       stopRevolve = true;
       givekey = false;
       bangdreameffect = true;
       String[] buttonLanguage = null;
       if (Settings.language == Settings.GameLanguage.ZHS) {
           buttonLanguage = new String[]{"灰爱音：朋友，停一停，别转了",
                   "这个，不需要了（指收集钥匙）",
                   "只会遇到MODBOSS",
                   "使本mod的boss失效"};
       }else {
            buttonLanguage = new String[]{
                    "AshANon: Friend, stop for a moment, don't spin anymore",
                    "This, not needed anymore (referring to collecting keys)",
                    "Only encounter the MOD BOSS",
                    "Deactivate the bosses of this mod"
            };
       }

       ModLabeledToggleButton revolve = new ModLabeledToggleButton(buttonLanguage[0], X, Y, Color.WHITE,
               FontHelper.buttonLabelFont, stopRevolve, panel, (l) -> {
       }, AnonMod::funToggle);
       ModLabeledToggleButton givekeybutton = new ModLabeledToggleButton(buttonLanguage[1], X, Y-100, Color.WHITE,
               FontHelper.buttonLabelFont, givekey, panel, (l) -> {
       }, AnonMod::givekeyfunToggle);
       panel.addUIElement(revolve);
       panel.addUIElement(givekeybutton);
       BaseMod.registerModBadge(new Texture("modBadge.png"), MOD_NAME, AUTHOR, DESCRIPTION, panel);

       ModLabeledToggleButton OnlyModBoss = new ModLabeledToggleButton(buttonLanguage[2], X, Y-200, Color.WHITE, FontHelper.buttonLabelFont, onlymodboss, panel, (label) -> {
       }, (button) -> {
           onlymodboss = button.enabled;
           try {
               config.setBool("onlymodboss", onlymodboss);
               config.save();
           } catch (IOException var2) {
               var2.printStackTrace();
           }

       });
       panel.addUIElement(OnlyModBoss);
//       ModLabeledToggleButton Forcecanclebosslogic = new ModLabeledToggleButton(buttonLanguage[3], X, Y-300, Color.WHITE, FontHelper.buttonLabelFont, forcecanclebosslogic, panel, (label) -> {
//       }, (button) -> {
//           forcecanclebosslogic = button.enabled;

//           try {
//               config.setBool("forcecanclebosslogic", forcecanclebosslogic);
//               config.save();
//           } catch (IOException var2) {
//               System.out.println("222");
//               var2.printStackTrace();
//           }

//       });
//       panel.addUIElement(Forcecanclebosslogic);

       BaseMod.addMonster(SpiritFireMonster.ID,
               CardCrawlGame.languagePack.getMonsterStrings(SpiritFireMonster.ID).NAME,
               () -> {
                   if (AbstractDungeon.actNum == 3) {
                       return new MonsterGroup(new SpiritFireMonster());
                   } else if (AbstractDungeon.actNum == 2) {
                       return new MonsterGroup(new SpiritFireMonster());
                   } else {
                       return new MonsterGroup(new SpiritFireMonster());
                   }
               });

//       BaseMod.addMonster("Nina", ()->{
//           AbstractMonster[] InnerFavillaeSide = new AbstractMonster[1];
//           InnerFavillaeSide[0] = new soyorin();
//           return new MonsterGroup(InnerFavillaeSide);
//       });
       BaseMod.addMonster("InnerFavillaeSide", ()->{
           AbstractMonster[] InnerFavillaeSide = new AbstractMonster[1];
           InnerFavillaeSide[0] = new InnerFavillaeSide(0,0);
           return new MonsterGroup(InnerFavillaeSide);
       });
       BaseMod.addMonster("StarAnonSide", StarAnonSide::new);
       BaseMod.addMonster("CrychicSide", ()->{
           AbstractMonster[] CrychicSide = new AbstractMonster[1];
           CrychicSide[0] = new CrychicSide(0,0);
           return new MonsterGroup(CrychicSide);
       });
       BaseMod.addMonster("AnonSide", ()->{
           AbstractMonster[] AnonSide = new AbstractMonster[1];
           AnonSide[0] = new AnonSide(0,0);
           return new MonsterGroup(AnonSide);
       });
       BaseMod.addMonster("Nina", TOGETOGE::new);
           BaseMod.addBoss(Exordium.ID, "Nina", "img/map/ToTo500.png", "img/map/ToTo500.png");

       BaseMod.addMonster("Mika", mika::new);
       BaseMod.addBoss(FakeDungeon.id, "Mika", "img/map/mikaboss.png", "img/map/mikaboss.png");

       BaseMod.addMonster("Bocchi", ()->{
           AbstractMonster[] Act1Boss = new AbstractMonster[2];
           Act1Boss[0] = new Bocchi(-100,0);
           Act1Boss[1] = new Ikuyo(200,0);
           return new MonsterGroup(Act1Boss);
       });
       BaseMod.addBoss(Exordium.ID, "Bocchi", "img/map/BocchiLogo.png", "img/map/BocchiLogo.png");
       BaseMod.addMonster("HifuuClub", ()->{
           AbstractMonster[] Act1Boss = new AbstractMonster[2];
           Act1Boss[0] = new Renko(-100,0);
           Act1Boss[1] = new Hearn(200,0);
           return new MonsterGroup(Act1Boss);
       });
       BaseMod.addBoss(Exordium.ID, "HifuuClub", "img/map/HifuuClubLogo.png", "img/map/HifuuClubLogo.png");
       BaseMod.addMonster("Roselia", ()->{
           AbstractMonster[] Roselia = new AbstractMonster[1];
           Roselia[0] = new Yukina(0,0);
           return new MonsterGroup(Roselia);
       });
       BaseMod.addBoss(TheCity.ID, "Roselia", "img/map/Roselia.png", "img/map/Roselia.png");

       BaseMod.addMonster("Poppin'Party", ()->{
           AbstractMonster[] Act2Boss = new AbstractMonster[2];
           Act2Boss[0] = new KSMBoss(-200,0);
           Act2Boss[1] = new ARSBoss(200,0);
           return new MonsterGroup(Act2Boss);
       });
       BaseMod.addBoss(TheCity.ID, "Poppin'Party", "img/map/PPLOGO.png", "img/map/PPLOGO.png");
       BaseMod.addMonster("Pastel*Palettes", ()->{
           AbstractMonster[] Act2Boss = new AbstractMonster[1];
           Act2Boss[0] = new caicai(200,0);
           return new MonsterGroup(Act2Boss);
       });
       BaseMod.addBoss(TheCity.ID, "Pastel*Palettes", "img/map/PPLOGO2.png", "img/map/PPLOGO2.png");
       BaseMod.addMonster("DustAnon", ()->{
           AbstractMonster[] DustAnon = new AbstractMonster[3];
           DustAnon[0] = new Crane(0,0);
           DustAnon[1] = new RoadRoller(-450,50);
           DustAnon[2] = new LeaderAnon(-100,100);
           return new MonsterGroup(DustAnon);
       });
       BaseMod.addBoss(FakeDungeon.id, "DustAnon", "img/map/tong.png", "img/map/tong.png");
       
       BaseMod.addMonster("ShoujoKageki", ()->{
           AbstractMonster[] Act3Boss = new AbstractMonster[1];
           Act3Boss[0] = new Karen(0,0);
           return new MonsterGroup(Act3Boss);
       });
       BaseMod.addBoss(FakeDungeon.id, "ShoujoKageki", "img/char/karen/skin_fan/aijo.png", "img/char/karen/skin_fan/aijo.png");

       BaseMod.addEvent(StarAnonEvent.StarAnonEventID, StarAnonEvent.class, BeyondTheStar.ID);
//       BaseMod.addEvent(SakikoShop.ID, SakikoShop.class, Exordium.ID);

       BeyondTheStar.NarrationAdd();
       ExcessiveFantasyWorld.ExcessiveFantasyWorldAdd();
       promiseOnStage.Add();
       TheTreeOfQliphoth.TheTreeOfQliphothAdd();

       unlockFinalAct();
       unlockBetaArtAndEnding();

       if (Settings.language == Settings.GameLanguage.ZHS) {
           CardCrawlGame.languagePack.getEventString(ActLikeIt.makeID("ForkInTheRoad")).OPTIONS[4] = "挑战里爱音，丰川祥子，灰爱音，心脏（需要凑齐三把钥匙）";
           CardCrawlGame.languagePack.getEventString(ActLikeIt.makeID("ForkInTheRoad")).DESCRIPTIONS[6] = "你的旅途即将抵达尽头 NL 选择你最后的 #b目标 ";
           CardCrawlGame.languagePack.getEventString(ActLikeIt.makeID("ForkInTheRoad")).DESCRIPTIONS[3] = "追随着过去的幻影，逐渐唤醒曾经乐队的记忆……";
       }else {
           CardCrawlGame.languagePack.getEventString(ActLikeIt.makeID("ForkInTheRoad")).OPTIONS[4] =
                   "Challenge InnerAnon, Sakiko Toyokawa, AshAnon, and the Heart (requires collecting three keys)";

           CardCrawlGame.languagePack.getEventString(ActLikeIt.makeID("ForkInTheRoad")).DESCRIPTIONS[6] =
                   "Your journey is about to reach its end. NL Choose your final #b target";

           CardCrawlGame.languagePack.getEventString(ActLikeIt.makeID("ForkInTheRoad")).DESCRIPTIONS[3] =
                   "Following the phantoms of the past, gradually awaken the memories of the once and former band...";
       }


       BaseMod.addBoss("TheEnding", "The Heart", "img/UI/lock.png", "img/UI/lock.png");
       BaseMod.addBoss("TheEnding", "The Heart", "img/UI/lock.png", "img/UI/lock.png");
       BaseMod.addBoss("TheEnding", "The Heart", "img/UI/lock.png", "img/UI/lock.png");

       BaseMod.addPotion(liveboost.class, liveboost.liquidColor, liveboost.hybridColor, liveboost.spotsColor, liveboost.ID);
       BaseMod.addPotion(IdeaPotion.class, IdeaPotion.liquidColor, IdeaPotion.hybridColor, IdeaPotion.spotsColor, IdeaPotion.ID);


       BaseMod.registerCustomReward(
               RewardTypeEnum.REWARD,
               (rewardSave) -> { // 该类型加载时的处理
                   return new DreamCardRewards(rewardSave.amount);
               },
               (customReward) -> { // 该类型保存时的处理
                   return new RewardSave(customReward.type.toString(), null, ((DreamCardRewards)customReward).amount, 0);
               });

   }

     public static void unlockFinalAct() {
         CardCrawlGame.playerPref.putBoolean(String.valueOf(AbstractPlayer.PlayerClass.IRONCLAD.name()) + "_WIN", true);
         CardCrawlGame.playerPref.putBoolean(String.valueOf(AbstractPlayer.PlayerClass.THE_SILENT.name()) + "_WIN", true);
         CardCrawlGame.playerPref.putBoolean(String.valueOf(AbstractPlayer.PlayerClass.DEFECT.name()) + "_WIN", true);
         CardCrawlGame.playerPref.flush();
     }

     public static void unlockBetaArtAndEnding() {
         String key = "THE_ENDING";
         if (!UnlockTracker.achievementPref.getBoolean(key, false)) {
             UnlockTracker.achievementPref.putBoolean(key, true);
             UnlockTracker.achievementPref.flush();
         }
     }
     public static ModInfo info;
     static {
         loadModInfo();
     }
     public static String modID;
     private static void loadModInfo() {
         Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo) -> {
             AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
             if (annotationDB == null)
                 return false;
             Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
             return initializers.contains(AnonMod.class.getName());
         }).findFirst();
         if (infos.isPresent()) {
             info = infos.get();
             modID = info.ID;
         } else {
             throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
         }
     }
     private static void funToggle(ModToggleButton t) {
         stopRevolve = t.enabled;
         saveSettings();
     }
     private static void givekeyfunToggle(ModToggleButton t) {
         givekey = t.enabled;
         saveSettings();
     }
     private static void bangdreameffectfunToggle(ModToggleButton t) {
         givekey = t.enabled;
         saveSettings();
     }
     private static void saveSettings() {
         config.setBool("stopRevolve", stopRevolve);
         config.setBool("givekey", givekey);
         config.setBool("bangdreameffect", bangdreameffect);
         try {
             config.save();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
     }
     private static void loadSettings() {
         try {
             config = new SpireConfig(MOD_NAME, MOD_NAME + "Config");
             config.load();
         } catch (Exception ex) {
             logger.catching(ex);
         }
         stopRevolve = config.getBool("stopRevolve");
     }
   @Override
   public void receivePostEnergyRecharge() {
     Iterator<AbstractCard> var1 = recyclecards.iterator();

     while (var1.hasNext()) {
       AbstractCard c = var1.next();
       AbstractCard card = c.makeStatEquivalentCopy();
       AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
     }
     recyclecards.clear();
   }

   private static final String resourcesFolder = "rosmod1";
     public static String powerPath(String file) {
         return resourcesFolder + "/images/powers/" + file;
     }

     public static String imagePath(String file) {
         return resourcesFolder + "/images/" + file;
     }

     @Override
     public void receivePostRender(SpriteBatch spriteBatch) {
         if (RestartRunHelper.queuedScoreRestart) {
             RestartRunHelper.scoreAndRestart();
         } else if (RestartRunHelper.queuedRestart) {
             RestartRunHelper.restartRun();
         } else if (RestartRunHelper.queuedRoomRestart) {
             RestartRunHelper.restartRoom();
         }
     }

     static  {
         try {
             saves = new SpireConfig("sp-racing", "saves");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     @Override
     public void receivePostCreateStartingDeck(AbstractPlayer.PlayerClass playerClass, CardGroup cardGroup) {
         if(playerClass.equals(ThmodClassEnum.Anon_COLOR) && SkinSelectScreen.Inst.index == 16){
             AbstractCard s = (new KiraKiraDokiDoki()).makeCopy();
             s.upgrade();
             cardGroup.addToBottom(s);
         }
         if(playerClass.equals(ThmodClassEnum.Anon_COLOR) && SkinSelectScreen.Inst.index == 13){
             cardGroup.clear();
             cardGroup.addToBottom( (new barrel()).makeCopy());
             cardGroup.addToBottom( (new CatchCivilEngineering()).makeCopy());
             cardGroup.addToBottom( (new dust()).makeCopy());
             cardGroup.addToBottom( (new LeadershipInspection()).makeCopy());
             cardGroup.addToBottom( (new Mixer()).makeCopy());
             cardGroup.addToBottom( (new RedRomantic()).makeCopy());
             cardGroup.addToBottom( (new RelatedHouseholds()).makeCopy());
             cardGroup.addToBottom( (new Scapegoat()).makeCopy());
             cardGroup.addToBottom( (new UnderMixer()).makeCopy());
             cardGroup.addToBottom( (new ProjectTwo()).makeCopy());
         }
     }
     @Override
     public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass playerClass, ArrayList<String> arrayList) {
         if(SkinSelectPatch.isAnonSelected()) {
             arrayList.clear();
             switch (SkinSelectScreen.Inst.index) {
                 case 2:
                     arrayList.add("GuitarWhiteAnon");
                     break;
                 case 3:
                     arrayList.add("StarAnonBike");
                     break;
                 default:
                     arrayList.add("Guitar");
             }
         }
     }

     @Override
     public void receivePostUpdate() {
         time += Gdx.graphics.getRawDeltaTime();
         if (!myActions.isEmpty()) {
             ((AbstractGameAction)myActions.get(0)).update();
             if (((AbstractGameAction)myActions.get(0)).isDone) myActions.remove(0);
         }
         if (CardCrawlGame.mode != CardCrawlGame.GameMode.GAMEPLAY && !postProcessors.isEmpty()) {
             clearPostProcessors();
         }
     }

     @Override
     public void receiveStartAct() {
         if(AbstractDungeon.player.hasRelic(StarAnonBike.ID)){
             for (AbstractRelic bike : AbstractDungeon.player.relics) {
                 if (bike.relicId.equals(StarAnonBike.ID)) {
                     bike.flash();
                     ((StarAnonBike)bike).useAgain();
                 }
                 if (bike.relicId.equals(StarAnonBike.ID)) {
                     bike.flash();
                     ((StarAnonBike)bike).useAgain();
                 }
             }
         }
         if (AbstractDungeon.id.equals(TheTreeOfQliphoth.ID)){
             for (AbstractRelic Basho : AbstractDungeon.player.relics) {
                 if (Basho.relicId.equals(yakusokuNoBasho.ID)) {
                     ((yakusokuNoBasho)Basho).usedUp();
                 }
             }
         }
     }

     @Override
     public void receivePostDeath() {

     }

     @Override
     public void receiveAddAudio() {
         BaseMod.addAudio("murimuri","vfx/CH0069_Battle_Defense_1.ogg");
     }


     class Keywords {
     Keyword[] keywords;
   }

 }


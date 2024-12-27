package OnStage;


import actlikeit.dungeons.CustomDungeon;
import basemod.BaseMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import relics.smartPhone;
import star.StarAnonEvent;
import star.starEventRoom;


import java.util.ArrayList;

public class promiseOnStage extends CustomDungeon {

  public static String ID = "promiseOnStage";

  private static final EventStrings EventStrings = CardCrawlGame.languagePack.getEventString(ID);

  public static  String NAME = EventStrings.NAME;

//   public static final String OPTION = TEXT[2];
public static void Add() {

  CustomDungeon.addAct(999, new promiseOnStage());
   BaseMod.addMonster("theEndKaren", theEndKaren::new);
   BaseMod.addBoss(ID, "theEndKaren", "img/char/karen/skin_fan/aijo.png", "img/char/karen/skin_fan/aijo.png");

}

public promiseOnStage() {
  super(NAME, ID, "img/map/oldpanel.png", false, 0, 0, 1);
}



public promiseOnStage(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) { super(cd, p, emptyList); }

public promiseOnStage(CustomDungeon cd, AbstractPlayer p, SaveFile saveFile) { super(cd, p, saveFile); }

public void Ending() {
  CardCrawlGame.music.fadeOutBGM();
  MapRoomNode node = new MapRoomNode(3, 4);
  node.room = new TrueVictoryRoom();
  AbstractDungeon.nextRoom = node;
  AbstractDungeon.closeCurrentScreen();
  AbstractDungeon.nextRoomTransitionStart();
  AbstractDungeon.overlayMenu.proceedButton.hide();

//     CardCrawlGame.music.fadeOutBGM();
//     MapRoomNode node = new MapRoomNode(3, 4);
//     node.room = new AnonVictoryRoom();
//     AbstractDungeon.nextRoom = node;
//     AbstractDungeon.closeCurrentScreen();
//     AbstractDungeon.nextRoomTransitionStart();
}


public String getOptionText() { return "前往约定的地方"; }



public AbstractScene DungeonScene() { return new TheEndingScene(); }




 protected void makeMap() {
     AbstractDungeon.levelNum = "少女歌剧";
     smartPhone.ifHeart = false;
   map = new ArrayList();
   ArrayList<MapRoomNode> row1 = new ArrayList<MapRoomNode>();
   MapRoomNode restNode = new MapRoomNode(3, 0);
     restNode.room = new TheEndKarenEventRoom(restNode);
   MapRoomNode shopNode = new MapRoomNode(3, 1);
   shopNode.room = new RestRoom();
     MapRoomNode enemyNode = new MapRoomNode(3, 2);
     enemyNode.room = new ShopRoom();
     MapRoomNode bossNode = new MapRoomNode(3, 3);
     bossNode.room = new MonsterRoomBoss();
     MapRoomNode victoryNode = new MapRoomNode(3, 4);
     victoryNode.room = new TrueVictoryRoom();

    connectNode(restNode, shopNode);
    connectNode(shopNode, enemyNode);
    enemyNode.addEdge(new MapEdge(enemyNode.x, enemyNode.y, enemyNode.offsetX, enemyNode.offsetY, bossNode.x, bossNode.y, bossNode.offsetX, bossNode.offsetY, false));
    row1.add(new MapRoomNode(0, 0));
    row1.add(new MapRoomNode(1, 0));
    row1.add(new MapRoomNode(2, 0));
    row1.add(restNode);
    row1.add(new MapRoomNode(4, 0));
    row1.add(new MapRoomNode(5, 0));
    row1.add(new MapRoomNode(6, 0));
    ArrayList<MapRoomNode> row2 = new ArrayList<MapRoomNode>();
    row2.add(new MapRoomNode(0, 1));
    row2.add(new MapRoomNode(1, 1));
    row2.add(new MapRoomNode(2, 1));
    row2.add(shopNode);
    row2.add(new MapRoomNode(4, 1));
    row2.add(new MapRoomNode(5, 1));
    row2.add(new MapRoomNode(6, 1));
         ArrayList<MapRoomNode> row3 = new ArrayList<MapRoomNode>();
         row3.add(new MapRoomNode(0, 2));
         row3.add(new MapRoomNode(1, 2));
         row3.add(new MapRoomNode(2, 2));
         row3.add(enemyNode);
         row3.add(new MapRoomNode(4, 2));
         row3.add(new MapRoomNode(5, 2));
         row3.add(new MapRoomNode(6, 2));
         ArrayList<MapRoomNode> row4 = new ArrayList<MapRoomNode>();
         row4.add(new MapRoomNode(0, 3));
         row4.add(new MapRoomNode(1, 3));
         row4.add(new MapRoomNode(2, 3));
         row4.add(bossNode);
         row4.add(new MapRoomNode(4, 3));
         row4.add(new MapRoomNode(5, 3));
         row4.add(new MapRoomNode(6, 3));
         ArrayList<MapRoomNode> row5 = new ArrayList<MapRoomNode>();
         row5.add(new MapRoomNode(0, 4));
         row5.add(new MapRoomNode(1, 4));
         row5.add(new MapRoomNode(2, 4));
         row5.add(victoryNode);
         row5.add(new MapRoomNode(4, 4));
         row5.add(new MapRoomNode(5, 4));
         row5.add(new MapRoomNode(6, 4));
         map.add(row1);
         map.add(row2);
         map.add(row3);
         map.add(row4);
         map.add(row5);

    logger.info("Generated the following dungeon map:");
    logger.info(MapGenerator.toString(map, Boolean.valueOf(true)));
    logger.info("Game Seed: " + Settings.seed);

    firstRoomChosen = false;
    fadeIn();
  }


  private void connectNode(MapRoomNode src, MapRoomNode dst) { src.addEdge(new MapEdge(src.x, src.y, src.offsetX, src.offsetY, dst.x, dst.y, dst.offsetX, dst.offsetY, false)); }



  protected void initializeLevelSpecificChances() {
      shopRoomChance = 0.00F;
      restRoomChance = 0.00F;
      treasureRoomChance = 0.0F;
      eventRoomChance = 1.00F;
      eliteRoomChance = 0.00F;

      smallChestChance = 30;
      mediumChestChance = 35;
      largeChestChance = 35;

      commonRelicChance = 50;
      uncommonRelicChance = 33;
      rareRelicChance = 17;
      colorlessRareChance = 0.3F;
    if (AbstractDungeon.ascensionLevel >= 12) {
      cardUpgradedChance = 0.25F;
    } else {
      cardUpgradedChance = 0.5F;
    }
  }

  protected void generateMonsters() {
    monsterList = new ArrayList();
    monsterList.add("Alpha");
    monsterList.add("Alpha");
    monsterList.add("Alpha");
    eliteMonsterList = new ArrayList();
    eliteMonsterList.add("Alpha");
    eliteMonsterList.add("Alpha");
    eliteMonsterList.add("Alpha");
  }


  protected void generateWeakEnemies(int count) {}


  protected void generateStrongEnemies(int count) {}


  protected void generateElites(int count) {}


  protected ArrayList<String> generateExclusions() { return new ArrayList(); }

  protected void initializeBoss() {
    bossList.add("theEndKaren");
    bossList.add("theEndKaren");
    bossList.add("theEndKaren");
  }

  protected void initializeEventList() {}

  protected void initializeEventImg() {
    if (eventBackgroundImg != null) {
      eventBackgroundImg.dispose();
      eventBackgroundImg = null;
    }
//     eventBackgroundImg = ImageMaster.loadImage("/img/UI/panel.png");
  }

  protected void initializeShrineList() {}
}


/* Location:              C:\Users\Administrator\Deskto\\uncommon\Paranoia.jar!\TNN\Paranoia\dungeon\Narration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
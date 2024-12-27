package ExcessiveFantasy;


import ExcessiveFantasy.boss.ExcessiveFantasyTrueEndingRoom;
import actlikeit.dungeons.CustomDungeon;
import basemod.BaseMod;
import ExcessiveFantasy.boss.TheConsciousnessOfTheWorld;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.beyond.Deca;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;

import java.util.ArrayList;
import java.util.Collections;

public class ExcessiveFantasyWorld extends CustomDungeon {

  public static String ID = "ExcessiveFantasy";

  private static final EventStrings EventStrings = CardCrawlGame.languagePack.getEventString(ID);

  public static  String NAME = EventStrings.NAME;

//   public static final String OPTION = TEXT[2];
public static void ExcessiveFantasyWorldAdd() {

  CustomDungeon.addAct(5, new ExcessiveFantasyWorld());
   BaseMod.addMonster("TheConsciousnessOfTheWorld", TheConsciousnessOfTheWorld::new);
   BaseMod.addBoss(ID, "TheConsciousnessOfTheWorld", "img/map/emptypixel.png", "img/map/emptypixel.png");
    BaseMod.addMonster("The End Awakened One", ()->{
        return new MonsterGroup(new AbstractMonster[] { new Cultist(-590.0F, 10.0F, false), new Cultist(-298.0F, -10.0F, false), new AwakenedOne(100.0F, 15.0F) });
    });
    BaseMod.addMonster("The End Time Eater", ()->{
        return new MonsterGroup(new TimeEater());
    });
    BaseMod.addMonster("The End Donu and Deca", ()->{
        return new MonsterGroup(new AbstractMonster[] { new Deca(), new Donu() });
    });
}

public ExcessiveFantasyWorld() {
  super(NAME, ID, "img/map/oldpanel.png", false, 0, 0, 1);
//     this.onEnterEvent(StarAnonEvent.class);
//     isFinalAct(true);
}



public ExcessiveFantasyWorld(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) { super(cd, p, emptyList); }

public ExcessiveFantasyWorld(CustomDungeon cd, AbstractPlayer p, SaveFile saveFile) { super(cd, p, saveFile); }

public void Ending() {
  CardCrawlGame.music.fadeOutBGM();
  MapRoomNode node = new MapRoomNode(3, 4);
  node.room = new ExcessiveFantasyTrueEndingRoom();
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


public String getOptionText() { return "???"; }



public AbstractScene DungeonScene() { return new ExcessiveFantasyScene(); }




 protected void makeMap() {
     AbstractDungeon.name = "千早爱音的冒险";
     AbstractDungeon.levelNum = "新篇";
     map = new ArrayList();
     ArrayList<MapRoomNode> row1 = new ArrayList<MapRoomNode>();
     MapRoomNode restNode = new MapRoomNode(3, 0);
     restNode.room = new ExcessiveFantasyFirstEventRoom();
     MapRoomNode shopNode = new MapRoomNode(3, 1);
     shopNode.room = new ExcessiveFantasySecondEventRoom();
     MapRoomNode enemyNode = new MapRoomNode(3, 2);
     enemyNode.room = new SakikoEventRoom(enemyNode,"ExcessiveFantasyAwake");
     enemyNode.room.setMapImg(ImageMaster.MAP_NODE_ELITE,ImageMaster.MAP_NODE_ELITE);
     MapRoomNode sakiko = new MapRoomNode(3, 3);
     sakiko.room = new SakikoEventRoom(sakiko,"TheFinSakikoShop");
     sakiko.room.setMapImg(ImageMaster.loadImage("img/UI/FamilyMart.png"), ImageMaster.loadImage("img/UI/FamilyMart.png"));
     MapRoomNode FakeRest = new MapRoomNode(3, 4);
     FakeRest.room = new ExcessiveFantasyRestRoom();
     MapRoomNode bossNode = new MapRoomNode(3, 5);
     bossNode.room = new MonsterRoomBoss();
     MapRoomNode victoryNode = new MapRoomNode(3, 6);
     victoryNode.room = new ExcessiveFantasyTrueEndingRoom();

    connectNode(restNode, shopNode);
    connectNode(shopNode, enemyNode);
     connectNode(enemyNode, sakiko);
     connectNode(sakiko, FakeRest);
     connectNode(FakeRest, bossNode);
//    enemyNode.addEdge(new MapEdge(enemyNode.x, enemyNode.y, enemyNode.offsetX, enemyNode.offsetY, bossNode.x, bossNode.y, bossNode.offsetX, bossNode.offsetY, false));
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
         row4.add(sakiko);
         row4.add(new MapRoomNode(4, 3));
         row4.add(new MapRoomNode(5, 3));
         row4.add(new MapRoomNode(6, 3));
         ArrayList<MapRoomNode> row5 = new ArrayList<MapRoomNode>();
         row5.add(new MapRoomNode(0, 4));
         row5.add(new MapRoomNode(1, 4));
         row5.add(new MapRoomNode(2, 4));
         row5.add(FakeRest);
         row5.add(new MapRoomNode(4, 4));
         row5.add(new MapRoomNode(5, 4));
         row5.add(new MapRoomNode(6, 4));
     ArrayList<MapRoomNode> row6 = new ArrayList<MapRoomNode>();
     row6.add(new MapRoomNode(0, 5));
     row6.add(new MapRoomNode(1, 5));
     row6.add(new MapRoomNode(2, 5));
     row6.add(bossNode);
     row6.add(new MapRoomNode(4, 5));
     row6.add(new MapRoomNode(5, 5));
     row6.add(new MapRoomNode(6, 5));
     ArrayList<MapRoomNode> row7 = new ArrayList<MapRoomNode>();
     row7.add(new MapRoomNode(0, 6));
     row7.add(new MapRoomNode(1, 6));
     row7.add(new MapRoomNode(2, 6));
     row7.add(victoryNode);
     row7.add(new MapRoomNode(4, 6));
     row7.add(new MapRoomNode(5, 6));
     row7.add(new MapRoomNode(6, 6));
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
      eliteMonsterList.add("The End Awakened One");
      eliteMonsterList.add("The End Time Eater");
      eliteMonsterList.add("The End Donu and Deca");
      Collections.shuffle(eliteMonsterList);
  }


  protected void generateWeakEnemies(int count) {}


  protected void generateStrongEnemies(int count) {}


  protected void generateElites(int count) {
//      ArrayList<MonsterInfo> monsters = new ArrayList();
//      monsters.add(new MonsterInfo("The End Awakened One", 2.0F));
//      monsters.add(new MonsterInfo("The End Time Eater", 2.0F));
//      monsters.add(new MonsterInfo("The End Donu and Deca", 2.0F));
//      MonsterInfo.normalizeWeights(monsters);
//      this.populateMonsterList(monsters, count, true);
  }


  protected ArrayList<String> generateExclusions() { return new ArrayList(); }

  protected void initializeBoss() {
    bossList.add("TheConsciousnessOfTheWorld");
    bossList.add("TheConsciousnessOfTheWorld");
    bossList.add("TheConsciousnessOfTheWorld");
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
package TheTreeOfQliphoth;


import actlikeit.dungeons.CustomDungeon;
import basemod.BaseMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import relics.smartPhone;

import java.util.ArrayList;

public class TheTreeOfQliphoth extends CustomDungeon {

  public static String ID = "TheTreeOfQliphoth";

public static void TheTreeOfQliphothAdd() {

  CustomDungeon.addAct(4, new TheTreeOfQliphoth());
   BaseMod.addMonster("Beast", FinAnonSide::new);
   BaseMod.addBoss(ID, "Beast", "tree/ui/BeastⅣ.png", "tree/ui/BeastⅣ.png");


//   BaseMod.addMonster("Paranoia:Alpha", () -> new MonsterGroup(new AbstractMonster[] { new StarAnonSide()}));
}

public TheTreeOfQliphoth() {
  super("逆卡巴拉生命树", ID, "img/map/oldpanel.png", false, 0, 0, 1);
    this.onEnterEvent(Present.class);
//     isFinalAct(true);
}



public TheTreeOfQliphoth(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) { super(cd, p, emptyList); }

public TheTreeOfQliphoth(CustomDungeon cd, AbstractPlayer p, SaveFile saveFile) { super(cd, p, saveFile); }

public void Ending() {
  CardCrawlGame.music.fadeOutBGM();
  MapRoomNode node = new MapRoomNode(3, 4);
  node.room = new TrueVictoryRoom();
  AbstractDungeon.nextRoom = node;
  AbstractDungeon.closeCurrentScreen();
  AbstractDungeon.nextRoomTransitionStart();
  AbstractDungeon.overlayMenu.proceedButton.hide();
}


public String getOptionText() { return "获得祝福，进入bossrush模式!"; }



public AbstractScene DungeonScene() { return new TheEndingScene(); }


 protected void makeMap() {
     ArrayList<MonsterRoomCreator> Bossrush = new ArrayList();
     Bossrush.add(new MonsterRoomCreator("img/map/mygoLogo.png", "img/map/mygoLogo.png", "AnonSide"));
     Bossrush.add(new MonsterRoomCreator("img/relics/mask.png", "img/relics/mask.png", "CrychicSide"));
     Bossrush.add(new MonsterRoomCreator("img/map/IF.png", "img/map/IF.png", "InnerFavillaeSide"));
     Bossrush.add(new MonsterRoomCreator("img/relics/PocketWatch.png", "img/relics/PocketWatch.png", "StarAnonSide"));
     ArrayList<MonsterRoomCreator> smallBossrush = new ArrayList();
     smallBossrush.add(new MonsterRoomCreator("img/map/ToTo500Bossrush.png", "img/map/ToTo500Bossrush.png", "Nina"));
     smallBossrush.add(new MonsterRoomCreator("img/map/PPLOGOBossrush.png", "img/map/PPLOGOBossrush.png", "Poppin'Party"));
     smallBossrush.add(new MonsterRoomCreator("img/map/tongBossrush.png", "img/map/tongBossrush.png", "DustAnon"));
     smallBossrush.add(new MonsterRoomCreator("img/map/aijoBossrush.png", "img/map/aijoBossrush.png", "ShoujoKageki"));
     smartPhone.ifHeart = false;
     map = new ArrayList();
     int index = 0;
   ArrayList<MapRoomNode> row1 = new ArrayList<MapRoomNode>();
   MapRoomNode one = new MapRoomNode(3, 0);
     one.room = new newShopRoom();
   MapRoomNode two = new MapRoomNode(3, 1);
     two.room = smallBossrush.get(0).get();
     MapRoomNode three = new MapRoomNode(1, 2);
     three.room = smallBossrush.get(1).get();
     MapRoomNode four = new MapRoomNode(5, 2);
     four.room = smallBossrush.get(2).get();
     MapRoomNode five = new MapRoomNode(3, 3);
     five.room = smallBossrush.get(3).get();
     MapRoomNode six = new MapRoomNode(1, 4);
     six.room = Bossrush.get(1).get();
     MapRoomNode seven = new MapRoomNode(5, 4);
     seven.room = Bossrush.get(0).get();
     MapRoomNode eight = new MapRoomNode(1, 5);
     eight.room = Bossrush.get(2).get();
     MapRoomNode nine = new MapRoomNode(5, 5);
     nine.room = Bossrush.get(3).get();
     MapRoomNode ten = new MapRoomNode(3, 6);
     ten.room = new MonsterRoomBoss();
     MapRoomNode fin = new MapRoomNode(3, 7);
     fin.room = new TrueVictoryRoom();
    connectNode(one, two);
    connectNode(two, three);
//     connectNode(four,two);
//     connectNode(four,two);
//     connectNode(four,two);
//     connectNode(three, five);
     connectNode(four, five);
//     connectNode(six,five );
     connectNode(five, seven);
     connectNode(six, eight);
//     connectNode(nine,seven);
//     connectNode(eight, ten);
     connectNode(nine, ten);


//     connectNode(one,three );
//     connectNode(three,one );
//     connectNode(four,one );
//     connectNode(five,two);
//     connectNode(three, six);
//     connectNode(four, seven);
//     connectNode(eight,five );
//     connectNode(nine,five );
//     connectNode(ten,five );
//     connectNode(three, four);
     connectNode(three,four);
     connectNode( seven,six);
     connectNode(eight, nine);

//     eight.addEdge(new MapEdge(eight.x, eight.y, eight.offsetX, eight.offsetY, fin.x, fin.y, fin.offsetX, fin.offsetY+250, false));
//     nine.addEdge(new MapEdge(nine.x, nine.y, nine.offsetX, nine.offsetY, fin.x, fin.y, fin.offsetX, fin.offsetY+250, false));
//     five.addEdge(new MapEdge(nine.x, nine.y, nine.offsetX, nine.offsetY, fin.x, fin.y, fin.offsetX, fin.offsetY+250, false));
    row1.add(new MapRoomNode(0, 0));
    row1.add(new MapRoomNode(1, 0));
    row1.add(new MapRoomNode(2, 0));
    row1.add(one);
    row1.add(new MapRoomNode(4, 0));
    row1.add(new MapRoomNode(5, 0));
    row1.add(new MapRoomNode(6, 0));
    ArrayList<MapRoomNode> row2 = new ArrayList<MapRoomNode>();
    row2.add(new MapRoomNode(0, 1));
    row2.add(new MapRoomNode(1, 1));
    row2.add(new MapRoomNode(2, 1));
    row2.add(two);
    row2.add(new MapRoomNode(4, 1));
    row2.add(new MapRoomNode(5, 1));
    row2.add(new MapRoomNode(6, 1));
         ArrayList<MapRoomNode> row3 = new ArrayList<MapRoomNode>();
         row3.add(new MapRoomNode(0, 2));
         row3.add(three);
         row3.add(new MapRoomNode(2, 2));
         row3.add(new MapRoomNode(5, 2));
         row3.add(new MapRoomNode(4, 2));
         row3.add(four);
         row3.add(new MapRoomNode(6, 2));
         ArrayList<MapRoomNode> row4 = new ArrayList<MapRoomNode>();
         row4.add(new MapRoomNode(0, 3));
         row4.add(new MapRoomNode(1, 3));
         row4.add(new MapRoomNode(2, 3));
         row4.add(five);
         row4.add(new MapRoomNode(4, 3));
         row4.add(new MapRoomNode(5, 3));
         row4.add(new MapRoomNode(6, 3));
         ArrayList<MapRoomNode> row5 = new ArrayList<MapRoomNode>();
         row5.add(new MapRoomNode(0, 4));
         row5.add(six);
         row5.add(new MapRoomNode(2, 4));
         row5.add(new MapRoomNode(2, 4));
         row5.add(new MapRoomNode(4, 4));
         row5.add(seven);
         row5.add(new MapRoomNode(6, 4));
     ArrayList<MapRoomNode> row6 = new ArrayList<MapRoomNode>();
     row6.add(new MapRoomNode(0, 5));
     row6.add(eight);
     row6.add(new MapRoomNode(2, 5));
     row6.add(new MapRoomNode(2, 5));
     row6.add(new MapRoomNode(4, 5));
     row6.add(nine);
     row6.add(new MapRoomNode(6, 5));
     ArrayList<MapRoomNode> row7 = new ArrayList<MapRoomNode>();
    row7.add(new MapRoomNode(0, 6));
    row7.add(new MapRoomNode(1, 6));
    row7.add(new MapRoomNode(2, 6));
    row7.add(ten);
    row7.add(new MapRoomNode(4, 6));
    row7.add(new MapRoomNode(5, 6));
    row7.add(new MapRoomNode(6, 6));
     ArrayList<MapRoomNode> row8 = new ArrayList<MapRoomNode>();
     row8.add(new MapRoomNode(0, 7));
     row8.add(new MapRoomNode(1, 7));
     row8.add(new MapRoomNode(2, 7));
     row8.add(fin);
     row8.add(new MapRoomNode(4, 7));
     row8.add(new MapRoomNode(5, 7));
     row8.add(new MapRoomNode(6, 7));
         map.add(row1);
         map.add(row2);
         map.add(row3);
         map.add(row4);
         map.add(row5);
     map.add(row6);
     map.add(row7);
     map.add(row8);

//     ArrayList<MonsterRoomCreator> easyishEncounters = new ArrayList();
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
//     easyishEncounters.add(new MonsterRoomCreator("tree/ui/map/wokeone.png", "tree/ui/map/wokeone.png", SlaverBlue.ID));
////     if (!Loader.isModLoaded("TheBandit") || !(AbstractDungeon.player instanceof TheBandit)) {
////         toughEncounters.add(new MonsterRoomCreator(PaleMod.assetPath("images/ui/map/bandit.png"), PaleMod.assetPath("images/ui/map/banditOutline.png"), paleoftheancients.bandit.monsters.TheBandit.ID));
////     }
//     map.add(this.singleNodeArea(new EventRoom(), index++));
//     map.add(this.singleNodeArea(new EventRoom(), index++));
//     map.add(this.doubleNodeArea(new EventRoom(), new EventRoom(), index++));
//     map.add(this.singleNodeArea(new EventRoom(), index++));
//     map.add(this.doubleNodeArea(new EventRoom(), new EventRoom(), index++));
//     map.add(this.doubleNodeArea(new RestRoom(), new RestRoom(), index++));
//     map.add(this.singleNodeArea(new TrueVictoryRoom(), index++, false));
//     map.add(this.populate(easyishEncounters, index++));
//     map.add(this.populate(easyishEncounters, index++));
//     map.add(this.singleNodeArea(new EventRoom(), index++));
//     map.add(this.doubleNodeArea(new TreasureRoom(), new ShopRoom(), index++));
//     map.add(this.singleNodeArea(new RestRoom(), index++));
////     map.add(this.populate(toughEncounters, index++));
//     map.add(this.singleNodeArea(new RestRoom(), index++));
//     map.add(this.doubleNodeArea(new TreasureRoom(), new ShopRoom(), index++));
////     map.add(this.singleNodeArea(new DejaVuRoom(), index++));
//     map.add(this.singleNodeArea(new MonsterRoomBoss(), index++));
//     map.add(this.singleNodeArea(new TrueVictoryRoom(), index++, false));
    logger.info("Generated the following dungeon map:");
    logger.info(MapGenerator.toString(map, Boolean.valueOf(true)));
    logger.info("Game Seed: " + Settings.seed);

    firstRoomChosen = false;
    fadeIn();
  }

    private ArrayList<MapRoomNode> populate(ArrayList<MonsterRoomCreator> possibilities, int index) {
        ArrayList<MapRoomNode> result = new ArrayList();

        int j;
        for(int i = 0; i < 7; ++i) {
            MapRoomNode mrn = new MapRoomNode(i, index);
            if (i % 2 == 1) {
                j = AbstractDungeon.mapRng.random(possibilities.size() - 1);
                mrn.room = ((MonsterRoomCreator)possibilities.get(j)).get();
                possibilities.remove(j);
            }

            result.add(mrn);
        }

        if (index > 0) {
            ArrayList<MapRoomNode> mapcontent = (ArrayList)map.get(index - 1);

            for(int i = 0; i < mapcontent.size(); ++i) {
                if (((MapRoomNode)mapcontent.get(i)).room != null) {
                    for(j = 0; j < result.size(); ++j) {
                        if (((MapRoomNode)result.get(j)).room != null && Math.abs(i - j) < 4) {
                            this.connectNode((MapRoomNode)mapcontent.get(i), (MapRoomNode)result.get(j));
                        }
                    }
                }
            }
        }

        return result;
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
      cardUpgradedChance = 0.125F;
    } else {
      cardUpgradedChance = 0.25F;
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
    bossList.add("Beast");
    bossList.add("Beast");
    bossList.add("Beast");
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

    private ArrayList<MapRoomNode> doubleNodeArea(AbstractRoom roomOne, AbstractRoom roomTwo, int index) {
        ArrayList<MapRoomNode> result = new ArrayList();
        result.add(new MapRoomNode(0, index));
        result.add(new MapRoomNode(1, index));
        MapRoomNode mrn = new MapRoomNode(2, index);
        mrn.room = roomOne;
        result.add(mrn);
        result.add(new MapRoomNode(3, index));
        mrn = new MapRoomNode(4, index);
        mrn.room = roomTwo;
        result.add(mrn);
        result.add(new MapRoomNode(5, index));
        result.add(new MapRoomNode(6, index));
        this.linkNonMonsterAreas(result);
        return result;
    }

    private void linkNonMonsterAreas(ArrayList<MapRoomNode> result) {
        if (!map.isEmpty()) {
            ArrayList<MapRoomNode> mapcontent = (ArrayList)map.get(map.size() - 1);

            for(int i = 0; i < mapcontent.size(); ++i) {
                if (((MapRoomNode)mapcontent.get(i)).room != null) {
                    for(int j = 0; j < result.size(); ++j) {
                        if (((MapRoomNode)result.get(j)).room != null) {
                            this.connectNode((MapRoomNode)mapcontent.get(i), (MapRoomNode)result.get(j));
                        }
                    }
                }
            }
        }

    }

    private ArrayList<MapRoomNode> singleNodeArea(AbstractRoom room, int index) {
        return this.singleNodeArea(room, index, true);
    }

    private ArrayList<MapRoomNode> singleNodeArea(AbstractRoom room, int index, boolean connected) {
        ArrayList<MapRoomNode> result = new ArrayList();
        result.add(new MapRoomNode(0, index));
        result.add(new MapRoomNode(1, index));
        result.add(new MapRoomNode(2, index));
        MapRoomNode mrn = new MapRoomNode(3, index);
        mrn.room = room;
        result.add(mrn);
        result.add(new MapRoomNode(4, index));
        result.add(new MapRoomNode(5, index));
        result.add(new MapRoomNode(6, index));
        if (connected) {
            this.linkNonMonsterAreas(result);
        }

        return result;
    }

    class MonsterRoomCreator {
        String image;
        String outline;
        String encounterID;

        public MonsterRoomCreator(String image, String outline, String encounterID) {
            this.image = image;
            this.outline = outline;
            this.encounterID = encounterID;
        }

        public MonsterRoom get() {
            return new FixedMonsterRoom(this.encounterID, this.image, this.outline);
        }
    }
}


/* Location:              C:\Users\Administrator\Deskto\\uncommon\Paranoia.jar!\TNN\Paranoia\dungeon\Narration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
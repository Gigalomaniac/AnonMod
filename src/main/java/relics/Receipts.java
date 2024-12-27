package relics;

//import actions.ClearRelicAction;
import actions.GainRelicAction;
import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomSavable;
import basemod.devcommands.relic.Relic;
import bossRoom.effect.LatterEffect;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import utils.ClickableRelic;
import utils.MemorySave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue.loadSaveString;


public class Receipts extends ClickableRelic implements CustomSavable<MemorySave> {


    public static final String ID = "Receipts";
    private static final String IMG = "img/relics/Receipts.png";
    private static final String IMG_OTL = "img/relics/Receipts.png";
    public static final String DESCRIPTION = "印刻着MyGO!!!!!的回忆";

    public static boolean isOn = false;
//    public static MemorySave savePlayer = new MemorySave(null,null);
    public int event = 0;
    public ArrayList<String> relics;
    public ArrayList<Integer> relic_counters;
    public ArrayList<CardSave>  cards;
    public AbstractEvent eventFinal = null;
    public int index;
    private float x;
    private float y;
    public float  memoryX = -0.5f;
    public Receipts() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
        this.x = 800.0F * Settings.xScale;
        this.y = AbstractDungeon.floorY;
    }



    public void memory() {
//        BaseMod.addSaveField("memory", this);
        AbstractPlayer p = AbstractDungeon.player;
        this.cards = p.masterDeck.getCardDeck();
        this.relics = new ArrayList();
        this.relic_counters = new ArrayList();
        Iterator var3 = p.relics.iterator();

        while(var3.hasNext()) {
            AbstractRelic r = (AbstractRelic)var3.next();
            this.relics.add(r.relicId);
            this.relic_counters.add(r.counter);
        }
        memoryX = this.currentX;
    }
    public void AwakeUp() {
        Random rand = new Random();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.masterDeck.group.size()<=3) {
//                this.addToTop(new ClearRelicAction());
//                AbstractDungeon.player.relics = new ArrayList<>();
                index = 0;
            for (AbstractRelic naire : AbstractDungeon.player.relics){
                if(!naire.relicId.equals(Receipts.ID)){
                    naire.currentX=-naire.currentX;
                    naire.hb.update(naire.currentX,naire.currentY);
                }else {
                    naire.currentX=memoryX;
                    naire.hb.update(naire.currentX,naire.currentY);
                }
            }
            for (CardSave s : cards) {
                float randomX = rand.nextInt(Settings.WIDTH/2) - (float) Settings.WIDTH /4;
                float randomY = rand.nextInt(Settings.HEIGHT/2) - (float) Settings.HEIGHT /4;
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(s.id, s.upgrades, s.misc), (float)Settings.WIDTH / 2.0F+randomX, (float)Settings.HEIGHT / 2.0F+randomY));
            }
        }
        this.memoryX = -0.5f;
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public AbstractRelic makeCopy() {
        return new Receipts();
    }

    @Override
    public void onRightClick() {
//        for (AbstractRelic naire : AbstractDungeon.player.relics){
//            if(!naire.relicId.equals(Receipts.ID)){
//                naire.currentX=-naire.currentX;
//                naire.hb.update(naire.currentX,naire.currentY);
//            }else {
//                naire.currentX=memoryX;
//                naire.hb.update(naire.currentX,naire.currentY);
//            }
//        }
//        System.out.println(relics.size());
//        AbstractPlayer p = AbstractDungeon.player;
//        this.addToTop(new ClearRelicAction());
//        index = 0;
//        for (final String s : relics) {
//            if (index < relic_counters.size()) {
//                this.addToBot(new GainRelicAction(RelicLibrary.getRelic(s).makeCopy(), relic_counters.get(index)));
//                index++;
//            }
//        }
////        if (p.masterDeck.group.isEmpty()) {
////            for (CardSave s : cards) {
////                p.masterDeck.addToTop(CardLibrary.getCopy(s.id, s.upgrades, s.misc));
////            }
//
////        }
    }

    private void setRelicStuff(AbstractRelic relic) {
        final float START_X = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_X");
        final float START_Y = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_Y");

        relic.isDone = true;
        relic.isObtained = true;
        relic.currentX = START_X + AbstractDungeon.player.relics.size() * AbstractRelic.PAD_X;
        relic.currentY = START_Y;
        relic.targetX = relic.currentX;
        relic.targetY = relic.currentY;
        relic.hb.move(relic.currentX, relic.currentY);

        AbstractDungeon.player.relics.add(relic);
    }

    @Override
    public MemorySave onSave() {
        MemorySave save = new MemorySave(relics,relic_counters,cards,memoryX);
        return save;
    }

    @Override
    public void onLoad(MemorySave memorySave) {
        relics = memorySave.relics;
        relic_counters = memorySave.relic_counters;
        cards= memorySave.cards;
        memoryX = memorySave.memoryX;
//        System.out.println("onLoadonLoadonLoadonLoadonLoad"+AbstractDungeon.player.relics.size()+"???"+memoryX);
        if(memoryX != -0.5f) {
//            System.out.println("onLoadonLoadonLoadonLoadonLoad"+AbstractDungeon.player.relics.size());
            for (AbstractRelic Rec : AbstractDungeon.player.relics) {
                if (Rec.relicId.equals(Receipts.ID)) {
                    final float START_X = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_X");
                    final float START_Y = (float) ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_Y");
                    Rec.currentX = START_X;
                    Rec.currentY = START_Y;
                    Rec.hb.update(START_X, START_Y);
                    break;
                }
            }
            int index = 0;
//            AbstractDungeon.player.relics.removeIf(relic -> !(relic instanceof Receipts));
            for (AbstractRelic naire : AbstractDungeon.player.relics) {
                if (!naire.relicId.equals(Receipts.ID)) {
                    naire.currentX = -naire.currentX;
                    naire.hb.update(naire.currentX, naire.currentY);
                }
                index++;
            }
        }
    }


}

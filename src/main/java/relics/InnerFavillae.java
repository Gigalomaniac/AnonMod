package relics;

import basemod.abstracts.CustomSavable;
import bossRoom.AnonSide;
import bossRoom.MonsterRoomMyBoss;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.shop.ShopScreen;
import utils.ClickableRelic;
import utils.SoulHeartSave;

import java.lang.reflect.Type;


public class InnerFavillae extends ClickableRelic implements CustomSavable<SoulHeartSave> {
    public static final String ID = "InnerFavillae";
    private static final String IMG = "img/relics/mygoLogo.png";
    private static final String IMG_OTL = "img/relics/mygoLogo.png";
    public static final String DESCRIPTION = "印刻着MyGO!!!!!的回忆";

    public static boolean isOn = false;

    public int event = 0;

    public AbstractEvent eventFinal = null;
    public InnerFavillae() {
        super("InnerFavillae", ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
//        setDescription();
    }
    @Override
    public SoulHeartSave onSave() {
        return null;
    }

    @Override
    public void onLoad(SoulHeartSave soulHeartSave) {

    }public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public Type savedType() {
        return CustomSavable.super.savedType();
    }

    @Override
    public JsonElement onSaveRaw() {
        return CustomSavable.super.onSaveRaw();
    }

    @Override
    public void onLoadRaw(JsonElement value) {
        CustomSavable.super.onLoadRaw(value);
    }

    private boolean enteredBloodShop = false;

    /**
     * 改变boss
     */
    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (enteredBloodShop) {
            AbstractDungeon.shopScreen = new ShopScreen();
            enteredBloodShop = false;
            isOn = false;
        }
        makeHeartToAnon();
//        makeBossToMonstro();
//        makeAllToHush();
    }

    public AbstractRelic makeCopy() {
        return new InnerFavillae();
    }

    @Override
    public void onRightClick() {

    }

    private void makeHeartToAnon() {
//        if (AbstractDungeon.floorNum < 53) {
//            return;
//        }
        String eventName = null;
            if (AbstractDungeon.nextRoom.room instanceof MonsterRoomBoss) {
                AbstractDungeon.nextRoom.room = new MonsterRoomMyBoss(new MonsterGroup(new AnonSide(-50.0F, 30.0F)));
                AbstractDungeon.lastCombatMetricKey = "Anon";
//        if (AbstractDungeon.nextRoom.room instanceof MonsterRoomBoss){
//            AbstractDungeon.nextRoom.room = new BandRoom();
//                AbstractDungeon.overlayMenu.proceedButton.hide();
//                eventFinal = EventUtils.getEvent(eventName);
//                eventFinal = new AllTheFinal();
//                eventFinal.onEnterRoom();
            }
//            else {
//                if (AbstractDungeon.nextRoom.room instanceof AbstractRoom) {
//
//                }
//            }

    }
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
}

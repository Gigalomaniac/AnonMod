package relics;

import basemod.abstracts.CustomSavable;
import bossRoom.CrychicSide;
import bossRoom.MonsterRoomMyBoss;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.shop.ShopScreen;
import events.ManaAshAnonEvent;
import rooms.ManaEventRoom;
import star.StarAnonEvent;
import star.StarAnonEventAgain;
import star.starEventRoom;
import utils.ClickableRelic;
import utils.SoulHeartSave;

import java.lang.reflect.Type;


public class EliteAshAnonKey extends ClickableRelic implements CustomSavable<Integer>  {
    public static final String ID = "EliteAshAnonKey";
    private static final String IMG = "img/relics/gituarbox.png";
    private static final String IMG_OTL = "img/relics/gituarbox.png";
    public static final String DESCRIPTION = "被打开的吉他箱子";
    MapRoomNode currNode;
    MapRoomNode node;
    public static boolean isOn = false;

    public int event = 0;

    public AbstractEvent eventFinal = null;
    public EliteAshAnonKey() {
        super("EliteAshAnonKey", ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
//        setDescription();
    }


    @Override
    public Integer onSave() {
        if(this.usedUp){
            return 1;
        }
        return 0;
    }
    //
    @Override
    public void onLoad(Integer integer) {
        if(integer ==  1){
            this.usedUp();
        }
    }


   public String getUpdatedDescription() {
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

    public void justEnteredRoom(AbstractRoom room) {
        if(!(room instanceof MonsterRoomBoss))
        if (room instanceof MonsterRoom && room.eliteTrigger == false && !usedUp) {
            this.usedUp();
            AbstractDungeon.overlayMenu.proceedButton.hide();
            this.currNode = AbstractDungeon.getCurrMapNode();
            this.currNode.setRoom(new EventRoom() {
                public void onPlayerEntry() {
                    AbstractDungeon.overlayMenu.proceedButton.hide();
                        this.event = new ManaAshAnonEvent();
                    this.event.onEnterRoom();
                }
            });
            AbstractDungeon.dungeonMapScreen.dismissable = true;
            AbstractDungeon.nextRoom = this.node;
            AbstractDungeon.setCurrMapNode(this.currNode);
            AbstractDungeon.getCurrRoom().onPlayerEntry();
            AbstractDungeon.scene.nextRoom(room);
        }
    }


    public AbstractRelic makeCopy() {
        return new EliteAshAnonKey();
    }

    @Override
    public void onRightClick() {

    }

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
}

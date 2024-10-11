package relics;

import basemod.abstracts.CustomSavable;
import bossRoom.CrychicSide;
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


public class SecondHalfKey extends ClickableRelic {
    public static final String ID = "SecondHalfKey";
    private static final String IMG = "img/relics/KeyBlack.png";
    private static final String IMG_OTL = "img/relics/KeyBlack.png";
//    public static final String DESCRIPTION = "你感觉到熟悉，又怀念。";

    public static boolean isOn = false;

    public int event = 0;

    public AbstractEvent eventFinal = null;
    public SecondHalfKey() {
        super("SecondHalfKey", ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
//        setDescription();
    }

    private boolean enteredBloodShop = false;


    public AbstractRelic makeCopy() {
        return new SecondHalfKey();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}

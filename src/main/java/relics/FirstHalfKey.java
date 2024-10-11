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


public class FirstHalfKey extends ClickableRelic{
    public static final String ID = "FirstHalfKey";
    private static final String IMG = "img/relics/KeyWhite.png";
    private static final String IMG_OTL = "img/relics/KeyWhite.png";
//    public static final String DESCRIPTION = "乐奈塞给你的一把钥匙，看上去能和另一把凑成一对。";

    public static boolean isOn = false;

    public int event = 0;

    public AbstractEvent eventFinal = null;
    public FirstHalfKey() {
        super("FirstHalfKey", ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
//        setDescription();
    }

    private boolean enteredBloodShop = false;



    public AbstractRelic makeCopy() {
        return new FirstHalfKey();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onRightClick() {

    }
}

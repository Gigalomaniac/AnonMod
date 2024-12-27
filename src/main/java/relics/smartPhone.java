package relics;
import TheTreeOfQliphoth.TheTreeOfQliphoth;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import bossRoom.effect.LatterEffect;
import cards.SpecialCard.AIHeart;
import cards.others.WhiteAnonHaruhikageSongs;
import characters.char_Anon;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;
import power.*;
import relics.chao.ThirdPerspectiveViewPatches;
import star.StarAnonSide;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import ui.SkinSelectScreen;

import java.util.ArrayList;


public class smartPhone extends CustomRelic implements CustomSavable<Integer> {

    public static final String ID = "smartPhone";
    private static final String IMG = "img/relics/SmartPhoneRelic.png";
    private static final String IMG_OTL = "img/relics/SmartPhoneRelic.png";

    public static int heavyC = 1;

    public static int allCount = 0;
    public boolean colosseum;
    public static SpireField<Color> boxColor;
    public static boolean ifHeart;
    public boolean StarAnonIsSaved  = false;
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public smartPhone() {
        super(ID, ImageMaster.loadImage(IMG),ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
        this.counter = 0;
        StarAnonIsSaved  = false;
        PowerTip tip = new PowerTip();
        tip.header="@STSLIB:FLAVOR@";
        tip.body="上课不许玩手机！！！";
        FlavorText.PowerTipFlavorFields.boxColor.set(tip, CardHelper.getColor(255, 136, 153));
        tip.img = ImageMaster.loadImage("img/test/anon.png");
        this.tips.add(tip);
    }
    public void atBattleStartPreDraw() {

        WhiteAnonHaruhikageSongs WhiteAnonHaruhikageSongs = (cards.others.WhiteAnonHaruhikageSongs) AbstractDungeon.player.masterDeck.findCardById("WhiteAnonHaruhikageSongs");
        if(WhiteAnonHaruhikageSongs != null && WhiteAnonHaruhikageSongs.hasUpgrades == 2){
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new heavy(AbstractDungeon.player, 1), 1));
        };
    }

    @Override
    public void onVictory() {
        //在胜利时触发
//        this.counter = -1;
        Shining.allCount = 0;
        musicStart.ifStart = 1;
        songs.SongsList = new String[]{"","","","","","","","","",""};
        char_Anon.beat = 0;
        char_Anon.beatState = "";
        songs.LiveSongsNum  = 2;
        Inspiration.AllCount = 0;
        BaseMod.MAX_HAND_SIZE =10;

    }
    @Override
    public void usedUp() {
        grayscale = true;
        usedUp = true;
//        counter = -99;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new smartPhone();
    }

    public void atBattleStart() {

        Shining.allCount = 0;
        musicStart.ifStart = 1;
        songs.SongsList = new String[]{"","","","","","","","","",""};
        char_Anon.beat = 0;
        char_Anon.beatState = "";
        songs.LiveSongsNum  = 2;
        Inspiration.AllCount = 0;
//        addToTop((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Inspiration(AbstractDungeon.player,1), 1));
//        addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));

        super.atBattleStart();
        musicStart.ifStart = 1;
        allCount=this.counter;
        flash();
//        addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 999), 999));
        addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new beat((AbstractCreature)AbstractDungeon.player, 0), 0));
        beat.nowBeats = "无";
        int heavyCount = 0;
        if(AbstractDungeon.player.hasRelic("Tomori_relic")){
            heavyCount = heavyCount + 3;
        }
        if(AbstractDungeon.player.hasRelic("Soyorin_relic")){
            heavyCount = heavyCount + 2;
        }
        if(AbstractDungeon.player.hasRelic("Rikki_relic")){
            heavyCount = heavyCount - 0;
        }
        if(AbstractDungeon.player.hasRelic("Rana_relic")){
            heavyCount = heavyCount - 0;
        }
        if(AbstractDungeon.player.hasRelic("Sakiko_relic")){
            heavyCount = heavyCount + 1;
        }
        if(AbstractDungeon.player.hasRelic("Mutsumi_relic")){
            heavyCount = heavyCount -1;
        }
        if(heavyCount > 0){
            addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new heavy((AbstractCreature)AbstractDungeon.player, heavyCount), 1));
            heavy.count = heavyCount;
        }else {
            heavy.count = 0;
        }

    }

    public void onEquip() {
        ifHeart = true;
        //关闭气泡
        for (com.megacrit.cardcrawl.vfx.AbstractGameEffect f : AbstractDungeon.effectList) {
            if ((f instanceof InfiniteSpeechBubble)) {
                ((InfiniteSpeechBubble) f).dismiss();
            }
        }

        AbstractCard card =  AbstractDungeon.player.masterDeck.findCardById(AIHeart.ID);
        if(card != null){
            ++this.counter;
        }

    }
    public void onUnequip() {
        if (!AbstractDungeon.id.equals(TheTreeOfQliphoth.ID))
        AbstractDungeon.effectList.add(new LatterEffect(() -> {
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new smartPhone());
            if(SkinSelectScreen.Inst.index ==1){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
            if(AbstractDungeon.player.hasRelic("Tomori_relic")){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
            if(AbstractDungeon.player.hasRelic("Soyorin_relic")){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
            if(AbstractDungeon.player.hasRelic("Rikki_relic")){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
            if(AbstractDungeon.player.hasRelic("Rana_relic")){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
            if(AbstractDungeon.player.hasRelic("Sakiko_relic")){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
            if(AbstractDungeon.player.hasRelic("Mutsumi_relic")){
                (AbstractDungeon.player.getRelic("smartPhone")).counter++;
            }
        },1));


    }
    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
//                !CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).isEmpty()));
        colosseum = false;
       options.add(new smartPhoneSearchNumbers((this.counter < 4),this));
       System.out.println(AbstractDungeon.actNum );
        System.out.println(ifHeart );
//        options.add(new AnonDeterminationCampfire((this.counter < 4),this));
       if(AbstractDungeon.actNum == 4 && !AbstractDungeon.bossList.get(0).equals(StarAnonSide.id)){
//           options.add(new AnonDeterminationCampfire((this.counter == 4),this));
//           options.add(new AnonDeterminationCampfire((this.counter < 4),this));
       }
    }
    public void atTurnStart() {
        flash();
    }

    public  void  onPlayerEndTurn(){
        if(AbstractDungeon.player.hasPower(musicStart.POWER_ID)){
            musicStart.ifStart =0;
        }else {
            musicStart.ifStart = 1;
        }
    }

    @Override
    public Integer onSave() {
        if(ifHeart = true){
            return 0;
        }
        return 1;
    }

    @Override
    public void onLoad(Integer integer) {
        System.out.println("ifHeart"+ifHeart);
        if(integer ==1){
            ifHeart = false;
        }else {
            ifHeart = true;
        }
    }
}

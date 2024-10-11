package power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//音符buff
public class songs extends AbstractPower {

    // 能力的ID
    public static final String POWER_ID = "songs";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static String SongsName  = "";

    public static String[] SongsList = new String[]{"","","","","","","","","",""};

    /**
     * live演奏几首歌
     */
    public static int LiveSongsNum = 2;

    public songs(AbstractCreature owner,String songs) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;
        if(songs != "songs"){
        for(int i = 0 ; i < 10 ; i ++){
            if (SongsList[i].equals("")){
                SongsList[i]=songs;
                break;
            }
        }
        }
        SongsName = songs;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/RarityStarPower84.png";
        String path48 = "img/newbuff/RarityStarPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+LiveSongsNum+DESCRIPTIONS[1];
        if(Settings.language == Settings.GameLanguage.ZHS){
            for(int i = 0 ; i < 10 ;  i ++){
                if(!SongsList[i].equals("")){
                    this.description += " NL "+(i+1) +"：" + SongsList[i];
                }
            }
        }else {
            for(int i = 0 ; i < 10 ;  i ++){
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("名無声"))
                    this.description += " NL "+(i+1) +"：" + "Namonaki";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("潜在表明"))
                        this.description += " NL "+(i+1) +"：" + "Senzaihyoumei";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("碧天伴走"))
                        this.description += " NL "+(i+1) +"：" + "Hekitenbansou";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("剪切线"))
                        this.description += " NL "+(i+1) +"：" + "Kiritorisen";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("壱雫空"))
                        this.description += " NL "+(i+1) +"：" + "Hitoshizuku";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("影色舞"))
                        this.description += " NL "+(i+1) +"：" + "SilhouetteDance";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("春日影"))
                        this.description += " NL "+(i+1) +"：" + "Haruhikage";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("詩超絆"))
                        this.description += " NL "+(i+1) +"：" + "Utakotoba";
                }
                if(!SongsList[i].equals("")){
                    if(SongsList[i].equals("音一会"))
                        this.description += " NL "+(i+1) +"：" + "Otoichie";
                }
            }
        }
    }

//    public int onAttacked(DamageInfo info, int damageAmount) {
//        // 非荆棘伤害，非生命流失伤害，伤害来源不为空，伤害来源不是能力持有者本身，伤害大于0
//        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
//            // 能力闪烁一下
//            this.flash();
//
//            // 添加回复action
//            this.addToTop(new HealAction(owner, owner, this.amount));
//        }
//        // 如果该能力不会修改受到伤害的数值，按原样返回即可
//        return damageAmount;
//    }
// 省略其他


    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
//        if (this.amount >= 3) {
//            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//            addToBot((AbstractGameAction)new PressEndTurnButtonAction());
//            this.amount -= 3;
//            if (this.amount <= 0)
//                addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "beat"));
//        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        flash();
//        this.description = "正在演奏"+ SongsName;
    }

    public void atStartOfTurnPostDraw() {
//
//        if(description.contains("春日影") && AbstractDungeon.player.hasPower("notLive")){
//            SongsName= "无";
//        }

        flash();
//        if (!AbstractDungeon.player.hasPower("beat") && this.amount >= 3) {
//            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//            addToBot((AbstractGameAction)new PressEndTurnButtonAction());
//        } else {
//            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new MantraPower(this.owner, this.amount), this.amount));
//        }
    }
//    addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
//    addToBot((AbstractGameAction)new PressEndTurnButtonAction());
    public void onEnergyRecharge() {
        if(musicStart.ifStart ==2){
            if (songs.SongsList.length >= songs.LiveSongsNum) {
                for (int length = songs.LiveSongsNum; length < songs.SongsList.length; length++) {
                    songs.SongsList[length - songs.LiveSongsNum] = songs.SongsList[length]; // 将当前元素移到前两个位置
                }
                for(int i = 1 ; i <= songs.LiveSongsNum ; i ++){
                    songs.SongsList[songs.SongsList.length - i] = "";

                }

//                songs.SongsList[songs.SongsList.length - 2] = "";
//                songs.SongsList[songs.SongsList.length - 1] = "";
            }else {
                SongsList = new String[]{"", "", "", "", "", "", "", "", "", ""};
            }
        }
        this.description = "之后Live的歌单(目前演奏"+LiveSongsNum+"首哦)：";
        for(int i = 0 ; i < 10 ;  i ++){
            if(!SongsList[i].equals("")){
                this.description += " NL "+(i+1) +"：" + SongsList[i];
            }
        }
        flash();
    }
    public void atEndOfTurn(boolean isPlayer) {
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.description = "之后Live的歌单(目前演奏"+LiveSongsNum+"首哦)：";
        for(int i = 0 ; i < 10 ;  i ++){
            if(!SongsList[i].equals("")){
                this.description += " NL "+(i+1) +"：" + SongsList[i];
            }
        }
    }

}


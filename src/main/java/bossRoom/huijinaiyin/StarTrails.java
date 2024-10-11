package bossRoom.huijinaiyin;

import Mod.AnonMod;
import bossRoom.CrychicSide;
import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.*;
import power.challenge.ChallengeStarTrails;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class StarTrails extends AbstractMonster {
    public static final String ID = "StarTrails";
//    private static final MonsterStrings monsterStrings;
    public static final String NAME;
//    public static final String[] MOVES;
//    public static final String[] DIALOG;
    public static final int HP_MIN = 38;
    public static final int HP_MAX = 40;
    public static final int A_2_HP_MIN = 40;
    public static final int A_2_HP_MAX = 45;
    public static final int ATTACK_DMG = 7;
    private static final byte TACKLE = 1;
    private float fireTimer = 0.0F;
    private static final float FIRE_TIME = 0.04F;
    private int moveCount = 0;
    private boolean isdone = false;
    private FrameBuffer frameBuffer;
    private SpriteBatch batch;
    private Sprite sprite;
    private long lastUpdateTimestamp; // 上一次更新的时间戳
    private final FileHandle fileHandle = Gdx.files.local("temp.png");

    private String time;

    private String timeg2;
    private int mod;
    private final  Texture[] imgAll;
    public StarTrails(float x, float y, int i) {
        super(NAME, "StarTrails", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 145.0F, 240.0F, (String)null, x, y);
        this.setMove((byte)1, Intent.ATTACK, 7);
        this.damage.add(new DamageInfo(this, 1));
//        this.loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
        this.img = new Texture(Gdx.files.internal("img/huijinaiyin/xzgj.png"));
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
//        e.setTime(e.getEndTime() * MathUtils.random());
            this.setHp(1, 1);
        // 加载原始纹理
        if(AnonMod.stopRevolve== false) {
           final Texture img01 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj001.png");
           final Texture img02 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj002.png");
           final Texture img03 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj003.png");
           final Texture img04 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj004.png");
           final Texture img05 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj005.png");
           final Texture img06 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj006.png");
           final Texture img07 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj007.png");
           final Texture img08 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj008.png");
           final Texture img09 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj009.png");
           final Texture img10 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj010.png");
           final Texture img11 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj011.png");
           final Texture img12 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj012.png");
           final Texture img13 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj013.png");
           final Texture img14 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj014.png");
           final Texture img15 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj015.png");
           final Texture img16 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj016.png");
           final Texture img17 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj017.png");
           final Texture img18 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj018.png");
           final Texture img19 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj019.png");
           final Texture img20 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj020.png");
           final Texture img21 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj021.png");
           final Texture img22 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj022.png");
           final Texture img23 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj023.png");
           final Texture img24 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj024.png");
           final Texture img25 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj025.png");
           final Texture img26 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj026.png");
           final Texture img27 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj027.png");
           final Texture img28 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj028.png");
           final Texture img29 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj029.png");
           final Texture img30 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj030.png");
           final Texture img31 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj031.png");
           final Texture img32 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj032.png");
           final Texture img33 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj033.png");
           final Texture img34 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj034.png");
           final Texture img35 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj035.png");
           final Texture img36 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj036.png");
           final Texture img37 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj037.png");
           final Texture img38 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj038.png");
           final Texture img39 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj039.png");
           final Texture img40 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj040.png");
           final Texture img41 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj041.png");
           final Texture img42 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj042.png");
           final Texture img43 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj043.png");
           final Texture img44 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj044.png");
           final Texture img45 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj045.png");
           final Texture img46 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj046.png");
           final Texture img47 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj047.png");
           final Texture img48 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj048.png");
           final Texture img49 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj049.png");
           final Texture img50 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj050.png");
           final Texture img51 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj051.png");
           final Texture img52 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj052.png");
           final Texture img53 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj053.png");
           final Texture img54 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj054.png");
           final Texture img55 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj055.png");
           final Texture img56 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj056.png");
           final Texture img57 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj057.png");
           final Texture img58 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj058.png");
           final Texture img59 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj059.png");
           final Texture img60 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj060.png");
           final Texture img61 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj061.png");
           final Texture img62 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj062.png");
           final Texture img63 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj063.png");
           final Texture img64 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj064.png");
           final Texture img65 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj065.png");
           final Texture img66 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj066.png");
           final Texture img67 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj067.png");
           final Texture img68 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj068.png");
           final Texture img69 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj069.png");
           final Texture img70 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj070.png");
           final Texture img71 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj071.png");
           final Texture img72 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj072.png");
           final Texture img73 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj073.png");
           final Texture img74 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj074.png");
           final Texture img75 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj075.png");
           final Texture img76 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj076.png");
           final Texture img77 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj077.png");
           final Texture img78 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj078.png");
           final Texture img79 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj079.png");
           final Texture img80 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj080.png");
           final Texture img81 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj081.png");
           final Texture img82 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj082.png");
           final Texture img83 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj083.png");
           final Texture img84 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj084.png");
           final Texture img85 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj085.png");
           final Texture img86 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj086.png");
           final Texture img87 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj087.png");
           final Texture img88 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj088.png");
           final Texture img89 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj089.png");
           final Texture img90 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj090.png");
           final Texture img91 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj091.png");
           final Texture img92 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj092.png");
           final Texture img93 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj093.png");
           final Texture img94 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj094.png");
           final Texture img95 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj095.png");
           final Texture img96 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj096.png");
           final Texture img97 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj097.png");
           final Texture img98 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj098.png");
           final Texture img99 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj099.png");
           final Texture img100 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj100.png");
           final Texture img101 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj101.png");
           final Texture img102 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj102.png");
           final Texture img103 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj103.png");
           final Texture img104 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj104.png");
           final Texture img105 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj105.png");
           final Texture img106 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj106.png");
           final Texture img107 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj107.png");
           final Texture img108 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj108.png");
           final Texture img109 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj109.png");
           final Texture img110 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj110.png");
           final Texture img111 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj111.png");
           final Texture img112 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj112.png");
           final Texture img113 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj113.png");
           final Texture img114 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj114.png");
           final Texture img115 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj115.png");
           final Texture img116 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj116.png");
           final Texture img117 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj117.png");
           final Texture img118 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj118.png");
           final Texture img119 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj119.png");
           final Texture img120 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj120.png");
           final Texture img121 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj121.png");
           final Texture img122 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj122.png");
           final Texture img123 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj123.png");
           final Texture img124 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj124.png");
           final Texture img125 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj125.png");
           final Texture img126 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj126.png");
           final Texture img127 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj127.png");
           final Texture img128 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj128.png");
           final Texture img129 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj129.png");
           final Texture img130 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj130.png");
           final Texture img131 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj131.png");
           final Texture img132 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj132.png");
           final Texture img133 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj133.png");
           final Texture img134 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj134.png");
           final Texture img135 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj135.png");
           final Texture img136 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj136.png");
           final Texture img137 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj137.png");
           final Texture img138 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj138.png");
           final Texture img139 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj139.png");
           final Texture img140 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj140.png");
           final Texture img141 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj141.png");
           final Texture img142 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj142.png");
           final Texture img143 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj143.png");
           final Texture img144 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj144.png");
           final Texture img145 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj145.png");
           final Texture img146 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj146.png");
           final Texture img147 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj147.png");
           final Texture img148 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj148.png");
           final Texture img149 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj149.png");
           final Texture img150 = new Texture("img/huijinaiyin/out/6s25hz/xzgj/xzgj000.png");

            imgAll = new Texture[]{
                    img01, img02, img03, img04, img05, img06, img07, img08, img09, img10,
                    img11, img12, img13, img14, img15, img16, img17, img18, img19, img20,
                    img21, img22, img23, img24, img25, img26, img27, img28, img29, img30,
                    img31, img32, img33, img34, img35, img36,
                    img37, img38, img39, img40, img41, img42, img43, img44, img45, img46,
                    img47, img48, img49, img50, img51, img52, img53, img54, img55, img56,
                    img57, img58, img59, img60, img61, img62, img63, img64, img65, img66,
                    img67, img68, img69, img70, img71, img72, img73, img74, img75, img76,
                    img77, img78, img79, img80, img81, img82, img83, img84, img85, img86,
                    img87, img88, img89, img90, img91, img92, img93, img94, img95, img96,
                    img97, img98, img99, img100, img101, img102, img103, img104, img105, img106,
                    img107, img108, img109, img110, img111, img112, img113, img114, img115, img116,
                    img117, img118, img119, img120, img121, img122, img123, img124, img125, img126,
                    img127, img128, img129, img130, img131, img132, img133, img134, img135, img136,
                    img137, img138, img139, img140, img141, img142, img143, img144, img145, img146,
                    img147, img148, img149, img150
            };
        }else {
            imgAll = null;
        }
    }

    public void takeTurn() {
        label23:
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new Shining(AbstractDungeon.player, -1), -1));
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
                while(true) {
                    if (!var1.hasNext()) {
                        break label23;
                    }
                    AbstractMonster m = (AbstractMonster)var1.next();
                    if (!m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new AveMujicaShining(m, 1), 1));
//                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(m, this, 20));
                    }
                }
        }
    }
    BufferedImage originalImage;

    int fourthLastDigit;
    int thirdLastDigit ;
    // 将这两个数字组合成一个新的整数
    int combinedDigits ;
    byte[] imageBytes;
    Texture texture ;
    BufferedImage rotatedTexture;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ByteArrayInputStream bis;
    long timestamp;
    public void update() {
        super.update();
        if(isdone == false){
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonMemory(this), 1));
            if(InnerFavillaeSide.Stage ==2 )
                addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ChallengeStarTrails(this,8), 8));
            isdone= true;
        }
        if(AnonMod.stopRevolve== false) {
            for (int i = 0; i < AshAnonSongs.SongsList.length; i++) {
                if (AshAnonSongs.SongsList[i].equals(this.name)) {
                    int index = (int) (((double) (System.currentTimeMillis() % 6000) / 6000 * 150) % 150);
                    this.img = imgAll[index];
                    break;
                }
            }
        }
//        if(InnerFavillaeSide.Stage ==3){
//         originalImage = null;
//        try {
//            InputStream is = StarTrails.class.getResourceAsStream("/img/huijinaiyin/xzgj.png");
//            originalImage = ImageIO.read(is);
////            originalImage = ImageIO.read(new File("img/huijinaiyin/Star150.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // 创建一个与原图大小相同的空白图片，用于存放旋转后的图片
//        BufferedImage rotatedImage = new BufferedImage(
//                originalImage.getWidth(),
//                originalImage.getHeight(),
//                originalImage.getType()
//        );
//
//        // 获取Graphics2D对象来绘制图片
//        Graphics2D g2d = rotatedImage.createGraphics();
//
//        // 设置绘图属性以保持透明度
//        g2d.setComposite(AlphaComposite.Src);
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // 计算旋转中心点
//        int x = originalImage.getWidth() / 2;
//        int y = originalImage.getHeight() / 2;
//        //时间
//        timestamp = System.currentTimeMillis();
//
//         fourthLastDigit = (int) ((timestamp / 1000) % 10); // 获取倒数第四位
//         thirdLastDigit = (int) ((timestamp / 100) % 10); // 获取倒数第三位
//        // 将这两个数字组合成一个新的整数
//        combinedDigits = fourthLastDigit * 10 + thirdLastDigit;
//
//        // 创建仿射变换对象并设置旋转参数
//        AffineTransform rotateAT = new AffineTransform();
//        rotateAT.translate(x, y); // 平移坐标系至中心
//        rotateAT.rotate(Math.toRadians(combinedDigits*3.6), 0, 0); // 旋转80度
//        rotateAT.translate(-x, -y); // 平移回原点
//
//        // 设置仿射变换
//        g2d.setTransform(rotateAT);
//
//        // 绘制旋转后的图片
//        g2d.drawImage(originalImage, 0, 0, null);
//
//        // 关闭Graphics2D对象
//        g2d.dispose();
//
//        // 将旋转后的图片保存到新变量
//        rotatedTexture = rotatedImage;
//         bos = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(rotatedTexture, "png", bos);
//            imageBytes = bos.toByteArray();
//            bis = new ByteArrayInputStream(imageBytes);
//            fileHandle.writeBytes(imageBytes, false);
//            texture = new Texture(fileHandle);
//            this.img=texture;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    protected void getMove(int num) {
//        this.setMove("无惧爱",(byte)1, Intent.DEBUFF, 50);
//        ++this.moveCount;
    }

    static {
        //        sprite.setRotation(sprite.getRotation() + rotationAngle);
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead");
        NAME = "星之轨迹";
//        MOVES = monsterStrings.MOVES;
//        DIALOG = monsterStrings.DIALOG;
    }
    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new AveMujicaBelief(this)));
    }
    @Override
    protected Texture getAttackIntent() {
        return super.getAttackIntent();
    }
    @Override
    public void createIntent() {

    }
    /**
     * 隐藏生命
     */
    public void showHealthBar() {
        if(InnerFavillaeSide.Stage == 3){
            this.hideHealthBar();
        }
    }

    public void die() {
        super.die();
        CrychicSide.Amoris=false;

    }
}

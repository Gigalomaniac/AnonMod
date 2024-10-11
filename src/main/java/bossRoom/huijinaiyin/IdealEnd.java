package bossRoom.huijinaiyin;

import Mod.AnonMod;
import bossRoom.CrychicSide;
import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.*;
import power.challenge.ChallengeIdealEnd;
import power.challenge.ChallengeStarTrails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class IdealEnd extends AbstractMonster {
    public static final String ID = "IdealEnd";
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
    private int width;

    private int height;
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
    private final Texture img01 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt001.png");
    private final Texture img02 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt002.png");
    private final Texture img03 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt003.png");
    private final Texture img04 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt004.png");
    private final Texture img05 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt005.png");
    private final Texture img06 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt006.png");
    private final Texture img07 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt007.png");
    private final Texture img08 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt008.png");
    private final Texture img09 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt009.png");
    private final Texture img10 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt010.png");
    private final Texture img11 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt011.png");
    private final Texture img12 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt012.png");
    private final Texture img13 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt013.png");
    private final Texture img14 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt014.png");
    private final Texture img15 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt015.png");
    private final Texture img16 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt016.png");
    private final Texture img17 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt017.png");
    private final Texture img18 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt018.png");
    private final Texture img19 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt019.png");
    private final Texture img20 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt020.png");
    private final Texture img21 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt021.png");
    private final Texture img22 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt022.png");
    private final Texture img23 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt023.png");
    private final Texture img24 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt024.png");
    private final Texture img25 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt025.png");
    private final Texture img26 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt026.png");
    private final Texture img27 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt027.png");
    private final Texture img28 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt028.png");
    private final Texture img29 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt029.png");
    private final Texture img30 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt030.png");
    private final Texture img31 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt031.png");
    private final Texture img32 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt032.png");
    private final Texture img33 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt033.png");
    private final Texture img34 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt034.png");
    private final Texture img35 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt035.png");
    private final Texture img36 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt036.png");
    private final Texture img37 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt037.png");
    private final Texture img38 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt038.png");
    private final Texture img39 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt039.png");
    private final Texture img40 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt040.png");
    private final Texture img41 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt041.png");
    private final Texture img42 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt042.png");
    private final Texture img43 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt043.png");
    private final Texture img44 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt044.png");
    private final Texture img45 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt045.png");
    private final Texture img46 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt046.png");
    private final Texture img47 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt047.png");
    private final Texture img48 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt048.png");
    private final Texture img49 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt049.png");
    private final Texture img50 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt050.png");
    private final Texture img51 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt051.png");
    private final Texture img52 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt052.png");
    private final Texture img53 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt053.png");
    private final Texture img54 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt054.png");
    private final Texture img55 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt055.png");
    private final Texture img56 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt056.png");
    private final Texture img57 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt057.png");
    private final Texture img58 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt058.png");
    private final Texture img59 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt059.png");
    private final Texture img60 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt060.png");
    private final Texture img61 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt061.png");
    private final Texture img62 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt062.png");
    private final Texture img63 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt063.png");
    private final Texture img64 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt064.png");
    private final Texture img65 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt065.png");
    private final Texture img66 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt066.png");
    private final Texture img67 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt067.png");
    private final Texture img68 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt068.png");
    private final Texture img69 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt069.png");
    private final Texture img70 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt070.png");
    private final Texture img71 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt071.png");
    private final Texture img72 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt072.png");
    private final Texture img73 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt073.png");
    private final Texture img74 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt074.png");
    private final Texture img75 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt075.png");
    private final Texture img76 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt076.png");
    private final Texture img77 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt077.png");
    private final Texture img78 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt078.png");
    private final Texture img79 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt079.png");
    private final Texture img80 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt080.png");
    private final Texture img81 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt081.png");
    private final Texture img82 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt082.png");
    private final Texture img83 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt083.png");
    private final Texture img84 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt084.png");
    private final Texture img85 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt085.png");
    private final Texture img86 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt086.png");
    private final Texture img87 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt087.png");
    private final Texture img88 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt088.png");
    private final Texture img89 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt089.png");
    private final Texture img90 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt090.png");
    private final Texture img91 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt091.png");
    private final Texture img92 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt092.png");
    private final Texture img93 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt093.png");
    private final Texture img94 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt094.png");
    private final Texture img95 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt095.png");
    private final Texture img96 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt096.png");
    private final Texture img97 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt097.png");
    private final Texture img98 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt098.png");
    private final Texture img99 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt099.png");
    private final Texture img100 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt100.png");
    private final Texture img101 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt101.png");
    private final Texture img102 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt102.png");
    private final Texture img103 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt103.png");
    private final Texture img104 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt104.png");
    private final Texture img105 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt105.png");
    private final Texture img106 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt106.png");
    private final Texture img107 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt107.png");
    private final Texture img108 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt108.png");
    private final Texture img109 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt109.png");
    private final Texture img110 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt110.png");
    private final Texture img111 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt111.png");
    private final Texture img112 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt112.png");
    private final Texture img113 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt113.png");
    private final Texture img114 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt114.png");
    private final Texture img115 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt115.png");
    private final Texture img116 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt116.png");
    private final Texture img117 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt117.png");
    private final Texture img118 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt118.png");
    private final Texture img119 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt119.png");
    private final Texture img120 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt120.png");
    private final Texture img121 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt121.png");
    private final Texture img122 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt122.png");
    private final Texture img123 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt123.png");
    private final Texture img124 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt124.png");
    private final Texture img125 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt125.png");
    private final Texture img126 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt126.png");
    private final Texture img127 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt127.png");
    private final Texture img128 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt128.png");
    private final Texture img129 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt129.png");
    private final Texture img130 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt130.png");
    private final Texture img131 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt131.png");
    private final Texture img132 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt132.png");
    private final Texture img133 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt133.png");
    private final Texture img134 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt134.png");
    private final Texture img135 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt135.png");
    private final Texture img136 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt136.png");
    private final Texture img137 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt137.png");
    private final Texture img138 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt138.png");
    private final Texture img139 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt139.png");
    private final Texture img140 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt140.png");
    private final Texture img141 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt141.png");
    private final Texture img142 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt142.png");
    private final Texture img143 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt143.png");
    private final Texture img144 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt144.png");
    private final Texture img145 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt145.png");
    private final Texture img146 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt146.png");
    private final Texture img147 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt147.png");
    private final Texture img148 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt148.png");
    private final Texture img149 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt149.png");
    private final Texture img150 = new Texture("img/huijinaiyin/out/6s25hz/lxjt/lxjt000.png");

    private final  Texture[] imgAll = {
            img01, img02, img03, img04, img05, img06, img07, img08, img09, img10,
            img11, img12, img13, img14, img15, img16, img17, img18, img19, img20,
            img21, img22, img23, img24, img25, img26, img27, img28, img29, img30,
            img31, img32, img33, img34, img35, img36,
            // 继续添加直到 img150
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
    private final FileHandle fileHandle = Gdx.files.local("temp.png");
    public IdealEnd(float x, float y, int i) {
        super(NAME, "IdealEnd", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 145.0F, 240.0F, (String)null, x, y);
        this.img = new Texture(Gdx.files.internal("img/huijinaiyin/Ideal150.png"));
        this.setMove((byte)1, Intent.ATTACK, 7);
        this.damage.add(new DamageInfo(this, 1));
        width = this.img.getWidth();  //图片原本的宽度
        height = this.img.getHeight();  //图片原本的高度
        TextureRegion region = new TextureRegion(this.img,0,0,width,height);

        this.setHp(1, 1);
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
    /**
     * 隐藏生命
     */
    public void showHealthBar() {
        if(InnerFavillaeSide.Stage == 3){
            this.hideHealthBar();
        }
    }
    public void update() {
        super.update();
        if(isdone == false){
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonMemory(this), 1));
            if(InnerFavillaeSide.Stage==2)
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ChallengeIdealEnd(this,8), 8));
            isdone= true;
        }
        if(AnonMod.stopRevolve== false){
            for (int i = 0; i < AshAnonSongs.SongsList.length; i++) {
                if (AshAnonSongs.SongsList[i].equals(this.name)) {
                    int index = (int) (((double) (System.currentTimeMillis() % 6000) / 6000 * 150) % 150);
                    this.img = imgAll[index];
                    break;
                }
            }
        }
//        if(InnerFavillaeSide.Stage ==3){
//            originalImage = null;
//            try {
//                InputStream is = StarTrails.class.getResourceAsStream("/img/huijinaiyin/Ideal150.png");
//                originalImage = ImageIO.read(is);
////            originalImage = ImageIO.read(new File("img/huijinaiyin/Star150.png"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            // 创建一个与原图大小相同的空白图片，用于存放旋转后的图片
//            BufferedImage rotatedImage = new BufferedImage(
//                    originalImage.getWidth(),
//                    originalImage.getHeight(),
//                    originalImage.getType()
//            );
//
//            // 获取Graphics2D对象来绘制图片
//            Graphics2D g2d = rotatedImage.createGraphics();
//
//            // 设置绘图属性以保持透明度
//            g2d.setComposite(AlphaComposite.Src);
//            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            // 计算旋转中心点
//            int x = originalImage.getWidth() / 2;
//            int y = originalImage.getHeight() / 2;
//            //时间
//            timestamp = System.currentTimeMillis();
//
//            fourthLastDigit = (int) ((timestamp / 1000) % 10); // 获取倒数第四位
//            thirdLastDigit = (int) ((timestamp / 100) % 10); // 获取倒数第三位
//            // 将这两个数字组合成一个新的整数
//            combinedDigits = fourthLastDigit * 10 + thirdLastDigit;
//
//            // 创建仿射变换对象并设置旋转参数
//            AffineTransform rotateAT = new AffineTransform();
//            rotateAT.translate(x, y); // 平移坐标系至中心
//            rotateAT.rotate(Math.toRadians(combinedDigits*3.6), 0, 0); // 旋转80度
//            rotateAT.translate(-x, -y); // 平移回原点
//
//            // 设置仿射变换
//            g2d.setTransform(rotateAT);
//
//            // 绘制旋转后的图片
//            g2d.drawImage(originalImage, 0, 0, null);
//
//            // 关闭Graphics2D对象
//            g2d.dispose();
//
//            // 将旋转后的图片保存到新变量
//            rotatedTexture = rotatedImage;
//            bos = new ByteArrayOutputStream();
//            try {
//                ImageIO.write(rotatedTexture, "png", bos);
//                imageBytes = bos.toByteArray();
//                bis = new ByteArrayInputStream(imageBytes);
//                fileHandle.writeBytes(imageBytes, false);
//                texture = new Texture(fileHandle);
//                this.img=texture;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
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
    public void die() {
        super.die();
        CrychicSide.Amoris=false;

    }
    protected void getMove(int num) {
//        this.setMove("无惧爱",(byte)1, Intent.DEBUFF, 50);
//        ++this.moveCount;
    }

    static {
//        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead");
        NAME = "理想的尽头";
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


}

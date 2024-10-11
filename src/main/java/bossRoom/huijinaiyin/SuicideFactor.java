package bossRoom.huijinaiyin;

import Mod.AnonMod;
import bossRoom.CrychicSide;
import bossRoom.InnerFavillaeSide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.*;
import power.challenge.ChallengeStarTrails;
import power.challenge.ChallengeSuicideFactor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class SuicideFactor extends AbstractMonster {
    public static final String ID = "SuicideFactor";
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
    private final FileHandle fileHandle = Gdx.files.local("temp.png");
    long timestamp;
    private Texture[] imgAll;
    public SuicideFactor(float x, float y, int i) {
        super(NAME, "SuicideFactor", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 145.0F, 240.0F, (String)null, x, y);
        this.setMove((byte)1, Intent.ATTACK, 7);
        this.damage.add(new DamageInfo(this, 1));
//        this.loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
        this.img = new Texture(Gdx.files.internal("img/huijinaiyin/SuicideFactor150.png"));
        if(AnonMod.stopRevolve== false) {
            final Texture img01 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz001.png");
            final Texture img02 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz002.png");
            final Texture img03 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz003.png");
            final Texture img04 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz004.png");
            final Texture img05 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz005.png");
            final Texture img06 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz006.png");
            final Texture img07 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz007.png");
            final Texture img08 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz008.png");
            final Texture img09 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz009.png");
            final Texture img10 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz010.png");
            final Texture img11 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz011.png");
            final Texture img12 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz012.png");
            final Texture img13 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz013.png");
            final Texture img14 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz014.png");
            final Texture img15 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz015.png");
            final Texture img16 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz016.png");
            final Texture img17 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz017.png");
            final Texture img18 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz018.png");
            final Texture img19 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz019.png");
            final Texture img20 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz020.png");
            final Texture img21 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz021.png");
            final Texture img22 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz022.png");
            final Texture img23 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz023.png");
            final Texture img24 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz024.png");
            final Texture img25 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz025.png");
            final Texture img26 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz026.png");
            final Texture img27 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz027.png");
            final Texture img28 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz028.png");
            final Texture img29 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz029.png");
            final Texture img30 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz030.png");
            final Texture img31 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz031.png");
            final Texture img32 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz032.png");
            final Texture img33 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz033.png");
            final Texture img34 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz034.png");
            final Texture img35 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz035.png");
            final Texture img36 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz036.png");
            final Texture img37 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz037.png");
            final Texture img38 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz038.png");
            final Texture img39 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz039.png");
            final Texture img40 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz040.png");
            final Texture img41 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz041.png");
            final Texture img42 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz042.png");
            final Texture img43 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz043.png");
            final Texture img44 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz044.png");
            final Texture img45 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz045.png");
            final Texture img46 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz046.png");
            final Texture img47 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz047.png");
            final Texture img48 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz048.png");
            final Texture img49 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz049.png");
            final Texture img50 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz050.png");
            final Texture img51 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz051.png");
            final Texture img52 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz052.png");
            final Texture img53 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz053.png");
            final Texture img54 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz054.png");
            final Texture img55 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz055.png");
            final Texture img56 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz056.png");
            final Texture img57 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz057.png");
            final Texture img58 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz058.png");
            final Texture img59 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz059.png");
            final Texture img60 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz060.png");
            final Texture img61 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz061.png");
            final Texture img62 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz062.png");
            final Texture img63 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz063.png");
            final Texture img64 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz064.png");
            final Texture img65 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz065.png");
            final Texture img66 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz066.png");
            final Texture img67 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz067.png");
            final Texture img68 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz068.png");
            final Texture img69 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz069.png");
            final Texture img70 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz070.png");
            final Texture img71 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz071.png");
            final Texture img72 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz072.png");
            final Texture img73 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz073.png");
            final Texture img74 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz074.png");
            final Texture img75 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz075.png");
            final Texture img76 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz076.png");
            final Texture img77 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz077.png");
            final Texture img78 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz078.png");
            final Texture img79 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz079.png");
            final Texture img80 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz080.png");
            final Texture img81 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz081.png");
            final Texture img82 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz082.png");
            final Texture img83 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz083.png");
            final Texture img84 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz084.png");
            final Texture img85 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz085.png");
            final Texture img86 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz086.png");
            final Texture img87 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz087.png");
            final Texture img88 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz088.png");
            final Texture img89 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz089.png");
            final Texture img90 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz090.png");
            final Texture img91 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz091.png");
            final Texture img92 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz092.png");
            final Texture img93 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz093.png");
            final Texture img94 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz094.png");
            final Texture img95 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz095.png");
            final Texture img96 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz096.png");
            final Texture img97 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz097.png");
            final Texture img98 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz098.png");
            final Texture img99 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz099.png");
            final Texture img100 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz100.png");
            final Texture img101 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz101.png");
            final Texture img102 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz102.png");
            final Texture img103 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz103.png");
            final Texture img104 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz104.png");
            final Texture img105 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz105.png");
            final Texture img106 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz106.png");
            final Texture img107 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz107.png");
            final Texture img108 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz108.png");
            final Texture img109 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz109.png");
            final Texture img110 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz110.png");
            final Texture img111 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz111.png");
            final Texture img112 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz112.png");
            final Texture img113 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz113.png");
            final Texture img114 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz114.png");
            final Texture img115 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz115.png");
            final Texture img116 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz116.png");
            final Texture img117 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz117.png");
            final Texture img118 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz118.png");
            final Texture img119 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz119.png");
            final Texture img120 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz120.png");
            final Texture img121 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz121.png");
            final Texture img122 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz122.png");
            final Texture img123 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz123.png");
            final Texture img124 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz124.png");
            final Texture img125 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz125.png");
            final Texture img126 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz126.png");
            final Texture img127 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz127.png");
            final Texture img128 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz128.png");
            final Texture img129 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz129.png");
            final Texture img130 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz130.png");
            final Texture img131 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz131.png");
            final Texture img132 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz132.png");
            final Texture img133 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz133.png");
            final Texture img134 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz134.png");
            final Texture img135 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz135.png");
            final Texture img136 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz136.png");
            final Texture img137 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz137.png");
            final Texture img138 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz138.png");
            final Texture img139 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz139.png");
            final Texture img140 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz140.png");
            final Texture img141 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz141.png");
            final Texture img142 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz142.png");
            final Texture img143 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz143.png");
            final Texture img144 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz144.png");
            final Texture img145 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz145.png");
            final Texture img146 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz146.png");
            final Texture img147 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz147.png");
            final Texture img148 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz148.png");
            final Texture img149 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz149.png");
            final Texture img150 = new Texture("img/huijinaiyin/out/6s25hz/zmyz/zmyz000.png");

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

    public void update() {
        super.update();
        if(isdone == false){
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new AshAnonMemory(this), 1));
            if(InnerFavillaeSide.Stage==2)
            addToBot((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new ChallengeSuicideFactor(this,8), 8));
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
//
//            try {
//                InputStream is = StarTrails.class.getResourceAsStream("/img/huijinaiyin/SuicideFactor150.png");
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
        NAME = "自灭因子";
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
}

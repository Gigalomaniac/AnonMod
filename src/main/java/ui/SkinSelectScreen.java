package ui;

import Mod.AnonMod;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.ISubscriber;
import characters.char_Anon;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import pathes.ThmodClassEnum;

import java.util.ArrayList;

public class SkinSelectScreen implements ISubscriber, CustomSavable<Integer> {
    public static String[] BGindex ;
    public static String getSkinSelectScreenBG;
    private static String[] TEXT;

    private static String[] Special;
    private static final ArrayList<Skin> SKINS = new ArrayList();
    public static SkinSelectScreen Inst;
    public Hitbox leftHb;
    public Hitbox rightHb;
    public TextureAtlas atlas;
    public Skeleton skeleton;
    public AnimationStateData stateData;
    public AnimationState state;
    public String curName = "";
    public String nextName = "";

    public String SpecialName = "";

    public static int index;
    public static boolean shouldUpdateBackground = false;
    public static Skin getSkin() {

        return (Skin)SKINS.get(Inst.index);

    }

    public SkinSelectScreen() {
        this.refresh();
        this.leftHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
        this.rightHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
        BaseMod.subscribe(this);
        BaseMod.addSaveField(AnonMod.MakePath("skin"), this);
    }
    public Texture usedImg;

    public static void reboot() {
        Inst.index = 0;
        index = 0;
    }

    public void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
//        this.usedImg = ImageMaster.loadImage("img/huijinaiyin/huijinaiyin260_2.png");
//        this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
//        SkeletonJson json = new SkeletonJson(this.atlas);
//        json.setScale(Settings.renderScale / scale);
//        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
//        this.skeleton = new Skeleton(skeletonData);
//        this.skeleton.setColor(Color.WHITE);
//        this.stateData = new AnimationStateData(skeletonData);
//        this.state = new AnimationState(this.stateData);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
//        e.setTimeScale(1.2F);
    }

    public void refresh() {
        Skin skin = (Skin)SKINS.get(this.index);
        this.usedImg = ImageMaster.loadImage(skin.charPath);
       char_Anon.SELES_STAND = skin.charPath;
        this.curName = skin.name;
        this.SpecialName = skin.special;
        this.nextName = ((Skin)SKINS.get(this.nextIndex())).name;
        shouldUpdateBackground = true;
        if( BGindex[this.index] !=null)
            getSkinSelectScreenBG = BGindex[this.index];
        else {
            getSkinSelectScreenBG = "img/charSelect/animeanon.png";
        }
        if (AbstractDungeon.player instanceof char_Anon) {
            char_Anon k = (char_Anon)AbstractDungeon.player;
            k.refreshSkin();
    }
    }

    public int prevIndex() {
        return this.index - 1 < 0 ? SKINS.size() - 1 : this.index - 1;
    }

    public int nextIndex() {
        return this.index + 1 > SKINS.size() - 1 ? 0 : this.index + 1;
    }

    public void update() {

        float centerX = (float)Settings.WIDTH * 0.8F;
        float centerY = (float)Settings.HEIGHT * 0.5F;
        this.leftHb.move(centerX - 200.0F * Settings.scale, centerY);
        this.rightHb.move(centerX + 200.0F * Settings.scale, centerY);
        this.updateInput();
    }

    private void updateInput() {
        if (CardCrawlGame.chosenCharacter == ThmodClassEnum.Anon_COLOR) {
            this.leftHb.update();
            this.rightHb.update();
            if (this.leftHb.clicked) {
                this.leftHb.clicked = false;
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.index = this.prevIndex();
                this.refresh();
            }

            if (this.rightHb.clicked) {
                this.rightHb.clicked = false;
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.index = this.nextIndex();
                this.refresh();
            }

            if (InputHelper.justClickedLeft) {
                if (this.leftHb.hovered) {
                    this.leftHb.clickStarted = true;
                }

                if (this.rightHb.hovered) {
                    this.rightHb.clickStarted = true;
                }
            }
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        Color RC = new Color(0.0F, 204.0F, 255.0F, 1.0F);
        float centerX = (float) Settings.WIDTH * 0.8F;
        float centerY = (float)Settings.HEIGHT * 0.5F;
        float usedx = (float) this.usedImg.getWidth() /2;
        float usedy = (float) this.usedImg.getWidth() /2;
        this.renderSkin(sb, centerX-usedx, centerY-usedy);
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[0], centerX, centerY + 250.0F * Settings.scale, Color.WHITE, 1.25F);
        Color color = Settings.GOLD_COLOR.cpy();
        color.a /= 2.0F;
        float dist = 100.0F * Settings.scale;
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, this.curName, centerX, centerY-200, RC);
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, this.SpecialName, centerX, centerY-250, RC);
        if (this.leftHb.hovered) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }

        sb.draw(ImageMaster.CF_LEFT_ARROW, this.leftHb.cX - 24.0F, this.leftHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
        if (this.rightHb.hovered) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }

        sb.draw(ImageMaster.CF_RIGHT_ARROW, this.rightHb.cX - 24.0F, this.rightHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
        this.rightHb.render(sb);
        this.leftHb.render(sb);
    }

    public void renderSkin(SpriteBatch sb, float x, float y) {
        if(this.usedImg != null){
            sb.draw(this.usedImg,x,y);
        }
    }



    //    public void onLoad(Integer arg0) {
//        this.index = arg0;
//        this.refresh();
//        Skin skin = (Skin)SKINS.get(this.index);
//        char_Anon.SELES_STAND = skin.charPath;
//    }
    public void onLoad(Integer arg0) {
     this.index = arg0.intValue();
     refresh();
   }
    public Integer onSave() {
        return this.index;
    }

    static {
        String[] special1 = new String[0];
        String[] text1 = new String[0];
        BGindex = new String[0];
//        TEXT = CardCrawlGame.languagePack.getUIString(AnonMod.MakePath(SkinSelectScreen.class.getSimpleName())).TEXT;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            text1 = new String[]{"选择你的爱音",
                    "爱音","灰爱音（少女们的逆行）","爱音（白神话）","星千早（星遗物）","劈瓦爱音","爱音(FES)","爱音(赠送)","爱音(爱灯素)","致幻爱音","肥驹爱音","黑暗爱音",
                    "爱猫爱音", "不是爱音，是爽世", "打灰爱音，鉴定为小工","打灰爱音，鉴定为技术","打灰爱音，鉴定为领导","闪光爱音","欠草爱音","圣园未花"};
            special1 = new String[]{"",
                    "","初始携带“机械心”,你的乐队为“她”留着位置……","初始携带“吉他-浅草&微露”,更换专属初始卡组","初始携带摩托，独奏演唱会，无法触及的未来","","","","","","","","",
                    "", "初始携带“鼠老师给的桶”和打灰套装","","初始携带“鼠老师给的桶”和“领导叫你背锅！”","初始额外携带“KiraKiraDokiDoki”","","测试中！"};
        }else {
            text1 = new String[]{"选择你的爱音",
                    "Anon","AshAnon","Tile destroyer Anon","White Anon","星千早（星遗物）","Anon (FES)","Anon (Gift)","Anon (Aideng Su)","Hallucination Anon","FeiJuAnon","DarkAnon","CatLoverAnon",
                    "Not Anon, is Soyorin", "Dusting Anon, Identified as Laborer","Dusting Anon, Identified as Technician","Dusting Anon, Identified as Leader","KiraKira Anon","AnonLackFuck","mika"};
            special1 = new String[]{"",
                    "","Replaces 'Basic Music'  with 'kikaikokoro:-AI Heart-”","White","初始携带摩托，无法触及的未来","","","","","","","","",
                    "", "Comes with 'The Barrel from the Da Laoshu'","","","Initially Carrying 'KiraKiraDokiDoki'","","testing"};
        }
        BGindex = new String[]{
                null,"img/test/BG/AshAnonBG.png","img/test/BG/WhiteAnon.png","img/test/BG/StarBG.png",null,null,null,null,null,
                null, null, null,null,null,null,null,null,null,null};

        Special = special1;
        TEXT = text1;
        SKINS.add(new Skin(0, "Anon"));
        SKINS.add(new Skin(1, "AshAnon"));
        SKINS.add(new Skin(2, "white"));
        SKINS.add(new Skin(3, "star"));
        SKINS.add(new Skin(4, "caicai"));
        SKINS.add(new Skin(5, "AnonFes"));
        SKINS.add(new Skin(6, "AnonGive"));
        SKINS.add(new Skin(7, "AnonSix"));
        SKINS.add(new Skin(8, "shiro"));
        SKINS.add(new Skin(9, "feimali"));
        SKINS.add(new Skin(10, "PAREO"));
        SKINS.add(new Skin(11, "yukina"));
        SKINS.add(new Skin(12, "soyo"));
        SKINS.add(new Skin(13, "smallworker"));
        SKINS.add(new Skin(14, "tech"));
        SKINS.add(new Skin(15, "leader"));
        SKINS.add(new Skin(16, "KSM"));
        SKINS.add(new Skin(17, "yuzu"));
        SKINS.add(new Skin(18, "mika"));
        Inst = new SkinSelectScreen();
    }


    public static class Skin {
        public String charPath;
        public String shoulder;
        public String name;
        public String special;
        public Skin(int index, String charPath) {
            if(charPath.equals("Anon")){
                this.charPath = "img/char/anon.png";
            }
            if(charPath.equals("AshAnon")){
                this.charPath = "img/test/newAshAnon.png";
            }
            if(charPath.equals("caicai")){
                this.charPath = "img/test/caicai.png";
            }
            if(charPath.equals("AnonFes")){
                this.charPath = "img/test/AnonFes.png";
            }
            if(charPath.equals("AnonGive")){
                this.charPath = "img/test/AnonGive.png";
            }
            if(charPath.equals("AnonSix")){
                this.charPath = "img/test/AnonSix.png";
            }
            if(charPath.equals("shiro")){
                this.charPath = "img/test/shiro.png";
            }
            if(charPath.equals("feimali")){
                this.charPath = "img/test/feimali.png";
            }
            if(charPath.equals("PAREO")){
                this.charPath = "img/test/PAREO.png";
            }
            if(charPath.equals("yukina")){
                this.charPath = "img/test/yukina.png";
            }
            if(charPath.equals("soyo")){
                this.charPath = "img/test/soyo.png";
            }
            if(charPath.equals("smallworker")){
                this.charPath = "img/test/smallworker.png";
            }
            if(charPath.equals("tech")){
                this.charPath = "img/test/tech.png";
            }
            if(charPath.equals("leader")){
                this.charPath = "img/test/leader.png";
            }
            if(charPath.equals("white")){
                this.charPath = "img/test/Anon white.png";
            }
            if(charPath.equals("KSM")){
                this.charPath = "img/test/KSM.png";
            }
            if(charPath.equals("yuzu")){
                this.charPath = "img/test/rabbit.png";
            }
            if(charPath.equals("star")){
                this.charPath = "img/test/AnonStar.png";
            }
            if(charPath.equals("mika")){
                this.charPath = "img/test/mikaIMG.png";
            }
            this.name = SkinSelectScreen.TEXT[index + 1];
            this.special = SkinSelectScreen.Special[index + 1];
        }
    }

    private static final String[] Anon = {"img/char/anon.png",
                                      "img/huijinaiyin/huijinaiyin260_2.png",
            "img/char/anon.png",
            "img/huijinaiyin/huijinaiyin260_2.png","img/char/anon.png",
            "img/huijinaiyin/huijinaiyin260_2.png",


    };
}

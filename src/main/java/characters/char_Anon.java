package characters;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import cards.others.BasicSongs;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import Mod.AnonMod;
import power.musicStart;
import pathes.ThmodClassEnum;
import pathes.AbstractCardEnum;
import relics.Mana_relic;
import ui.SkinSelectScreen;
//import ui.SkinSelectScreen;

import java.util.ArrayList;
import java.util.List;

//import static ui.SkinSelectScreen.Inst;

public class char_Anon extends CustomPlayer  {
    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String SELES_SHOULDER_2 = "img/char/anon_1700.png";
    private static final String SELES_SHOULDER_1 = "img/char/anon_1700.png";
    private static final String SELES_CORPSE = "img/char/anon_fall.png";

    public static String SELES_STAND = null;


    //2顺势转，3逆时针转

    private static final String[] ORB_TEXTURES = new String[] {
            "img/UI/urgreen_129.png",
            "img/UI/layer_5_127.png",
            "img/UI/layer_3.png",
            "img/UI/layer2.png",
            "img/UI/threeButterfly_127.png",
            "img/UI/fivestar_129.png",
            "img/UI/urgreen_129.png",
            "img/UI/layer_5_d.png",
            "img/UI/layer_3_d.png",
            "img/UI/layer2d.png",
            "img/UI/threeButterfly_127.png" };

    private static final String ORB_VFX = "img/UI/vfx.png";
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 80;
    private static final int MAX_HP = 80;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    private Hitbox Corrode;
    public static float Corrode_BAR_HEIGHT =  20.0F * Settings.scale;
    public Color CorrodeBarColor1 = new Color(255.0F, 192.0F, 203.0F, 1.0F);
    public Color CorrodeBarColor2 = new Color(139.0F, 0.0F, 0.0F, 1.0F);
    public Color CorrodeShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color CorrodeBgColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
    public Color CorrodetextColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    private float CorrodeWidth;

    private int ifStart;
    public static CardGroup DiaryGroup;
    private int heavy;
    private float CorrodeValueWidth;

    public static int beat = 0;

    public static String[] skinAnimation = {"img/test/Anon.scml",
            "img/test/AshAnon.scml",
            "img/test/AnonWhite.scml",
            "img/test/AnonStar.scml",
            "img/test/caicai.scml",
            "img/test/AnonFes.scml",
            "img/test/AnonGive.scml",
            "img/test/AnonSIx.scml",
            "img/test/shiro.scml",
            "img/test/feimali.scml",
            "img/test/PAREO.scml",
            "img/test/yukina.scml",
            "img/test/soyo.scml",
            "img/test/smallworker.scml",
            "img/test/tech.scml",
            "img/test/leader.scml",
            "img/test/KSM.scml",
            "img/test/rabbit.scml",
            "img/test/mika.scml"};
    public static String[] WhiteAnonskinAnimation = {
            "img/test/AnonWhite.scml","img/test/AnonWhiteAlter.scml"
    };
    public static String[] AshAnonskinAnimation = {
            "img/test/AshAnon.scml","img/test/AshAnon2.scml"
    };
    public Color CorrodeBarColorGreen = new Color(0.0F, 255F, 0.0F, 1.0F);
    public Color CorrodeBarColorRed = new Color(255.0F, 0.0F, 0.0F, 1.0F);
    public Color CorrodeBarColorBlue = new Color(0.0F, 0F, 255F, 1.0F);
    public static String beatState = "";
    int a= 0;
    SpriterAnimation anon = new SpriterAnimation(filepath);
    public static String filepath = "img/test/Anon.scml";
    public static AnimationState.TrackEntry e;


    public char_Anon(String name) {
        //构造方法，初始化参数
//        super(name, ThmodClassEnum.Anon_COLOR, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null, (String)null);
        super(name, ThmodClassEnum.Anon_COLOR, new CustomEnergyOrb(ORB_TEXTURES, ORB_VFX, LAYER_SPEED), new SpriterAnimation(filepath));
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass( null , SELES_SHOULDER_2, SELES_SHOULDER_1, SELES_CORPSE,
                getLoadout(),
                50.0F, -20.0F, 230.0F, 350.0F,
                new EnergyManager(ENERGY_PER_TURN));
        refreshSkin();
    }

//    public static String characterPath(String file) { return "keinemodResources/character/" + file; }
   public void refreshSkin() {
       this.animation = new SpriterAnimation(skinAnimation[SkinSelectScreen.Inst.index]);
//       filepath =
//   SkinSelectScreen.Skin skin = SkinSelectScreen.getSkin();
//   loadAnimation(skin.charPath + ".atlas", skin.charPath + ".json", 1.8F);
//   AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
//   e.setTime(e.getEndTime() * MathUtils.random());
//   e.setTimeScale(1.2F);
 }

    public void WhiteAnonAwakeRefreshSkin(boolean ifAwake) {
        if(ifAwake){
            this.animation = new SpriterAnimation(WhiteAnonskinAnimation[1]);
        }else {
            this.animation = new SpriterAnimation(WhiteAnonskinAnimation[0]);
        }

    }

    public void AshAnonAwakeRefreshSkin() {
        if(AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(Mana_relic.ID)){
            this.animation = new SpriterAnimation(AshAnonskinAnimation[1]);
        }else {
            this.animation = new SpriterAnimation(AshAnonskinAnimation[0]);
        }

    }
    public static boolean isNotable(AbstractCard c) {
        return !c.exhaust && c.type != AbstractCard.CardType.POWER && c.cost >= 0;
    }

    public void onStanceChange(String newStance) {
//        filepath = "img/test/Anon.scml";
    }
    @Override
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Strike_Anon");
        retVal.add("Strike_Anon");
        retVal.add("Strike_Anon");
        retVal.add("Strike_Anon");
        retVal.add("Strike_Anon");
        retVal.add("Defend_Anon");
        retVal.add("Defend_Anon");
        retVal.add("Defend_Anon");
        if(SkinSelectScreen.Inst.index!= 15) {
            retVal.add("Defend_Anon");
        }
        retVal.add("ChangeStateSongs");

        switch (SkinSelectScreen.Inst.index) {
            case 1:
                retVal.add("AIHeartSongs");
                AnonMod.saves.setBool("MachineHeartLocked",true);
                break;
            case 2:
                retVal.add("WhiteTrio");
                retVal.add("WhiteAnonHaruhikageSongs");
                break;
            case 3:
                retVal.add("UntouchableFuture");
                retVal.add("SoloConcertSongs");
                break;
//            case 17:
//                retVal.add("yuzu_Image");
//                retVal.add("yuzu_Climax");
//                retVal.add("yuzu_SimplifiedCombo");
//                retVal.add("KoiKouEnishiSongs");
//                retVal.remove("ChangeStateSongs");
//                break;
            default:
                retVal.add("BasicSongs");
                break;
        }
        if(SkinSelectScreen.Inst.index == 13) {
            retVal.add("barrel");

        }
        if(SkinSelectScreen.Inst.index == 15) {
            retVal.add("barrel");
            retVal.add("LeaderScapegoat");
            retVal.remove("BasicSongs");
        }

        System.out.println("初始卡组");
        System.out.println("加载");
//        retVal.add("Printf");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Guitar");
        retVal.add("GuitarWhiteAnon");
        retVal.add("StarAnonBike");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选英雄界面的文字描述
        String title="";
        String flavor="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "Anon";
            flavor = "和Anon一起，组建乐队吧！";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
        } else {
        }

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP,HAND_SIZE , STARTING_GOLD, ASCENSION_MAX_HP_LOSS, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        //应该是进游戏后左上角的角色名
        String title="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "Anon";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "Anon";
        } else {
            title = "Anon";
        }

        return title;
    }

    @Override

    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return AbstractCardEnum.Anon_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return SILVER;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new BasicSongs();
    }

    @Override
    public Color getCardTrailColor() {
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }
    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        String char_name;
//        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "Anon";
//        } else if (Settings.language == Settings.GameLanguage.ZHT) {
//            char_name = "Anon";
//        } else {
//            char_name = "Anon";
//        }
        return char_name;
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new char_Anon(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "这就是最后了吗……";
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getVampireText() {

        return "“欸，你说你们是吸血鬼？”"+" NL " +
                "(完蛋了，他们好像是英国来的，不会认识我吧……)";
    }
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }

    private void renderTruthValueBar(SpriteBatch sb, float x, float y) {
        try {
            Corrode_BAR_HEIGHT = this.hb.cX/ 25.0F;
            sb.setColor(this.CorrodeBarColor1);

            if(beatState.equals("attack")){
                sb.setColor(this.CorrodeBarColorRed);
            }
            if(beatState.equals("skill")){
                sb.setColor(this.CorrodeBarColorGreen);
            }
            if(beatState.equals("power")){
                sb.setColor(this.CorrodeBarColorBlue);
            }
            //实际左框
            sb.draw(ImageMaster.HEALTH_BAR_L, x - this.hb.width/ 12F, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.5F, 20.0F * Settings.scale, 25);
            //实际中间width表示长度
            sb.draw(ImageMaster.HEALTH_BAR_B, x  , this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F + this.hb.width * 1.5F, beat * (this.hb.width/7), 25);
            //实际右边的框
            sb.draw(ImageMaster.HEALTH_BAR_R, x  + beat * (this.hb.width/7) , this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F + this.hb.width * 1.5F, 20.0F * Settings.scale, 25);

//            sb.draw(ImageMaster.HEALTH_BAR_L, x - Corrode_BAR_HEIGHT  / 2.0F, this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
//            sb.draw(ImageMaster.HEALTH_BAR_B, x, this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F,  beat * (this.hb.width/7), Corrode_BAR_HEIGHT);
//            sb.draw(ImageMaster.HEALTH_BAR_R, x + beat * (this.hb.width/7), this.Corrode.cY - Corrode_BAR_HEIGHT / 2.0F, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
        }catch (Exception e){

        }
    }

    private character3DHelper char3D;
    /*
    测试皮肤
     */
    public void update() {
        super.update();
        if(SkinSelectScreen.Inst.index ==18){
            if (this.char3D == null)
                try {
                    this.char3D = new character3DHelper();
                } catch (Exception e) {
                }
            this.char3D.update(0, 0);
        }
    }
//    private void updateSkinType() { int i = SkinSelectScreen.Inst.index;
//        if (this.formalSkinType != this.skinType) {
//            this.formalSkinType = this.skinType;
//            if (this.skinType != -1) {
//                this.img = ImageMaster.loadImage(stand2D[1]);
//                this.corpseImg = ImageMaster.loadImage(stand2D[1]);
//            } else {
//                this.img = ImageMaster.loadImage(stand2D[0]);
//                this.corpseImg = ImageMaster.loadImage("tunerResources/img/char/HinaDie.png");
//            }
//        }
//    }
    public void TruthValueUpdatedEvent() {
        this.CorrodeValueWidth = (float) AnonMod.corrode / 5.0F * this.hb.width;
        if (AnonMod.corrode == 5) {
            this.CorrodeWidth = this.hb.width;
        } else if (AnonMod.corrode == 0) {
            this.CorrodeWidth = 0.0F;
            this.CorrodeValueWidth = 10.0F;
        }

        this.CorrodeWidth = this.CorrodeValueWidth;
    }
    public AnimationState eyeState;
    public void renderHealth(SpriteBatch sb) {
        super.renderHealth(sb);
        float x = this.hb.cX - this.hb.width / 2.0F;
        float y = this.hb.cY - this.hb.height / 2.0F;
//        float x = 1000;
//        float y = 1000;
        this.Corrode = new Hitbox(x, y, this.hb_w, Corrode_BAR_HEIGHT);
        this.Corrode.render(sb);
        this.TruthValueBgRender(sb, x, y);//长条
        this.renderTruthValueBar(sb, x, y);//两点
        this.TruthValueText(sb, y);//数字文本
    }

    public void render(SpriteBatch spriteBatch) { if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.RestRoom)) {
        if (SkinSelectScreen.Inst.index == 18) {
            if (this.isDead)
                this.char3D.update(this.drawX, this.drawY);
            spriteBatch.setColor(Color.WHITE);
            if (char3D != null) {
                this.char3D.render(spriteBatch, this.flipHorizontal);
            } else
                System.out.println("char3D = null");
        }
    }
        super.render(spriteBatch); }
// public void onStanceChange(String newStance) {
//   if (newStance.equals("HakutakuForm")) {
//     this.animation = new SpriterAnimation(KeineMod.characterPath("animation/hakutaku.scml"));
//   } else {
//     this.animation = new SpriterAnimation(KeineMod.characterPath("img/test/Ash.scml"));
//   }
//        /*     */   }
//    AbstractAnimation animation2 = new SpriterAnimation("img/test/Ash.scml");
    private void TruthValueBgRender(SpriteBatch sb, float x, float y) {
        if(a == 0){
//            this.animation = new SpriterAnimation("img/test/Ash.scml");
            a++;
        }

//        this.eyeState.setAnimation(0, "Calm", true);
//        this.animation = new SpriterAnimation("img/test/Ash.scml");
//        this.animation=null;
//        animation.renderSprite(sb,AbstractDungeon.player.drawX + 0.0F * Settings.scale,AbstractDungeon.player.drawY + 0.0F * Settings.scale);
        sb.setColor(this.CorrodeShadowColor);
        //左底框
        sb.draw(ImageMaster.HB_SHADOW_L, x - this.hb.width/ 12F, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.5F, 20.0F * Settings.scale, 25);
        //中间
        sb.draw(ImageMaster.HB_SHADOW_B, x, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.5F, this.hb.width, 25);
        //右底框
        sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width , this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.5F, 20.0F * Settings.scale, 25);
        sb.setColor(this.CorrodeBgColor);

//        sb.draw(ImageMaster.HEALTH_BAR_L, x - Corrode_BAR_HEIGHT, y, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
//        sb.draw(ImageMaster.HEALTH_BAR_B, x, y, this.hb.width, Corrode_BAR_HEIGHT);
//        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.hb.width, y, 20.0F * Settings.scale, Corrode_BAR_HEIGHT);
    }
//
    private void TruthValueText(SpriteBatch sb, float y) {
        float tmp = this.CorrodetextColor.a;
        if(musicStart.ifStart == 2){
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "LIVE进行中！", this.hb.cX, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.7F, this.CorrodetextColor);

        }else {
        }
        if(musicStart.ifStart == 0){
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, "准备演奏！", this.hb.cX, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.55F , this.CorrodetextColor);
        }
        else {
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, beat + "/7", this.hb.cX, this.Corrode.cY  - Corrode_BAR_HEIGHT / 2.0F  + this.hb.width * 1.55F, this.CorrodetextColor);
        }
        this.CorrodetextColor.a = tmp;
    }

    /**
     * 通关动画
     * @return
     */
    public List<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> list = new ArrayList();
        list.add(new CutscenePanel("img/boss/end1.png"));
        list.add(new CutscenePanel("img/boss/end2.png"));
        list.add(new CutscenePanel("img/boss/end3.png"));
        return list;
    }

    public void WhiteAnonAwake(boolean ifawake) {
    if (ifawake){
        SkinSelectScreen.Inst.index = 15;
        refreshSkin();
    }else {
        SkinSelectScreen.Inst.index =3;
        refreshSkin();
    }

    }



    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass char_Anon;
        @SpireEnum
        public static AbstractCard.CardTags Anon;


        public Enums() {
        }
    }

    public static void getBeats(int num,String color){
        beat = num;
        beatState = color;

    }
    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        if(SkinSelectScreen.Inst.index ==18)
        applyUseCardAni(c, monster);
        super.useCard(c, monster, energyOnUse);
    }

    private void applyUseCardAni(AbstractCard c, AbstractMonster monster) {
        ArrayList<AnimaItem> list = new ArrayList<AnimaItem>();
            if (c.type == AbstractCard.CardType.ATTACK)
                if (c.costForTurn < 2) {
                    list.add(MikaAnimaItem.Normal_Attack_Ing);
                    list.add(MikaAnimaItem.Normal_Attack_End);
                    this.char3D.queueAnimaItem(list, !this.char3D.isAttacking());
                } else {
                    list.add(MikaAnimaItem.Normal_Attack_Start);
                    list.add(MikaAnimaItem.Normal_Attack_Ing);
                    list.add(MikaAnimaItem.Normal_Attack_End);
                    this.char3D.queueAnimaItem(list, true);
                }
            if (c.type == AbstractCard.CardType.POWER) {
                list.add(MikaAnimaItem.Cafe_Reaction);
                CardCrawlGame.sound.play("murimuri");
                this.char3D.queueAnimaItem(list, true);
            }
        if (c.type == AbstractCard.CardType.SKILL) {
            list.add(MikaAnimaItem.Normal_Reload);
            this.char3D.queueAnimaItem(list, true);
        }
        }
}

package power;

import actions.SkipEnemiesTurnFalseAction;
import bossRoom.CrychicSide;
import bossRoom.InnerFavillaeSide;
import bossRoom.huijinaiyin.*;
import bossRoom.huijinaiyin.effect.InnerFavillaeLightEffect;
import cards.token.Idea;
import cards.token.Minute;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
//import org.graalvm.compiler.phases.common.NodeCounterPhase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class AshAnonBeat extends AbstractPower {
    public static boolean Word = false;
        public static final String POWER_ID = "AshAnonBeat";
//        private static final PowerStrings powerStrings;
//        public static final String NAME;
//        public static final String[] DESC;
        private static final int STR_AMT = 2;
        private static final int COUNTDOWN_AMT = 12;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        public static int beatNum ;
        public ArrayList<DamageInfo> damage;
        public AshAnonBeat(AbstractCreature owner) {
            Word = false;
            this.name = NAME;
            this.ID = POWER_ID;
            this.owner = owner;
            this.amount = 0;
            this.updateDescription();
//            this.loadRegion("time");
            damage = new ArrayList<>();
            damage.add(new DamageInfo(this.owner, 5));
            this.type = PowerType.BUFF;
            String path128 = "img/newbuff/notePower84.png";
            String path48 = "img/newbuff/notePower32.png";
            this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        }

//        public void playApplyPowerSfx() {
//            CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
//        }

        public void updateDescription() {
//            this.description = "在使用" +(7-this.amount) +"张牌后进行属于InnerFavillae的Live。";
            this.description = DESCRIPTIONS[0] +(7-this.amount) + DESCRIPTIONS[1];
        }

        public void onAfterUseCard(AbstractCard card, UseCardAction action) {

            if(this.owner.hasPower(StunMonsterPower.POWER_ID)){
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, StunMonsterPower.POWER_ID));
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "你为什么会觉得这会奏效？", false));
                }else {
                    AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "Why would you think this would work?", false));
                }
            }
            if(InnerFavillaeSide.Stage == 3 && Word == false && !this.owner.hasPower(ArtifactPower.POWER_ID)) {
                if (card.cardID.equals(cards.others.LoveMeIFYouCan.ID)) {
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "我没输，只是真奈来了而已！", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "I haven't lost, it's just that Mana came!", false));
                    }
                    Word = true;
                }
            }
                if(beatNum==6 && InnerFavillaeSide.Stage == 3){
                    if(AbstractDungeon.player.hasPower(variation.POWER_ID)){
                        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new cards.others.LoveMeIFYouCan(), 1));
                    }else {
                        if(beat.nowBeats == "attack" && card.type.equals(AbstractCard.CardType.ATTACK) ||
                                beat.nowBeats == "skill" && card.type.equals(AbstractCard.CardType.SKILL)||
                                beat.nowBeats == "power" && card.type.equals(AbstractCard.CardType.POWER)){
                            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new cards.others.LoveMeIFYouCan(), 1));
                        }
                    }
            }
            Boolean done = false;
            this.flashWithoutSound();
            ++this.amount;

            beatNum = beat.AllAmount;
            if (this.amount >= 7) {
                this.amount = 0;
                this.playApplyPowerSfx();
                if(this.owner.hasPower(LoveMeIFYouCan.POWER_ID)){
                    if (this.owner.getPower(LoveMeIFYouCan.POWER_ID).amount == 1) {
                        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "LoveMe'IF'YouCan"));
                    } else {
                        this.addToBot(new ReducePowerAction(this.owner, this.owner, "LoveMe'IF'YouCan", 1));
                    }
                }else {
                    for(int i = 0; i < 10; ++i) {
                        if (!AshAnonSongs.SongsList[i].equals("")) {
                            this.musicEffect(AbstractDungeon.player, i);
                        }
                    }
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new Ash(this.owner,1), 1));

                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 1 && !done){
                    this.addToBot(new VFXAction(new InnerFavillaeLightEffect()));
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"星之轨迹"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals(MachineHeart.ID)) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new StarTrails(300.0F, 400.0F, 0), false));
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "感受机械之心的跃动吧……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "Feel the beat of the mechanical heart.", false));
                    }
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 2 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"镇魂祭"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals("StarTrails")) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new ZhenHunJi(300.0F, 400.0F, 0), false));
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "照亮黑暗的光啊……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "Illuminating light of the darkness...", false));
                    }
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 3 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"理想的尽头"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals("ZhenHunJi")) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "不过是太过美化的幻想……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "Nothing more than an overly romanticized fantasy.", false));
                    }
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new IdealEnd(300.0F, 400.0F, 0), false));
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 4 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"自灭因子"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals("IdealEnd")) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "请不要再往前进了……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "Please do not advance any further.", false));
                    }
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SuicideFactor(300.0F, 400.0F, 0), false));
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 5 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"少女再生"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals("SuicideFactor")) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "有谁会获救吗？ NL 是你，还是我……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "Will anyone be saved? NL Is it you, or is it me...?", false));
                    }
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GirlRelive(300.0F, 400.0F, 0), false));
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 6 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"坠落少女"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals("GirlRelive")) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "这里是我的领域 NL 也是我坚持的理由……", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "This is my domain NL It is also the reason I persist...", false));
                    }
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Falling(300.0F, 400.0F, 0), false));
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 1 && InnerFavillaeSide.StageSongsNum == 7 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"机械心"), 1));
                    InnerFavillaeSide.StageSongsNum =1;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while(var4.hasNext()) {
                        AbstractMonster m2 = (AbstractMonster)var4.next();
                        if (m2.id.equals("Falling")) {
                            addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)m2));
                        }
                    }
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "游戏结束了 NL 与我一同坠落吧！", false));
                    }else {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.owner.hb.cX + this.owner.dialogX - 50, this.owner.hb.cY + this.owner.dialogY + 50, 2.5F, "The game is over. NL Let's falling together!", false));
                    }
                    InnerFavillaeSide.Stage=2;
                    InnerFavillaeSide.moveCount = 0;
                    done = true;
                }
        ////////**下面是二阶段演奏

                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 1 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"星之轨迹"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 2 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"镇魂祭"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 3 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"理想的尽头"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 4 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"自灭因子"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 5 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"少女再生"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 6 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"坠落少女"), 1));
                    InnerFavillaeSide.StageSongsNum ++;
                    done = true;
                }
                if (InnerFavillaeSide.Stage == 2 && InnerFavillaeSide.StageSongsNum == 7 && !done){
                    AshAnonSongs.SongsList = new String[]{"","","","","","","","","",""};
                    addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new AshAnonSongs(this.owner,"机械心"), 1));
                    InnerFavillaeSide.StageSongsNum =1;
//                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new MachineHeart(300.0F, 400.0F, 0), false));
                    done = true;
                }
            }
            this.updateDescription();
        }
    public void musicEffect(AbstractPlayer p, int songsNum){
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "机械心")) {
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new InnerFavillaeShining(this.owner,1), 1));
            }
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "星之轨迹")) {
                addToBot((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this.owner));
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new ArtifactPower(this.owner,1), 1));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, 50));
            }
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "镇魂祭")) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this.owner, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 5, true), 5));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this.owner, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 5, true), 5));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this.owner, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 5, true), 5));
            }
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "理想的尽头")) {
                musicStart.ifStart =1;
                addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new notLive(AbstractDungeon.player, 1, true), 1));
                addToBot((AbstractGameAction)new SkipEnemiesTurnFalseAction());
            }
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "自灭因子")) {
                if(AbstractDungeon.player.maxHealth >= 6){
                    AbstractDungeon.player.maxHealth -= 5;
//                    AbstractDungeon.player.currentHealth -= 5;
                }else {
                    AbstractDungeon.player.maxHealth = 1;
//                    AbstractDungeon.player.currentHealth = 1;
                }
                if(AbstractDungeon.player.currentHealth >= 6) {
                    AbstractDungeon.player.currentHealth -= 5;
                }else {
                    AbstractDungeon.player.currentHealth = 1;
                }
            }
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "少女再生")) {
                AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, 50));
            for (Iterator<AbstractPower> s = p.powers.iterator(); s.hasNext(); ) {
                AbstractPower c = s.next();
                if (c.type == PowerType.BUFF && !c.ID.equals(Shining.POWER_ID) && !c.ID.equals("beat")&& !c.ID.equals(musicStart.POWER_ID)&& !c.ID.equals(songs.POWER_ID)){
                    s.remove();
                    break;
                }

            }
            }
            if (Objects.equals(AshAnonSongs.SongsList[songsNum], "坠落少女")) {
                if(InnerFavillaeSide.Stage != 3){
                    AbstractDungeon.actionManager.callEndTurnEarlySequence();
                }else {
                    for (int i = 0; i < 4; i++) {
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                    }
                }
            }
    }
        static {
//            powerStrings = CardCrawlGame.languagePack.getPowerStrings("Time Warp");
//            NAME = powerStrings.NAME;
//            DESC = powerStrings.DESCRIPTIONS;
        }
    }



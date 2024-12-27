package power;

import actions.RemoveNumDebuffsAction;
import actions.dislocateAction;
import basemod.BaseMod;
import bossRoom.CrychicSide;
import cards.token.Idea;
import characters.char_Anon;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.ForesightPower;
import com.megacrit.cardcrawl.powers.watcher.NirvanaPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import relics.GuitarWhiteAnon;


import java.util.*;

public class musicStart extends AbstractPower {
    // 这是高潮迭起
    public static final String POWER_ID ="muiscStart";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME =  powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    /**
     * 0准备 2进行中 1其他 3初始
     */
    public static int ifStart = 3;




    public musicStart(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
//        String path128 = "img/newbuff/Example32.png";
//        String path48 = "img/newbuff/Example84.png";
        String path128 = "img/newbuff/musicStartPower84.png";
        String path48 = "img/newbuff/musicStartPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
//        this.description = "Anon收到某人影响，现在有" + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "个好点子";
        this.description = DESCRIPTIONS[0];
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
//
//        // 如果该能力不会修改受到伤害的数值，按原样返回即可
//        return damageAmount;
//    }
// 省略其他


    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999)
            this.amount = 999;
    }
    public void atStartOfTurn() {
//        int usedEnergy = AbstractDungeon.player.energy.energy-EnergyPanel.totalCount;
//        AbstractDungeon.player.energy.use(usedEnergy-1);
//        EnergyPanel.totalCount = beat.energy + 1;

///       AbstractDungeon.player.loseEnergy(this.energyLoss);
//        System.out.println("EnergyPanel.totalCount"+EnergyPanel.totalCount+"AbstractDungeon.player.energy.energy"+AbstractDungeon.player.energy.energy);

        AbstractPlayer p = AbstractDungeon.player;
        flash();
        musicStart.ifStart = 2;
        addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        for(int i = 0; i <= songs.LiveSongsNum - 1; ++i) {
            if (i >= songs.SongsList.length) {
                break;
            }
            if (!songs.SongsList[i].equals("")) {
                System.out.println("演奏第"+i+"首歌："+songs.SongsList[i]);
                this.musicEffect(p, i);
            }
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new notLive(this.owner,1,false), 1));
    }

    public void onEnergyRecharge() {

    }

    public void musicEffect(AbstractPlayer p,int songsNum){
        if(p.hasPower(songs.POWER_ID)){
        if (Objects.equals(songs.SongsList[songsNum], "潜在表明")) {
            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, 2), 2));
            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, 2), 2));
        }
        if (Objects.equals(songs.SongsList[songsNum], "音一会")) {
            boolean powerExists = false;
            Iterator var4 = p.powers.iterator();
            while(var4.hasNext()) {
                AbstractPower pow = (AbstractPower)var4.next();
                if (pow.ID.equals("Barricade")) {
                    powerExists = true;
                    break;
                }
            }
            if (!powerExists) {
                this.addToBot(new ApplyPowerAction(p, p, new BarricadePower(p)));
            }
            this.addToBot(new GainBlockAction(p, p, 20));
        }
        if (Objects.equals(songs.SongsList[songsNum], "名無声")) {
            addToBot((AbstractGameAction) new RemoveDebuffsAction((AbstractCreature) AbstractDungeon.player));
            addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, 20));
        }
        if (Objects.equals(songs.SongsList[songsNum], "剪切线")) {
            this.addToBot(new MakeTempCardInHandAction(new Idea(), 2));
        }
        if (Objects.equals(songs.SongsList[songsNum], "壱雫空")) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) mo, 5, false), 5, true, AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 5, false), 5, true, AbstractGameAction.AttackEffect.NONE));
            }
        } if (Objects.equals(songs.SongsList[songsNum], "影色舞")) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new SilhouettePower(this.owner), this.amount));
        }
        if (Objects.equals(songs.SongsList[songsNum], "詩超絆")) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DoubleDamagePower(this.owner, 1, false), this.amount));
        }
        if (Objects.equals(songs.SongsList[songsNum], "碧天伴走")) {
            addToBot((AbstractGameAction) new GainEnergyAction(2));
            addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, 2));
        }
        if (Objects.equals(songs.SongsList[songsNum], "春日影")) {
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster mo = (AbstractMonster)var3.next();
                if (!mo.isDead) {
                    if(mo.id.equals("sakiko")){
                        CrychicSide.Haruhikage = true;
                    }else {
                        this.addToBot(new StunMonsterAction(mo, p, 1));
                    }
                }
            }
        }
            if (Objects.equals(songs.SongsList[songsNum], "youthfulbeautiful")) {
                this.addToBot(new GainBlockAction(p, p, 20));
                if(Objects.equals(songs.SongsList[1], "")){
                    addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, 2), 2));
                    addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, 2), 2));
                }
            }
            if (Objects.equals(songs.SongsList[songsNum], "毫无活力")) {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));

                }
                if(Objects.equals(songs.SongsList[1], "")) {
                    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -2), -2));
                    }
                }
            }
            if (Objects.equals(songs.SongsList[songsNum], "碧天伴走(星)")) {
                addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, 3));
                if(Objects.equals(songs.SongsList[1], "")){
                    this.addToBot(new GainEnergyAction(2));
                    addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DrawCardNextTurnPower((AbstractCreature) AbstractDungeon.player, 2), 2));
                }
            }
            if (Objects.equals(songs.SongsList[songsNum], "机械心")) {
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new ArtifactPower(this.owner,3), 3));
            }
            if (Objects.equals(songs.SongsList[songsNum], "毁灭你，与你何干？") || Objects.equals(songs.SongsList[songsNum], "拯救你，与你何干？") ||Objects.equals(songs.SongsList[songsNum] , "我爱你，与你何干？")) {
                char_Anon k = (char_Anon)AbstractDungeon.player;
                k.WhiteAnonAwakeRefreshSkin(true);

                if (p.hasPower(GirlsReboot.POWER_ID)) {
                    addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, 1), 1));
                    addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, 1), 1));
                }
                if (p.hasPower(WorldLegacy.POWER_ID)) {
                    if (p.hasRelic("GuitarWhiteAnon") && p.hasPower(notLive.POWER_ID)) {
                        Random random = new Random();
                        int randomNumber = random.nextInt(100) + 1;
                        if (randomNumber >= p.getRelic(GuitarWhiteAnon.ID).counter) {
                            addToBot((AbstractGameAction) new ApplyPowerAction(p, p, (AbstractPower) new notLive(p, -1, false), -1));
                        }
                    }
                }
                if (p.hasPower(WhiteMythology.POWER_ID)) {
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new heavy((AbstractCreature) p, 2), 2));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new ConstrictedPower((AbstractCreature) p, p, 4), 4));
                    Iterator var2 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while (var2.hasNext()) {
                        AbstractMonster mo = (AbstractMonster) var2.next();
                        if (!mo.isDead) {
                            addToBot((AbstractGameAction) new ApplyPowerAction(mo, (AbstractCreature) p, (AbstractPower) new EnemyHeavy((AbstractCreature) mo, 2), 2));
                            addToBot((AbstractGameAction) new ApplyPowerAction( mo, (AbstractCreature) p, (AbstractPower) new ConstrictedPower((AbstractCreature) mo, p, 4), 4));
                        }
                    }
                }

                //预见
                if(GuitarWhiteAnon.ifGirlReboot && GuitarWhiteAnon.ifWorldLegacy && GuitarWhiteAnon.ifWhiteMythology){
                    this.addToBot(new ScryAction(5));
                }


                if (Objects.equals(songs.SongsList[songsNum], "毁灭你，与你何干？")) {
                    if (!p.hasPower(WhiteMythology.POWER_ID))
                        addToBot((AbstractGameAction) new ApplyPowerAction(p, p, (AbstractPower) new ArtifactPower(p, 1), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new GirlsReboot((AbstractCreature) p), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) p, 1, false), 1));
                    Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                    while (var3.hasNext()) {
                        AbstractMonster mo = (AbstractMonster) var3.next();
                        if (!mo.isDead) {
                            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new WeakPower((AbstractCreature) mo, 1, false), 1));
                        }
                    }
                    this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, (heavy.count / 2) + 5), (heavy.count / 2) + 5));
                    GuitarWhiteAnon.ifGirlReboot = true;
                }
                if (Objects.equals(songs.SongsList[songsNum], "拯救你，与你何干？")) {
//                    for (Iterator<AbstractPower> s = p.powers.iterator(); s.hasNext(); ) {
//                        AbstractPower c = s.next();
//                        if (c.type == AbstractPower.PowerType.DEBUFF) {
//                            s.remove();
//                            break;
//                        }
//                    }
//                    for (Iterator<AbstractPower> s = p.powers.iterator(); s.hasNext(); ) {
//                        AbstractPower c = s.next();
//                        if (c.type == AbstractPower.PowerType.DEBUFF) {
//                            s.remove();
//                            break;
//                        }
//                    }
                    addToBot((AbstractGameAction)new RemoveNumDebuffsAction((AbstractCreature)AbstractDungeon.player));
                    addToBot((AbstractGameAction)new RemoveNumDebuffsAction((AbstractCreature)AbstractDungeon.player));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new WorldLegacy((AbstractCreature) p), 1));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new RegenPower((AbstractCreature) p, 4), 4));
                    addToBot((AbstractGameAction) new GainEnergyAction(1));
                    GuitarWhiteAnon.ifWorldLegacy = true;
                }
                if (Objects.equals(songs.SongsList[songsNum], "我爱你，与你何干？")) {
//                    int randomNum2 = 1;
                    if(p.hasPower(GirlsReboot.POWER_ID) && Shining.allCount != heavy.count){
                        Random rand = new Random();
                        int randomNum = rand.nextBoolean() ? 4 : 5;
//                        randomNum2 = rand.nextBoolean() ? 0 : 1;
                        addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, randomNum));
                    }else {
                        addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, 5));
                    }

                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new WhiteMythology((AbstractCreature) p), 1));
                    addToBot((AbstractGameAction) new dislocateAction((AbstractCreature) p, null));
                    GuitarWhiteAnon.ifWhiteMythology = true;
                }
            }
            if (Objects.equals(songs.SongsList[songsNum], "咫尺天涯的春日影")) {
                this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, 1, false),1));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
            if (Objects.equals(songs.SongsList[songsNum], "隔世遥望的春日影")) {
                this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 1), 1));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, 1, false),1));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                }

            }
            if (Objects.equals(songs.SongsList[songsNum],  "魂牵梦萦的春日影")) {
                this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 1), 1));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, 1, false),1));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
    }
        if (Objects.equals(songs.SongsList[songsNum], "以恋结缘")) {
            this.addToBot(new ApplyPowerAction(p, p, new KoiKouEnishi(p, 6), 6));
        }
        if (Objects.equals(songs.SongsList[songsNum], "熙熙攘攘、我们的城市")) {
            this.addToBot(new ApplyPowerAction(p, p, new FlightPower(p, 1), 1));
        }
        if (Objects.equals(songs.SongsList[songsNum], "空之箱")) {
            if(!AbstractDungeon.player.drawPile.isEmpty()) {
                Iterator var3 = AbstractDungeon.player.drawPile.group.iterator();
                while(var3.hasNext()) {
                    AbstractCard e = (AbstractCard) var3.next();
                    if (e.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || e.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                        addToTop(new ExhaustSpecificCardAction(e, AbstractDungeon.player.drawPile));
                    }
                }
            }
        }
        if (Objects.equals(songs.SongsList[songsNum], "吉他与孤独与蓝色星球")) {
            this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 2), 2));
        }

        if (Objects.equals(songs.SongsList[songsNum], "ときめきエクスペリエンス！")) {
            this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 5), 5));
        }
        if (Objects.equals(songs.SongsList[songsNum], "FIREBIRD")) {
            this.addToBot(new ApplyPowerAction(p, p, new NirvanaPower(p, 10), 10));
            this.addToBot(new ApplyPowerAction(p, p, new ForesightPower(p, 3), 3));
        }
        if (Objects.equals(songs.SongsList[songsNum], "しゅわりん☆どり～みん")) {
            AbstractCard xiuwaxiuwa = new SelfRepair();
            this.addToBot(new MakeTempCardInHandAction(xiuwaxiuwa, 3));
        }
        if (Objects.equals(songs.SongsList[songsNum], "卫星露天咖啡屋")) {
            BaseMod.MAX_HAND_SIZE +=2;
        }
    }
}


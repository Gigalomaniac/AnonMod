package cards.uncommon;

import BossRewards.KarenRewardCard;
import actions.dislocateAction;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import bossRoom.CrychicSide;
import cards.token.Idea;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import characters.char_Anon;
import com.megacrit.cardcrawl.powers.watcher.ForesightPower;
import com.megacrit.cardcrawl.powers.watcher.NirvanaPower;
import pathes.AbstractCardEnum;
import power.*;
import power.FlightPower;
import relics.GuitarWhiteAnon;

import java.util.*;

import static utils.AnonSpireKit.addToTop;

/**
 * 每一个瞬间的积累
 */
public class DreamComeTrue extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DreamComeTrue");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "DreamComeTrue";
    public static final String IMG_PATH = "img/card_Anon/bangdream_skill.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public DreamComeTrue() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //初始为3层活力
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("songs")){
            musicEffect(p);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new DreamComeTrue();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果，升级后活力+2
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void musicEffect(AbstractPlayer p){
        if (Objects.equals(songs.SongsList[0], "潜在表明")) {
            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, 2), 2));
            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, 2), 2));
        }
        if (Objects.equals(songs.SongsList[0], "音一会")) {
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
        if (Objects.equals(songs.SongsList[0], "名無声")) {
            addToBot((AbstractGameAction) new RemoveDebuffsAction((AbstractCreature) AbstractDungeon.player));
            addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, 20));
        }
        if (Objects.equals(songs.SongsList[0], "剪切线")) {
            this.addToBot(new MakeTempCardInHandAction(new Idea(), 2));
        }
        if (Objects.equals(songs.SongsList[0], "壱雫空")) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) mo, 5, false), 5, true, AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 5, false), 5, true, AbstractGameAction.AttackEffect.NONE));
            }
        } if (Objects.equals(songs.SongsList[0], "影色舞")) {
            this.addToBot(new ApplyPowerAction(p, p, new SilhouettePower(p), 1));
        }
        if (Objects.equals(songs.SongsList[0], "詩超絆")) {
            this.addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false), 1));
        }
        if (Objects.equals(songs.SongsList[0], "碧天伴走")) {
            addToBot((AbstractGameAction) new GainEnergyAction(2));
            addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, 2));
        }
        if (Objects.equals(songs.SongsList[0], "春日影")) {
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
        if (Objects.equals(songs.SongsList[0], "youthfulbeautiful")) {
            this.addToBot(new GainBlockAction(p, p, 20));
            if(Objects.equals(songs.SongsList[1], "")){
                addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new StrengthPower((AbstractCreature) AbstractDungeon.player, 2), 2));
                addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, 2), 2));
            }
        }
        if (Objects.equals(songs.SongsList[0], "毫无活力")) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));

            }
            if(Objects.equals(songs.SongsList[1], "")) {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -2), -2));
                }
            }
        }
        if (Objects.equals(songs.SongsList[0], "碧天伴走(星)")) {
            addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, 3));
            if(Objects.equals(songs.SongsList[1], "")){
                this.addToBot(new GainEnergyAction(2));
                addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DrawCardNextTurnPower((AbstractCreature) AbstractDungeon.player, 2), 2));
            }
        }
        if (Objects.equals(songs.SongsList[0], "机械心")) {
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new ArtifactPower(AbstractDungeon.player,3), 3));
        }
        if (Objects.equals(songs.SongsList[0], "毁灭你，与你何干？") || Objects.equals(songs.SongsList[0], "拯救你，与你何干？") || Objects.equals(songs.SongsList[0], "我爱你，与你何干？")) {
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
                        addToBot((AbstractGameAction) new ApplyPowerAction(mo, (AbstractCreature) p, (AbstractPower) new ConstrictedPower((AbstractCreature) mo, p, 4), 4));
                    }
                }
            }

            //预见
            if(GuitarWhiteAnon.ifGirlReboot && GuitarWhiteAnon.ifWorldLegacy && GuitarWhiteAnon.ifWhiteMythology){
                this.addToBot(new ScryAction(5));
            }
        if (Objects.equals(songs.SongsList[0], "毁灭你，与你何干？")) {
            if(!p.hasPower(WhiteMythology.POWER_ID))
                addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ArtifactPower(p,1), 1));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new GirlsReboot((AbstractCreature)p), 1));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)p,1,false), 1));
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(var3.hasNext()) {
                AbstractMonster mo = (AbstractMonster)var3.next();
                if (!mo.isDead) {
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)mo, (AbstractPower)new WeakPower((AbstractCreature)mo,1,false), 1));
                }
            }
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p,  (heavy.count /2)+ 5) , (heavy.count /2)+ 5));
        }
        if (Objects.equals(songs.SongsList[0], "拯救你，与你何干？")) {
            for (Iterator<AbstractPower> s = p.powers.iterator(); s.hasNext(); ) {
                AbstractPower c = s.next();
                if (c.type == AbstractPower.PowerType.DEBUFF ){
                    s.remove();
                    break;
                }
            }
            for (Iterator<AbstractPower> s = p.powers.iterator(); s.hasNext(); ) {
                AbstractPower c = s.next();
                if (c.type == AbstractPower.PowerType.DEBUFF ){
                    s.remove();
                    break;
                }
            }
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new WorldLegacy((AbstractCreature)p), 1));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new RegenPower((AbstractCreature)p,4), 4));
            addToBot((AbstractGameAction) new GainEnergyAction(1));
        }
        if (Objects.equals(songs.SongsList[0], "我爱你，与你何干？")) {
            int randomNum2 = 1;
            if(p.hasPower(GirlsReboot.POWER_ID) && Shining.allCount != heavy.count){
                Random rand = new Random();
                int randomNum = rand.nextBoolean() ? 4 : 5;
                 randomNum2 = rand.nextBoolean() ? 0 : 1;
                addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, randomNum));
            }else {
                addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) p, 5));
            }

            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new WhiteMythology((AbstractCreature) p), 1));
            addToBot((AbstractGameAction) new dislocateAction((AbstractCreature) p, this));

        }

    }
        if (Objects.equals(songs.SongsList[0], "咫尺天涯的春日影")) {
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, 1, false),1));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        if (Objects.equals(songs.SongsList[0], "隔世遥望的春日影")) {
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
            this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 1), 1));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, 1, false),1));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
            }

        }
        if (Objects.equals(songs.SongsList[0], "魂牵梦萦的春日影")) {
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
            this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 1), 1));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, 1, false),1));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        if (Objects.equals(songs.SongsList[0], "以恋结缘")) {
            this.addToBot(new ApplyPowerAction(p, p, new KoiKouEnishi(p, 6), 6));
        }
        if (Objects.equals(songs.SongsList[0], "熙熙攘攘、我们的城市")) {
            this.addToBot(new ApplyPowerAction(p, p, new FlightPower(p, 1), 1));
        }
        if (Objects.equals(songs.SongsList[0], "空之箱")) {
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
        if (Objects.equals(songs.SongsList[0], "ときめきエクスペリエンス！")) {
            this.addToBot(new ApplyPowerAction(p, p, new Shining(p, 5), 5));
        }
        if (Objects.equals(songs.SongsList[0], "FIREBIRD")) {
            this.addToBot(new ApplyPowerAction(p, p, new NirvanaPower(p, 10), 10));
            this.addToBot(new ApplyPowerAction(p, p, new ForesightPower(p, 3), 3));
        }
        if (Objects.equals(songs.SongsList[0], "しゅわりん☆どり～みん")) {
           AbstractCard xiuwaxiuwa = new SelfRepair();
            this.addToBot(new MakeTempCardInHandAction(xiuwaxiuwa, 3));
        }
        if (Objects.equals(songs.SongsList[0], "吉他与孤独与蓝色星球")) {
            this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 2), 2));
        }
        if (Objects.equals(songs.SongsList[0], "卫星露天咖啡屋")) {
//            AbstractDungeon.player.gameHandSize+=2;
            BaseMod.MAX_HAND_SIZE+=2;

        }
}
}

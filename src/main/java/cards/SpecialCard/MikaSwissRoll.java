package cards.SpecialCard;
import ExcessiveFantasy.boss.TheConsciousnessOfTheWorld;
import OnStage.theEndKaren;
import actions.SkipEnemiesTurnFalseAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.core.Settings;
import monster.mika.mika;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import monster.ShoujoKageki.Karen;
import pathes.AbstractCardEnum;
import power.Karen.KarenShining;
import power.Karen.TheEndKarenShining;
import power.mika.MikaCharge;

import java.util.Iterator;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:46
 * 备注: 基础打击牌（初始牌）
 */
public class MikaSwissRoll extends CustomCard{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("MikaSwissRoll");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/card_Anon/mika/未花情人节巧克力_skill.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "MikassRoll";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public MikaSwissRoll() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Anon_COLOR, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.setBackgroundTexture("img/1024/mika_bg_skill_512.png","img/1024/mika_bg_skill_1024.png");
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(var3.hasNext()) {
//            AbstractMonster mo = (AbstractMonster)var3.next();
//            if (!mo.isDead && (mo.id.equals(mika.ID)))  {
//                if(mo.hasPower(MikaCharge.POWER_ID) && mo.getPower(MikaCharge.POWER_ID).amount < 6){
//                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction((AbstractMonster) mo));
//                    switch (mo.id) {
//                        case mika.ID:
//                            this.addToTop(new HealAction((AbstractCreature) mo, (AbstractCreature) mo, 30));
//                            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new MikaCharge((AbstractCreature) mo, 1), 1));
//                            break;
//                    }
//                }else{
//                    addToBot((AbstractGameAction)new SkipEnemiesTurnFalseAction());
//                    this.addToBot(new PressEndTurnButtonAction());
//                }
//            }
//        }

    }
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    public void triggerOnExhaust() {
        super.triggerOnExhaust();
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && (mo.id.equals(mika.ID)))  {
                if(mo.hasPower(MikaCharge.POWER_ID) && mo.getPower(MikaCharge.POWER_ID).amount < 6){
                    AbstractDungeon.actionManager.addToBottom(new RollMoveAction((AbstractMonster) mo));
                    switch (mo.id) {
                        case mika.ID:
                            this.addToTop(new HealAction((AbstractCreature) mo, (AbstractCreature) mo, 30));
                            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) mo, (AbstractPower) new MikaCharge((AbstractCreature) mo, 1), 1));
                            break;
                    }
                }else{
                    addToBot((AbstractGameAction)new SkipEnemiesTurnFalseAction());
                    this.addToBot(new PressEndTurnButtonAction());
                }

            }
        }
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new MikaSwissRoll();
    }
    @Override

    public boolean isStrike() {

        return true;
    }
    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

}

package actions;

import cards.uncommon.Pafe;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import power.Shining;

public class HopeBanishAction extends AbstractGameAction {
    private AbstractMonster m;

    private AbstractPlayer p;

    private DamageInfo info;

    private int magicNumber;

    private boolean Upgraded;

    public HopeBanishAction(boolean isUpgraded, AbstractPlayer p, AbstractMonster m,int magicnumber, DamageInfo info) {
        this.m = m;
        this.p = p;
        this.info = info;
        this.magicNumber = magicnumber;
        this.Upgraded = isUpgraded;
    }

    public void update() {
//        if(this.Upgraded){
            addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new Shining(p,this.magicNumber), this.magicNumber));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m,info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        }else {
//            addToBot((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new Shining(p,this.magicNumber), this.magicNumber));
//            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.magicNumber*2 , DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//
//        }

        this.isDone = true;
    }
}

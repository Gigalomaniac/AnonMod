package power.yuzu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

public class YUZUImagePower extends AbstractPower implements OnLoseTempHpPower {
    public static final String POWER_ID= "YUZUImagePower";
    private static final PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84= "img/newbuff/yuzu/default84.png";
    private static final String IMG_32="img/newbuff/yuzu/default32.png";
    public YUZUImagePower(AbstractCreature owner) {
        this.name=NAME;
        this.ID=POWER_ID;
        this.type=TYPE;
        this.owner=owner;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=-1;

        updateDescription();

    }


    @Override
    public int onLoseTempHp(DamageInfo damageInfo, int i) {
        if(TempHPField.tempHp.get(this.owner)<=i){
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if(!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)){
                        SmokeBomb bomb=new SmokeBomb();
                        bomb.use(null);
                    }
                    this.isDone=true;

                }
            });
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
        return i;
    }

    @Override
    public void updateDescription() {
        this.description=DESCRIPTIONS[0];
    }
}

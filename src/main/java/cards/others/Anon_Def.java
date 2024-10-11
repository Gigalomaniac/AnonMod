package cards.others;
import actions.RemoveNumDebuffsAction;
import actions.RestartAction;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:23
 * 备注: 基础的防御牌(初始牌)
 */
public class Anon_Def extends CustomCard
    {
        //从.json文件中提取键名为Strike_Seles的信息
        private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_Anon");
        public static final String NAME = cardStrings.NAME;
        public static final String DESCRIPTION = cardStrings.DESCRIPTION;
        private static final int COST = 1;
        private static final int BLOCK_AMT = 5;
        private static final int UPGRADE_PLUS_BLOCK = 3;
        public static final String ID = "Defend_Anon";
        public static final String IMG_PATH = "img/card_Anon/def_skill.png";

        //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
        public Anon_Def() {
            super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.SELF);
            //添加基础防御标签和将格挡设为5
            this.tags.add(BaseModCardTags.BASIC_DEFEND);
            this.baseBlock = BLOCK_AMT;
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            //使用卡牌时触发的动作
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));

        }

        @Override
        public AbstractCard makeCopy() {
            //复制卡牌时触发
            return (AbstractCard)new Anon_Def();
        }

        @Override
        public boolean isDefend() {
            //是否是最基础防御牌，
            return true;
        }

        @Override
        public void upgrade() {
            //卡牌升级后的效果
            if (!this.upgraded) {
                //更改名字和提高3点格挡
                upgradeName();
                upgradeBlock(UPGRADE_PLUS_BLOCK);
            }
        }
}

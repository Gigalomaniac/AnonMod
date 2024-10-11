package cards.others;
import basemod.abstracts.CustomCard;
import cards.token.*;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 创建人:谢文
 * 创建时间:2021/8/17 15:23
 * 备注: 基础的防御牌(初始牌)
 */
public class WhiteTrio extends CustomCard
    {
        //从.json文件中提取键名为Strike_Seles的信息
        private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WhiteTrio");
        public static final String NAME = cardStrings.NAME;
        public static final String DESCRIPTION = cardStrings.DESCRIPTION;
        private static final int COST = 0;
        public static final String ID = "WhiteTrio";
        public static final String IMG_PATH = "img/card_Anon/White/Anon tri_skill.png";
        public Color CorrodeBarColoRed = new Color(0.0F, 0F, 0F, 1.0F);
        //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
        public WhiteTrio() throws IllegalAccessException, NoSuchFieldException {
            super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.BASIC, CardTarget.NONE);
            //初始为3层活力
            this.baseMagicNumber = 1;
            this.magicNumber = this.baseMagicNumber;
            this.exhaust = false;
            this.selfRetain = false;
            this.setBackgroundTexture("img/pink/test/bg_skill_white_512.png","img/pink/test/bg_skill_white.png");

//            try{
//           Class parent = AbstractCard.class;
//           Field DeclaredField = parent.getDeclaredField("textColor");
//           Field field = parent.getDeclaredField("descBoxColor");
//           DeclaredField.setAccessible(true);
//           field.setAccessible(true);
//           DeclaredField.set(this,CorrodeBarColoRed);
//           field.set(this,CorrodeBarColoRed);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            } catch (NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            }




        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new GirlsReboot());
            stanceChoices.add(new WorldLegacy());
            stanceChoices.add(new WhiteMythology());
            if (this.upgraded)
                for (AbstractCard c : stanceChoices)
                    c.upgrade();
            addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
        }

        @Override
        public AbstractCard makeCopy() {
            //复制卡牌时触发
            try {
                return (AbstractCard)new WhiteTrio();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void upgrade() {
            //卡牌升级后的效果，升级后活力+2
            if (!this.upgraded) {
                upgradeName();
                this.selfRetain = true;
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                this.initializeDescription();
            }
        }

        public void initializeDescription() {
            super.initializeDescription();
            for (DescriptionLine tmp : this.description) {
                try {
                    Field text = tmp.getClass().getDeclaredField("text");
                    text.setAccessible(true);
                    String str = (String)text.get(tmp);
                    String updateTmp = "";
                    updateTmp = updateTmp + "[#000000]";
                    updateTmp = updateTmp + str;
//                    updateTmp = updateTmp + "[]";
                    text.set(tmp, updateTmp);
                } catch (NoSuchFieldException|IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

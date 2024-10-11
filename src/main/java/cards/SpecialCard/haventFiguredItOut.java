package cards.SpecialCard;

import Mod.AnonCardSignStrings;
import Mod.AnonMod;
import basemod.abstracts.CustomCard;
import cards.SpecialAnonCard;
import cards.token.Minute;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import pathes.AbstractCardEnum;


/**
 * 备注: 凝神，0费，技能牌，获得3层活力（升级后为5层）
 */
public class haventFiguredItOut extends SpecialAnonCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("haventFiguredItOut");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 0;
    public static final String ID = "haventFiguredItOut";
    public static final String IMG_PATH = "img/card_Anon/hualixiemu_skill.png";
//    private Texture img;;
    private Texture texture;
    private TextureRegion textureRegion;
//    private TextureRegion rotatedTextureRegion;
    private FrameBuffer fbo;
    protected String cardSign = null;
    private OrthographicCamera camera;
    Color StarShiningColor = new Color(1.0F, 1.0F, 1.0F, 0.7f);
    static float StarShining = 1f;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public haventFiguredItOut() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,  AbstractCardEnum.Anon_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardID=ID;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, CardHelper.getColor(255, 136, 153));
        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;

    }
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderCardSign(sb);
    }

    public void renderInLibrary(SpriteBatch sb) {
        super.renderInLibrary(sb);
        if (!SingleCardViewPopup.isViewingUpgrade || !this.isSeen || this.isLocked)
            renderCardSign(sb);
    }
    public void renderCardSign(SpriteBatch sb) { renderCardSign(sb, this.current_x, this.current_y, 400.0F, this.drawScale); }

    public void renderCardSign(SpriteBatch sb, float xPos, float yPos, float yOffsetBase, float scale) {
//        if (!AliceConfigHelper.enableSpellCardSignDisplay()) {
//            return;
//        }
        if (this.cardSign != null) {
            if (this.isFlipped || this.isLocked || this.transparency <= 0.0F) {
                return;
            }
            float offsetY = yOffsetBase * Settings.scale * scale / 2.0F;
            BitmapFont.BitmapFontData fontData = FontHelper.cardTitleFont.getData();
            float originalScale = fontData.scaleX;
            float scaleMultiplier = 0.8F;

            fontData.setScale(scaleMultiplier * scale * 0.85F);
            Color color = Settings.CREAM_COLOR.cpy();
            color.a = this.transparency;
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, this.cardSign, xPos, yPos, 0.0F, offsetY, this.angle, true, color);

            fontData.setScale(originalScale);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p, p, this.magicNumber, false));
        if (!this.upgraded) {
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        }else {
            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Minute(), this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new haventFiguredItOut();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.cardsToPreview = new Minute();
        }
    }
}

package cards;

import Mod.AnonCardSignStrings;
import Mod.AnonMod;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import utils.AnonSpireKit;

public class SpecialAnonCard extends CustomCard
        implements SpawnModificationCard {
    protected static final Color CYAN_COLOR = new Color(0.0F, 0.8F, 0.0F, 1.0F);

    protected String cardSign = null;

    public boolean cantBePlayed = false;

    public int tempHP = 0;
    public int baseTempHP = 0;

    public int secondaryMagicNumber = -1;
    public int baseSecondaryMagicNumber = -1;

    public boolean upgradedSecondaryMagicNumber = false;
    public boolean isSecondaryMagicNumberModified = false;
    public int secondaryDamage = -1;
    public int baseSecondaryDamage = -1;

    public boolean upgradedSecondaryDamage = false;

    public boolean isSecondaryDamageModified = false;
    public SpecialAnonCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, name,

                (img != null) ? img : (
                        (type == AbstractCard.CardType.ATTACK) ? AnonSpireKit.getCardImgFilePath("Attack") : (
                                (type == AbstractCard.CardType.SKILL) ? AnonSpireKit.getCardImgFilePath("Skill") :
                                        AnonSpireKit.getCardImgFilePath("Power"))), cost, rawDescription, type, color, rarity, target);

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, AnonMod.Anon_PUPPETEER_FLAVOR);

        AnonCardSignStrings sign = AnonCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

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
    public void renderCardSign(SpriteBatch sb) { renderCardSign(sb, this.current_x, this.current_y, 400.0F, this.drawScale); }
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderCardSign(sb);
    }
    public void renderInLibrary(SpriteBatch sb) {
        super.renderInLibrary(sb);
        if (!SingleCardViewPopup.isViewingUpgrade || !this.isSeen || this.isLocked)
            renderCardSign(sb);
    }

    public void upgradeSecondaryDamage(int amount) {
    }
}

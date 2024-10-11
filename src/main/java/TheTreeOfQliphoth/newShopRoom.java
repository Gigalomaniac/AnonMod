package TheTreeOfQliphoth;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.shop.Merchant;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.Iterator;

public class newShopRoom extends AbstractRoom {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public int shopRarityBonus = 6;
    public newMerchant merchant;

    public newShopRoom() {
        this.phase = AbstractRoom.RoomPhase.COMPLETE;
        this.merchant = null;
        this.mapSymbol = "$";
        this.mapImg = ImageMaster.MAP_NODE_MERCHANT;
        this.mapImgOutline = ImageMaster.MAP_NODE_MERCHANT_OUTLINE;
        this.baseRareCardChance = 9;
        this.baseUncommonCardChance = 37;
    }

    public void setMerchant(newMerchant merc) {
        this.merchant = merc;
    }

    public void onPlayerEntry() {
        if (!AbstractDungeon.id.equals("TheEnding")) {
            this.playBGM("SHOP");
        }

        AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
        this.setMerchant(new newMerchant());
    }

    public AbstractCard.CardRarity getCardRarity(int roll) {
        return this.getCardRarity(roll, false);
    }

    public void update() {
        super.update();
        if (this.merchant != null) {
            this.merchant.update();
        }
//        SpicyPurgePatches.additionalPurge = true;
        this.updatePurge();
    }

    private void updatePurge() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            ShopScreen.purgeCard();
            Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

            while(var1.hasNext()) {
                AbstractCard card = (AbstractCard)var1.next();
                CardCrawlGame.metricData.addPurgedItem(card.getMetricID());
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                AbstractDungeon.player.masterDeck.removeCard(card);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.shopScreen.purgeAvailable = false;
        }

    }

    public void render(SpriteBatch sb) {
        if (this.merchant != null) {
            this.merchant.render(sb);
        }

        super.render(sb);
        this.renderTips(sb);
    }

    public void dispose() {
        super.dispose();
        if (this.merchant != null) {
            this.merchant.dispose();
            this.merchant = null;
        }

    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ShopRoom");
        TEXT = uiStrings.TEXT;
    }
}

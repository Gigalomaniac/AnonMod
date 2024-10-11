package TheTreeOfQliphoth;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.Iterator;

public class newShopScreen extends ShopScreen {
    public void updatePurge() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            purgeCard();
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
}

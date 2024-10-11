package bossRoom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MonsterRoomMyBoss extends MonsterRoomBoss {
    private static final Logger logger = LogManager.getLogger(MonsterRoomBoss.class.getName());

    private MonsterGroup monsterGroup;

    public MonsterRoomMyBoss(MonsterGroup monsterGroup) {
        this.mapSymbol = "B";
        this.monsterGroup = monsterGroup;
    }

    public void onPlayerEntry() {
        this.monsters = monsterGroup;
        logger.info("BOSSES: " + AbstractDungeon.bossList.size());
        CardCrawlGame.metricData.path_taken.add("BOSS");
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.bossList.remove(0);
        if (this.monsters != null) {
            this.monsters.init();
        }

        waitTimer = 0.1F;
    }

    public AbstractCard.CardRarity getCardRarity(int roll) {
        return AbstractCard.CardRarity.RARE;
    }

}

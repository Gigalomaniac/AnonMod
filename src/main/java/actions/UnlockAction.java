package actions;

import cards.AbstractLockAnonCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;
import java.util.UUID;

public class UnlockAction extends AbstractGameAction {
    private boolean locked;
    private UUID uuid;
    public static final String unlock_IMG_PATH = "img/pink/test/bg_skill_white.png";
    public static final String unlock_IMG_PATH512 = "img/pink/test/bg_skill_white_512.png";
    public UnlockAction(UUID targetUUID, boolean iflocked, String unlock_IMG_PATH512) {
        this.locked = iflocked;
        this.uuid = targetUUID;
    }

    public void update() {
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

        AbstractLockAnonCard c;
        while(var1.hasNext()) {
            c = (AbstractLockAnonCard)var1.next();
            if (c.uuid.equals(this.uuid)) {
              c.locked = this.locked;

            }
        }

        this.isDone = true;
    }
}

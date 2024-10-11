package characters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Mod.AnonMod;

public class AnonAction extends AbstractGameAction {

    int Num;
    boolean spe;
    AbstractPlayer p;


    public AnonAction(int Num, boolean spe) {
        this.p = AbstractDungeon.player;
        this.Num = Num;
        this.spe = spe;
    }


    public void update() {
        if (this.spe && this.Num > 0) {
            if (AnonMod.corrode + this.Num > 5) {
                AnonMod.corrode = 5;
            } else {
                AnonMod.corrode += this.Num;
            }
        }

        if (this.p instanceof char_Anon) {
        }

        this.isDone = true;
    }
}

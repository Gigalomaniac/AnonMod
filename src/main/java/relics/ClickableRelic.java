package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public abstract class ClickableRelic extends CustomRelic {

    public boolean RclickStart;
    public boolean Rclick;
    public int price;

    public ClickableRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
        this.Rclick = false;
        this.RclickStart = false;
    }

    public ClickableRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id, texture, outline, tier, sfx);
        this.Rclick = false;
        this.RclickStart = false;
    }

    public ClickableRelic(String id, String imgName, RelicTier tier, LandingSound sfx) {
        super(id, imgName, tier, sfx);
        this.Rclick = false;
        this.RclickStart = false;
    }

    public void show() {
        this.flash();
    }

    public abstract void onRightClick();

    @Override
    public void onEquip() {
        super.onEquip();
//        IsaacMod.relics.remove(this.relicId);
//        IsaacMod.devilRelics.remove(this.relicId);
//        IsaacMod.devilOnlyRelics.remove(this.relicId);
    }

    @Override
    public void update() {
        super.update();
        if (this.RclickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Rclick = true;
            }
            this.RclickStart = false;
        }
        if ((this.isObtained) && (this.hb != null) && ((this.hb.hovered) && (InputHelper.justClickedRight))) {
            this.RclickStart = true;
        }
        if ((this.Rclick)) {
            this.Rclick = false;
            this.onRightClick();
        }
    }
}

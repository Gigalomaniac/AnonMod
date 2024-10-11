package bossRoom.effect;

import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
public abstract class AbstractChangeSceneEffect extends AbstractGameEffect {
    public void end() {}

    public void changeImg() {}

    public void changeImgBack() {}

    public abstract void update(float deltaTime);
}
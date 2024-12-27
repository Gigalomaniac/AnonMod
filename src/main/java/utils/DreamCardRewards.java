package utils;

import BossRewards.*;
import basemod.abstracts.CustomReward;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DreamCardRewards extends CustomReward {
    private static final Texture ICON = new Texture("img/UI/reward/RewardIcon.png");
    public int amount;
    //    private static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("tuner:ImaginaryReward")).TEXT;
private boolean half = true;
private float particleTimer;
    public  int  card = 0;
    public DreamCardRewards(int i) {
        super(ICON, "探寻记忆中的歌曲", RewardTypeEnum.REWARD);
        this.particleTimer = 0F;
        card = i;
        this.amount = i;
    }

    @Override
    public boolean claimReward() {
        this.cards.clear();
        switch (card){
            case 1:
                this.cards.add(new OurCitySongs());
                break;
            case 2:
                this.cards.add(new BocchiAndPlanetSongs());
                break;
            case 3:
                this.cards.add(new HifuuClubSongs());
                break;
            case 21:
                this.cards.add(new PoppinPartyRewardSongs());
                break;
            case 22:
                this.cards.add(new FIREBIRDSongs());
                break;
            case 23:
                this.cards.add(new xiuwaxiuwaSongs());
                break;
            case 31:
                this.cards.add(new KarenRewardCard());
                break;
            case 32:
                this.cards.add(new Mika());
                break;
            case 33:
                this.cards.add(new DustAnonRewards());
                break;
        }


        AbstractDungeon.cardRewardScreen.open(this.cards, this, "探索你的回忆");
        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        return false;
    }

    public void render(SpriteBatch sb){
        super.render(sb);
            particleTimer -= Gdx.graphics.getDeltaTime();
            if(half){
            if (particleTimer <= 0F) {
                half =false;
                particleTimer = 0.08F;
                float gravity = 180.0F * Settings.scale;
                    effects.add(new FireBurstParticleEffectCopyPaste(Color.WHITE.cpy(), this.hb.x + MathUtils.random(this.hb.width), this.hb.y, gravity));
                    effects.add(new FireBurstParticleEffectCopyPaste(Color.WHITE.cpy(), this.hb.x + MathUtils.random(this.hb.width), this.hb.y + this.hb.height, gravity));
//                }
//                effects.add(new FireBurstParticleEffectCopyPaste(Color.WHITE.cpy(), this.hb.x + this.hb.width, this.hb.y + MathUtils.random(this.hb.height), gravity));
//                effects.add(new FireBurstParticleEffectCopyPaste(Color.WHITE.cpy(), this.hb.x, this.hb.y + MathUtils.random(this.hb.height), gravity));
            }}
            else {
                half =true;
            }
        if(this.hb.hovered) {
            for(final AbstractGameEffect age : effects) {
                age.render(sb);
            }
        }

    }
}

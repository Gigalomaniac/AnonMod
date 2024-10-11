//package relics.chao.rewards;
//
//import basemod.ReflectionHacks;
//import basemod.abstracts.CustomReward;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.FontHelper;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.helpers.TipHelper;
//import com.megacrit.cardcrawl.helpers.input.InputHelper;
//import com.megacrit.cardcrawl.rewards.RewardItem;
//import com.megacrit.cardcrawl.rewards.RewardSave;
//import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
//import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
//import io.chaofan.sts.chaofanmod.relics.SpiritFire;
//import io.chaofan.sts.chaofanmod.utils.ChaofanModEnums;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//
//public class RubyKeyReward extends CustomReward {
//    public RubyKeyReward(RewardItem link) {
//        super((Texture) null, CardCrawlGame.languagePack.getRelicStrings(SpiritFire.ID).DESCRIPTIONS[3], ChaofanModEnums.CHAOFAN_MOD_RUBY_KEY);
//
//        this.img = ImageMaster.loadImage("images/relics/ruby_key.png");
//        this.outlineImg = ImageMaster.loadImage("images/relics/outline/ruby_key.png");
//        this.relicLink = link;
//        link.relicLink = this;
//    }
//
//    @Override
//    public boolean claimReward() {
//        if (!this.ignoreReward) {
//            AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.RED));
//        }
//
//        this.relicLink.isDone = true;
//        this.relicLink.ignoreReward = true;
//        this.img.dispose();
//        this.outlineImg.dispose();
//        return true;
//    }
//
//    @Override
//    public void render(SpriteBatch sb) {
//        if (this.hb.hovered) {
//            sb.setColor(new Color(0.4F, 0.6F, 0.6F, 1.0F));
//        } else {
//            sb.setColor(new Color(0.5F, 0.6F, 0.6F, 0.8F));
//        }
//
//        if (this.hb.clickStarted) {
//            sb.draw(ImageMaster.REWARD_SCREEN_ITEM, (float)Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale * 0.98F, Settings.scale * 0.98F, 0.0F, 0, 0, 464, 98, false, false);
//        } else {
//            sb.draw(ImageMaster.REWARD_SCREEN_ITEM, (float)Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 464, 98, false, false);
//        }
//
//        if (this.flashTimer != 0.0F) {
//            sb.setColor(0.6F, 1.0F, 1.0F, this.flashTimer * 1.5F);
//            sb.setBlendFunction(770, 1);
//            sb.draw(ImageMaster.REWARD_SCREEN_ITEM, (float)Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale * 1.03F, Settings.scale * 1.15F, 0.0F, 0, 0, 464, 98, false, false);
//            sb.setBlendFunction(770, 771);
//        }
//
//        sb.setColor(Color.WHITE);
//        Method renderKey = ReflectionHacks.getCachedMethod(RewardItem.class, "renderKey", SpriteBatch.class);
//        try {
//            renderKey.invoke(this, sb);
//        } catch (IllegalAccessException | InvocationTargetException ignored) {
//        }
//
//        if (this.hb.hovered && this.relicLink != null) {
//            TipHelper.renderGenericTip(360.0F * Settings.scale, (float) InputHelper.mY + 50.0F * Settings.scale, TEXT[7], TEXT[8] + FontHelper.colorString(this.relicLink.relic.name + TEXT[9], "y"));
//        }
//
//        Color color;
//        if (this.hb.hovered) {
//            color = Settings.GOLD_COLOR;
//        } else {
//            color = Settings.CREAM_COLOR;
//        }
//
//        if (this.redText) {
//            color = Settings.RED_TEXT_COLOR;
//        }
//
//        float REWARD_TEXT_X = (float)Settings.WIDTH * 0.434F;
//        FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, this.text, REWARD_TEXT_X, this.y + 5.0F * Settings.scale, 1000.0F * Settings.scale, 0.0F, color);
//        if (!this.hb.hovered) {
//            for (AbstractGameEffect e : ReflectionHacks.<ArrayList<AbstractGameEffect>>getPrivate(this, RewardItem.class, "effects")) {
//                e.render(sb);
//            }
//        }
//
//        if (Settings.isControllerMode) {
//            this.renderReticle(sb, this.hb);
//        }
//
//        Method renderRelicLink = ReflectionHacks.getCachedMethod(RewardItem.class, "renderRelicLink", SpriteBatch.class);
//        try {
//            renderRelicLink.invoke(this, sb);
//        } catch (IllegalAccessException | InvocationTargetException ignored) {
//        }
//
//        this.hb.render(sb);
//    }
//
//    public static CustomReward load(RewardSave rewardSave) {
//        // FIXME created PR to basemod to keep order of saved rewards
//        ArrayList<RewardItem> rewards = AbstractDungeon.getCurrRoom().rewards;
//        RewardItem item = rewards
//                .stream()
//                .filter(r -> r.relic != null && r.relic.relicId.equals(rewardSave.id))
//                .findFirst()
//                .orElse(null);
//        if (item != null) {
//            return new RubyKeyReward(item);
//        } else {
//            return new RubyKeyReward(rewards.get(rewards.size() - 1));
//        }
//    }
//
//    public static RewardSave save(CustomReward customReward) {
//        return new RewardSave(ChaofanModEnums.CHAOFAN_MOD_RUBY_KEY.toString(), customReward.relicLink.relic.relicId, 0, 0);
//    }
//}

//package relics.chao.rewards;
//
//import basemod.abstracts.CustomReward;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.rewards.RewardSave;
//import io.chaofan.sts.chaofanmod.relics.SpiritFire;
//import io.chaofan.sts.chaofanmod.utils.ChaofanModEnums;
//
//public class HealReward extends CustomReward {
//    public HealReward(int amount) {
//        super(ImageMaster.TP_HP, String.format(CardCrawlGame.languagePack.getRelicStrings(SpiritFire.ID).DESCRIPTIONS[4], amount), ChaofanModEnums.CHAOFAN_MOD_HEAL);
//        this.goldAmt = amount;
//    }
//
//    @Override
//    public boolean claimReward() {
//        AbstractDungeon.player.heal(goldAmt, true);
//        return true;
//    }
//
//    public static CustomReward load(RewardSave rewardSave) {
//        return new HealReward(rewardSave.amount);
//    }
//
//    public static RewardSave save(CustomReward customReward) {
//        return new RewardSave(ChaofanModEnums.CHAOFAN_MOD_HEAL.toString(), null, customReward.goldAmt, 0);
//    }
//}

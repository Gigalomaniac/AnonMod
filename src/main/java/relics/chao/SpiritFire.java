//package relics.chao;
//
//import basemod.ReflectionHacks;
//import basemod.abstracts.CustomRelic;
//import basemod.abstracts.CustomSavable;
//import com.badlogic.gdx.graphics.Texture;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.helpers.MonsterHelper;
//import com.megacrit.cardcrawl.rewards.RewardItem;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//import com.megacrit.cardcrawl.rooms.CampfireUI;
//import com.megacrit.cardcrawl.rooms.RestRoom;
//import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
//
////import relics.chao.rewards.HealReward;
////import relics.chao.rewards.RubyKeyReward;
//
//
//import java.util.ArrayList;
//
//
//public class SpiritFire extends CustomRelic implements CustomSavable<SpiritFire.Usage> {
//    public static boolean isInCampfireCombat = false;
//    public static final String ID = "relic.SpiritFire";
//    private static final String IMG = "img/relics/InnerFavillae.png";
//    private static final Texture COMBAT = ImageMaster.loadImage("img/relics/fire/combat.png");
//
//    private Usage usage = new Usage();
//
//    public SpiritFire() {
//        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.MAGICAL);
//    }
//
//    @Override
//    public String getUpdatedDescription() {
//        return DESCRIPTIONS[0];
//    }
//
//    @Override
//    public boolean canUseCampfireOption(AbstractCampfireOption option) {
//        if (option instanceof CombatOption) {
//            return AbstractDungeon.actNum < 4 && !usage.usedInActs[AbstractDungeon.actNum];
//        }
//
//        return super.canUseCampfireOption(option);
//    }
//
//    @Override
//    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
//        isInCampfireCombat = false;
//        options.add(new CombatOption());
//    }
//
//    @Override
//    public Usage onSave() {
//        return this.usage;
//    }
//
//    @Override
//    public void onLoad(Usage usage) {
//        if (usage != null) {
//            this.usage = usage;
//        }
//    }
//
//    @Override
//    public void onEnterRoom(AbstractRoom room) {
//        ThirdPerspectiveViewPatches.setEnable(false);
//    }
//
//    @Override
//    public boolean canSpawn() {
//        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
//    }
//
//    public class CombatOption extends AbstractCampfireOption {
//        private boolean setRewards = true;
//
//        public CombatOption() {
//            this.label = DESCRIPTIONS[1];
//            this.description = DESCRIPTIONS[2];
//            this.img = COMBAT;
//        }
//
//        public void useOption() {
//            isInCampfireCombat = true;
//            usage.usedInActs[AbstractDungeon.actNum] = true;
//
//            AbstractRoom room = AbstractDungeon.getCurrRoom();
//            if (setRewards) {
////                room.rewards.add(new HealReward((int) Math.ceil(AbstractDungeon.player.maxHealth * 0.3)));
//                room.addRelicToRewards(returnRandomRelicTier());
//
////                if (Settings.isFinalActAvailable && !Settings.hasRubyKey) {
////                    ArrayList<RewardItem> rewards = room.rewards;
////                    RewardItem lastReward = rewards.get(rewards.size() - 1);
////                    rewards.add(new RubyKeyReward(lastReward));
////                }
//            }
//
//            room.monsters = MonsterHelper.getEncounter(SpiritFireMonster.ID);
//            AbstractDungeon.lastCombatMetricKey = SpiritFireMonster.ID;
//            room.phase = AbstractRoom.RoomPhase.COMBAT;
//            room.monsters.init();
//            AbstractRoom.waitTimer = 0.1F;
//            AbstractDungeon.player.preBattlePrep();
//
//            ThirdPerspectiveViewPatches.setEnable(true);
//        }
//
//        @Override
//        public void update() {
//            super.update();
//
//            AbstractRoom room = AbstractDungeon.getCurrRoom();
//            if (room.isBattleOver && room instanceof RestRoom && room.phase == AbstractRoom.RoomPhase.INCOMPLETE) {
//                setRewards = false;
//                this.useOption();
//                CampfireUI campfireUI = ((RestRoom) room).campfireUI;
//                campfireUI.somethingSelected = true;
//                campfireUI.touchOption = null;
//                AbstractDungeon.player.animX = 0;
//                ReflectionHacks.setPrivate(campfireUI, CampfireUI.class, "charAnimTimer", 0f);
//            }
//        }
//
//        private RelicTier returnRandomRelicTier() {
//            int roll = AbstractDungeon.relicRng.random(0, 99);
//
//            if (roll < 50) {
//                return RelicTier.COMMON;
//            }
//            if (roll > 82) {
//                return RelicTier.RARE;
//            }
//
//            return RelicTier.UNCOMMON;
//        }
//    }
//
//    static class Usage {
//        boolean[] usedInActs = new boolean[5];
//    }
//}

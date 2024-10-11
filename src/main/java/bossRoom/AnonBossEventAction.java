package bossRoom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import utils.Invoker;

import java.util.ArrayList;

public class AnonBossEventAction extends AbstractGameAction {
    private boolean hasBuilt = false;
    private AnonSide AnonSide;

    public  int event = 0;

    public AnonBossEventAction(AnonSide AnonSide) {
        this.AnonSide = AnonSide;
    }

    public void update() {
        GenericEventDialog var10000;
        if (!this.hasBuilt) {
            var10000 = this.AnonSide.imageEventText;
            GenericEventDialog.show();
            this.hasBuilt = true;
//            RenderHandPatch.plsDontRenderHand = true;
            AbstractDungeon.overlayMenu.hideCombatPanels();

            if (Settings.language == Settings.GameLanguage.ZHS) {
                this.AnonSide.imageEventText.setDialogOption("进入房间……");
            }else {
                this.AnonSide.imageEventText.setDialogOption("Enter the room……");
            }
        }
        AnonSide.imageEventText.update();
        if (!GenericEventDialog.waitForInput && GenericEventDialog.getSelectedOption() == 0) {
            AnonSide.imageEventText.loadImage("img/event/hospitalIn_600.png");
            if (Settings.language == Settings.GameLanguage.ZHS) {
                AnonSide.imageEventText.show(titleText[0], bodyText2[0]);
            }else {
                AnonSide.imageEventText.show(titleText[1], bodyText2[1]);
            }
            if(event == 0){
                AnonSide.imageEventText.optionList.get(0).slot = 5;
                AnonSide.imageEventText.setDialogOption("……");
                event = 1;
            }

        }
        if (GenericEventDialog.getSelectedOption() == 1){
            if(event == 1) {
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AnonSide.imageEventText.show(titleText[0], bodyText3[0]);
                }else {
                    AnonSide.imageEventText.show(titleText[1], bodyText3[1]);
                }
                AnonSide.imageEventText.optionList.get(1).slot = 6;
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    AnonSide.imageEventText.setDialogOption("“ #b……我自己 ”");
                }else {
                    AnonSide.imageEventText.setDialogOption("“ #b……MyselF ”");
                }
                event = 2;
            }
            this.isDone = false;
        }

        if (GenericEventDialog.getSelectedOption() == 2){
            AnonSide.ifMusic = true;
            AnonSide.imageEventText.clear();
            this.isDone = true;
            CardCrawlGame.music.playTempBgmInstantly("03. 迷星叫 -Instrumental-.mp3", true);
        }
    }

    private static String titleText[] = {
            "千早爱音的觉悟",
            "Anon's determination",
    };

    private static String bodyText2[] = {
            "“爱音，以前总是觉得你烦，可是现在这里真的好安静”"+" NL " +
                    "“不知道你现在能不能听到我说的话，但是我们现在都在这里等着你回来”"+ " NL " +
                    "“医生说你只是没有意识到自己在做梦，只要多和你说说话就更容易恢复”"+ " NL "+
                    "“爱音……为什么不愿意呢……”"+ " NL "+
                    "“………………”"+" NL "+
                    "“……我来给你讲个故事吧"+" NL "+
                    "故事名字就叫做……" +" NL "+
                    " #b千早爱音的冒险 ”",
            "Anon, I always thought you were annoying before, but it's really quiet here now." + " NL " +
                    "I don't know if you can hear what I'm saying now, but we are all here waiting for you to come back." + " NL " +
                    "The doctor said you're just not aware that you're dreaming; talking to you more will help you recover more easily." + " NL " +
                    "Anon... why are you unwilling... " + " NL " +
                    "..." + " NL " +
                    "... Let me tell you a story." + " NL " +
                    "The story is called... " + " NL " +
                    " The Adventure of Chihaya Anon "
    };

    private static String bodyText3[] = {
            "某一天，爱音在塔底醒来，带着她随身的吉他和手机，踏上了冒险 NL "+
                    "旅途中，她遇到了来帮助她的 NL " +
                    friendsName()  + " NL " +
                    "抵达最深处时，她才发现，大家原来一直在她的身边 NL " +
                    "只剩下最后一步了…… NL " +
                    "最后的敌人只剩下，那个…… NL ",
            "One day, Aiyin woke up at the bottom of the tower, with her guitar and phone, and set off on an adventure." + " NL "
                    + "On her journey, she met those who came to help her, Tom, Jerry, and Alice." + " NL "
                    + "When she reached the deepest part, she realized that everyone had always been by her side." + " NL "
                    + "There is only one last step left... " + " NL "
                    + "The final enemy left is just that..."
    };
    private static String friendsName() {
        String name = "";
        if (AbstractDungeon.player.hasRelic("Tomori_relic")) {
            name = name + " #y小灯， ";
        }
        if (AbstractDungeon.player.hasRelic("Soyorin_relic")) {
            name = name + "  #y爽世， ";
        }
        if (AbstractDungeon.player.hasRelic("Rikki_relic")) {
            name = name + "  #y立希， ";
        }
        if (AbstractDungeon.player.hasRelic("Rana_relic")) {
            name = name + "  #y乐奈， ";
        }
        if (AbstractDungeon.player.hasRelic("Sakiko_relic")) {
            name = name + "  #y小祥， ";
        }
        if (AbstractDungeon.player.hasRelic("Mutsumi_relic")) {
            name = name + "  #y小睦， ";
        }
        return  name.substring(0,name.length());
    }
}

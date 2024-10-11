package bossRoom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;

public class CrychicBossEventAction extends AbstractGameAction {
    private boolean hasBuilt = false;
    private CrychicSide CrychicSide;

    public  int event = 0;

    public CrychicBossEventAction(CrychicSide CrychicSide) {
        this.CrychicSide = CrychicSide;
    }

    public void update() {
        GenericEventDialog var10000;
        if (!this.hasBuilt) {
            var10000 = this.CrychicSide.imageEventText;
            GenericEventDialog.show();
            this.hasBuilt = true;
//            RenderHandPatch.plsDontRenderHand = true;
            AbstractDungeon.overlayMenu.hideCombatPanels();
            this.CrychicSide.imageEventText.setDialogOption("……");
        }
        CrychicSide.imageEventText.update();
        if (!GenericEventDialog.waitForInput && GenericEventDialog.getSelectedOption() == 0) {
            CrychicSide.imageEventText.loadImage("img/event/hospitalIn_600.png");
            if (Settings.language == Settings.GameLanguage.ZHS) {
                CrychicSide.imageEventText.show(titleText[0], bodyText2[0]);
            }else {
                CrychicSide.imageEventText.show(titleText[1], bodyText2[1]);
            }
            if(event == 0){
                CrychicSide.imageEventText.optionList.get(0).slot = 5;
                CrychicSide.imageEventText.setDialogOption("……");
                event = 1;
            }

        }
        if (GenericEventDialog.getSelectedOption() == 1){
            if(event == 1) {
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    CrychicSide.imageEventText.show(titleText[0], bodyText3[0]);
                }else {
                    CrychicSide.imageEventText.show(titleText[1], bodyText3[1]);
                }
                CrychicSide.imageEventText.optionList.get(1).slot = 6;
                if (Settings.language == Settings.GameLanguage.ZHS) {
                    CrychicSide.imageEventText.setDialogOption("“ #b等着我，祥子！ ”");
                }else {
                    CrychicSide.imageEventText.setDialogOption("“ Waiting For Me，Sakiko! ”");
                }
                event = 2;
            }
            this.isDone = false;
        }

        if (GenericEventDialog.getSelectedOption() == 2){
            CardCrawlGame.music.playTempBgmInstantly("CRYCHIC - 春日影.mp3", true);
            CrychicSide.ifMusic = true;
            CrychicSide.imageEventText.clear();
            this.isDone = true;
        }
//        if (!GenericEventDialog.waitForInput && !this.isDone) {
//            GenericEventDialog var10001 = this.CrychicSide.imageEventText;
////            this.CrychicSide.currentEvent.buttonEffect(GenericEventDialog.getSelectedOption());
////            RenderHandPatch.plsDontRenderHand = false;
//            var10000 = this.CrychicSide.imageEventText;
//            GenericEventDialog.hide();
//            AbstractDungeon.overlayMenu.showCombatPanels();
//            this.isDone = true;
//        }

    }

    private static String titleText[] = {
            " Crychic的阴影 ",
            " Crychic Shadow "
    };

    private static String bodyText2[] = {
            "“曾经那个想要离开的她，原来比谁都执着与那个乐队吗…………”"+" NL " +
                    "“小祥……为什么不愿意呢……”"+ " NL "+
                    "“………………”"+" NL "+
                    "“这次轮到你来给我们讲个故事了"+" NL "+
                    "故事名字就叫做……" +" NL "+
                    " #bCrychic的回忆 ”",
            "Did she, who once wanted to leave, actually cling more to that band than anyone else?..." + " NL " +
                    "Xiao Xiang... why are you unwilling?..." + " NL " +
                    "..." + " NL " +
                    "It's your turn to tell us a story now." + " NL " +
                    "The story is called..." + " NL " +
                    "#bMemories of Crychic"
    };

    private static String bodyText3[] = {
            "某一天，爱音在塔底醒来，带着她随身的吉他和手机，踏上了寻找丰川祥子的冒险 NL "+
                    "旅途中，大家一直陪伴在她的身边， NL " +
                    friendsName()  + " NL " +
                    "现在，终于就差最后一步了，将那个 #b迷路的孩子 带回来吧"+ " NL " +
                    "全部都交给你了， #r爱音 ！ NL ",
            "One day, Aiyin woke up at the bottom of the tower, with her guitar and phone by her side, and embarked on an adventure to find Toyokawa Shoko." + " NL " +
                    "Throughout the journey, everyone has been accompanying her, " + friendsName() + " NL " +
                    "Now, it's finally just one last step to bring back the #blost child" + " NL " +
                    "It's all up to you, #rAnon! " + " NL "
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

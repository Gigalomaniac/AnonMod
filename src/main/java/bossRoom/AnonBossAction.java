package bossRoom;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.audio.MainMusic;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import utils.Invoker;

import java.util.ArrayList;



/**
 * Created by aqiod on 2019/3/22.
 */
public class AnonBossAction extends AbstractGameAction {

    private boolean hasBuilt = false;
    public AnonSide anonSide;
    public  int event = 0;
    public AnonBossAction(AnonSide anonSide) {
        this.anonSide = anonSide;
    }





public void update() {
//        if (!this.hasBuilt) {
//            GenericEventDialog.show();
//            this.hasBuilt = true;
//        }

        anonSide.imageEventText.update();
        if (!GenericEventDialog.waitForInput && GenericEventDialog.getSelectedOption() == 0) {
            anonSide.imageEventText.loadImage("img/event/hospitalIn_600.png");
            anonSide.imageEventText.show(titleText[0], bodyText2[0]);
            if(event == 0){
                anonSide.imageEventText.optionList.get(0).slot = 5;
                anonSide.imageEventText.setDialogOption("……");
                event = 1;
            }

        }
        if (GenericEventDialog.getSelectedOption() == 1){
            if(event == 1) {
                anonSide.imageEventText.show(titleText[0], bodyText3[0]);
                anonSide.imageEventText.optionList.get(1).slot = 6;
                anonSide.imageEventText.setDialogOption("……我自己");
                event = 2;
            }
            this.isDone = false;
        }

        if (GenericEventDialog.getSelectedOption() == 2){
            CardCrawlGame.music.playTempBgmInstantly("03. 迷星叫 -Instrumental-.mp3", true);
            anonSide.ifMusic = true;
            anonSide.imageEventText.clear();
            this.isDone = true;
        }
    }

    private static String titleText[] = {
            "千早爱音的觉悟",
    };

    private static String bodyText2[] = {
            "“爱音，以前总是觉得你烦，可是现在这里真的好安静”"+" NL " +
            "“不知道你现在能不能听到我说的话，但是我们现在都在这里等着你回来”"+ " NL " +
            "“医生说你只是没有意识到自己在做梦，只要多和你说说话就更容易恢复”"+ " NL "+
            "“爱音……为什么不愿意呢……”"+ " NL "+
            "“………………”"+" NL "+
            "“……我来给你讲个故事吧"+" NL "+
            "故事名字就叫，千早爱音的冒险”"
    };

    private static String bodyText3[] = {
            "某一天，爱音在塔底醒来，带着她随身的吉他和手机，踏上了冒险 NL "+
            "旅途中，她遇到了来帮助她的 NL " +
                    friendsName()  + " NL " +
            "抵达最深处时，她才发现，大家原来一直在她的身边 NL " +
            "只剩下最后一步了…… NL " +
            "最后的敌人只剩下，那个…… NL "
    };
    private static String friendsName() {
        String name = "";
        if (AbstractDungeon.player.hasRelic("Tomori_relic")) {
            name = name + " 小灯，";
        }
        if (AbstractDungeon.player.hasRelic("Soyorin_relic")) {
            name = name + " 爽世，";
        }
        if (AbstractDungeon.player.hasRelic("Rikki_relic")) {
            name = name + " 立希，";
        }
        if (AbstractDungeon.player.hasRelic("Rana_relic")) {
            name = name + " 乐奈，";
        }
        if (AbstractDungeon.player.hasRelic("Sakiko_relic")) {
            name = name + " 小祥，";
        }
        if (AbstractDungeon.player.hasRelic("Mutsumi_relic")) {
            name = name + " 小睦，";
        }
        return  name.substring(0,name.length());
    }
}

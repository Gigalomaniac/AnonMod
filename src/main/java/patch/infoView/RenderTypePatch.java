package patch.infoView;
import BossRewards.BocchiAndPlanetSongs;
import BossRewards.KarenRewardCard;
import BossRewards.Mika;
import BossRewards.OurCitySongs;
import Mod.AnonMod;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderFixSwitches;
import cards.AbstractLockAnonCard;
import cards.FullImgCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import java.lang.reflect.Field;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class RenderTypePatch
{
  private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardType");
  public static final String[] TEXT = uiStrings.TEXT;




  @SpirePatch(clz = AbstractCard.class, method = "renderType")
  public static class renderType
  {
    @SpireInsertPatch(rloc = 0)
    public static SpireReturn Insert(AbstractCard _inst, SpriteBatch sb) {
        if (_inst instanceof FullImgCard) {
            return SpireReturn.Return(null);
        }
      if (_inst instanceof AbstractLockAnonCard) {
        BitmapFont font = FontHelper.cardTypeFont;
        font.getData().setScale(_inst.drawScale);

               if(!AnonMod.saves.getBool("MachineHeartLocked")){
                   ((AbstractLockAnonCard) _inst).unlockInfo();
                       }

      return SpireReturn.Return(null);
    }
    return SpireReturn.Continue();
  }
}



@SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
public static class renderCardTypeText
{
  @SpireInsertPatch(rloc = 0)
  public static SpireReturn Insert(SingleCardViewPopup _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
    Field card = _inst.getClass().getDeclaredField("card");
    card.setAccessible(true);
      if (card.get(_inst) instanceof FullImgCard) {
        return SpireReturn.Return(null);
    }
    if (card.get(_inst) instanceof AbstractLockAnonCard) {
        FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, RenderTypePatch.TEXT[0], Settings.WIDTH / 2.0F + 3.0F * Settings.scale, Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, new Color(0.35F, 0.35F, 0.35F, 1.0F));

  if(!((AbstractLockAnonCard) card.get(_inst)).locked){
      ((AbstractLockAnonCard) card.get(_inst)).unlockInfo();
  }
        if(!AnonMod.saves.getBool("MachineHeartLocked")){
            ((AbstractLockAnonCard) card.get(_inst)).unlockInfo();
        }

         return SpireReturn.Return(null);
       }
       return SpireReturn.Continue();
     }
   }


    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderDescriptionCN")
    public static class renderDescriptionCN
    { @SpireInsertPatch(rloc = 0)
    public static SpireReturn Insert(SingleCardViewPopup _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
        Field card = _inst.getClass().getDeclaredField("card");
        card.setAccessible(true);
        if(card.get(_inst) instanceof FullImgCard){
//            if (isInstanceOfAny(card.get(_inst), cardClass)) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderDescriptionCN")
    public static class AbstractCardrenderDescriptionCN {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn Insert(AbstractCard _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
//        Field card = _inst.getClass().getDeclaredField("card");
//        card.setAccessible(true);
                if (_inst instanceof FullImgCard) {
                    return SpireReturn.Return(null);
                }
                return SpireReturn.Continue();
            }

    }


//    @SpirePatch(clz = RenderFixSwitches.class, method = "renderHelper", paramtypez = {AbstractCard.class,SpriteBatch.class,Color.class,TextureAtlas.AtlasRegion.class,float.class,float.class})
//    public static class PatchRenderHelperA {
//        public static float ChangeTime= 1f;
//        public static Color ChangeColor= Color.WHITE;
//        @SpirePrefixPatch
//        public static SpireReturn<Void> PatchRenderHelper(RenderFixSwitches _inst, AbstractCard card, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion region, float xPos, float yPos) throws NoSuchFieldException, IllegalAccessException {
//            ChangeTime += Gdx.graphics.getDeltaTime();
//            System.out.println(ChangeTime);
//            if(ChangeTime <=1) {
//                if (ChangeTime < 0.5f) {
//                    ChangeColor.a = (1 - ChangeTime) * 2;
//                    sb.setColor(ChangeColor);
//                }else {
//                    ChangeColor.a = (ChangeTime) * 2;
//                    sb.setColor(ChangeColor);
//                }
//            }
//            return SpireReturn.Continue();
//        }
//
//    }


 }

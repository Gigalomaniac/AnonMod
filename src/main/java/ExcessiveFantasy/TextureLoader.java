/*    */ package ExcessiveFantasy;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.utils.GdxRuntimeException;
/*    */ import java.util.HashMap;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class TextureLoader
/*    */ {
/* 11 */   public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());
/* 12 */   private static HashMap<String, Texture> textrues = new HashMap();
/*    */   
/*    */   public static Texture getTexture(String id) {
/* 15 */     if (textrues.get(id) == null) {
/*    */       try {
/* 17 */         loadTexture(id);
/* 18 */       } catch (GdxRuntimeException e) {
/* 19 */         logger.error("Could not find Texture " + id);
/* 20 */         return getTexture("img/relics/image.png");
/*    */       } 
/*    */     }
/* 23 */     return (Texture)textrues.get(id);
/*    */   }
/*    */   
/*    */   private static void loadTexture(String id) throws GdxRuntimeException {
/* 27 */     logger.info("BATwinsMod:LoadTexture" + id);
/* 28 */     Texture texture = new Texture(id);
/* 29 */     texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/* 30 */     textrues.put(id, texture);
/*    */   }
/*    */ }


/* Location:              F:\BlueArchive_SaibaSisters_Mod.jar!\baModDeveloper\helpers\TextureLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
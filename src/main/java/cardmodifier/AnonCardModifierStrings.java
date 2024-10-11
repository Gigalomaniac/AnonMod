/*    */ package cardmodifier;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AnonCardModifierStrings
/*    */ {
/*    */   public String NAME;
/*    */   public String DESCRIPTION;
/* 10 */   public String[] EXTENDED_DESCRIPTION = null;
/*    */   
/* 12 */   static Map<String, AnonCardModifierStrings> strings = null;
/*    */ 
/*    */   
/* 15 */   public static void init(Map<String, AnonCardModifierStrings> strings) { AnonCardModifierStrings.strings = strings; }
/*    */ 
/*    */   
/*    */   public static AnonCardModifierStrings get(String id) {
/* 19 */     if (!strings.containsKey(id)) {
/* 20 */
/*    */     }
/* 22 */     return (AnonCardModifierStrings)strings.get(id);
/*    */   }
/*    */ }


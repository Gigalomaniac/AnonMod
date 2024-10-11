/*    */ package cardmodifier;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public abstract class AnonMiscKit
/*    */ {
/*  7 */   public static <T> boolean arrayContains(T[] array, T value) { return Arrays.asList(array).contains(value); }
/*    */ 
/*    */   
/*    */   public static <T> T[] reversedArray(T[] array) {
/* 11 */     T[] res = (T[])Arrays.copyOf(array, array.length);
/* 12 */     for (int i = 0; i < res.length / 2; i++) {
/* 13 */       T tmp = res[i];
/* 14 */       res[i] = res[res.length - i - 1];
/* 15 */       res[res.length - i - 1] = tmp;
/*    */     } 
/* 17 */     return res;
/*    */   }
/*    */   
/*    */   public static String join(String... strings) {
/* 21 */     StringBuilder sb = new StringBuilder();
/* 22 */     for (String s : strings) {
/* 23 */       sb.append(s);
/* 24 */       sb.append(" ");
/*    */     } 
/* 26 */     return sb.toString().trim();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\star\AliceMargatroid (3).jar!\rs\antileaf\alic\\utils\AliceMiscKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
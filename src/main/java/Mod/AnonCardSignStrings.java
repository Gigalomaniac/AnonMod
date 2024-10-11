package Mod;

import java.util.Map;
import org.jetbrains.annotations.Nullable;


public class AnonCardSignStrings
{
  public static String DEFAULT_TITLE;
  public String SIGN;
  public String[] EXTENDED_DESCRIPTION = null;

  static Map<String, AnonCardSignStrings> strings = null;


  public static void init(Map<String, AnonCardSignStrings> strings) { AnonCardSignStrings.strings = strings; }


  @Nullable
  public static AnonCardSignStrings get(String id) {
    if (!strings.containsKey(id))
      return null;
    return (AnonCardSignStrings)strings.get(id);
  }
}



package sud.indepth.intellibuild.util;

import java.util.List;

/**
 * Created by sud on 14/4/19.
 */
public class Printer {
  public static void println(Object message) {
    System.out.println(message);
  }

  public static void println(List<? extends Object> objects) {
    objects.forEach(System.out::println);
  }
}

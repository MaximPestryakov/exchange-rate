import api.FixerApi;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {

  public static void main(String[] args) {
    Currency base = new Currency(System.in);
    Currency symbols = new Currency(System.in);

    while (!base.hasCurrency()) {
      System.out.println("Enter from currency:");
      try {
        base.read();
      } catch (InputMismatchException e) {
        System.out.println("Wrong format!");
      }
    }
    while (!symbols.hasCurrency()) {
      System.out.println("Enter to currency:");
      try {
        symbols.read();
      } catch (InputMismatchException e) {
        System.out.println("Wrong format!");
      }
    }
    try {
      new FixerApi().getLatest(base.toString(), symbols.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

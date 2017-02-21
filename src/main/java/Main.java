import api.FixerApi;
import java.util.InputMismatchException;
import util.Progress;

public class Main {

  private Main() {
  }

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
    Progress progress = new Progress();
    progress.start();
    FixerApi.getInstance()
        .getLatest(base.toString(), symbols.toString())
        .thenAccept(data -> {
          String date = data.getDate();
          double rate = data.getRate();
          progress.stop();
          System.out.printf("The exchange rate for %s is %f\n", date, rate);
        });
  }
}

import api.FixerApi;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import util.Progress;

public class Main {

  private static final ResourceBundle res = ResourceBundle.getBundle("Strings");

  private Main() {
  }

  public static void main(String[] args) {

    Currency base = new Currency(System.in);
    Currency symbols = new Currency(System.in);

    while (!base.hasCurrency()) {
      System.out.println(res.getString("enter_from_currency"));
      try {
        base.read();
      } catch (InputMismatchException e) {
        System.out.println(res.getString("wrong_format"));

      }
    }
    while (!symbols.hasCurrency()) {
      System.out.println(res.getString("enter_to_currency"));

      try {
        symbols.read();
      } catch (InputMismatchException e) {
        System.out.println(res.getString("wrong_format"));

      }
    }
    Progress progress = new Progress();
    progress.start();
    FixerApi.getInstance()
        .getLatest(base.toString(), symbols.toString())
        .thenAccept(data -> {
          progress.stop();
          if (data == null) {
            return;
          }
          String date = data.getDate();
          double rate = data.getRate();
          System.out.printf(res.getString("rate_output_format"), date, rate);
        });
  }
}

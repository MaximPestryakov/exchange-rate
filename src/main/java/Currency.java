import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Currency {

  private static final List<String> CURRENCY_STINGS = Arrays
      .asList("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK",
          "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON",
          "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR");

  private int index = -1;

  private Scanner scanner;

  Currency(InputStream inputStream) {
    scanner = new Scanner(inputStream);
  }

  void read() {
    String currency = scanner.next().toUpperCase();
    index = CURRENCY_STINGS.indexOf(currency);
    if (index == -1) {
      throw new InputMismatchException();
    }
  }

  boolean hasCurrency() {
    return index != -1;
  }

  @Override
  public String toString() {
    if (index == -1) {
      return "";
    }
    return CURRENCY_STINGS.get(index);
  }
}

package api;

import com.mashape.unirest.http.Unirest;
import java.util.Currency;

public class FixerApi {

  private static final String API_URL = "https://api.fixer.io/{method}";

  FixerApi() {

  }

  void getLatest(Currency base, Currency symbols) {
    Unirest.get(API_URL)
        .routeParam("method", "latest")
        .queryString("base", base.toString())
        .queryString("symbols", symbols.toString())
        .asJsonAsync();
  } 
}

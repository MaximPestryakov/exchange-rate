package api.model;

import java.util.Map;

public class ApiResponse {

  private String base;
  private Map<String, Double> rates;

  public Map<String, Double> getRates() {
    return rates;
  }
}

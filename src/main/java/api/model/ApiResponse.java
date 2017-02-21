package api.model;

import java.util.Map;

public class ApiResponse {

  private String base;
  private String date;
  private Map<String, Double> rates;
  private String error;

  public String getBase() {
    return base;
  }

  public String getDate() {
    return date;
  }

  public Map<String, Double> getRates() {
    return rates;
  }

  public String getError() {
    return error;
  }
}

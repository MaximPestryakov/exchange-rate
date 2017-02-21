package api.model;

public class ApiResponse {

  private String base;
  private String date;
  private RateObject rates;
  private String error;

  public String getBase() {
    return base;
  }

  public String getDate() {
    return date;
  }

  public String getError() {
    return error;
  }

  public double getRate() {
    return rates.value;
  }

  public static class RateObject {

    private String name;
    private double value;

    public RateObject(String name, double value) {
      this.name = name;
      this.value = value;
    }
  }
}

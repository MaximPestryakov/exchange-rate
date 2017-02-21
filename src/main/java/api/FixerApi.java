package api;

import api.model.ApiResponse;
import api.model.ApiResponse.RateObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import util.Cache;

public class FixerApi {

  private static final FixerApi INSTANCE = new FixerApi();
  private static final String API_SCHEME = "https";
  private static final String API_URL = "api.fixer.io";

  private final OkHttpClient client = new OkHttpClient();
  private final Cache cache = new Cache();
  private Gson gson;

  private FixerApi() {
    gson = new GsonBuilder()
        .registerTypeAdapter(RateObject.class, new RatesDeserializer())
        .create();
  }

  public static FixerApi getInstance() {
    return INSTANCE;
  }

  public CompletableFuture<ApiResponse> getLatest(String base, String symbols) {
    CompletableFuture<ApiResponse> future = new CompletableFuture<>();
    new Thread(() -> {
      Request request = new Builder()
          .url(new HttpUrl.Builder()
              .scheme(API_SCHEME)
              .host(API_URL)
              .addPathSegment("latest")
              .addQueryParameter("base", base)
              .addQueryParameter("symbols", symbols)
              .build())
          .get()
          .build();
      String response;
      try {
        response = client.newCall(request).execute().body().string();
        cache.write(base + "_" + symbols, response);
      } catch (IOException e) {
        System.err.println("[Warning] No internet connection");
        response = cache.read(base + "_" + symbols);
        if (response != null) {
          System.err.println("[Warning] Using cache");
        }
      }
      ApiResponse resp = null;
      if (response != null) {
        resp = gson.fromJson(response, ApiResponse.class);
      }
      future.complete(resp);
    }).start();
    return future;
  }
}

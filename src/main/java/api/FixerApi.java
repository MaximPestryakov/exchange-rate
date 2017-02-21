package api;

import api.model.ApiResponse;
import com.google.gson.Gson;
import java.util.concurrent.CompletableFuture;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FixerApi {

  private static final String API_SCHEME = "https";
  private static final String API_URL = "api.fixer.io";

  private final OkHttpClient client = new OkHttpClient();
  private final Gson gson = new Gson();

  public CompletableFuture<ApiResponse> getLatest(String base, String symbols) {
    CompletableFuture<ApiResponse> future = new CompletableFuture<>();
    new Thread(() -> {
      try {
        Request request = new Request.Builder()
            .url(new HttpUrl.Builder()
                .scheme(API_SCHEME)
                .host(API_URL)
                .addPathSegment("latest")
                .addQueryParameter("base", base)
                .addQueryParameter("symbols", symbols)
                .build())
            .get()
            .build();
        Response response = client.newCall(request).execute();
        ApiResponse resp = gson.fromJson(response.body().string(), ApiResponse.class);
        System.out.println(resp.getRates().get(symbols));
      } catch (Throwable throwable) {
        future.completeExceptionally(throwable);
      }
    }).start();
    return future;
  }
}

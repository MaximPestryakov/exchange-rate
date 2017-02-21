package api;

import api.model.ApiResponse.RateObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

public class RatesDeserializer implements JsonDeserializer<RateObject> {

  @Override
  public RateObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    RateObject rate = null;
    if (json.isJsonObject()) {
      Set<Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();
      if (!entries.isEmpty()) {
        Entry<String, JsonElement> entry = entries.iterator().next();
        String name = entry.getKey();
        double value = entry.getValue().getAsDouble();
        rate = new RateObject(name, value);
      }
    }
    return rate;
  }
}

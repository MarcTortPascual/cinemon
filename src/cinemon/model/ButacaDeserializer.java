package cinemon.model;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ButacaDeserializer implements JsonDeserializer<List<Butaca>> {
    

  @Override
  public List<Butaca> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Butaca> butacas = new ArrayList<>();

        // Verificamos si el campo es un arreglo
        if (json.isJsonArray()) {
            // Si es un arreglo, deserializamos normalmente en una lista de Butaca
            JsonArray jsonArray = json.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                Butaca butaca = context.deserialize(element, Butaca.class);
                butacas.add(butaca);
            }
        } else if (json.isJsonObject()) {
            // Si es un solo objeto, lo agregamos a la lista
            Butaca butaca = context.deserialize(json, Butaca.class);
            butacas.add(butaca);
        }

        return butacas;
    }
}
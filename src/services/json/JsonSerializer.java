package services.json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import services.Service;

public class JsonSerializer implements Service {
    private final Gson gson;

    public JsonSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.setPrettyPrinting();
        this.gson = gsonBuilder.create();
    }

    public <T> String serialize(T object) {
        return gson.toJson(object);
    }
    public <T> void serializeToFile(T object,String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public <T> T deserialize(String json, Class<T> objectType) {
        return gson.fromJson(json, objectType);
    }

    public <T> T deserializeFromFile(String filename, Class<T> objectType) {
        try(FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, objectType);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

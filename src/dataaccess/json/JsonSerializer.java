package dataaccess.json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    public <T> Set<T> deserializeSetFromFile(String filename, Class<T> elementType) throws IOException {
        ensureFileExists(filename);
        return deserializeFromFile(filename, TypeToken.getParameterized(Set.class, elementType).getType());
    }

    private void ensureFileExists(String filename) throws IOException {
        Path path = Path.of(filename);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private <T> Set<T> deserializeFromFile(String filename, Type type) throws IOException {
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, type);
        }
    }


}

package app;

import com.github.javafaker.Faker;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import models.authorizable.User;
import services.json.JsonSerializer;
import services.ServiceLocator;
import services.logging.ConsoleLogger;

public class App {
    private static final ServiceLocator serviceLocator = ServiceLocator.getLocator();

    public static void main(String[] args) {
        serviceLocator.addService("logger", new ConsoleLogger());
        serviceLocator.addService("serializer",new JsonSerializer());

        JsonSerializer serializer = (JsonSerializer) serviceLocator.getService("serializer");

        serializer.serializeToFile(generateUsers(5),"users.json");

        User[] usersList = serializer.deserializeFromFile("users.json",User[].class);
        for(User user:usersList) {
            System.out.println(user);
        }

    }

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            String password = faker.internet().password();
            String email = faker.internet().emailAddress();
            LocalDate birthday = faker.date()
                .birthday()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            String username = faker.name().username();

            User user = new User(username,password, birthday);
            users.add(user);
        }

        return users;
    }
    public static ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

}

package app;

import com.github.javafaker.Faker;
import dataaccess.repositories.DataContext;
import dataaccess.repositories.DataRepository;
import dataaccess.repositories.JsonDataContext;
import dataaccess.repositories.OrdersRepository;
import dataaccess.repositories.UsersRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import models.authorizable.User;
import dataaccess.json.JsonSerializer;
import services.ServiceLocator;
import services.logging.ConsoleLogger;
import services.logging.Logger;

public class App{
    private final ServiceLocator serviceLocator = ServiceLocator.getLocator();

    public static void main(String[] args) {
        App app = new App();
        JsonSerializer serializer = new JsonSerializer();
        DataContext dataContext = new JsonDataContext(serializer);
        app.serviceLocator.addService("logger", new ConsoleLogger());
        app.serviceLocator.addService("serializer",serializer);
        app.serviceLocator.addService("data",dataContext);

        UsersRepository repository = new UsersRepository(dataContext);
        repository.insert(new User("vasya","lohsdfvd123",LocalDate.now()));
        repository.delete(UUID.fromString("94c36a56-1408-43bc-ae8a-73677540161a"));
        repository.getAll().forEach(System.out::println);

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
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}

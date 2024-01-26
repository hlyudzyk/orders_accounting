package app;

import app.driverside.DriverController;
import com.github.javafaker.Faker;
import dataaccess.repositories.DataContext;
import dataaccess.repositories.DriversRepository;
import dataaccess.repositories.JsonDataContext;
import java.sql.Driver;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import models.authorizable.DriverPojo;
import models.authorizable.UserPojo;
import dataaccess.json.JsonSerializer;
import services.ServiceLocator;
import services.logging.ConsoleLogger;

public class App{
    private final ServiceLocator serviceLocator = ServiceLocator.getLocator();

    public static void main(String[] args) {
        App app = new App();

        JsonSerializer serializer = new JsonSerializer();
        DataContext dataContext = new JsonDataContext(serializer);
        app.serviceLocator.addService("logger", new ConsoleLogger());
        app.serviceLocator.addService("serializer",serializer);
        app.serviceLocator.addService("data",dataContext);

        DriverController driverController = new DriverController(new DriversRepository(dataContext));
        driverController.registerAsDriver("John","1233456",LocalDate.now(),"Opel");

        DriversRepository repo = new DriversRepository(dataContext);
        Set<DriverPojo> driverPojos = repo.getAll();
        for(DriverPojo driverPojo:driverPojos){
            System.out.println(driverPojo.getUserName());
        }


    }


    public static List<UserPojo> generateUsers(int count) {
        List<UserPojo> userPojos = new ArrayList<>();
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

            UserPojo userPojo = new UserPojo(username,password, birthday);
            userPojos.add(userPojo);
        }

        return userPojos;
    }
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}

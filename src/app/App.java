package app;

import app.server.Server;
import dataaccess.repositories.DataContext;
import dataaccess.repositories.JsonDataContext;
import dataaccess.repositories.OrdersRepository;
import dataaccess.json.JsonSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import models.authorizable.AdminPojo;
import models.authorizable.Authorizable;
import models.authorizable.DriverPojo;
import models.authorizable.UserPojo;
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

        Server server = new Server(dataContext, serializer);

        server.run();

    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}

package app;

import app.server.Server;
import dataaccess.repositories.AuthorizableRepository;
import dataaccess.repositories.DataContext;
import dataaccess.repositories.JsonDataContext;
import dataaccess.repositories.OrdersRepository;
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

        Server server = new Server(new AuthorizableRepository(dataContext),
            new OrdersRepository(dataContext), serializer);

        server.run();

    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}

package dataaccess.repositories;

import services.json.JsonSerializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import models.Order;
import models.authorizable.AdminPojo;
import models.authorizable.DriverPojo;
import models.authorizable.UserPojo;

public class JsonDataContext extends DataContext {
    private final JsonSerializer serializer;
    public JsonDataContext(JsonSerializer serializer) {
         this.serializer = serializer;
         loadData();
    }

    protected <T> void loadEntities(String fileName, Class<T> objectType, Set<T> targetSet) {
        try {
            Set<T> dataFromFile = serializer.deserializeSetFromFile(fileName, objectType);
            if (dataFromFile == null) {
                targetSet = new HashSet<>();
                return;
            }
            targetSet = dataFromFile;

        } catch (IOException e) {
            targetSet = new HashSet<>();
        }
    }

    @Override
    protected void loadAdmins() {
        try {
            Set<AdminPojo> adminsFromFile = serializer.deserializeSetFromFile("admins.json", AdminPojo.class);
            if (adminsFromFile == null) {
                this.admins = new HashSet<>();
                return;
            }
            this.admins = adminsFromFile;

        } catch (IOException e) {
            this.admins = new HashSet<>();
        }
    }

    @Override
    protected void loadDrivers() {
        try {
            Set<DriverPojo> driversFromFile = serializer.deserializeSetFromFile("drivers.json", DriverPojo.class);
            if (driversFromFile == null) {
                this.drivers = new HashSet<>();
                return;
            }
            this.drivers = driversFromFile;

        } catch (IOException e) {
            this.drivers = new HashSet<>();
        }
    }

    @Override
    protected void loadUsers() {
        try {
            Set<UserPojo> usersFromFile = serializer.deserializeSetFromFile("users.json", UserPojo.class);
            if (usersFromFile == null) {
                this.users = new HashSet<>();
                return;
            }
            this.users = usersFromFile;

        } catch (IOException e) {
            this.users = new HashSet<>();
        }
    }

    @Override
    protected void loadOrders() {
        try {
            Set<Order> ordersFromFile = serializer.deserializeSetFromFile("orders.json", Order.class);
            if (ordersFromFile == null) {
                this.orders = new HashSet<>();
                return;
            }
            this.orders= ordersFromFile;

        } catch (IOException e) {
            this.orders = new HashSet<>();
        }
    }

    @Override
    public void saveChanges() {
        serializer.serializeToFile(this.orders,"orders.json");
        serializer.serializeToFile(this.users,"users.json");
        serializer.serializeToFile(this.drivers,"drivers.json");
        serializer.serializeToFile(this.admins,"admins.json");
    }


}

package dataaccess.repositories;

import java.util.HashSet;
import java.util.Set;
import models.Order;
import models.authorizable.AdminPojo;
import models.authorizable.DriverPojo;
import models.authorizable.UserPojo;
import services.Service;

public abstract class DataContext implements Service {
    Set<Order> orders;
    Set<UserPojo> users;
    Set<DriverPojo> drivers;
    Set<AdminPojo> admins;

    protected final void loadData(){
        loadOrders();
        loadUsers();
        loadDrivers();
        loadAdmins();
    }
    protected abstract void loadAdmins();
    protected abstract void loadDrivers();
    protected abstract void loadUsers();
    protected abstract void loadOrders();

    public abstract void saveChanges();

}

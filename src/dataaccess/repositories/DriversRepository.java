package dataaccess.repositories;

import java.io.IOException;
import models.authorizable.DriverPojo;

public class DriversRepository extends DataRepository<DriverPojo> {
    private DataContext dataContext;

    public DriversRepository(DataContext context){
        super(context.drivers);
        this.dataContext = context;
    }

    @Override
    public void update(DriverPojo itemToUpdate) throws RuntimeException, IOException {

    }
}

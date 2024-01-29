package dataaccess.repositories;

import java.io.IOException;
import java.util.Set;
import models.authorizable.AdminPojo;

public class AdminsRepository extends DataRepository<AdminPojo> {
    private DataContext dataContext;

    public AdminsRepository(DataContext context){
        super(context.admins);
        this.dataContext = context;
    }

    @Override
    public void update(AdminPojo itemToUpdate) throws RuntimeException, IOException {

    }
}

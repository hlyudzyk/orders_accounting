package dataaccess.repositories;

import java.io.IOException;
import java.util.Optional;
import models.authorizable.UserPojo;

public class UsersRepository extends DataRepository<UserPojo> {
    private DataContext dataContext;

    public UsersRepository(DataContext context){
        super(context.users);
        this.dataContext = context;
    }

    public Optional<UserPojo> findByName(String name){
        return getAll().stream().filter(u->u.getUserName().equals(name)).findFirst();
    }

    @Override
    public void update(UserPojo itemToUpdate) throws RuntimeException, IOException {

    }
}

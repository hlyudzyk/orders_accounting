package dataaccess.repositories;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import models.Order;

public class OrdersRepository extends DataRepository<Order>
{
    private DataContext dataContext;

    public OrdersRepository(DataContext context){
        super(context.orders);
        this.dataContext = context;
    }

    public void saveChanges() {
        dataContext.saveChanges();
    }
    @Override
    public void update(Order itemToUpdate) throws RuntimeException, IOException {

    }
}

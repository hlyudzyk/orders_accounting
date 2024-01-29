package dataaccess.repositories;

import java.io.IOException;
import java.util.List;
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

    public List<Order> getOrdersByUserId(UUID userId){
        return getEntities().stream().filter(o->o.getUserId().equals(userId)).toList();
    }
    @Override
    public void update(Order itemToUpdate) throws RuntimeException {

    }
}

package dataaccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.Order;

public class OrdersRepository implements DataRepository<Order> {

    private List<Order> orders;

    public OrdersRepository() {
        this.orders = new ArrayList<>();
    }

    @Override
    public Optional<Order> find(String id) {
        return Optional.empty();
    }

    @Override
    public void insert(Order itemToInsert){
        orders.add(itemToInsert);
    }

    @Override
    public void update(Order itemToUpdate) throws RuntimeException, IOException {

    }

    @Override
    public void delete(Order itemToDelete) throws RuntimeException {

    }

    @Override
    public List<Order> getAll() {
        return orders;
    }
}

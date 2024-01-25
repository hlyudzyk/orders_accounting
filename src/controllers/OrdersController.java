package controllers;

import app.App;
import dataaccess.repositories.OrdersRepository;
import models.Order;
import dataaccess.json.JsonSerializer;
import services.logging.Logger;

public class OrdersController {
    OrdersRepository ordersRepository;
    Logger logger;
    JsonSerializer serializer;

    public OrdersController(OrdersRepository repository) {

    }

    public void createOrder(){
    }

    public void sendOrderHistory() {

    }


}

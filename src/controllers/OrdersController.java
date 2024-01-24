package controllers;

import app.App;
import dataaccess.OrdersRepository;
import models.Order;
import services.json.JsonSerializer;
import services.logging.Logger;

public class OrdersController {
    OrdersRepository ordersRepository;
    Logger logger;
    JsonSerializer serializer;

    public OrdersController(OrdersRepository repository) {
        this.ordersRepository = repository;
        this.logger = (Logger) App.getServiceLocator().getService("logger");
        this.serializer = (JsonSerializer) App.getServiceLocator().getService("serializer");
    }

    public void createOrder(){
        String orderData = "Here will be order data";
        logger.log("Received CREATE_ORDER request: " + orderData);
        Order order = serializer.deserialize(orderData,Order.class);
        ordersRepository.insert(order);
    }

    public void sendOrderHistory() {
        logger.log("Received ORDER_HISTORY request");
        String orderHistory = serializer.serialize(ordersRepository.getAll());
    }


}

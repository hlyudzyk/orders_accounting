package app.server.controllers;

import app.dto.OrderDTO;
import app.server.Response;
import app.server.Response.ResponseBuilder;
import app.server.Response.StatusCode;
import java.util.List;
import services.json.JsonSerializer;
import dataaccess.repositories.OrdersRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.UUID;
import models.Order;


public class OrdersController {
    OrdersRepository ordersRepository;
    BufferedWriter serverWriter;
    BufferedReader serverReader;
    //Logger logger;
    JsonSerializer serializer;

    public OrdersController(OrdersRepository repository, BufferedWriter writer, BufferedReader reader,
        JsonSerializer serializer) {
        this.ordersRepository = repository;
        this.serverWriter = writer;
        this.serverReader = reader;
        this.serializer = serializer;
        //this.logger = (Logger) App.getServiceLocator().getService("logger");
    }

    public void createOrder(String requestData) throws IOException {
        System.out.println("Received CREATE_ORDER request: " + requestData);
        OrderDTO orderDTO = serializer.deserialize(requestData,OrderDTO.class);

        Order order = new Order(UUID.fromString(orderDTO.userId),orderDTO.locationFrom,orderDTO.locationTo);
        ordersRepository.insert(order);

        serverWriter.write(serializer.serialize(
            new Response(StatusCode.OK,"Order created successfully!"))
        );
        serverWriter.flush();
    }

    public void sendOrderHistory(String requestData) throws IOException {
        System.out.println("Received ORDER_HISTORY request");
        ResponseBuilder responseBuilder = new ResponseBuilder();

        List<Order> ordersFromQuery = ordersRepository.getOrdersByUserId(UUID.fromString(requestData));
        if(ordersFromQuery.isEmpty()) {
            responseBuilder.withStatusCode(StatusCode.NO_CONTENT);
            responseBuilder.withResponseData("No orders were found by this id");
        } else {
            responseBuilder.withStatusCode(StatusCode.OK);
            responseBuilder.withResponseData(serializer.serialize(ordersFromQuery));
        }

        serverWriter.write(serializer.serialize(responseBuilder.build()));

    }

}

package app.server.controllers;

import services.json.JsonSerializer;
import dataaccess.repositories.AdminsRepository;
import dataaccess.repositories.OrdersRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class DriverController {
    OrdersRepository ordersRepository;
    AdminsRepository adminsRepository;
    BufferedWriter serverWriter;
    BufferedReader serverReader;
    //Logger logger;
    JsonSerializer serializer;

    public DriverController(OrdersRepository repository, AdminsRepository adminsRepository,
        BufferedWriter writer, BufferedReader reader, JsonSerializer serializer){

    }


}

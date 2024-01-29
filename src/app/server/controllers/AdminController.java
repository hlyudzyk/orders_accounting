package app.server.controllers;

import services.json.JsonSerializer;
import dataaccess.repositories.AdminsRepository;
import dataaccess.repositories.OrdersRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import models.authorizable.AdminPojo;

public class AdminController {
    OrdersRepository ordersRepository;
    AdminsRepository adminsRepository;
    BufferedWriter serverWriter;
    BufferedReader serverReader;
    //Logger logger;
    JsonSerializer serializer;

    public AdminController(OrdersRepository repository, AdminsRepository adminsRepository, BufferedWriter writer, BufferedReader reader,
        JsonSerializer serializer) {
        this.ordersRepository = repository;
        this.adminsRepository = adminsRepository;
        this.serverWriter = writer;
        this.serverReader = reader;
        this.serializer = serializer;
        //this.logger = (Logger) App.getServiceLocator().getService("logger");
    }
    public void registerAdmin(String requestData) throws IOException {
        System.out.println("Receiver Admin REGISTER request: " + requestData);
        AdminPojo adminPojo = serializer.deserialize(requestData, AdminPojo.class);
        adminsRepository.insert(adminPojo);
        serverWriter.write("Admin registered successfully.\n");
        serverWriter.flush();
    }

}

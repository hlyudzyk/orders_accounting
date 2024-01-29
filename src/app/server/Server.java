package app.server;

import static models.authorizable.Authorizable.*;

import app.Configuration;
import app.exceptions.BadRequestException;
import app.server.controllers.AdminController;
import app.server.controllers.DriverController;
import app.server.controllers.OrdersController;
import app.server.controllers.UserController;
import services.json.JsonSerializer;
import dataaccess.repositories.AdminsRepository;
import dataaccess.repositories.DataContext;
import dataaccess.repositories.OrdersRepository;
import dataaccess.repositories.UsersRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final AdminsRepository adminsRepository;
    private final DataContext dataContext;
    OrdersController ordersController;
    UserController userController;
    AdminController adminController;
    DriverController driverController;
    JsonSerializer serializer;
    public Server(DataContext dataContext,JsonSerializer serializer) {
        this.serializer = serializer;
        this.dataContext = dataContext;
        this.ordersRepository = new OrdersRepository(dataContext);
        this.usersRepository = new UsersRepository(dataContext);
        this.adminsRepository = new AdminsRepository(dataContext);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Configuration.PORT)) {
            System.out.println("Server is waiting for client connections...");

            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String permission = reader.readLine();
            Role role = Role.valueOf(permission);

            switch (role) {
                case USER:
                    handleUser(clientSocket, reader, writer);
                    break;
                case ADMIN:
                    handleAdmin(clientSocket, reader, writer);
                    break;
                case DRIVER:
                    handleDriver(clientSocket, reader, writer);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUser(Socket clientSocket, BufferedReader reader, BufferedWriter writer) throws IOException {
        this.ordersController = new OrdersController(ordersRepository,writer,reader,serializer);
        this.userController = new UserController(usersRepository,writer,reader,serializer);
        String command = reader.readLine();

        if (command != null) {
            Request request = serializer.deserialize(command,Request.class);

            switch (request.header) {
                case "CREATE_ORDER":
                    ordersController.createOrder(request.requestData);
                    break;
                case "ORDER_HISTORY":
                    ordersController.sendOrderHistory(request.requestData);
                    break;
                case "REGISTER":
                    userController.registerUser(request.requestData);
                    break;
                case "LOGIN":
                    userController.loginUser(request.requestData);
                    break;
                default:
                    throw new BadRequestException();
            }
        }
        dataContext.saveChanges();
        writer.flush();
    }

    private void handleAdmin(Socket clientSocket, BufferedReader reader, BufferedWriter writer) throws IOException {
        this.adminController = new AdminController(ordersRepository,adminsRepository,writer,reader,serializer);
        String command = reader.readLine();

        if (command != null) {
            Request request = serializer.deserialize(command,Request.class);

            switch (request.header) {
                case "REGISTER":
                    adminController.registerAdmin(request.requestData);
                    break;
                default:
                    throw new BadRequestException();
            }
        }

        writer.flush();
    }

    private void handleDriver(Socket clientSocket, BufferedReader reader, BufferedWriter writer) throws IOException {
        System.out.println("I'm handling driver");
    }

}

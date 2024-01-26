package app.server;

import app.Configuration;
import dataaccess.json.JsonSerializer;
import dataaccess.repositories.AuthorizableRepository;
import dataaccess.repositories.OrdersRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private final OrdersRepository ordersRepository;
    AuthorizableRepository authorizableRepository;
    OrdersController ordersController;
    JsonSerializer serializer;
    public Server(AuthorizableRepository authorizableRepository,OrdersRepository ordersRepository,
        JsonSerializer serializer) {
        this.authorizableRepository = authorizableRepository;
        this.serializer = serializer;
        this.ordersRepository = ordersRepository;

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
            this.ordersController = new OrdersController(ordersRepository,writer,reader,serializer);
            String command = reader.readLine();

            if (command != null) {
                switch (command) {
                    case "CREATE_ORDER":
                        ordersController.createOrder();
                        break;

                    case "ORDER_HISTORY":
                        ordersController.sendOrderHistory();
                        break;

                    default:
                        System.out.println("400 BAD REQUEST");
                        break;
                }
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

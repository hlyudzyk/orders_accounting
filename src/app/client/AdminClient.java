package app.client;

import app.client.requestcontrollers.AdminRequestController;
import app.exceptions.ServerIsDownException;
import services.json.JsonSerializer;

public class AdminClient {

    public static void main(String[] args) {
        try {
            AdminRequestController adminRequestController = new AdminRequestController(new JsonSerializer());

        } catch (ServerIsDownException e){
            e.printStackTrace();
        }
    }
}

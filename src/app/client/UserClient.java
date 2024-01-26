package app.client;

import app.client.requestcontrollers.UserRequestsController;
import app.exceptions.ServerIsDownException;
import dataaccess.json.JsonSerializer;
import java.time.LocalDate;
import models.authorizable.UserPojo;

public class UserClient {
    public static void main(String[] args){
        try{
            UserRequestsController userRequestsController = new UserRequestsController(
                new UserPojo("John Wick","pass1234", LocalDate.now()),
                new JsonSerializer());
            userRequestsController.requestNewOrder();
            userRequestsController.requestHistory();
        }catch (ServerIsDownException e){
            e.printStackTrace();
        }

    }
}


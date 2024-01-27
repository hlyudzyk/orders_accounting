package app.client;

import app.client.requestcontrollers.UserRequestsController;
import app.exceptions.ServerIsDownException;
import dataaccess.json.JsonSerializer;


public class UserClient {
    public static void main(String[] args){
        try{
            UserRequestsController userRequestsController =
                new UserRequestsController(new JsonSerializer());

            userRequestsController.requestNewOrder();
            userRequestsController.requestHistory();
        }catch (ServerIsDownException e){
            e.printStackTrace();
        }

    }
}


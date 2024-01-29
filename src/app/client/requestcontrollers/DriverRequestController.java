package app.client.requestcontrollers;

import services.json.JsonSerializer;
import java.time.LocalDate;
import models.authorizable.Authorizable.Role;

public class DriverRequestController extends RequestController {
    public DriverRequestController(JsonSerializer dtoSerializer) {
        super(Role.DRIVER,dtoSerializer);
    }

    public void requestDriversOrdersHistory(){

    }

    public void acceptOrder(){

    }

    public void loginDriver(){

    }

    public void registerDriver(){

    }


    @Override
    public void registerAuthorizable(String username, String password, LocalDate birthDay) {

    }

    @Override
    public void loginAuthorizable(String username, String password) {

    }
}

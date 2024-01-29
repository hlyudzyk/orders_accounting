package app.client.requestcontrollers;

import services.json.JsonSerializer;
import java.time.LocalDate;
import models.authorizable.Authorizable.Role;

public class AdminRequestController extends RequestController{
    public AdminRequestController(JsonSerializer dtoSerializer) {
        super(Role.ADMIN, dtoSerializer);
    }

    @Override
    public void registerAuthorizable(String username, String password, LocalDate birthDay) {

    }

    @Override
    public void loginAuthorizable(String username, String password) {

    }
}

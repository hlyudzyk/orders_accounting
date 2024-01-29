package app.client.requestcontrollers;


import app.dto.LoginDTO;
import app.dto.OrderDTO;
import app.dto.RegisterDTO;
import app.annotations.Authorized;
import app.server.Request;
import app.server.Response;
import app.server.Response.StatusCode;
import services.json.JsonSerializer;
import java.time.LocalDate;
import models.authorizable.Authorizable.Role;
import models.authorizable.UserPojo;

public class UserRequestController extends RequestController {

    public UserRequestController(JsonSerializer dtoSerializer) {
        super(Role.USER, dtoSerializer);
    }

    @Authorized
    public String requestNewOrder(String locationFrom,String locationTo) {
        if (!isAuthorized()) {
            System.out.println("Authorization Required");
            return "";
        }

        Response response = sendRequest(
            new Request(isAuthorized(), "CREATE_ORDER",
                dtoSerializer.serialize(
                    new OrderDTO(this.authorizable.getId().toString(),locationFrom,locationTo)
                )
            )
        );
        System.out.println(response.responseData);
        return response.responseData;
    }

    @Authorized
    public void requestHistory(){
        if (!isAuthorized()) {
            System.out.println("Authorization Required");
            return;
        }

        Response response = sendRequest(
            new Request(isAuthorized(), "ORDER_HISTORY", this.authorizable.getId().toString())
        );

        System.out.println(response.responseData);
    }


    @Override
    public void registerAuthorizable(String username, String password,LocalDate birthDay) {
        if(isAuthorized()) return;
        Response response = sendRequest(
            new Request(isAuthorized(),"REGISTER", dtoSerializer.serialize(
                new RegisterDTO(username,password,birthDay,LocalDate.now())))
        );

        if(response.statusCode.equals(StatusCode.CREATED)){
            setAuthorizable(dtoSerializer.deserialize(response.responseData, UserPojo.class));
            System.out.println(response.responseData);
            setAuthorized(true);
        }else {
            System.out.println(response.statusCode + response.responseData);
        }
    }

    @Override
    public void loginAuthorizable(String username, String password) {
        if(isAuthorized()) return;
        Response response = sendRequest(
            new Request(isAuthorized(),"LOGIN",dtoSerializer.serialize(
                new LoginDTO(username, password)))
        );

        if (response.statusCode.equals(StatusCode.OK)) {
            setAuthorizable(dtoSerializer.deserialize(response.responseData, UserPojo.class));
            System.out.println(response.responseData);
            setAuthorized(true);
        } else {
            System.out.println("not logined");
        }

    }
}

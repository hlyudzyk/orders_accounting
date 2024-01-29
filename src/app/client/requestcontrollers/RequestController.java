package app.client.requestcontrollers;

import app.Configuration;
import app.exceptions.ServerIsDownException;
import app.server.Request;
import app.server.Response;
import services.json.JsonSerializer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDate;
import models.authorizable.Authorizable;
import models.authorizable.Authorizable.Role;

abstract class RequestController {
    protected JsonSerializer dtoSerializer;
    protected Authorizable authorizable;
    protected final String SERVER_HOST;
    protected final Role permission;
    protected final int SERVER_PORT;
    private boolean isAuthorized;

    protected RequestController(Role permission,JsonSerializer dtoSerializer) {
        this.permission = permission;
        this.dtoSerializer = dtoSerializer;
        this.isAuthorized = false;
        SERVER_HOST = Configuration.HOST;
        SERVER_PORT = Configuration.PORT;
    }

    protected Response sendRequest(Request request) throws ServerIsDownException {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))
        ) {
            writer.write(permission.toString()+"\n");
            writer.flush();

            writer.write(dtoSerializer.serialize(request)+"\n");
            writer.flush();

            String responseMessage = reader.readLine();

            return dtoSerializer.deserialize(responseMessage,Response.class);

        } catch (IOException e) {
            throw new ServerIsDownException();
        }
    }

    protected void setAuthorizable(Authorizable authorizable){
        this.authorizable = authorizable;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public abstract void registerAuthorizable(String username, String password, LocalDate birthDay);

    public abstract void loginAuthorizable(String username,String password);

    /*protected boolean checkAuthorized() throws UnauthorizedException
    {
        if(isAuthorized) return true;
        else throw new UnauthorizedException();
    }*/

}

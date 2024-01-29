package app.server;


import java.net.Socket;

public class AuthorizationHandler {
    private Socket socket;

    public AuthorizationHandler(Socket socket) {
        this.socket = socket;
    }
}


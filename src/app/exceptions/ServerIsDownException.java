package app.exceptions;

public class ServerIsDownException extends RuntimeException {
    public ServerIsDownException() {
        super("Server is currently not working. Try again later");
    }
}

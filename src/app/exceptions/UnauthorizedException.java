package app.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException() {
        super("Access denied. Authorization required!");
    }
}

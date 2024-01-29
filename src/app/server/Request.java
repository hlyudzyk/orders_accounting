package app.server;

import java.time.LocalDateTime;

public class Request{
    public final boolean isAuthorized;
    public final String header;
    public final String requestData;
    public final LocalDateTime timeStamp;

    public Request(boolean isAuthorized, String header, String requestData) {
        this.isAuthorized = isAuthorized;
        this.header = header;
        this.requestData = requestData;
        this.timeStamp = LocalDateTime.now();
    }

    public enum HEADERS{
        LOGIN,
        REGISTER,
        CREATE_ORDER,
        ORDERS_HISTORY;
    }
}

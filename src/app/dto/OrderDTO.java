package app.dto;

public class OrderDTO {
    public final String userId;
    public final String locationFrom;
    public final String locationTo;

    public OrderDTO(String userId, String locationFrom, String locationTo) {
        this.userId = userId;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
    }
}

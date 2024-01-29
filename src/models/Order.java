package models;


import java.time.LocalDateTime;
import java.util.UUID;

public class Order extends Entity {
    private final UUID userId;
    private UUID driverId;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private String locationFrom;
    private String locationTo;

    public Order(UUID userId,String locationFrom,String locationTo) {
        this.userId = userId;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    @Override
    public String toString() {
        return "OrderData{" +
            "orderId=" + getId() +
            ", user=" + getUserId() +
            ", driver=" + getDriverId() +
            ", orderDateTime=" + createdAt +
            ", locationTo='" + locationTo + '\'' +
            '}';
    }
}

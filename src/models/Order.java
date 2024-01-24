package models;


import java.time.LocalDateTime;
import java.util.UUID;

public class Order extends Entity {
    private final UUID userId;
    private final UUID driverId;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private String locationTo;

    public Order(UUID userId, UUID driverId) {
        this.userId = userId;
        this.driverId = driverId;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

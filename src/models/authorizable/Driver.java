package models.authorizable;

import java.time.LocalDate;
import models.authorizable.Authorizable;

public class Driver extends Authorizable {
    private String car;
    private double averageRank;

    public Driver(String username, String password, LocalDate dateOfBirth, String car, double averageRank) {
        super(dateOfBirth);
        this.setUserName(username);
        this.setPassword(password);
        this.car = car;
        this.averageRank = averageRank;
    }

    public double getAverageRank() {
        return averageRank;
    }

    public void setAverageRank(double averageRank) {
        this.averageRank = averageRank;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}

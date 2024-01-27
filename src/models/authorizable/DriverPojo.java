package models.authorizable;

import java.time.LocalDate;

public class DriverPojo extends Authorizable {
    private String car;

    public DriverPojo(String username, String password, LocalDate dateOfBirth, String car) {
        super(Role.DRIVER,dateOfBirth);
        this.setUserName(username);
        this.setPassword(password);
        this.car = car;
    }


    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "DriverPojo{" +
            "name= " + getUserName() +
            ", car='" + car + '\'' +
            ", role=" + role +
            '}';
    }
}

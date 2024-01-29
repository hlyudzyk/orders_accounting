package models.authorizable;

import java.time.LocalDate;

public class AdminPojo extends Authorizable {

    public AdminPojo(String username, String password, LocalDate dateOfBirth,LocalDate joiningDate) {
        super(Role.ADMIN,dateOfBirth,joiningDate);
        this.setUserName(username);
        this.setPassword(password);
    }
}

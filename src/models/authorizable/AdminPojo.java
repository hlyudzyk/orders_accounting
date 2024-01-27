package models.authorizable;

import java.time.LocalDate;

public class AdminPojo extends Authorizable {

    public AdminPojo(String username, String password, LocalDate dateOfBirth) {
        super(Role.ADMIN,dateOfBirth);
        this.setUserName(username);
        this.setPassword(password);
    }
}

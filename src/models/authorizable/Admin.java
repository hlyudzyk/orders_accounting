package models.authorizable;

import java.time.LocalDate;

public class Admin extends Authorizable {

    public Admin(String username, String password, LocalDate dateOfBirth) {
        super(dateOfBirth);
        this.setUserName(username);
        this.setPassword(password);
    }
}

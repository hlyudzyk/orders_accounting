package models.authorizable;

import java.time.LocalDate;

public class UserPojo extends Authorizable {
    public UserPojo(String username, String password, LocalDate dateOfBirth) {
        super(dateOfBirth);
        this.setUserName(username);
        this.setPassword(password);
    }
}
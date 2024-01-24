package models.authorizable;

import java.time.LocalDate;

public class User extends Authorizable {
    public User(String username, String password, LocalDate dateOfBirth) {
        super(dateOfBirth);
        this.setUserName(username);
        this.setPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username=" + getUserName() +
            ", birth=" + getDateOfBirth() +
            '}';
    }
}

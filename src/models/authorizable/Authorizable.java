package models.authorizable;

import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.management.relation.Role;
import models.Entity;
import models.ErrorTemplates;
import models.exceptions.EntityArgumentException;

public abstract class Authorizable extends Entity {
    private String username;
    private String password;
    private final LocalDate joiningDate;
    protected final Role role;
    private final LocalDate dateOfBirth;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    protected Authorizable(Role role, LocalDate dateOfBirth,LocalDate joiningDate) {
        this.role = role;
        this.joiningDate = joiningDate;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public LocalDate getJoiningDate() {
        return joiningDate;
    }


    public enum Role{
        ADMIN,DRIVER,USER;
    }

    @Override
    public String toString() {
        return "Authorizable{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", joiningDate=" + joiningDate +
            ", role=" + role +
            ", dateOfBirth=" + dateOfBirth +
            '}';
    }
}

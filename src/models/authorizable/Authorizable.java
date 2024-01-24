package models.authorizable;

import java.time.LocalDate;
import java.util.regex.Pattern;
import models.Entity;
import models.ErrorTemplates;
import models.exceptions.EntityArgumentException;

public class Authorizable extends Entity {
    private String userName;
    private String password;
    private final LocalDate joiningDate;
    private final LocalDate dateOfBirth;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Authorizable(LocalDate dateOfBirth) {
        this.joiningDate = LocalDate.now();
        this.dateOfBirth = dateOfBirth;

    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String username) {
        final String templateName = "логіну";

        if (username.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (username.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (username.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 24));
        }
        var pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        if (pattern.matcher(username).matches()) {
            errors.add(ErrorTemplates.ONLY_LATIN.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.userName = username;
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

}

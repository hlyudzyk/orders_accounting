package app.dto;

public class LoginDTO {
    public final String username;
    public final String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

@Data
public class RegistrationUserRequestDto {

    private String username;
    private String password;
    private String email;
    private String role;

    public User getUser() {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);

        return user;
    }

    public String getRole() {
        return role.toUpperCase();
    }
}

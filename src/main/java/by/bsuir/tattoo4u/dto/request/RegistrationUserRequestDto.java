package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.controller.controllerExceptionExtn.EmptyDataException;
import by.bsuir.tattoo4u.controller.controllerExceptionExtn.IncorrectDataInputException;
import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
public class RegistrationUserRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
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

package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.controller.controllerExceptionExtn.EmptyDataException;
import by.bsuir.tattoo4u.controller.controllerExceptionExtn.IncorrectDataInputException;
import by.bsuir.tattoo4u.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;

@Data
public class RegistrationUserRequestDto {

    private final static Gson gson=new Gson();

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

    public static RegistrationUserRequestDto fromJson(String jsonString){
        if(jsonString==null || jsonString.isEmpty()){
            throw new EmptyDataException();
        }
        try {
            return gson.fromJson(jsonString, RegistrationUserRequestDto.class);
        }catch (JsonSyntaxException e){
            throw new IncorrectDataInputException("Incorrect JSON object");
        }

    }
}

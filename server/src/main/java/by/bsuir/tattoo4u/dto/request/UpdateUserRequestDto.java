package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

@Data
public class UpdateUserRequestDto {

    private String username;
    private String email;

    public User getUser(){
        User user=new User();
        user.setEmail(email);
        user.setUsername(username);

        return user;
    }

}

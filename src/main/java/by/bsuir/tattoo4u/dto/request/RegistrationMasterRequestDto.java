package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.Master;
import lombok.Data;

@Data
public class RegistrationMasterRequestDto{

    private String username;
    private String password;
    private String email;
    private String role;
    private Double rating;

    public Master getMaster(){
        Master master=new Master();
//        master.setUsername(username);
//        master.setPassword(password);
//        master.setEmail(email);
        master.setRating(rating);

        return master;
    }

    public String getRole(){
        return role.toUpperCase();
    }
}

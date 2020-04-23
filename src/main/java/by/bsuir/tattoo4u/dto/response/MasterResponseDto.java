package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Master;
import lombok.Data;

@Data
public class MasterResponseDto {

    private String id;
    private String username;
    private String email;
    private String rating;
    private String photo;

    public MasterResponseDto(Master master) {
        this.id = master.getUser().getId().toString();
        this.username = master.getUser().getUsername();
        this.email = master.getUser().getEmail();
        this.rating = master.getRating().toString();
        this.photo = master.getUser().getPhoto().getUrl();
    }
}

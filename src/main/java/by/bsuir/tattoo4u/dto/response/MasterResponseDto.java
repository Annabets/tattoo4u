package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Master;
import lombok.Data;

@Data
public class MasterResponseDto {

    private Long id;
    private String username;
    private String email;
    private Double rating;

    public MasterResponseDto(Master master) {
        this.id = master.getUser().getId();
        this.username = master.getUser().getUsername();
        this.email = master.getUser().getEmail();
        this.rating = master.getRating();
    }
}

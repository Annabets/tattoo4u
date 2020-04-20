package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Studio;
import lombok.Data;

@Data
public class StudioResponseDto {
    private String id;
    private String name;
    private String rating;
    private String description;
    private String address;
    private String owner;
    private String ownerId;
    private String contact;

    public StudioResponseDto(Studio studio) {
        this.id = studio.getId().toString();
        this.address = studio.getAddress();
        this.description = studio.getDescription();
        this.name = studio.getName();
        this.rating = studio.getRating().toString();
        this.owner = studio.getOwner().getUsername();
        this.ownerId = studio.getOwner().getId().toString();
        this.contact = studio.getContact();
    }
}

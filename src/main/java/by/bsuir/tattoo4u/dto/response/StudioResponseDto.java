package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Studio;
import lombok.Data;

@Data
public class StudioResponseDto {
    private Long id;
    private String name;
    private Double rating;
    private String description;
    private String address;
    private String owner;
    private Long ownerId;
    private String contact;

    public static StudioResponseDto fromStudio(Studio studio) {
        StudioResponseDto studioResponse = new StudioResponseDto();
        studioResponse.id = studio.getId();
        studioResponse.address = studio.getAddress();
        studioResponse.description = studio.getDescription();
        studioResponse.name = studio.getName();
        studioResponse.rating = studio.getRating();
        studioResponse.owner = studio.getOwner().getUsername();
        studioResponse.ownerId = studio.getOwner().getId();
        studioResponse.contact = studio.getContact();

        return studioResponse;
    }
}

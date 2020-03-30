package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

@Data
public class StudioResponseDto {
    private String name;
    private Double rating;
    private String description;
    private String address;
    private String owner;

    public static StudioResponseDto fromStudio(Studio studio) {
        StudioResponseDto studioResponse = new StudioResponseDto();
        studioResponse.address = studio.getAddress();
        studioResponse.description = studio.getDescription();
        studioResponse.name = studio.getName();
        studioResponse.rating = studio.getRating();
        studioResponse.owner = studio.getOwner().getUsername();

        return studioResponse;
    }
}

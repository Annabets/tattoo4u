package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.Studio;
import lombok.Data;

@Data
public class StudioRegistrationRequestDto {
    private String name;
    private String description;
    private String address;
    private String contact;

    public Studio getStudio() {
        return new Studio(name, description, address, contact);
    }
}

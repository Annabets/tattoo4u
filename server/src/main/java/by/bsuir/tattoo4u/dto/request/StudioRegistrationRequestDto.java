package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.Studio;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class StudioRegistrationRequestDto {
    private String name;
    private String description;
    private String address;
    private String contact;

    @NotNull(message = "File must not be null")
    private MultipartFile file;

    public Studio getStudio() {
        return new Studio(name, description, address, contact);
    }
}

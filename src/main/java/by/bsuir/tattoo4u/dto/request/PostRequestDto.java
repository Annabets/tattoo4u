package by.bsuir.tattoo4u.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PostRequestDto {
    @NotNull(message = "File must not be null")
    private MultipartFile file;

    @Size(max = 2048, message = "Description size should be no more than 2048 characters")
    private String description;

    @NotNull(message = "Tags must not be null")
    private List<String> tags;
}

package by.bsuir.tattoo4u.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequestDto {
    private MultipartFile file;
    private String description;
    private List<String> tags;
}

package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.Order;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class OrderRequestDto {
    private Long studioId;
    private String description;

    @NotNull(message = "File must not be null")
    private MultipartFile file;

    public Order getOrder() {
        return new Order(description);
    }
}

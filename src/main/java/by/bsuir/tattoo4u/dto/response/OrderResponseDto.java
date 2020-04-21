package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Order;
import lombok.Data;

@Data
public class OrderResponseDto {
    private String description;
    private String studioName;
    private String userName;
    private String userId;
    private String studioId;
    private String photoUrl;
    private boolean finish;

    public OrderResponseDto(Order order) {
        this.description = order.getDescription();
        this.studioId = order.getStudio().getId().toString();
        this.studioName = order.getStudio().getName();
        this.photoUrl = order.getPhoto().getUrl();
        this.userId = order.getAuthor().getId().toString();
        this.userName = order.getAuthor().getUsername();
        this.finish = order.isFinish();
    }
}
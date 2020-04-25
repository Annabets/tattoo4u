package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.StudioFeedback;
import lombok.Data;

@Data
public class StudioFeedbackRequestDto {
    private Long studioId;
    private Double rating;
    private String feedback;

    public StudioFeedback getStudioFeedback() {
        if (feedback.equals("")) {
            return new StudioFeedback(rating);
        } else {
            return new StudioFeedback(feedback, rating);
        }
    }
}

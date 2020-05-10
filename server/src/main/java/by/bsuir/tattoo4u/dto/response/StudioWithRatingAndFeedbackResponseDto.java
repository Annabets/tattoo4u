package by.bsuir.tattoo4u.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class StudioWithRatingAndFeedbackResponseDto {
    private String rating;
    private List<StudioFeedbackResponseDto> feedbacks;

    public StudioWithRatingAndFeedbackResponseDto(Double rating, List<StudioFeedbackResponseDto> feedbacks) {
        this.rating = rating.toString();
        this.feedbacks = feedbacks;
    }
}

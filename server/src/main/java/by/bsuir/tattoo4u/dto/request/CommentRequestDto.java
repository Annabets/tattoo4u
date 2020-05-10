package by.bsuir.tattoo4u.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentRequestDto {
    @NotNull(message = "Comment must not be null")
    @Size(max = 2048, message = "Comment size should be no more than 2048 characters")
    private String comment;
}

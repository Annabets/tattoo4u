package by.bsuir.tattoo4u.dto.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class MasterCommentRequestDto {

    @NotNull
    private Long masterId;

    @Nullable
    private String comment;

    @Nullable
    @Max(value = 10)
    @Min(value = 0)
    private Double rating;
}

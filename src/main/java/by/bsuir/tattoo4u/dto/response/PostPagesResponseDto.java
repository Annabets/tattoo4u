package by.bsuir.tattoo4u.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPagesResponseDto {
    private Integer totalPages;
    private List<PostResponseDto> postResponseDtoList;
}

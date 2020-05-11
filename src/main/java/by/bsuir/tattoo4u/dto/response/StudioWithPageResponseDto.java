package by.bsuir.tattoo4u.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class StudioWithPageResponseDto {
    private List<StudioResponseDto> studios;
    private Integer totalPages;
    private Integer currentPage;

    public StudioWithPageResponseDto(List<StudioResponseDto> studios, Integer totalPages) {
        this.studios = studios;
        this.totalPages = totalPages;
    }
}

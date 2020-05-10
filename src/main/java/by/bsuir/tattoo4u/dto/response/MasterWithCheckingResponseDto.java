package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Master;
import lombok.Data;

@Data
public class MasterWithCheckingResponseDto extends MasterResponseDto{
    
    private boolean inFavorites;
    
    public MasterWithCheckingResponseDto(Master master, boolean inFavorites){
        super(master);
        this.inFavorites=inFavorites;
    }
}

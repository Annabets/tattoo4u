package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudioService {
    void add(Studio studio) throws ServiceException;

    List<StudioResponseDto> takeStudios(Pageable pageable) throws ServiceException;

    List<StudioResponseDto> takeByName(String name, Pageable pageable) throws ServiceException;
}

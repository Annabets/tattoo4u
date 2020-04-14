package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.dto.response.UserResponseDto;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudioService {
    void add(Studio studio) throws ServiceException;

    StudioResponseDto takeStudioById(Long id) throws ServiceException;

    List<StudioResponseDto> takeStudios(Pageable pageable) throws ServiceException;

    List<StudioResponseDto> takeByName(String name, Pageable pageable) throws ServiceException;

    void addMaster(User master, Long studioId) throws ServiceException;

    List<UserResponseDto> takeMasters(String studioName) throws ServiceException;

    void removeMaster(User master, Long studioId) throws ServiceException;
}

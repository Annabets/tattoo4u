package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.dto.response.MasterResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioFeedbackResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioWithPageResponseDto;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.StudioFeedback;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudioService {
    void save(Studio studio) throws ServiceException;

    Studio takeStudioById(Long id) throws ServiceException;

    StudioResponseDto takeStudioResponseDtoById(Long id) throws ServiceException;

    StudioResponseDto takeStudioByIdWithFavourites(Long id, User user) throws ServiceException;

    StudioWithPageResponseDto takeStudios(Pageable pageable) throws ServiceException;

    List<StudioResponseDto> takeNotConfirmStudios() throws ServiceException;

    StudioWithPageResponseDto takeStudiosWithFavourites(Pageable pageable, User user) throws ServiceException;

    StudioWithPageResponseDto takeByName(String name, Pageable pageable) throws ServiceException;

    StudioWithPageResponseDto takeStudiosByNameWithFavourites(String name, Pageable pageable, User user) throws ServiceException;

    void addMaster(User master, Long studioId) throws ServiceException;

    List<MasterResponseDto> takeMasters(Long studioId) throws ServiceException;

    void removeMaster(User master, Long studioId) throws ServiceException;

    void removeStudio(Long id) throws ServiceException;

    void addFeedback(StudioFeedback feedback) throws ServiceException;

    List<StudioFeedbackResponseDto> takeStudiosFeedbacks(Long studioId) throws ServiceException;

    void confirmStudio(Long studioId) throws ServiceException;
}

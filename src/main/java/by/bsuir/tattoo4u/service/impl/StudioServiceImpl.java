package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.dto.response.UserResponseDto;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.StudioRepository;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.StudioService;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {
    private final UserService userService;
    private final StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(UserService userService, StudioRepository studioRepository) {
        this.userService = userService;
        this.studioRepository = studioRepository;
    }

    @Override
    public void add(Studio studio) throws ServiceException {
        studioRepository.save(studio);
    }

    @Override
    public StudioResponseDto takeStudioById(Long id) throws ServiceException {
        Studio studio = studioRepository.getById(id);
        return StudioResponseDto.fromStudio(studio);
    }

    @Override
    public List<StudioResponseDto> takeStudios(Pageable pageable) throws ServiceException {
        Page<Studio> studios = studioRepository.findAll(pageable);

        return compileStudioResponseList(studios);
    }

    @Override
    public List<StudioResponseDto> takeByName(String name, Pageable pageable) throws ServiceException {
        Page<Studio> studios = studioRepository.findAllByNameContainingIgnoreCase(name, pageable);//studioRepository.findAllByName(name, pageable);

        return compileStudioResponseList(studios);
    }

    private List<StudioResponseDto> compileStudioResponseList(Page<Studio> studios) {
        List<StudioResponseDto> page = new LinkedList<>();
        for (Studio studio: studios) {
            page.add(StudioResponseDto.fromStudio(studio));
        }
        return page;
    }

    @Override
    public void addMaster(User master, Long studioId) throws ServiceException {
        //User master = userService.getById(masterId);
        Studio studio = studioRepository.getById(studioId);

        studio.getMasters().add(master);
        studioRepository.save(studio);
    }

    @Override
    public void removeMaster(User master, Long studioId) throws ServiceException {
        //User master = userService.getById(masterId);
        Studio studio = studioRepository.getById(studioId);

        studio.getMasters().remove(master);
        studioRepository.save(studio);
    }

    @Override
    public List<UserResponseDto> takeMasters(String studioName) throws ServiceException {
        Studio studio = studioRepository.getByName(studioName);
        List<User> masters = studio.getMasters();
        return compileMastersList(masters);
    }

    private List<UserResponseDto> compileMastersList(List<User> mastersList) {
        List<UserResponseDto> masters = new LinkedList<>();
        for (User master: mastersList) {
            masters.add(UserResponseDto.fromUser(master));
        }
        return masters;
    }
}

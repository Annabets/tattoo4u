package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.dto.response.MasterResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.StudioRepository;
import by.bsuir.tattoo4u.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public StudioServiceImpl(UserService userService, StudioRepository studioRepository,
                             UserRepository userRepository) {
        this.userService = userService;
        this.studioRepository = studioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Studio studio) throws ServiceException {
        studioRepository.save(studio);
    }

    @Override
    public Studio takeStudioById(Long id) throws ServiceException {
        return studioRepository.getById(id);
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
        for (Studio studio : studios) {
            page.add(new StudioResponseDto(studio));
        }
        return page;
    }

    @Override
    public void addMaster(User master, Long studioId) throws ServiceException {
        Studio studio = studioRepository.getById(studioId);

        studio.getMasters().add(master.getMasterInfo());
        studioRepository.save(studio);
    }

    @Override
    public void removeMaster(User user, Long studioId) throws ServiceException {
        Master master = user.getMasterInfo();
        Studio studio = studioRepository.getById(studioId);

        List<Master> masters = studio.getMasters();//.remove(master);
        masters.remove(master);
        studio.setMasters(masters);
        studioRepository.save(studio);
    }

    @Override
    public List<MasterResponseDto> takeMasters(Long studioId) throws ServiceException {
        Studio studio = studioRepository.getById(studioId);
        List<Master> masters = studio.getMasters();
        return compileMastersList(masters);
    }

    private List<MasterResponseDto> compileMastersList(List<Master> mastersList) {
        List<MasterResponseDto> masters = new LinkedList<>();
        for (Master master : mastersList) {
            masters.add(new MasterResponseDto(master));
        }
        return masters;
    }

    @Override
    public void removeStudio(Long id) throws ServiceException {
        User owner = studioRepository.getById(id).getOwner();
        owner.setStudio(null);

        userRepository.save(owner);
        studioRepository.deleteById(id);
    }
}

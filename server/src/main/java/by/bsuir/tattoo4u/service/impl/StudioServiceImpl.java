package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.dto.response.MasterResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioFeedbackResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.dto.response.StudioWithPageResponseDto;
import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.StudioFeedback;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.StudioFeedbackRepository;
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
import java.util.Set;

@Service
public class StudioServiceImpl implements StudioService {
    private final UserService userService;
    private final StudioRepository studioRepository;
    private final UserRepository userRepository;
    private final StudioFeedbackRepository studioFeedbackRepository;

    @Autowired
    public StudioServiceImpl(UserService userService, StudioRepository studioRepository,
                             UserRepository userRepository, StudioFeedbackRepository studioFeedbackRepository) {
        this.userService = userService;
        this.studioRepository = studioRepository;
        this.userRepository = userRepository;
        this.studioFeedbackRepository = studioFeedbackRepository;
    }

    @Override
    public void save(Studio studio) throws ServiceException {
        Studio temp = studioRepository.getByOwner(studio.getOwner());
        if (temp != null) {
            throw new ServiceException("You can create only one studio");
        }
        studio.getOwner().getMasterInfo().setJob(studio);
        //userRepository.save()

        studioRepository.save(studio);
    }

    @Override
    public Studio takeStudioById(Long id) throws ServiceException {
        return studioRepository.getById(id);
    }

    @Override
    public StudioResponseDto takeStudioResponseDtoById(Long id) throws ServiceException {
        Studio studio = studioRepository.getById(id);
        return new StudioResponseDto(studio);
    }

    @Override
    public StudioResponseDto takeStudioByIdWithFavourites(Long id, User user) throws ServiceException {
        Studio studio = studioRepository.getById(id);
        if (user.getFavouritesStudios().contains(studio)) {
            return new StudioResponseDto(studio, true);
        } else {
            return new StudioResponseDto(studio, false);
        }

    }

    @Override
    public StudioWithPageResponseDto takeStudios(Pageable pageable) throws ServiceException {
        Page<Studio> page = studioRepository.findAllByConfirmationIsTrue(pageable);
        List<StudioResponseDto> studios = compileStudioResponseListFromPage(page);
        return new StudioWithPageResponseDto(studios, page.getTotalPages());
    }

    @Override
    public List<StudioResponseDto> takeNotConfirmStudios() throws ServiceException {
        return compileStudioResponseList(studioRepository.findAllByConfirmationIsFalse());
    }

    @Override
    public StudioWithPageResponseDto takeByName(String name, Pageable pageable) throws ServiceException {
        Page<Studio> page = studioRepository.findAllByNameContainingIgnoreCaseAndConfirmationIsTrue(name, pageable);
        List<StudioResponseDto> studios = compileStudioResponseListFromPage(page);
        return new StudioWithPageResponseDto(studios, page.getTotalPages());
    }

    private List<StudioResponseDto> compileStudioResponseListFromPage(Page<Studio> studios) {
        List<Studio> page = new LinkedList<Studio>(studios.getContent());
        return compileStudioResponseList(page);
    }

    private List<StudioResponseDto> compileStudioResponseList(List<Studio> studios) {
        List<StudioResponseDto> page = new LinkedList<>();
        for (Studio studio : studios) {
            page.add(new StudioResponseDto(studio));
        }
        return page;
    }

    @Override
    public StudioWithPageResponseDto takeStudiosWithFavourites(Pageable pageable, User user) throws ServiceException {
        Page<Studio> page = studioRepository.findAllByConfirmationIsTrue(pageable);
        List<StudioResponseDto> studios = compileStudioWithFavouritesResponseList(page, user);

        return new StudioWithPageResponseDto(studios, page.getTotalPages());
    }

    @Override
    public StudioWithPageResponseDto takeStudiosByNameWithFavourites(String name, Pageable pageable, User user)
            throws ServiceException {

        Page<Studio> page = studioRepository.findAllByNameContainingIgnoreCaseAndConfirmationIsTrue(name, pageable);
        List<StudioResponseDto> studios = compileStudioWithFavouritesResponseList(page, user);

        return new StudioWithPageResponseDto(studios, page.getTotalPages());
    }

    private List<StudioResponseDto> compileStudioWithFavouritesResponseList(Page<Studio> studios, User user) {
        List<StudioResponseDto> page = new LinkedList<>();
        Set<Studio> studioSet = user.getFavouritesStudios();
        for (Studio studio : studios) {
            if (studioSet.contains(studio)) {
                page.add(new StudioResponseDto(studio, true));
            } else {
                page.add(new StudioResponseDto(studio, false));
            }
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

        List<Master> masters = studio.getMasters();
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

    @Override
    public void addFeedback(StudioFeedback feedback) throws ServiceException {
        studioFeedbackRepository.save(feedback);

        Studio studio = feedback.getStudio();
        Double rating = studioFeedbackRepository.avg(studio.getId());
        studio.setRating(rating);
        studioRepository.save(studio);
    }

    @Override
    public List<StudioFeedbackResponseDto> takeStudiosFeedbacks(Long studioId) {
        Studio studio = studioRepository.getById(studioId);
        return compileFeedbackList(studioFeedbackRepository.findAllByStudio(studio));
    }

    private List<StudioFeedbackResponseDto> compileFeedbackList(List<StudioFeedback> feedbackList) {
        List<StudioFeedbackResponseDto> feedbacks = new LinkedList<>();
        for (StudioFeedback feedback : feedbackList) {
            feedbacks.add(new StudioFeedbackResponseDto(feedback));
        }
        return feedbacks;
    }

    @Override
    public void confirmStudio(Long studioId) throws ServiceException {
        Studio studio = studioRepository.getById(studioId);
        studio.setConfirmation(true);
        studioRepository.save(studio);
    }
}

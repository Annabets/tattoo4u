package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.repository.StudioRepository;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {
    private final StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public void add(Studio studio) throws ServiceException {
        studioRepository.save(studio);
    }

    @Override
    public List<StudioResponseDto> takeStudios(Pageable pageable) throws ServiceException {
        Page<Studio> studios = studioRepository.findAll(pageable);

        return compileList(studios);
    }

    @Override
    public List<StudioResponseDto> takeByName(String name, Pageable pageable) throws ServiceException {
        Page<Studio> studios = studioRepository.findAllByName(name, pageable);

        return compileList(studios);
    }

    private List<StudioResponseDto> compileList(Page<Studio> studios) {
        List<StudioResponseDto> page = new LinkedList<>();
        for (Studio studio: studios) {
            page.add(StudioResponseDto.fromStudio(studio));
        }
        return page;
    }
}

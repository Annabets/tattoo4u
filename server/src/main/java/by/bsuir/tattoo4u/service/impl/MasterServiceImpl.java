package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.RoleType;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.MasterRepository;
import by.bsuir.tattoo4u.repository.RoleRepository;
import by.bsuir.tattoo4u.repository.UserRepository;
import by.bsuir.tattoo4u.service.MasterService;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    private MasterRepository masterRepository;
    private UserService userService;

    @Autowired
    public MasterServiceImpl(MasterRepository masterRepository, UserService userService) {
        this.masterRepository = masterRepository;
        this.userService = userService;
    }

    @Override
    public Master add(User user, String roleName) throws ServiceException {

        Master master=new Master();

        User savedUser=userService.register(user, roleName);
        master.setUser(savedUser);
        master.setRating(0.0);

        return masterRepository.save(master);
    }

    @Override
    public Master getById(Long id) {
        User user=userService.getById(id);
        return masterRepository.getByUser(user);
    }

    @Override
    public Master getByName(String username) {
        User user=userService.getByUsername(username);
        return masterRepository.getByUser(user);
    }

    @Override
    public Master getByUser(User user) {
        return masterRepository.getByUser(user);
    }

    @Override
    public List<Master> getAllUsernameContain(String username, Pageable pageable) {
        return compileList(masterRepository.findAllByUser_UsernameContainingIgnoreCase(username, pageable));
    }

    @Override
    public List<Master> getAll(Pageable pageable) {
        return compileList(masterRepository.findAll(pageable));
    }

    private List<Master> compileList(Page<Master> masterPage){
        List<Master> masters=new LinkedList<>();
        for(Master master:masterPage){
            masters.add(master);
        }
        return masters;
    }

    @Override
    public List<Master> getUnemployedMasters() throws ServiceException {
        return masterRepository.findAllByJob(null);
    }
}

package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MasterService {

    Master add(User user, String role) throws ServiceException;

    Master getById(Long id);
    Master getByName(String username);
    Master getByUser(User user);
    List<Master> getAllUsernameContain(String username, Pageable pageable);
    List<Master> getAll(Pageable pageable);
}
